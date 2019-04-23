package cn.itrip.dao.itripLabelDic;

import cn.itrip.beans.pojo.ItripLabelDic;
import cn.itrip.beans.vo.ItripLabelDicVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItripLabelDicMapper {

    public List<ItripLabelDic> getItripLabelDicListByMap(Map<String,Object> param)throws Exception;
    public List<ItripLabelDicVO> getItripLabelDicByParentId(@Param(value = "parentId") Long parentId)throws Exception;
}
