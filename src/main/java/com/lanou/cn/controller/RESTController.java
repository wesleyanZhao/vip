package com.lanou.cn.controller;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.lanou.cn.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by lanou on 2017/7/26.
 */
@RestController
@RequestMapping("/rest")
public class RESTController {

    @Resource
    private ModifyService modifyService;

    @Resource
    private VipService vipService;

    @Autowired
    private BaseService baseService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserService userService;

    @Autowired
    private IntegralService integralService;

    @Resource
    private CpnService cpnService;

    private static final String LOGIN_INFO = "loginInfo";

    /**
     * 注册会员账户及其相关表
     * @param params
     * @return
     */

    @RequestMapping("addVip")
    public Map<String,Object> addVip(@RequestParam Map<String,Object> params){
        Map<String,Object> result = null;
        try {
            result = vipService.addVip(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 查找省信息
     * @return
     */
    @RequestMapping("findProvince")
    public List<Map<String,Object>> findProvince(){
        List<Map<String,Object>> findProvince= vipService.findProvince();
        System.out.println(findProvince);

        return findProvince;
    }

    /**
     * 根据provinceId查找城市
     * @param params
     * @return
     */
    @RequestMapping("findCity")
    public List<Map<String,Object>> findCity(@RequestParam Map<String,Object> params){
        List<Map<String,Object>> findCity=vipService.findCity(Integer.parseInt((String) params.get("provinceId")));
        return findCity;

    }

    /**
     * 登录判断账号是否存在
     * @param params
     * @return
     */
    @RequestMapping("loginFrom")
    public Map<String,Object> loginFrom(@RequestParam Map<String,Object> params){
        Map<String,Object> result=vipService.loginFrom(params);
        return result;
    }

    /**
     * 页面异步判断账号是否重复
     * @param vipAccount
     * @return
     */
    @RequestMapping("findVipAccount")
    public Map<String,Object> findVipAccount(@RequestParam String vipAccount){
        return vipService.findVipAccount(vipAccount);
    }

    /**
     * 页面异步判断身份证号是否重复
     * @param idCard
     * @return
     */
    @RequestMapping("findIdCard")
    public Map<String,Object> findIdCard(@RequestParam String idCard){
        return vipService.findIdCard(idCard);
    }

    /**
     * 根据id查找相关地址信息
     * @param id
     * @return
     */
    @RequestMapping("findAddress")
    public Map<String,Object> findAddress(@RequestParam int id){
        return vipService.findAddress(id);
    }

    /**
     * 个人信息页面添加地址
     * @param params
     * @return
     */
    @RequestMapping("addVipAddress")
    public Map<String,Object> addVipAddress(@RequestParam Map<String,Object> params){
        return vipService.addVipAddress(params);
    }

    /**
     * 根据id查找银行卡信息
     * @param id
     * @return
     */
    @RequestMapping("findBank")
    public List<Map<String,Object>> findBank(@RequestParam int id){
        return vipService.findBank(id);
    }

    /**
     * 个人信息页面添加银行卡
     * @param params
     * @return
     */
    @RequestMapping("addBankInfo")
    public Map<String,Object> addBankInfo(@RequestParam Map<String,Object> params){
        System.out.println(params);
        return vipService.addBankInfo(params);
    }

    /**
     * 会员信息查询
     * @param params
     * @return
     */
    @RequestMapping("findVipInfo")
    public List<Map<String,Object>> findVipInfo(@RequestParam Map<String,Object> params){
        PageInfo<Map<String,Object>> page=vipService.findVipInfo(params);
        System.out.println(params);
        System.out.println(page.getList().size());
        return page.getList();
    }

    /**
     * 会员信息修改
     * @param params
     * @return
     */
    @RequestMapping("updateVipInfo")
    public Map<String,Object> updateVipInfo(@RequestParam Map<String,Object> params){
        return vipService.updateVipInfo(params);
    }

    /**
     * 查找会员地址接口
     * @param params
     * @return
     */
    @RequestMapping("showSite")
    public List showSite(@RequestParam Map<String,Object> params){
        List<Map<String,Object>> list=vipService.showSite(params);
        return list;
    }

    @RequestMapping("returnMoney")
    public Map<String,Object> returnMoney(@RequestParam Map<String,Object> params){
        Map<String,Object> result = vipService.returnMoney(params);
        System.out.println(result);
        return result;
    }
    @RequestMapping("useMoney")
    public Map<String,Object> useMoney(@RequestParam Map<String,Object> params){
        Map<String,Object> result = new HashMap<>();
        try {
            vipService.useMoney(params);
            result.put("result","success");
        }
        catch (Exception e) {
            e.printStackTrace();
            if("mc001".equals(e.getMessage())){
                result.put("result","insufficient");
            }
            result.put("result", "failure");

        }
        return result;
    }
    @RequestMapping("useCard")
    public Map<String,Object> useCard(@RequestParam Map<String,Object> params){
        Map<String,Object> result = new HashMap<>();
        try {
            vipService.useCard(params);
            result.put("result","success");
        }
        catch (Exception e){
            result.put("result","failure");
        }
        return result;
    }


    //查询代金券接口（各种条件）
    @RequestMapping("findCpnInfo")
    public List<Map<String, Object>> findCpnInfo(@RequestParam Map<String,Object> params) {
        System.out.println(params);
        List<Map<String, Object>> CpnInfo = cpnService.findCpnInfo(params);
        return CpnInfo;
    }
    //用户查询现在和以后能用的代金券   分页
    @RequestMapping("vipFindAllCpnInfo")
    public Map<String,Object> vipFindAllCpnInfo(@RequestParam Map<String,Object> params){
        Map<String,Object> cpnMap=new HashMap<>();
        PageInfo<Map<String, Object>> pageInfo = cpnService.vipFindAllCpnInfo(params);
        //返回值
        cpnMap.put("pageNum",pageInfo.getPageNum());
        cpnMap.put("total",pageInfo.getTotal());
        cpnMap.put("pages",pageInfo.getPages());
        cpnMap.put("list",pageInfo.getList());
        return cpnMap;
    }

    //用户领取代金券  添加会员代金券关系表
    @RequestMapping("addVipCpnR")
    public Map<String,String> addVipCpnR(@RequestParam Map<String,Object> params) {
        Map<String,String> result =new HashMap<>();
        try {
            cpnService.addVipCpnR(params);
            result.put("result","success");
        }catch (Exception e){
            result.put("result","failure");
        }
        return result;
    }

    //支付成功后   通过代金券编号  用户编号 修改 会员拥有代金券关系表    并且添加到 会员代金券记录表
    @RequestMapping("updateVipCpnR")
    public Map<String,String> updateIsUsed(@RequestParam Map<String,Object> params) {
        Map<String,String> result =new HashMap<>();
        try {
            cpnService.updateVipCpnR(params);
            result.put("result","success");
        }catch (Exception e){
            result.put("result","failure");
        }
        return result;
    }

    /**
     * 返还积分接口
     * 需要使用的前端数据为（会员表ID，会员编号vip_no,积分加减量point，企划信息layout）
     * @param params
     * @return
     */
    @RequestMapping("returnPoint")
    public Map<String, Object> returnPoint(@RequestParam Map<String, Object> params) {
        Map<String,Object> result = new HashMap<>();
        try {
            vipService.returnPoint(params);
            result.put("result","success");
        }catch (Exception e) {
            result.put("result","error");
        }
        return result;
    }


    /**
     * 积分使用接口
     * 需要使用的前端数据为（会员表ID，会员编号vip_no,积分加减量point，企划信息layout）
     * @param params
     * @return
     */
    @RequestMapping("usePoint")
    public Map<String, Object> usePoint(@RequestParam Map<String, Object> params) {
        Map<String,Object> result = new HashMap<>();
        try {
            vipService.usePoint(params);
            result.put("result","success");
        } catch (Exception e) {
            result.put("result","error");
        }
        return result;
    }
    /**
     * 给前端页面提供账户，代金券，银行卡信息查询接口
     * @param params
     * @return
     */
    @RequestMapping("vipAccountPage")
    public Map<String,Object> vipAccountPage(@RequestParam Map<String,Object> params){
        Map<String,Object> result=new HashMap<>();
        PageInfo<Map<String,Object>> pageInfo=modifyService.findAllUsersPageList(params);
        result.put("list",pageInfo.getList());
        result.put("total",pageInfo.getTotal());
        result.put("pages",pageInfo.getPages());
        result.put("pageNum",pageInfo.getPageNum());
        return result;
    }

    /**
     *给前端页面提供积分查询接口
     * @param params
     * @return
     */
    @RequestMapping("vipIntegral")
    public Map<String,Object> vipIntegral(@RequestParam Map<String,Object> params){
        Map<String,Object> result=new HashMap<>();
        System.out.println(params.get("currentPage"));
        PageInfo<Map<String,Object>> pageInfo=modifyService.findIntegral(params);
        result.put("pages",pageInfo.getPages());
        result.put("list",pageInfo.getList());
        result.put("total",pageInfo.getTotal());
        result.put("pageNum",pageInfo.getPageNum());
        return result;
    }

    /**
     * 查询账户积分 acc_point
     * @param params
     * @return
     */
    @RequestMapping("findAccPoint")
    public Map<String,Object> findAccPoint(@RequestParam Map<String,Object> params){
        Map<String,Object> result=vipService.findAccPoint(params);
        System.out.println(result);
        return result;
    }

    /* 基础接口 */

    /**
     * 登录提交、权限验证
     * @param params
     * @return
     */
    @RequestMapping("login")
    public Map<String,String> loginForm(@RequestParam() Map<String,String> params, HttpServletRequest request) {
        Map<String,String> result = baseService.loginValidate(params.get("username"),params.get("password"));
        if("success".equals(result.get("result"))) {
            request.getSession().setAttribute(LOGIN_INFO,params.get("username"));
            request.getSession().setMaxInactiveInterval(5*60);//以秒为单位
        }

        return result;
    }

    /**
     * 首页
     * @return
     */
    @RequestMapping("home")
    public Map<String,Object> home(String loginUser) {
        Map<String,Object> map = new HashMap<>();
        List<LinkedHashMap<String,Object>> list = baseService.findAllMenuList();
        String result = JSONArray.toJSONString(list);
        map.put("result",result);
        map.put("loginUser",null == loginUser ? "未登录" : loginUser);
        return map;
    }

    /**
     * 新用户注册
     * 学生完成
     * @param params
     * @return
     */
    @RequestMapping("register")
    public Map<String,Object> registerForm(@RequestParam Map<String,Object> params) {
        Map<String,Object> result = new HashMap<>();
        System.out.println(params);
        try {
            result = baseService.findUsers(params);
            System.out.println(result.get("result"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 查询用户信息
     * @param params
     * @return
     */
    @RequestMapping("findUserInfo")
    public Map<String,Object> findUserInfo(@RequestParam Map<String,Object> params){
        Map<String,Object> map = userService.getUserInfo(params);

        //判断是否为空，不为空的话，代表注册过，将注册的信息拿出来
        if(CollectionUtils.isEmpty(map)){
            map = new HashMap<>();
        }

        return map;
    }

    /**
     *完善个人信息
     * @param params
     * @return
     */
    @RequestMapping("userInfoForm")
    public Map<String,Object> userInfoForm(@RequestParam Map<String,Object> params){
        Map<String,Object> result = userService.userIsExist(params);
        return result;
    }

    /**
     * 修改密码
     * @param params
     * @return
     */
    @RequestMapping("updatePwd")
    public Map<String,Object> updatePwd(@RequestParam Map<String,Object> params){
        Map<String,Object> result = userService.getPassword(params);
        return result;
    }
    /**
     * 添加菜单
     * @return
     */
    @RequestMapping("addMenu")
    public Map<String,Object> addMenu(@RequestParam Map<String,Object> params){
        Map<String,Object> result = menuService.insertMenuForm(params);
        return result;
    }

    /**
     * 选择二级时，ajax异步请求显示可选择的一级菜单
     * @return
     */
    @RequestMapping("findSonMenu")
    public List<Map<String,Object>> findSonMenu(@RequestParam Map<String,Object> params){
        List<Map<String,Object>> list = menuService.getFirstMenu(params);
        return list;
    }

    /**
     * 分页查询菜单
     * @param params
     * @return
     */
    @RequestMapping("menuPage")
    public Map<String,Object> menuPage(@RequestParam Map<String,Object> params){

        Map<String,Object> result = new HashMap<>();
        PageInfo<Map<String,Object>> pageInfo = menuService.getAllSelectMenu(params);

        result.put("pageNum",pageInfo.getPageNum());
        result.put("total",pageInfo.getTotal());
        result.put("pages",pageInfo.getPages());
        result.put("list",pageInfo.getList());

        return result;
    }

    /**
     * 打开更新菜单
     * @param params
     * @return
     */
    @RequestMapping("updateMenu")
    public Map<String,Object> updateMenu(@RequestParam Map<String,Object> params){

        Map<String,Object> menu = menuService.getCurrentMenu(params);

        menu.put("id",params.get("id"));

        if(!menu.get("pId").equals(0)){
            List<Map<String,Object>> list = menuService.getFirstMenu(params);
            menu.put("list",list);
        }
        else{
            List<Map<String,Object>> list = new ArrayList<>();
            menu.put("list",list);
        }
        System.out.println(menu);
        return menu;
    }
    /**
     * 更新菜单
     * @param params
     * @return
     */
    @RequestMapping("updateMenuForm")
    public Map<String,Object> updateMenuForm(@RequestParam Map<String,Object> params){
        Map<String,Object> result = new HashMap<>();
        if(!params.get("pId").equals(0)){
            params.put("pId",params.get("first"));
            result = menuService.updateMenu(params);
        }
        else{
            result = menuService.updateMenu(params);
        }
        System.out.println(params);
        return result;
    }

    /**
     * 会员升级
     * @param params
     * @return
     */
    @RequestMapping("vipUpgrade")
    public Map<String,Object> vipUpgrade(@RequestParam Map<String,Object> params){
        Map<String,Object> result = vipService.vipUpgrade(params);
        return result;
    }



    /**
     * 账户积分的返还与使用
     * @param params
     * @return
     */
    @RequestMapping("returnOrUse")
    @Transactional
    public Map<String,Object> returnOrUse(@RequestParam Map<String,Object> params){
        Map<String,Object> result = new HashMap<>();
        System.out.println(params);
        try {
            if(!StringUtils.isEmpty(params.get("payIntegral"))){
                System.out.println(params + "积分返还");
                vipService.returnPoint(params);
                result.put("result","success");
            }
            if(!StringUtils.isEmpty(params.get("payMoney"))){
                System.out.println(params + "余额返还");
                vipService.returnMoney(params);
                result.put("result","success");
            }
        }
        catch (Exception e){
            e.printStackTrace();
            result.put("result","failure");
        }
        return result;
    }

    /**
     * 根据VIPNO查询账户余额
     * @param params
     * @return
     */
    @RequestMapping("findVipMoney")
    public Map<String,Object> findVipMoney(@RequestParam Map<String,Object> params){
        System.out.println(params);
        return vipService.findVipMoney(params);
    }
    /**
     * 查询返还的积分
     * @param params
     * @return
     */
    @RequestMapping("findReturnIn")
    public Map<String,Object> findReturnIn(@RequestParam Map<String, Object> params) {
        System.out.println(params);
        return vipService.findReturnIn(params);
    }

        /**
         * 支付金额和积分
         * @param params
         * @return
         */
    @RequestMapping("pay")
    public Map<String,Object> pay(@RequestParam Map<String,Object> params){
        Map<String,Object> result = new HashMap<>();
        try {
            vipService.pay(params);
            result.put("result","success");
        }
        catch (Exception e) {
            e.printStackTrace();
            if("mc001".equals(e.getMessage())){
                result.put("result","insufficient");
            }
            if("mc002".equals("e.getMessage()")){
                result.put("result","point");
            }
            result.put("result", "failure");
        }
        return result;
    }
}

