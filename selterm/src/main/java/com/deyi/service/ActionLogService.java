package com.deyi.service;

import java.util.List;

import com.deyi.util.Page;
import com.deyi.vo.ActionLogVo;
import org.springframework.stereotype.Service;


public interface ActionLogService {

	public int savelog(String userid,String username,String tyep,String subtype,String content);

	List<ActionLogVo> selectAllLog(Page<ActionLogVo> page);

}
