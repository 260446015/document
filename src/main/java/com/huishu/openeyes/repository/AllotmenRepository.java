package com.huishu.openeyes.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.openeyes.annotations.OpenEnum;
import com.huishu.openeyes.entity.open.Allotmen;

/**
 * 【web版】配股情况（股票）
 * @author yindawei 
 * @date 2018年2月8日上午9:26:57
 * @description 
 * @version
 */
@OpenEnum(price=0.07,url="https://open.api.tianyancha.com/services/v3/newopen/allotmen.json")
public interface AllotmenRepository extends CrudRepository<Allotmen, String> {

	List<Allotmen> findByCname(String cname);

}
