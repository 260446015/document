package com.huishu.openeyes.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.openeyes.annotations.OpenEnum;
import com.huishu.openeyes.entity.open.CourtAnnouncement;

/**
 * 
 * @author yindawei 
 * @date 2018年2月8日上午10:29:35
 * @description 法院公告
 * @version
 */
@OpenEnum(price=0.05,url="http://open.api.tianyancha.com/services/v4/open/courtAnnouncement")
public interface CourtAnnouncementRepository extends CrudRepository<CourtAnnouncement, Long> {

	List<CourtAnnouncement> findByCname(String cname);

}
