/*
 * �� Ŭ������ ����:
 * ������ ���࿡ �ʿ��� SqlSession �� ���� ���� ���� �ֵ��� ���뼺�� ����Ͽ� ������ ��ü
 * Ư�� �� ��ü�� �ν��Ͻ��� ���ø����̼� ���� 1���� �־��ϹǷ� SingleTon �������� �����Ѵ�!
 * 
 * */

package com.koreait.mylegacy.mybatis.config;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Component;

@Component
public class MybatisConfigManager {
	InputStream inputStream;
	SqlSessionFactory sqlSessionFactory;
	//2) �����ڸ� �������Ƿ� ���� Ŭ�������� �ν��Ͻ��� �������������� ����� ����� ����
	//���� ���� Ŭ�������� getter �� ����������
	private static MybatisConfigManager instance;
	
	//3) getter�� ���������� ���� �ν��Ͻ� �޼��� �̹Ƿ� new �� �������� ȣ���Ҽ��ֱ⶧����
	//�� � ��ü�ε� �� �޼��带 ȣ���Ҽ� ����
	//�ذ�å? nmew ���� �ʰ� �Ʒ��� �޼��带 ȣ���Ҽ��ֵ��� static �޼���� ��������
	public static MybatisConfigManager getInstance() {
		//4) �� �޼��� ȣ��� instance ������ null�̶�� ���⼭ �ν��Ͻ��� �����ؼ� null���� �ƴ� ���� ���������� ó������
		if(instance == null) {
			instance = new MybatisConfigManager();
		}
		return instance;
	}


	//1)�����ڸ� ��� �ƹ��� new ���ϰ� �����
	private MybatisConfigManager() {
		String resource = "com/koreait/mylegacy/mybatis/config/config.xml";
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	//session ���� �ݱ�
	public SqlSession getSqlSession() {
		SqlSession sqlSession = null;
		sqlSession=sqlSessionFactory.openSession();
		return sqlSession;
	}


	public void close(SqlSession session) {
		session.close();
	}
	
	
	
}
