package com.lanou.cn.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.cn.mapper.CpnMapper;
import com.lanou.cn.mapper.VipMapper;
import com.lanou.cn.service.VipService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lanou on 2017/7/24.
 */
@Service
public class VipServiceImpl implements VipService{

    @Resource
    private VipMapper vipMapper;

    @Resource
    private CpnMapper cpnMapper;

    @Override
    public PageInfo<Map<String,Object>> findVipInfo(Map<String, Object> params) {
        Integer currentPage = params.get("currentPage") == null ? 1:Integer.parseInt((String)params.get("currentPage"));
        PageHelper.startPage(currentPage, 5);
        List<Map<String,Object>> list=vipMapper.findVipInfo(params);
        PageInfo<Map<String,Object>> page = new PageInfo<Map<String,Object>>(list);
        return page;
    }

    @Override
    public Map<String,Object> updateVipInfo(Map<String, Object> params) {
        Map<String,Object> result = new HashMap<>();
        try {
            vipMapper.updateVipInfo(params);
            result.put("result","success");
        }
        catch (Exception e){
            e.printStackTrace();
            result.put("result","failure");

        }
        return result;
    }

    @Override
    public List<Map<String,Object>> showSite(Map<String,Object> params) {
        List<Map<String,Object>> list=vipMapper.showSite(params);
        return list;
    }


    @Override
    public void updateSite(Map<String, Object> params) {
        vipMapper.updateSite(params);

    }




    /**
     * 注册方法
     * @param params
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String,Object> addVip(Map<String, Object> params) throws Exception{
        Map<String,Object> result = new HashMap<>();
        //Map<String, Object> map = (Map<String, Object>) JSONObject.parse((String)params.get("params"));

        vipMapper.addVip(params);//添加vip_info表
        vipMapper.addAddress(params);//添加vip_add_info
        System.out.println(params);
        vipMapper.addBank(params);
        boolean falge=params.containsKey("id");
        if(falge){
            result.put("result","success");
        }else{
            result.put("result","failure");
            Exception exception=new Exception();
            throw exception;
        }
        return result;
    }

    /**
     * 查询所有的省
     * @return
     */
    @Override
    public List<Map<String, Object>> findProvince() {
        return vipMapper.findProvince();
    }

    /**
     * 根据省查询市
     * @param provinceId
     * @return
     */
    @Override
    public List<Map<String, Object>> findCity(int provinceId) {
        return vipMapper.findCity(provinceId);
    }

    /**
     * 登录方法
     * @param params
     * @return
     */
    @Override
    public Map<String, Object> loginFrom(Map<String, Object> params) {

        return vipMapper.loginFrom(params);
    }

    /**
     * 查询会员账号，异步判断是否重复
     * @param vipAccount
     * @return
     */
    @Override
    public Map<String,Object> findVipAccount(String vipAccount) {
        Map<String,Object> result=new HashMap<>();
        if(null!=vipAccount){
            int accCount=vipMapper.findVipAccount(vipAccount);
            if(0==accCount){
                result.put("result","success");
            }else{
                result.put("result","fail");
            }
        }else{
            result.put("result","error");
        }

        return result;
    }

    /**
     * 查询身份证号,异步判断是否重复
     * @param idCard
     * @return
     */
    @Override
    public Map<String,Object> findIdCard(String idCard) {
        Map<String,Object> result=new HashMap<>();
        if(null!=idCard){
            int idCardCount=vipMapper.findIdCard(idCard);
            if(0==idCardCount){
                result.put("result","success");
            }else{
                result.put("result","fail");
            }
        }else{
            result.put("result","error");
        }

        return result;
    }

    /**
     * 查询有关新添加地址的数据
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> findAddress(int id) {
        System.out.println(id);
        return vipMapper.findAddress(id);
    }

    /**
     * 在个人信息处添加副地址
     * @param params
     * @return
     */
    @Override
    public Map<String, Object> addVipAddress(Map<String, Object> params) {
        //Map<String, Object> map = (Map<String, Object>)JSONObject.parse((String)params.get("params"));
        Map<String,Object> result = new HashMap<>();
        try {
            vipMapper.addVipAddress(params);
            result=new HashMap<>();
            result.put("result","success");
        }
        catch (Exception e){
            e.printStackTrace();
            result.put("result","failure");
        }
        return result;
    }

    /**
     * 查询有关新添加银行卡的数据
     * @param id
     * @return
     */
    @Override
    public List<Map<String, Object>> findBank(int id) {
        System.out.println(id);
        return vipMapper.findBank(id);
    }

    @Override
    public Map<String, Object> addBankInfo(Map<String, Object> params) {
        Map<String,Object> result=new HashMap<>();
        try {
            vipMapper.addBankInfo(params);
            result.put("result","success");
        }
        catch (Exception e){
            e.printStackTrace();
            result.put("result","failure");
        }
        return result;
    }



    /**
     * 返还积分实现
     * 并且添加记录
     * @param params
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void returnPoint(Map<String, Object> params) {
        if(!StringUtils.isEmpty(params.get("payIntegral"))){
            vipMapper.returnPoint(params);
            vipMapper.addReturnPointRecord(params);
        }
    }


    /**
     * 使用积分实现
     * 并添加记录
     * 判断积分是否合法（大于等于0）
     * 如果不合法则回滚抛出异常
     * @param params
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void usePoint(Map<String, Object> params) throws Exception {

        vipMapper.usePoint(params);
        Map<String,Object> vipPoint=vipMapper.findPoint(params);
        vipMapper.addUsePointRecord(params);
        if (0 > (int)vipPoint.get("accPoint")){
            Exception e = new Exception();//创建异常对象
            throw e;//抛出异常
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void returnOrUse(Map<String, Object> params) {
        vipMapper.returnMoney(params);
        vipMapper.returnPoint(params);
    }


    /**
     * 金额返还
     * @param params
     * @return
     */
    @Override
    public Map<String, Object> returnMoney(Map<String, Object> params) {
        Map<String,Object> result = new HashMap<>();
        try{
            vipMapper.returnMoney(params);
            params.put("useState","r");
            System.out.println(params);
            vipMapper.addCardRecord(params);
            result.put("result","success");
        }catch (Exception e){
            e.printStackTrace();
            result.put("result","failure");
        }
        return result;
    }

    /**
     * 金额使用
     * @param params
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void useMoney(Map<String, Object> params) throws Exception{
        vipMapper.useMoney(params);
        Map<String,Object> vip = vipMapper.findMoney(params);
        if((double)vip.get("accSum") < 0){
            throw new RuntimeException("mc001");
        }
        else{
            params.put("useState","u");
            vipMapper.addCardRecord(params);
        }
    }

    /**
     * 使用惠买卡充值
     * @param params
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void useCard(Map<String, Object> params) throws Exception {

        Map<String,Object> card = vipMapper.findCard(params);

        if(!CollectionUtils.isEmpty(card)){
            vipMapper.useCard(params);
            vipMapper.addMoney(params);
            params.put("useState","t");
            vipMapper.addCardRecord(params);
        }
    }
    /**
     * 会员升级
     * @param params
     * @return
     */
    @Override
    public Map<String, Object> vipUpgrade(Map<String, Object> params) {
        Map<String,Object> result = new HashMap<>();
        Map<String,Object> price = vipMapper.vipUpgrade(params);
        //调用VIP中的vip等等级查询接口
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<String, Object>();
        bodyMap.setAll(params);
        List<Map<String,Object>> list = restTemplate.postForObject("http://192.168.2.25:8888/rest/findVipInfo.do",bodyMap, List.class);
        int vipLevel = (int)list.get(0).get("vipLevel");
        double monetary = 0;
        if(CollectionUtils.isEmpty(price)){
            monetary = 0;
        }
        else{
            monetary = (double)price.get("monetary");
        }
        if(monetary < 5000 && monetary >= 0){
            vipLevel = 1;
        }
        else if(monetary >= 5000 && monetary < 25000){
            vipLevel = 2;
        }
        else if(monetary >= 25000 && monetary < 75000){
            vipLevel = 3;
        }
        else if(monetary >= 75000 && monetary < 175000){
            vipLevel = 4;
        }
        else if(monetary >= 175000){
            vipLevel = 5;
        }
        params.put("vipLevel",vipLevel);
        bodyMap.setAll(params);
        //修改vip等级
        result = restTemplate.postForObject("http://192.168.2.25:8888/rest/updateVipInfo.do",bodyMap, Map.class);
        /*if(!CollectionUtils.isEmpty(price)){
            double monetary = (double)price.get("monetary");
            if(!CollectionUtils.isEmpty(list)){
                if(monetary < 5000 && monetary >= 0){
                    vipLevel = 1;
                }
                else if(monetary >= 5000 && monetary < 25000){
                    vipLevel = 2;
                }
                else if(monetary >= 25000 && monetary < 75000){
                    vipLevel = 3;
                }
                else if(monetary >= 75000 && monetary < 175000){
                    vipLevel = 4;
                }
                else if(monetary >= 175000){
                    vipLevel = 5;
                }
                params.put("vipLevel",vipLevel);
                bodyMap.setAll(params);
                //修改vip等级
                result = restTemplate.postForObject("http://192.168.2.25:8888/rest/updateVipInfo.do",bodyMap, Map.class);
            }
        }
        else{
            double monetary = 0;
            if(monetary < 5000 && monetary >= 0){
                vipLevel = 1;
            }
            else if(monetary >= 5000 && monetary < 25000){
                vipLevel = 2;
            }
            else if(monetary >= 25000 && monetary < 75000){
                vipLevel = 3;
            }
            else if(monetary >= 75000 && monetary < 175000){
                vipLevel = 4;
            }
            else if(monetary >= 175000){
                vipLevel = 5;
            }
            params.put("vipLevel",vipLevel);
            bodyMap.setAll(params);
            //修改vip等级
            result = restTemplate.postForObject("http://192.168.2.25:8888/rest/updateVipInfo.do",bodyMap, Map.class);
        }*/
        return result;
    }

    /**
     * 会员账户金额和积分
     * @param params
     * @return
     */
    @Override
    public Map<String, Object> findVipMoney(Map<String, Object> params) {
        Map<String, Object> vip = vipMapper.findVipMoney(params);
        return vip;
    }

    /**
     * 支付金额和积分
     * @param params
     * @return
     */
    @Override
    @Transactional
    public void pay(Map<String, Object> params) {
        if(!StringUtils.isEmpty(params.get("payIntegral"))){
            //积分使用
            vipMapper.usePoint(params);
            Map<String,Object> vipPoint=vipMapper.findPoint(params);
            vipMapper.addUsePointRecord(params);
            vipMapper.updateVipInfo(params);
            if (0 > (int)vipPoint.get("accPoint")){
                throw new RuntimeException("mc002");//抛出异常
            }
        }
        if(!StringUtils.isEmpty(params.get("payMoney"))){
            //余额使用
            vipMapper.useMoney(params);
            Map<String,Object> vip = vipMapper.findMoney(params);
            if((double)vip.get("accSum") < 0){
                throw new RuntimeException("mc001");
            }
            else{
                params.put("useState","u");
                vipMapper.addCardRecord(params);
            }
        }
        if(!StringUtils.isEmpty(params.get("cpnNo"))){
            //代金券使用
            //修改会员代金券关系表的is_used
            cpnMapper.updateVipCpnR(params);
            //添加到 会员使用代金券记录表
            cpnMapper.addVipCpnUsed(params);
        }
    }

    /**
     * 查询返还的积分
     * @param params
     * @return
     */
    @Override
    public Map<String,Object> findReturnIn(Map<String, Object> params) {
        System.out.println(params);
        return vipMapper.findReturnIn(params);
    }


    /**
     * 会员降级系统
     * 需要数据：
     * 会员编号（vip_no）
     * @param params
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String,Object> UpdateVipLevel(Map<String, Object> params) {
        Map<String,Object> result = new HashMap<>();
        System.out.println(params);
        //查询本月退货记录
        int returnGoods= Integer.parseInt((String) params.get("returnGoods"));
        System.out.println(returnGoods);
        //如果退货大于等于10次会员被拉黑
        if (10<=returnGoods){
            vipMapper.pullTheBlack(params);
            result.put("result","0");
        }

        //如果退货大于等于三次会员降1级
        if (3<=returnGoods&&10>returnGoods){
            vipMapper.UpdateVipLevel(params);
            result.put("result","1");
        }
        return result;
    }

    @Override
    public Map<String, Object> findAccPoint(Map<String, Object> params) {
        return vipMapper.findAccPoint(params);
    }
}
