package com.huishu.openeyes.service.strategy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.openeyes.entity.open.OwnTax;
import com.huishu.openeyes.repository.OwnTaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ydw
 * Created on 2018/5/18
 */
@Service
public class OwnTaxStrategy implements ISaveData {
    @Autowired
    private OwnTaxRepository ownTaxRepository;
    @Override
    public boolean savaData(JSONObject jsonObject,String name) {
        JSONArray jsonArr = jsonObject.getJSONObject("result").getJSONArray("items");
        if (jsonArr != null || jsonArr.size() == 0) {
            List<OwnTax> list = new ArrayList<>();
            jsonArr.forEach(obj -> {
                OwnTax parseObject = JSONObject.parseObject(obj.toString(), OwnTax.class);
                parseObject.setCreationTime(System.currentTimeMillis());
                String id = getGeneratedId(parseObject);
                parseObject.setId(id);
                list.add(parseObject);
            });
            try {
                ownTaxRepository.save(list);
            } catch (Exception e) {
                throw e;
            }
        }
        return true;
    }
}
