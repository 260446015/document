package com.huishu.openeyes.service.strategy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.openeyes.entity.open.Employment;
import com.huishu.openeyes.repository.EmploymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ydw
 * Created on 2018/5/18
 */
@Service
public class EmploymentsStrategy implements ISaveData {
    @Autowired
    private EmploymentRepository employmentRepository;
    @Override
    public boolean savaData(JSONObject jsonObject,String name) {
        JSONArray jsonArr = jsonObject.getJSONObject("result").getJSONArray("items");
        if (jsonArr != null || jsonArr.size() == 0) {
            List<Employment> list = new ArrayList<>();
            jsonArr.forEach(obj -> {
                Employment parseObject = JSONObject.parseObject(obj.toString(), Employment.class);
                parseObject.setCreationTime(System.currentTimeMillis());
                list.add(parseObject);
            });
            try {
                employmentRepository.save(list);
            } catch (Exception e) {
                throw e;
            }
        }
        return true;
    }
}
