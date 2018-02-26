package com.huishu.openeyes.repository;

import java.util.List;

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
@OpenEnum(price=0.15,url="https://open.api.tianyancha.com/services/v3/newopen/certificate.json")
public interface CertificateRepository extends CrudRepository<Certificate, String> {

	List<Certificate> findByCname(String cname);

}
