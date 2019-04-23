package cn.itrip.service.areadic.serviceImpl;

import cn.itrip.beans.pojo.ItripAreaDic;
import cn.itrip.dao.areadic.ItripAreaDicMapper;
import cn.itrip.service.areadic.ItripAreaDicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
@Service("itripareadicService")
public class ItripAreaDicServiceImpl implements ItripAreaDicService {
    @Resource
    private ItripAreaDicMapper itripAreaDicMapper;
    @Override
    public List<ItripAreaDic> getItripAreaDicListByMap(Map<String, Object> param) throws Exception {
        return itripAreaDicMapper.getItripAreaDicListByMap(param);
    }
}