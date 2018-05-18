package com.huishu.openeyes.service.strategy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.openeyes.entity.open.PunishmentInfo;
import com.huishu.openeyes.repository.PunishmentInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ydw
 * Created on 2018/5/18
 */
@Service
public class PunishmentInfoStrategy implements ISaveData {
    @Autowired
    private PunishmentInfoRepository punishmentInfoRepository;
    @Override
    public boolean savaData(JSONObject jsonObject,String name) {
        JSONArray jsonArr = jsonObject.getJSONObject("result").getJSONArray("items");
        if (jsonArr != null || jsonArr.size() == 0) {
            List<PunishmentInfo> list = new ArrayList<>();
            jsonArr.forEach(obj -> {
                PunishmentInfo parseObject = JSONObject.parseObject(obj.toString(), PunishmentInfo.class);
                parseObject.setCreationTime(System.currentTimeMillis());
                String id = getGeneratedId(parseObject);
                parseObject.setId(id);
                list.add(parseObject);
            });
            try {
                punishmentInfoRepository.save(list);
            } catch (Exception e) {
                throw e;
            }
        }
        return true;
    }
}
