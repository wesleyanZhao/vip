package com.lanou.cn.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * Created by admin on 28/6/17.
 */
public interface VipMapper {
    /**
     * 金额返还,如果1组传的是其他，需要去修改添加一个查询语句
     * @param params
     * @return
     */
    @Update("update vip_info set acc_sum = acc_sum + #{params.payMoney} where id = #{params.id}")
    void returnMoney(@Param("params") Map<String,Object> params);

    /**
     * 金额使用
     * @param params
     * @return
     */
    @Update("update vip_info set acc_sum = acc_sum - #{params.payMoney} where id = #{params.id}")
    void useMoney(@Param("params") Map<String,Object> params);

    /**
     * 查询当前账户余额
     * @param params
     * @return
     */
    @Select("select acc_sum as accSum from vip_info where id = #{params.id}")
    Map<String,Object> findMoney(@Param("params") Map<String,Object> params);

    /**
     * 使用惠买卡
     * @param params
     */
    void useCard(@Param("params") Map<String,Object> params);

    /**
     *充值
     * @param params
     */
    void addMoney(@Param("params") Map<String,Object> params);

    /**
     * 查询充值卡是否存在
     * @param params
     * @return
     */
    @Select("select id from card_info where card_no = #{params.cardNo} and card_pwd = #{params.cardPwd}")
    Map<String,Object> findCard(@Param("params") Map<String,Object> params);

    /**
     * 添加记录表
     * @param params
     */
    void addCardRecord(@Param("params") Map<String,Object> params);

    /**
     * 查询会员信息
     * @param params
     * @return
     */
    List<Map<String,Object>> findVipInfo(@Param("params") Map<String,Object> params);

    /**
     * 查找城市名
     * @param provinceId
     * @return
     */
    List<Map<String,Object>> findCity(@Param("provinceId") int provinceId);

    /**
     * 修改会员信息
     * @param params
     */
    void updateVipInfo(@Param("params") Map<String,Object> params);

    /**
     * 地址页面
     * @param params
     * @return
     */
    List<Map<String,Object>> showSite(@Param("params") Map<String,Object> params);

    /**
     * 修改地址
     * @param params
     */
    void updateSite(@Param("params") Map<String,Object> params);

    /**
     * 添加vip_info表
     * @param params
     */
    void addVip(Map<String,Object> params);
    /**
     * 插入vip_add_info表
     * @param params
     */
    void addAddress(@Param("params") Map<String, Object> params);

    /**
     * 注册时添加银行卡
     * @param params
     */

    void addBank(@Param("params") Map<String, Object> params);
    /**
     * 在个人信息处添加副地址
     * @param params
     */
    @Insert("insert into vip_add_info(vip_no,province_id,city_id,addr_info,is_used,inst_date,inst_id,addr_state) values(#{params.vipNo},#{params.provinceId},#{params.cityId},#{params.addrInfo},#{params.isUsed},now(),#{params.instId},1)")
    void addVipAddress(@Param("params") Map<String,Object> params);

    /**
     * 在个人信息时添加银行卡
     * @param params
     */
    @Insert("insert into bank_info(vip_no,bank_card_no,bank_name,bank_card_type) values(#{params.vipNo},#{params.bankCardNo},#{params.bankName},#{params.bankCardType})")
    void addBankInfo(@Param("params") Map<String,Object> params);

    /**
     * 查询所有的省
     * @return
     */
    List<Map<String,Object>> findProvince();

    /**
     * 登陆时根据vipAccount和vipPassword查找有无对应数据，以判断能否登录
     * @param params
     * @return
     */
    Map<String,Object> loginFrom(@Param("params") Map<String,Object> params);

    /**
     * 查询会员账号，异步判断是否重复
     * @param vipAccount
     * @return
     */
    @Select("select count(1) from vip_info where vip_account=#{vipAccount}")
    int findVipAccount(@Param("vipAccount") String vipAccount);

    /**
     * 查询身份证号,异步判断是否重复
     * @param idCard
     * @return
     */
    @Select("select count(1) from vip_info where id_card=#{idCard}")
    int findIdCard(@Param("idCard") String idCard);

    /**
     * 查询有关新添加地址的数据
     * @param id
     * @return
     */
    Map<String,Object> findAddress(@Param("id") int id);


    /**
     * 查询有关新添加银行卡的数据
     * @param id
     * @return
     */
    List<Map<String,Object>> findBank(@Param("id") int id);

    @Update("update vip_info set acc_point=acc_point+#{params.payIntegral} where id=#{params.id}")
    void returnPoint(@Param("params") Map<String, Object> params);

    @Update("update vip_info set acc_point=acc_point - #{params.payIntegral} where id=#{params.id}")
    void usePoint(@Param("params") Map<String, Object> params);


    @Select("SELECT acc_point as accPoint from vip_info WHERE id=#{params.id}")
    Map<String,Object> findPoint(@Param("params") Map<String, Object> params);

    @Insert("insert into integral_record(vip_no,point_num,rcd_date,point_state,ord_no) values(#{params.vipNo},#{params.payIntegral},now(),'u',#{params.ordNo})")
    void addUsePointRecord(@Param("params") Map<String, Object> params);


    @Insert("insert into integral_record(vip_no,point_num,rcd_date,point_state,ord_no) values(#{params.vipNo},#{params.payIntegral},now(),'r',#{params.ordNo})")
    void addReturnPointRecord(@Param("params") Map<String, Object> params);

    /**
     * 会员升级
     * @param params
     * @return
     */
    Map<String,Object> vipUpgrade(@Param("params") Map<String, Object> params);
    /**
     * 会员降级
     * 需要数据
     * 会员编号（vip_no）
     * @param params
     */
    void UpdateVipLevel(@Param("params") Map<String, Object> params);

    /**
     * 会员拉黑
     * 需要数据
     * 会员编号（vip_no）
     * @param params
     */
    void pullTheBlack(@Param("params") Map<String, Object> params);

    /**
     * 根据vipNo查询会员账户积分
     * @param params
     * @return
     */
    @Select("select acc_point accPoint from vip_info where vip_no = #{params.vipNo}")
    Map<String,Object> findAccPoint(@Param("params") Map<String,Object> params);

    /**
     * 会员账户金额和积分
     * @param params
     * @return
     */
    @Select("select acc_sum as accSum,acc_point as accPoint from vip_info where id = #{params.vipId}")
    Map<String,Object> findVipMoney(@Param("params") Map<String,Object> params);


    /**
     * 查询返还的积分
     * @param params
     * @return
     */
    Map<String,Object> findReturnIn(@Param("params") Map<String,Object> params);
}