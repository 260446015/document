package com.huishu.openeyes.repository;

import org.springframework.data.repository.CrudRepository;

import com.huishu.openeyes.annotations.OpenEnum;
import com.huishu.openeyes.entity.open.BaseInfo;

@OpenEnum(price=0.1,url="http://open.api.tianyancha.com/services/v4/open/baseinfoV2")
public interface BaseInfoRepository extends CrudRepository<BaseInfo, Long> {

	BaseInfo findByName(String name);

}
