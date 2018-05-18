package com.huishu.openeyes.service.strategy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.openeyes.entity.open.Patents;
import com.huishu.openeyes.repository.PatentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ydw
 * Created on 2018/5/18
 */
@Service
public class PatentsStrategy implements ISaveData {
    @Autowired
    private PatentsRepository patentsRepository;
    @Override
    public boolean savaData(JSONObject jsonObject,String name) {
        JSONArray jsonArray = jsonObject.getJSONObject("result").getJSONArray("items");
        if (jsonArray != null || jsonArray.size() == 0) {
            List<Patents> list = new ArrayList<>();
            jsonArray.forEach(obj -> {
                Patents parseObject = JSONObject.parseObject(obj.toString(), Patents.class);
                parseObject.setCreationTime(System.currentTimeMillis());
                list.add(parseObject);
            });
            try {
                patentsRepository.save(list);
            } catch (Exception e) {
                throw e;
            }
        }
        return true;
    }
}
