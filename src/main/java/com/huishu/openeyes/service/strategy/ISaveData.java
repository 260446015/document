package com.huishu.openeyes.service.strategy;

import com.alibaba.fastjson.JSONObject;
import com.huishu.openeyes.common.Digests;
import com.huishu.openeyes.common.Encodes;

/**
 * @author ydw
 * Created on 2018/5/17
 */
public interface ISaveData {
    public boolean savaData(JSONObject jsonObject,String name) throws Exception;

    public default String getGeneratedId(Object info) {
        byte[] hashPassword = Digests.sha1(info.toString().getBytes(), null, Encodes.HASH_INTERATIONS);
        return Encodes.encodeHex(hashPassword);
    }

}
