package com.example.tushu.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SmsController {
    String id = "111154";
    String smstoken = "1a6ea57199bd445c9ffb1002960554a5";
    String urlRegister = "https://api.kfd.pub/student/SmsRegister";
    String urReset = "https://api.kfd.pub/student/SmsReset";

    @PostMapping("/SmsRegister")
    public JSONObject SmsRegister(String mobile) {
        JSONObject postdata = new JSONObject();
        RestTemplate client = new RestTemplate();
        postdata.put("id", id);
        postdata.put("smstoken", smstoken);
        postdata.put("mobile", mobile);
        JSONObject res = client.postForEntity(urlRegister, postdata, JSONObject.class).getBody();
        return res;
    }

    @PostMapping("/SmsReset")
    public JSONObject SmsReset(String mobile) {
        JSONObject postdata = new JSONObject();
        RestTemplate client = new RestTemplate();
        postdata.put("id", id);
        postdata.put("smstoken", smstoken);
        postdata.put("mobile", mobile);
        JSONObject res = client.postForEntity(urReset, postdata, JSONObject.class).getBody();
        return res;
    }
}
