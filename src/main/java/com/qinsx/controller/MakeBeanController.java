package com.qinsx.controller;

import com.qinsx.bean.Templete;
import com.qinsx.service.MakeBeanServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by shuxinqin
 * DATE : 18-2-5
 * TIME : 下午3:36
 * PROJECT : convenienceTools
 * PACKAGE : com.qsx.autoTools.controller
 *
 * @author <a href="mailto:shuxin.qin@qunar.com">shuxin.qin</a>
 */
@Controller
public class MakeBeanController {

    @Resource
    private MakeBeanServiceImpl makeBeanService;

    @RequestMapping(value = "/makeBean")
    public String home(){

        System.out.println("==========================");
        return "makeBean";
    }

    @RequestMapping(value = "/upload", method = { RequestMethod.POST })
    public String upload(Map<String, Object> model, @RequestParam(value = "createTable") String createTable)
            throws UnsupportedEncodingException {

        //createTable = new String(createTable.getBytes("8859_1"), "utf8");
        System.out.println("---------------------------");
        Templete templete = makeBeanService.makeBeanByCreateTableString(createTable);
        model.put("bean", templete.getModel());
        model.put("mapper", templete.getMapper());
        model.put("xml", templete.getXml());
        model.put("source", createTable);
        return "makeBean";
    }
}
