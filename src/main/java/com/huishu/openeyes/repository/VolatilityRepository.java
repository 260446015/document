package com.huishu.openeyes.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.openeyes.annotations.OpenEnum;
import com.huishu.openeyes.entity.open.Volatility;

/**
 * 
 * @author yindawei 
 * @date 2018年2月8日下午3:25:15
 * @description 股票行情
 * @version
 */
@OpenEnum(price=0.07,url="https://open.api.tianyancha.com/services/v3/newopen/volatility.json")
public interface VolatilityRepository extends CrudRepository<Volatility, String> {

	List<Volatility> findByCname(String cname);

}
