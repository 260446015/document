package com.huishu.openeyes.repository;

import com.huishu.openeyes.openenum.MethodMappingEnum;
import org.springframework.data.repository.CrudRepository;

import com.huishu.openeyes.annotations.OpenEnum;
import com.huishu.openeyes.entity.open.BaseInfo;

import java.util.List;

@OpenEnum(price=0.1,url="http://open.api.tianyancha.com/services/v4/open/baseinfoV2",method = MethodMappingEnum.BASEINFOV2)
public interface BaseInfoRepository extends CrudRepository<BaseInfo, Long> {

	List<BaseInfo> findByName(String name);

}
