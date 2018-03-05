package com.huishu.openeyes.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.openeyes.annotations.OpenEnum;
import com.huishu.openeyes.entity.open.ChangeInfo;

/**
 * 
 * @author yindawei 
 * @date 2018年2月8日上午10:10:01
 * @description 变更记录
 * @version
 */
@OpenEnum(price=0.07,url="http://open.api.tianyancha.com/services/v4/open/changeinfo")
public interface ChangeInfoRepository extends CrudRepository<ChangeInfo, String> {

	List<ChangeInfo> findByCname(String cname);

}
