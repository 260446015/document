package com.huishu.openeyes.repository;

import java.util.List;

import com.huishu.openeyes.openenum.MethodMappingEnum;
import org.springframework.data.repository.CrudRepository;

import com.huishu.openeyes.annotations.OpenEnum;
import com.huishu.openeyes.entity.open.Inverst;

/**
 * 
 * @author yindawei 
 * @date 2018年2月8日下午2:47:00
 * @description 对外投资
 * @version
 */
@OpenEnum(price=0.07,url="http://open.api.tianyancha.com/services/v4/open/inverst",method = MethodMappingEnum.INVERST)
public interface InverstRepository extends CrudRepository<Inverst, Long> {

	List<Inverst> findByCname(String cname);

}
