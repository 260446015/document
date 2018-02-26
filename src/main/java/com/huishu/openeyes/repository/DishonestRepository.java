package com.huishu.openeyes.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.openeyes.annotations.OpenEnum;
import com.huishu.openeyes.entity.open.Dishonest;

/**
 * 
 * @author yindawei 
 * @date 2018年2月8日上午10:30:37
 * @description 失信人
 * @version
 */
@OpenEnum(price=0.05,url="https://open.api.tianyancha.com/services/v3/newopen/dishonest.json")
public interface DishonestRepository extends CrudRepository<Dishonest, Long> {

	List<Dishonest> findByIname(String cname);

}
