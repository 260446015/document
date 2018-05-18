package com.huishu.openeyes.service.strategy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.openeyes.entity.open.ShangBiao;
import com.huishu.openeyes.repository.ShangBiaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ydw
 * Created on 2018/5/18
 */
@Service
public class TmStrategy implements ISaveData {
    @Autowired
    private ShangBiaoRepository shangBiaoRepository;
    @Override
    public boolean savaData(JSONObject jsonObject,String name) {
        JSONArray jsonArray = jsonObject.getJSONObject("result").getJSONArray("items");
        if (jsonArray != null || jsonArray.size() == 0) {
            List<ShangBiao> list = new ArrayList<>();
            jsonArray.forEach(obj -> {
                ShangBiao parseObject = JSONObject.parseObject(obj.toString(), ShangBiao.class);
                parseObject.setCreationTime(System.currentTimeMillis());
                list.add(parseObject);
            });
            try {
                shangBiaoRepository.save(list);
            } catch (Exception e) {
                throw e;
            }
        }
        return true;
    }
}
