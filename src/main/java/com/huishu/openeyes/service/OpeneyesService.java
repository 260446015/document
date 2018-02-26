package com.huishu.openeyes.service;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public interface OpeneyesService {

	JSONObject searchMessage(Map<String,String> params,String spec) throws Exception;
}
