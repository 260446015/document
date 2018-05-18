package com.huishu.openeyes.repository;

import java.util.List;

import com.huishu.openeyes.openenum.MethodMappingEnum;
import org.springframework.data.repository.CrudRepository;

import com.huishu.openeyes.annotations.OpenEnum;
import com.huishu.openeyes.entity.open.Patents;

/**
 * 
 * @author yindawei 
 * @date 2018年2月8日下午3:07:14
 * @description 专利
 * @version
 */
@OpenEnum(price=0.05,url="http://open.api.tianyancha.com/services/v4/open/patents",method = MethodMappingEnum.PATENTS)
public interface PatentsRepository extends CrudRepository<Patents, Long> {

	List<Patents> findByApplicantName(String cname);

}
