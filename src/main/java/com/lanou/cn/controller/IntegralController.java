package com.lanou.cn.controller;


import com.lanou.cn.service.IntegralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by admin on 27/6/17.
 */
@RestController
@RequestMapping("/point")
public class IntegralController {
	@Autowired
	private IntegralService integralService;


	/**
	 * 返还积分接口
	 * 需要使用的前端数据为（会员表ID，会员编号vip_no,积分加减量point_num，order_no订单号,point_state积分状态（u:使用或者r：返还））
	 * @param params
	 * @return
	 */
	@RequestMapping("returnPoint")
	public Map<String, Object> returnPoint(@RequestParam Map<String, Object> params) {
		Map<String,Object> result = new HashMap<>();
		try {
			integralService.returnPoint(params);
			result.put("result","success");
		}catch (Exception e) {
			result.put("result","error");
		}
		return result;
	}


	/**
	 * 积分使用接口
	 * 需要使用的前端数据为（会员表ID，会员编号vip_no,积分加减量point_num，order_no订单号,point_state积分状态（u:使用或者r：返还））
	 * @param params
	 * @return
	 */
	@RequestMapping("usePoint")
	public Map<String, Object> usePoint(@RequestParam Map<String, Object> params) {
		Map<String,Object> result = new HashMap<>();
		try {
			integralService.usePoint(params);
			result.put("result","success");
		} catch (Exception e) {
			result.put("result","error");
		}
		return result;
	}






}
