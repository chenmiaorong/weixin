package com.cmr.weixin.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.cmr.weixin.dao.PaperTipsDao;
@Repository
public class PaperTipsDaoImpl extends SqlSessionDaoSupport implements PaperTipsDao{

	@Override
	public String getTips() {
		// TODO Auto-generated method stub
		return this.getSqlSession().selectOne("com.cmr.weixin.selectPaperTips");
	}

}
