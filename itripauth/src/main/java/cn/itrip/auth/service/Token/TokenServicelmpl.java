package cn.itrip.auth.service.Token;

import cn.itrip.beans.pojo.ItripUser;
import cn.itrip.common.MD5;
import cn.itrip.common.RedisAPI;
import com.alibaba.fastjson.JSON;
import nl.bitwalker.useragentutils.UserAgent;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service("tokenService")
public class TokenServicelmpl implements TokenService{
    @Resource
    private RedisAPI redisAPI;
    private long expireTime =2*60*60*1000;
    private int delay=2*60;
    private int friTime=5*60*1000;
    public String generateToken(String userAgent, ItripUser user) {
        StringBuilder str=new StringBuilder("token:");
        UserAgent agent = UserAgent.parseUserAgentString(userAgent);
        if (agent.getOperatingSystem().isMobileDevice())
        {
            str.append("MOBILE-");
        }
        else{
            str.append("PC-");
        }
        str.append(MD5.getMd5(user.getUserCode(),32) + "-");
        str.append(user.getId().toString() + "-");
        str.append(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "-");
        str.append(MD5.getMd5(userAgent, 6));
        return str.toString();


    }

    public void save(String token, ItripUser user) {
        if (token.startsWith("token:PC-")) {  //PC端, 有效期是以【秒】为单位。
            redisAPI.set(token, 2 * 60 * 60, JSON.toJSONString(user));
        } else {  //移动端, 当不设置有效期，默认是一直有效.
            redisAPI.set(token, JSON.toJSONString(user));
        }
    }

    public boolean validate(String userAgent, String token) {
        if (!redisAPI.exist(token)) {
            return false;
        }
        String agentMD5=token.split("-")[4];
        if (!MD5.getMd5(userAgent,6).equals(agentMD5))
        {
            return false;
        }
        return true;
    }

    @Override
    public void delete(String token) {
        redisAPI.delete(token);
    }

    @Override
    public String reloadToken(String userAgent, String token) throws Exception {
        if (!redisAPI.exist(token))
        {
            throw  new Exception("token无效");
        }
        Date genTime=new SimpleDateFormat("yyyyMMddHHmmss").parse(token.split("-")[3]);//获取token的生成时间
        long activeTime=genTime.getTime()+expireTime;//token的有效期
        long passed=activeTime- Calendar.getInstance().getTimeInMillis();//过期时间-当前时间
        if (passed>friTime)
        {
            throw  new Exception("处在token置换保护期内，不能置换,剩余时间为"+passed/1000+"s");
        }
        /*
          进行置换
         */
        String userString=redisAPI.get(token);//得到用户字符串
        ItripUser user=JSON.parseObject(userString,ItripUser.class);
        String newToken=generateToken(userAgent,user);//生成新的token
        redisAPI.set(token,delay,JSON.toJSONString(userString));
        this.save(newToken,user);
        return newToken;
    }

}

