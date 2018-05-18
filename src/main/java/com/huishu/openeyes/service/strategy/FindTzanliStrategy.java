package com.huishu.openeyes.service.strategy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.openeyes.entity.open.TouZi;
import com.huishu.openeyes.repository.TouZiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ydw
 * Created on 2018/5/18
 */
@Service
public class FindTzanliStrategy implements ISaveData {
    @Autowired
    private TouZiRepository touZiRepository;
    @Override
    public boolean savaData(JSONObject jsonObject,String name) {
        JSONArray jsonArray = jsonObject.getJSONObject("result").getJSONArray("items");
        if (jsonArray != null || jsonArray.size() == 0) {
            List<TouZi> list = new ArrayList<>();
            jsonArray.forEach(obj -> {
                TouZi parseObject = JSONObject.parseObject(obj.toString(), TouZi.class);
                parseObject.setCompanyName(name);
                parseObject.setCreationTime(System.currentTimeMillis());
                list.add(parseObject);
            });
            try {
                touZiRepository.save(list);
            } catch (Exception e) {
                throw e;
            }
        }
        return true;
    }
}
