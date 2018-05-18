package com.huishu.openeyes.service.strategy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.openeyes.entity.open.RiskDetail;
import com.huishu.openeyes.repository.RiskDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ydw
 * Created on 2018/5/18
 */
@Service
public class RiskDetailStrategy implements ISaveData {
    @Autowired
    private RiskDetailRepository riskDetailRepository;
    @Override
    public boolean savaData(JSONObject jsonObject,String name) {
        JSONArray jsonArr = jsonObject.getJSONObject("result").getJSONArray("dataList");
//        String searchId = params.get("id");
        if (jsonArr != null || jsonArr.size() == 0) {
            List<RiskDetail> list = new ArrayList<>();
            jsonArr.forEach(obj -> {
                RiskDetail parseObject = JSONObject.parseObject(obj.toString(), RiskDetail.class);
                parseObject.setCreationTime(System.currentTimeMillis());
                parseObject.setCompanyName(name);
                String id = getGeneratedId(parseObject);
                parseObject.setId(id);
//                parseObject.setSearchId(Long.parseLong(searchId));
                list.add(parseObject);
            });
            try {
                riskDetailRepository.save(list);
            } catch (Exception e) {
                throw e;
            }
        }
        return true;
    }
}
