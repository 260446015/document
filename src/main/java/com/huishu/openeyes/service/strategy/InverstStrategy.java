package com.huishu.openeyes.service.strategy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.openeyes.entity.open.Inverst;
import com.huishu.openeyes.repository.InverstRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ydw
 * Created on 2018/5/18
 */
@Service
public class InverstStrategy implements ISaveData {
    @Autowired
    private InverstRepository inverstRepository;
    @Override
    public boolean savaData(JSONObject jsonObject,String name) {
        JSONArray jsonArr = jsonObject.getJSONObject("result").getJSONArray("items");
        if (jsonArr != null || jsonArr.size() == 0) {
            List<Inverst> list = new ArrayList<>();
            jsonArr.forEach(obj -> {
                Inverst parseObject = JSONObject.parseObject(obj.toString(), Inverst.class);
                parseObject.setCreationTime(System.currentTimeMillis());
                parseObject.setCname(name);
                list.add(parseObject);
            });
            try {
                inverstRepository.save(list);
            } catch (Exception e) {
                throw e;
            }
        }
        return true;
    }
}
