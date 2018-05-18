package com.huishu.openeyes.service.strategy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.openeyes.entity.open.Allotmen;
import com.huishu.openeyes.repository.AllotmenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ydw
 * Created on 2018/5/18
 */
@Service
public class AllotmenStrategy implements ISaveData{
    @Autowired
    private AllotmenRepository allotmenRepository;
    @Override
    public boolean savaData(JSONObject jsonObject,String name) {
        JSONArray jsonArr = jsonObject.getJSONObject("result").getJSONArray("dataList");
        if (jsonArr != null || jsonArr.size() == 0) {
            List<Allotmen> list = new ArrayList<>();
            jsonArr.forEach(obj -> {
                Allotmen parseObject = JSONObject.parseObject(obj.toString(), Allotmen.class);
                parseObject.setCreationTime(System.currentTimeMillis());
                parseObject.setCname(name);
                String id = getGeneratedId(obj);
                parseObject.setId(id);
                list.add(parseObject);
            });
            try {
                allotmenRepository.save(list);
            } catch (Exception e) {
                throw e;
            }
        }
        return true;
    }
}
