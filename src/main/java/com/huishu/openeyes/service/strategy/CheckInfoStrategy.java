package com.huishu.openeyes.service.strategy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.openeyes.entity.open.CheckInfo;
import com.huishu.openeyes.repository.CheckInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ydw
 * Created on 2018/5/18
 */
@Service
public class CheckInfoStrategy implements ISaveData {
    @Autowired
    private CheckInfoRepository checkInfoRepository;
    @Override
    public boolean savaData(JSONObject jsonObject,String name) {
        JSONArray jsonArr = jsonObject.getJSONObject("result").getJSONArray("items");
        if (jsonArr != null || jsonArr.size() == 0) {
            List<CheckInfo> list = new ArrayList<>();
            jsonArr.forEach(obj -> {
                CheckInfo parseObject = JSONObject.parseObject(obj.toString(), CheckInfo.class);
                parseObject.setCreationTime(System.currentTimeMillis());
                String id = getGeneratedId(obj);
                parseObject.setId(id);
                parseObject.setCname(name);
                list.add(parseObject);
            });
            try {
                checkInfoRepository.save(list);
            } catch (Exception e) {
                throw e;
            }
        }
        return true;
    }
}
