package com.huishu.openeyes.repository;

import java.util.List;

import com.huishu.openeyes.openenum.MethodMappingEnum;
import org.springframework.data.repository.CrudRepository;

import com.huishu.openeyes.annotations.OpenEnum;
import com.huishu.openeyes.entity.open.TeamMember;

/**
 * 
 * @author yindawei 
 * @date 2018年2月8日下午3:23:20
 * @description 核心团队
 * @version
 */
@OpenEnum(price=0.07,url="http://open.api.tianyancha.com/services/v4/open/findTeamMember",method = MethodMappingEnum.FINDTEAMMEMBER)
public interface TeamMemberRepository extends CrudRepository<TeamMember, Long> {

	List<TeamMember> findByCompanyName(String cname);

}
