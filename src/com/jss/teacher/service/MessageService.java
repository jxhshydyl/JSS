package com.jss.teacher.service;

import java.util.List;
import com.jss.teacher.dao.MessageDao;
import com.jss.teacher.pojo.MessageDetailPojo;
import com.jss.teacher.pojo.MessagePojo;

public class MessageService {
	/**
	 * 查询所有提问信息
	 */
	public List<MessagePojo> queryAllMessage(String tno,int pageNow,int pageSize){
		MessageDao dao=new MessageDao();
		List<MessagePojo> list = dao.queryAllMessage(tno,pageNow,pageSize);
		return list;
	}
	/**
	 * 根据条件查询所有提问信息
	 */
	public List<MessagePojo> queryMessage(String tno,String condition,int pageNow,int pageSize){
		MessageDao dao=new MessageDao();
		List<MessagePojo> list = dao.queryMessage(tno,condition,pageNow,pageSize);
		return list;
	}
	/**
	 * 查询所有提问信息的数量
	 */
	public int queryMessagesCount(String tno) {
		MessageDao dao=new MessageDao();
		int num=0;
		num=dao.queryAllMessageCount(tno);
		return num;
	}
	/**
	 * 根据条件查询所有提问信息的数量
	 */
	public int queryMessagesCount(String tno, String condition) {
		MessageDao dao=new MessageDao();
		int num=0;
		num=dao.queryMessageCount(tno,condition);
		return num;
	}
	/**
	 * 删除信息
	 * @param qid
	 */
	public void deleteMessage(int[] qid) {
		MessageDao dao=new MessageDao();
		for(int i=0;i<qid.length;i++){
			dao.deleteMessage(qid[i]);
		}
	}
	/**
	 * 根据消息编号查询详细信息
	 * @param Ano
	 */
	public List<MessageDetailPojo> queryMessageDetail(String Ano) {
		MessageDao dao=new MessageDao();
		List<MessageDetailPojo> list=dao.queryMessageDetail(Ano);
		return list;
	}
}
