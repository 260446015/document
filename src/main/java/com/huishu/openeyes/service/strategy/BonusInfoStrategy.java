package com.huishu.openeyes.service.strategy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.openeyes.entity.open.BonusInfo;
import com.huishu.openeyes.repository.BonusInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ydw
 * Created on 2018/5/18
 */
@Service
public class BonusInfoStrategy implements ISaveData {
    @Autowired
    private BonusInfoRepository bonusInfoRepository;
    @Override
    public boolean savaData(JSONObject jsonObject,String name) {
        JSONArray jsonArr = jsonObject.getJSONObject("result").getJSONArray("items");
        if (jsonArr != null || jsonArr.size() == 0) {
            List<BonusInfo> list = new ArrayList<>();
            jsonArr.forEach(obj -> {
                BonusInfo parseObject = JSONObject.parseObject(obj.toString(), BonusInfo.class);
                parseObject.setCname(name);
                parseObject.setCreationTime(System.currentTimeMillis());
                String id = getGeneratedId(obj);
                parseObject.setId(id);
                list.add(parseObject);
            });
            try {
                bonusInfoRepository.save(list);
            } catch (Exception e) {
                throw e;
            }
        }
        return true;
    }
}
