package com.huishu.openeyes.repository;

import java.util.List;

import com.huishu.openeyes.openenum.MethodMappingEnum;
import org.springframework.data.repository.CrudRepository;

import com.huishu.openeyes.annotations.OpenEnum;
import com.huishu.openeyes.entity.open.HumanRiskInfo;

/**
 * 
 * @author yindawei 
 * @date 2018年2月8日下午2:41:30
 * @description 人风险
 * @version
 */
@OpenEnum(price=0.1,url="http://open.api.tianyancha.com/services/v4/open/humanRiskInfo",method = MethodMappingEnum.HUMANRISKINFO)
public interface HumanRiskInfoRepository extends CrudRepository<HumanRiskInfo, Long> {

	List<HumanRiskInfo> findByCompanyNameAndHumanName(String cname, String humanName);

}
