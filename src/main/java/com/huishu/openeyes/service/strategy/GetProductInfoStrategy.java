package com.huishu.openeyes.service.strategy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.openeyes.entity.open.ProductInfo;
import com.huishu.openeyes.repository.ProductInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ydw
 * Created on 2018/5/18
 */
@Service
public class GetProductInfoStrategy implements ISaveData {
    @Autowired
    private ProductInfoRepository productInfoRepository;
    @Override
    public boolean savaData(JSONObject jsonObject,String name) {
        JSONArray jsonArray = jsonObject.getJSONObject("result").getJSONArray("items");
        if (jsonArray != null || jsonArray.size() == 0) {
            List<ProductInfo> list = new ArrayList<>();
            jsonArray.forEach(obj -> {
                ProductInfo parseObject = JSONObject.parseObject(obj.toString(), ProductInfo.class);
                parseObject.setCreationTime(System.currentTimeMillis());
                list.add(parseObject);
            });
            try {
                productInfoRepository.save(list);
            } catch (Exception e) {
                throw e;
            }
        }
        return true;
    }
}
