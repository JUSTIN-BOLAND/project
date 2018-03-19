package com.deyi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.deyi.dao.ActionLogMapper;
import com.deyi.entity.ActionLog;
import com.deyi.service.ActionLogService;
import com.deyi.util.Page;
import com.deyi.vo.ActionLogVo;


@Service
public class ActionLogServiceImpl implements ActionLogService{


	@Autowired
	private ActionLogMapper actionLogMapper;

	@Override
	public int savelog(String userid,String username, String type,String subtype ,String content) {
		ActionLog actionLog = new ActionLog();
		actionLog.setUserid(userid);
		actionLog.setType(type);
		actionLog.setContent(content);
		actionLog.setUsername(username);
		actionLog.setSubtype(subtype);
		int row = actionLogMapper.insertSelective(actionLog);
		return row;
	}

	@Override
	public List<ActionLogVo> selectAllLog(Page<ActionLogVo> page) {
		return actionLogMapper.selectByPage(page);
	}

}
