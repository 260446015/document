package com.huishu.openeyes.repository;

import java.util.List;

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
@OpenEnum(price=0.07,url="https://open.api.tianyancha.com/services/v3/newopen/shareholder.json")
public interface ShareHolderRepository extends CrudRepository<ShareHolder, String> {

	List<ShareHolder> findByCname(String cname);

}
