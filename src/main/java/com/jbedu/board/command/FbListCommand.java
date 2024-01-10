package com.jbedu.board.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.jbedu.board.dao.FreeBoardDao;
import com.jbedu.board.dto.FboardDto;

public class FbListCommand implements FbCommand{
	
	public void execute(Model model) {
		FreeBoardDao freeBoardDao = new FreeBoardDao();
		ArrayList<FboardDto> dtos = freeBoardDao.list();
		
		model.addAttribute("fboardDtos", dtos);
	}
}