package com.huishu.openeyes.service;



import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.huishu.openeyes.common.HttpUtils;

public class OpeneyesServiceImpl implements OpeneyesService {

	@Override
	public JSONObject searchMessage(Map<String,String> params,String spec) {
		//构建返回json对象
		JSONObject resultData = new JSONObject();
		//查询天眼查接口
		Map<String, String> openParam = getOpenParam(params);
		try {
			resultData = JSONObject.parseObject(HttpUtils.sendHttpGet(spec, openParam));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultData;
		
	}
	
	private Map<String, String> getOpenParam(Map<String, String> parameterMap) {
		Map<String, String> openParams = new HashMap<>();
		parameterMap.forEach((k,v) ->{
			if(!StringUtil.isEmpty(v)){
				if(k.equalsIgnoreCase("name")){
					openParams.put("name", v);
				}else if(k.equalsIgnoreCase("id")){
					openParams.put("id", v);
				}else if(k.equalsIgnoreCase("pageNum")){
					openParams.put("pageNum", v);
				}else if(k.equalsIgnoreCase("pageSize")){
					openParams.put("pageSize", v);
				}else if(k.equalsIgnoreCase("humanName")){
					openParams.put("pageSize", v);
				}else if(k.equalsIgnoreCase("word")){
					openParams.put("word", v);
				}else if(k.equalsIgnoreCase("type")){
					openParams.put("type", v);
				}
			}
		});
		return openParams;
	}

}
