package com.wong.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.MD5;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * 这是类的描述 补充它
 *
 * @author : Wym's Code coding MacBook pro 2020 Silicon
 * @date : 2025/6/4 16:05
 */
@Component
@Slf4j
public class DevInsightUtil {
    @Value("${devinsight.appid}")
    private String appid;
    @Value("${devinsight.appsecret}")
    private String appsecret;
    @Value("${devinsight.baseUrl}")
    private String baseUrl;

    private void sign(TreeMap<String,Object> param) throws UnsupportedEncodingException {
        param.put("appid", appid);
        param.put("nonce_str", String.valueOf(System.currentTimeMillis()));
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : param.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            // 对键和值进行URL编码
            // String encodedKey = URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8.toString());
            // String encodedValue = URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8.toString());
            sb.append(entry.getKey()).append("=").append(entry.getValue());
        }
        log.debug("marshall->[{}]", sb.toString());
        String strSign = sb.toString() + "&key=" + appsecret;
        log.debug("sign->[{}]", strSign);
        param.put("sign", MD5.create().digestHex(strSign).toUpperCase());
    }

    public JSONObject invoke(TreeMap<String, Object> param,String url) {
        try {
            sign(param);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        // 1. 创建 OkHttpClient 实例
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), JSON.toJSONString(param));
        // 2. 构建请求
        Request request = new Request.Builder()
                .url(baseUrl + url)
                .method("POST", body)
                .build();

        // 3. 发送请求并处理响应
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                // 打印响应体内容
                String result = response.body().string();
                log.debug("请求成功，响应体：{}", result);
                log.debug("请求成功，请求体：{}", JSON.toJSONString(param));
                return JSONObject.parse(result);
            } else {
                log.error("请求失败，状态码：{}", response.code());
                log.error("请求失败，请求体：{}", JSON.toJSONString(param));
                throw new RuntimeException("请求失败");
            }
        } catch (IOException e) {
            log.error("出错了哟", e);
            throw new RuntimeException(e);
        }
    }

    public boolean judgeSuccess(JSONObject result) {
        Integer code = result.getInteger("code");
        return Objects.nonNull(code) && code == 200;
    }

    public <T> List<T> getDataList(JSONObject result,Class<T> clazz) {
        String data = result.getString("data");
        if (StrUtil.isNotBlank(data)) {
            List<T> list = JSONArray.parseArray(data, clazz);
            return list;
        } else {
            return null;
        }
    }


}
