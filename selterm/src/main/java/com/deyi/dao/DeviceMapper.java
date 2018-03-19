package com.deyi.dao;

import com.deyi.entity.Dealer;
import com.deyi.entity.DealerPay;
import com.deyi.entity.Device;
import com.deyi.util.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DeviceMapper {
    List<Device> getPageDevices(Page<Device> page);
    List<Device> getDevices(Device device);
    Device getDeviceByCode(Device device);
    Device getDevice(Integer id);
    int insert(Device device);
    int update(Device device);
    int delete(Integer id);
    @Select("SELECT b.* FROM t_device a JOIN t_dealer b ON a.dealerId = b.id AND a.deviceCode=#{deviceCode}")
    Dealer getDealerByDeviceCode(@Param("deviceCode") String deviceCode);
    @Select("SELECT b.* FROM t_device a JOIN t_dealer b ON a.dealerId = b.id AND a.id=#{deviceId}")
    Dealer getDealerByDeviceId(@Param("deviceId") String deviceId);
}
