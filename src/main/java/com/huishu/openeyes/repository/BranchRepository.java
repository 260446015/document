package com.huishu.openeyes.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.openeyes.annotations.OpenEnum;
import com.huishu.openeyes.entity.open.Branch;

/**
 * 
 * @author yindawei 
 * @date 2018年2月8日上午10:05:39
 * @description 分支机构
 * @version
 */
@OpenEnum(price=0.07,url="https://open.api.tianyancha.com/services/v3/newopen/branch.json")
public interface BranchRepository extends CrudRepository<Branch, Long> {

	List<Branch> findByCompanyName(String cname);

}
