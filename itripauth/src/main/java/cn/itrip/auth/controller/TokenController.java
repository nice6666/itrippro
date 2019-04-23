package cn.itrip.auth.controller;

import cn.itrip.beans.dto.Dto;
import cn.itrip.beans.vo.ItripTokenVO;
import cn.itrip.auth.service.Token.TokenService;
import cn.itrip.common.DtoUtil;
import cn.itrip.common.ErrorCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

@Controller
@RequestMapping("/api")
public class TokenController {
    @Resource
    private TokenService tokenService;

    @RequestMapping(value = "/validateToken", method = RequestMethod.GET, headers = "token")
    @ResponseBody
    public Dto validate(HttpServletRequest request) {
        try {
            String agent=request.getHeader("user-agent");
            String token=request.getHeader("token");
            boolean result = tokenService.validate(agent,token);
            if (result) {
                return DtoUtil.returnSuccess("token有效");
            } else {
                return DtoUtil.returnSuccess("token无效");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail(e.getMessage(), ErrorCode.AUTH_UNKNOWN);
        }
    }
    @RequestMapping(value = "/reloadToken", method = RequestMethod.POST, headers = "token")
    @ResponseBody
    public Dto reloadToken (HttpServletRequest request) {
        try {
            String agent=request.getHeader("user-agent");
            String token1=request.getHeader("token");
            String token = this.tokenService.reloadToken(agent,token1);
            ItripTokenVO vo = new ItripTokenVO(token, Calendar.getInstance().getTimeInMillis() + 2 * 60 * 60 * 1000,  Calendar.getInstance().getTimeInMillis());
            return DtoUtil.returnDataSuccess(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail(e.getMessage(),ErrorCode.AUTH_UNKNOWN);
        }
    }

}


