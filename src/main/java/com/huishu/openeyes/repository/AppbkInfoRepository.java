package com.huishu.openeyes.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.openeyes.annotations.OpenEnum;
import com.huishu.openeyes.entity.open.AppbkInfo;

/**
 * 产品信息
 * @author yindawei 
 * @date 2018年2月8日上午9:58:56
 * @description 
 * @version
 */
@OpenEnum(price=0.05,url="http://open.api.tianyancha.com/services/v4/open/appbkInfo")
public interface AppbkInfoRepository extends CrudRepository<AppbkInfo, String> {

	List<AppbkInfo> findByCname(String cname);

}
