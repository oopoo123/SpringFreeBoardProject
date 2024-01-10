package com.jbedu.board.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.jbedu.board.dao.FreeBoardDao;

public class FbDeleteCommand implements FbCommand {
	
	public void execute(Model model) {
		
		Map<String, Object> requestMap = model.asMap();
		HttpServletRequest request = (HttpServletRequest) requestMap.get("request");
		
		FreeBoardDao freeBoardDao = new FreeBoardDao();
		
		freeBoardDao.delete(request.getParameter("fbnum"));
	}
	
}