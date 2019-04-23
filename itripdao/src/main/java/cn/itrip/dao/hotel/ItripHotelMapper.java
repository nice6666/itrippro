package cn.itrip.dao.hotel;

import cn.itrip.beans.pojo.ItripAreaDic;
import cn.itrip.beans.pojo.ItripHotel;
import cn.itrip.beans.pojo.ItripLabelDic;
import cn.itrip.beans.vo.hotel.ItripSearchDetailsHotelVO;
import cn.itrip.beans.vo.hotel.ItripSearchFacilitiesHotelVO;
import cn.itrip.beans.vo.hotel.ItripSearchPolicyHotelVO;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface ItripHotelMapper {
    public List<ItripLabelDic> getHotelFeatureByHotelId(@Param(value = "id")long id)throws Exception;
    public List<ItripAreaDic> getHotelAreaByHotelId(@Param(value = "id")long id)throws Exception;
    public ItripHotel getItripHotelById(@Param(value = "id") Long id)throws Exception;
    public ItripSearchFacilitiesHotelVO getItripHotelFacilitiesById(@Param(value = "id") Long id) throws Exception;
    public ItripSearchPolicyHotelVO queryHotelPolicy(Long id)throws Exception;
    public List<ItripSearchDetailsHotelVO> queryHotelDetails(Long id)throws Exception;

}
