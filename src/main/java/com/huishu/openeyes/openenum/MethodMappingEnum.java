package com.huishu.openeyes.openenum;

/**
 * @author ydw
 * Created on 2018/5/17
 */
public enum MethodMappingEnum {

    经营异常(MethodMappingEnum.ABNORMAL, "com.huishu.openeyes.service.strategy.AllotmenStrategy"),
    配股情况(MethodMappingEnum.ALLOTMEN, "com.huishu.openeyes.service.strategy.AllotmenStrategy"),
    上市公告(MethodMappingEnum.ANNOUNCEMENT, "com.huishu.openeyes.service.strategy.AnnouncementStrategy"),
    产品信息(MethodMappingEnum.APPBKINFO, "com.huishu.openeyes.service.strategy.AppbkInfoStrategy"),
    基本信息(MethodMappingEnum.BASEINFOV2, "com.huishu.openeyes.service.strategy.BaseInfoStrategy"),
    招投标(MethodMappingEnum.BIDS, "com.huishu.openeyes.service.strategy.BidsStrategy"),
    债券信息(MethodMappingEnum.BOND, "com.huishu.openeyes.service.strategy.BondStrategy"),
    分红情况(MethodMappingEnum.BONUSINFO, "com.huishu.openeyes.service.strategy.BonusInfoStrategy"),
    分支机构(MethodMappingEnum.BRANCH, "com.huishu.openeyes.service.strategy.BranchStrategy"),
    资质证书(MethodMappingEnum.CERTIFICATE, "com.huishu.openeyes.service.strategy.CertificateStrategy"),
    变更记录(MethodMappingEnum.CHANGEINFO, "com.huishu.openeyes.service.strategy.ChangeinfoStrategy"),
    抽查检查(MethodMappingEnum.CHECKINFO, "com.huishu.openeyes.service.strategy.CheckInfoStrategy"),
    企业简介(MethodMappingEnum.COMPANYINFO, "com.huishu.openeyes.service.strategy.CompanyInfoStrategy"),
    著作权(MethodMappingEnum.COPYREG, "com.huishu.openeyes.service.strategy.CopyRegStrategy"),
    法院公告(MethodMappingEnum.COURTANNOUNCEMENT, "com.huishu.openeyes.service.strategy.CourtAnnouncementStrategy"),
    失信人(MethodMappingEnum.DISHONEST, "com.huishu.openeyes.service.strategy.DishonestStrategy"),
    招聘信息(MethodMappingEnum.EMPLOYMENTS, "com.huishu.openeyes.service.strategy.EmploymentsStrategy"),
    股本变动(MethodMappingEnum.EQUITYCHANGE, "com.huishu.openeyes.service.strategy.EquityChangeStrategy"),
    融资历史(MethodMappingEnum.FINDHISTORYRONGZI, "com.huishu.openeyes.service.strategy.FindHistoryRongziStrategy"),
    竞品信息(MethodMappingEnum.FINDJINGPIN, "com.huishu.openeyes.service.strategy.FindJingpinStrategy"),
    核心团队(MethodMappingEnum.FINDTEAMMEMBER, "com.huishu.openeyes.service.strategy.FindTeamMember"),
    投资案例(MethodMappingEnum.FINDTZANLI, "com.huishu.openeyes.service.strategy.FindTzanliStrategy"),
    企业业务(MethodMappingEnum.GETPRODUCTINFO, "com.huishu.openeyes.service.strategy.GetProductInfoStrategy"),
    股东信息(MethodMappingEnum.HOLDER, "com.huishu.openeyes.service.strategy.HolderStrategy"),
    参股控股(MethodMappingEnum.HOLDINGCOMPANY, "com.huishu.openeyes.service.strategy.HoldingCompanyStrategy"),
    人风险(MethodMappingEnum.HUMANRISKINFO, "com.huishu.openeyes.service.strategy.HumanRiskInfoStrategy"),
    网站备案(MethodMappingEnum.ICP, "com.huishu.openeyes.service.strategy.IcpStrategy"),
    严重违法(MethodMappingEnum.ILLEGALINFO, "com.huishu.openeyes.service.strategy.IllegalinfoStrategy"),
    对外投资(MethodMappingEnum.INVERST, "com.huishu.openeyes.service.strategy.InverstStrategy"),
    发行相关(MethodMappingEnum.ISSUERELATED, "com.huishu.openeyes.service.strategy.IssueRelatedStrategy"),
    法律诉讼(MethodMappingEnum.LAWSUIT, "com.huishu.openeyes.service.strategy.LawSuitStrategy"),
    新闻(MethodMappingEnum.NEWS, "com.huishu.openeyes.service.strategy.NewsStrategy"),
    欠税公告(MethodMappingEnum.OWNTAX, "com.huishu.openeyes.service.strategy.OwnTaxStrategy"),
    专利(MethodMappingEnum.PATENTS, "com.huishu.openeyes.service.strategy.PatentsStrategy"),
    行政处罚(MethodMappingEnum.PUNISHMENTINFO, "com.huishu.openeyes.service.strategy.PunishmentInfoStrategy"),
    购地信息(MethodMappingEnum.PURCHASELAND, "com.huishu.openeyes.service.strategy.PurchaseLandStrategy"),
    风险信息(MethodMappingEnum.RISKDETAIL, "com.huishu.openeyes.service.strategy.RiskDetailStrategy"),
    企业风险(MethodMappingEnum.RISKINFO, "com.huishu.openeyes.service.strategy.RiskInfoStrategy"),
    高管信息(MethodMappingEnum.SENIOREXECUTIVE, "com.huishu.openeyes.service.strategy.SeniorExecutiveStrategy"),
    十大股东(MethodMappingEnum.SHAREHOLDER, "com.huishu.openeyes.service.strategy.ShareholderStrategy"),
    股本结构(MethodMappingEnum.SHARESTRUCTURE, "com.huishu.openeyes.service.strategy.ShareStructureStrategy"),
    主要人员(MethodMappingEnum.STAFF, "com.huishu.openeyes.service.strategy.StaffStrategy"),
    税务评级(MethodMappingEnum.TAXCREDIT, "com.huishu.openeyes.service.strategy.TaxCreditStrategy"),
    商标信息(MethodMappingEnum.TM, "com.huishu.openeyes.service.strategy.TmStrategy"),
    股票行情(MethodMappingEnum.VOLATILITY, "com.huishu.openeyes.service.strategy.VolatilityStrategy"),
    被执行人(MethodMappingEnum.ZHIXINGINFO, "com.huishu.openeyes.service.strategy.ZhixinginfoStrategy");

   
    private String methodName;
    private String className;

    public static final String ABNORMAL = "abnormal";
    public static final String ALLOTMEN = "allotmen";
    public static final String BASEINFOV2 = "baseinfoV2";
    public static final String ANNOUNCEMENT = "announcement";
    public static final String APPBKINFO = "appbkInfo";
    public static final String BIDS = "bids";
    public static final String BOND = "bond";
    public static final String BONUSINFO = "bonusInfo";
    public static final String BRANCH = "branch";
    public static final String CERTIFICATE = "certificate";
    public static final String CHANGEINFO = "changeinfo";
    public static final String CHECKINFO = "checkInfo";
    public static final String COMPANYINFO = "companyInfo";
    public static final String COPYREG = "copyReg";
    public static final String COURTANNOUNCEMENT = "courtAnnouncement";
    public static final String DISHONEST = "dishonest";
    public static final String EMPLOYMENTS = "employments";
    public static final String EQUITYCHANGE = "equityChange";
    public static final String FINDHISTORYRONGZI = "findHistoryRongzi";
    public static final String HOLDER = "holder";
    public static final String HOLDINGCOMPANY = "holdingCompany";
    public static final String HUMANRISKINFO = "humanRiskInfo";
    public static final String ICP = "icp";
    public static final String ILLEGALINFO = "illegalinfo";
    public static final String INVERST = "inverst";
    public static final String ISSUERELATED = "issueRelated";
    public static final String FINDJINGPIN = "findJingpin";
    public static final String LAWSUIT = "lawSuit";
    public static final String NEWS = "news";
    public static final String OWNTAX = "ownTax";
    public static final String PATENTS = "patents";
    public static final String GETPRODUCTINFO = "getProductInfo";
    public static final String PUNISHMENTINFO = "punishmentInfo";
    public static final String PURCHASELAND = "purchaseLand";
    public static final String RISKDETAIL = "riskDetail";
    public static final String RISKINFO = "riskInfo";
    public static final String SENIOREXECUTIVE = "seniorExecutive";
    public static final String TM = "tm";
    public static final String SHAREHOLDER = "shareholder";
    public static final String SHARESTRUCTURE = "shareStructure";
    public static final String STAFF = "staff";
    public static final String TAXCREDIT = "taxCredit";
    public static final String FINDTEAMMEMBER = "findTeamMember";
    public static final String FINDTZANLI = "findTzanli";
    public static final String VOLATILITY = "volatility";
    public static final String ZHIXINGINFO = "zhixinginfo";

    MethodMappingEnum(String methodName, String className) {
        this.methodName = methodName;
        this.className = className;
    }

    public static Class<?> getServiceClass(String methodName) {
        MethodMappingEnum[] values = MethodMappingEnum.values();
        for (int i = 0; i < values.length; i++) {
            MethodMappingEnum value = values[i];
            if (value.methodName.equals(methodName)) {
                try {
                    return Class.forName(value.className);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
