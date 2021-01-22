package com.koreait.restproject.message;

import lombok.Data;

//이객체에 대한 getter/setter 를 정의

@Data
public class Message {
	//클라이언트가 받게될 에러 메시지
	private String msg; 
	private int statusCode; //http 상태 코드
}
