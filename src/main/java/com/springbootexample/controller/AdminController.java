package com.springbootexample.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springbootexample.model.Category;
import com.springbootexample.model.Duration;
import com.springbootexample.model.Membership;
import com.springbootexample.pojo.StaffDetails;
import com.springbootexample.services.AdminService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@RequestMapping(value = "/staff")
    public String handleStaffRequest(HttpServletRequest request, Model model,@ModelAttribute StaffDetails staffDetails) {
		model.addAttribute("view", "staff");
		if(request.getMethod().matches("GET")) {
	        return "/base/base";
		}
		adminService.saveStaffDetails(staffDetails);
		return "redirect:/admin/staff";
    }
		
	@RequestMapping(value = "/setup")
    public ModelAndView handleSetupRequest(HttpServletRequest request, ModelAndView model,
    		@ModelAttribute Membership membership,
    		@ModelAttribute Duration duration) {
		model.addObject("view", "setup");
		if(request.getMethod().matches("GET")) {
			model.setViewName("/base/base");
			model.addObject("durationObject", new Duration());
			model.addObject("membershipObject", new Membership());
			return model;
		}
		if(duration != null) {
			adminService.saveDuration(duration);
		}
		if(membership != null) {
			adminService.saveMember(membership);
		}
		return new ModelAndView("redirect:/admin/setup");
    }
	
	@RequestMapping(value = "/services")
    public ModelAndView services(HttpServletRequest request, ModelAndView model,
    		@ModelAttribute Category category) {
		model.addObject("view", "services");
		if(request.getMethod().matches("GET")) {
			model.setViewName("/base/base");
			model.addObject("categoryObject", new Category());
			return model;
		}
		if(category != null) {
			adminService.saveCategory(category);
		}
		return new ModelAndView("redirect:/admin/services");
    }
}