package com.wong.redisdemo;

import cn.hutool.core.io.FileUtil;
import com.wong.testdemo.Testdemo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.List;

/**
 * 功能类测试代码
 *
 * 视频文件存储格式：原始上传标题@作者名.mp4
 * @author: Wym's Code code in MacBook pro 2020 Silicon
 * @date: 2022/3/19 13:09
 */
@SpringBootTest(classes = Testdemo.class)
public class FunctionTest {

    //源视频目录
    public static final String PATH = "/Users/wangyumou/Desktop/origin";
    //存放的目标视频目录
    public static final String TARGET_PATH = "/Users/wangyumou/Desktop/target";

    /**
     * 测试获取给定一个文件夹，得到所有的文件。打印其全部原始文件名
     */
    @Test
    public void testGetDirectoryAllFiles() {
        List<File> files = FileUtil.loopFiles(PATH, FunctionTest::isRuledMp4File);
        System.out.println("文件数量:" + files.size());
        files.forEach(e-> System.out.println(e.getName()));
    }

    /**
     * 重命名 去掉空格
     */
    @Test
    public void testRenameVideos() {
        // 遍历所有文件，去掉文件名中的所有空格
        List<File> loopFiles = FileUtil.loopFiles(PATH, FunctionTest::isMp4);
        loopFiles.forEach(e -> System.out.println("修改前文件名:" + e.getName()));
        loopFiles.forEach(e -> FileUtil.rename(e, FileUtil.getPrefix(e.getName().replace(" ", "")), true, true));
        System.out.println("<================>");
        //再次遍历 打印名字
        List<File> loop2Files = FileUtil.loopFiles(PATH, FunctionTest::isMp4);
        loop2Files.forEach(e -> System.out.println("修改后文件名:" + e.getName()));
    }

    /**
     * 测试把文件转存到另一个文件夹下，并且目标文件夹里构造新的子目录
     * 子目录以@后紧跟的作者名建文件夹，视频文件存储到对应的作者名子目录中
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

    //判断一个文件是否是mp4文件
    public static boolean isMp4(File file) {
        if (!file.isFile()) {
            return false;
        }
        return FileUtil.getSuffix(file.getName()).equals("mp4");
    }

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
    public static void set2Redis(String fileName) {

    }

    /**
     * 查找是否存在对应的Redis视频键
     * @param fileName
     * @return
     */
    public static boolean getFromRedis(String fileName) {
        return true;
    }

    /**
     * 把现有全部视频键导出到外部文件以txt形式保存
     * @return
     */
    public static boolean exportFilenameFromRedis() {
        return true;
    }

    /**
     * 清空Redis现有全部视频键
     * 根据外部文件，视为视频键存储到Redis
     */
    public static boolean importFromFile2Redis() {
        return true;
    }
}
