package com.huishu.openeyes.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.openeyes.annotations.OpenEnum;
import com.huishu.openeyes.entity.open.RiskInfo;

/**
 * 
 * @author yindawei 
 * @date 2018年2月8日下午3:12:48
 * @description 企业风险
 * @version
 */
@OpenEnum(price=0.1,url="https://open.api.tianyancha.com/services/v3/newopen/riskInfo.json")
public interface RiskInfoRepository extends CrudRepository<RiskInfo, Long> {

	List<RiskInfo> findByCname(String cname);

}
