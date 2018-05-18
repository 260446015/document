package com.huishu.openeyes.service.strategy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.openeyes.entity.open.JingPin;
import com.huishu.openeyes.repository.JingPinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ydw
 * Created on 2018/5/18
 */
@Service
public class FindJingpinStrategy implements ISaveData {
    @Autowired
    private JingPinRepository jingPinRepository;
    @Override
    public boolean savaData(JSONObject jsonObject,String name) {
        JSONArray jsonArray = jsonObject.getJSONObject("result").getJSONArray("items");
        if (jsonArray != null || jsonArray.size() == 0) {
            List<JingPin> list = new ArrayList<>();
            jsonArray.forEach(obj -> {
                JingPin parseObject = JSONObject.parseObject(obj.toString(), JingPin.class);
                String id = getGeneratedId(parseObject);
                parseObject.setId(id);
                parseObject.setCname(name);
                parseObject.setCreationTime(System.currentTimeMillis());
                list.add(parseObject);
            });
            try {
                jingPinRepository.save(list);
            } catch (Exception e) {
                throw e;
            }
        }
        return true;
    }
}
