package com.huishu.openeyes.service.strategy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.openeyes.entity.open.Holder;
import com.huishu.openeyes.repository.HolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ydw
 * Created on 2018/5/18
 */
@Service
public class HolderStrategy implements ISaveData {
    @Autowired
    private HolderRepository holderRepository;
    @Override
    public boolean savaData(JSONObject jsonObject,String name) {
        JSONArray jsonArr = jsonObject.getJSONObject("result").getJSONArray("items");
        if (jsonArr != null || jsonArr.size() == 0) {
            List<Holder> list = new ArrayList<>();
            jsonArr.forEach(obj -> {
                JSONObject target = JSONObject.parseObject(obj.toString());
                Holder holder = new Holder();
                holder.setCreationTime(System.currentTimeMillis());
                holder.setAmount(target.getDouble("amount"));
                holder.setId(target.getLong("id"));
                holder.setLogo(target.getString("logo"));
                holder.setName(target.getString("name"));
                holder.setToco(target.getIntValue("toco"));
                holder.setType(target.getIntValue("type"));
                holder.setCapital(target.getString("capital"));
                holder.setCname(name);
                list.add(holder);
            });
            try {
                holderRepository.save(list);
            } catch (Exception e) {
                throw e;
            }
        }
        return true;
    }
}
