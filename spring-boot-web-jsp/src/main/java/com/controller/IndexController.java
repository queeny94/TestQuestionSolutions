package com.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.pojo.UserDetails;

@Controller
public class IndexController {

	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		return "welcome";
	}

	@RequestMapping(value = "/addDetails",method = RequestMethod.POST)
	public String addDetails(HttpServletRequest request, HttpServletResponse response) {
		try {
			addDetailsToSession(request.getParameter("bankName"),request.getParameter("cardnumber"),request.getParameter("expirydate"),request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "welcome";
	}

	@PostMapping
	@RequestMapping(value = "/addBulkDetails")
	public String addBulkDetails(@RequestParam("file") MultipartFile file,HttpServletRequest request, HttpServletResponse response) {
		try {
			String line;
			InputStream is = file.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				//try {
					String[] temp = line.split(",");
					addDetailsToSession(temp[0], temp[1], temp[2], request);
				/*} catch (Exception e) {
					System.err.println("Error while processing "+line);
					e.printStackTrace();
				}*/
			}
			br.close();
			request.setAttribute("fileuploadstatus", "File uploaded successfully");
		} catch (Exception e) {
			request.setAttribute("fileuploadstatus", "File upload failed");
			e.printStackTrace();
		}
		return "welcome";
	}
	@RequestMapping(value = "/logout",method = RequestMethod.POST)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session= request.getSession();
			session.invalidate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "welcome";
	}
	@SuppressWarnings("unchecked")
	private void addDetailsToSession(String bankname, String cardNumber, String expdate, HttpServletRequest request) {
		try {
			String sessionname = "userDetails";
			HttpSession session= request.getSession();
			List<UserDetails> userDetails = new ArrayList<UserDetails>();
			if(null!=session.getAttribute(sessionname)) {
				userDetails = (List<UserDetails>) session.getAttribute(sessionname);
			}
			UserDetails details = new UserDetails(bankname, cardNumber, expdate);
			userDetails.add(details);
			Collections.sort(userDetails);
			session.setAttribute(sessionname, userDetails);
		} catch (Exception e) {
			request.setAttribute("status", "Invalid");
			e.printStackTrace();
		}
	}
}
