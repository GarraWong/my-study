package com.wong.redisdemo.controller;

import com.wong.redisdemo.model.PayVo;
import com.wong.redisdemo.service.ValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 输入类描述
 *
 * @author : WangYumou
 * @version : 1.0
 * Create in 2021/9/26 10:01
 */
@RestController
@RequestMapping("/root")
public class ValidatorController {

    @Autowired
    private ValidatorService validatorService;

    @PostMapping("/pay")
    public String pay(HttpServletRequest request) {
        PayVo vo = new PayVo();
        String tradeid = request.getParameter("tradeid");
        vo.setTradeid(tradeid);
        vo.setBusid(request.getParameter("busid"));
        vo.setListno(request.getParameter("listno"));
        vo.setAmount(Long.valueOf(request.getParameter("amount")));
        vo.setEntime("");
        vo.setExtime("");
        vo.setRemark("");
        vo.setRemark1("");
        String pay = validatorService.pay(vo);
        return pay;
    }
}
