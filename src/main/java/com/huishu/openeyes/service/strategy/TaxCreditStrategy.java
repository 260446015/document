package com.huishu.openeyes.service.strategy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.openeyes.entity.open.TaxCredit;
import com.huishu.openeyes.repository.TaxCreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ydw
 * Created on 2018/5/18
 */
@Service
public class TaxCreditStrategy implements ISaveData {
    @Autowired
    private TaxCreditRepository taxCreditRepository;
    @Override
    public boolean savaData(JSONObject jsonObject,String name) {
        JSONArray jsonArr = jsonObject.getJSONObject("result").getJSONArray("items");
        if (jsonArr != null || jsonArr.size() == 0) {
            List<TaxCredit> list = new ArrayList<>();
            jsonArr.forEach(obj -> {
                TaxCredit parseObject = JSONObject.parseObject(obj.toString(), TaxCredit.class);
                parseObject.setCreationTime(System.currentTimeMillis());
                String id = getGeneratedId(obj);
                parseObject.setId(id);
                list.add(parseObject);
            });
            try {
                taxCreditRepository.save(list);
            } catch (Exception e) {
                throw e;
            }
        }
        return true;
    }
}
