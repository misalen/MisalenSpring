package org.misalen.generator.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.misalen.common.advice.structure.RestResult;
import org.misalen.generator.domain.SysFlowNode;
import org.misalen.generator.domain.SysFlowNodeAtta;
import org.misalen.generator.service.SysFlowNodeAttaService;
import org.misalen.generator.service.SysFlowNodeService;
import org.misalen.system.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author DO·VIS
 *
 */
@Controller
@RequestMapping("/sys/flow/designer")
public class SysFlowDesignerController extends BaseController {

	@Autowired
	public SysFlowNodeService sysFlowNodeService;
	@Autowired
	public SysFlowNodeAttaService sysFlowNodeAttaService;

	@GetMapping("/{primaryKey}")
	public ModelAndView home(@PathVariable String primaryKey) {
		ModelAndView andView = new ModelAndView("sys/flow/designer/index");
		andView.addObject("primaryKey", primaryKey);
		return andView;
	}

	@PostMapping("/save/{primaryKey}")
	@Transactional
	public @ResponseBody RestResult<?> add(@PathVariable String primaryKey,
			@RequestBody List<SysFlowNode> sysFlowNodes) {
		List<SysFlowNode> flowNodes = sysFlowNodeService.findByFlowId(primaryKey);
		for (SysFlowNode sysFlowNode : flowNodes) {
			sysFlowNodeService.delete(sysFlowNode);
		}
		Map<String, String> map = new HashMap<String, String>();
		Set<SysFlowNodeAtta> set = new HashSet<SysFlowNodeAtta>();
		for (SysFlowNode sysFlowNode : sysFlowNodes) {
			sysFlowNode.setAddTime(new Date());
			if (sysFlowNode.getSysFlowNodeAttas() != null) {
				set.addAll(sysFlowNode.getSysFlowNodeAttas());
			}
			sysFlowNode.setSysFlowNodeAttas(null);
			SysFlowNode sysFlowNode2 = sysFlowNodeService.save(sysFlowNode);
			map.put(sysFlowNode.getPrimaryKey(), sysFlowNode2.getPrimaryKey());
		}
		for (SysFlowNodeAtta flowNodeAtta : set) {
			flowNodeAtta.setPrimaryKey(null);
			flowNodeAtta.setAddTime(new Date());
			flowNodeAtta.setNodeId(map.get(flowNodeAtta.getNodeId()));
			flowNodeAtta.setTargetId(map.get(flowNodeAtta.getTargetId()));
			sysFlowNodeAttaService.save(flowNodeAtta);
		}
		return renderSuccess();
	}

	@PostMapping("/add")
	public @ResponseBody RestResult<?> add(@RequestBody SysFlowNode sysFlowNode) {
		boolean allow = sysFlowNodeService.allowAdd(sysFlowNode.getNodeType(), sysFlowNode.getFlowId());
		if (allow) {
			sysFlowNode.setAddTime(new Date());
			return renderSuccess(sysFlowNodeService.save(sysFlowNode));
		} else {
			return renderError("只能有一个开始或者结束");
		}

	}

	@PostMapping("/get/{primaryKey}")
	public @ResponseBody RestResult<?> get(@PathVariable String primaryKey) {
		List<SysFlowNode> sysFlowNode = sysFlowNodeService.findByFlowId(primaryKey);
		return renderSuccess(sysFlowNode);
	}
}
