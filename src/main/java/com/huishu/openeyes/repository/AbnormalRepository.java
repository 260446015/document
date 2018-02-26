package com.huishu.openeyes.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.openeyes.annotations.OpenEnum;
import com.huishu.openeyes.entity.open.Abnormal;

/**
 * 经营异常
 * @author yindawei 
 * @date 2018年2月8日上午9:26:24
 * @description 
 * @version
 */
@OpenEnum(price=0.15,url="https://open.api.tianyancha.com/services/v3/newopen/abnormal.json")
public interface AbnormalRepository extends CrudRepository<Abnormal, String>{

	List<Abnormal> findByCompanyName(String cname);

}
