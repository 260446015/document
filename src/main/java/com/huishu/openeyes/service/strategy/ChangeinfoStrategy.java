package com.huishu.openeyes.service.strategy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.openeyes.entity.open.ChangeInfo;
import com.huishu.openeyes.repository.ChangeInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ydw
 * Created on 2018/5/18
 */
@Service
public class ChangeinfoStrategy implements ISaveData{
    @Autowired
    private ChangeInfoRepository changeInfoRepository;
    @Override
    public boolean savaData(JSONObject jsonObject,String name) {
        JSONArray jsonArr = jsonObject.getJSONObject("result").getJSONArray("items");
        if (jsonArr != null || jsonArr.size() == 0) {
            List<ChangeInfo> list = new ArrayList<>();
            jsonArr.forEach(obj -> {
                ChangeInfo parseObject = JSONObject.parseObject(obj.toString(), ChangeInfo.class);
                parseObject.setCreationTime(System.currentTimeMillis());
                String id = getGeneratedId(obj);
                parseObject.setId(id);
                parseObject.setCname(name);
                list.add(parseObject);
            });
            try {
                changeInfoRepository.save(list);
            } catch (Exception e) {
                throw e;
            }
        }
        return true;
    }
}
