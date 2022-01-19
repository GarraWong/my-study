package com.wong.redisdemo;

import com.deepoove.poi.XWPFTemplate;
import com.wong.testdemo.Testdemo;
import com.wong.testdemo.service.ValidatorService;
import org.docx4j.Docx4J;
import org.docx4j.Docx4jProperties;
import org.docx4j.convert.out.ConversionFeatures;
import org.docx4j.convert.out.HTMLSettings;
import org.docx4j.convert.out.html.SdtToListSdtTagHandler;
import org.docx4j.convert.out.html.SdtWriter;
import org.docx4j.fonts.BestMatchingMapper;
import org.docx4j.fonts.IdentityPlusMapper;
import org.docx4j.fonts.Mapper;
import org.docx4j.fonts.PhysicalFonts;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;

/**
 * 输入类描述
 *
 * @author : WangYumou
 * @version : 1.0
 * Create in 2021/9/26 9:34
 */
@SpringBootTest(classes = Testdemo.class)
public class ValidatorTest {

    private static final Logger logger = LoggerFactory.getLogger(ValidatorTest.class);

    @Autowired
    private ValidatorService validatorService;

    static {
        inputfilepath = "C:\\Users\\WangYumou\\Desktop\\出差报告.docx";
        save = true;
        nestLists = false;
    }

    static boolean save;
    static boolean nestLists;
    static String inputfilepath;

    @Test
    public void onlyInterface() {
        String name = validatorService.getName("");
        logger.info("接口调用返回结果:{}", name);
    }

    @Test
    public void testPoi() throws Exception{
        XWPFTemplate template = XWPFTemplate.compile("D:\\javaworkspace\\my-study\\testdemo\\target\\classes\\myfile\\template.docx").render(
                new HashMap<String, Object>(){{
                    put("id", "Hi, poi-tl Word模板引擎");
                    put("name", "模板引擎");
                }});
        template.writeToFile("C:\\Users\\WangYumou\\Desktop\\output.docx");
    }


    @Test
    public void testDocx4j() throws Exception{
        WordprocessingMLPackage wordMLPackage = Docx4J.load(new File(inputfilepath));

        HTMLSettings htmlSettings = Docx4J.createHTMLSettings();

        htmlSettings.setImageDirPath(inputfilepath + "_files");
        htmlSettings.setImageTargetUri(inputfilepath.substring(inputfilepath.lastIndexOf("/") + 1) + "_files");
        htmlSettings.setOpcPackage(wordMLPackage);

        String userCSS = null;
        if (nestLists) {
            // use browser defaults for ol, ul, li
            userCSS = "html, body, div, span, h1, h2, h3, h4, h5, h6, p, a, img,  table, caption, tbody, tfoot, thead, tr, th, td " +
                    "{ margin: 0; padding: 0; border: 0;}" +
                    "body {line-height: 1;} ";
        } else {
            userCSS = "html, body, div, span, h1, h2, h3, h4, h5, h6, p, a, img,  ol, ul, li, table, caption, tbody, tfoot, thead, tr, th, td " +
                    "{ margin: 0; padding: 0; border: 0;}" +
                    "body {line-height: 1;} ";
        }
        htmlSettings.setUserCSS(userCSS);


        Mapper fontMapper = new IdentityPlusMapper(); // better for Windows
//        Mapper fontMapper = new BestMatchingMapper(); // better for Linux
        wordMLPackage.setFontMapper(fontMapper);
//        fontMapper.put("DengXian", PhysicalFonts.get("等线"));




        if (nestLists) {
            SdtWriter.registerTagHandler("HTML_ELEMENT", new SdtToListSdtTagHandler());
        } else {
            htmlSettings.getFeatures().remove(ConversionFeatures.PP_HTML_COLLECT_LISTS);
        }

        OutputStream os;
        if (save) {
            os = new FileOutputStream(inputfilepath + ".html");
        } else {
            os = new ByteArrayOutputStream();
        }

        Docx4jProperties.setProperty("docx4j.Convert.Out.HTML.OutputMethodXML", true);

        Docx4J.toHTML(htmlSettings, os, Docx4J.FLAG_EXPORT_PREFER_XSL);

        if (save) {
            System.out.println("Saved: " + inputfilepath + ".html ");
        } else {
            System.out.println(((ByteArrayOutputStream) os).toString());
        }

        if (wordMLPackage.getMainDocumentPart().getFontTablePart() != null) {
            wordMLPackage.getMainDocumentPart().getFontTablePart().deleteEmbeddedFontTempFiles();
        }
        htmlSettings = null;
        wordMLPackage = null;
    }

}
