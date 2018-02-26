package com.huishu.openeyes.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.openeyes.annotations.OpenEnum;
import com.huishu.openeyes.entity.open.IssueRelated;

/**
 * 
 * @author yindawei 
 * @date 2018年2月8日下午2:48:15
 * @description 发行相关（股票）
 * @version
 */
@OpenEnum(price=0.07,url="https://open.api.tianyancha.com/services/v3/newopen/issueRelated.json")
public interface IssueRelatedRepository extends CrudRepository<IssueRelated, String> {

	List<IssueRelated> findByCname(String cname);

}
