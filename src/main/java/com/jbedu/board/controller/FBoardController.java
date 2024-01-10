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

import com.jbedu.board.command.FbCommand;
import com.jbedu.board.command.FbContentCommand;
import com.jbedu.board.command.FbDeleteCommand;
import com.jbedu.board.command.FbListCommand;
import com.jbedu.board.command.FbModifyCommand;
import com.jbedu.board.command.FbSearchCommand;
import com.jbedu.board.command.FbWriteCommand;
import com.jbedu.board.dao.FreeBoardDao;
import com.jbedu.board.dto.FboardDto;

@Controller
public class FBoardController {
	
	//DataSource dataSource;
	FbCommand command = null;
	
	@RequestMapping(value = "/")
	public String index() {
		return "redirect:list";
	}
	
	@RequestMapping(value = "/write_form")
	public String write_form() {
		return "write_form";
	}
	
	@RequestMapping(value = "/write")
	public String write(HttpServletRequest request, Model model) {
		
//		String fbtitle = request.getParameter("fbtitle");
//		String fbname = request.getParameter("fbname");
//		String fbcontent = request.getParameter("fbcontent");
		
		model.addAttribute("request", request);
		
		command = new FbWriteCommand();
//		command.execute(fbtitle, fbname, fbcontent);
		command.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping(value = "/list")
	public String list(Model model ) {
		
		command = new FbListCommand();
//		ArrayList<FboardDto> dtos = command.execute();
		command.execute(model);
		
//		model.addAttribute("fboardDtos", dtos);
		
		return "list";
	}
	
	@RequestMapping(value = "/content_view")
	public String content_view(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		
//		String fbnum = request.getParameter("fbnum");//클릭한 글번호
		
		command = new FbContentCommand();
		command.execute(model);
		
//		model.addAttribute("fboardDto", fboardDto);
		
		return "content_view";
	}
	
	@RequestMapping(value = "modify_form")
	public String modify_form(HttpServletRequest request, Model model) {
		
//		String fbnum = request.getParameter("fbnum");//현재 보고 있는 (수정하려는) 글의 번호
		model.addAttribute("request", request);
		
		command = new FbContentCommand();
		command.execute(model);
		
//		model.addAttribute("fboardDto", fboardDto);
		
		return "modify_form";
	}
	
	@RequestMapping(value = "/modify")
	public String modify(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		
//		String fbnum = request.getParameter("fbnum");
//		String fbtitle = request.getParameter("fbtitle");
//		String fbname = request.getParameter("fbname");
//		String fbcontent = request.getParameter("fbcontent");
		
		command = new FbModifyCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping(value = "/delete")
	public String delete(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		
		//String fbnum = request.getParameter("fbnum");
		
		command = new FbDeleteCommand();		
		command.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping(value = "/search")
	public String search(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		
		command = new FbSearchCommand();
		command.execute(model);
		
		return "list";
	}
	
}