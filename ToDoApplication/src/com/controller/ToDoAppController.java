package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.common.util.JsonUtil;
import com.dto.TaskDto;
import com.dto.ToDoAppDTO;
import com.handler.ToDoAppHandler;

@Controller
public class ToDoAppController {

	/**
	 * This method will display the page used to display the grid.
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET }, path = "/toDoApplication")
	public String jqGrid(HttpServletRequest req, HttpServletResponse res) {
		String forward = "appto/toDoApplication";

		return forward;
	}

	/**
	 * This method will handle fetching data required for the JQGrid.
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET }, path = "/loadData")
	public String loadData(HttpServletRequest req, HttpServletResponse res) {
		String forward = "common/formData";

		ToDoAppDTO<TaskDto> gridData = new ToDoAppHandler().loadTasks(req);
		req.setAttribute("formData", JsonUtil.toJsonObj(gridData));
		return forward;
	}
	
	@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET }, path = "/saveData")
	public String saveData(HttpServletRequest req, HttpServletResponse res) {
		String forward = "common/formData";

		ToDoAppDTO<TaskDto> gridData = new ToDoAppHandler().saveTasks(req);
		req.setAttribute("formData", JsonUtil.toJsonObj(gridData));
		return forward;
	}
	
	@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET }, path = "/deleteTask")
	public String deleteData(HttpServletRequest req, HttpServletResponse res) {
		String forward = "common/formData";

		ToDoAppDTO<TaskDto> gridData = new ToDoAppHandler().deleteTasks(req);
		req.setAttribute("formData", JsonUtil.toJsonObj(gridData));
		return forward;
	}
	
	@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET }, path = "/changeStatus")
	public String changeStatus(HttpServletRequest req, HttpServletResponse res) {
		String forward = "common/formData";

		ToDoAppDTO<TaskDto> gridData = new ToDoAppHandler().changeStatus(req);
		req.setAttribute("formData", JsonUtil.toJsonObj(gridData));
		return forward;
	}	

}
