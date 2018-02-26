package com.huishu.openeyes.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.openeyes.annotations.OpenEnum;
import com.huishu.openeyes.entity.open.Bond;

/**
 * 债券信息
 * @author yindawei 
 * @date 2018年2月8日上午10:02:30
 * @description 
 * @version
 */
@OpenEnum(price=0.15,url="https://open.api.tianyancha.com/services/v3/newopen/bond.json")
public interface BondRepository extends CrudRepository<Bond, Long> {

	List<Bond> findByCname(String cname);

}
