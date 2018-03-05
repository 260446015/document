package com.huishu.openeyes.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.openeyes.annotations.OpenEnum;
import com.huishu.openeyes.entity.open.ShareStructure;

/**
 * 
 * @author yindawei 
 * @date 2018年2月8日下午3:18:53
 * @description 【web版】股本结构（股票）
 * @version
 */
@OpenEnum(price=0.07,url="http://open.api.tianyancha.com/services/v4/open/shareStructure")
public interface ShareStructureRepository extends CrudRepository<ShareStructure, String> {

	List<ShareStructure> findByCname(String cname);

}
