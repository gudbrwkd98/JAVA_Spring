/*
 * 이 클래스의 목적:
 * 쿼리문 수행에 필요한 SqlSession 을 보다 쉽게 얻어갈수 있도록 재사용성을 고려하여 정의한 객체
 * 특히 이 객체의 인스턴스는 어플리케이션 내에 1개만 둬야하므로 SingleTon 패턴으로 정의한다!
 * 
 * */

package com.model2.mybatis.config;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisConfigManager {
	InputStream inputStream;
	SqlSessionFactory sqlSessionFactory;
	//2) 생성자를 묶었으므로 현재 클래스에서 인스턴스를 제공하지않으면 사용할 방법이 없다
	//따라서 현재 클래스에서 getter 를 제공해주자
	private static MybatisConfigManager instance;
	
	//3) getter는 제공했으나 역시 인스턴스 메서드 이므로 new 한 다음에나 호출할수있기때문에
	//그 어떤 객체로도 이 메서드를 호출할수 없다
	//해결책? nmew 하지 않고도 아래의 메서드를 호출할수있도록 static 메서드로 정의하자
	public static MybatisConfigManager getInstance() {
		//4) 이 메서드 호출시 instance 변수가 null이라면 여기서 인스턴스를 생성해서 null값이 아닌 값을 가져가도록 처리하자
		if(instance == null) {
			instance = new MybatisConfigManager();
		}
		return instance;
	}


	//1)생성자를 묶어서 아무나 new 못하게 만들기
	private MybatisConfigManager() {
		String resource = "com/model2/mybatis/config/config.xml";
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	//session 얻기와 닫기
	public SqlSession getSqlSession() {
		SqlSession sqlSession = null;
		sqlSession=sqlSessionFactory.openSession();
		return sqlSession;
	}


	public void close(SqlSession session) {
		session.close();
	}
	
	
	
}
