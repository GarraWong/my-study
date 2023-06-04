package com.zjpl.system.webservice;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(DateUtil.now());
        MdmServiceImplService service = new MdmServiceImplService();
        RequestMessage requestMessage = new RequestMessage();
        requestMessage.setVerb("GET");
       requestMessage.setNoun("PSNBASDOC");
//        requestMessage.setNoun("ORGNIZITION");
//        requestMessage.setNoun("PSNDOC");
//        requestMessage.setNoun("CONTRACT");
//        requestMessage.setNoun("JOB");
//        requestMessage.setNoun("PROJECT");
//         requestMessage.setNoun("ITEM");
        requestMessage.setOperationtype("1");
        requestMessage.setMdmid(null);
        requestMessage.setSyscode("CMIBD");
        File file = new File("/Users/cym/Downloads/D/ITEM.txt");
        // FileWriter fileWriter = new FileWriter(file);
        MdmService port = service.getMdmServiceImplPort();
        System.out.println(DateUtil.now());

        String txt = port.getData(requestMessage).getResult();
        System.out.println(txt.length());
        System.out.println(DateUtil.now());

        // fileWriter.write(txt);
        // fileWriter.flush();
        // fileWriter.close();


//        JSONArray jsonArray = JSON.parseArray(txt);
//        System.out.println(jsonArray.size());
//        System.out.println((int) jsonArray.stream().filter(i -> {
//            return "2".equals(((JSONObject) i).getString("CONTRACT_CAT"));
//        }).count());
    }
}