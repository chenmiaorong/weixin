package com.cmr.weixin.dao;

import java.util.List;

import com.cmr.weixin.model.Paper;

public interface PaperDao {
	Paper selectPaperById(String paperid);
	
	List<String> selectAllPaperIds();
}
