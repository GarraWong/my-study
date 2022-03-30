package com.wong.testdemo.model.validator;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 这是类注释 完善它
 *
 * @author : wangyumou's Dell inspiration
 * @date : 2022/3/1 17:38
 */
public class ExistModel {

    @NotBlank(message = "名称不能为空")
    private String name;

    @Valid
    private List<ExistSubModel> subs;

}
