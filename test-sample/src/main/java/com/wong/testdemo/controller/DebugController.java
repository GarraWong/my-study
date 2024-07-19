package com.wong.testdemo.controller;

import lombok.Data;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 这是类的描述 补充它
 *
 * @author : Wym's Code code in MacBook pro 2020 Silicon
 * @date : 2022/11/17 11:09
 */
@RestController
@RequestMapping("/debug/token")
public class DebugController {

    private static final Logger logger = LoggerFactory.getLogger(DebugController.class);

    @GetMapping("/test")
    public String debugTest() {
        logger.info("进入方法");
        byte[] bytes = new byte[1024 * 1024 * 1024 * 6];
        byte[] bytes1 = new byte[1024 * 1024 * 1024 * 6];
        byte[] bytes2 = new byte[1024 * 1024 * 1024 * 6];
        byte[] bytes3 = new byte[1024 * 1024 * 1024 * 6];

        return "success";
    }

    @PostMapping("/api/standard/getToken")
    public TokenResponse debugResult(@RequestBody Request request, HttpServletRequest servletRequest) {
        logger.info("{}", request);
        TokenResponse response = new TokenResponse();
        response.setCode(0);
        response.setMsg("成功");
        response.setData("笑死");
        response.setAppToken("123token");
        response.setTokenValidTime("1秒");
        return response;
    }


    @Data
    @ToString
    public static class Request{
        private String appKey;
        private String appSecret;

    }

    /**
     * 获取动态令牌 3.2接口返回结果
     */
    @lombok.Data
    @ToString
    public class TokenResponse extends AbstractResponse{
        /** 动态数据访问令牌*/
        private String appToken;
        /** 动 态 数 据 访 问 令 牌 有 效 时 间 ， 7200 秒， 即 120 分钟。*/
        private String tokenValidTime;
    }


    /** 只包含基础返回 数据*/
    private class BaseData extends AbstractResponse{

    }

    @lombok.Data
    @ToString
    public abstract class AbstractResponse{
        /** 返回码, 0 为成功, 其它为失败*/
        private Integer code;
        /** 返回消息描述， 成功或失败的消息描述*/
        private String msg;
        /** 返回数据信息*/
        private String data;

        boolean isSuccess() {
            return code != null && code.equals(0);
        }

    }
}
