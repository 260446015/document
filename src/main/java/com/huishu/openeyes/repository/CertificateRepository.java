package com.huishu.openeyes.repository;

import java.util.List;

import com.huishu.openeyes.openenum.MethodMappingEnum;
import org.springframework.data.repository.CrudRepository;

import com.huishu.openeyes.annotations.OpenEnum;
import com.huishu.openeyes.entity.open.Certificate;

/**
 * 
 * @author yindawei 
 * @date 2018年2月8日上午10:06:55
 * @description 资质证书
 * @version
 */
@OpenEnum(price=0.15,url="http://open.api.tianyancha.com/services/v4/open/certificate",method = MethodMappingEnum.CERTIFICATE)
public interface CertificateRepository extends CrudRepository<Certificate, String> {

	List<Certificate> findByCname(String cname);

}
