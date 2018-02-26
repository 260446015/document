package com.huishu.openeyes.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.openeyes.annotations.OpenEnum;
import com.huishu.openeyes.entity.open.HistoryRongZi;

/**
 * 
 * @author yindawei 
 * @date 2018年2月8日下午2:38:07
 * @description 融资历史
 * @version
 */
@OpenEnum(price=0.07,url="https://open.api.tianyancha.com/services/v3/newopen/findHistoryRongzi.json")
public interface HistoryRongZiRepository extends CrudRepository<HistoryRongZi, Long> {

	List<HistoryRongZi> findByCompanyName(String companyName);

}
