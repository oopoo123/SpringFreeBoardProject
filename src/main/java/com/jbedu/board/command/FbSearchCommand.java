package com.jbedu.board.command;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.jbedu.board.dao.FreeBoardDao;
import com.jbedu.board.dto.FboardDto;

public class FbSearchCommand implements FbCommand {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		
		Map<String, Object> requestMap = model.asMap();
		HttpServletRequest request = (HttpServletRequest) requestMap.get("request");
		
		String searchKey = request.getParameter("searchKey");		
			
		FreeBoardDao freeBoardDao = new FreeBoardDao();		
		ArrayList<FboardDto> dtos = freeBoardDao.search_list(searchKey);
		
		model.addAttribute("fboardDtos", dtos);
		
	}

}