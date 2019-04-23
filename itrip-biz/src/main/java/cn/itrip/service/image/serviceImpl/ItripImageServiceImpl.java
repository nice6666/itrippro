package cn.itrip.service.image.serviceImpl;

import cn.itrip.beans.vo.ItripImageVO;
import cn.itrip.dao.image.ItripImageMapper;
import cn.itrip.service.image.ItripImageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
@Service("itripImageService")
public class ItripImageServiceImpl implements ItripImageService {
    @Resource
    private ItripImageMapper itripImageMapper;

    public List<ItripImageVO>  getItripImageListByMap(Map<String,Object> param)throws Exception{
        return itripImageMapper.getItripImageListByMap(param);
    }

}
