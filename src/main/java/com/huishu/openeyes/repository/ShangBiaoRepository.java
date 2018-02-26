package com.huishu.openeyes.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.openeyes.annotations.OpenEnum;
import com.huishu.openeyes.entity.open.ShangBiao;

/**
 * 
 * @author yindawei 
 * @date 2018年2月8日下午3:16:02
 * @description 商标信息
 * @version
 */
@OpenEnum(price=0.05,url="https://open.api.tianyancha.com/services/v3/newopen/tm.json")
public interface ShangBiaoRepository extends CrudRepository<ShangBiao, Long> {

	List<ShangBiao> findByApplicantCn(String cname);

}
