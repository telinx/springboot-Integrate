package com.mohai.one.springbootsecuritydynamic.config;

import com.mohai.one.springbootsecuritydynamic.filter.DynamicallyUrlFilterSecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.*;

/**
 *
 * @Auther: moerhai@qq.com
 * @Date: 2020/9/26 09:57
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public DynamicallyUrlFilterSecurityInterceptor dynamicallyUrlInterceptor()  throws Exception {
        DynamicallyUrlFilterSecurityInterceptor interceptor = new DynamicallyUrlFilterSecurityInterceptor();
        LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap = new LinkedHashMap<>();

        interceptor.setSecurityMetadataSource(new MyFilterSecurityMetadataSource(requestMap));
        // ?????? RoleVoter ??????
        List<AccessDecisionVoter<? extends Object>> decisionVoters = new ArrayList<AccessDecisionVoter<? extends Object>>();
        decisionVoters.add(new RoleVoter());
        // ???????????????????????????
        interceptor.setAccessDecisionManager(new DynamicallyUrlAccessDecisionManager(decisionVoters));
        // ???????????????????????????
        interceptor.setAuthenticationManager(authenticationManagerBean());
        interceptor.afterPropertiesSet();
        return interceptor;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // ?????????????????????
        http.addFilterAfter(dynamicallyUrlInterceptor(), FilterSecurityInterceptor.class)
                .authorizeRequests() // ???????????????????????????
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated() // ????????????????????????????????????????????????
                .and()
                .formLogin().loginPage("/login").permitAll() // ????????????
                .and()
                .logout().permitAll(); // ????????????
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
//                .exceptionHandling()
//                .authenticationEntryPoint(problemSupport)
//                .accessDeniedHandler(problemSupport)
//                .and()
//                .csrf()
//                .disable()
//                .headers()
//                .frameOptions()
//                .disable()
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                // ?????????accessDecisionManager
//                .accessDecisionManager(accessDecisionManager())
//
//                .and()
//                .apply(securityConfigurerAdapter());
//
//    }


    @Bean
    public AccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter<? extends Object>> decisionVoters
                = Arrays.asList(
                new WebExpressionVoter(),
                // new RoleVoter(),
                new RoleBasedVoter(),
                new AuthenticatedVoter());
        return new UnanimousBased(decisionVoters);
    }

}