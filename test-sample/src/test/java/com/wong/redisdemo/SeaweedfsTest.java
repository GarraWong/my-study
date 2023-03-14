package com.wong.redisdemo;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.wong.testdemo.Testdemo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import seaweedfs.client.FilerClient;

import java.io.BufferedOutputStream;
import java.io.File;

/**
 * Seaweedfs 测试用例
 *
 * @author : Wym's Code code in MacBook pro 2020 Silicon
 * @date : 2022/9/26 14:56
 */
@SpringBootTest(classes = Testdemo.class)
@RunWith(SpringRunner.class)
public class SeaweedfsTest {


    private static final Logger logger = LoggerFactory.getLogger(SeaweedfsTest.class);

    @Autowired
    @Qualifier("httpTemplate")
    private RestTemplate restTemplate;

    /**
     * volumeServer对外访问地址端口
     */
    private static final String volumeServerUrl = "http://172.16.7.104:8080";
    /**
     * masterServer对外访问地址端口
     */
    private static final String masterServerUrl = "http://172.16.7.104:9333";

    /**
     * 测试获取文件id,正常执行会返回fid
     */
    @Test
    public void getFileIdTest() {

        final String ROUTING_SUFFIX = "/dir/assign";    //路由地址，不能修改
        //请求地址
        String url = masterServerUrl + ROUTING_SUFFIX;
        ResponseEntity<String> entity;
        try {
            entity = restTemplate.getForEntity(url, String.class);
            logger.info("获取fileId返回实体 {}", entity);
            //TODO if (entity.getStatusCode().is2xxSuccessful())
            //TODO JSONObject.parseObject(entity,xxx.class);
            String body = entity.getBody();
            logger.info("获取fileId返回body  {}", body);
        } catch (Exception e) {
            logger.error("调用Seaweedfs接口获取fileId失败", e);
        }
    }


    /**
     * 测试上传文件,修改FILE_ID和FILE_ABSOLUTE_PATH测试
     */
    @Test
    public void uploadFileTest() {
        /*
        FILE_ID 为{@code getFileIdTest()}得到的文件id
         */
        final String FILE_ID = "/2,030c96cab0";
        /*
        FILE_ABSOLUTE_PATH 为电脑上真实存在的文件绝对路径，此处就可以为前端MultipartFile转换的文件，该temp文件可以在方法执行完毕以后切面删除
         */
        final String FILE_ABSOLUTE_PATH = "/Users/wangyumou/Desktop/MySQL至KingbaseES迁移最佳实践.pdf";

        //请求地址
        //TODO 该处volumeServerUrl本应是从{"count":1,"fid":"3,01637037d6","url":"127.0.0.1:8080","publicUrl":"localhost:8080"}获取，受限于部署情况写成配置。若是jar程序所在的服务器能直接访问返回的publicUrl地址，可以从该处获取
        String url = volumeServerUrl + FILE_ID;
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("multipart/form-data");
        headers.setContentType(type);
        // headers.add("Accept", "*/*");

        File file = new File(FILE_ABSOLUTE_PATH);
        FileSystemResource resource = new FileSystemResource(file);

        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("file", resource);   //file键名从官网例子得来
        //请求实体，包含请求体、请求头
        HttpEntity<MultiValueMap<String, Object>> formEntity = new HttpEntity<>(params, headers);
        ResponseEntity<String> entity;
        try {
            entity = restTemplate.postForEntity(url, formEntity, String.class);
            logger.info("返回实体 {}", entity);
            String body = entity.getBody();
            logger.info("返回body  {}", body);
        } catch (Exception e) {
            logger.error("调用Seaweedfs接口失败", e);
        }
    }

    /**
     * 测试根据fileId获取文件流,重命名为其他文件
     *
     */
    @Test
    public void testGetExistFileStream() {
        final String FILE_ID = "/2,030c96cab0";

        //请求地址
        String url = volumeServerUrl + FILE_ID;
        ResponseEntity<Resource> entity;
        try {
            entity = restTemplate.getForEntity(url, Resource.class);  //传输文件最终都是字节数组的传输
            //TODO if (entity.getStatusCode().is2xxSuccessful())
            Resource body = entity.getBody();
            Assert.assertNotNull(body.getInputStream());
            File targetFile = new File("/Users/wangyumou/Desktop/还原后文件.pdf");   //目标存储文件，改名
            File orignalFile = new File("/Users/wangyumou/Desktop/MySQL至KingbaseES迁移最佳实践.pdf"); //原始文件
            BufferedOutputStream outputStream = FileUtil.getOutputStream(targetFile);
            IoUtil.copy(body.getInputStream(), outputStream);   //拷贝文件

            Assert.assertTrue(targetFile.exists());
            Assert.assertEquals(orignalFile.length(), targetFile.length());
            Assert.assertEquals(DigestUtil.md5Hex(orignalFile), DigestUtil.md5Hex(targetFile));

            //TODO  根据自己存储的文件基础信息表(fid文件id,suffix后缀,ordinalName全称...etc)，还原文件，返回前端

        } catch (Exception e) {
            logger.error("调用Seaweedfs接口获取fileId失败", e);
        }
    }

}
