
package com.lanou.cn.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.cn.controller.IndexController;
import com.lanou.cn.mapper.CpnMapper;
import com.lanou.cn.service.CpnService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by admin on 28/6/17.
 */

@Service
public class CpnServiceImpl implements CpnService {
	public static final String HOST_URL = "http://192.168.2.1:8180";
	public static  final Logger logger = LoggerFactory.getLogger(IndexController.class);
	@Resource
	private CpnMapper cpnMapper;
	//查询代金券类型
	@Override
	public List<Map<String, Object>> findCpnType() {
		List<Map<String, Object>> cpnTypeList=cpnMapper.findCpnType();
		return cpnTypeList;
	}


	//查询商品类型
	@Override
	public List<Map<String, Object>> findTpCd() {

		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap<String, String>();
		List<Map<String, Object>> tpCdList = restTemplate.postForObject(HOST_URL + "/rest/getType.do",bodyMap, List.class);
		System.out.println(tpCdList);
		return tpCdList;
	}

	//添加代金券
	@Override
	public void addCpn(Map<String, Object> params) {
		System.out.println("22333333");
		System.out.println(params);
		String uername=(String) params.get("username");
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap<String, String>();

		bodyMap.add("uername",uername);
	//	Map<String,Object> map  = restTemplate.postForObject("http://192.168.2.1:8080" + "/rest/getUserInfo.do",bodyMap,Map.class);
		params.put("instId","1");

	//	System.out.println(map);


		cpnMapper.addCpn(params);
	}
	//后台查询代金券 分页
	@Override
	public PageInfo<Map<String, Object>> findAllCpnPageList(Map<String, Object> params) {
		if("k".equals(params.get("isUsed"))){
			params.put("isUsed","");
		}
		if("999".equals(params.get("cpnTpyeNo"))){
			params.put("cpnTpyeNo","");
		}
		Integer currentPage = params.get("currentPage") == null ? 1:Integer.parseInt((String)params.get("currentPage"));
		//设置一页的个数
		PageHelper.startPage(currentPage, 5);
		List<Map<String,Object>> list = cpnMapper.findCpn(params);
		//用PageInfo对结果进行包装
		PageInfo<Map<String,Object>> page = new PageInfo<Map<String,Object>>(list);
		return page;
	}
	//用户领取代金券  添加会员代金券关系表
	@Override
	public void addVipCpnR(Map<String, Object> params) {
		cpnMapper.addVipCpnR(params);
	}
	//下单成功后修改关系表  添加记录表
	@Override
	@Transactional
	public void updateVipCpnR(Map<String, Object> params) {
		//修改会员代金券关系表的is_used
		cpnMapper.updateVipCpnR(params);
		//添加到 会员使用代金券记录表
		cpnMapper.addVipCpnUsed(params);
	}
	//查询商品当前和以后可用的代金券接口（企划用与详情页面当中） 出去该用户已经领取的
	@Override
	public List<Map<String, Object>> findCpnInfo(Map<String, Object> params) {
		//查询用户已经存在该类型下的代金券


		List<Map<String, Object>> cpnInfoList=cpnMapper.findCpnInfo(params);


		return cpnInfoList;
	}
	// 前端会员查询可用的代金券接口  参数是会员编号 orderTime="1" 是查询当前可用的 (目前没加)会员代金券表   用户查代金券信息表
	@Override
	public PageInfo<Map<String, Object>> vipFindAllCpnInfo(Map<String, Object> params) {
		Integer currentPage = params.get("currentPage") == null ? 1:Integer.parseInt((String)params.get("currentPage"));
		//设置一页的个数
		PageHelper.startPage(currentPage, 5);
		List<Map<String,Object>> list = cpnMapper.findCpnInfo(params);
		//用PageInfo对结果进行包装
		PageInfo<Map<String,Object>> page = new PageInfo<Map<String,Object>>(list);
		return page;
	}
}
