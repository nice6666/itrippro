package cn.itrip.auth.service.mail;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service("mailService")
public class MailSericeImpl implements MailService{
    @Resource
    private SimpleMailMessage activationMailMessage;
    @Resource
    private MailSender mailSender;
    @Override
    public void sendActivationMail(String mailTo, String activationCode) {
         activationMailMessage.setTo(mailTo);
         activationMailMessage.setText("您的激活码"+activationCode);
         mailSender.send(activationMailMessage);
    }
}
