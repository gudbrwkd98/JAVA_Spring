package test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/*
 * 자바 스탠다드에서는 SOcket(stateful:연결지속됨) 통신뿐만 아니라 
 * 웹서버와의 HTTP통신 (stateless) 도 지원한다
 * 
 * */
public class HttpTest {
	
	URL url;
	HttpURLConnection con; //http 통신 객체
	OutputStream os; 
	
	public HttpTest() {
		// TODO Auto-generated constructor stub
		try {
			url = new URL("http://localhost:8888/rest/member");//요청주소
			con = (HttpURLConnection)url.openConnection(); //커넥션 객체 생성
			con.setRequestMethod("POST"); //post방식
			con.setRequestProperty("Content-Type", "application/json;utf-8"); //http통신시 헤더 정보 구성 
			//웹서버에 요청시작
			con.setDoOutput(true);
			OutputStream os = con.getOutputStream();
			os.write(0);
			
 		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	public static void main(String[] args) {
		new HttpTest();
	}
}
