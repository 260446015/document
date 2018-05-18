package com.huishu.openeyes.service.strategy;

import com.alibaba.fastjson.JSONObject;
import com.huishu.openeyes.entity.open.BaseInfo;
import com.huishu.openeyes.repository.BaseInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ydw
 * Created on 2018/5/17
 */
@Service
public class BaseInfoStrategy implements ISaveData {

    @Autowired
    private BaseInfoRepository baseInfoRepository;

    @Override
    public boolean savaData(JSONObject jsonObject,String name) {
        JSONObject jsonObj = jsonObject.getJSONObject("result");
        BaseInfo info = new BaseInfo();
        info.setActualCapital(jsonObj.getString("actualCapital"));
        info.setApprovedTime(jsonObj.getLong("approvedTime"));
        info.setBase(jsonObj.getString("base"));
        info.setBusinessScope(jsonObj.getString("businessScope"));
        info.setCategoryScore(jsonObj.getInteger("categoryScore"));
        info.setCompanyId(jsonObj.getLong("companyId"));
        info.setCompanyOrgType(jsonObj.getString("companyOrgType"));
        info.setCorrectCompanyId(jsonObj.getString("correctCompanyId"));
        info.setCreationTime(jsonObj.getLong("creationTime"));
        info.setCreditCode(jsonObj.getString("creditCode"));
        info.setEstiblishTime(jsonObj.getLong("estiblishTime"));
        info.setFromTime(jsonObj.getLong("fromTime"));
        info.setId(jsonObj.getLong("id"));
        info.setIndustry(jsonObj.getString("industry"));
        info.setLegalPersonId(jsonObj.getLong("legalPersonId"));
        info.setLegalPersonName(jsonObj.getString("legalPersonName"));
        info.setName(jsonObj.getString("name"));
        info.setOrgApprovedInstitute(jsonObj.getString("orgApprovedInstitute"));
        info.setOrgNumber(jsonObj.getString("orgNumber"));
        info.setPercentileScore(jsonObj.getInteger("percentileScore"));
        info.setPhoneNumber(jsonObj.getString("phoneNumber"));
        info.setRegCapital(jsonObj.getString("regCapital"));
        info.setRegInstitute(jsonObj.getString("regInstitute"));
        info.setRegLocation(jsonObj.getString("regLocation"));
        info.setRegNumber(jsonObj.getString("regNumber"));
        info.setRegStatus(jsonObj.getString("regStatus"));
        info.setToTime(jsonObj.getLong("toTime"));
        info.setType(jsonObj.getInteger("type"));
        info.setUpdatetime(jsonObj.getLong("updatetime"));
        info.setEmail(jsonObj.getString("email"));
        info.setHistoryNames(jsonObj.getString("historyNames"));
        info.setBondNum(jsonObj.getString("bondNum"));
        info.setBondName(jsonObj.getString("bondName"));
        info.setProperty3(jsonObj.getString("property3"));
        info.setUsedBondName(jsonObj.getString("usedBondName"));
        info.setSourceFlag(jsonObj.getString("sourceFlag"));
        info.setTags(jsonObj.getString("tags"));
        info.setBondType(jsonObj.getString("bondType"));
        info.setUpdateTimes(jsonObj.getLong("updateTimes"));
        info.setWebsiteList(jsonObj.getString("websiteList"));
        info.setCreationTime(System.currentTimeMillis());

        try {
            baseInfoRepository.save(info);
        } catch (Exception e) {
            throw e;
        }
        return true;
    }
}
