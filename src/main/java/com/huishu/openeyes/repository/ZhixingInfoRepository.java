package com.huishu.openeyes.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.openeyes.annotations.OpenEnum;
import com.huishu.openeyes.entity.open.ZhixingInfo;

/**
 * 
 * @author yindawei 
 * @date 2018年2月8日下午3:26:33
 * @description 被执行人
 * @version
 */
@OpenEnum(price=0.05,url="http://open.api.tianyancha.com/services/v4/open/zhixinginfo")
public interface ZhixingInfoRepository extends CrudRepository<ZhixingInfo, String> {

	List<ZhixingInfo> findByCname(String cname);

}
