package com.huishu.openeyes.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.openeyes.annotations.OpenEnum;
import com.huishu.openeyes.entity.open.CheckInfo;

/**
 * 
 * @author yindawei 
 * @date 2018年2月8日上午10:11:40
 * @description 抽查检查
 * @version
 */
@OpenEnum(price=0.15,url="http://open.api.tianyancha.com/services/v4/open/checkInfo")
public interface CheckInfoRepository extends CrudRepository<CheckInfo, String> {

	List<CheckInfo> findByCname(String cname);

}
