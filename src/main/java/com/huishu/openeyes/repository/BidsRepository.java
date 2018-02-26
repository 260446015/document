package com.huishu.openeyes.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.openeyes.annotations.OpenEnum;
import com.huishu.openeyes.entity.open.Bids;

/**
 * 招投标
 * @author yindawei 
 * @date 2018年2月8日上午10:01:01
 * @description 
 * @version
 */
@OpenEnum(price=0.15,url="https://open.api.tianyancha.com/services/v3/newopen/bids.json")
public interface BidsRepository extends CrudRepository<Bids, String> {

	List<Bids> findByCname(String cname);

}
