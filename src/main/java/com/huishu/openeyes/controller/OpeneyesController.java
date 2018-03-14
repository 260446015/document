package com.huishu.openeyes.controller;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.huishu.openeyes.handler.OpeneyesHandler;
import com.huishu.openeyes.service.OpeneyesService;

@RestController
@RequestMapping(value="/openeyes")
public class OpeneyesController extends BaseController{

	@Autowired
	private OpeneyesService openeyesService;
	@Autowired
	private OpeneyesHandler handler;
	@RequestMapping(value="/search.json",method={RequestMethod.GET})
	public JSONObject searchMessage(HttpServletRequest request){
		Map<String, String[]> parameterMap = request.getParameterMap();
		Map<String, String> openParam = getOpenParam(parameterMap);
		OpeneyesService handlerService = (OpeneyesService) Proxy.newProxyInstance(openeyesService.getClass().getClassLoader(), new Class[]{OpeneyesService.class}, handler);
		JSONObject resultData = new JSONObject();
		try {
			resultData = handlerService.searchMessage(openParam,"");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultData;
	}
	
	private Map<String, String> getOpenParam(Map<String, String[]> parameterMap) {
		Map<String, String> openParams = new HashMap<>();
		parameterMap.forEach((k,v) ->{
			String value = v[0];
			openParams.put(k, value);
		});
		return openParams;
	}
}
