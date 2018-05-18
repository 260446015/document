package com.huishu.openeyes.service.strategy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.openeyes.entity.open.Bond;
import com.huishu.openeyes.repository.BondRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ydw
 * Created on 2018/5/18
 */
@Service
public class BondStrategy implements ISaveData {
    @Autowired
    private BondRepository bondRepository;
    @Override
    public boolean savaData(JSONObject jsonObject,String name) {
        JSONArray jsonArr = jsonObject.getJSONObject("result").getJSONArray("items");
        if (jsonArr != null || jsonArr.size() == 0) {
            List<Bond> list = new ArrayList<>();
            jsonArr.forEach(obj -> {
                Bond parseObject = JSONObject.parseObject(obj.toString(), Bond.class);
                parseObject.setCreationTime(System.currentTimeMillis());
                parseObject.setCname(name);
                list.add(parseObject);
            });
            try {
                bondRepository.save(list);
            } catch (Exception e) {
                throw e;
            }
        }
        return true;
    }
}
