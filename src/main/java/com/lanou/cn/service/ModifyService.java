package com.lanou.cn.service;

import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by lanou on 2017/7/24.
 */
public interface ModifyService {
    /**
     * 根据vipNo查询 账户积分，余额 ，代金券
     * @param vipNo
     * @return
     */
    Map<String,Object> findBalance(String vipNo);

    /**
     * 根据vipNo 更新数据
     * @param params
     */
    void updateBalance(Map<String,Object> params);

    /**
     * 根据cpnNo 更新数据
     * @param params
     */
    void updateVoucher(Map<String,Object> params);

    /**
     * 查询所有信息
     * @param params
     * @return
     */
    /**
     * 分页demo
     * @param params
     * @return
     */
    PageInfo<Map<String,Object>> findAllUsersPageList(Map<String, Object> params);

    /**
     * 更新银行卡号
     * @param params
     */
    void updateBankCardNo(Map<String,Object> params);

    /**
     * 查询积分信息
     * @param params
     * @return
     */
    PageInfo<Map<String,Object>> findIntegral(Map<String,Object> params);

    /**
     * 查询代金券
     * @param vipNo
     * @return
     */
    List<Map<String,Object>> findCoupon(String vipNo);


}
