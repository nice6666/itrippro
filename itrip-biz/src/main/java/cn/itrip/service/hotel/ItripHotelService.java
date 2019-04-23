package cn.itrip.service.hotel;

import cn.itrip.beans.pojo.ItripHotel;
import cn.itrip.beans.vo.hotel.HotelVideoDescVO;
import cn.itrip.beans.vo.hotel.ItripSearchDetailsHotelVO;
import cn.itrip.beans.vo.hotel.ItripSearchFacilitiesHotelVO;
import cn.itrip.beans.vo.hotel.ItripSearchPolicyHotelVO;

import java.util.List;

public interface ItripHotelService {
    public HotelVideoDescVO getVideoDescByHotelId(long id) throws Exception;
    public ItripSearchPolicyHotelVO queryHotelPolicy(Long id)throws Exception;
    public List<ItripSearchDetailsHotelVO> queryHotelDetails(Long id)throws Exception;
    public ItripSearchFacilitiesHotelVO getItripHotelFacilitiesById(Long id)throws Exception;
    public ItripHotel getItripHotelById(Long id)throws Exception;
}
