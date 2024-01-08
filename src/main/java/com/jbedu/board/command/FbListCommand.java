package com.jbedu.board.command;

import java.util.ArrayList;

import com.jbedu.board.dao.FreeBoardDao;
import com.jbedu.board.dto.FboardDto;

public class FbListCommand {
	
	public ArrayList<FboardDto> execute() {
		FreeBoardDao freeBoardDao = new FreeBoardDao();
		ArrayList<FboardDto> dtos = freeBoardDao.list();
		
		return dtos;
	}
}