package com.olive.approval.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.olive.approval.service.ApprovalService;
import com.olive.dto.Doc_Type;
import com.olive.dto.Document;
import com.olive.dto.Emp;

@Controller
@RequestMapping("/approval/")
public class ApprovalController {
	private ApprovalService approvalService;
	
	@Autowired
	public void setApprovalService(ApprovalService approvalService) {
		this.approvalService=approvalService;
	}
	
	
	@RequestMapping(value = "approvalHome.do", method = RequestMethod.GET)
	public String approvalHome() {
		
		return "approval/approvalHome";
	}
	
	@RequestMapping(value = "DocWrite.do", method = RequestMethod.GET)
	public String docWrite(Model model, HttpServletRequest request) {
		Date nowTime = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		model.addAttribute("time", sf.format(nowTime));
		String empno = request.getUserPrincipal().getName();
		System.out.println(empno);
		Emp emp = approvalService.selectEmp(empno);
		List<Doc_Type> docType = approvalService.selectDocType();
		
		model.addAttribute("docType", docType);
		System.out.println(docType);
		model.addAttribute("emp", emp);
		
		
		return "approval/DocWirte";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@RequestMapping(value="DocWrite.do", method=RequestMethod.POST)
	public String docInsert(Document doc,BindingResult result,HttpServletRequest request) {
		
		approvalService.writeDoc(doc,request);
		return "redirect:/approval/approvalHome.do";
	}
	
	@RequestMapping(value = "PersonalDoc.do", method = RequestMethod.GET)
	public String showPersonalDoc() {
		
		return "approval/PersonalDoc";
	}
	
	@RequestMapping(value = "ProgressDoc.do", method = RequestMethod.GET)
	public String showPregressDoc(Principal p) {
		
		return "approval/ProgressDoc";
	}
}
