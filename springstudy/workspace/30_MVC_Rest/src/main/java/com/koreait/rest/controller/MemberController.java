package com.koreait.rest.controller;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.rest.command.MemberCommand;
import com.koreait.rest.command.MemberListCommand;
import com.koreait.rest.dto.MemberDto;

@Controller
public class MemberController {

	// field
	private MemberCommand memberCommand;
	
	@Autowired
	private SqlSession sqlSession;
	
	// method
	@RequestMapping(value="member",
			        method=RequestMethod.GET,
			        produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> memberList(Model model) {
		memberCommand = new MemberListCommand();
		return memberCommand.execute(sqlSession, model);
	}
	
}





