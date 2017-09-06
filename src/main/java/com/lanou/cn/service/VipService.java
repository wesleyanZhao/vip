package com.lanou.cn.service;

import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by lanou on 2017/7/24.
 */
public interface VipService {

    /**
     * 查询会员信息
     * @param params
     * @return
     */
    PageInfo<Map<String,Object>> findVipInfo(Map<String,Object> params);

    /**
     * 修改会员信息
     * @param params
     */
    Map<String,Object> updateVipInfo(Map<String,Object> params);

    /**
     * 地址页面
     * @param params
     * @return
     */
    List<Map<String,Object>> showSite(Map<String,Object> params);

    /**
     * 修改地址
     * @param params
     */
    void updateSite(Map<String,Object> params);

    /**
     * 金额返还
     * @param params
     * @return
     */
    Map<String,Object> returnMoney(Map<String,Object> params);

    /**
     * 金额使用
     * @param params
     * @return
     */
    void useMoney(Map<String,Object> params) throws Exception;


    /**
     * 使用惠卖卡充值
     * @param params
     * @throws Exception
     */
    void useCard(Map<String,Object> params) throws Exception;

    /**
     * 注册添加方法
     * @param params
     * @return
     * @throws Exception
     */
    Map<String,Object> addVip(Map<String,Object> params) throws Exception;

    /**
     * 查询所有的省
     * @return
     */
    List<Map<String,Object>> findProvince();

    /**
     * 根据省查询市
     * @param provinceId
     * @return
     */
    List<Map<String,Object>> findCity(int provinceId);

    /**
     * 登录方法
     * @param params
     * @return
     */
    Map<String,Object> loginFrom(Map<String,Object> params);

    /**
     * 查询会员账号，异步判断是否重复
     * @param vipAccount
     * @return
     */
    Map<String,Object> findVipAccount(String vipAccount);

    /**
     * 查询身份证号,异步判断是否重复
     * @param idCard
     * @return
     */
    Map<String,Object> findIdCard(String idCard);

    /**
     * 查询有关新添加地址的数据
     * @param id
     * @return
     */
    Map<String,Object> findAddress(int id);

    /**
     * 在个人信息处添加副地址
     * @param params
     * @return
     */
    Map<String,Object> addVipAddress(Map<String,Object> params);

    /**
     * 查询有关新添加银行卡的数据
     * @param id
     * @return
     */
    List<Map<String,Object>> findBank(int id);

    /**
     * 在个人信息时添加银行卡
     * @param params
     * @return
     */
    Map<String,Object> addBankInfo(Map<String,Object> params);
    /**
     * 会员降级系统
     * 需要数据：
     * 会员编号（vip_no）
     * @param params
     */
    Map<String,Object> UpdateVipLevel(Map<String, Object> params);

    /**
     * 返还积分接口
     * @param params
     */
    void returnPoint(Map<String, Object> params);

    /**
     * 使用积分接口
     * @param params
     * @throws Exception
     */
    void usePoint(Map<String, Object> params) throws Exception;

    /**
     * 账户余额，积分返还
     * @param params
     */
    void returnOrUse(Map<String, Object> params);

    /**
     * 会员升级
     * @param params
     * @return
     */
    Map<String,Object> vipUpgrade(Map<String,Object> params);

    /**
     * 查询积分
     * @param params
     * @return
     */
    Map<String,Object> findAccPoint(Map<String,Object> params);
    /**
     * 会员账户金额和积分
     * @param params
     * @return
     */
    Map<String,Object> findVipMoney(Map<String,Object> params);

    /**
     * 支付金额和积分
     * @param params
     * @return
     */
    void pay(Map<String,Object> params);

    /**
     * 查询返还的积分
     * @param params
     * @return
     */
    Map<String,Object> findReturnIn(Map<String,Object> params);
}
