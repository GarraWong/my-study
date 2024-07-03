package com.wong.config;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.config.SentinelWebMvcConfig;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 这是类的描述 补充它
 *
 * @author : Wym's Code coding MacBook pro 2020 Silicon
 * @date : 2024/6/26 10:19
 */
@Configuration
public class SentielConfig  implements SmartInitializingSingleton, ApplicationContextAware {


    private ApplicationContext applicationContext;

    @Override
    public void afterSingletonsInstantiated(){
        SentinelWebMvcConfig contextBean = applicationContext.getBean(SentinelWebMvcConfig.class);
        contextBean.setBlockExceptionHandler(new CustomExceptionHandler());
        List<FlowRule> ruleList = null;
        try {
            ruleList = convertJson2List();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for (FlowRule rule : ruleList) {
            rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
            rule.setCount(1);
        }
        FlowRuleManager.loadRules(ruleList);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    private List<FlowRule> convertJson2List() throws Exception{
        InputStream inputStream = new ClassPathResource("config.json").getInputStream();
        BufferedReader reader = IoUtil.getUtf8Reader(inputStream);
        StringBuilder builder = new StringBuilder();
        try {
            String line;
            while (true) {
                line = reader.readLine();
                if (line == null) {
                    break;
                }
                builder.append(line);
            }
        } catch (IOException e) {
            throw new IORuntimeException(e);
        } finally {
            IoUtil.close(reader);
        }
        ObjectMapper mapper = new ObjectMapper();
        List<FlowRule> ruleList = mapper.readValue(builder.toString(), new TypeReference<List<FlowRule>>() {});
        return ruleList;
    }

    private class CustomExceptionHandler implements BlockExceptionHandler{
        @Override
        public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
            String msg = "未知异常";
            int status = HttpStatus.INTERNAL_SERVER_ERROR.value();
            if (e instanceof FlowException) {
                msg = "请求被限流了";
            } else if (e instanceof ParamFlowException) {
                msg = "请求被热点参数限流";
            } else if (e instanceof DegradeException) {
                msg = "请求被降级了";
            } else if (e instanceof AuthorityException) {
                msg = "没有权限访问";
                status = HttpStatus.UNAUTHORIZED.value();
            }
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(status);
            response.getWriter().println("{\"msg\": " + msg + ", \"code\": " + status + "}");
        }
    }

}
