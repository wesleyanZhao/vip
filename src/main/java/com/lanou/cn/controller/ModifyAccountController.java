package com.lanou.cn.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.cn.service.ModifyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lanou on 2017/7/24.
 */
@Controller
@RequestMapping("/mac")
public class ModifyAccountController {

    @Resource
    private ModifyService modifyService;

    /**
     * /访问页面  返回查出的数据
     * @param
     * @param
     * @return
     */
    @RequestMapping("modifyUpdate")
    public ModelAndView modifyUpdate(@RequestParam Map<String,Object> params){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/vip/accountUpdate");
        modelAndView.addObject("result",params);
        return modelAndView;
    }

    /**
     * /更新账户积分，账户余额
     * @param params
     * @return
     */
    @RequestMapping("updateBalance")
    @ResponseBody
    public Map<String,Object> updateBalance(@RequestParam Map<String,Object> params){
        System.out.println(params+"他妈的");
        Map<String,Object> result=new HashMap<>();
        modifyService.updateBalance(params);
        modifyService.updateBankCardNo(params);
        result.put("result","success");
        return result;
    }

    /**
     * 账户分页查询
     * @param params
     * @return
     */
    @RequestMapping("findAccount")
    public ModelAndView mainPage(@RequestParam Map<String,Object> params){
        System.out.println(params.get("vipNo")+"11111");
        System.out.println(params.get("cpnNo")+"22222");
        System.out.println(params);
        ModelAndView modelAndView = new ModelAndView();
        PageInfo<Map<String, Object>> pageInfo = modifyService.findAllUsersPageList(params);
        System.out.println(pageInfo);
        modelAndView.addObject("page",pageInfo);
        modelAndView.addObject("list",pageInfo.getList());
        modelAndView.setViewName("/vip/accountPage");
        modelAndView.addObject("params",params);
        return modelAndView;
    }

    /**
     * /跳转积分页面
     * @param params
     * @return
     */
    @RequestMapping("integralFind")
    public ModelAndView integralFind(@RequestParam Map<String,Object> params){
        String vipNo= (String) params.get("vipNo");
        System.out.println(vipNo+"xxxxxx");
        ModelAndView modelAndView=new ModelAndView();
        PageInfo<Map<String, Object>> pageInfo = modifyService.findIntegral(params);
        System.out.println(params);
//        System.out.println(lm+"erbi");
//        modelAndView.setViewName("/base/integralFind");
//        modelAndView.addObject("lm",lm);
        modelAndView.addObject("page",pageInfo);
        modelAndView.addObject("lm",pageInfo.getList());
        modelAndView.setViewName("/vip/integralFind");
        modelAndView.addObject("params",params);
        return modelAndView;
    }

    @RequestMapping("findCoupon")
    public ModelAndView findCoupon(String vipNo){
        ModelAndView modelAndView=new ModelAndView();
        List<Map<String,Object>> list= modifyService.findCoupon(vipNo);
        modelAndView.addObject("list",list);
        modelAndView.setViewName("/vip/findCoupon");
        return modelAndView;
    }

}
