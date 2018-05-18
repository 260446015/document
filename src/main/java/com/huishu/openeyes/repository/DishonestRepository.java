package com.huishu.openeyes.repository;

import java.util.List;

import com.huishu.openeyes.openenum.MethodMappingEnum;
import org.springframework.data.repository.CrudRepository;

import com.huishu.openeyes.annotations.OpenEnum;
import com.huishu.openeyes.entity.open.Dishonest;

/**
 * 
 * @author yindawei 
 * @date 2018年2月8日上午10:30:37
 * @description 失信人
 * @version
 */
@OpenEnum(price=0.05,url="http://open.api.tianyancha.com/services/v4/open/dishonest",method = MethodMappingEnum.DISHONEST)
public interface DishonestRepository extends CrudRepository<Dishonest, Long> {

	List<Dishonest> findByIname(String cname);

}
