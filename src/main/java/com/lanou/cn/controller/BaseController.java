package com.lanou.cn.controller;

import com.lanou.cn.service.BaseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 27/6/17.
 */
@Controller
@RequestMapping("/base")
public class BaseController {

	@Resource
	private BaseService baseService;


	private static final String LOGIN_INFO = "loginInfo";

	/**
	 * 登录页面
	 * @return
	 */
	@RequestMapping("login")
	public String login() {
		return "base/login";
	}

	/**
	 * 注册页面
	 * @return
	 */
	@RequestMapping("register")
	public String register() {
		return "base/register";
	}

	/**
	 * 没有权限页面
	 * @return
	 */
	@RequestMapping("noPermission")
	public String noPermission() {
		return "base/noPermission";
	}

	/**
	 * 登录提交、权限验证
	 * @param params
	 * @return
	 */
	@RequestMapping("loginForm")
	@ResponseBody
	public Map<String,Object> loginForm(@RequestParam Map<String,Object> params, HttpServletRequest request) {
		Map<String,Object> result = baseService.login(params);
		if("success".equals(result.get("result"))) {
			request.getSession().setAttribute(LOGIN_INFO,params.get("username"));
			request.getSession().setMaxInactiveInterval(5*60);//以秒为单位
		}

		return result;
	}

	/**
	 * 新用户注册
	 * 学生完成
	 * @param params
	 * @return
	 */
	@RequestMapping("registerForm")
	@ResponseBody
	public Map<String,Object> registerForm(@RequestParam Map<String,Object> params) {
		Map<String,Object> result = new HashMap<>();
		result = baseService.register(params);
		return result;
	}


}
