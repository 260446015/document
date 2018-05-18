package com.huishu.openeyes.service.strategy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.openeyes.entity.open.Icp;
import com.huishu.openeyes.repository.IcpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ydw
 * Created on 2018/5/18
 */
@Service
public class IcpStrategy implements ISaveData {
    @Autowired
    private IcpRepository icpRepository;
    @Override
    public boolean savaData(JSONObject jsonObject,String name) {
        JSONArray jsonArray = jsonObject.getJSONObject("result").getJSONArray("items");
        if (jsonArray != null || jsonArray.size() == 0) {
            List<Icp> list = new ArrayList<>();
            jsonArray.forEach(obj -> {
                Icp parseObject = JSONObject.parseObject(obj.toString(), Icp.class);
                parseObject.setCreationTime(System.currentTimeMillis());
                String id = getGeneratedId(parseObject);
                parseObject.setId(id);
                list.add(parseObject);
            });
            try {
                icpRepository.save(list);
            } catch (Exception e) {
                throw e;
            }
        }
        return true;
    }
}
