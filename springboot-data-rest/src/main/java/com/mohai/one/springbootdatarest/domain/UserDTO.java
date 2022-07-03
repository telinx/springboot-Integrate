package com.mohai.one.springbootdatarest.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @Auther: moerhai@qq.com
 * @Date: 2020/7/22 01:00
 */
@Entity
@Data
@RequiredArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class UserDTO {

    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private String sex;
    @JsonIgnore  //可以隐藏属性
    private String password;

    @CreatedDate
    LocalDateTime createdDate;
    @LastModifiedDate
    LocalDateTime modifiedDate;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    AddressDTO addressDTO;

}