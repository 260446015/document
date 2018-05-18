package com.huishu.openeyes.service.strategy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.openeyes.entity.open.CopyReg;
import com.huishu.openeyes.repository.CopyRegRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ydw
 * Created on 2018/5/18
 */
@Service
public class CopyRegStrategy implements ISaveData {
    @Autowired
    private CopyRegRepository copyRegRepository;
    @Override
    public boolean savaData(JSONObject jsonObject,String name) {
        JSONArray jsonArray = jsonObject.getJSONObject("result").getJSONArray("items");
        if (jsonArray != null || jsonArray.size() == 0) {
            List<CopyReg> list = new ArrayList<>();
            jsonArray.forEach(obj -> {
                CopyReg parseObject = JSONObject.parseObject(obj.toString(), CopyReg.class);
                parseObject.setCreationTime(System.currentTimeMillis());
                parseObject.setCompanyName(name);
                list.add(parseObject);
            });
            try {
                copyRegRepository.save(list);
            } catch (Exception e) {
                throw e;
            }
        }
        return true;
    }
}
