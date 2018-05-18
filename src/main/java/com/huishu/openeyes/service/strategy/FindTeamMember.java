package com.huishu.openeyes.service.strategy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.openeyes.entity.open.TeamMember;
import com.huishu.openeyes.repository.TeamMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ydw
 * Created on 2018/5/18
 */
@Service
public class FindTeamMember implements ISaveData {
    @Autowired
    private TeamMemberRepository teamMemberRepository;
    @Override
    public boolean savaData(JSONObject jsonObject,String name) {
        JSONArray jsonArray = jsonObject.getJSONObject("result").getJSONArray("items");
        if (jsonArray != null || jsonArray.size() == 0) {
            List<TeamMember> list = new ArrayList<>();
            jsonArray.forEach(obj -> {
                TeamMember parseObject = JSONObject.parseObject(obj.toString(), TeamMember.class);
                parseObject.setCreationTime(System.currentTimeMillis());
                list.add(parseObject);
            });
            try {
                teamMemberRepository.save(list);
            } catch (Exception e) {
                throw e;
            }
        }
        return true;
    }
}
