package com.huishu.openeyes.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.openeyes.annotations.OpenEnum;
import com.huishu.openeyes.entity.open.BonusInfo;

/**
 * 
 * @author yindawei 
 * @date 2018年2月8日上午10:03:20
 * @description 【web版】分红情况（股票）
 * @version
 */
@OpenEnum(price=0.07,url="https://open.api.tianyancha.com/services/v3/newopen/bonusInfo.json")
public interface BonusInfoRepository extends CrudRepository<BonusInfo, String> {

	List<BonusInfo> findByCname(String cname);

}
