package com.huishu.openeyes.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.openeyes.annotations.OpenEnum;
import com.huishu.openeyes.entity.open.OwnTax;
/**
 * @author yindawei 
 * @date 2018年2月8日下午3:05:57
 * @description 欠税公告
 * @version
 */
@OpenEnum(price=0.15,url="https://open.api.tianyancha.com/services/v3/newopen/ownTax.json")
public interface OwnTaxRepository extends CrudRepository<OwnTax, String> {

	List<OwnTax> findByName(String cname);

}
