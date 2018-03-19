package com.deyi.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.deyi.entity.InheritRecordSheet;
import com.deyi.service.InheritRecordSheetService;
import com.deyi.util.Component;
import com.deyi.util.Page;

@Controller
@RequestMapping(value = "inheritRecordSheet")
public class InheritRecordSheetController extends Component<InheritRecordSheet> {
	
//	private Logger LOG = LoggerFactory.getLogger(InheritRecordSheetController.class);
	
	@Autowired
	private InheritRecordSheetService inheritRecordSheetService;


	@RequestMapping(value = "list")
	public ModelAndView list(ModelAndView mav) {
		mav.setViewName("inheritRecordSheet/inheritList");
		return mav;
	}
	
	@RequestMapping(value = "page")
	public ModelAndView page(ModelAndView mav, HttpServletRequest request, InheritRecordSheet inheritRecordSheet, Page<InheritRecordSheet> page) {
		setParams(request, inheritRecordSheet, page);
		List<InheritRecordSheet> inheritRecordSheets = inheritRecordSheetService.getInheritRecordSheets(page);
		page.setResults(inheritRecordSheets);
		mav.addObject("page", page);
		mav.setViewName("inheritRecordSheet/pageInherit");
		return mav;
	}
	
}
