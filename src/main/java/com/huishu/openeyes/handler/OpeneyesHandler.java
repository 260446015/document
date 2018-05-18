package com.huishu.openeyes.handler;

import java.io.File;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.util.StringUtil;
import com.huishu.openeyes.common.DataCheckUtil;
import com.huishu.openeyes.entity.open.SearchCount;
import com.huishu.openeyes.openenum.MethodMappingEnum;
import com.huishu.openeyes.repository.SearchCountRepository;
import com.huishu.openeyes.service.strategy.Context;
import com.huishu.openeyes.service.strategy.ISaveData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.huishu.openeyes.annotations.OpenEnum;
import com.huishu.openeyes.common.Digests;
import com.huishu.openeyes.common.Encodes;
import com.huishu.openeyes.service.OpeneyesService;

/**
 * @author ydw
 */
public class OpeneyesHandler implements InvocationHandler {

    private OpeneyesService openeyesService;
    private static Logger log = LoggerFactory.getLogger(OpeneyesHandler.class);

    public OpeneyesHandler(OpeneyesService openeyesService) {
        this.openeyesService = openeyesService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Map<String, String> params = (Map<String, String>) args[0];
        JSONObject localData = null;
        OpenEnum oe = null;
        try {
            Map<OpenEnum, JSONObject> result = searchFromLocal(params);
            for (Map.Entry<OpenEnum, JSONObject> entry:result.entrySet()) {
                localData = entry.getValue();
                oe = entry.getKey();
            }
            boolean checkLocal = checkMessage(localData,params);
            if (checkLocal) {
                return localData;
            }else{
                String url = oe.url();
                if(StringUtil.isEmpty(url)){
                    throw new Exception("查询时,url获取为空,请检查注解是否添加url项!");
                }
                args[1] = url;
            }
        } catch (Exception e) {
            log.error("查询本地数据库出错，调用参数为" + params.toString(), e.getMessage());
            throw e;
        }
        JSONObject openData = new JSONObject();
        try {
            openData = (JSONObject) method.invoke(openeyesService, args);
        } catch (Exception e) {
            log.error("调用天眼查接口数据出错，调用参数为" + args, e.getMessage());
        }
        boolean checkOpen = checkMessage(openData, params);
        if (checkOpen) {
            saveSearChTrace(params,oe);
            try {
                saveData(openData, params);
            } catch (Exception e) {
                log.error("存储天眼查信息出错，存储的数据为:" + openData + "。存储参数params为:" + params, e.getMessage());
            }
            return openData;
        } else {
            finishData(openData);
        }
        return openData;

    }

    private void saveData(JSONObject openEyesTarget, Map<String, String> params) {
        String method = params.get("method");
        String name = params.get("name");
        log.info("保存数据开始---调用接口:" + method + "---保存公司名称:" + name);
        Class<?> aClass = MethodMappingEnum.getServiceClass(method);
        ISaveData bean = (ISaveData)beanNameContext.getBean(aClass);
        Context context = new Context(bean);
        try {
            context.doSaveData(openEyesTarget,name);
        } catch (Exception e) {
            log.error("存储数据发生异常:调用方法:" + method + "调用参数为:" + params, e.getMessage());
        }
    }

    private JSONObject finishData(JSONObject resultData) {
        resultData.put("err_msg", "查询数据为空");
        resultData.put("err_code", "999");
        return resultData;
    }

    private boolean checkMessage(JSONObject data, Map<String, String> params) {
        log.info("当前检查数据为:" + data + "参数为:" + params);
        if (data.size() <= 1) {
            return false;
        }
        Integer errorCode = data.getInteger("error_code");
        if (errorCode != null) {
            if (errorCode == 0 && data.getString("reason").equalsIgnoreCase("ok")) {// 请求成功开始计费
                return true;
            } else if (errorCode == 300000) {// 无数据
                return false;
            } else if (errorCode == 300001) {// 请求失败
                return false;
            } else if (errorCode == 300002) {// 账号失效
                return false;
            } else if (errorCode == 300003) {// 账号过期
                return false;
            } else if (errorCode == 300004) {// 访问频率过快
                return false;
            } else if (errorCode == 300005) {// 无权限访问此api
                return false;
            } else if (errorCode == 300006) {// 余额不足
                return false;
            } else if (errorCode == 300007) {// 剩余次数不足
                return false;
            } else if (errorCode == 300008) {// 缺少必要参数
                return false;
            } else if (errorCode == 300009) {// 账号信息有误
                return false;
            } else if (errorCode == 300010) {// URL不存在
                return false;
            }
        } else {//本地数据跑这里(包括不规范的天眼查数据)
            if (data.size() == 1) {//本地数据
                return true;
            }
            if (data.getInteger("totalRows") != null && data.getIntValue("totalRows") != 0) {
                return true;
            }
            if (data.getJSONObject("data") != null && data.getJSONObject("data").getInteger("total") != null && data.getJSONObject("data").getInteger("total") != 0) {
                return true;
            }
            if (data.getJSONObject("data") != null && data.getJSONObject("data").getInteger("count") != null && data.getJSONObject("data").getInteger("count") != 0) {
                return true;
            }
            if (data.getInteger("total") != null && data.getInteger("total") != null) {
                return true;
            }
        }
        return false;

    }

    private void saveSearChTrace(Map<String, String> params,OpenEnum openEnum) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            String today = format.format(date);
            String method = params.get("method");
            String tag = params.get("tag");
            SearchCount search = new SearchCount();
            search.setToday(today);
            search.setStartTime(date.getTime());
            search.setTag(tag);
            search.setPrice(openEnum.price());
            search.setMethod(method);
            searchCountRepository.save(search);
        } catch (Exception e) {
            throw e;
        }
    }

    protected String getGeneratedId(Object info) {
        byte[] hashPassword = Digests.sha1(info.toString().getBytes(), null, Encodes.HASH_INTERATIONS);
        return Encodes.encodeHex(hashPassword);
    }

    private void doScanner(String packageName,List<String> methodMapping){
        URL url = this.getClass().getClassLoader().getResource(packageName.replaceAll("\\.","/"));
        File classDir = new File(url.getFile());
        for (File file: classDir.listFiles()) {
            if(file.isDirectory()){
                doScanner(packageName+"."+file.getName().substring(0,file.getName().indexOf(".")),methodMapping);
            }else{
                try {
                    OpenEnum openEnum = Class.forName(packageName + "." + file.getName().substring(0,file.getName().indexOf("."))).getAnnotation(OpenEnum.class);
                    if(openEnum != null){
                        methodMapping.add(packageName+"."+file.getName().substring(0,file.getName().indexOf(".")));
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private Map<OpenEnum, JSONObject> searchFromLocal(Map<String, String> params) throws Exception {
        log.info("查询本地数据库开始-------,参数为:" + params);
        JSONObject resultData = new JSONObject();
        String method = params.get("method");
        String name = params.get("name");
        Map<OpenEnum, JSONObject> privateParams = new HashMap<>(1);
        List<String> methodMapping = new ArrayList<>();
        doScanner("com.huishu.openeyes.repository",methodMapping);
        OpenEnum openEnum = null;
        if(methodMapping.size() != 0){
            A:for (String rep : methodMapping) {
                try {
                    openEnum = Class.forName(rep).getAnnotation(OpenEnum.class);
                    String openMethod = openEnum.method();
                    if(openMethod.equalsIgnoreCase(method)){
                        Object bean = beanNameContext.getBean(Class.forName(rep));
                        Method[] methods = bean.getClass().getDeclaredMethods();
                        B:for (Method invokeMethod : methods) {
                            if(invokeMethod.getName().contains("findBy")){
                                List<Object> list = (List<Object>) invokeMethod.invoke(bean, name);
                                if(list.size() != 0){
                                    boolean flag = dataCheckUtil.checkLocalDataIsExpire(list);
                                    if(flag){
                                        bean.getClass().getMethod("delete",Iterable.class).invoke(bean,list);
                                    }else{
                                        JSONObject inList = new JSONObject();
                                        inList.put("items", list);
                                        resultData.put("result", inList);
                                    }
                                }
                                break A;
                            }
                        }
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        log.info("查询本地数据resultData:" + resultData);
        privateParams.put(openEnum,resultData);
        return privateParams;
    }

    @Autowired
    private SearchCountRepository searchCountRepository;
    @Autowired
    private BeanNameContext beanNameContext;
    @Autowired
    private DataCheckUtil dataCheckUtil;
}
