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
@OpenEnum(price=0.07,url="http://open.api.tianyancha.com/services/v4/open/volatility")
public interface VolatilityRepository extends CrudRepository<Volatility, String> {

	List<Volatility> findByCname(String cname);

}
