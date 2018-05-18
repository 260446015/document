package com.huishu.openeyes.service.strategy;

import com.alibaba.fastjson.JSONObject;

/**
 * @author ydw
 * Created on 2018/5/17
 */
public class Context {

    private ISaveData iSaveData;
    public Context(ISaveData iSaveData) {
        this.iSaveData = iSaveData;
    }

    public void doSaveData(JSONObject jsonObject,String name) throws Exception {
        iSaveData.savaData(jsonObject,name);
    }
}
