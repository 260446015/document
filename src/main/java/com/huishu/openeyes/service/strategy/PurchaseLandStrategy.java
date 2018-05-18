package com.huishu.openeyes.service.strategy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.openeyes.entity.open.Purchaseland;
import com.huishu.openeyes.repository.PurchaselandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ydw
 * Created on 2018/5/18
 */
@Service
public class PurchaseLandStrategy implements ISaveData {
    @Autowired
    private PurchaselandRepository purchaselandRepository;
    @Override
    public boolean savaData(JSONObject jsonObject,String name) {
        JSONArray jsonArr = jsonObject.getJSONObject("result").getJSONArray("items");
        if (jsonArr != null || jsonArr.size() == 0) {
            List<Purchaseland> list = new ArrayList<>();
            jsonArr.forEach(obj -> {
                JSONObject parseObject = JSONObject.parseObject(obj.toString());
                Purchaseland pl = new Purchaseland();
                pl.setCreationTime(System.currentTimeMillis());
                pl.setAdminRegion(parseObject.getString("adminRegion"));
                pl.setAssignee(parseObject.getString("assignee"));
                pl.setCname(name);
                pl.setCreateTime(parseObject.getLong("createTime"));
                pl.setDealPrice(parseObject.getString("dealPrice"));
                pl.setElecSupervisorNo(parseObject.getString("elecSupervisorNo"));
                pl.setEndTime(parseObject.getLong("endTime"));
                pl.setId(parseObject.getLong("id"));
                pl.setLinkUrl(parseObject.getString("linkUrl"));
                pl.setLocation(parseObject.getString("location"));
                pl.setMaxVolume(parseObject.getString("maxVolume"));
                pl.setMinVolume(parseObject.getString("minVolume"));
                pl.setParentCompany(parseObject.getString("parentCompany"));
                pl.setPurpose(parseObject.getString("purpose"));
                pl.setSignedDate(parseObject.getLong("signedDate"));
                pl.setStartTime(parseObject.getLong("startTime"));
                pl.setSupplyWay(parseObject.getString("supplyWay"));
                pl.setTotalArea(parseObject.getString("totalArea"));
                pl.setUpdateTime(parseObject.getLong("updateTime"));
                list.add(pl);
            });
            try {
                purchaselandRepository.save(list);
            } catch (Exception e) {
                throw e;
            }
        }
        return true;
    }
}
