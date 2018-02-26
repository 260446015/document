package com.huishu.openeyes.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.openeyes.annotations.OpenEnum;
import com.huishu.openeyes.entity.open.Illegalinfo;

/**
 * 
 * @author yindawei 
 * @date 2018年2月8日下午2:44:34
 * @description 严重违法
 * @version
 */
@OpenEnum(price=0.15,url="https://open.api.tianyancha.com/services/v3/newopen/illegalinfo.json")
public interface IllegalinfoRepository extends CrudRepository<Illegalinfo, String> {

	List<Illegalinfo> findByCompanyName(String cname);

}
