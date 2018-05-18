package com.huishu.openeyes.repository;

import java.util.List;

import com.huishu.openeyes.openenum.MethodMappingEnum;
import org.springframework.data.repository.CrudRepository;

import com.huishu.openeyes.annotations.OpenEnum;
import com.huishu.openeyes.entity.open.CopyReg;

/**
 * 
 * @author yindawei 
 * @date 2018年2月8日上午10:28:33
 * @description 著作权
 * @version
 */
@OpenEnum(price=0.15,url="http://open.api.tianyancha.com/services/v4/open/copyReg",method = MethodMappingEnum.COPYREG)
public interface CopyRegRepository extends CrudRepository<CopyReg, Long> {

	List<CopyReg> findByCompanyName(String cname);

}
