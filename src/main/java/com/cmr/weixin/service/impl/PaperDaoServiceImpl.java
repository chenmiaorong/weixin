package com.cmr.weixin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmr.weixin.dao.PaperDao;
import com.cmr.weixin.model.Paper;
import com.cmr.weixin.service.PaperDaoService;

@Service("paperDaoService")
public class PaperDaoServiceImpl implements PaperDaoService{

	@Autowired
	private PaperDao  paperDao;
	
	@Override
	public Paper getPaperById(String Id) {
		return paperDao.selectPaperById(Id);
	}

	@Override
	public List<String> selectAllPaperIds() {
		
		return paperDao.selectAllPaperIds();
	}

}
