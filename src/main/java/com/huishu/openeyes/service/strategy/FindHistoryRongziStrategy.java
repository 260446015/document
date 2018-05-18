package com.huishu.openeyes.service.strategy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.openeyes.entity.open.HistoryRongZi;
import com.huishu.openeyes.repository.HistoryRongZiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ydw
 * Created on 2018/5/18
 */
@Service
public class FindHistoryRongziStrategy implements ISaveData {

    @Autowired
    private HistoryRongZiRepository historyRongZiRepository;
    @Override
    public boolean savaData(JSONObject jsonObject,String name) {
        JSONArray jsonArray = jsonObject.getJSONObject("result").getJSONArray("items");
        if (jsonArray != null || jsonArray.size() == 0) {
            List<HistoryRongZi> list = new ArrayList<>();
            jsonArray.forEach(obj -> {
                HistoryRongZi parseObject = JSONObject.parseObject(obj.toString(), HistoryRongZi.class);
                String id = getGeneratedId(parseObject);
                parseObject.setCreationTime(System.currentTimeMillis());
                parseObject.setId(id);
                list.add(parseObject);
            });
            try {
                historyRongZiRepository.save(list);
            } catch (Exception e) {
                throw e;
            }
        }
        return true;
    }
}
