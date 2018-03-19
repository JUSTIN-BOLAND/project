package com.deyi.dao;

import com.deyi.entity.RechargePlan;
import com.deyi.util.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import java.util.List;

import static com.mongodb.QueryOperators.WHERE;

@Repository
public interface RechargePlanMapper {
    List<RechargePlan> getPageRechargePlans(Page<RechargePlan> page);
    List<RechargePlan> getRechargePlans(RechargePlan rechargePlan);
    RechargePlan getRechargePlan(Integer id);
    int insert(RechargePlan rechargePlan);
    int update(RechargePlan rechargePlan);
    int delete(Integer id);
    RechargePlan getRechargePlanByCode(RechargePlan rechargePlan);
    @Select("SELECT 	b.* FROM t_device_plan a inner JOIN t_recharge_plan b ON a.planId = b.id WHERE a.deviceId=#{deviceId}")
    List<RechargePlan> getRechargePlansByDeviceId(@Param("deviceId") int deviceId);

}
