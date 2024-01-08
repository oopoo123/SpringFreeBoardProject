package com.jbedu.board.command;

import com.jbedu.board.dao.FreeBoardDao;
import com.jbedu.board.dto.FboardDto;

public class FbContentCommand {
	
	public FboardDto execute(String fbnum) {
		FreeBoardDao freeBoardDao = new FreeBoardDao();
		FboardDto fboardDto = freeBoardDao.content_view(fbnum);
		
		return fboardDto;
	} 

}