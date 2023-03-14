package com.wong.domain;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.wong.annotations.Excel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Transient;

import java.util.*;
import java.io.Serializable;

/**
 * 体检表(SearchInfo)实体类
 *
 * @author makejava
 * @since 2023-02-25 12:42:14
 */
public class SearchInfo implements Serializable {
    private static final long serialVersionUID = 853641479647680512L;

    private static final Logger logger = LoggerFactory.getLogger(SearchInfo.class);

    public SearchInfo() {
    }

    /**
     * 主键
     */
    private Integer id;
    /**
     * 姓名
     */
    @Excel(name = "姓名")
    private String name;
    /**
     * 身份证
     */
    @Excel(name = "身份证")
    private String idnumber;
    /**
     * 门诊
     */
    @Excel(name = "门诊")
    private String cardid;
    /**
     * 性别
     */
    @Excel(name = "性别")
    private String sex;
    /**
     * 年龄
     */
    @Excel(name = "年龄")
    private String age;
    /**
     * 入组时间
     */
    @Excel(name = "入组时间",dateFormat="yyyy/MM/dd")
    private Date intime;
    @Excel(name = "血常规检验时间",dateFormat="yyyy/MM/dd")
    private Date xcgtime;
    /**
     * 白细胞;973
     */
    @Excel(name = "白细胞")
    private String xcgBxb;
    /**
     * 中性细胞比率;974
     */
    @Excel(name = "中性细胞比率")
    private String xcgZxbl;
    /**
     * 中性细胞数;975
     */
    @Excel(name = "中性细胞数")
    private String xcgZx;
    /**
     * 单核细胞比率;976
     */
    @Excel(name = "单核细胞比率")
    private String xcgDhbl;
    /**
     * 单核细胞数;977
     */
    @Excel(name = "单核细胞数")
    private String xcgDh;
    /**
     * 淋巴细胞比率;978
     */
    @Excel(name = "淋巴细胞比率")
    private String xcgLbbl;
    /**
     * 淋巴细胞数;979
     */
    @Excel(name = "淋巴细胞数")
    private String xcgLb;
    /**
     * 嗜酸性粒细胞比率;980
     */
    @Excel(name = "嗜酸性粒细胞比率")
    private String xcgSsxlbl;
    /**
     * 嗜酸性粒细胞数;981
     */
    @Excel(name = "嗜酸性粒细胞数")
    private String xcgSsxl;
    /**
     * 嗜碱性粒细胞比率;982
     */
    @Excel(name = "嗜碱性粒细胞比率")
    private String xcgSjxlbl;
    /**
     * 嗜碱性粒细胞数;983
     */
    @Excel(name = "嗜碱性粒细胞数")
    private String xcgSjxl;
    /**
     * 红细胞;984
     */
    @Excel(name = "红细胞")
    private String xcgHxb;
    /**
     * 血红蛋白;985
     */
    @Excel(name = "血红蛋白")
    private String xcgXhdb;
    /**
     * 红细胞压积;986
     */
    @Excel(name = "红细胞压积")
    private String xcgHxbyj;
    /**
     * 红细胞平均体积;987
     */
    @Excel(name = "红细胞平均体积")
    private String xcgHxbtj;
    /**
     * 平均血红蛋白量;988
     */
    @Excel(name = "平均血红蛋白量")
    private String xcgPjxh;
    /**
     * 平均血红蛋白浓度;989
     */
    @Excel(name = "平均血红蛋白浓度")
    private String xcgPjxhnd;
    /**
     * RBC分布宽度SD;990
     */
    @Excel(name = "RBC分布宽度SD")
    private String xcgRbcsd;
    /**
     * RBC分布宽度CV;991
     */
    @Excel(name = "RBC分布宽度CV")
    private String xcgRbccv;
    /**
     * 血小板;992
     */
    @Excel(name = "血小板")
    private String xcgXxb;
    /**
     * 平均血小板体积;993
     */
    @Excel(name = "平均血小板体积")
    private String xcgXxbtj;
    /**
     * 血小板压积;994
     */
    @Excel(name = "血小板压积")
    private String xcgXxbyj;
    /**
     * 血小板分布宽度;995
     */
    @Excel(name = "血小板分布宽度")
    private String xcgXxbkd;
    /**
     * 大型血小板比率;996
     */
    @Excel(name = "大型血小板比率")
    private String xcgXxbdxbl;

    @Excel(name = "乙肝两对半检验时间",dateFormat="yyyy/MM/dd")
    private Date ygtime;
    /**
     * 乙肝表面抗原测定(定量);1033
     */
    @Excel(name = "乙肝表面抗原测定(定量)")
    private String ygBmky;
    /**
     * 乙肝表面抗体测定(定量);1034
     */
    @Excel(name = "乙肝表面抗体测定(定量)")
    private String ygBmkt;
    /**
     * 乙肝e抗原测定(定量);1035
     */
    @Excel(name = "乙肝e抗原测定(定量)")
    private String ygEky;
    /**
     * 乙肝e抗体测定(定量);1036
     */
    @Excel(name = "乙肝e抗体测定(定量)")
    private String ygEkt;
    /**
     * 乙肝核心抗体测定;1037
     */
    @Excel(name = "乙肝核心抗体测定")
    private String ygHxky;
    @Excel(name = "尿常规检验时间",dateFormat="yyyy/MM/dd")
    private Date ncgtime;
    /**
     * 尿胆原;893
     */
    @Excel(name = "尿胆原")
    private String ncgNdy;
    /**
     * 胆红素;894
     */
    @Excel(name = "胆红素")
    private String ncgDhs;
    /**
     * 酮体;895
     */
    @Excel(name = "酮体")
    private String ncgTt;
    /**
     * 隐血;896
     */
    @Excel(name = "隐血")
    private String ncgYx;
    /**
     * 蛋白质;897
     */
    @Excel(name = "蛋白质")
    private String ncgDbz;
    /**
     * 亚硝酸盐;898
     */
    @Excel(name = "亚硝酸盐")
    private String ncgYxsy;
    /**
     * 白细胞;899
     */
    @Excel(name = "白细胞")
    private String ncgBxb;
    /**
     * 葡萄糖;900
     */
    @Excel(name = "葡萄糖")
    private String ncgPpt;
    /**
     * 比重;901
     */
    @Excel(name = "比重")
    private String ncgBz;
    /**
     * pH值;902
     */
    @Excel(name = "pH值")
    private String ncgPh;
    @Excel(name = "维生素C")
    private String ncgWss;
    /**
     * 微白蛋白;904
     */
    @Excel(name = "微白蛋白")
    private String ncgWbdb;
    /**
     * 颜色;905
     */
    @Excel(name = "颜色")
    private String ncgYs;
    /**
     * 透明度;906
     */
    @Excel(name = "透明度")
    private String ncgTmd;
    /**
     * 红细胞定量;907
     */
    @Excel(name = "红细胞定量")
    private String ncgHxbdl;
    /**
     * 白细胞定量;908
     */
    @Excel(name = "白细胞定量")
    private String ncgBxbdl;
    /**
     * 管型;909
     */
    @Excel(name = "管型")
    private String ncgGx;
    /**
     * 盐类结晶;910
     */
    @Excel(name = "盐类结晶")
    private String ncgYljj;
    /**
     * 上皮细胞;911
     */
    @Excel(name = "上皮细胞")
    private String ncgSpxb;

    @Excel(name = "肾功能七项检验时间",dateFormat="yyyy/MM/dd")
    private Date sngtime;
    /**
     * 碳酸氢根(HCO3);1021
     */
    @Excel(name = "碳酸氢根(HCO3)")
    private String sgnTsqg;
    /**
     * 尿素测定;1022
     */
    @Excel(name = "尿素测定")
    private String sgnNs;
    /**
     * 肌酐测定;1023
     */
    @Excel(name = "肌酐测定")
    private String sgnJg;
    /**
     * 尿酸测定;1024
     */
    @Excel(name = "尿酸测定")
    private String sgnNsuan;
    /**
     * 葡萄糖测定;1025
     */
    @Excel(name = "葡萄糖测定")
    private String sgnPpt;
    /**
     * β2-微球蛋白测定(化学发光法);1627
     */
    @Excel(name = "β2-微球蛋白测定(化学发光法)")
    private String sgnalf;
    /**
     * 血清胱抑素(CystatinC)测定;1847
     */
    @Excel(name = "血清胱抑素(CystatinC)测定")
    private String sgnXqg;
    /**
     * 内生肌酐清除率试验;1947
     */
    @Excel(name = "内生肌酐清除率试验")
    private String sgnNsjg;
    @Excel(name = "血脂四项检验时间",dateFormat="yyyy/MM/dd")
    private Date xztime;
    /**
     * 总胆固醇;1026
     */
    @Excel(name = "总胆固醇")
    private String xzDgc;
    /**
     * 甘油三酯;1027
     */
    @Excel(name = "甘油三酯")
    private String xzGysz;
    /**
     * 高密度脂蛋白;1028
     */
    @Excel(name = "高密度脂蛋白")
    private String xzGmd;
    /**
     * 低密度脂蛋白;1029
     */
    @Excel(name = "低密度脂蛋白")
    private String xzDmd;

    @Excel(name = "凝血六项检验时间",dateFormat="yyyy/MM/dd")
    private Date nxltime;
    /**
     * 凝血酶原时间测定;887
     */
    @Excel(name = "凝血酶原时间测定")
    private String nxlNxmcd;
    /**
     * 活化部分凝血活酶时间测定;888
     */
    @Excel(name = "活化部分凝血活酶时间测定")
    private String nxlHh;
    /**
     * 纤维蛋白原测定;889
     */
    @Excel(name = "纤维蛋白原测定")
    private String nxlXwcd;
    /**
     * 凝血酶时间测定;890
     */
    @Excel(name = "凝血酶时间测定")
    private String nxlNxmsj;
    /**
     * 国际标准化比值;891
     */
    @Excel(name = "国际标准化比值")
    private String nxlBzh;
    /**
     * 凝血酶原时间比值;892
     */
    @Excel(name = "凝血酶原时间比值")
    private String nxlNxmbz;
    /**
     * D-二聚体测定;2547
     */
    @Excel(name = "D-二聚体测定")
    private String nxlEjt;
    /**
     * 纤维蛋白原降解产物检测;2548
     */
    @Excel(name = "纤维蛋白原降解产物检测")
    private String nxlXwjjw;
    @Excel(name = "HBV-DNA检验时间",dateFormat="yyyy/MM/dd")
    private Date hbvtime;
    /**
     * 高敏乙型肝炎病毒(HBV-DNA)定量;2387
     */
    @Excel(name = "高敏乙型肝炎病毒(HBV-DNA)定量")
    private String hbvDna;

    @Excel(name = "血糖检验时间",dateFormat="yyyy/MM/dd")
    private Date xttime;
    /**
     * 葡萄糖(空腹)测定(静脉血);910_1268
     */
    @Excel(name = "葡萄糖(空腹)测定(静脉血)")
    private String xtKf;
    /**
     * 葡萄糖(餐后2小时)(静脉血);907_1062
     */
    @Excel(name = "葡萄糖(餐后2小时)(静脉血)")
    private String xtCh;
    /**
     * 葡萄糖测定(静脉血);905_1025
     */
    @Excel(name = "葡萄糖测定(静脉血)")
    private String xtJmcd;
    /**
     * key: projectId_targetId
     * value: 属性名.
     * 使用contains方法找到key值，获取属性名利用反射进行赋值
     */
    @Transient
    static final Map<String, String> CACHE = new HashMap<>();
    @Transient
    private String projectId;
    @Transient
    private String targetId;
    @Transient
    private String result;
    @Transient
    private String projectName;

    private static final String XUE_CHANG_GUI ="538";   //血常规
    private static final String YI_GAN ="541";   //乙肝
    private static final String YI_GAN_1 ="4984";   //乙肝
    private static final String NIAO_CHANG_GUI ="458";   //尿常规
    private static final String SHEN_GONG_NENG ="3124";   //肾功能
    private static final String XUE_ZHI ="2305";   //血脂
    private static final String NINE_XUE_MEI ="3326";    //凝血
    private static final String GAO_MIN_HBV ="3224";    //高敏乙肝


    // private static final String XIN_DIAN_TU ="537";    //心电图
    // private static final String BI_CHAO ="1164";    //B超
    // private static final String BI_CHAO_2 ="658";    //B超腹部
    // private static final String BI_CHAO_3 ="1144";    //B超腹部乳腺
    // private static final String BI_CHAO_4 ="662";    //B超腹部+经阴道子宫附件
    private static final String XUE_QUAN_ZHI ="568";    //血脂肪酶淀粉酶测定(静脉血)

    private static final Set<String> EXCLULSION_PROJECT = new HashSet<>();  //不予处理的过滤集合
    private static final Set<String> EXCLULSION_PROJECT_target = new HashSet<>();  //不予处理的过滤集合

    private static final String SPLITOR = "_";

    /**
     * 静态代码块，初始化projectId_targetId字符串
     */
    static {

        EXCLULSION_PROJECT.add(XUE_QUAN_ZHI);
        EXCLULSION_PROJECT.add("708");
        EXCLULSION_PROJECT.add("3385"); //葡萄糖耐量试验(糖后2h)(血液)
        EXCLULSION_PROJECT.add("908"); //葡萄糖(餐后3小时)(静脉血)


        EXCLULSION_PROJECT_target.add("538_6411");
        EXCLULSION_PROJECT_target.add("458_2967");
        EXCLULSION_PROJECT_target.add("3584_2647");
        EXCLULSION_PROJECT_target.add("541_2747");
        EXCLULSION_PROJECT_target.add("541_6669");
        EXCLULSION_PROJECT_target.add("541_6670");
        EXCLULSION_PROJECT_target.add("541_6671");
        EXCLULSION_PROJECT_target.add("541_6672");
        EXCLULSION_PROJECT_target.add("906_1061");


        CACHE.put(XUE_CHANG_GUI + SPLITOR + "973", "xcgBxb");//白细胞
        CACHE.put(XUE_CHANG_GUI + SPLITOR + "974", "xcgZxbl");
        CACHE.put(XUE_CHANG_GUI + SPLITOR + "975", "xcgZx");
        CACHE.put(XUE_CHANG_GUI + SPLITOR + "976", "xcgDhbl");
        CACHE.put(XUE_CHANG_GUI + SPLITOR + "977", "xcgDh");
        CACHE.put(XUE_CHANG_GUI + SPLITOR + "978", "xcgLbbl");
        CACHE.put(XUE_CHANG_GUI + SPLITOR + "979", "xcgLb");
        CACHE.put(XUE_CHANG_GUI + SPLITOR + "980", "xcgSsxlbl");
        CACHE.put(XUE_CHANG_GUI + SPLITOR + "981", "xcgSsxl");
        CACHE.put(XUE_CHANG_GUI + SPLITOR + "982", "xcgSjxlbl");
        CACHE.put(XUE_CHANG_GUI + SPLITOR + "983", "xcgSjxl");
        CACHE.put(XUE_CHANG_GUI + SPLITOR + "984", "xcgHxb");
        CACHE.put(XUE_CHANG_GUI + SPLITOR + "985", "xcgXhdb");
        CACHE.put(XUE_CHANG_GUI + SPLITOR + "986", "xcgHxbyj");
        CACHE.put(XUE_CHANG_GUI + SPLITOR + "987", "xcgHxbtj");
        CACHE.put(XUE_CHANG_GUI + SPLITOR + "988", "xcgPjxh");
        CACHE.put(XUE_CHANG_GUI + SPLITOR + "989", "xcgPjxhnd");
        CACHE.put(XUE_CHANG_GUI + SPLITOR + "990", "xcgRbcsd");
        CACHE.put(XUE_CHANG_GUI + SPLITOR + "991", "xcgRbccv");
        CACHE.put(XUE_CHANG_GUI + SPLITOR + "992", "xcgXxb");
        CACHE.put(XUE_CHANG_GUI + SPLITOR + "993", "xcgXxbtj");
        CACHE.put(XUE_CHANG_GUI + SPLITOR + "994", "xcgXxbyj");
        CACHE.put(XUE_CHANG_GUI + SPLITOR + "995", "xcgXxbkd");
        CACHE.put(XUE_CHANG_GUI + SPLITOR + "996", "xcgXxbdxbl");

        CACHE.put(YI_GAN + SPLITOR + "1033", "ygBmky");
        CACHE.put(YI_GAN + SPLITOR + "1034", "ygBmkt");
        CACHE.put(YI_GAN + SPLITOR + "1035", "ygEky");
        CACHE.put(YI_GAN + SPLITOR + "1036", "ygEkt");
        CACHE.put(YI_GAN + SPLITOR + "1037", "ygHxky");

        CACHE.put(YI_GAN_1 + SPLITOR + "1407", "ygBmky");
        CACHE.put(YI_GAN_1 + SPLITOR + "1408", "ygBmkt");
        CACHE.put(YI_GAN_1 + SPLITOR + "1409", "ygEky");
        CACHE.put(YI_GAN_1 + SPLITOR + "1410", "ygEkt");
        CACHE.put(YI_GAN_1 + SPLITOR + "1411", "ygHxky");

        CACHE.put(NIAO_CHANG_GUI + SPLITOR + "893", "ncgNdy");
        CACHE.put(NIAO_CHANG_GUI + SPLITOR + "894", "ncgDhs");
        CACHE.put(NIAO_CHANG_GUI + SPLITOR + "895", "ncgTt");
        CACHE.put(NIAO_CHANG_GUI + SPLITOR + "896", "ncgYx");
        CACHE.put(NIAO_CHANG_GUI + SPLITOR + "897", "ncgDbz");
        CACHE.put(NIAO_CHANG_GUI + SPLITOR + "898", "ncgYxsy");
        CACHE.put(NIAO_CHANG_GUI + SPLITOR + "899", "ncgBxb");
        CACHE.put(NIAO_CHANG_GUI + SPLITOR + "900", "ncgPpt");
        CACHE.put(NIAO_CHANG_GUI + SPLITOR + "901", "ncgBz");
        CACHE.put(NIAO_CHANG_GUI + SPLITOR + "902", "ncgPh");
        CACHE.put(NIAO_CHANG_GUI + SPLITOR + "903", "ncgWss");
        CACHE.put(NIAO_CHANG_GUI + SPLITOR + "904", "ncgWbdb");
        CACHE.put(NIAO_CHANG_GUI + SPLITOR + "905", "ncgYs");
        CACHE.put(NIAO_CHANG_GUI + SPLITOR + "906", "ncgTmd");
        CACHE.put(NIAO_CHANG_GUI + SPLITOR + "907", "ncgHxbdl");
        CACHE.put(NIAO_CHANG_GUI + SPLITOR + "908", "ncgBxbdl");
        CACHE.put(NIAO_CHANG_GUI + SPLITOR + "909", "ncgGx");
        CACHE.put(NIAO_CHANG_GUI + SPLITOR + "910", "ncgYljj");
        CACHE.put(NIAO_CHANG_GUI + SPLITOR + "911", "ncgSpxb");

        CACHE.put(SHEN_GONG_NENG + SPLITOR + "1021", "sgnTsqg");
        CACHE.put(SHEN_GONG_NENG + SPLITOR + "1022", "sgnNs");
        CACHE.put(SHEN_GONG_NENG + SPLITOR + "1023", "sgnJg");
        CACHE.put(SHEN_GONG_NENG + SPLITOR + "1024", "sgnNsuan");
        CACHE.put(SHEN_GONG_NENG + SPLITOR + "1025", "sgnPpt");
        CACHE.put(SHEN_GONG_NENG + SPLITOR + "1847", "sgnXqg");
        CACHE.put(SHEN_GONG_NENG + SPLITOR + "1947", "sgnNsjg");
        CACHE.put(SHEN_GONG_NENG + SPLITOR + "1627", "sgnalf");

        CACHE.put(XUE_ZHI + SPLITOR + "1026", "xzDgc");
        CACHE.put(XUE_ZHI + SPLITOR + "1027", "xzGysz");
        CACHE.put(XUE_ZHI + SPLITOR + "1028", "xzGmd");
        CACHE.put(XUE_ZHI + SPLITOR + "1029", "xzDmd");

        CACHE.put(NINE_XUE_MEI + SPLITOR + "887", "nxlNxmcd");
        CACHE.put(NINE_XUE_MEI + SPLITOR + "888", "nxlHh");
        CACHE.put(NINE_XUE_MEI + SPLITOR + "889", "nxlXwcd");
        CACHE.put(NINE_XUE_MEI + SPLITOR + "890", "nxlNxmsj");
        CACHE.put(NINE_XUE_MEI + SPLITOR + "891", "nxlBzh");
        CACHE.put(NINE_XUE_MEI + SPLITOR + "892", "nxlNxmbz");
        CACHE.put(NINE_XUE_MEI + SPLITOR + "2547", "nxlEjt");
        CACHE.put(NINE_XUE_MEI + SPLITOR + "2548", "nxlXwjjw");

        CACHE.put(GAO_MIN_HBV + SPLITOR + "2387", "hbvDna");

        CACHE.put("910_1268", "xtKf");
        CACHE.put("907_1062", "xtCh");
        CACHE.put("905_1025", "xtJmcd");



    }


    public SearchInfo(ImportDomain e, String originalFilename) {
        this.name = e.getName().trim();
        this.cardid = e.getMzh().trim();
        this.sex = e.getSex().trim();
        if (e.getAge().trim().contains("岁")) {
            this.age = StrUtil.subBefore(e.getAge().trim(), "岁", false);
        } else {
            this.age = e.getAge().trim();
        }
        if (StrUtil.isBlankIfStr(this.age)) {
            throw new RuntimeException("构造时年龄为空,构造后:" + this + "构造前数据:" + e);
        }
        // this.age = e.getAge();
        //入群时间以文件名那个月的天为准
        //TODO 这里对上传的文件名进行一定的格式要求，因为文件本身数量不大，因此这个步骤需要手动操作
        try {
            this.intime = DateTime.of(StrUtil.subBefore(originalFilename, "(", false), "yyyy-MM-dd");
        } catch (Exception ex) {
            throw new RuntimeException("构造时间失败:" + originalFilename);
        }
        this.projectId = e.getProjectId().trim();
        this.targetId = e.getTargetId().trim();
        this.projectName = e.getProjectName().trim();
        this.result = e.getResult().trim();
            //面试题：如果在构造方法调用期间，对实体值进行改动，会发生什么？ 答：可能发生引用逃逸，必须要业务逻辑事实上实现线程安全
            //面试题2：new一个对象，经历了哪些步骤？ 答:类加载，分配内存，初始化，设置对象头，执行init方法.
            //面试题3：类加载步骤？ 答:
            //利用反射，通过缓存注入字段
            // Field targetFiled = this.getClass().getDeclaredField(result);   //需要注入的属性值
            // ReflectUtil.setFieldValue(this, result, e.getResult());
            // targetFiled.setAccessible(true);
            // targetFiled.set(this, e.getResult());

    }

    public void setValue() {
        //接下来是处理项目指标和结论.
        //初步设想是结合到projectId和targetId做符号拼凑
        //因为已经做了非空校验.
        String targetSearch = this.projectId.trim() + SPLITOR + this.targetId.trim();
        String result = CACHE.getOrDefault(targetSearch, "");
        if (StrUtil.isBlankIfStr(result)) {
            throw new RuntimeException("不存在这样的key[" + this.toString() + "]");
        }
        ReflectUtil.setFieldValue(this, result, this.result);

    }

    public String toString() {
        return "{name:" + name + ",sex:" + sex + ",age:" + age + ",cardid:" + cardid + ",intime:" + intime + "}";
    }

    // @Override
    // public String toString() {
    //     return "SearchInfo{" +
    //             "name='" + name + '\'' +
    //             ", cardid='" + cardid + '\'' +
    //             ", sex='" + sex + '\'' +
    //             ", age='" + age + '\'' +
    //             ", projectId='" + projectId + '\'' +
    //             ", targetId='" + targetId + '\'' +
    //             ", result='" + result + '\'' +
    //             '}';
    // }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public Date getIntime() {
        return intime;
    }

    public void setIntime(Date intime) {
        this.intime = intime;
    }

    public String getXcgBxb() {
        return xcgBxb;
    }

    public void setXcgBxb(String xcgBxb) {
        this.xcgBxb = xcgBxb;
    }

    public String getXcgZxbl() {
        return xcgZxbl;
    }

    public void setXcgZxbl(String xcgZxbl) {
        this.xcgZxbl = xcgZxbl;
    }

    public String getXcgZx() {
        return xcgZx;
    }

    public void setXcgZx(String xcgZx) {
        this.xcgZx = xcgZx;
    }

    public String getXcgDhbl() {
        return xcgDhbl;
    }

    public void setXcgDhbl(String xcgDhbl) {
        this.xcgDhbl = xcgDhbl;
    }

    public String getXcgDh() {
        return xcgDh;
    }

    public void setXcgDh(String xcgDh) {
        this.xcgDh = xcgDh;
    }

    public String getXcgLbbl() {
        return xcgLbbl;
    }

    public void setXcgLbbl(String xcgLbbl) {
        this.xcgLbbl = xcgLbbl;
    }

    public String getXcgLb() {
        return xcgLb;
    }

    public void setXcgLb(String xcgLb) {
        this.xcgLb = xcgLb;
    }

    public String getXcgSsxlbl() {
        return xcgSsxlbl;
    }

    public void setXcgSsxlbl(String xcgSsxlbl) {
        this.xcgSsxlbl = xcgSsxlbl;
    }

    public String getXcgSsxl() {
        return xcgSsxl;
    }

    public void setXcgSsxl(String xcgSsxl) {
        this.xcgSsxl = xcgSsxl;
    }

    public String getXcgSjxlbl() {
        return xcgSjxlbl;
    }

    public void setXcgSjxlbl(String xcgSjxlbl) {
        this.xcgSjxlbl = xcgSjxlbl;
    }

    public String getXcgSjxl() {
        return xcgSjxl;
    }

    public void setXcgSjxl(String xcgSjxl) {
        this.xcgSjxl = xcgSjxl;
    }

    public String getXcgHxb() {
        return xcgHxb;
    }

    public void setXcgHxb(String xcgHxb) {
        this.xcgHxb = xcgHxb;
    }

    public String getXcgXhdb() {
        return xcgXhdb;
    }

    public void setXcgXhdb(String xcgXhdb) {
        this.xcgXhdb = xcgXhdb;
    }

    public String getXcgHxbyj() {
        return xcgHxbyj;
    }

    public void setXcgHxbyj(String xcgHxbyj) {
        this.xcgHxbyj = xcgHxbyj;
    }

    public String getXcgHxbtj() {
        return xcgHxbtj;
    }

    public void setXcgHxbtj(String xcgHxbtj) {
        this.xcgHxbtj = xcgHxbtj;
    }

    public String getXcgPjxh() {
        return xcgPjxh;
    }

    public void setXcgPjxh(String xcgPjxh) {
        this.xcgPjxh = xcgPjxh;
    }

    public String getXcgPjxhnd() {
        return xcgPjxhnd;
    }

    public void setXcgPjxhnd(String xcgPjxhnd) {
        this.xcgPjxhnd = xcgPjxhnd;
    }

    public String getXcgRbcsd() {
        return xcgRbcsd;
    }

    public void setXcgRbcsd(String xcgRbcsd) {
        this.xcgRbcsd = xcgRbcsd;
    }

    public String getXcgRbccv() {
        return xcgRbccv;
    }

    public void setXcgRbccv(String xcgRbccv) {
        this.xcgRbccv = xcgRbccv;
    }

    public String getXcgXxb() {
        return xcgXxb;
    }

    public void setXcgXxb(String xcgXxb) {
        this.xcgXxb = xcgXxb;
    }

    public String getXcgXxbtj() {
        return xcgXxbtj;
    }

    public void setXcgXxbtj(String xcgXxbtj) {
        this.xcgXxbtj = xcgXxbtj;
    }

    public String getXcgXxbyj() {
        return xcgXxbyj;
    }

    public void setXcgXxbyj(String xcgXxbyj) {
        this.xcgXxbyj = xcgXxbyj;
    }

    public String getXcgXxbkd() {
        return xcgXxbkd;
    }

    public void setXcgXxbkd(String xcgXxbkd) {
        this.xcgXxbkd = xcgXxbkd;
    }

    public String getXcgXxbdxbl() {
        return xcgXxbdxbl;
    }

    public void setXcgXxbdxbl(String xcgXxbdxbl) {
        this.xcgXxbdxbl = xcgXxbdxbl;
    }

    public String getYgBmky() {
        return ygBmky;
    }

    public void setYgBmky(String ygBmky) {
        this.ygBmky = ygBmky;
    }

    public String getYgBmkt() {
        return ygBmkt;
    }

    public void setYgBmkt(String ygBmkt) {
        this.ygBmkt = ygBmkt;
    }

    public String getYgEky() {
        return ygEky;
    }

    public void setYgEky(String ygEky) {
        this.ygEky = ygEky;
    }

    public String getYgEkt() {
        return ygEkt;
    }

    public void setYgEkt(String ygEkt) {
        this.ygEkt = ygEkt;
    }

    public String getYgHxky() {
        return ygHxky;
    }

    public void setYgHxky(String ygHxky) {
        this.ygHxky = ygHxky;
    }

    public String getNcgNdy() {
        return ncgNdy;
    }

    public void setNcgNdy(String ncgNdy) {
        this.ncgNdy = ncgNdy;
    }

    public String getNcgDhs() {
        return ncgDhs;
    }

    public void setNcgDhs(String ncgDhs) {
        this.ncgDhs = ncgDhs;
    }

    public String getNcgTt() {
        return ncgTt;
    }

    public void setNcgTt(String ncgTt) {
        this.ncgTt = ncgTt;
    }

    public String getNcgYx() {
        return ncgYx;
    }

    public void setNcgYx(String ncgYx) {
        this.ncgYx = ncgYx;
    }

    public String getNcgDbz() {
        return ncgDbz;
    }

    public void setNcgDbz(String ncgDbz) {
        this.ncgDbz = ncgDbz;
    }

    public String getNcgYxsy() {
        return ncgYxsy;
    }

    public void setNcgYxsy(String ncgYxsy) {
        this.ncgYxsy = ncgYxsy;
    }

    public String getNcgBxb() {
        return ncgBxb;
    }

    public void setNcgBxb(String ncgBxb) {
        this.ncgBxb = ncgBxb;
    }

    public String getNcgPpt() {
        return ncgPpt;
    }

    public void setNcgPpt(String ncgPpt) {
        this.ncgPpt = ncgPpt;
    }

    public String getNcgBz() {
        return ncgBz;
    }

    public void setNcgBz(String ncgBz) {
        this.ncgBz = ncgBz;
    }

    public String getNcgPh() {
        return ncgPh;
    }

    public void setNcgPh(String ncgPh) {
        this.ncgPh = ncgPh;
    }

    public String getNcgWbdb() {
        return ncgWbdb;
    }

    public void setNcgWbdb(String ncgWbdb) {
        this.ncgWbdb = ncgWbdb;
    }

    public String getNcgYs() {
        return ncgYs;
    }

    public void setNcgYs(String ncgYs) {
        this.ncgYs = ncgYs;
    }

    public String getNcgTmd() {
        return ncgTmd;
    }

    public void setNcgTmd(String ncgTmd) {
        this.ncgTmd = ncgTmd;
    }

    public String getNcgHxbdl() {
        return ncgHxbdl;
    }

    public void setNcgHxbdl(String ncgHxbdl) {
        this.ncgHxbdl = ncgHxbdl;
    }

    public String getNcgBxbdl() {
        return ncgBxbdl;
    }

    public void setNcgBxbdl(String ncgBxbdl) {
        this.ncgBxbdl = ncgBxbdl;
    }

    public String getNcgGx() {
        return ncgGx;
    }

    public void setNcgGx(String ncgGx) {
        this.ncgGx = ncgGx;
    }

    public String getNcgYljj() {
        return ncgYljj;
    }

    public void setNcgYljj(String ncgYljj) {
        this.ncgYljj = ncgYljj;
    }

    public String getNcgSpxb() {
        return ncgSpxb;
    }

    public void setNcgSpxb(String ncgSpxb) {
        this.ncgSpxb = ncgSpxb;
    }

    public String getSgnTsqg() {
        return sgnTsqg;
    }

    public void setSgnTsqg(String sgnTsqg) {
        this.sgnTsqg = sgnTsqg;
    }

    public String getSgnNs() {
        return sgnNs;
    }

    public void setSgnNs(String sgnNs) {
        this.sgnNs = sgnNs;
    }

    public String getSgnJg() {
        return sgnJg;
    }

    public void setSgnJg(String sgnJg) {
        this.sgnJg = sgnJg;
    }

    public String getSgnNsuan() {
        return sgnNsuan;
    }

    public void setSgnNsuan(String sgnNsuan) {
        this.sgnNsuan = sgnNsuan;
    }

    public String getSgnPpt() {
        return sgnPpt;
    }

    public void setSgnPpt(String sgnPpt) {
        this.sgnPpt = sgnPpt;
    }

    public String getSgnXqg() {
        return sgnXqg;
    }

    public void setSgnXqg(String sgnXqg) {
        this.sgnXqg = sgnXqg;
    }

    public String getSgnNsjg() {
        return sgnNsjg;
    }

    public void setSgnNsjg(String sgnNsjg) {
        this.sgnNsjg = sgnNsjg;
    }

    public String getXzDgc() {
        return xzDgc;
    }

    public void setXzDgc(String xzDgc) {
        this.xzDgc = xzDgc;
    }

    public String getXzGysz() {
        return xzGysz;
    }

    public void setXzGysz(String xzGysz) {
        this.xzGysz = xzGysz;
    }

    public String getXzGmd() {
        return xzGmd;
    }

    public void setXzGmd(String xzGmd) {
        this.xzGmd = xzGmd;
    }

    public String getXzDmd() {
        return xzDmd;
    }

    public void setXzDmd(String xzDmd) {
        this.xzDmd = xzDmd;
    }

    public String getNxlNxmcd() {
        return nxlNxmcd;
    }

    public void setNxlNxmcd(String nxlNxmcd) {
        this.nxlNxmcd = nxlNxmcd;
    }

    public String getNxlHh() {
        return nxlHh;
    }

    public void setNxlHh(String nxlHh) {
        this.nxlHh = nxlHh;
    }

    public String getNxlXwcd() {
        return nxlXwcd;
    }

    public void setNxlXwcd(String nxlXwcd) {
        this.nxlXwcd = nxlXwcd;
    }

    public String getNxlNxmsj() {
        return nxlNxmsj;
    }

    public void setNxlNxmsj(String nxlNxmsj) {
        this.nxlNxmsj = nxlNxmsj;
    }

    public String getNxlBzh() {
        return nxlBzh;
    }

    public void setNxlBzh(String nxlBzh) {
        this.nxlBzh = nxlBzh;
    }

    public String getNxlNxmbz() {
        return nxlNxmbz;
    }

    public void setNxlNxmbz(String nxlNxmbz) {
        this.nxlNxmbz = nxlNxmbz;
    }

    public String getNxlEjt() {
        return nxlEjt;
    }

    public void setNxlEjt(String nxlEjt) {
        this.nxlEjt = nxlEjt;
    }

    public String getNxlXwjjw() {
        return nxlXwjjw;
    }

    public void setNxlXwjjw(String nxlXwjjw) {
        this.nxlXwjjw = nxlXwjjw;
    }

    public String getHbvDna() {
        return hbvDna;
    }

    public void setHbvDna(String hbvDna) {
        this.hbvDna = hbvDna;
    }

    public String getXtKf() {
        return xtKf;
    }

    public void setXtKf(String xtKf) {
        this.xtKf = xtKf;
    }

    public String getXtCh() {
        return xtCh;
    }

    public void setXtCh(String xtCh) {
        this.xtCh = xtCh;
    }

    public String getXtJmcd() {
        return xtJmcd;
    }

    public void setXtJmcd(String xtJmcd) {
        this.xtJmcd = xtJmcd;
    }

    public String getNcgWss() {
        return ncgWss;
    }

    public void setNcgWss(String ncgWss) {
        this.ncgWss = ncgWss;
    }

    public String getProjectId() {
        return projectId;
    }

    /**
     * 过滤掉不使用的玩意儿
     * 过滤内容是特定的projectId，项目名包含心电图和B超的项目
     * @return
     */
    public boolean couldUse() {
        boolean b = !CollectionUtil.contains(EXCLULSION_PROJECT, projectId);
        boolean b1 = !CollectionUtil.contains(EXCLULSION_PROJECT_target, projectId + "_" + targetId);

        boolean isNotBiChao = !this.projectName.contains("B超");
        boolean isNotXinDianTu = !this.projectName.contains("心电图");

        return b && isNotBiChao && isNotXinDianTu && b1;
    }

    public void insertTargetValueSet(SearchInfo info) {
        if (StrUtil.isNotBlank(this.name)) {
            info.setName(this.name);
        }
        if (StrUtil.isNotBlank(this.cardid)) {
            info.setCardid(this.cardid);
        }
        if (StrUtil.isNotBlank(this.sex)) {
            info.setSex(this.sex);
        }
        if (StrUtil.isNotBlank(this.age)) {
            info.setAge(this.age);
        }
        if (this.intime != null) {
            info.setIntime(this.intime);
        }
        if (StrUtil.isNotBlank(this.xcgBxb)) {
            info.setXcgBxb(this.xcgBxb);
        }
        if (StrUtil.isNotBlank(this.xcgZxbl)) {
            info.setXcgZxbl(xcgZxbl);
        }
        if (StrUtil.isNotBlank(this.xcgZx)) {
            info.setXcgZx(this.xcgZx);
        }
        if (StrUtil.isNotBlank(this.xcgDhbl)) {
            info.setXcgDhbl(xcgDhbl);
        }
        if (StrUtil.isNotBlank(this.xcgDh)) {
            info.setXcgDh(this.xcgDh);
        }
        if (StrUtil.isNotBlank(this.xcgLbbl)) {
            info.setXcgLbbl(this.xcgLbbl);
        }
        if (StrUtil.isNotBlank(this.xcgLb)) {
            info.setXcgLb(xcgLb);
        }
        if (StrUtil.isNotBlank(this.xcgSsxlbl)) {
            info.setXcgSsxlbl(this.xcgSsxlbl);
        }
        if (StrUtil.isNotBlank(this.xcgSsxl)) {
            info.setXcgSsxl(this.xcgSsxl);
        }
        if (StrUtil.isNotBlank(this.xcgSjxlbl)) {
            info.setXcgSjxlbl(this.xcgSjxlbl);
        }
        if (StrUtil.isNotBlank(this.xcgSjxl)) {
            info.setXcgSjxl(this.xcgSjxl);
        }
        if (StrUtil.isNotBlank(this.xcgHxb)) {
            info.setXcgHxb(this.xcgHxb);
        }
        if (StrUtil.isNotBlank(this.xcgXhdb)) {
            info.setXcgXhdb(this.xcgXhdb);
        }
        if (StrUtil.isNotBlank(this.xcgHxbyj)) {
            info.setXcgHxbyj(this.xcgHxbyj);
        }
        if (StrUtil.isNotBlank(this.xcgHxbtj)) {
            info.setXcgHxbtj(this.xcgHxbtj);
        }
        if (StrUtil.isNotBlank(this.xcgPjxh)) {
            info.setXcgPjxh(xcgPjxh);
        }
        if (StrUtil.isNotBlank(this.xcgPjxhnd)) {
            info.setXcgPjxhnd(this.xcgPjxhnd);
        }
        if (StrUtil.isNotBlank(this.xcgRbcsd)) {
            info.setXcgRbcsd(this.xcgRbcsd);
        }
        if (StrUtil.isNotBlank(this.xcgRbccv)) {
            info.setXcgRbccv(this.xcgRbccv);
        }
        if (StrUtil.isNotBlank(this.xcgXxb)) {
            info.setXcgXxb(this.xcgXxb);
        }
        if (StrUtil.isNotBlank(this.xcgXxbtj)) {
            info.setXcgXxbtj(this.xcgXxbtj);
        }
        if (StrUtil.isNotBlank(this.xcgXxbyj)) {
            info.setXcgXxbyj(this.xcgXxbyj);
        }
        if (StrUtil.isNotBlank(this.xcgXxbkd)) {
            info.setXcgXxbkd(this.xcgXxbkd);
        }
        if (StrUtil.isNotBlank(this.xcgXxbdxbl)) {
            info.setXcgXxbdxbl(this.xcgXxbdxbl);
        }
        if (StrUtil.isNotBlank(this.ygBmky)) {
            info.setYgBmky(this.ygBmky);
        }
        if (StrUtil.isNotBlank(this.ygBmky)) {
            info.setYgBmkt(this.ygBmky);
        }
        if (StrUtil.isNotBlank(this.ygEky)) {
            info.setYgEky(this.ygEky);
        }
        if (StrUtil.isNotBlank(this.ygEkt)) {
            info.setYgEkt(this.ygEkt);
        }
        if (StrUtil.isNotBlank(this.ygHxky)) {
            info.setYgHxky(this.ygHxky);
        }
        if (StrUtil.isNotBlank(this.ncgNdy)) {
            info.setNcgNdy(this.ncgNdy);
        }
        if (StrUtil.isNotBlank(this.ncgDhs)) {
            info.setNcgDhs(this.ncgDhs);
        }
        if (StrUtil.isNotBlank(this.ncgTt)) {
            info.setNcgTt(this.ncgTt);
        }
        if (StrUtil.isNotBlank(this.ncgYx)) {
            info.setNcgYx(this.ncgYx);
        }
        if (StrUtil.isNotBlank(this.ncgDbz)) {
            info.setNcgDbz(this.ncgDbz);
        }
        if (StrUtil.isNotBlank(this.ncgYxsy)) {
            info.setNcgYxsy(this.ncgYxsy);
        }
        if (StrUtil.isNotBlank(this.ncgBxb)) {
            info.setNcgBxb(this.ncgBxb);
        }
        if (StrUtil.isNotBlank(this.ncgPpt)) {
            info.setNcgPpt(this.ncgPpt);
        }
        if (StrUtil.isNotBlank(this.ncgBz)) {
            info.setNcgBz(this.ncgBz);
        }
        if (StrUtil.isNotBlank(this.ncgPh)) {
            info.setNcgPh(this.ncgPh);
        }
        if (StrUtil.isNotBlank(this.ncgWbdb)) {
            info.setNcgWbdb(this.ncgWbdb);
        }
        if (StrUtil.isNotBlank(this.ncgYs)) {
            info.setNcgYs(this.ncgYs);
        }
        if (StrUtil.isNotBlank(this.ncgTmd)) {
            info.setNcgTmd(this.ncgTmd);
        }
        if (StrUtil.isNotBlank(this.ncgHxbdl)) {
            info.setNcgHxbdl(this.ncgHxbdl);
        }
        if (StrUtil.isNotBlank(this.ncgBxbdl)) {
            info.setNcgBxbdl(this.ncgBxbdl);
        }
        if (StrUtil.isNotBlank(this.ncgGx)) {
            info.setNcgGx(this.ncgGx);
        }
        if (StrUtil.isNotBlank(this.ncgYljj)) {
            info.setNcgYljj(this.ncgYljj);
        }
        if (StrUtil.isNotBlank(this.ncgSpxb)) {
            info.setNcgSpxb(ncgSpxb);
        }
        if (StrUtil.isNotBlank(this.sgnTsqg)) {
            info.setSgnTsqg(this.sgnTsqg);
        }
        if (StrUtil.isNotBlank(this.sgnNs)) {
            info.setSgnNs(this.sgnNs);
        }
        if (StrUtil.isNotBlank(this.sgnJg)) {
            info.setSgnJg(this.sgnJg);
        }
        if (StrUtil.isNotBlank(this.sgnNsuan)) {
            info.setSgnNsuan(this.sgnNsuan);
        }
        if (StrUtil.isNotBlank(this.sgnPpt)) {
            info.setSgnPpt(this.sgnPpt);
        }
        if (StrUtil.isNotBlank(this.sgnXqg)) {
            info.setSgnXqg(this.sgnXqg);
        }
        if (StrUtil.isNotBlank(this.sgnNsjg)) {
            info.setSgnNsjg(this.sgnNsjg);
        }
        if (StrUtil.isNotBlank(this.xzDgc)) {
            info.setXzDgc(this.xzDgc);
        }
        if (StrUtil.isNotBlank(this.xzGysz)) {
            info.setXzGysz(this.xzGysz);
        }
        if (StrUtil.isNotBlank(this.xzGmd)) {
            info.setXzGmd(this.xzGmd);
        }
        if (StrUtil.isNotBlank(this.xzDmd)) {
            info.setXzDmd(this.xzDmd);
        }
        if (StrUtil.isNotBlank(this.nxlNxmcd)) {
            info.setNxlNxmcd(this.nxlNxmcd);
        }
        if (StrUtil.isNotBlank(this.nxlHh)) {
            info.setNxlHh(this.nxlHh);
        }
        if (StrUtil.isNotBlank(this.nxlXwcd)) {
            info.setNxlXwcd(this.nxlXwcd);
        }
        if (StrUtil.isNotBlank(this.nxlNxmsj)) {
            info.setNxlNxmsj(this.nxlNxmsj);
        }
        if (StrUtil.isNotBlank(this.nxlBzh)) {
            info.setNxlBzh(this.nxlBzh);
        }
        if (StrUtil.isNotBlank(this.nxlNxmbz)) {
            info.setNxlNxmbz(this.nxlNxmbz);
        }
        if (StrUtil.isNotBlank(this.nxlEjt)) {
            info.setNxlEjt(this.nxlEjt);
        }
        if (StrUtil.isNotBlank(this.nxlXwjjw)) {
            info.setNxlXwjjw(this.nxlXwjjw);
        }
        if (StrUtil.isNotBlank(this.hbvDna)) {
            info.setHbvDna(this.hbvDna);
        }
        if (StrUtil.isNotBlank(this.xtKf)) {
            info.setXtKf(this.xtKf);
        }
        if (StrUtil.isNotBlank(this.xtCh)) {
            info.setXtCh(this.xtCh);
        }
        if (StrUtil.isNotBlank(this.xtJmcd)) {
            info.setXtJmcd(this.xtJmcd);
        }
        if (StrUtil.isNotBlank(this.ncgWss)) {
            info.setNcgWss(this.ncgWss);
        }

    }

    public Date getXcgtime() {
        return xcgtime;
    }

    public void setXcgtime(Date xcgtime) {
        this.xcgtime = xcgtime;
    }

    public Date getYgtime() {
        return ygtime;
    }

    public void setYgtime(Date ygtime) {
        this.ygtime = ygtime;
    }

    public Date getNcgtime() {
        return ncgtime;
    }

    public void setNcgtime(Date ncgtime) {
        this.ncgtime = ncgtime;
    }

    public Date getSngtime() {
        return sngtime;
    }

    public void setSngtime(Date sngtime) {
        this.sngtime = sngtime;
    }

    public String getSgnalf() {
        return sgnalf;
    }

    public void setSgnalf(String sgnalf) {
        this.sgnalf = sgnalf;
    }

    public Date getXztime() {
        return xztime;
    }

    public void setXztime(Date xztime) {
        this.xztime = xztime;
    }

    public Date getNxltime() {
        return nxltime;
    }

    public void setNxltime(Date nxltime) {
        this.nxltime = nxltime;
    }

    public Date getHbvtime() {
        return hbvtime;
    }

    public void setHbvtime(Date hbvtime) {
        this.hbvtime = hbvtime;
    }

    public Date getXttime() {
        return xttime;
    }

    public void setXttime(Date xttime) {
        this.xttime = xttime;
    }
}

