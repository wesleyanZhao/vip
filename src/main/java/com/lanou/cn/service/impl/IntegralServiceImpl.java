package com.lanou.cn.service.impl;

import com.lanou.cn.mapper.IntegralMapper;
import com.lanou.cn.service.IntegralService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by admin on 28/6/17.
 */
@Service
public class IntegralServiceImpl implements IntegralService {

	@Resource
	private IntegralMapper integralMapper;

	/**
	 * 返还积分实现
	 * 并且添加记录
	 * @param params
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void returnPoint(Map<String, Object> params) {

		integralMapper.returnPoint(params);
		integralMapper.addReturnPointRecord(params);
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

			integralMapper.usePoint(params);
			int i=integralMapper.findPoint(params);
			integralMapper.addUsePointRecord(params);
			if (0>i){
				Exception e = new Exception();//创建异常对象
				throw e;//抛出异常
			}

	}


}
