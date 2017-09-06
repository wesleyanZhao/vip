
package com.lanou.cn.service;

import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface CpnService {
	//添加代金券
	void addCpn(Map<String, Object> params);

	//添加代金券 首先查出代金券类型
	List<Map<String, Object>> findCpnType();
	//添加代金券 首先查出商品的类型
	List<Map<String,Object>> findTpCd();
	//分页   代金券   后台用
	PageInfo<Map<String, Object>> findAllCpnPageList(Map<String, Object> params);
	//用户领取代金券  添加会员代金券关系表
	void addVipCpnR(Map<String, Object> params);
	//通过代金券编号  用户编号 修改 会员拥有代金券关系表    并且添加到 会员代金券记录表
	void updateVipCpnR(Map<String, Object> params);
	//查询商品当前和以后可用的代金券接口（企划用）  orderTime="1"是只查询当前可用（可能用于支付）  会员代金券表   用户查代金券信息表
	List<Map<String, Object>> findCpnInfo(Map<String, Object> params);
	//分页  用户查询现在和以后能用的代金券
	PageInfo<Map<String, Object>> vipFindAllCpnInfo(Map<String, Object> params);
	//


}

