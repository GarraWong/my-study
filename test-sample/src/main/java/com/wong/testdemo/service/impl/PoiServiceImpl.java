package com.wong.testdemo.service.impl;

import com.wong.testdemo.service.IPoiService;
import org.springframework.stereotype.Service;

/**
 * 这是类注释 完善它
 *
 * @author : wangyumou's Dell inspiration
 * @date : 2022/1/14 12:31
 */
@Service
public class PoiServiceImpl  implements IPoiService {


    @Override
    public String docxToHtml(String filename) {
        return filename;
    }

}
