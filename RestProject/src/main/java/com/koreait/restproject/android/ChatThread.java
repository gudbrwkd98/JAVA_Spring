package com.koreait.restproject.android;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

//소켓을 가지고 입력과 출력을 수행하되 다른 객체와 독립적으로 수행될 수있어야 하므로
//쓰레드로 정의한다!!
public class ChatThread extends Thread{
	Socket socket;
	BufferedReader buffr;
	BufferedWriter buffw;
	boolean flag = true;
	ChatServer chatServer;
	public ChatThread(Socket socket,ChatServer chatServer) {
		// TODO Auto-generated constructor stub
		this.socket = socket;
		this.chatServer = chatServer;
		try {
			buffr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	

	//메시지 청취
	public void listen() {
		String msg = null;
		try {
			msg = buffr.readLine();
			//로그에 남기기 
			chatServer.area.append(msg+"\n");
			for(ChatThread chatThread : chatServer.vec) {//접속한 모든 접속자에게 대해 send 호출 == broad casting 방송하자 
				chatThread.send(msg);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//전송 
	public void send(String msg) {
		try {
			buffw.write(msg+"\n");
			buffw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(flag) {
			listen();
		}
	}
	

}
