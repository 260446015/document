package com.huishu.openeyes.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.openeyes.annotations.OpenEnum;
import com.huishu.openeyes.entity.open.Purchaseland;

/**
 * 
 * @author yindawei 
 * @date 2018年2月8日下午3:10:11
 * @description 购地信息
 * @version
 */
@OpenEnum(price=0.15,url="https://open.api.tianyancha.com/services/v3/newopen/purchaseLand.json")
public interface PurchaselandRepository extends CrudRepository<Purchaseland, Long> {

	List<Purchaseland> findByCname(String cname);

}
