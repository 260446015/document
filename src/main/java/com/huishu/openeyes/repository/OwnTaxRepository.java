package com.huishu.openeyes.repository;

import java.util.List;

import com.huishu.openeyes.openenum.MethodMappingEnum;
import org.springframework.data.repository.CrudRepository;

import com.huishu.openeyes.annotations.OpenEnum;
import com.huishu.openeyes.entity.open.OwnTax;
/**
 * @author yindawei 
 * @date 2018年2月8日下午3:05:57
 * @description 欠税公告
 * @version
 */
@OpenEnum(price=0.15,url="http://open.api.tianyancha.com/services/v4/open/ownTax",method = MethodMappingEnum.OWNTAX)
public interface OwnTaxRepository extends CrudRepository<OwnTax, String> {

	List<OwnTax> findByName(String cname);

}
