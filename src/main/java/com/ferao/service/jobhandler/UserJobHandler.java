package com.ferao.service.jobhandler;/*
 * @author Ferao
 * @date
 * @discription
 */

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserJobHandler {

    private static Logger logger = LoggerFactory.getLogger(UserJobHandler.class);
    @XxlJob(value="SampleXxlJobHandler")
    public ReturnT<String> execute(String s) throws Exception {
        System.out.println("ferao xxljob paramter --"+s);

        JSONObject jsonObject = JSONObject.parseObject(s);
        String token = (String)jsonObject.get("token");
        int amount = Integer.parseInt((String) jsonObject.get("amount"));
        System.out.println(token+"--"+amount);
        JSONArray merNoList = jsonObject.getJSONArray("merNoList");
        for (Object o : merNoList){
            System.out.println(o);
        }

        ReturnT<String> objectReturnT = new ReturnT<>();
        //SUCCESS_CODE
        objectReturnT.setCode(ReturnT.SUCCESS_CODE);
        int code = objectReturnT.getCode();
        System.out.println("getCode.."+code);
        //setContent getContent
        objectReturnT.setContent("ferao xxljob Content 已执行 ..");
        String content = objectReturnT.getContent();
        System.out.println("getContent.."+content);
        //getMsg setMsg
        objectReturnT.setMsg("ferao xxljob Msg 任务执行成功 ..");
        String msg = objectReturnT.getMsg();
        System.out.println("getMsg.."+msg);
        //toString
        String s1 = objectReturnT.toString();
        System.out.println("getToString.."+s1);
        return objectReturnT;
    }
}
