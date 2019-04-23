package cn.itrip.service.orderlinkuser.serviceImpl;

import cn.itrip.beans.vo.order.ItripOrderLinkUserVo;
import cn.itrip.dao.orderlinkuser.ItripOrderLinkUserMapper;
import cn.itrip.service.orderlinkuser.ItripOrderLinkUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
@Service("itripOrderLinkUserService")
public class ItripOrderLinkUserServiceImpl implements ItripOrderLinkUserService {
    @Resource
    private ItripOrderLinkUserMapper itripOrderLinkUserMapper;
    @Override
    public List<Long> getItripOrderLinkUserIdsByOrder() throws Exception {
        return itripOrderLinkUserMapper.getItripOrderLinkUserIdsByOrder();
    }

    @Override
    public List<ItripOrderLinkUserVo>  getItripOrderLinkUserListByMap(Map<String,Object> param)throws Exception{
        return itripOrderLinkUserMapper.getItripOrderLinkUserListByMap(param);
    }

}
