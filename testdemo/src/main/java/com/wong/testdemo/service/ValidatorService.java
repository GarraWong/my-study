package com.wong.testdemo.service;

import com.wong.testdemo.model.PayVo;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * 测试验证仅service层使用hibernate-validator
 *
 * @author : WangYumou
 * @version : 1.0
 * Create in 2021/9/26 9:21
 */
@Validated
public interface ValidatorService {


    String getName(@NotBlank String name);

    String pay(@Valid PayVo vo);
}
