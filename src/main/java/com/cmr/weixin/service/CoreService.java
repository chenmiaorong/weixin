package com.cmr.weixin.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.cmr.weixin.model.Paper;
import com.cmr.weixin.model.message.resp.TextMessage;
import com.cmr.weixin.utils.MessageUtil;


/**
 * ���ķ�����
 * 
 * @author liufeng
 * @date 2013-05-20
 */
@Service
public class CoreService {
	
	@Resource(name = "paperDaoService")
	private  PaperDaoService paperDaoService;
	
	
	Logger logger = Logger.getLogger(CoreService.class);
	/**
	 * ����΢�ŷ���������
	 * 
	 * @param request
	 * @return
	 */
	
	
	final String tips = "Ŀǰ�������Ϊreplace. �밴���¹������ѯ��\"71A05\"��"
			+ "71A�����Ծ�ţ�05������š�δ�ϼܵ���Ŀ��������ʦ�Ժ��ֶ��ظ���";
		
	public  String processRequest(HttpServletRequest request) {
		String respMessage = null;
		try {
			// Ĭ�Ϸ��ص��ı���Ϣ����
			String respContent = "�������쳣�����Ժ��ԣ�";

			// xml�������
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			logger.info(JSON.toJSONString(requestMap));
			
			// ���ͷ��ʺţ�open_id��
			String fromUserName = requestMap.get("FromUserName");
			// �����ʺ�
			String toUserName = requestMap.get("ToUserName");
			// ��Ϣ����
			String msgType = requestMap.get("MsgType");

			// �ظ��ı���Ϣ
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);

			// �ı���Ϣ
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				String  paperId  =  requestMap.get("Content");
				if(StringUtils.isNotBlank(paperId)){
					Paper paper = paperDaoService.getPaperById(StringUtils.upperCase(paperId));
					if(paper != null ){
						respContent = paper.getAnswer();
					}else{
						List<String> papaerIds = paperDaoService.selectAllPaperIds();
						respContent = tips.replace("replace", JSON.toJSONString(papaerIds));
					}
				}
				else{
					respContent = "����������";
				}
			}
			// ͼƬ��Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "�����͵���ͼƬ��Ϣ��";
			}
			// ����λ����Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "�����͵��ǵ���λ����Ϣ��";
			}
			// ������Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "�����͵���������Ϣ��";
			}
			// ��Ƶ��Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "�����͵�����Ƶ��Ϣ��";
			}
			// �¼�����
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// �¼�����
				String eventType = requestMap.get("Event");
				// ����
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = "лл���Ĺ�ע��";
				}
				// ȡ������
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO ȡ�����ĺ��û����ղ������ںŷ��͵���Ϣ����˲���Ҫ�ظ���Ϣ
				}
				// �Զ���˵�����¼�
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// �¼�KEYֵ���봴���Զ���˵�ʱָ����KEYֵ��Ӧ
					String eventKey = requestMap.get("EventKey");

					if (eventKey.equals("11")) {
						respContent = "����Ԥ���˵�������";
					} else if (eventKey.equals("12")) {
						respContent = "������ѯ�˵�������";
					} else if (eventKey.equals("13")) {
						respContent = "�ܱ������˵�������";
					} else if (eventKey.equals("14")) {
						respContent = "��ʷ�ϵĽ���˵�������";
					} else if (eventKey.equals("21")) {
						respContent = "�����㲥�˵�������";
					} else if (eventKey.equals("22")) {
						respContent = "������Ϸ�˵�������";
					} else if (eventKey.equals("23")) {
						respContent = "��Ů��̨�˵�������";
					} else if (eventKey.equals("24")) {
						respContent = "����ʶ��˵�������";
					} else if (eventKey.equals("25")) {
						respContent = "������ྲ˵�������";
					} else if (eventKey.equals("31")) {
						respContent = "Q��Ȧ�˵�������";
					} else if (eventKey.equals("32")) {
						respContent = "��Ӱ���а�˵�������";
					} else if (eventKey.equals("33")) {
						respContent = "��ĬЦ���˵�������";
					}
				}
			}

			textMessage.setContent(respContent);
			respMessage = MessageUtil.textMessageToXml(textMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("\n" + respMessage);
		return respMessage;
	}
}