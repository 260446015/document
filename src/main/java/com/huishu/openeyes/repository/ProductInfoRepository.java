package com.huishu.openeyes.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.openeyes.annotations.OpenEnum;
import com.huishu.openeyes.entity.open.ProductInfo;

/**
 * 
 * @author yindawei 
 * @date 2018年2月8日下午3:08:00
 * @description 企业业务
 * @version
 */
@OpenEnum(price=0.07,url="https://open.api.tianyancha.com/services/v3/newopen/getProductInfo.json")
public interface ProductInfoRepository extends CrudRepository<ProductInfo, Long> {

	List<ProductInfo> findByCompanyName(String cname);

}
