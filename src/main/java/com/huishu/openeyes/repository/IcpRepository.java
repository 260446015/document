package com.huishu.openeyes.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.openeyes.annotations.OpenEnum;
import com.huishu.openeyes.entity.open.Icp;

/**
 * 
 * @author yindawei 
 * @date 2018年2月8日下午2:43:40
 * @description 网站备案
 * @version
 */
@OpenEnum(price=0.05,url="http://open.api.tianyancha.com/services/v4/open/icp")
public interface IcpRepository extends CrudRepository<Icp, Long> {

	List<Icp> findByCompanyName(String cname);

}
