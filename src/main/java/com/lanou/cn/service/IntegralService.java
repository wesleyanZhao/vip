package com.lanou.cn.service;


import java.util.Map;

/**
 * Created by admin on 28/6/17.
 */
public interface IntegralService {

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




}
