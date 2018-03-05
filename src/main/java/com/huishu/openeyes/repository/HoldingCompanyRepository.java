package com.huishu.openeyes.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.openeyes.annotations.OpenEnum;
import com.huishu.openeyes.entity.open.HoldingCompany;

/**
 * 
 * @author yindawei 
 * @date 2018年2月8日下午2:40:27
 * @description 参股控股（股票）
 * @version
 */
@OpenEnum(price=0.07,url="https://open.api.tianyancha.com/services/v4/open/holdingCompany")
public interface HoldingCompanyRepository extends CrudRepository<HoldingCompany, Long> {

	List<HoldingCompany> findByCname(String cname);

}
