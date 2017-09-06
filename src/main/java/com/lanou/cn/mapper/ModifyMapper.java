package com.lanou.cn.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * Created by lanou on 2017/7/24.
 */

public interface ModifyMapper {

    /**
     * 根据vipNo查询 账户积分，余额 ，代金券
     * @param vipNo
     * @return
     */
    Map<String,Object> findBalance(@Param("vipNo") String  vipNo);

    /**
     * 根据vip_no 更新 vip_info数据
     * @param params
     */
    void updateBalance(@Param("params") Map<String,Object> params);


    /**
     * 根据cpn_no 更新cpn_info 数据
     * @param params
     * @return
     */
    @Update("update cpn_info set cpn_sum = #{params.cpnSum},cpn_content = #{params.cpnContent} where cpn_no = #{params.cpnNo}")
    void updateVoucher(@Param("params") Map<String,Object> params);
    /**
     * 查询所有数据
     * @param params
     * @return
     */
    List<Map<String,Object>> findAllInfo(@Param("params") Map<String,Object> params);

    /**
     * 更新银行卡号
     * @param params
     */
    void updateBankCardNo(@Param("params") Map<String,Object> params);

    /**
     * 查询积分数据
     * @param params
     * @return
     */
    List<Map<String,Object>> findIntegral(@Param("params") Map<String,Object> params);

//    @Select("select ci.cpn_no cpnNo,ci.cpn_type_no cpnTypeNo,ci.cpn_sum cpnSum,ci.cpn_content cpnContent,ci.inst_date instDate from vip_cpn_r where vip_no=#{params.vipNo}")
    List<Map<String,Object>> findCoupon(@Param("vipNo") String vipNo);

}
