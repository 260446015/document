package com.huishu.openeyes.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.openeyes.annotations.OpenEnum;
import com.huishu.openeyes.entity.open.News;
/**
 * 
 * @author yindawei 
 * @date 2018年2月8日下午3:04:34
 * @description 新闻
 * @version
 */
@OpenEnum(price=0.07,url="http://open.api.tianyancha.com/services/v3/open/news")
public interface NewsRepository extends CrudRepository<News, String> {

	List<News> findByCompanyName(String cname);

}
