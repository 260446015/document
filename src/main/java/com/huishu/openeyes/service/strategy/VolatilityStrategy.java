package com.huishu.openeyes.service.strategy;

import com.alibaba.fastjson.JSONObject;
import com.huishu.openeyes.entity.open.Volatility;
import com.huishu.openeyes.repository.VolatilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ydw
 * Created on 2018/5/18
 */
@Service
public class VolatilityStrategy implements ISaveData {
    @Autowired
    private VolatilityRepository volatilityRepository;
    @Override
    public boolean savaData(JSONObject jsonObject,String name) {
        JSONObject jsonArr = jsonObject.getJSONObject("result");
        if (jsonArr != null || jsonArr.size() == 0) {
            Volatility parseObject = JSONObject.parseObject(jsonArr.toString(), Volatility.class);
            parseObject.setCreationTime(System.currentTimeMillis());
            parseObject.setCname(name);
            String id = getGeneratedId(jsonArr);
            parseObject.setId(id);
            try {
                volatilityRepository.save(parseObject);
            } catch (Exception e) {
                throw e;
            }
        }
        return true;
    }
}
