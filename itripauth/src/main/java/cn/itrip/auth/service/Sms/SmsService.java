package cn.itrip.auth.service.Sms;

public interface SmsService {
    public void sendMsg(String to,String templateId,String[] datas)throws Exception;
}
