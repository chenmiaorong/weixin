package com.cmr.weixin.service;

import java.util.List;

import com.cmr.weixin.model.Paper;

public interface PaperDaoService {
	public Paper getPaperById(String Id);
	
	public List<String> selectAllPaperIds();
}
