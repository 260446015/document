package com.huishu.openeyes.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.openeyes.annotations.OpenEnum;
import com.huishu.openeyes.entity.open.RiskDetail;

/**
 * 
 * @author yindawei 
 * @date 2018年2月8日下午3:11:32
 * @description 风险信息
 * @version
 */
@OpenEnum(price=0.1,url="http://open.api.tianyancha.com/services/v4/open/riskDetail")
public interface RiskDetailRepository extends CrudRepository<RiskDetail, String> {

	List<RiskDetail> findByCompanyName(String cname);

	List<RiskDetail> findBySearchId(Long id);

}
