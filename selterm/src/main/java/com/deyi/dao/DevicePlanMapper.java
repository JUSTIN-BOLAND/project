package com.deyi.dao;

import com.deyi.entity.DeviceType;
import com.deyi.entity.RechargePlan;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by root on 2018/1/31 0031.
 */
@Repository("devicePlanMapper")
public interface DevicePlanMapper {
    @Insert("insert into t_device_plan select ${deviceId},a.id from t_recharge_plan a where id in(${planIds})")
    public int  insert(@Param("deviceId") int deviceId,@Param("planIds") String planIds);

    @Delete("delete from t_device_plan where deviceId=#{deviceId}")
    public int  delete(@Param("deviceId") int deviceId);
    @Select("select b.* from t_device_plan a inner join t_recharge_plan b on a.planId = b.id  where a.deviceId=#{deviceId}")
    public List<RechargePlan> getRechargePlans(@Param("deviceId") int deviceId);
}
