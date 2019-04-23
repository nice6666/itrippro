package cn.itrip.auth.service.user;

import cn.itrip.beans.pojo.ItripUser;

public interface UserService {
    public ItripUser login(String userCode, String userPassword)throws Exception;
    public void insertUser(ItripUser user)throws Exception;
    public ItripUser findByUsername(String username);
    public boolean activate(String mail,String code)throws Exception;
    public void insertUserByPhone(ItripUser user) throws Exception;
    public boolean validatePhone(String phoneNum,String code)throws Exception;
}
