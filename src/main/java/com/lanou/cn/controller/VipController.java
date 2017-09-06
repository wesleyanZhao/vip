package com.lanou.cn.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.cn.service.VipService;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lanou on 2017/7/24.
 */
@Controller
@RequestMapping("/vip")
public class VipController {

    @Resource
    private VipService vipService;

    /**
     *查询会员信息
     * @param params
     * @return
     */
    @RequestMapping("findVipInfo")
    public ModelAndView findVipInfo(@RequestParam Map<String,Object> params){
        ModelAndView modelAndView=new ModelAndView();
        List<Map<String,Object>> cityList = new ArrayList<>();
        PageInfo<Map<String,Object>> page=vipService.findVipInfo(params);
        List<Map<String,Object>> provinceList=vipService.findProvince();
        if (!StringUtils.isEmpty(params.get("provinceId"))){
            int fatherId=Integer.parseInt((String) params.get("provinceId"));
            cityList=vipService.findCity(fatherId);
        }
        modelAndView.addObject("cityList",cityList);
        modelAndView.addObject("page",page);
        modelAndView.addObject("list",page.getList());
        modelAndView.addObject("map",params);
        modelAndView.addObject("provinceList",provinceList);
        modelAndView.setViewName("vip/findVipInfo");
        return modelAndView;
    }

    /**
     * 查询城市名
     * @param provinceId
     * @return
     */
    @RequestMapping("findCity")
    @ResponseBody
    public List<Map<String,Object>> findCity(@RequestParam String provinceId){
        int fatherId=Integer.parseInt(provinceId);
        List<Map<String,Object>> cityList = vipService.findCity(fatherId);
        return cityList;
    }

    /**
     * 进入修改页面
     * @param map
     * @return
     */

    @RequestMapping("showVipInfo")
    public ModelAndView showVipInfo(@RequestParam Map<String,Object> map){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("map",map);
        modelAndView.setViewName("vip/updateVipInfo");
        return modelAndView;
    }

    /**
     * 修改会员信息数据
     * @param params
     * @return
     */
    @RequestMapping("updateVipInfo")
    @ResponseBody
    public Map<String,String> updateVipInfo(@RequestParam Map<String,Object> params){
        Map<String,String> map=new HashMap();
        try {
            vipService.updateVipInfo(params);
            map.put("result","success");
        }
        catch (Exception e){
            e.printStackTrace();
            map.put("result","failure");
        }
        return map;
    }

    /**
     * 进入会员地址修改页面
     * @param params
     * @return
     */
    @RequestMapping("showSite")
    public ModelAndView showSite(@RequestParam Map<String,Object> params){
        ModelAndView modelAndView=new ModelAndView();
        List<Map<String,Object>> list=vipService.showSite(params);
        List<Map<String,Object>> provinceList=vipService.findProvince();
        modelAndView.addObject("map",params);
        modelAndView.addObject("list",list);
        modelAndView.addObject("provinceList",provinceList);
        modelAndView.setViewName("vip/findSite");
        return modelAndView;
    }

    /**
     * 修改会员地址
     * @param params
     * @return
     */
    @RequestMapping("updateSite")
    @ResponseBody
    public Map<String,String> updateSite(@RequestParam Map<String,Object> params){
        Map<String,String> map=new HashMap<>();
        map.put("result","success");
        vipService.updateSite(params);
        return map;
    }


    /**
     * 金额返还
     * @param params
     * @return
     */
    @RequestMapping("returnMoney")
    public Map<String,Object> returnMoney(@RequestParam Map<String,Object> params){
        Map<String,Object> result = vipService.returnMoney(params);
        System.out.println(result);
        return result;
    }

    /**
     * 金额使用
     * @param params
     * @return
     */
    @RequestMapping("useMoney")
    public Map<String,Object> useMoney(@RequestParam Map<String,Object> params){
        Map<String,Object> result = new HashMap<>();
        try {
            vipService.useMoney(params);
            result.put("result","success");
        }
        catch (Exception e){
            e.printStackTrace();
            result.put("result","failure");
        }
        return result;
    }

    /**
     * 使用惠买卡充值
     * @param params
     * @return
     */
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

    /**
     * 返还积分接口
     * 需要使用的前端数据为（会员表ID，会员编号vip_no,积分加减量point_num，order_no订单号,point_state积分状态（u:使用或者r：返还））
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
     * 需要使用的前端数据为（会员表ID，会员编号vip_no,积分加减量point_num，order_no订单号,point_state积分状态（u:使用或者r：返还））
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
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 会员降级系统
     * 需要数据：
     * 会员编号（vip_no）
     * @param params
     * @return
     */
    @RequestMapping("UpdateVipLevel")
    public Map<String, Object> UpdateVipLevel(@RequestParam Map<String, Object> params) {
        Map<String,Object> result = new HashMap<>();
        result=vipService.UpdateVipLevel(params);
        return result;
    }

}

