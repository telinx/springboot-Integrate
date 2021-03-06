package com.mohai.one.app.core;

import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import io.jsonwebtoken.io.Encoders;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.util.Base64Utils;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

//@SpringBootTest
class AppCoreApplicationTests {

	@Test
	void contextLoads() {
		String str = "测试base64";
		byte[] bytes = str.getBytes();
		//Base64 加密
		String encoded = Base64.getEncoder().encodeToString(bytes);
		System.out.println("Base 64 加密后：" + encoded);
		//Base64 解密
		byte[] decoded = Base64.getDecoder().decode(encoded);
		String decodeStr = new String(decoded);
		System.out.println("Base 64 解密后：" + decodeStr);

		System.out.println(new String(Base64.getDecoder().decode("ZmQ0ZGI5NjQ0MDQwY2I4MjMxY2Y3ZmI3MjdhN2ZmMjNhODViOTg1ZGE0NTBjMGM4NDA5NzYxMjdjOWMwYWRmZTBlZjlhNGY3ZTg4Y2U3YTE1ODVkZDU5Y2Y3OGYwZWE1NzUzNWQ2YjFjZDc0NGMxZWU2MmQ3MjY1NzJmNTE0MzI=")));
	}

	@Test
	void testRsa(){
		RSA rsa = new RSA();
		byte[] bs = rsa.encrypt("qqqqqqq".getBytes(),KeyType.PublicKey);
		System.out.println(new String(bs));
		String s = rsa.decryptStr("1233445", KeyType.PrivateKey);
		System.out.println(s);
	}

	@Test
	void testBase64(){
		//获取盐
		String gensalt = BCrypt.gensalt();
		System.out.println("salt:"+gensalt);
		//将密码传入使用hashpw加密
		String saltPassword = BCrypt.hashpw("123456", gensalt); System.out.println("本次生成的密码:"+saltPassword);
		boolean checkpw = BCrypt.checkpw("123456", saltPassword);
		System.out.println("密码校验结果:"+checkpw);

		System.out.println(new String(Base64Utils.encode("123456".getBytes())));
	}

	@Test
	void testBase(){
		System.out.println(Encoders.BASE64.encode("123456".getBytes()));
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("123456");

		System.out.println(new String(apiKeySecretBytes));

		System.out.println(enCodeBase("123456"));

	}

	public static String enCodeBase(String str){
		byte[] enCodeBase;
		try {
			enCodeBase = org.apache.commons.codec.binary.Base64.encodeBase64(str.getBytes("utf-8"));
			String enCode = new String(enCodeBase);
			return enCode;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "出现异常";
		}
	}

	//base64位的解密
	public static String deCodeBase(String str){
		byte[] deCodeBase64;
		try {
			deCodeBase64 = org.apache.commons.codec.binary.Base64.decodeBase64(str.getBytes("utf-8"));
			String deCode = new String(deCodeBase64);
			return deCode;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return  "出现异常";
		}
	}
	
	
}
