package com.koreait.restproject.android;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatServer extends JFrame {
	JPanel p_north;
	JTextField t_port;
	JButton bt_start;
	JTextArea area;
	JScrollPane scroll;
	Thread thread; //서버 가동 쓰레드 , 메인 쓰레드가 서버소켓의 accept() 에 의해  대기 상태에 빠지면 안되므로..
	ServerSocket server ; //접속 감지 소켓
	Vector<ChatThread> vec;
	public ChatServer() {
		p_north = new JPanel();
		t_port = new JTextField("9999");
		bt_start = new JButton("가동");
		area = new JTextArea();
		scroll = new JScrollPane(area);
		vec = new Vector();
		
		p_north.add(t_port);
		p_north.add(bt_start);
		add(p_north,BorderLayout.NORTH);
		add(scroll);
		
		bt_start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					startServer();
			}
		});
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				stopServer();
			}
		});
		
		setSize(300,400);
		setVisible(true);
		
		
	}
	
	public void stopServer() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void startServer() {
		thread = new Thread() {
			@Override
			public void run() {
				try {
					server = new ServerSocket(Integer.parseInt(t_port.getText()));
					area.append("서버 가동 ! \n");
					
					while(true) {
					Socket socket = server.accept();//클라이언트 접속할떄까지 대기 
					area.append("접속 감지!!\n");
					
					ChatThread chatThread = new ChatThread(socket,ChatServer.this);
					chatThread.start(); // 대화 시작!!
					
					//벡터에 지금 생성된 쓰레드 추가 
					vec.add(chatThread);
					area.append("현재 접속자수" + vec.size());
					}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		thread.start();
	}
	
	public static void main(String[] args) {
		new ChatServer();
	}
}
