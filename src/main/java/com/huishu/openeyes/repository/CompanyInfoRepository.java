package com.huishu.openeyes.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.openeyes.annotations.OpenEnum;
import com.huishu.openeyes.entity.open.CompanyInfo;

/**
 * 
 * @author yindawei 
 * @date 2018年2月8日上午10:22:50
 * @description 企业简介（股票）
 * @version
 */
@OpenEnum(price=0.07,url="http://open.api.tianyancha.com/services/v4/open/companyInfo")
public interface CompanyInfoRepository extends CrudRepository<CompanyInfo, String> {

	List<CompanyInfo> findByCompanyName(String cname);

}
