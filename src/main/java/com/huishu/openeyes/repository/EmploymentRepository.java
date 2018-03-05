package com.huishu.openeyes.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.openeyes.annotations.OpenEnum;
import com.huishu.openeyes.entity.open.Employment;

/**
 * 
 * @author yindawei 
 * @date 2018年2月8日上午11:12:24
 * @description 招聘信息
 * @version
 */
@OpenEnum(price=0.15,url="http://open.api.tianyancha.com/services/v4/open/employments")
public interface EmploymentRepository extends CrudRepository<Employment, Long> {

	List<Employment> findByCompanyName(String cname);

}
