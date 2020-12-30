package com.model2.comment.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.model2.domain.Comment;
import com.model2.mybatis.config.MybatisConfigManager;

public class CommentDAO {
	MybatisConfigManager manager = MybatisConfigManager.getInstance();
	public List selectAll(int board_id) {
		List list = null;
		SqlSession session = manager.getSqlSession();
		list = session.selectList("Comment.selectAll",board_id);
		manager.close(session);
		return list;
	}
	
	public int insert(Comment comment) {
		int result = 0;
		SqlSession session = manager.getSqlSession();
		result = session.insert("Comment.insert",comment);
		session.commit();
		manager.close(session);
		return result;
	}
	 
	public int delete(int comment_id) {
		int result = 0;
		SqlSession session = manager.getSqlSession();
		result = session.delete("Comment.delete",comment_id);
		session.commit();
		manager.close(session);
		return result;
	}
}
