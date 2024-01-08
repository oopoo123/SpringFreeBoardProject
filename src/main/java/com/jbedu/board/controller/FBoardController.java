package com.jbedu.board.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jbedu.board.command.FbWriteCommand;
import com.jbedu.board.command.FbListCommand;
import com.jbedu.board.dao.FreeBoardDao;
import com.jbedu.board.dto.FboardDto;

@Controller
public class FBoardController {
	
	//DataSource dataSource;
	
	@RequestMapping(value = "/write_form")
	public String write_form() {
		return "write_form";
	}
	
	@RequestMapping(value = "/write")
	public String write(HttpServletRequest request) {
		
		String fbtitle = request.getParameter("fbtitle");
		String fbname = request.getParameter("fbname");
		String fbcontent = request.getParameter("fbcontent");
		
		FbWriteCommand command = new FbWriteCommand();
		command.execute(fbtitle, fbname, fbcontent);
	
		
		return "redirect:list";
	}
	
	@RequestMapping(value = "/list")
	public String list(Model model ) {
		
		FbListCommand command = new FbListCommand();
		ArrayList<FboardDto> dtos = command.execute();
		
		model.addAttribute("fboardDtos", dtos);
		
		return "list";
	}
	
}