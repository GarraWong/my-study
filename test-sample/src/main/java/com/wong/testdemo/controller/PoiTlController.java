package com.wong.testdemo.controller;

import com.wong.testdemo.service.IPoiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 * 这是类注释 完善它
 *
 * @author : wangyumou's Dell inspiration
 * @date : 2022/1/14 10:37
 */
@RestController
@CrossOrigin
public class PoiTlController {

    private static final Logger logger = LoggerFactory.getLogger(PoiTlController.class);

    private final IPoiService poiService;

    public PoiTlController(IPoiService poiService) {
        this.poiService = poiService;
    }


}
