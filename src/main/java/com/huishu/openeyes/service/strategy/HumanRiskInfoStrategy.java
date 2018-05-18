package com.huishu.openeyes.service.strategy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.openeyes.entity.open.HumanRiskInfo;
import com.huishu.openeyes.repository.HumanRiskInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ydw
 * Created on 2018/5/18
 */
@Service
public class HumanRiskInfoStrategy implements ISaveData {
    @Autowired
    private HumanRiskInfoRepository humanRiskInfoRepository;
    @Override
    public boolean savaData(JSONObject jsonObject,String name) {
        JSONArray jsonArr = jsonObject.getJSONObject("result").getJSONArray("externalList");
        if (jsonArr != null || jsonArr.size() == 0) {
            List<HumanRiskInfo> list = new ArrayList<>();
            jsonArr.forEach(obj -> {
                HumanRiskInfo parseObject = JSONObject.parseObject(obj.toString(), HumanRiskInfo.class);
                parseObject.setCreationTime(System.currentTimeMillis());
                parseObject.setHumanName(name);
                list.add(parseObject);
            });
            try {
                humanRiskInfoRepository.save(list);
            } catch (Exception e) {
                throw e;
            }
        }
        return true;
    }
}
