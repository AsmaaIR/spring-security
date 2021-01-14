package com.springsecurity.demo.controller;

import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springsecurity.demo.dto.CustomUser;
import com.springsecurity.demo.entity.User;
import com.springsecurity.demo.service.UserService;

@Controller
@RequestMapping("/register")
public class RegistrationController {

	@Autowired
	private UserService userService;

	private Logger logger = Logger.getLogger(getClass().getName());

	/*
	 * WebDataBinder extends DataBinder.
	 * 
	 * It can be used to register custom formatter, validators and PropertyEditors.
	 * 
	 * //StringTrimmerEditor is a class with two constructors. The one we're using
	 * here is the one which takes only a boolean value. if it set to true, an empty
	 * String is to be transformed into null i.e empty is treated as null. Finally,
	 * we're registering it using WebDataBinder.
	 * 
	 * @InitBinder is something we use to customize the request parameter data
	 * binding. for more details about @InitBinder and WebDataBinder, read this:
	 * https://www.intertech.com/spring-frameworks-webdatabinder/ and this :
	 * https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#
	 * mvc-ann-initbinder
	 * https://www.logicbig.com/tutorials/spring-framework/spring-web-mvc/spring-
	 * custom-property-editor.html
	 *
	 * The method annotated with @InitBinder will be called before loading the form
	 **/

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		// add an initBinder ... to convert trim input strings
		// remove leading and trailing whitespace
		// StringTrimmerEditor will apply in all fields
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@GetMapping("/showRegistrationForm")
	public String showMyRegPage(Model theModel) {

		theModel.addAttribute("customUser", new CustomUser());
		return "registration-form";
	}

	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(@Valid @ModelAttribute("customUser") CustomUser theCustomUser,
			BindingResult theBindingResult, Model theModel) {

		String userName = theCustomUser.getUserName();
		logger.info("Processing registration form for: " + userName);

		// form validation
		if (theBindingResult.hasErrors()) {
			return "registration-form";
		}

		// check the database if user already exists
		User existing = userService.findByUserName(userName);
		if (existing != null) {
			theModel.addAttribute("customUser", new CustomUser());
			theModel.addAttribute("registrationError", "User name already exists.");

			logger.warning("User name already exists.");
			return "registration-form";
		}
		// create user account
		userService.save(theCustomUser);

		logger.info("Successfully created user: " + userName);

		return "registration-confirmation";
	}
}
