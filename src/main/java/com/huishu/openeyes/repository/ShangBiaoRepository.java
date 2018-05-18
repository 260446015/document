package com.huishu.openeyes.repository;

import java.util.List;

import com.huishu.openeyes.openenum.MethodMappingEnum;
import org.springframework.data.repository.CrudRepository;

import com.huishu.openeyes.annotations.OpenEnum;
import com.huishu.openeyes.entity.open.ShangBiao;

/**
 * 
 * @author yindawei 
 * @date 2018年2月8日下午3:16:02
 * @description 商标信息
 * @version
 */
@OpenEnum(price=0.05,url="http://open.api.tianyancha.com/services/v4/open/tm",method = MethodMappingEnum.TM)
public interface ShangBiaoRepository extends CrudRepository<ShangBiao, Long> {

	List<ShangBiao> findByApplicantCn(String cname);

}
