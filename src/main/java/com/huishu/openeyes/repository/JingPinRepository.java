package com.huishu.openeyes.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.openeyes.annotations.OpenEnum;
import com.huishu.openeyes.entity.open.JingPin;

/**
 * 
 * @author yindawei 
 * @date 2018年2月8日下午2:55:47
 * @description 竞品信息
 * @version
 */
@OpenEnum(price=0.15,url="https://open.api.tianyancha.com/services/v3/newopen/findJingpin.json")
public interface JingPinRepository extends CrudRepository<JingPin, Long> {

	List<JingPin> findByCname(String cname);

}
