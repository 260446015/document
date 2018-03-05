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
@OpenEnum(price=0.15,url="http://open.api.tianyancha.com/services/v4/open/bond")
public interface BondRepository extends CrudRepository<Bond, Long> {

	List<Bond> findByCname(String cname);

}
