package cn.itrip.auth.service.user;

import cn.itrip.auth.service.Sms.SmsService;
import cn.itrip.beans.pojo.ItripUser;
import cn.itrip.auth.dao.user.ItripUserMapper;
import cn.itrip.auth.service.mail.MailService;
import cn.itrip.common.MD5;
import cn.itrip.common.RedisAPI;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService{
    @Resource
    private ItripUserMapper itripUserMapper;
    @Resource
    private RedisAPI redisAPI;
    @Resource
    private  MailService mailService;
    @Resource
    private SmsService smsService;
    public ItripUser findByUsername(String username){
        Map<String, Object> param=new HashMap();
        param.put("userCode", username);
        List<ItripUser> list= itripUserMapper.getItripUserListByMap(param);
        if(list.size()>0)
            return list.get(0);
        else
            return null;
    }

    @Override
    public boolean activate(String mail, String code) throws Exception {
        String value=redisAPI.get("activation:"+mail);
        if (value.equals(code))
        {
            ItripUser user=findByUsername(mail);
            user.setActivated(1);
            user.setUserType(0);
            user.setFlatID(user.getId());
            itripUserMapper.updateItripUser(user);
            return true;
        }
        else{
            return false;
        }

    }

    @Override
    public void insertUserByPhone(ItripUser user) throws Exception {
      itripUserMapper.insertItripUser(user);
      int code =MD5.getRandomCode();
      smsService.sendMsg(user.getUserCode(),"1",new String[]{String.valueOf(code),"1"});
      redisAPI.set("activation:"+user.getUserCode(),2*60,String.valueOf(code));
    }

    @Override
    public boolean validatePhone(String phoneNum, String code) throws Exception {
        String key="activation:"+phoneNum;
        String value=redisAPI.get(key);
        if (value!=null&&value.equals(code))
        {
            ItripUser user=findByUsername(phoneNum);
            if (user!=null)
            {
                //更新用户激活状态
                user.setActivated(1);
                user.setFlatID(user.getId());
                user.setUserType(0);
                itripUserMapper.updateItripUser(user);
                return true;
            }
        }
        return false;
    }

    public ItripUser login(String userCode, String userPassword) throws Exception {
        ItripUser user = this.findByUsername(userCode);
        if(null !=user && user.getUserPassword().equals(userPassword)) {
            if(user.getActivated() != 1){
                throw new Exception("账号尚未激活！");
            }
            return user;
        }
        else {
            return null;
        }
    }

    @Override
    public void insertUser(ItripUser user) throws Exception {
        //1、添加用户
        itripUserMapper.insertItripUser(user);

        //2、生成激活码， new Date().toLocaleString()生成日期和时间，
        //格式如：2019-1-25 22:56:32，然后进行32位的MD5加密
        String activationCode = MD5.getMd5(new Date().toLocaleString(), 32);

        //3、发送邮件
        mailService.sendActivationMail(user.getUserCode(),activationCode);
        //4、激活码存入Redis中
        redisAPI.set("activation:" + user.getUserCode(),30 * 60, activationCode);
    }
}


