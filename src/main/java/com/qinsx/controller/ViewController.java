package com.qinsx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.Map;

/**
 * Created by qinsx
 * DATE : 2019-07-04
 * TIME : 16:34
 * PROJECT : tools2.0
 * PACKAGE : com.qinsx.controller
 *
 * @author <a href="mailto:shuxin.qin@qunar.com">shuxin.qin</a>
 */
@Controller
@RequestMapping("/")
public class ViewController {

    @RequestMapping(value = "index")
    public String home(Map<String, Object> model){
        model.put("time", new Date());
        System.out.println("++++++++++++++++++++++++++");
        return "index";
    }
}
