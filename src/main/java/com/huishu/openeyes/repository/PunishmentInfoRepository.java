package com.huishu.openeyes.repository;

import java.util.List;

import com.huishu.openeyes.openenum.MethodMappingEnum;
import org.springframework.data.repository.CrudRepository;

import com.huishu.openeyes.annotations.OpenEnum;
import com.huishu.openeyes.entity.open.PunishmentInfo;

/**
 * 
 * @author yindawei 
 * @date 2018年2月8日下午3:08:51
 * @description 行政处罚
 * @version
 */
@OpenEnum(price=0.15,url="http://open.api.tianyancha.com/services/v4/open/punishmentInfo",method = MethodMappingEnum.PUNISHMENTINFO)
public interface PunishmentInfoRepository extends CrudRepository<PunishmentInfo, String> {

	List<PunishmentInfo> findByName(String cname);

}
