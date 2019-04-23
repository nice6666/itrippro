package cn.itrip.service.labeldic.serviceImpl;


import cn.itrip.beans.pojo.ItripLabelDic;
import cn.itrip.beans.vo.ItripLabelDicVO;
import cn.itrip.dao.itripLabelDic.ItripLabelDicMapper;
import cn.itrip.service.labeldic.ItripLabelDicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
@Service("itripLabelDicService")
public class ItripLabelDicServiceImpl implements ItripLabelDicService {

    @Resource
    private ItripLabelDicMapper itripLabelDicMapper;

    @Override
    public List<ItripLabelDic> getItripLabelDicListByMap(Map<String, Object> param) throws Exception {
        return itripLabelDicMapper.getItripLabelDicListByMap(param);
    }

    @Override
    /**
     * 根据parentId查询数据字典
     * @param parentId
     */
    public List<ItripLabelDicVO> getItripLabelDicByParentId(Long parentId)throws Exception{
        return itripLabelDicMapper.getItripLabelDicByParentId(parentId);
    }

}
