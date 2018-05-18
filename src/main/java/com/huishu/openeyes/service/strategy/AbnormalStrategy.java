package com.huishu.openeyes.service.strategy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.openeyes.entity.open.Abnormal;
import com.huishu.openeyes.repository.AbnormalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ydw
 * Created on 2018/5/18
 */
@Service
public class AbnormalStrategy implements ISaveData{
    @Autowired
    private AbnormalRepository abnormalRepository;
    @Override
    public boolean savaData(JSONObject jsonObject,String name) {
        JSONArray abnormal = jsonObject.getJSONObject("result").getJSONArray("items");
        if (abnormal != null) {
            List<Abnormal> list = new ArrayList<>();
            abnormal.forEach(obj -> {
                Abnormal parseObject = JSONObject.parseObject(obj.toString(), Abnormal.class);
                parseObject.setCreationTime(System.currentTimeMillis());
                String id = getGeneratedId(parseObject);
                parseObject.setId(id);
                parseObject.setCompanyName(name);
                list.add(parseObject);
            });
            try {
                abnormalRepository.save(list);
            } catch (Exception e) {
                throw e;
            }
        }
        return true;
    }
}
