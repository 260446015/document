package com.huishu.openeyes.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.openeyes.annotations.OpenEnum;
import com.huishu.openeyes.entity.open.Staff;

/**
 * 
 * @author yindawei 
 * @date 2018年2月8日下午3:21:14
 * @description 主要人员
 * @version
 */
@OpenEnum(price=0.1,url="https://open.api.tianyancha.com/services/v3/newopen/staff.json")
public interface StaffRepository extends CrudRepository<Staff, Long> {

	List<Staff> findByCname(String cname);

}
