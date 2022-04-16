package com.wong.redisdemo;

import cn.hutool.core.io.FileUtil;
import com.wong.testdemo.Testdemo;
import com.wong.testdemo.utils.RedisCache;
import com.wong.testdemo.utils.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 功能类测试代码
 *
 * 视频文件存储格式：原始上传标题@作者名.mp4
 * @author: Wym's Code code in MacBook pro 2020 Silicon
 * @date: 2022/3/19 13:09
 */
@SpringBootTest(classes = Testdemo.class)
@RunWith(SpringRunner.class)
public class FunctionTest {

    @Autowired
    RedisCache redisCache;
    //源视频目录
    public final String PATH = "/Users/wangyumou/Desktop/origin";
    //存放的目标视频目录
    public final String TARGET_PATH = "/Users/wangyumou/Desktop/target";

    public final String DesktopPath = "/Users/wangyumou/Desktop";


    /**
     * 测试获取给定一个文件夹，得到所有的文件。打印其全部原始文件名
     * 并全部存储到Redis键中
     */
    @Test
    public void testGetDirectoryAllFiles() {
        List<File> files = FileUtil.loopFiles(PATH, FunctionTest::isRuledMp4File);
        System.out.println("文件数量:" + files.size());
        files.forEach(e-> {
            System.out.println(e.getName());
            String prefix = FileUtil.getPrefix(e.getName());
            set2Redis(prefix);
        });
    }

    /**
     * 重命名 去掉空格
     */
    @Test
    public void testRenameVideos() {
        // 遍历所有文件，去掉文件名中的所有空格
        List<File> loopFiles = FileUtil.loopFiles(PATH, FunctionTest::isMp4);
        System.out.println("文件数量为:" + loopFiles.size());
        loopFiles.forEach(e -> System.out.println("修改前文件名:" + e.getName()));
        loopFiles.forEach(e -> FileUtil.rename(e, FileUtil.getPrefix(e.getName().replace(" ", "")), true, true));
        System.out.println("<================>");
        //再次遍历 打印名字
        List<File> loop2Files = FileUtil.loopFiles(PATH, FunctionTest::isMp4);
        loop2Files.forEach(e -> System.out.println("修改后文件名:" + e.getName()));
    }

    /**
     *
     * 把PATH路径下的所有文件，转换到TARGET_PATH路径下，在TARGET_PATH下会新建N个子目录
     * 子目录以视频文件名第一个@符号后的作者名创建
     * 视频文件存储到对应的作者名子目录中
     */
    @Test
    public void testRemoveFileAndSortByName() {
        // 遍历文件
        List<File> files = FileUtil.loopFiles(PATH, FunctionTest::isRuledMp4File);
        System.out.println("更改文件位置时文件数量:" + files.size());
        files.forEach(e -> System.out.println("全部文件名:" + e.getName()));
        files.forEach(e-> {
            File authorDirFile = new File(TARGET_PATH + "/" + getAuthorName(e.getName()));
            File targetFile = new File(authorDirFile.getAbsolutePath() + "/" + e.getName());
            FileUtil.copy(e, targetFile, false);
        });
    }

    /**
     * 检查文件是否后缀是mp4
     * @param file 待检查文件
     * @return {@code true} 符合
     *         {@code false} 不符合
     */
    public static boolean isMp4(File file) {
        if (!file.isFile()) {
            return false;
        }
        return FileUtil.getSuffix(file.getName()).equals("mp4");
    }

    /**
     * 检查是否是按照规则给视频文件进行的取名
     * 规则：上传标题@作者名.mp4 或 上传标题@作者名@上传时间.mp4
     * @param file 待检查文件
     * @return {@code true} 符合规则
     *         {@code false} 不符合规则
     */
    public static boolean isRuledMp4File(File file) {
        if (!file.isFile()) {
            return false;
        }
        if (!FileUtil.getSuffix(file.getName()).equals("mp4")) {
            return false;
        }
        String name = file.getName();
        String[] split = name.split("@");
        return split.length == 2 || split.length == 3;
    }

    /**
     * 返回作者名称
     * 因文件取名有两种不同的规则，因此作者名可能是第一个@后，后缀前的部分
     * 也有可能是第一个@后，第二个@前
     *
     * @param originalName 原始文件名
     * @return 作者名
     */
    public static String getAuthorName(String originalName) {
        String[] split = originalName.split("@");
        if (split.length == 2) {
            return split[1].split(".mp4")[0];
        } else if (split.length == 3) {
            return split[1];
        }
        throw new RuntimeException("名称格式不符合预定格式");
    }


    /**
     * 把文件名作为视频键存储到Redis
     */
    public  void set2Redis(String fileName) {
        redisCache.setCacheObject(getVideoKey(fileName), "fileName");
    }

    /**
     * 查找是否存在对应的Redis视频键
     * @param fileName 视频文件名
     * @return
     */
    public  boolean getFromRedis(String fileName) {
        Object cacheObj = redisCache.getCacheObject(getVideoKey(fileName));
        return StringUtils.isNotNull(cacheObj);
    }

    /**
     * 把现有全部视频键导出到外部文件以txt形式保存
     * 文件导出为 {@code DesktopPath} + "/redis.txt"
     * 一键一行
     * @return
     */
    @Test
    public  void exportFilenameFromRedis() {
        Collection<String> keys = redisCache.keys(getVideoKey("*"));
        System.out.println(keys.size());
        File txt = FileUtil.touch(DesktopPath + "/redis.txt");
        List<String> strings = keys.stream()
                .map(e -> e.replace(getVideoKey(""), ""))
                .collect(Collectors.toList());
        FileUtil.appendLines(strings, txt, StandardCharsets.UTF_8);
        // return true;
    }

    /**
     * 清空Redis现有全部视频键
     * 根据外部文件，视为视频键存储到Redis
     * 外部文件位置为 {@code DesktopPath} + "/redis.txt"
     */
    @Test
    public void importFromFile2Redis() {
        Collection<String> oldValues = redisCache.keys(getVideoKey("*"));
        redisCache.deleteObject(oldValues);//删除旧有键值
        File txt = FileUtil.touch(DesktopPath + "/redis.txt");
        List<String> list = FileUtil.readLines(txt, StandardCharsets.UTF_8);
        Map<String, String> collect = list.stream()
                .map(Tmp::new)
                .collect(Collectors.toMap(e->getVideoKey(e.getKey()), Tmp::getValue));
        redisCache.setCacheObjects(collect);
        // return true;
    }

    class Tmp{
        private String key;
        private String value;

        public Tmp(String key) {
            this.key = key;
            this.value = "filename";
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    private  String getVideoKey(String filename) {
        return "VIDEO:" + filename;
    }
}
