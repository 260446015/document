package com.huishu.openeyes.service.strategy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.openeyes.entity.open.Staff;
import com.huishu.openeyes.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ydw
 * Created on 2018/5/17
 */
@Service
public class StaffStrategy implements ISaveData{
    @Autowired
    private StaffRepository staffRepository;
    @Override
    public boolean savaData(JSONObject jsonObject,String name) {
        JSONArray jsonArray = jsonObject.getJSONObject("result").getJSONArray("items");
        if (jsonArray != null) {
            List<Staff> list = new ArrayList<>();
            jsonArray.forEach((obj) -> {
                Staff parseObject = JSONObject.parseObject(obj.toString(), Staff.class);
                parseObject.setCname(name);
                parseObject.setCreationTime(System.currentTimeMillis());
                list.add(parseObject);
            });
            try {
                staffRepository.save(list);
            } catch (Exception e) {
                throw e;
            }
        }
        return true;
    }
}
