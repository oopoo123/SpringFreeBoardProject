package com.jbedu.board.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.jbedu.board.dao.FreeBoardDao;

public class FbModifyCommand implements FbCommand{
	public void execute(Model model) {
		
		Map<String, Object> requestMap = model.asMap();
		HttpServletRequest request = (HttpServletRequest) requestMap.get("request");
		
		String fbnum = request.getParameter("fbnum");
		String fbtitle = request.getParameter("fbtitle");
		String fbname = request.getParameter("fbname");
		String fbcontent = request.getParameter("fbcontent");
			
		FreeBoardDao freeBoardDao = new FreeBoardDao();		
		freeBoardDao.modify(fbtitle, fbname, fbcontent, fbnum);
	}
}