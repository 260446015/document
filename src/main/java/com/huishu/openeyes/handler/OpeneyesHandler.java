package com.huishu.openeyes.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.openeyes.annotations.OpenEnum;
import com.huishu.openeyes.common.Digests;
import com.huishu.openeyes.common.Encodes;
import com.huishu.openeyes.common.KeyConstan;
import com.huishu.openeyes.entity.open.Abnormal;
import com.huishu.openeyes.entity.open.Allotmen;
import com.huishu.openeyes.entity.open.Announcement;
import com.huishu.openeyes.entity.open.AppbkInfo;
import com.huishu.openeyes.entity.open.BaseInfo;
import com.huishu.openeyes.entity.open.Bids;
import com.huishu.openeyes.entity.open.Bond;
import com.huishu.openeyes.entity.open.BonusInfo;
import com.huishu.openeyes.entity.open.Branch;
import com.huishu.openeyes.entity.open.Certificate;
import com.huishu.openeyes.entity.open.ChangeInfo;
import com.huishu.openeyes.entity.open.CheckInfo;
import com.huishu.openeyes.entity.open.CompanyInfo;
import com.huishu.openeyes.entity.open.CopyReg;
import com.huishu.openeyes.entity.open.CourtAnnouncement;
import com.huishu.openeyes.entity.open.Dishonest;
import com.huishu.openeyes.entity.open.Employment;
import com.huishu.openeyes.entity.open.EquityChange;
import com.huishu.openeyes.entity.open.HistoryRongZi;
import com.huishu.openeyes.entity.open.Holder;
import com.huishu.openeyes.entity.open.HoldingCompany;
import com.huishu.openeyes.entity.open.HumanRiskInfo;
import com.huishu.openeyes.entity.open.Icp;
import com.huishu.openeyes.entity.open.Illegalinfo;
import com.huishu.openeyes.entity.open.Inverst;
import com.huishu.openeyes.entity.open.IssueRelated;
import com.huishu.openeyes.entity.open.JingPin;
import com.huishu.openeyes.entity.open.LawSuit;
import com.huishu.openeyes.entity.open.News;
import com.huishu.openeyes.entity.open.OwnTax;
import com.huishu.openeyes.entity.open.Patents;
import com.huishu.openeyes.entity.open.ProductInfo;
import com.huishu.openeyes.entity.open.PunishmentInfo;
import com.huishu.openeyes.entity.open.Purchaseland;
import com.huishu.openeyes.entity.open.RiskDetail;
import com.huishu.openeyes.entity.open.RiskInfo;
import com.huishu.openeyes.entity.open.SearchCount;
import com.huishu.openeyes.entity.open.SeniorExecutive;
import com.huishu.openeyes.entity.open.ShangBiao;
import com.huishu.openeyes.entity.open.ShareHolder;
import com.huishu.openeyes.entity.open.ShareStructure;
import com.huishu.openeyes.entity.open.Staff;
import com.huishu.openeyes.entity.open.TaxCredit;
import com.huishu.openeyes.entity.open.TeamMember;
import com.huishu.openeyes.entity.open.TouZi;
import com.huishu.openeyes.entity.open.Volatility;
import com.huishu.openeyes.entity.open.ZhixingInfo;
import com.huishu.openeyes.repository.AbnormalRepository;
import com.huishu.openeyes.repository.AllotmenRepository;
import com.huishu.openeyes.repository.AnnouncementRepository;
import com.huishu.openeyes.repository.AppbkInfoRepository;
import com.huishu.openeyes.repository.BaseInfoRepository;
import com.huishu.openeyes.repository.BidsRepository;
import com.huishu.openeyes.repository.BondRepository;
import com.huishu.openeyes.repository.BonusInfoRepository;
import com.huishu.openeyes.repository.BranchRepository;
import com.huishu.openeyes.repository.CertificateRepository;
import com.huishu.openeyes.repository.ChangeInfoRepository;
import com.huishu.openeyes.repository.CheckInfoRepository;
import com.huishu.openeyes.repository.CompanyInfoRepository;
import com.huishu.openeyes.repository.CopyRegRepository;
import com.huishu.openeyes.repository.CourtAnnouncementRepository;
import com.huishu.openeyes.repository.DishonestRepository;
import com.huishu.openeyes.repository.EmploymentRepository;
import com.huishu.openeyes.repository.EquityChangeRepository;
import com.huishu.openeyes.repository.HistoryRongZiRepository;
import com.huishu.openeyes.repository.HolderRepository;
import com.huishu.openeyes.repository.HoldingCompanyRepository;
import com.huishu.openeyes.repository.HumanRiskInfoRepository;
import com.huishu.openeyes.repository.IcpRepository;
import com.huishu.openeyes.repository.IllegalinfoRepository;
import com.huishu.openeyes.repository.InverstRepository;
import com.huishu.openeyes.repository.IssueRelatedRepository;
import com.huishu.openeyes.repository.JingPinRepository;
import com.huishu.openeyes.repository.LawSuitRepository;
import com.huishu.openeyes.repository.NewsRepository;
import com.huishu.openeyes.repository.OwnTaxRepository;
import com.huishu.openeyes.repository.PatentsRepository;
import com.huishu.openeyes.repository.ProductInfoRepository;
import com.huishu.openeyes.repository.PunishmentInfoRepository;
import com.huishu.openeyes.repository.PurchaselandRepository;
import com.huishu.openeyes.repository.RiskDetailRepository;
import com.huishu.openeyes.repository.RiskInfoRepository;
import com.huishu.openeyes.repository.SearchCountRepository;
import com.huishu.openeyes.repository.SeniorExecutiveRepository;
import com.huishu.openeyes.repository.ShangBiaoRepository;
import com.huishu.openeyes.repository.ShareHolderRepository;
import com.huishu.openeyes.repository.ShareStructureRepository;
import com.huishu.openeyes.repository.StaffRepository;
import com.huishu.openeyes.repository.TaxCreditRepository;
import com.huishu.openeyes.repository.TeamMemberRepository;
import com.huishu.openeyes.repository.TouZiRepository;
import com.huishu.openeyes.repository.VolatilityRepository;
import com.huishu.openeyes.repository.ZhixingInfoRepository;
import com.huishu.openeyes.service.OpeneyesService;

public class OpeneyesHandler implements InvocationHandler {

    private OpeneyesService openeyesService;
    private static Logger log = LoggerFactory.getLogger(OpeneyesHandler.class);

    public OpeneyesHandler(OpeneyesService openeyesService) {
        this.openeyesService = openeyesService;
    }

    private ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Map<String, String> params = (Map<String, String>) args[0];
        try {
            JSONObject localData = searchFromLocal(params);
            boolean checkLocal = checkMessage(localData, params);
            if (checkLocal) {
                return localData;
            }
        } catch (Exception e) {
            log.error("查询本地数据库出错，调用参数为" + params.toString(), e.getMessage());
        }
        args[1] = threadLocal.get().get("url");
        JSONObject openData = new JSONObject();
        try {
            openData = (JSONObject) method.invoke(openeyesService, args);
        } catch (Exception e) {
            log.error("调用天眼查接口数据出错，调用参数为" + args, e.getMessage());
        }
        boolean checkOpen = checkMessage(openData, params);
        if (checkOpen) {
            try {
                saveData(openData, params);
            } catch (Exception e) {
                log.error("存储天眼查信息出错，存储的数据为:" + openData + "。存储参数params为:" + params, e.getMessage());
            }
            return openData;
        } else {
            finishData(openData);
        }
        return openData;

    }

    private void saveData(JSONObject openEyesTarget, Map<String, String> params) {
        String method = params.get("method");
        String name = params.get("name");
        log.info("保存数据开始---调用接口:" + method + "---保存公司名称:" + name);
        try {
            if ("baseInfo".equalsIgnoreCase(method)) {
                JSONObject jsonObj = openEyesTarget.getJSONObject("result");
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
                baseInfoRepository.save(info);
            } else if ("staff".equalsIgnoreCase(method)) {
                JSONArray jsonArray = openEyesTarget.getJSONObject("data").getJSONArray("result");
                if (jsonArray != null) {
                    List<Staff> list = new ArrayList<>();
                    jsonArray.forEach((obj) -> {
                        Staff parseObject = JSONObject.parseObject(obj.toString(), Staff.class);
                        parseObject.setCname(name);
                        parseObject.setCreationTime(System.currentTimeMillis());
                        list.add(parseObject);
                    });
                    staffRepository.save(list);
                }
            } else if ("branch".equalsIgnoreCase(method)) {
                JSONArray jsonArray = openEyesTarget.getJSONObject("data").getJSONArray("result");
                if (jsonArray != null) {
                    List<Branch> list = new ArrayList<>();
                    jsonArray.forEach(obj -> {
                        Branch parseObject = JSONObject.parseObject(obj.toString(), Branch.class);
                        parseObject.setCompanyName(name);
                        parseObject.setCreationTime(System.currentTimeMillis());
                        list.add(parseObject);
                    });
                    branchRepository.save(list);
                }
            } else if ("HistoryRongZi".equalsIgnoreCase(method)) {
                JSONArray jsonArray = openEyesTarget.getJSONObject("result").getJSONObject("page").getJSONArray("rows");
                if (jsonArray != null || jsonArray.size() == 0) {
                    List<HistoryRongZi> list = new ArrayList<>();
                    jsonArray.forEach(obj -> {
                        HistoryRongZi parseObject = JSONObject.parseObject(obj.toString(), HistoryRongZi.class);
                        String id = getGeneratedId(parseObject);
                        parseObject.setCreationTime(System.currentTimeMillis());
                        parseObject.setId(id);
                        list.add(parseObject);
                    });
                    historyRongZiRepository.save(list);
                }
            } else if ("TeamMember".equalsIgnoreCase(method)) {
                JSONArray jsonArray = openEyesTarget.getJSONObject("result").getJSONObject("page").getJSONArray("rows");
                if (jsonArray != null || jsonArray.size() == 0) {
                    List<TeamMember> list = new ArrayList<>();
                    jsonArray.forEach(obj -> {
                        TeamMember parseObject = JSONObject.parseObject(obj.toString(), TeamMember.class);
                        parseObject.setCreationTime(System.currentTimeMillis());
                        list.add(parseObject);
                    });
                    teamMemberRepository.save(list);
                }
            } else if ("ProductInfo".equalsIgnoreCase(method)) {
                JSONArray jsonArray = openEyesTarget.getJSONObject("result").getJSONObject("page").getJSONArray("rows");
                if (jsonArray != null || jsonArray.size() == 0) {
                    List<ProductInfo> list = new ArrayList<>();
                    jsonArray.forEach(obj -> {
                        ProductInfo parseObject = JSONObject.parseObject(obj.toString(), ProductInfo.class);
                        parseObject.setCreationTime(System.currentTimeMillis());
                        list.add(parseObject);
                    });
                    productInfoRepository.save(list);
                }
            } else if ("TouZi".equalsIgnoreCase(method)) {
                JSONArray jsonArray = openEyesTarget.getJSONObject("result").getJSONObject("page").getJSONArray("rows");
                if (jsonArray != null || jsonArray.size() == 0) {
                    List<TouZi> list = new ArrayList<>();
                    jsonArray.forEach(obj -> {
                        TouZi parseObject = JSONObject.parseObject(obj.toString(), TouZi.class);
                        parseObject.setCompanyName(name);
                        parseObject.setCreationTime(System.currentTimeMillis());
                        list.add(parseObject);
                    });
                    touZiRepository.save(list);
                }
            } else if ("JingPin".equalsIgnoreCase(method)) {
                JSONArray jsonArray = openEyesTarget.getJSONObject("result").getJSONObject("page").getJSONArray("rows");
                if (jsonArray != null || jsonArray.size() == 0) {
                    List<JingPin> list = new ArrayList<>();
                    jsonArray.forEach(obj -> {
                        JingPin parseObject = JSONObject.parseObject(obj.toString(), JingPin.class);
                        String id = getGeneratedId(parseObject);
                        parseObject.setId(id);
                        parseObject.setCname(name);
                        parseObject.setCreationTime(System.currentTimeMillis());
                        list.add(parseObject);
                    });
                    jingPinRepository.save(list);
                }
            } else if ("ShangBiao".equalsIgnoreCase(method)) {
                JSONArray jsonArray = openEyesTarget.getJSONObject("data").getJSONArray("items");
                if (jsonArray != null || jsonArray.size() == 0) {
                    List<ShangBiao> list = new ArrayList<>();
                    jsonArray.forEach(obj -> {
                        ShangBiao parseObject = JSONObject.parseObject(obj.toString(), ShangBiao.class);
                        parseObject.setCreationTime(System.currentTimeMillis());
                        list.add(parseObject);
                    });
                    shangBiaoRepository.save(list);
                }
            } else if ("Patents".equalsIgnoreCase(method)) {
                JSONArray jsonArray = openEyesTarget.getJSONObject("data").getJSONArray("items");
                if (jsonArray != null || jsonArray.size() == 0) {
                    List<Patents> list = new ArrayList<>();
                    jsonArray.forEach(obj -> {
                        Patents parseObject = JSONObject.parseObject(obj.toString(), Patents.class);
                        parseObject.setCreationTime(System.currentTimeMillis());
                        list.add(parseObject);
                    });
                    patentsRepository.save(list);
                }
            } else if ("CopyReg".equalsIgnoreCase(method)) {
                JSONArray jsonArray = openEyesTarget.getJSONObject("data").getJSONArray("items");
                if (jsonArray != null || jsonArray.size() == 0) {
                    List<CopyReg> list = new ArrayList<>();
                    jsonArray.forEach(obj -> {
                        CopyReg parseObject = JSONObject.parseObject(obj.toString(), CopyReg.class);
                        parseObject.setCreationTime(System.currentTimeMillis());
                        parseObject.setCompanyName(name);
                        list.add(parseObject);
                    });
                    copyRegRepository.save(list);
                }
            } else if ("Icp".equalsIgnoreCase(method)) {
                JSONArray jsonArray = openEyesTarget.getJSONArray("data");
                if (jsonArray != null || jsonArray.size() == 0) {
                    List<Icp> list = new ArrayList<>();
                    jsonArray.forEach(obj -> {
                        Icp parseObject = JSONObject.parseObject(obj.toString(), Icp.class);
                        parseObject.setCreationTime(System.currentTimeMillis());
                        String id = getGeneratedId(parseObject);
                        parseObject.setId(id);
                        list.add(parseObject);
                    });
                    icpRepository.save(list);
                }
            } else if ("Abnormal".equalsIgnoreCase(method)) {
                JSONArray abnormal = openEyesTarget.getJSONObject("data").getJSONArray("result");
                if (abnormal != null) {
                    List<Abnormal> list = new ArrayList<>();
                    abnormal.forEach(obj -> {
                        Abnormal parseObject = JSONObject.parseObject(obj.toString(), Abnormal.class);
                        parseObject.setCreationTime(System.currentTimeMillis());
                        String id = getGeneratedId(parseObject);
                        parseObject.setId(id);
                        parseObject.setCompanyName(name);
                        list.add(parseObject);
                    });
                    abnormalRepository.save(list);
                }
            } else if ("PunishmentInfo".equalsIgnoreCase(method)) {
                JSONArray jsonArr = openEyesTarget.getJSONObject("data").getJSONArray("items");
                if (jsonArr != null || jsonArr.size() == 0) {
                    List<PunishmentInfo> list = new ArrayList<>();
                    jsonArr.forEach(obj -> {
                        PunishmentInfo parseObject = JSONObject.parseObject(obj.toString(), PunishmentInfo.class);
                        parseObject.setCreationTime(System.currentTimeMillis());
                        String id = getGeneratedId(parseObject);
                        parseObject.setId(id);
                        list.add(parseObject);
                    });
                    punishmentInfoRepository.save(list);
                }
            } else if ("Illegalinfo".equalsIgnoreCase(method)) {
                JSONArray jsonArr = openEyesTarget.getJSONObject("data").getJSONArray("items");
                if (jsonArr != null || jsonArr.size() == 0) {
                    List<Illegalinfo> list = new ArrayList<>();
                    jsonArr.forEach(obj -> {
                        Illegalinfo parseObject = JSONObject.parseObject(obj.toString(), Illegalinfo.class);
                        parseObject.setCreationTime(System.currentTimeMillis());
                        String id = getGeneratedId(parseObject);
                        parseObject.setId(id);
                        parseObject.setCompanyName(name);
                        list.add(parseObject);
                    });
                    illegalinfoRepository.save(list);
                }
            } else if ("OwnTax".equalsIgnoreCase(method)) {
                JSONArray jsonArr = openEyesTarget.getJSONObject("data").getJSONArray("items");
                if (jsonArr != null || jsonArr.size() == 0) {
                    List<OwnTax> list = new ArrayList<>();
                    jsonArr.forEach(obj -> {
                        OwnTax parseObject = JSONObject.parseObject(obj.toString(), OwnTax.class);
                        parseObject.setCreationTime(System.currentTimeMillis());
                        String id = getGeneratedId(parseObject);
                        parseObject.setId(id);
                        list.add(parseObject);
                    });
                    ownTaxRepository.save(list);
                }
            } else if ("News".equalsIgnoreCase(method)) {
                JSONArray jsonArr = openEyesTarget.getJSONArray("result");
                if (jsonArr != null || jsonArr.size() == 0) {
                    List<News> list = new ArrayList<>();
                    jsonArr.forEach(obj -> {
                        News parseObject = JSONObject.parseObject(obj.toString(), News.class);
                        parseObject.setCreationTime(System.currentTimeMillis());
                        String id = getGeneratedId(parseObject);
                        parseObject.setId(id);
                        parseObject.setCompanyName(name);
                        list.add(parseObject);
                    });
                    newsRepository.save(list);
                }
            } else if ("Dishonest".equalsIgnoreCase(method)) {
                JSONArray jsonArr = openEyesTarget.getJSONObject("data").getJSONArray("items");
                if (jsonArr != null || jsonArr.size() == 0) {
                    List<Dishonest> list = new ArrayList<>();
                    jsonArr.forEach(obj -> {
                        Dishonest parseObject = JSONObject.parseObject(obj.toString(), Dishonest.class);
                        parseObject.setCreationTime(System.currentTimeMillis());
                        list.add(parseObject);
                    });
                    dishonestRepository.save(list);
                }
            } else if ("RiskInfo".equalsIgnoreCase(method)) {
                JSONArray internalList = openEyesTarget.getJSONObject("data").getJSONArray("internalList");
                List<RiskInfo> list = new ArrayList<>();
                if (internalList != null) {
                    internalList.forEach(obj -> {
                        RiskInfo parseObject = JSONObject.parseObject(obj.toString(), RiskInfo.class);
                        parseObject.setCreationTime(System.currentTimeMillis());
                        parseObject.setRiskType("internalList");
                        parseObject.setCname(name);
                        list.add(parseObject);
                    });
                }
                JSONArray externalList = openEyesTarget.getJSONObject("data").getJSONArray("externalList");
                if (externalList != null) {
                    externalList.forEach(obj -> {
                        RiskInfo parseObject = JSONObject.parseObject(obj.toString(), RiskInfo.class);
                        parseObject.setCreationTime(System.currentTimeMillis());
                        parseObject.setRiskType("externalList");
                        parseObject.setCname(name);
                        list.add(parseObject);
                    });
                }
                if (list.size() > 0) {
                    riskInfoRepository.save(list);
                }
            } else if ("HumanRiskInfo".equalsIgnoreCase(method)) {
                JSONArray jsonArr = openEyesTarget.getJSONObject("data").getJSONArray("externalList");
                if (jsonArr != null || jsonArr.size() == 0) {
                    List<HumanRiskInfo> list = new ArrayList<>();
                    jsonArr.forEach(obj -> {
                        HumanRiskInfo parseObject = JSONObject.parseObject(obj.toString(), HumanRiskInfo.class);
                        parseObject.setCreationTime(System.currentTimeMillis());
                        parseObject.setHumanName(name);
                        list.add(parseObject);
                    });
                    humanRiskInfoRepository.save(list);
                }
            } else if ("RiskDetail".equalsIgnoreCase(method)) {
                JSONArray jsonArr = openEyesTarget.getJSONObject("data").getJSONArray("dataList");
                String searchId = params.get("id");
                if (jsonArr != null || jsonArr.size() == 0) {
                    List<RiskDetail> list = new ArrayList<>();
                    jsonArr.forEach(obj -> {
                        RiskDetail parseObject = JSONObject.parseObject(obj.toString(), RiskDetail.class);
                        parseObject.setCreationTime(System.currentTimeMillis());
                        parseObject.setCompanyName(name);
                        String id = getGeneratedId(parseObject);
                        parseObject.setId(id);
                        parseObject.setSearchId(Long.parseLong(searchId));
                        list.add(parseObject);
                    });
                    riskDetailRepository.save(list);
                }
            } else if ("TaxCredit".equalsIgnoreCase(method)) {
                JSONArray jsonArr = openEyesTarget.getJSONObject("data").getJSONArray("items");
                if (jsonArr != null || jsonArr.size() == 0) {
                    List<TaxCredit> list = new ArrayList<>();
                    jsonArr.forEach(obj -> {
                        TaxCredit parseObject = JSONObject.parseObject(obj.toString(), TaxCredit.class);
                        parseObject.setCreationTime(System.currentTimeMillis());
                        String id = getGeneratedId(obj);
                        parseObject.setId(id);
                        list.add(parseObject);
                    });
                    taxCreditRepository.save(list);
                }
            } else if ("sousuo".equalsIgnoreCase(method)) {
                //待定
            } else if ("Holder".equalsIgnoreCase(method)) {
                JSONArray jsonArr = openEyesTarget.getJSONObject("data").getJSONArray("result");
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
                    holderRepository.save(list);
                }
            } else if ("ChangeInfo".equalsIgnoreCase(method)) {
                JSONArray jsonArr = openEyesTarget.getJSONObject("data").getJSONArray("result");
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
                    changeInfoRepository.save(list);
                }
            } else if ("Inverst".equalsIgnoreCase(method)) {
                JSONArray jsonArr = openEyesTarget.getJSONObject("data").getJSONArray("result");
                if (jsonArr != null || jsonArr.size() == 0) {
                    List<Inverst> list = new ArrayList<>();
                    jsonArr.forEach(obj -> {
                        Inverst parseObject = JSONObject.parseObject(obj.toString(), Inverst.class);
                        parseObject.setCreationTime(System.currentTimeMillis());
                        parseObject.setCname(name);
                        list.add(parseObject);
                    });
                    inverstRepository.save(list);
                }
            } else if ("Bids".equalsIgnoreCase(method)) {
                JSONArray jsonArr = openEyesTarget.getJSONObject("data").getJSONArray("items");
                if (jsonArr != null || jsonArr.size() == 0) {
                    List<Bids> list = new ArrayList<>();
                    jsonArr.forEach(obj -> {
                        JSONObject parseObject = JSONObject.parseObject(obj.toString());
                        Bids bid = new Bids();
                        bid.setCreationTime(System.currentTimeMillis());
                        bid.setAbs(parseObject.getString("abs"));
                        bid.setCname(name);
                        bid.setContent(parseObject.getString("content"));
                        bid.setIntro(parseObject.getString("intro"));
                        bid.setLink(parseObject.getString("link"));
                        bid.setProxy(parseObject.getString("proxy"));
                        bid.setPublishTime(parseObject.getLong("publishTime"));
                        bid.setPurchaser(parseObject.getString("purchaser"));
                        bid.setTitle(parseObject.getString("title"));
                        bid.setUuid(parseObject.getString("uuid"));
                        list.add(bid);
                    });
                    bidsRepository.save(list);
                }
            } else if ("Bond".equalsIgnoreCase(method)) {
                JSONArray jsonArr = openEyesTarget.getJSONObject("data").getJSONArray("bondList");
                if (jsonArr != null || jsonArr.size() == 0) {
                    List<Bond> list = new ArrayList<>();
                    jsonArr.forEach(obj -> {
                        Bond parseObject = JSONObject.parseObject(obj.toString(), Bond.class);
                        parseObject.setCreationTime(System.currentTimeMillis());
                        parseObject.setCname(name);
                        list.add(parseObject);
                    });
                }
            } else if ("Purchaseland".equalsIgnoreCase(method)) {
                JSONArray jsonArr = openEyesTarget.getJSONObject("data").getJSONArray("companyPurchaseLandList");
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
                    purchaselandRepository.save(list);
                }
            } else if ("Employment".equalsIgnoreCase(method)) {
                JSONArray jsonArr = openEyesTarget.getJSONObject("result").getJSONArray("items");
                if (jsonArr != null || jsonArr.size() == 0) {
                    List<Employment> list = new ArrayList<>();
                    jsonArr.forEach(obj -> {
                        Employment parseObject = JSONObject.parseObject(obj.toString(), Employment.class);
                        parseObject.setCreationTime(System.currentTimeMillis());
                        list.add(parseObject);
                    });
                    employmentRepository.save(list);
                }
            } else if ("CheckInfo".equalsIgnoreCase(method)) {
                JSONArray jsonArr = openEyesTarget.getJSONObject("data").getJSONArray("items");
                if (jsonArr != null || jsonArr.size() == 0) {
                    List<CheckInfo> list = new ArrayList<>();
                    jsonArr.forEach(obj -> {
                        CheckInfo parseObject = JSONObject.parseObject(obj.toString(), CheckInfo.class);
                        parseObject.setCreationTime(System.currentTimeMillis());
                        String id = getGeneratedId(obj);
                        parseObject.setId(id);
                        parseObject.setCname(name);
                        list.add(parseObject);
                    });
                    checkInfoRepository.save(list);
                }
            } else if ("AppbkInfo".equalsIgnoreCase(method)) {
                JSONArray jsonArr = openEyesTarget.getJSONObject("data").getJSONArray("items");
                if (jsonArr != null || jsonArr.size() == 0) {
                    List<AppbkInfo> list = new ArrayList<>();
                    jsonArr.forEach(obj -> {
                        AppbkInfo parseObject = JSONObject.parseObject(obj.toString(), AppbkInfo.class);
                        parseObject.setCreationTime(System.currentTimeMillis());
                        String id = getGeneratedId(obj);
                        parseObject.setId(id);
                        parseObject.setCname(name);
                        list.add(parseObject);
                    });
                    appbkInfoRepository.save(list);
                }
            } else if ("LawSuit".equalsIgnoreCase(method)) {
                JSONArray jsonArr = openEyesTarget.getJSONObject("data").getJSONArray("items");
                if (jsonArr != null || jsonArr.size() == 0) {
                    List<LawSuit> list = new ArrayList<>();
                    jsonArr.forEach(obj -> {
                        LawSuit parseObject = JSONObject.parseObject(obj.toString(), LawSuit.class);
                        parseObject.setCreationTime(System.currentTimeMillis());
                        parseObject.setCname(name);
                        list.add(parseObject);
                    });
                    lawSuitRepository.save(list);
                }
            } else if ("CourtAnnouncement".equalsIgnoreCase(method)) {
                JSONArray jsonArr = openEyesTarget.getJSONArray("courtAnnouncements");
                if (jsonArr != null || jsonArr.size() == 0) {
                    List<CourtAnnouncement> list = new ArrayList<>();
                    jsonArr.forEach(obj -> {
                        CourtAnnouncement parseObject = JSONObject.parseObject(obj.toString(), CourtAnnouncement.class);
                        parseObject.setCreationTime(System.currentTimeMillis());
                        parseObject.setCname(name);
                        list.add(parseObject);
                    });
                    courtAnnouncementRepository.save(list);
                }
            } else if ("ZhixingInfo".equalsIgnoreCase(method)) {
                JSONArray jsonArr = openEyesTarget.getJSONObject("data").getJSONArray("items");
                if (jsonArr != null || jsonArr.size() == 0) {
                    List<ZhixingInfo> list = new ArrayList<>();
                    jsonArr.forEach(obj -> {
                        ZhixingInfo parseObject = JSONObject.parseObject(obj.toString(), ZhixingInfo.class);
                        parseObject.setCreationTime(System.currentTimeMillis());
                        parseObject.setCname(name);
                        String id = getGeneratedId(obj);
                        parseObject.setId(id);
                        list.add(parseObject);
                    });
                    zhixingInfoRepository.save(list);
                }
            } else if ("Volatility".equalsIgnoreCase(method)) {
                JSONObject jsonArr = openEyesTarget.getJSONObject("data");
                if (jsonArr != null || jsonArr.size() == 0) {
                    Volatility parseObject = JSONObject.parseObject(jsonArr.toString(), Volatility.class);
                    parseObject.setCreationTime(System.currentTimeMillis());
                    parseObject.setCname(name);
                    String id = getGeneratedId(jsonArr);
                    parseObject.setId(id);
                    volatilityRepository.save(parseObject);
                }
            } else if ("CompanyInfo".equalsIgnoreCase(method)) {
                JSONObject jsonArr = openEyesTarget.getJSONObject("data");
                if (jsonArr != null || jsonArr.size() == 0) {
                    CompanyInfo parseObject = JSONObject.parseObject(jsonArr.toJSONString(), CompanyInfo.class);
                    parseObject.setCreationTime(System.currentTimeMillis());
                    String id = getGeneratedId(jsonArr);
                    parseObject.setId(id);
                    companyInfoRepository.save(parseObject);
                }
            } else if ("SeniorExecutive".equalsIgnoreCase(method)) {
                JSONArray jsonArr = openEyesTarget.getJSONObject("data").getJSONArray("dataList");
                if (jsonArr != null || jsonArr.size() == 0) {
                    List<SeniorExecutive> list = new ArrayList<>();
                    jsonArr.forEach(obj -> {
                        SeniorExecutive parseObject = JSONObject.parseObject(obj.toString(), SeniorExecutive.class);
                        parseObject.setCreationTime(System.currentTimeMillis());
                        parseObject.setCname(name);
                        String id = getGeneratedId(obj);
                        parseObject.setId(id);
                        list.add(parseObject);
                    });
                    seniorExecutiveRepository.save(list);
                }
            } else if ("HoldingCompany".equalsIgnoreCase(method)) {
                JSONArray jsonArr = openEyesTarget.getJSONObject("data").getJSONArray("dataList");
                if (jsonArr != null || jsonArr.size() == 0) {
                    List<HoldingCompany> list = new ArrayList<>();
                    jsonArr.forEach(obj -> {
                        HoldingCompany parseObject = JSONObject.parseObject(obj.toString(), HoldingCompany.class);
                        parseObject.setCreationTime(System.currentTimeMillis());
                        parseObject.setCname(name);
                        list.add(parseObject);
                    });
                    holdingCompanyRepository.save(list);
                }
            } else if ("Certificate".equalsIgnoreCase(method)) {
                JSONArray jsonArr = openEyesTarget.getJSONObject("data").getJSONArray("resultList");
                if (jsonArr != null || jsonArr.size() == 0) {
                    List<Certificate> list = new ArrayList<>();
                    jsonArr.forEach(obj -> {
                        Certificate parseObject = JSONObject.parseObject(obj.toString(), Certificate.class);
                        parseObject.setCreationTime(System.currentTimeMillis());
                        parseObject.setCname(name);
                        list.add(parseObject);
                    });
                    certificateRepository.save(list);
                }
            } else if ("Announcement".equalsIgnoreCase(method)) {
                JSONArray jsonArr = openEyesTarget.getJSONObject("data").getJSONArray("dataList");
                if (jsonArr != null || jsonArr.size() == 0) {
                    List<Announcement> list = new ArrayList<>();
                    jsonArr.forEach(obj -> {
                        Announcement parseObject = JSONObject.parseObject(obj.toString(), Announcement.class);
                        parseObject.setCreationTime(System.currentTimeMillis());
                        parseObject.setCname(name);
                        list.add(parseObject);
                    });
                    announcementRepository.save(list);
                }
            } else if ("ShareHolder".equalsIgnoreCase(method)) {
                JSONArray jsonArr = openEyesTarget.getJSONObject("data").getJSONArray("holderList");
                if (jsonArr != null || jsonArr.size() == 0) {
                    List<ShareHolder> list = new ArrayList<>();
                    jsonArr.forEach(obj -> {
                        ShareHolder parseObject = JSONObject.parseObject(obj.toString(), ShareHolder.class);
                        parseObject.setCreationTime(System.currentTimeMillis());
                        parseObject.setCname(name);
                        list.add(parseObject);
                    });
                    shareHolderRepository.save(list);
                }
            } else if ("IssueRelated".equalsIgnoreCase(method)) {
                JSONObject jsonArr = openEyesTarget.getJSONObject("data");
                if (jsonArr != null || jsonArr.size() == 0) {
                    IssueRelated parseObject = JSONObject.parseObject(jsonArr.toJSONString(), IssueRelated.class);
                    parseObject.setCreationTime(System.currentTimeMillis());
                    String id = getGeneratedId(parseObject);
                    parseObject.setCname(name);
                    parseObject.setId(id);
                    issueRelatedRepository.save(parseObject);
                }
            } else if ("ShareStructure".equalsIgnoreCase(method)) {
                JSONArray jsonArr = openEyesTarget.getJSONObject("data").getJSONArray("dataList");
                if (jsonArr != null || jsonArr.size() == 0) {
                    List<ShareStructure> list = new ArrayList<>();
                    jsonArr.forEach(obj -> {
                        ShareStructure parseObject = JSONObject.parseObject(obj.toString(), ShareStructure.class);
                        parseObject.setCreationTime(System.currentTimeMillis());
                        parseObject.setCname(name);
                        String id = getGeneratedId(obj);
                        parseObject.setId(id);
                        list.add(parseObject);
                    });
                    shareStructureRepository.save(list);
                }
            } else if ("EquityChange".equalsIgnoreCase(method)) {
                JSONArray jsonArr = openEyesTarget.getJSONObject("data").getJSONArray("dataList");
                if (jsonArr != null || jsonArr.size() == 0) {
                    List<EquityChange> list = new ArrayList<>();
                    jsonArr.forEach(obj -> {
                        EquityChange parseObject = JSONObject.parseObject(obj.toString(), EquityChange.class);
                        parseObject.setCreationTime(System.currentTimeMillis());
                        parseObject.setCname(name);
                        String id = getGeneratedId(obj);
                        parseObject.setId(id);
                        list.add(parseObject);
                    });
                    equityChangeRepository.save(list);
                }
            } else if ("BonusInfo".equalsIgnoreCase(method)) {
                JSONArray jsonArr = openEyesTarget.getJSONObject("data").getJSONArray("dataList");
                if (jsonArr != null || jsonArr.size() == 0) {
                    List<BonusInfo> list = new ArrayList<>();
                    jsonArr.forEach(obj -> {
                        BonusInfo parseObject = JSONObject.parseObject(obj.toString(), BonusInfo.class);
                        parseObject.setCname(name);
                        parseObject.setCreationTime(System.currentTimeMillis());
                        String id = getGeneratedId(obj);
                        parseObject.setId(id);
                        list.add(parseObject);
                    });
                    bonusInfoRepository.save(list);
                }
            } else if ("Allotmen".equalsIgnoreCase(method)) {
                JSONArray jsonArr = openEyesTarget.getJSONObject("data").getJSONArray("dataList");
                if (jsonArr != null || jsonArr.size() == 0) {
                    List<Allotmen> list = new ArrayList<>();
                    jsonArr.forEach(obj -> {
                        Allotmen parseObject = JSONObject.parseObject(obj.toString(), Allotmen.class);
                        parseObject.setCreationTime(System.currentTimeMillis());
                        parseObject.setCname(name);
                        String id = getGeneratedId(obj);
                        parseObject.setId(id);
                        list.add(parseObject);
                    });
                    allotmenRepository.save(list);
                }
            }
        } catch (Exception e) {
            log.error("存储数据发生异常:调用方法:" + method + "调用参数为:" + params, e.getMessage());
        }
    }

    private JSONObject finishData(JSONObject resultData) {
        resultData.put("err_msg", "查询数据为空");
        resultData.put("err_code", "999");
        return resultData;
    }

    private boolean checkMessage(JSONObject data, Map<String, String> params) {
        log.info("当前检查数据为:" + data);
        if (data.size() == 0) {
            return false;
        }
        Integer errorCode = data.getInteger("error_code");
        if (errorCode != null) {
            if (errorCode == 0 && data.getString("reason").equalsIgnoreCase("ok")) {// 请求成功开始计费
                try {
                    saveSearChTrace(params);
                } catch (Exception e) {
                    log.error("存储查询记录失败!失败是的参数为:" + params, e.getMessage());
                }
                return true;
            } else if (errorCode == 300000) {// 无数据
                return false;
            } else if (errorCode == 300001) {// 请求失败
                return false;
            } else if (errorCode == 300002) {// 账号失效
                return false;
            } else if (errorCode == 300003) {// 账号过期
                return false;
            } else if (errorCode == 300004) {// 访问频率过快
                return false;
            } else if (errorCode == 300005) {// 无权限访问此api
                return false;
            } else if (errorCode == 300006) {// 余额不足
                return false;
            } else if (errorCode == 300007) {// 剩余次数不足
                return false;
            } else if (errorCode == 300008) {// 缺少必要参数
                return false;
            } else if (errorCode == 300009) {// 账号信息有误
                return false;
            } else if (errorCode == 300010) {// URL不存在
                return false;
            }
        } else {//本地数据跑这里(包括不规范的天眼查数据)
            if (data.size() == 1) {//本地数据
                return true;
            }
            if (data.getInteger("totalRows") != null && data.getIntValue("totalRows") != 0) {
                saveSearChTrace(params);
                return true;
            }
            if (data.getJSONObject("data") != null && data.getJSONObject("data").getInteger("total") != null && data.getJSONObject("data").getInteger("total") != 0) {
                saveSearChTrace(params);
                return true;
            }
            if (data.getJSONObject("data") != null && data.getJSONObject("data").getInteger("count") != null && data.getJSONObject("data").getInteger("count") != 0) {
                saveSearChTrace(params);
                return true;
            }
            if (data.getInteger("total") != null && data.getInteger("total") != null) {
                saveSearChTrace(params);
                return true;
            }
        }
        return false;

    }

    private void saveSearChTrace(Map<String, String> params) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            String today = format.format(date);
            String method = params.get("method");
            String tag = params.get("tag");
            SearchCount search = new SearchCount();
            search.setToday(today);
            search.setStartTime(date.getTime());
            search.setTag(tag);
            search.setPrice(Double.parseDouble(threadLocal.get().get("price").toString()));
            search.setMethod(method);
            searchCountRepository.save(search);
        } catch (Exception e) {
            throw e;
        }
    }

    protected String getGeneratedId(Object info) {
        byte[] hashPassword = Digests.sha1(info.toString().getBytes(), null, Encodes.HASH_INTERATIONS);
        return Encodes.encodeHex(hashPassword);
    }

    private JSONObject searchFromLocal(Map<String, String> params) throws Exception {
        log.info("查询本地数据库开始-----------------");
        JSONObject resultData = new JSONObject();
        String method = params.get("method");
        String name = params.get("name");
        Map<String, Object> privateParams = new HashMap<>();
        try {
            if (method.equalsIgnoreCase("sousuo")) {
                privateParams.put("price", 0.01);
                privateParams.put("url", "https://open.api.tianyancha.com/services/v3/newopen/searchV2.json");
                privateParams.put("method", "sousuo");
                threadLocal.set(privateParams);
            } else if (method.equalsIgnoreCase("baseInfo")) {
                BaseInfo findByName = baseInfoRepository.findByName(name);
                OpenEnum annotation = BaseInfoRepository.class.getAnnotation(OpenEnum.class);
                double price = annotation.price();
                String url = annotation.url();
                privateParams.put("price", price);
                privateParams.put("url", url);
                privateParams.put("method", method);
                threadLocal.set(privateParams);
                if (findByName != null) {
                    resultData.put("result", findByName);
                }
            } else if (method.equalsIgnoreCase("staff")) {
                List<Staff> list = staffRepository.findByCname(name);
                OpenEnum annotation = StaffRepository.class.getAnnotation(OpenEnum.class);
                double price = annotation.price();
                String url = annotation.url();
                privateParams.put("price", price);
                privateParams.put("url", url);
                privateParams.put("method", method);
                threadLocal.set(privateParams);
                if (list.size() > 0) {
                    JSONObject inList = new JSONObject();
                    inList.put("result", list);
                    resultData.put("data", inList);
                    try {
                        long time = System.currentTimeMillis() - list.get(0).getCreationTime();
                        if (time >= KeyConstan.FAILURE_TIME) {
                            staffRepository.delete(list);
                        }
                    } catch (Exception e) {
                        log.info("删除过期数据出错:", e.getMessage());
                    }
                }
            } else if (method.equalsIgnoreCase("holder")) {
                List<Holder> list = holderRepository.findByCname(name);
                OpenEnum annotation = HolderRepository.class.getAnnotation(OpenEnum.class);
                double price = annotation.price();
                String url = annotation.url();
                privateParams.put("price", price);
                privateParams.put("url", url);
                privateParams.put("method", method);
                threadLocal.set(privateParams);
                if (list.size() > 0) {
                    JSONObject inList = new JSONObject();
                    inList.put("result", list);
                    resultData.put("data", inList);
                    //当用户查询时，如果存储时间超过1个月就重新查询
                    try {
                        long time = System.currentTimeMillis() - list.get(0).getCreationTime();
                        if (time >= KeyConstan.FAILURE_TIME) {
                            holderRepository.delete(list);
                        }
                    } catch (Exception e) {
                        log.info("删除过期数据出错:", e.getMessage());
                    }
                }
            } else if (method.equalsIgnoreCase("INVERST")) {
                List<Inverst> list = inverstRepository.findByCname(name);
                OpenEnum annotation = InverstRepository.class.getAnnotation(OpenEnum.class);
                double price = annotation.price();
                String url = annotation.url();
                privateParams.put("price", price);
                privateParams.put("url", url);
                privateParams.put("method", method);
                threadLocal.set(privateParams);
                if (list.size() > 0) {
                    JSONObject inList = new JSONObject();
                    inList.put("result", list);
                    resultData.put("data", inList);
                    //当用户查询时，如果存储时间超过1个月就重新查询
                    try {
                        long time = System.currentTimeMillis() - list.get(0).getCreationTime();
                        if (time >= KeyConstan.FAILURE_TIME) {
                            inverstRepository.delete(list);
                        }
                    } catch (Exception e) {
                        log.info("删除过期数据出错:", e.getMessage());
                    }
                }
            } else if (method.equalsIgnoreCase("CHANGEINFO")) {
                List<ChangeInfo> list = changeInfoRepository.findByCname(name);
                OpenEnum annotation = ChangeInfoRepository.class.getAnnotation(OpenEnum.class);
                double price = annotation.price();
                String url = annotation.url();
                privateParams.put("price", price);
                privateParams.put("url", url);
                privateParams.put("method", method);
                threadLocal.set(privateParams);
                if (list.size() > 0) {
                    JSONObject inList = new JSONObject();
                    inList.put("result", list);
                    resultData.put("data", inList);
                    //当用户查询时，如果存储时间超过1个月就重新查询
                    try {
                        long time = System.currentTimeMillis() - list.get(0).getCreationTime();
                        if (time >= KeyConstan.FAILURE_TIME) {
                            changeInfoRepository.delete(list);
                        }
                    } catch (Exception e) {
                        log.info("删除过期数据出错:", e.getMessage());
                    }
                }
            } else if (method.equalsIgnoreCase("NIANBAO")) {

            } else if (method.equalsIgnoreCase("BRANCH")) {
                List<Branch> list = branchRepository.findByCompanyName(name);
                OpenEnum annotation = BranchRepository.class.getAnnotation(OpenEnum.class);
                double price = annotation.price();
                String url = annotation.url();
                privateParams.put("price", price);
                privateParams.put("url", url);
                privateParams.put("method", method);
                threadLocal.set(privateParams);
                if (list.size() > 0) {
                    JSONObject inList = new JSONObject();
                    inList.put("result", list);
                    resultData.put("data", inList);
                    try {
                        long time = System.currentTimeMillis() - list.get(0).getCreationTime();
                        if (time >= KeyConstan.FAILURE_TIME) {
                            branchRepository.delete(list);
                        }
                    } catch (Exception e) {
                        log.info("删除过期数据出错:", e.getMessage());
                    }
                }
            } else if (method.equalsIgnoreCase("HISTORYRONGZI")) {
                List<HistoryRongZi> list = historyRongZiRepository.findByCompanyName(name);
                OpenEnum annotation = HistoryRongZiRepository.class.getAnnotation(OpenEnum.class);
                double price = annotation.price();
                String url = annotation.url();
                privateParams.put("price", price);
                privateParams.put("url", url);
                privateParams.put("method", method);
                threadLocal.set(privateParams);
                if (list.size() > 0) {
                    JSONObject inList = new JSONObject();
                    inList.put("rows", list);
                    JSONObject inList2 = new JSONObject();
                    inList2.put("page", inList);
                    resultData.put("result", inList2);
                    try {
                        long time = System.currentTimeMillis() - list.get(0).getCreationTime();
                        if (time >= KeyConstan.FAILURE_TIME) {
                            historyRongZiRepository.delete(list);
                        }
                    } catch (Exception e) {
                        log.info("删除过期数据出错:", e.getMessage());
                    }
                }
            } else if (method.equalsIgnoreCase("TEAMMEMBER")) {
                List<TeamMember> list = teamMemberRepository.findByCompanyName(name);
                OpenEnum annotation = TeamMemberRepository.class.getAnnotation(OpenEnum.class);
                double price = annotation.price();
                String url = annotation.url();
                privateParams.put("price", price);
                privateParams.put("url", url);
                privateParams.put("method", method);
                threadLocal.set(privateParams);
                if (list.size() > 0) {
                    JSONObject inList = new JSONObject();
                    inList.put("rows", list);
                    JSONObject page = new JSONObject();
                    page.put("page", inList);
                    resultData.put("result", page);
                    try {
                        long time = System.currentTimeMillis() - list.get(0).getCreationTime();
                        if (time >= KeyConstan.FAILURE_TIME) {
                            teamMemberRepository.delete(list);
                        }
                    } catch (Exception e) {
                        log.info("删除过期数据出错:", e.getMessage());
                    }
                }
            } else if (method.equalsIgnoreCase("PRODUCTINFO")) {
                List<ProductInfo> list = productInfoRepository.findByCompanyName(name);
                OpenEnum annotation = ProductInfoRepository.class.getAnnotation(OpenEnum.class);
                double price = annotation.price();
                String url = annotation.url();
                privateParams.put("price", price);
                privateParams.put("url", url);
                privateParams.put("method", method);
                threadLocal.set(privateParams);
                if (list.size() > 0) {
                    JSONObject inList = new JSONObject();
                    inList.put("rows", list);
                    JSONObject page = new JSONObject();
                    page.put("page", inList);
                    resultData.put("result", page);
                    try {
                        long time = System.currentTimeMillis() - list.get(0).getCreationTime();
                        if (time >= KeyConstan.FAILURE_TIME) {
                            productInfoRepository.delete(list);
                        }
                    } catch (Exception e) {
                        log.info("删除过期数据出错:", e.getMessage());
                    }
                }
            } else if (method.equalsIgnoreCase("TouZi")) {
                List<TouZi> list = touZiRepository.findByCompanyName(name);
                OpenEnum annotation = TouZiRepository.class.getAnnotation(OpenEnum.class);
                double price = annotation.price();
                String url = annotation.url();
                privateParams.put("price", price);
                privateParams.put("url", url);
                privateParams.put("method", method);
                threadLocal.set(privateParams);
                if (list.size() > 0) {
                    JSONObject inList = new JSONObject();
                    inList.put("items", list);
                    resultData.put("result", inList);
                    try {
                        long time = System.currentTimeMillis() - list.get(0).getCreationTime();
                        if (time >= KeyConstan.FAILURE_TIME) {
                            touZiRepository.delete(list);
                        }
                    } catch (Exception e) {
                        log.info("删除过期数据出错:", e.getMessage());
                    }
                }
            } else if (method.equalsIgnoreCase("JINGPIN")) {
                List<JingPin> list = jingPinRepository.findByCname(name);
                OpenEnum annotation = JingPinRepository.class.getAnnotation(OpenEnum.class);
                double price = annotation.price();
                String url = annotation.url();
                privateParams.put("price", price);
                privateParams.put("url", url);
                privateParams.put("method", method);
                threadLocal.set(privateParams);
                if (list.size() > 0) {
                    JSONObject inList = new JSONObject();
                    inList.put("rows", list);
                    JSONObject outList = new JSONObject();
                    outList.put("page", inList);
                    resultData.put("result", outList);
                    try {
                        long time = System.currentTimeMillis() - list.get(0).getCreationTime();
                        if (time >= KeyConstan.FAILURE_TIME) {
                            jingPinRepository.delete(list);
                        }
                    } catch (Exception e) {
                        log.info("删除过期数据出错:", e.getMessage());
                    }
                }
            } else if (method.equalsIgnoreCase("LAWSUIT")) {
                List<LawSuit> list = lawSuitRepository.findByCname(name);
                OpenEnum annotation = LawSuitRepository.class.getAnnotation(OpenEnum.class);
                double price = annotation.price();
                String url = annotation.url();
                privateParams.put("price", price);
                privateParams.put("url", url);
                privateParams.put("method", method);
                threadLocal.set(privateParams);
                if (list.size() > 0) {
                    JSONObject inList = new JSONObject();
                    inList.put("items", list);
                    resultData.put("data", inList);
                    //当用户查询时，如果存储时间超过1个月就重新查询
                    try {
                        long time = System.currentTimeMillis() - list.get(0).getCreationTime();
                        if (time >= KeyConstan.FAILURE_TIME) {
                            lawSuitRepository.delete(list);
                        }
                    } catch (Exception e) {
                        log.info("删除过期数据出错:", e.getMessage());
                    }
                }
            } else if (method.equalsIgnoreCase("COURTANNOUNCEMENT")) {
                List<CourtAnnouncement> list = courtAnnouncementRepository.findByCname(name);
                OpenEnum annotation = CourtAnnouncementRepository.class.getAnnotation(OpenEnum.class);
                double price = annotation.price();
                String url = annotation.url();
                privateParams.put("price", price);
                privateParams.put("url", url);
                privateParams.put("method", method);
                threadLocal.set(privateParams);
                if (list.size() > 0) {
                    resultData.put("courtAnnouncements", list);
                    //当用户查询时，如果存储时间超过1个月就重新查询
                    try {
                        long time = System.currentTimeMillis() - list.get(0).getCreationTime();
                        if (time >= KeyConstan.FAILURE_TIME) {
                            courtAnnouncementRepository.delete(list);
                        }
                    } catch (Exception e) {
                        log.info("删除过期数据出错:", e.getMessage());
                    }
                }
            } else if (method.equalsIgnoreCase("Dishonest")) {
                List<Dishonest> list = dishonestRepository.findByIname(name);
                OpenEnum annotation = DishonestRepository.class.getAnnotation(OpenEnum.class);
                double price = annotation.price();
                String url = annotation.url();
                privateParams.put("price", price);
                privateParams.put("url", url);
                privateParams.put("method", method);
                threadLocal.set(privateParams);
                if (list.size() > 0) {
                    JSONObject inList = new JSONObject();
                    inList.put("items", list);
                    resultData.put("result", inList);
                    //当用户查询时，如果存储时间超过1个月就重新查询
                    try {
                        long time = System.currentTimeMillis() - list.get(0).getCreationTime();
                        if (time >= KeyConstan.FAILURE_TIME) {
                            dishonestRepository.delete(list);
                        }
                    } catch (Exception e) {
                        log.info("删除过期数据出错:", e.getMessage());
                    }
                }
            } else if (method.equalsIgnoreCase("ZHIXINGINFO")) {
                List<ZhixingInfo> list = zhixingInfoRepository.findByCname(name);
                OpenEnum annotation = ZhixingInfoRepository.class.getAnnotation(OpenEnum.class);
                double price = annotation.price();
                String url = annotation.url();
                privateParams.put("price", price);
                privateParams.put("url", url);
                privateParams.put("method", method);
                threadLocal.set(privateParams);
                if (list.size() > 0) {
                    JSONObject inList = new JSONObject();
                    inList.put("items", list);
                    resultData.put("data", inList);
                    //当用户查询时，如果存储时间超过1个月就重新查询
                    try {
                        long time = System.currentTimeMillis() - list.get(0).getCreationTime();
                        if (time >= KeyConstan.FAILURE_TIME) {
                            zhixingInfoRepository.delete(list);
                        }
                    } catch (Exception e) {
                        log.info("删除过期数据出错:", e.getMessage());
                    }
                }
            } else if (method.equalsIgnoreCase("ABNORMAL")) {
                List<Abnormal> list = abnormalRepository.findByCompanyName(name);
                OpenEnum annotation = AbnormalRepository.class.getAnnotation(OpenEnum.class);
                double price = annotation.price();
                String url = annotation.url();
                privateParams.put("price", price);
                privateParams.put("url", url);
                privateParams.put("method", method);
                threadLocal.set(privateParams);
                if (list.size() > 0) {
                    JSONObject inList = new JSONObject();
                    inList.put("data", list);
                    resultData.put("result", inList);
                    //当用户查询时，如果存储时间超过1个月就重新查询
                    try {
                        long time = System.currentTimeMillis() - list.get(0).getCreationTime();
                        if (time >= KeyConstan.FAILURE_TIME) {
                            abnormalRepository.delete(list);
                        }
                    } catch (Exception e) {
                        log.info("删除过期数据出错:", e.getMessage());
                    }
                }
            } else if (method.equalsIgnoreCase("PunishmentInfo")) {
                List<PunishmentInfo> list = punishmentInfoRepository.findByName(name);
                OpenEnum annotation = PunishmentInfoRepository.class.getAnnotation(OpenEnum.class);
                double price = annotation.price();
                String url = annotation.url();
                privateParams.put("price", price);
                privateParams.put("url", url);
                privateParams.put("method", method);
                threadLocal.set(privateParams);
                if (list.size() > 0) {
                    JSONObject inList = new JSONObject();
                    inList.put("items", list);
                    resultData.put("data", inList);
                    try {
                        long time = System.currentTimeMillis() - list.get(0).getCreationTime();
                        if (time >= KeyConstan.FAILURE_TIME) {
                            punishmentInfoRepository.delete(list);
                        }
                    } catch (Exception e) {
                        log.info("删除过期数据出错:", e.getMessage());
                    }
                }
            } else if (method.equalsIgnoreCase("Illegalinfo")) {
                List<Illegalinfo> list = illegalinfoRepository.findByCompanyName(name);
                OpenEnum annotation = IllegalinfoRepository.class.getAnnotation(OpenEnum.class);
                double price = annotation.price();
                String url = annotation.url();
                privateParams.put("price", price);
                privateParams.put("url", url);
                privateParams.put("method", method);
                threadLocal.set(privateParams);
                if (list.size() > 0) {
                    JSONObject inList = new JSONObject();
                    inList.put("items", list);
                    resultData.put("result", inList);
                    try {
                        long time = System.currentTimeMillis() - list.get(0).getCreationTime();
                        if (time >= KeyConstan.FAILURE_TIME) {
                            illegalinfoRepository.delete(list);
                        }
                    } catch (Exception e) {
                        log.info("删除过期数据出错:", e.getMessage());
                    }
                }
            } else if (method.equalsIgnoreCase("OwnTax")) {
                List<OwnTax> list = ownTaxRepository.findByName(name);
                OpenEnum annotation = OwnTaxRepository.class.getAnnotation(OpenEnum.class);
                double price = annotation.price();
                String url = annotation.url();
                privateParams.put("price", price);
                privateParams.put("url", url);
                privateParams.put("method", method);
                threadLocal.set(privateParams);
                if (list.size() > 0) {
                    JSONObject inList = new JSONObject();
                    inList.put("items", list);
                    resultData.put("result", inList);
                    //当用户查询时，如果存储时间超过1个月就重新查询
                    try {
                        long time = System.currentTimeMillis() - list.get(0).getCreationTime();
                        if (time >= KeyConstan.FAILURE_TIME) {
                            ownTaxRepository.delete(list);
                        }
                    } catch (Exception e) {
                        log.info("删除过期数据出错:", e.getMessage());
                    }
                }
            } else if (method.equalsIgnoreCase("BIDS")) {
                List<Bids> list = bidsRepository.findByCname(name);
                OpenEnum annotation = BidsRepository.class.getAnnotation(OpenEnum.class);
                double price = annotation.price();
                String url = annotation.url();
                privateParams.put("price", price);
                privateParams.put("url", url);
                privateParams.put("method", method);
                threadLocal.set(privateParams);
                if (list.size() > 0) {
                    JSONObject inList = new JSONObject();
                    inList.put("items", list);
                    resultData.put("data", inList);
                    //当用户查询时，如果存储时间超过1个月就重新查询
                    try {
                        long time = System.currentTimeMillis() - list.get(0).getCreationTime();
                        if (time >= KeyConstan.FAILURE_TIME) {
                            bidsRepository.delete(list);
                        }
                    } catch (Exception e) {
                        log.info("删除过期数据出错:", e.getMessage());
                    }
                }
            } else if (method.equalsIgnoreCase("BOND")) {
                List<Bond> list = bondRepository.findByCname(name);
                OpenEnum annotation = BondRepository.class.getAnnotation(OpenEnum.class);
                double price = annotation.price();
                String url = annotation.url();
                privateParams.put("price", price);
                privateParams.put("url", url);
                privateParams.put("method", method);
                threadLocal.set(privateParams);
                if (list.size() > 0) {
                    JSONObject inList = new JSONObject();
                    inList.put("bondList", list);
                    resultData.put("data", inList);
                    //当用户查询时，如果存储时间超过1个月就重新查询
                    try {
                        long time = System.currentTimeMillis() - list.get(0).getCreationTime();
                        if (time >= KeyConstan.FAILURE_TIME) {
                            bondRepository.delete(list);
                        }
                    } catch (Exception e) {
                        log.info("删除过期数据出错:", e.getMessage());
                    }
                }
            } else if (method.equalsIgnoreCase("PURCHASELAND")) {
                List<Purchaseland> list = purchaselandRepository.findByCname(name);
                OpenEnum annotation = PurchaselandRepository.class.getAnnotation(OpenEnum.class);
                double price = annotation.price();
                String url = annotation.url();
                privateParams.put("price", price);
                privateParams.put("url", url);
                privateParams.put("method", method);
                threadLocal.set(privateParams);
                if (list.size() > 0) {
                    JSONObject inList = new JSONObject();
                    inList.put("companyPurchaseLandList", list);
                    resultData.put("data", inList);
                    //当用户查询时，如果存储时间超过1个月就重新查询
                    try {
                        long time = System.currentTimeMillis() - list.get(0).getCreationTime();
                        if (time >= KeyConstan.FAILURE_TIME) {
                            purchaselandRepository.delete(list);
                        }
                    } catch (Exception e) {
                        log.info("删除过期数据出错:", e.getMessage());
                    }
                }
            } else if (method.equalsIgnoreCase("EMPLOYMENT")) {
                List<Employment> list = employmentRepository.findByCompanyName(name);
                OpenEnum annotation = EmploymentRepository.class.getAnnotation(OpenEnum.class);
                double price = annotation.price();
                String url = annotation.url();
                privateParams.put("price", price);
                privateParams.put("url", url);
                privateParams.put("method", method);
                threadLocal.set(privateParams);
                if (list.size() > 0) {
                    JSONObject inList = new JSONObject();
                    inList.put("items", list);
                    resultData.put("result", inList);
                    //当用户查询时，如果存储时间超过1个月就重新查询
                    try {
                        long time = System.currentTimeMillis() - list.get(0).getCreationTime();
                        if (time >= KeyConstan.FAILURE_TIME) {
                            employmentRepository.delete(list);
                        }
                    } catch (Exception e) {
                        log.info("删除过期数据出错:", e.getMessage());
                    }
                }
            } else if (method.equalsIgnoreCase("TaxCredit")) {
                List<TaxCredit> list = taxCreditRepository.findByName(name);
                OpenEnum annotation = TaxCreditRepository.class.getAnnotation(OpenEnum.class);
                double price = annotation.price();
                String url = annotation.url();
                privateParams.put("price", price);
                privateParams.put("url", url);
                privateParams.put("method", method);
                threadLocal.set(privateParams);
                if (list.size() > 0) {
                    JSONObject inList = new JSONObject();
                    inList.put("items", list);
                    resultData.put("data", inList);
                    //当用户查询时，如果存储时间超过1个月就重新查询
                    try {
                        long time = System.currentTimeMillis() - list.get(0).getCreationTime();
                        if (time >= KeyConstan.FAILURE_TIME) {
                            taxCreditRepository.delete(list);
                        }
                    } catch (Exception e) {
                        log.info("删除过期数据出错:", e.getMessage());
                    }
                }
            } else if (method.equalsIgnoreCase("CHECKINFO")) {
                List<CheckInfo> list = checkInfoRepository.findByCname(name);
                OpenEnum annotation = CheckInfoRepository.class.getAnnotation(OpenEnum.class);
                double price = annotation.price();
                String url = annotation.url();
                privateParams.put("price", price);
                privateParams.put("url", url);
                privateParams.put("method", method);
                threadLocal.set(privateParams);
                if (list.size() > 0) {
                    JSONObject inList = new JSONObject();
                    inList.put("items", list);
                    resultData.put("data", inList);
                    //当用户查询时，如果存储时间超过1个月就重新查询
                    try {
                        long time = System.currentTimeMillis() - list.get(0).getCreationTime();
                        if (time >= KeyConstan.FAILURE_TIME) {
                            checkInfoRepository.delete(list);
                        }
                    } catch (Exception e) {
                        log.info("删除过期数据出错:", e.getMessage());
                    }
                }
            } else if (method.equalsIgnoreCase("APPBKINFO")) {
                List<AppbkInfo> list = appbkInfoRepository.findByCname(name);
                OpenEnum annotation = AppbkInfoRepository.class.getAnnotation(OpenEnum.class);
                double price = annotation.price();
                String url = annotation.url();
                privateParams.put("price", price);
                privateParams.put("url", url);
                privateParams.put("method", method);
                threadLocal.set(privateParams);
                if (list.size() > 0) {
                    JSONObject inList = new JSONObject();
                    inList.put("items", list);
                    resultData.put("data", inList);
                    //当用户查询时，如果存储时间超过1个月就重新查询
                    try {
                        long time = System.currentTimeMillis() - list.get(0).getCreationTime();
                        if (time >= KeyConstan.FAILURE_TIME) {
                            appbkInfoRepository.delete(list);
                        }
                    } catch (Exception e) {
                        log.info("删除过期数据出错:", e.getMessage());
                    }
                }
            } else if (method.equalsIgnoreCase("CERTIFICATE")) {
                List<Certificate> list = certificateRepository.findByCname(name);
                OpenEnum annotation = CertificateRepository.class.getAnnotation(OpenEnum.class);
                double price = annotation.price();
                String url = annotation.url();
                privateParams.put("price", price);
                privateParams.put("url", url);
                privateParams.put("method", method);
                threadLocal.set(privateParams);
                if (list.size() > 0) {
                    JSONObject inList = new JSONObject();
                    inList.put("resultList", list);
                    resultData.put("data", inList);
                    //当用户查询时，如果存储时间超过1个月就重新查询
                    try {
                        long time = System.currentTimeMillis() - list.get(0).getCreationTime();
                        if (time >= KeyConstan.FAILURE_TIME) {
                            certificateRepository.delete(list);
                        }
                    } catch (Exception e) {
                        log.info("删除过期数据出错:", e.getMessage());
                    }
                }
            } else if (method.equalsIgnoreCase("SHANGBIAO")) {
                List<ShangBiao> list = shangBiaoRepository.findByApplicantCn(name);
                OpenEnum annotation = ShangBiaoRepository.class.getAnnotation(OpenEnum.class);
                double price = annotation.price();
                String url = annotation.url();
                privateParams.put("price", price);
                privateParams.put("url", url);
                privateParams.put("method", method);
                threadLocal.set(privateParams);
                if (list.size() > 0) {
                    JSONObject inList = new JSONObject();
                    inList.put("items", list);
                    resultData.put("data", inList);
                    try {
                        long time = System.currentTimeMillis() - list.get(0).getCreationTime();
                        if (time >= KeyConstan.FAILURE_TIME) {
                            shangBiaoRepository.delete(list);
                        }
                    } catch (Exception e) {
                        log.info("删除过期数据出错:", e.getMessage());
                    }
                }
            } else if (method.equalsIgnoreCase("PATENTS")) {
                List<Patents> list = patentsRepository.findByApplicantName(name);
                OpenEnum annotation = PatentsRepository.class.getAnnotation(OpenEnum.class);
                double price = annotation.price();
                String url = annotation.url();
                privateParams.put("price", price);
                privateParams.put("url", url);
                privateParams.put("method", method);
                threadLocal.set(privateParams);
                if (list.size() > 0) {
                    JSONObject inList = new JSONObject();
                    inList.put("items", list);
                    resultData.put("data", inList);
                    //当用户查询时，如果存储时间超过1个月就重新查询
                    try {
                        long time = System.currentTimeMillis() - list.get(0).getCreationTime();
                        if (time >= KeyConstan.FAILURE_TIME) {
                            patentsRepository.delete(list);
                        }
                    } catch (Exception e) {
                        log.info("删除过期数据出错:", e.getMessage());
                    }
                }
            } else if (method.equalsIgnoreCase("COPYREG")) {
                List<CopyReg> list = copyRegRepository.findByCompanyName(name);
                OpenEnum annotation = CopyRegRepository.class.getAnnotation(OpenEnum.class);
                double price = annotation.price();
                String url = annotation.url();
                privateParams.put("price", price);
                privateParams.put("url", url);
                privateParams.put("method", method);
                threadLocal.set(privateParams);
                if (list.size() > 0) {
                    JSONObject inList = new JSONObject();
                    inList.put("items", list);
                    resultData.put("data", inList);
                    //当用户查询时，如果存储时间超过1个月就重新查询
                    try {
                        long time = System.currentTimeMillis() - list.get(0).getCreationTime();
                        if (time >= KeyConstan.FAILURE_TIME) {
                            copyRegRepository.delete(list);
                        }
                    } catch (Exception e) {
                        log.info("删除过期数据出错:", e.getMessage());
                    }
                }
            } else if (method.equalsIgnoreCase("ICP")) {
                List<Icp> list = icpRepository.findByCompanyName(name);
                OpenEnum annotation = IcpRepository.class.getAnnotation(OpenEnum.class);
                double price = annotation.price();
                String url = annotation.url();
                privateParams.put("price", price);
                privateParams.put("url", url);
                privateParams.put("method", method);
                threadLocal.set(privateParams);
                if (list.size() > 0) {
                    resultData.put("data", list);
                    //当用户查询时，如果存储时间超过1个月就重新查询
                    try {
                        long time = System.currentTimeMillis() - list.get(0).getCreationTime();
                        if (time >= KeyConstan.FAILURE_TIME) {
                            icpRepository.delete(list);
                        }
                    } catch (Exception e) {
                        log.info("删除过期数据出错:", e.getMessage());
                    }
                }
            } else if (method.equalsIgnoreCase("VOLATILITY")) {
                List<Volatility> list = volatilityRepository.findByCname(name);
                OpenEnum annotation = VolatilityRepository.class.getAnnotation(OpenEnum.class);
                double price = annotation.price();
                String url = annotation.url();
                privateParams.put("price", price);
                privateParams.put("url", url);
                privateParams.put("method", method);
                threadLocal.set(privateParams);
                if (list.size() > 0) {
                    resultData.put("data", list.get(0));
                    //当用户查询时，如果存储时间超过1个月就重新查询
                    try {
                        long time = System.currentTimeMillis() - list.get(0).getCreationTime();
                        if (time >= KeyConstan.FAILURE_TIME) {
                            volatilityRepository.delete(list);
                        }
                    } catch (Exception e) {
                        log.info("删除过期数据出错:", e.getMessage());
                    }
                }
            } else if (method.equalsIgnoreCase("COMPANYINFO")) {
                List<CompanyInfo> list = companyInfoRepository.findByCompanyName(name);
                OpenEnum annotation = CompanyInfoRepository.class.getAnnotation(OpenEnum.class);
                double price = annotation.price();
                String url = annotation.url();
                privateParams.put("price", price);
                privateParams.put("url", url);
                privateParams.put("method", method);
                threadLocal.set(privateParams);
                if (list.size() > 0) {
                    resultData.put("data", list.get(0));
                    //当用户查询时，如果存储时间超过1个月就重新查询
                    try {
                        long time = System.currentTimeMillis() - list.get(0).getCreationTime();
                        if (time >= KeyConstan.FAILURE_TIME) {
                            companyInfoRepository.delete(list);
                        }
                    } catch (Exception e) {
                        log.info("删除过期数据出错:", e.getMessage());
                    }
                }
            } else if (method.equalsIgnoreCase("HOLDINGCOMPANY")) {
                List<HoldingCompany> list = holdingCompanyRepository.findByCname(name);
                OpenEnum annotation = HoldingCompanyRepository.class.getAnnotation(OpenEnum.class);
                double price = annotation.price();
                String url = annotation.url();
                privateParams.put("price", price);
                privateParams.put("url", url);
                privateParams.put("method", method);
                threadLocal.set(privateParams);
                if (list.size() > 0) {
                    JSONObject inList = new JSONObject();
                    inList.put("dataList", list);
                    resultData.put("data", inList);
                    //当用户查询时，如果存储时间超过1个月就重新查询
                    try {
                        long time = System.currentTimeMillis() - list.get(0).getCreationTime();
                        if (time >= KeyConstan.FAILURE_TIME) {
                            holdingCompanyRepository.delete(list);
                        }
                    } catch (Exception e) {
                        log.info("删除过期数据出错:", e.getMessage());
                    }
                }
            } else if (method.equalsIgnoreCase("SENIOREXECUTIVE")) {
                List<SeniorExecutive> list = seniorExecutiveRepository.findByCname(name);
                OpenEnum annotation = SeniorExecutiveRepository.class.getAnnotation(OpenEnum.class);
                double price = annotation.price();
                String url = annotation.url();
                privateParams.put("price", price);
                privateParams.put("url", url);
                privateParams.put("method", method);
                threadLocal.set(privateParams);
                if (list.size() > 0) {
                    JSONObject inList = new JSONObject();
                    inList.put("dataList", list);
                    resultData.put("data", inList);
                    //当用户查询时，如果存储时间超过1个月就重新查询
                    try {
                        long time = System.currentTimeMillis() - list.get(0).getCreationTime();
                        if (time >= KeyConstan.FAILURE_TIME) {
                            seniorExecutiveRepository.delete(list);
                        }
                    } catch (Exception e) {
                        log.info("删除过期数据出错:", e.getMessage());
                    }
                }
            } else if (method.equalsIgnoreCase("ANNOUNCEMENT")) {
                List<Announcement> list = announcementRepository.findByCname(name);
                OpenEnum annotation = AnnouncementRepository.class.getAnnotation(OpenEnum.class);
                double price = annotation.price();
                String url = annotation.url();
                privateParams.put("price", price);
                privateParams.put("url", url);
                privateParams.put("method", method);
                threadLocal.set(privateParams);
                if (list.size() > 0) {
                    JSONObject inList = new JSONObject();
                    inList.put("dataList", list);
                    resultData.put("data", inList);
                    //当用户查询时，如果存储时间超过1个月就重新查询
                    try {
                        long time = System.currentTimeMillis() - list.get(0).getCreationTime();
                        if (time >= KeyConstan.FAILURE_TIME) {
                            announcementRepository.delete(list);
                        }
                    } catch (Exception e) {
                        log.info("删除过期数据出错:", e.getMessage());
                    }
                }
            } else if (method.equalsIgnoreCase("SHAREHOLDER")) {
                List<ShareHolder> list = shareHolderRepository.findByCname(name);
                OpenEnum annotation = ShareHolderRepository.class.getAnnotation(OpenEnum.class);
                double price = annotation.price();
                String url = annotation.url();
                privateParams.put("price", price);
                privateParams.put("url", url);
                privateParams.put("method", method);
                threadLocal.set(privateParams);
                if (list.size() > 0) {
                    JSONObject inList = new JSONObject();
                    inList.put("holderList", list);
                    resultData.put("data", inList);
                    //当用户查询时，如果存储时间超过1个月就重新查询
                    try {
                        long time = System.currentTimeMillis() - list.get(0).getCreationTime();
                        if (time >= KeyConstan.FAILURE_TIME) {
                            shareHolderRepository.delete(list);
                        }
                    } catch (Exception e) {
                        log.info("删除过期数据出错:", e.getMessage());
                    }
                }
            } else if (method.equalsIgnoreCase("ISSUERELATED")) {
                List<IssueRelated> list = issueRelatedRepository.findByCname(name);
                OpenEnum annotation = IssueRelatedRepository.class.getAnnotation(OpenEnum.class);
                double price = annotation.price();
                String url = annotation.url();
                privateParams.put("price", price);
                privateParams.put("url", url);
                privateParams.put("method", method);
                threadLocal.set(privateParams);
                if (list.size() > 0) {
                    resultData.put("data", list.get(0));
                    //当用户查询时，如果存储时间超过1个月就重新查询
                    try {
                        long time = System.currentTimeMillis() - list.get(0).getCreationTime();
                        if (time >= KeyConstan.FAILURE_TIME) {
                            issueRelatedRepository.delete(list);
                        }
                    } catch (Exception e) {
                        log.info("删除过期数据出错:", e.getMessage());
                    }
                }
            } else if (method.equalsIgnoreCase("SHARESTRUCTURE")) {
                List<ShareStructure> list = shareStructureRepository.findByCname(name);
                OpenEnum annotation = ShareStructureRepository.class.getAnnotation(OpenEnum.class);
                double price = annotation.price();
                String url = annotation.url();
                privateParams.put("price", price);
                privateParams.put("url", url);
                privateParams.put("method", method);
                threadLocal.set(privateParams);
                if (list.size() > 0) {
                    JSONObject inList = new JSONObject();
                    inList.put("dataList", list);
                    resultData.put("data", inList);
                    //当用户查询时，如果存储时间超过1个月就重新查询
                    try {
                        long time = System.currentTimeMillis() - list.get(0).getCreationTime();
                        if (time >= KeyConstan.FAILURE_TIME) {
                            shareStructureRepository.delete(list);
                        }
                    } catch (Exception e) {
                        log.info("删除过期数据出错:", e.getMessage());
                    }
                }
            } else if (method.equalsIgnoreCase("EQUITYCHANGE")) {
                List<EquityChange> list = equityChangeRepository.findByCname(name);
                OpenEnum annotation = EquityChangeRepository.class.getAnnotation(OpenEnum.class);
                double price = annotation.price();
                String url = annotation.url();
                privateParams.put("price", price);
                privateParams.put("url", url);
                privateParams.put("method", method);
                threadLocal.set(privateParams);
                if (list.size() > 0) {
                    JSONObject inList = new JSONObject();
                    inList.put("dataList", list);
                    resultData.put("data", inList);
                    //当用户查询时，如果存储时间超过1个月就重新查询
                    try {
                        long time = System.currentTimeMillis() - list.get(0).getCreationTime();
                        if (time >= KeyConstan.FAILURE_TIME) {
                            equityChangeRepository.delete(list);
                        }
                    } catch (Exception e) {
                        log.info("删除过期数据出错:", e.getMessage());
                    }
                }
            } else if (method.equalsIgnoreCase("BONUSINFO")) {
                List<BonusInfo> list = bonusInfoRepository.findByCname(name);
                OpenEnum annotation = BonusInfoRepository.class.getAnnotation(OpenEnum.class);
                double price = annotation.price();
                String url = annotation.url();
                privateParams.put("price", price);
                privateParams.put("url", url);
                privateParams.put("method", method);
                threadLocal.set(privateParams);
                if (list.size() > 0) {
                    JSONObject inList = new JSONObject();
                    inList.put("dataList", list);
                    resultData.put("data", inList);
                    //当用户查询时，如果存储时间超过1个月就重新查询
                    try {
                        long time = System.currentTimeMillis() - list.get(0).getCreationTime();
                        if (time >= KeyConstan.FAILURE_TIME) {
                            bonusInfoRepository.delete(list);
                        }
                    } catch (Exception e) {
                        log.info("删除过期数据出错:", e.getMessage());
                    }
                }
            } else if (method.equalsIgnoreCase("ALLOTMEN")) {
                List<Allotmen> list = allotmenRepository.findByCname(name);
                OpenEnum annotation = AllotmenRepository.class.getAnnotation(OpenEnum.class);
                double price = annotation.price();
                String url = annotation.url();
                privateParams.put("price", price);
                privateParams.put("url", url);
                privateParams.put("method", method);
                threadLocal.set(privateParams);
                if (list.size() > 0) {
                    JSONObject inList = new JSONObject();
                    inList.put("dataList", list);
                    resultData.put("data", inList);
                    //当用户查询时，如果存储时间超过1个月就重新查询
                    try {
                        long time = System.currentTimeMillis() - list.get(0).getCreationTime();
                        if (time >= KeyConstan.FAILURE_TIME) {
                            allotmenRepository.delete(list);
                        }
                    } catch (Exception e) {
                        log.info("删除过期数据出错:", e.getMessage());
                    }
                }
            } else if (method.equalsIgnoreCase("RiskInfo")) {
                List<RiskInfo> list = riskInfoRepository.findByCname(name);
                OpenEnum annotation = RiskInfoRepository.class.getAnnotation(OpenEnum.class);
                double price = annotation.price();
                String url = annotation.url();
                List<RiskInfo> internal = null;
                List<RiskInfo> external = null;
                privateParams.put("price", price);
                privateParams.put("url", url);
                privateParams.put("method", method);
                threadLocal.set(privateParams);
                if (list.size() > 0) {
                    internal = list.stream().filter((riskInfo -> riskInfo.getRiskType().equals("internalList"))).collect(Collectors.toList());
                    external = list.stream().filter((riskInfo -> riskInfo.getRiskType().equals("externalList"))).collect(Collectors.toList());
                    JSONObject inList = new JSONObject();
                    inList.put("internalList", internal);
                    inList.put("externalList", external);
                    resultData.put("data", inList);
                    //当用户查询时，如果存储时间超过1个月就重新查询
                    try {
                        long time = System.currentTimeMillis() - list.get(0).getCreationTime();
                        if (time >= KeyConstan.FAILURE_TIME) {
                            riskInfoRepository.delete(list);
                        }
                    } catch (Exception e) {
                        log.info("删除过期数据出错:", e.getMessage());
                    }
                }
            } else if (method.equalsIgnoreCase("RiskDetail")) {
                String id = params.get("id");
                List<RiskDetail> list = riskDetailRepository.findBySearchId(Long.parseLong(id));
                OpenEnum annotation = RiskDetailRepository.class.getAnnotation(OpenEnum.class);
                double price = annotation.price();
                String url = annotation.url();
                privateParams.put("price", price);
                privateParams.put("url", url);
                privateParams.put("method", method);
                threadLocal.set(privateParams);
                if (list.size() > 0) {
                    JSONObject inList = new JSONObject();
                    inList.put("dataList", list);
                    resultData.put("result", inList);
                    //当用户查询时，如果存储时间超过1个月就重新查询
                    try {
                        long time = System.currentTimeMillis() - list.get(0).getCreationTime();
                        if (time >= KeyConstan.FAILURE_TIME) {
                            riskDetailRepository.delete(list);
                        }
                    } catch (Exception e) {
                        log.info("删除过期数据出错:", e.getMessage());
                    }
                }
            } else if (method.equalsIgnoreCase("HumanRiskInfo")) {
                String humanName = params.get("humanName");
                List<HumanRiskInfo> list = humanRiskInfoRepository.findByCompanyNameAndHumanName(name, humanName);
                OpenEnum annotation = HumanRiskInfoRepository.class.getAnnotation(OpenEnum.class);
                double price = annotation.price();
                String url = annotation.url();
                privateParams.put("price", price);
                privateParams.put("url", url);
                privateParams.put("method", method);
                threadLocal.set(privateParams);
                if (list.size() > 0) {
                    JSONObject inList = new JSONObject();
                    inList.put("externalList", list);
                    resultData.put("data", inList);
                    //当用户查询时，如果存储时间超过1个月就重新查询
                    try {
                        long time = System.currentTimeMillis() - list.get(0).getCreationTime();
                        if (time >= KeyConstan.FAILURE_TIME) {
                            humanRiskInfoRepository.delete(list);
                        }
                    } catch (Exception e) {
                        log.info("删除过期数据出错:", e.getMessage());
                    }
                }
            } else if (method.equalsIgnoreCase("news")) {
                List<News> list = newsRepository.findByCompanyName(name);
                OpenEnum annotation = NewsRepository.class.getAnnotation(OpenEnum.class);
                double price = annotation.price();
                String url = annotation.url();
                privateParams.put("price", price);
                privateParams.put("url", url);
                privateParams.put("method", method);
                threadLocal.set(privateParams);
                if (list.size() > 0) {
                    resultData.put("result", list);
                    //当用户查询时，如果存储时间超过1个月就重新查询
                    try {
                        long time = System.currentTimeMillis() - list.get(0).getCreationTime();
                        if (time >= KeyConstan.FAILURE_TIME) {
                            newsRepository.delete(list);
                        }
                    } catch (Exception e) {
                        log.info("删除过期数据出错:", e.getMessage());
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }
        log.info("查询本地数据resultData:" + resultData);
        return resultData;

    }

    @Autowired
    private BaseInfoRepository baseInfoRepository;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private HistoryRongZiRepository historyRongZiRepository;
    @Autowired
    private TeamMemberRepository teamMemberRepository;
    @Autowired
    private ProductInfoRepository productInfoRepository;
    @Autowired
    private TouZiRepository touZiRepository;
    @Autowired
    private JingPinRepository jingPinRepository;
    @Autowired
    private ShangBiaoRepository shangBiaoRepository;
    @Autowired
    private PatentsRepository patentsRepository;
    @Autowired
    private CopyRegRepository copyRegRepository;
    @Autowired
    private IcpRepository icpRepository;
    @Autowired
    private AbnormalRepository abnormalRepository;
    @Autowired
    private PunishmentInfoRepository punishmentInfoRepository;
    @Autowired
    private IllegalinfoRepository illegalinfoRepository;
    @Autowired
    private OwnTaxRepository ownTaxRepository;
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private DishonestRepository dishonestRepository;
    @Autowired
    private RiskInfoRepository riskInfoRepository;
    @Autowired
    private HumanRiskInfoRepository humanRiskInfoRepository;
    @Autowired
    private RiskDetailRepository riskDetailRepository;
    @Autowired
    private TaxCreditRepository taxCreditRepository;
    //	@Autowired
//	private SearchRepository searchRepository;
    @Autowired
    private HolderRepository holderRepository;
    @Autowired
    private ChangeInfoRepository changeInfoRepository;
    @Autowired
    private InverstRepository inverstRepository;
    @Autowired
    private BidsRepository bidsRepository;
    @Autowired
    private BondRepository bondRepository;
    @Autowired
    private PurchaselandRepository purchaselandRepository;
    @Autowired
    private EmploymentRepository employmentRepository;
    @Autowired
    private CheckInfoRepository checkInfoRepository;
    @Autowired
    private AppbkInfoRepository appbkInfoRepository;
    @Autowired
    private LawSuitRepository lawSuitRepository;
    @Autowired
    private CourtAnnouncementRepository courtAnnouncementRepository;
    @Autowired
    private ZhixingInfoRepository zhixingInfoRepository;
    @Autowired
    private VolatilityRepository volatilityRepository;
    @Autowired
    private CompanyInfoRepository companyInfoRepository;
    @Autowired
    private SeniorExecutiveRepository seniorExecutiveRepository;
    @Autowired
    private HoldingCompanyRepository holdingCompanyRepository;
    @Autowired
    private AnnouncementRepository announcementRepository;
    @Autowired
    private ShareHolderRepository shareHolderRepository;
    @Autowired
    private IssueRelatedRepository issueRelatedRepository;
    @Autowired
    private ShareStructureRepository shareStructureRepository;
    @Autowired
    private EquityChangeRepository equityChangeRepository;
    @Autowired
    private BonusInfoRepository bonusInfoRepository;
    @Autowired
    private AllotmenRepository allotmenRepository;
    @Autowired
    private CertificateRepository certificateRepository;
    @Autowired
    private SearchCountRepository searchCountRepository;
}
