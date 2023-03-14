package com.wong.controller;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.wong.domain.ImportDomain;
import com.wong.domain.SearchInfo;
import com.wong.domain.TjDomain;
import com.wong.mapper.SearchInfoDao;
import com.wong.mapper.Tjmapper;
import com.wong.utils.poi.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

/**
 * 这是类的描述 补充它
 *
 * @author : Wym's Code code in MacBook pro 2020 Silicon
 * @date : 2022/12/29 12:41
 */

@RestController
@RequestMapping("/tj")
public class TjContoller {

    private static final Logger logger = LoggerFactory.getLogger(TjContoller.class);

    @Autowired
    Tjmapper tjmapper;
    @Autowired
    SearchInfoDao searchInfoDao;

    @PostMapping("/file")
    public String upload(MultipartFile file) {
        ExcelUtil<TjDomain> util = new ExcelUtil<>(TjDomain.class);
        try {
            logger.info("开始导入");
            List<TjDomain> domains = util.importExcel(file.getInputStream());
            List<TjDomain> result = domains.stream().map(e -> {
                if (StrUtil.isNotBlank(e.getName())) {
                    e.setName(StrUtil.trim(e.getName()));
                }
                if (StrUtil.isNotBlank(e.getNation())) {
                    e.setNation(StrUtil.trim(e.getNation()));
                }
                if (StrUtil.isNotBlank(e.getSex())) {
                    e.setSex(StrUtil.trim(e.getSex()));
                }
                return e;
            }).collect(Collectors.toList());

            int tj = tjmapper.insertTj(result);
            logger.info("数据插入完毕,共计{}条", result.size());
            return String.valueOf(tj);
        } catch (Exception e) {
            logger.error("出错了", e);
            return "出错了";
        }
    }

    static final Pattern p = Pattern.compile("^[\\u4e00-\\u9fa5]{0,}$");  //中文正则表达式


    /**
     * 六万条导入的数据
     * @param file
     * @return
     */
    @PostMapping("/file2")
    public String upload2(MultipartFile file) {
        ExcelUtil<ImportDomain> util = new ExcelUtil<>(ImportDomain.class);
        try {
            logger.info("开始导入");
            List<ImportDomain> domains = util.importExcel(file.getInputStream());
            logger.info("导入完毕，导入数据:{}", domains.size());
            String originalFilename = file.getOriginalFilename();
            List<SearchInfo> result = domains.parallelStream()
                    .filter(e -> p.matcher(e.getName()).matches())
                    .filter(e->{
                        //数据校验 begin
                        if (StrUtil.isBlankIfStr(e.getName())) {
                            return false;
                            // throw new IllegalArgumentException("姓名不能为空:" + e);
                        }
                        if (StrUtil.isBlankIfStr(e.getAge())) {
                            return false;
                            // throw new IllegalArgumentException("年龄不能为空:" + e);
                        }
                        if (StrUtil.isBlankIfStr(e.getSex())) {
                            return false;
                            // throw new IllegalArgumentException("性别不能为空:" + e);
                        }
                        if (StrUtil.isBlankIfStr(e.getSex())
                                || StrUtil.isBlankIfStr(e.getProjectName())
                                || StrUtil.isBlankIfStr(e.getProjectId())
                                || StrUtil.isBlankIfStr(e.getTargetId())
                                || StrUtil.isBlankIfStr(e.getTargetName())) {
                            return false;
                            // throw new IllegalArgumentException("项目四要素不能为空");
                        }
                        return true;
                    })
                    .map(e -> new SearchInfo(e, originalFilename))
                    .filter(SearchInfo::couldUse)
                    .collect(Collectors.toList());
            logger.info("构造完毕");
            logger.info("开始特定赋值");
            result.forEach(SearchInfo::setValue);
            logger.info("赋值完毕,数据量{},过滤掉{}条数据,第一条数据{}", result.size(), result.size() - domains.size(), result.get(0));

            int i = result.size() / 5000;
            int i2 = result.size() % 5000;
            for (int i1 = 0; i1 < i; ) {
                searchInfoDao.insertBatch(result.stream().skip(i1 * 5000).limit(5000).collect(Collectors.toList()));
                logger.info("插入了第{}到第{}条数据", i1 * 5000, (i1 + 1) * 5000);
                i1++;
                if (i1 == i) {
                    if (i2 > 0) {
                        searchInfoDao.insertBatch(result.stream().skip(i1 * 5000).collect(Collectors.toList()));
                        logger.info("插入了第{}到剩余数据", i1 * 5000);
                    }
                }
            }


            // int tj = searchInfoDao.insertBatch(result);
            logger.info("数据插入完毕,共计{}条", result.size());
            return String.valueOf("tj");
        } catch (Exception e) {
            logger.error("出错了", e);
            return "出错了";
        }
    }


    @GetMapping("/exportdata")
    public String exportdata() {
        List<SearchInfo> infos = searchInfoDao.queryAllDataWithDeal();
        infos.forEach(e->{
            if (StrUtil.isNotBlank(e.getXcgBxb()) || StrUtil.isNotBlank(e.getXcgXxb())
                    || StrUtil.isNotBlank(e.getXcgZx()) || StrUtil.isNotBlank(e.getXcgPjxh())
                    || StrUtil.isNotBlank(e.getXcgSsxl())) {
                e.setXcgtime(e.getIntime());
            }
            if (StrUtil.isNotBlank(e.getYgBmkt()) || StrUtil.isNotBlank(e.getYgBmky())
                    || StrUtil.isNotBlank(e.getYgEky()) || StrUtil.isNotBlank(e.getYgEkt())
                    || StrUtil.isNotBlank(e.getYgHxky())) {
                e.setYgtime(e.getIntime());
            }
            if (StrUtil.isNotBlank(e.getNcgBxb()) || StrUtil.isNotBlank(e.getNcgBz())
                    || StrUtil.isNotBlank(e.getNcgBxbdl()) || StrUtil.isNotBlank(e.getNcgNdy())
                    || StrUtil.isNotBlank(e.getNcgPpt()) || StrUtil.isNotBlank(e.getNcgPh())) {
                e.setNcgtime(e.getIntime());
            }
            if (StrUtil.isNotBlank(e.getSgnalf()) || StrUtil.isNotBlank(e.getSgnJg())
                    || StrUtil.isNotBlank(e.getSgnNsjg()) || StrUtil.isNotBlank(e.getSgnPpt())
                    || StrUtil.isNotBlank(e.getSgnNsuan()) || StrUtil.isNotBlank(e.getSgnXqg())) {
                e.setSngtime(e.getIntime());
            }
            if (StrUtil.isNotBlank(e.getXzDgc()) || StrUtil.isNotBlank(e.getXzDmd())
                    || StrUtil.isNotBlank(e.getXzGmd()) || StrUtil.isNotBlank(e.getXzGysz())
                    ) {

                e.setXztime(e.getIntime());
            }
            if (StrUtil.isNotBlank(e.getNxlBzh()) || StrUtil.isNotBlank(e.getNxlHh())
                    || StrUtil.isNotBlank(e.getNxlNxmbz()) || StrUtil.isNotBlank(e.getNxlEjt())
                    || StrUtil.isNotBlank(e.getNxlXwcd()) || StrUtil.isNotBlank(e.getNxlXwjjw())) {
                e.setNxltime(e.getIntime());
            }
            if (StrUtil.isNotBlank(e.getHbvDna()) ) {
                e.setHbvtime(e.getIntime());
            }
            if (StrUtil.isNotBlank(e.getXtCh()) || StrUtil.isNotBlank(e.getXtJmcd())
                    || StrUtil.isNotBlank(e.getXtCh()) ) {

                e.setXttime(e.getIntime());
            }
        });
        ExcelUtil<SearchInfo> util = new ExcelUtil<>(SearchInfo.class);
        util.exportExcel(infos, "数据导出");
        return "导出完毕";
    }

    @GetMapping("/createdata")
    public String createdata() {

        List<SearchInfo> infos = searchInfoDao.queryGroupBy();
        Map<String, List<SearchInfo>> map = infos.stream()
                .collect(groupingBy(SearchInfo::getName))//根据名字分组
                .entrySet()
                .stream()
                .filter(e -> e.getValue().size() > 1) // key : 名字 value: 名字相同的值  过滤出包含1个以上的，这就是需要去check的部分
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (key1, key2) -> key2));
        logger.info("过滤后只剩下这部分");
        AtomicInteger i = new AtomicInteger(1);
        Map<String, List<SearchInfo>> currenct = new ConcurrentHashMap<>();
        map.forEach((k, v) -> {
            // logger.info("第{}组,姓名:{},信息:{}",i, k, v);
            i.getAndIncrement();
            Map<String, List<SearchInfo>> result;
            result = v.stream()
                    .collect(groupingBy(e -> e.getName() + "_" + String.valueOf(DateUtil.year(e.getIntime()) - NumberUtil.parseInt(e.getAge()))));
            currenct.putAll(result);
            // if (result.size() > 1) {
            //     logger.info("第{}个出现复杂结果:{}", i2.getAndIncrement(), result);
            // }
        });
        int haha = currenct.keySet().size();
        List<SearchInfo> insertList = new ArrayList<>();
        //TODO 把这部分有重复的先处理了
        logger.info("开始处理重复的");
        currenct.entrySet()
                .parallelStream()
                .forEach(k -> {
                    List<SearchInfo> list = searchInfoDao.queryByCardIds(k.getValue().stream().map(SearchInfo::getCardid).collect(Collectors.toList()));
                    //对结果做归并
                    SearchInfo insertTarget = new SearchInfo();
                    list.forEach(e -> e.insertTargetValueSet(insertTarget));
                    insertList.add(insertTarget);
                });
        logger.info("重复的处理完了");

        Map<String, List<SearchInfo>> map2 = infos.stream()
                .collect(groupingBy(SearchInfo::getName))//根据名字分组
                .entrySet()
                .stream()
                .filter(e -> e.getValue().size() == 1) // key : 名字 value: 名字相同的值  过滤出只有1个的
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (key1, key2) -> key2));
        logger.info("不重复的开始处理");

        map2.entrySet()
            .parallelStream()
            .forEach(k -> {
                List<SearchInfo> list = searchInfoDao.queryByCardIds(k.getValue().stream().map(SearchInfo::getCardid).collect(Collectors.toList()));
                //对结果做归并
                SearchInfo insertTarget = new SearchInfo();
                list.forEach(e -> e.insertTargetValueSet(insertTarget));
                insertList.add(insertTarget);
            });
        logger.info("不重复的处理完了");

        logger.info("开始处理身份证号");
        insertList.parallelStream()
                        .forEach(e->{
                            int i1 = DateUtil.year(e.getIntime()) - NumberUtil.parseInt(e.getAge());
                            String format = DateUtil.format(DateTime.of(String.valueOf(i1), "yyyy").offset(DateField.DAY_OF_YEAR, RandomUtil.randomInt(365)), "yyyyMMdd");
                            e.setIdnumber(ID_PREFIX.get(RandomUtil.randomInt(7)) + format + RandomUtil.randomNumbers(4));
                            if (StrUtil.isBlankIfStr(e.getCardid())) {
                                e.setCardid("");
                            }
                        });
        logger.info("身份证号处理完毕");

        logger.info("开始处理插入");
        int size = insertList.size() / 5000;
        int size2 = insertList.size() % 5000;
        for (int factor = 0; factor < size; ) {
            searchInfoDao.insertBatch2(insertList.stream().skip(factor * 5000).limit(5000).collect(Collectors.toList()));
            logger.info("插入了第{}到第{}条数据", factor * 5000, (factor + 1) * 5000);
            factor++;
            if (factor == size) {
                if (size2 > 0) {
                    searchInfoDao.insertBatch2(insertList.stream().skip(factor * 5000).collect(Collectors.toList()));
                    logger.info("插入了第{}到剩余数据", factor * 5000);
                }
            }
        }
        logger.info("插入处理完毕");
        return String.valueOf(insertList.size());
    }

    /*
        广西 崇左市	451400
        广西 崇左市 市辖区	451401
        广西 崇左市 扶绥县	451421
        广西 崇左市 宁明县	451422
        广西 崇左市 龙州县	451423
        广西 崇左市 大新县	451424
        广西 崇左市 天等县	451425
        广西 崇左市 江洲区	451429
         */
    static final List<String> ID_PREFIX = new ArrayList<>();
    static {
        ID_PREFIX.add("451400");
        ID_PREFIX.add("451401");
        ID_PREFIX.add("451421");
        ID_PREFIX.add("451422");
        ID_PREFIX.add("451423");
        ID_PREFIX.add("451424");
        ID_PREFIX.add("451425");
        ID_PREFIX.add("451429");
    }


    @GetMapping("/deal")
    public String dealData(HttpServletResponse response) {
        ExcelUtil<TjDomain> util = new ExcelUtil<>(TjDomain.class);

        List<TjDomain> list = tjmapper.selectAll();
        Map<String, List<TjDomain>> map = list.stream()
                .collect(groupingBy(e -> e.getName() + e.getSex() +e.getNation()));    //按照姓名和性别初步分组
        Collection<List<TjDomain>> lists = map.values();
        for (List<TjDomain> domains : lists) {
            int flag = 1;
            f(domains, 0, new ArrayList<>(), false);
        }
        AtomicInteger i = new AtomicInteger(1);
        List<TjDomain> finalresult = result.stream()
                .flatMap(e -> {
                    Stream<TjDomain> stream = e.stream().map(value -> {
                        value.setFlag(i.get());
                        return value;
                    });
                    i.getAndIncrement();
                    return stream;
                })
                .sorted(Comparator.comparing(TjDomain::getFlag))
                .collect(Collectors.toList());
        logger.info("haha");
        util.exportExcel(finalresult,"result","标题");
        return "chu理完毕";

    }

    static final List<List<TjDomain>> result = new ArrayList<>();

    private static int f(List<TjDomain> list, int index, List<TjDomain> sameCollection, boolean needJump) {
        if (index == list.size()) {
            return index;
        }
        Integer ageValue = list.get(index).getAge();
        TjDomain value = list.get(index);


        if (sameCollection.size() == 0) {
            if (needJump) {
//                result.add(sameCollection);
                return index;
            }
            result.add(sameCollection);
            sameCollection.add(value);
            return f(list, index + 1, sameCollection, needJump);
        } else if (sameCollection.size() == 3) {    //组合已经有3个了，生成新组，把当前组放入总组合
            if (needJump) {
//                result.add(sameCollection);
                return index;
            }
            List<TjDomain> collection = new ArrayList<>();
            collection.add(value);
            result.add(collection);
            return f(list, index + 1, collection, needJump);
        } else if (sameCollection.size() == 1) {
            if (ageValue - sameCollection.get(0).getAge() > 3) {  //与该集合第一个和第二个对比 超过3 需要新开一个组合
                List<TjDomain> collection = new ArrayList<>();
                collection.add(value);
                result.add(collection);
                return f(list, index + 1, collection, needJump);
            } else if (ageValue - sameCollection.get(0).getAge() <= 0) {    //如果相等，需要调组
                ArrayList<TjDomain> thisOne = new ArrayList<>();
                thisOne.add(value);
                result.add(thisOne);

                return f(list, f(list, index + 1, thisOne, true), sameCollection, needJump);  //这是当前的数字
            } else {
                sameCollection.add(value);
                return f(list, index + 1, sameCollection, needJump);
            }
        } else if (sameCollection.size() == 2) {
            if (ageValue - sameCollection.get(0).getAge() > 3) {  //与该集合第一个和第二个对比 超过3 需要新开一个组合
                List<TjDomain> collection = new ArrayList<>();
                collection.add(value);
                result.add(collection);
                return f(list, index + 1, collection, needJump);
            } else {
                if (ageValue - sameCollection.get(1).getAge() > 3) {
                    List<TjDomain> collection = new ArrayList<>();
                    collection.add(value);
                    result.add(collection);
                    return f(list, index + 1, collection, needJump);
                } else if (ageValue - sameCollection.get(1).getAge() <= 0) {
                    ArrayList<TjDomain> thisOne = new ArrayList<>();
                    thisOne.add(value);
                    result.add(thisOne);

                    return f(list, f(list, index + 1, thisOne, true), sameCollection, needJump);  //这是当前的数字
                } else {
                    sameCollection.add(value);
                    return f(list, index + 1, sameCollection, needJump);
                }

            }
        } else {
            throw new RuntimeException("hahaha");
        }
    }


}
