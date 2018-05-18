package com.huishu.openeyes.service.strategy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.openeyes.entity.open.RiskInfo;
import com.huishu.openeyes.repository.RiskInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ydw
 * Created on 2018/5/18
 */
@Service
public class RiskInfoStrategy implements ISaveData {
    @Autowired
    private RiskInfoRepository riskInfoRepository;
    @Override
    public boolean savaData(JSONObject jsonObject,String name) {
        JSONArray internalList = jsonObject.getJSONObject("result").getJSONArray("internalList");
        List<RiskInfo> list = new ArrayList<>();
        if (internalList != null) {
            internalList.forEach(obj -> {
                RiskInfo parseObject = JSONObject.parseObject(obj.toString(), RiskInfo.class);
                parseObject.setCreationTime(System.currentTimeMillis());
                parseObject.setRiskType("internalList");
                parseObject.setCname(name);
                list.add(parseObject);
            });
        }
        JSONArray externalList = jsonObject.getJSONObject("result").getJSONArray("externalList");
        if (externalList != null) {
            externalList.forEach(obj -> {
                RiskInfo parseObject = JSONObject.parseObject(obj.toString(), RiskInfo.class);
                parseObject.setCreationTime(System.currentTimeMillis());
                parseObject.setRiskType("externalList");
                parseObject.setCname(name);
                list.add(parseObject);
            });
        }
        if (list.size() > 0) {
            try {
                riskInfoRepository.save(list);
            } catch (Exception e) {
                throw e;
            }
        }
        return true;
    }
}
