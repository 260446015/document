package com.huishu.openeyes.entity.open;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/***
 * 用来映射查询广西项目查询天眼查接口的实体类
 * 
 * @author yindawei
 * @date 2017年11月16日下午4:17:05
 * @description
 * @version
 */
@Table(name = "t_search_count")
@Entity
public class SearchCount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3725298032617999071L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	/** 第一次查询时间 */
	private Long startTime;
	/** 最后一次查询时间 */
	private Long lastTime;
	/** 查询日期 */
	private String today;
	/** 来源 */
	private String fromType;
	/** 所属用户 */
	private String tag;
	/** 单价 */
	private double price;
	/** 访问接口 */
	private String method;

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getLastTime() {
		return lastTime;
	}

	public void setLastTime(Long lastTime) {
		this.lastTime = lastTime;
	}

	public String getToday() {
		return today;
	}

	public void setToday(String today) {
		this.today = today;
	}

	public String getFromType() {
		return fromType;
	}

	public void setFromType(String fromType) {
		this.fromType = fromType;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}
