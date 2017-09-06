
package com.lanou.cn.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface CpnMapper {
	//查询出代金券类型 并返回页面
	@Select("select cpn_type_no cpnTypeNo,cpn_name cpnName from cpn_type")
	List<Map<String, Object>> findCpnType();
	//添加代金券
	void addCpn(@Param("params") Map<String, Object> params);
	//代金券分页
	List<Map<String,Object>> findCpn(@Param("params") Map<String, Object> params);
	//会员代金券关系表  addVipCpnR
	@Insert("insert into vip_cpn_r (vip_no,cpn_no,is_used,get_date) values(#{params.vipNo},#{params.cpnNo},'y',now())")
	void addVipCpnR(@Param("params") Map<String, Object> params);
	/*
	  * //修改会员代金券关系表的is_used
		cpnMapper.updateIsUsed(params);
		//添加到 会员使用代金券记录表
		cpnMapper.addVipCpnUsed(params);
	* */
	@Update("update vip_cpn_r set is_used = 'n' where vip_no = #{params.vipNo} and cpn_no =  #{params.cpnNo}")
	void updateVipCpnR(@Param("params") Map<String, Object> params);
	//添加记录 会员代金券记录表 vip_cpn_used
	@Insert("insert into vip_cpn_used (vip_no,cpn_no,use_date) values(#{params.vipNo},#{params.cpnNo},now())")
	void addVipCpnUsed(@Param("params") Map<String, Object> params);
	//用户查询现在和以后能用的代金券 用户编号 vip_no  会员代金券表   用户查代金券信息表
	List<Map<String,Object>> findCpnInfo(@Param("params") Map<String, Object> params);

}
