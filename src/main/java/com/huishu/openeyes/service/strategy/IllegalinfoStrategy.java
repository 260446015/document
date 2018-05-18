package com.huishu.openeyes.service.strategy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.openeyes.entity.open.Illegalinfo;
import com.huishu.openeyes.repository.IllegalinfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ydw
 * Created on 2018/5/18
 */
@Service
public class IllegalinfoStrategy implements ISaveData {
    @Autowired
    private IllegalinfoRepository illegalinfoRepository;
    @Override
    public boolean savaData(JSONObject jsonObject,String name) {
        JSONArray jsonArr = jsonObject.getJSONObject("result").getJSONArray("items");
        if (jsonArr != null || jsonArr.size() == 0) {
            List<Illegalinfo> list = new ArrayList<>();
            jsonArr.forEach(obj -> {
                Illegalinfo parseObject = JSONObject.parseObject(obj.toString(), Illegalinfo.class);
                parseObject.setCreationTime(System.currentTimeMillis());
                String id = getGeneratedId(parseObject);
                parseObject.setId(id);
                parseObject.setCompanyName(name);
                list.add(parseObject);
            });
            try {
                illegalinfoRepository.save(list);
            } catch (Exception e) {
                throw e;
            }
        }
        return true;
    }
}
