package com.huishu.openeyes.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.openeyes.annotations.OpenEnum;
import com.huishu.openeyes.entity.open.TaxCredit;

/**
 * 
 * @author yindawei 
 * @date 2018年2月8日下午3:22:16
 * @description 税务评级
 * @version
 */
@OpenEnum(price=0.15,url="http://open.api.tianyancha.com/services/v4/open/taxCredit")
public interface TaxCreditRepository extends CrudRepository<TaxCredit, String> {

	List<TaxCredit> findByName(String cname);

}
