package com.huishu.openeyes.repository;

import java.util.List;

import com.huishu.openeyes.openenum.MethodMappingEnum;
import org.springframework.data.repository.CrudRepository;

import com.huishu.openeyes.annotations.OpenEnum;
import com.huishu.openeyes.entity.open.Announcement;

/**
 * 上市公告（股票）
 * @author yindawei 
 * @date 2018年2月8日上午9:28:03
 * @description 
 * @version
 */
@OpenEnum(price=0.07,url="https://open.api.tianyancha.com/services/v4/open/announcement",method = MethodMappingEnum.ANNOUNCEMENT)
public interface AnnouncementRepository extends CrudRepository<Announcement, String> {

	List<Announcement> findByCname(String cname);

}
