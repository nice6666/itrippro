package cn.itrip.auth.service.Token;

import cn.itrip.beans.pojo.ItripUser;

public interface TokenService {
    public String generateToken(String userAgent, ItripUser user);
    public void save(String token, ItripUser user);
    public boolean validate(String userAgent, String token);
    void delete(String token);
    String reloadToken(String userAgent,String token)throws Exception;
}
