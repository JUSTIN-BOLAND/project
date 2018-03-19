package com.deyi.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.deyi.service.ActionLogService;
import com.deyi.util.Component;
import com.deyi.util.Page;
import com.deyi.vo.ActionLogVo;

@RequestMapping(value="log")
@Controller
public class ActionLogContrller extends Component<ActionLogVo>{


	@Autowired

	private ActionLogService actionLogService;

	@RequestMapping(value="view")
	public ModelAndView view(ModelAndView mav){
		mav.setViewName("log/loglist");
		return mav;
	}

	@RequestMapping(value="page")
	public ModelAndView page(ModelAndView mav,ActionLogVo actiovo,Page<ActionLogVo> page,HttpServletRequest request){

		setParams(request, actiovo, page);
		List<ActionLogVo> results = actionLogService.selectAllLog(page);
		page.setResults(results);
		mav.addObject("page", page);
		mav.setViewName("log/logpage");
		return mav;
	}

}
