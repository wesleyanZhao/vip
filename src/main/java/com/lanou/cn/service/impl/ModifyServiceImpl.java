package com.lanou.cn.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.cn.mapper.ModifyMapper;
import com.lanou.cn.service.ModifyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by lanou on 2017/7/24.
 */
@Service
public class ModifyServiceImpl implements ModifyService{



    @Resource
    private ModifyMapper modifyMapper;

    /**
     * 根据Id 查找账户信息
     * @param vipNo,cpnNo
     * @return
     */
    @Override
    public Map<String, Object> findBalance(String vipNo) {
        return modifyMapper.findBalance(vipNo);
    }

    /**
     * 更新数据
     * @param params
     */
    @Override
    public void updateBalance(Map<String,Object> params) {
        modifyMapper.updateBalance(params);
    }


    /**
     * 更新数据 根据cpnNo
     * @param params
     */
    @Override
    public void updateVoucher(Map<String, Object> params) {
        modifyMapper.updateVoucher(params);
    }

    /**
     * 查询所有信息
     * @param params
     * @return
     */
    @Override
    public PageInfo<Map<String, Object>> findAllUsersPageList(Map<String, Object> params) {
        Integer currentPage = params.get("currentPage") == null ? 1:Integer.parseInt((String)params.get("currentPage"));
        //Integer size = params.get("size") == null ? 5:Integer.parseInt((String)params.get("size"));
        PageHelper.startPage(currentPage, 5);
        List<Map<String,Object>> list = modifyMapper.findAllInfo(params);
        System.out.println(list);
        //用PageInfo对结果进行包装
        PageInfo<Map<String,Object>> page = new PageInfo<Map<String,Object>>(list);
        return page;
    }

    /**
     * 更新银行卡号
     * @param params
     */
    @Override
    public void updateBankCardNo(Map<String, Object> params) {
        modifyMapper.updateBankCardNo(params);
    }

    /**
     * 查询积分
     * @param params
     * @return
     */
    @Override
    public PageInfo<Map<String, Object>> findIntegral(Map<String, Object> params) {
        System.out.println(params.get("currentPage"));
        Integer currentPage = params.get("currentPage") == null ? 1:Integer.parseInt((String) params.get("currentPage"));
        //Integer size = params.get("size") == null ? 5:Integer.parseInt((String)params.get("size"));
        PageHelper.startPage(currentPage, 5);
        List<Map<String,Object>> list = modifyMapper.findIntegral(params);
        System.out.println(list);
        //用PageInfo对结果进行包装
        PageInfo<Map<String,Object>> page = new PageInfo<Map<String,Object>>(list);
        return page;
    }

    /**
     * 查询代金券
     * @param cpnNo
     * @return
     */
    @Override
    public List<Map<String, Object>> findCoupon(String vipNO) {
        return modifyMapper.findCoupon(vipNO);
    }
}
