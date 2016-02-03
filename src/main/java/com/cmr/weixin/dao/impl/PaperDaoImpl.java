package com.cmr.weixin.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.cmr.weixin.dao.PaperDao;
import com.cmr.weixin.model.Paper;

@Repository
public class PaperDaoImpl extends SqlSessionDaoSupport  implements PaperDao {

	@Override
	public Paper selectPaperById(String paperid) {
		// TODO Auto-generated method stub
		return this.getSqlSession().selectOne("com.cmr.weixin.selectPaperById", paperid);
	}

	@Override
	public List<String> selectAllPaperIds() {
		// TODO Auto-generated method stub
		
		return this.getSqlSession().selectList("com.cmr.weixin.selectAllPaperIds");
	}

}
