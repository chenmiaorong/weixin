package com.cmr.weixin.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.cmr.weixin.dao.PaperTipsDao;
import com.cmr.weixin.model.Paper;
import com.cmr.weixin.service.PaperDaoService;

public class PaperDaoServiceTest extends BaseSpringTest{

	@Resource(name = "paperDaoService")
	private PaperDaoService paperDaoService;
	
	
	@Autowired
	private PaperTipsDao paperTipsDao;
	
	
	@Test
	public void getPaper(){
		String id = "71";
		List<String> paper = paperDaoService.selectAllPaperIds();
		System.out.println(JSON.toJSONString(paper));
	}
	
	
	@Test
	public void getPapertips(){
		String s = paperTipsDao.getTips();
		System.out.println(s);
	}
	
}
