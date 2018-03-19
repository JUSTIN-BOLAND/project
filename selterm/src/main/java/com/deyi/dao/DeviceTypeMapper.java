package com.deyi.dao;

import com.deyi.entity.DeviceType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by root on 2018/1/31 0031.
 */
@Repository("deviceTypeMapper")
public interface DeviceTypeMapper {

    @Select("select * from t_dic_device_type")
    public List<DeviceType> getDeviceTypes();
}
