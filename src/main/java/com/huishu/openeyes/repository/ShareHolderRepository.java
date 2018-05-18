package com.huishu.openeyes.repository;

import java.util.List;

import com.huishu.openeyes.openenum.MethodMappingEnum;
import org.springframework.data.repository.CrudRepository;

import com.huishu.openeyes.annotations.OpenEnum;
import com.huishu.openeyes.entity.open.ShareHolder;

/**
 * 
 * @author yindawei 
 * @date 2018年2月8日下午3:17:44
 * @description 十大股东（十大流通股东）
 * @version
 */
@OpenEnum(price=0.07,url="http://open.api.tianyancha.com/services/v4/open/shareholder",method = MethodMappingEnum.SHAREHOLDER)
public interface ShareHolderRepository extends CrudRepository<ShareHolder, String> {

	List<ShareHolder> findByCname(String cname);

}
