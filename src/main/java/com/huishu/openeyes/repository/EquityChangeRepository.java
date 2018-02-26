package com.huishu.openeyes.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.openeyes.annotations.OpenEnum;
import com.huishu.openeyes.entity.open.EquityChange;

/**
 * 
 * @author yindawei 
 * @date 2018年2月8日上午11:14:00
 * @description 【web版】股本变动（股票）
 * @version
 */
@OpenEnum(price=0.07,url="https://open.api.tianyancha.com/services/v3/newopen/equityChange.json")
public interface EquityChangeRepository extends CrudRepository<EquityChange, String> {

	List<EquityChange> findByCname(String cname);

}
