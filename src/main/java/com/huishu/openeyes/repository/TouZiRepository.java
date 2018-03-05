package com.huishu.openeyes.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.openeyes.annotations.OpenEnum;
import com.huishu.openeyes.entity.open.TouZi;

/**
 * 
 * @author yindawei 
 * @date 2018年2月8日下午3:24:23
 * @description 投资案例
 * @version
 */
@OpenEnum(price=0.07,url="http://open.api.tianyancha.com/services/v4/open/findTzanli")
public interface TouZiRepository extends CrudRepository<TouZi, Long> {

	List<TouZi> findByCompanyName(String cname);

}
