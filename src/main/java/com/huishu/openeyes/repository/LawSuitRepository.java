package com.huishu.openeyes.repository;

import java.util.List;

import com.huishu.openeyes.openenum.MethodMappingEnum;
import org.springframework.data.repository.CrudRepository;

import com.huishu.openeyes.annotations.OpenEnum;
import com.huishu.openeyes.entity.open.LawSuit;

/**
 * 
 * @author yindawei 
 * @date 2018年2月8日下午2:57:06
 * @description 法律诉讼
 * @version
 */
@OpenEnum(price=0.05,url="http://open.api.tianyancha.com/services/v4/open/lawSuit",method = MethodMappingEnum.LAWSUIT)
public interface LawSuitRepository extends CrudRepository<LawSuit, String> {

	List<LawSuit> findByCname(String cname);

}
