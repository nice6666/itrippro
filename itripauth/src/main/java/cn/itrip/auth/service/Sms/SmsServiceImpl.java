package cn.itrip.auth.service.Sms;

import com.cloopen.rest.sdk.CCPRestSmsSDK;
import org.springframework.stereotype.Service;

import java.util.HashMap;
@Service("SmsService")
public class SmsServiceImpl implements SmsService{
    @Override
    public void sendMsg(String to, String templateId, String[] datas) throws Exception {
        CCPRestSmsSDK sdk=new CCPRestSmsSDK();
        //初始化开发者账号信息中的url
        sdk.init("app.cloopen.com","8883");
        sdk.setAccount("8aaf0708697b6beb01697f4c02350229","bc1d57f1eabc49a79b44499e83336e20");
        sdk.setAppId("8aaf0708697b6beb01697f4c028a022f");
        HashMap result=sdk.sendTemplateSMS(to,templateId,datas);
        //查看短信是否发送成功。就通过查看statusCoe的值是否为000000
        if ("000000".equals(result.get("statusCode"))) {
            System.out.println("短信发送成功");
        } else {
            throw new Exception(result.get("statusCode").toString() + ":" + result.get("statusMsg").toString());
        }
    }
}

