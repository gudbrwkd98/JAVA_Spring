package com.koreait.bootapp0208;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.koreait.bootapp0208.test.Dog;

@SpringBootApplication
public class Bootapp0208Application {

	public static void main(String[] args) {
		SpringApplication.run(Bootapp0208Application.class, args);
	}
	
	//Spring 2.5버전 부터는 xml 을 사용하지 않고, 자바코드에서도 빈들을 생성 및 설정할 수있는 방법을 제공한다..
	//이때 사용되는 어노테이션이 @Bean <bean id ="" class = "대상클래스"> 
	
	@Bean
	public Dog dog() {
		return new Dog();
	};
	

}
