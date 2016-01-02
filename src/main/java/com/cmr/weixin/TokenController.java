package com.cmr.weixin;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmr.weixin.main.MenuManager;
import com.cmr.weixin.service.CoreService;
import com.cmr.weixin.utils.SignUtil;

@Controller
public class TokenController {

//	@ResponseBody
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String getToken(@RequestParam String signature, @RequestParam String echostr, @RequestParam String timestamp,
//			@RequestParam String nonce) {
//		
//		if(SignUtil.checkSignature(signature, timestamp, nonce)){
//			return echostr;
//		}else
//			return "404";
//	}
	
	@ResponseBody
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String Test() {
			return "404";
	}
	
	@ResponseBody
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public void Rundemo() {
		MenuManager.Run(null);
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/", produces="text/plain;charset=UTF-8", method = RequestMethod.POST)
	public String messageInput(HttpServletRequest request,HttpServletResponse response ) throws IOException {
		String respMessage = CoreService.processRequest(request);
		return respMessage;
	}
}
