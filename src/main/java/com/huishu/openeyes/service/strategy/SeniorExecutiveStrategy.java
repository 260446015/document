package com.huishu.openeyes.service.strategy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.openeyes.entity.open.SeniorExecutive;
import com.huishu.openeyes.repository.SeniorExecutiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ydw
 * Created on 2018/5/18
 */
@Service
public class SeniorExecutiveStrategy implements ISaveData {
    @Autowired
    private SeniorExecutiveRepository seniorExecutiveRepository;
    @Override
    public boolean savaData(JSONObject jsonObject,String name) {
        JSONArray jsonArr = jsonObject.getJSONObject("result").getJSONArray("items");
        if (jsonArr != null || jsonArr.size() == 0) {
            List<SeniorExecutive> list = new ArrayList<>();
            jsonArr.forEach(obj -> {
                SeniorExecutive parseObject = JSONObject.parseObject(obj.toString(), SeniorExecutive.class);
                parseObject.setCreationTime(System.currentTimeMillis());
                parseObject.setCname(name);
                String id = getGeneratedId(obj);
                parseObject.setId(id);
                list.add(parseObject);
            });
            try {
                seniorExecutiveRepository.save(list);
            } catch (Exception e) {
                throw e;
            }
        }
        return true;
    }
}
