package com.lanou.cn.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.cn.service.CpnService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 27/6/17.
 */
@Controller
@RequestMapping("/cpn")
public class CpnController {
	@Resource
	private CpnService cpnService;
	private static final String LOGIN_INFO = "loginInfo";
	//打开添加代金券页面   获取代金券内容
	@RequestMapping("addCpn")
	public  ModelAndView addPrd(){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("cpn/addCpn");
		//查询代金券类别列表  tp_type_no   tp_name  类别编号和名称
		List<Map<String,Object>> result = cpnService.findCpnType();
		List<Map<String,Object>> resultTpCd = cpnService.findTpCd();
		modelAndView.addObject("result",result);
		modelAndView.addObject("resultTpCd",resultTpCd);
		return modelAndView;
}
	//添加代金券form提交
	@RequestMapping("addCpnForm")
	@ResponseBody
	public Map<String,Object> menuInfoForm(@RequestParam Map<String,Object> params, HttpServletRequest request ) {

		Object sessionObj = request.getSession().getAttribute(LOGIN_INFO);
		if(sessionObj != null){
			params.put("username",sessionObj);
		}
		System.out.println("controller"+params);
		cpnService.addCpn(params);
		Map<String,Object> result=new HashMap<>();
		result.put("result","success");
		return result;
	}
	// 打开管理代金券  分页 d列表
	@RequestMapping("cpnPage")
	public ModelAndView menuPage(@RequestParam Map<String,Object> params){
		System.out.println(params);
		ModelAndView modelAndView = new ModelAndView();
		//查询代金券类别列表  tp_type_no   tp_name  类别编号和名称
		List<Map<String,Object>> resultType = cpnService.findCpnType();
		modelAndView.addObject("resultType",resultType);
		PageInfo<Map<String, Object>> pageInfo = cpnService.findAllCpnPageList(params);
		modelAndView.addObject("page",pageInfo);
		modelAndView.addObject("list",pageInfo.getList());
		modelAndView.addObject("params",params);
		modelAndView.setViewName("/cpn/cpnPage");
		return modelAndView;
	}
}
