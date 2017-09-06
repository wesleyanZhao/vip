package com.lanou.cn.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Map;


/**
 * Created by admin on 28/6/17.
 */
public interface IntegralMapper {


        @Update("update vip_info set acc_point=acc_point+#{params.point} where id=#{params.id}")
        void returnPoint(@Param("params") Map<String, Object> params);

        @Update("update vip_info set acc_point=acc_point-#{params.point} where id=#{params.id}")
        void usePoint(@Param("params") Map<String, Object> params);


        @Select("SELECT acc_point from vip_info WHERE id=#{params.id}")
        int findPoint(@Param("params") Map<String, Object> params);

        @Insert("insert into integral_record(vip_no,point_num,rcd_date,order_no,point_state) values(#{params.vipNo},#{params.point},now(),#{params.orderNo}),u")
        void addUsePointRecord(@Param("params") Map<String, Object> params);


        @Insert("insert into integral_record(vip_no,point_num,rcd_date,order_no,point_state) values(#{params.vipNo},#{params.point},now(),#{params.orderNo}),r")
        void addReturnPointRecord(@Param("params") Map<String, Object> params);

}
