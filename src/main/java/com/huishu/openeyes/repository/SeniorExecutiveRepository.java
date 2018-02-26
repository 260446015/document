package com.huishu.openeyes.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.openeyes.annotations.OpenEnum;
import com.huishu.openeyes.entity.open.SeniorExecutive;

/**
 * 
 * @author yindawei 
 * @date 2018年2月8日下午3:14:48
 * @description 高管信息（股票）
 * @version
 */
@OpenEnum(price=0.07,url="https://open.api.tianyancha.com/services/v3/newopen/seniorExecutive.json")
public interface SeniorExecutiveRepository extends CrudRepository<SeniorExecutive, String> {

	List<SeniorExecutive> findByCname(String cname);

}
