package com.huishu.openeyes.repository;

import java.util.List;

import com.huishu.openeyes.openenum.MethodMappingEnum;
import org.springframework.data.repository.CrudRepository;

import com.huishu.openeyes.annotations.OpenEnum;
import com.huishu.openeyes.entity.open.Holder;

/**
 * 
 * @author yindawei 
 * @date 2018年2月8日下午2:39:25
 * @description 股东信息
 * @version
 */
@OpenEnum(price=0.1,url="http://open.api.tianyancha.com/services/v4/open/holder",method = MethodMappingEnum.HOLDER)
public interface HolderRepository extends CrudRepository<Holder, Long> {

	List<Holder> findByCname(String cname);

}
