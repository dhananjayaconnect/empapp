package com.handler;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.bson.types.ObjectId;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.config.ApplicationConfig;
import com.dto.TaskDto;
import com.dto.TaskDto.TaskStatus;
import com.dto.ToDoAppDTO;
import com.iservices.ITaskService;

public class ToDoAppHandler {
	
	private static final String PENDING_DATE = "pendingDate";
	private static final String ROWS = "rows";
	private static final String PAGE = "page";
	private static final String DESCRIPTION = "description";
	
	AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
	ITaskService taskService = (ITaskService) context.getBean("taskService");

	public void saveData(final HttpServletRequest req) {
		
	}
	
	public ToDoAppDTO<TaskDto> loadTasks(final HttpServletRequest req) {

		int page = Integer.valueOf(req.getParameter(PAGE)).intValue();
		int pageSize = Integer.valueOf(req.getParameter(ROWS)).intValue();
		/*String filters = req.getParameter("filters");
		if(filters != null && filters.length() > 0){
			JSONReader jsonReader = new JSONReader();
			try {
				HashMap<String, Object> obj = (HashMap<String, Object>)jsonReader.read(filters);
				List<Object> rules = (List<Object>)obj.get("rules");
				String value = null;
				for(Object rule : rules){
					value =((HashMap<Object, Object>)rule).get("data").toString();
					break;
				}
				System.out.println(value);

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		int total = -1;		

		List < TaskDto > taskList = taskService.findAll();		
		ToDoAppDTO<TaskDto> jqGridData = new ToDoAppDTO<TaskDto>();
		total = taskList.size();
		jqGridData.setPage(page);
		jqGridData.setTotal(String.valueOf(Math.ceil((double) total / pageSize)));
		jqGridData.setRecords(String.valueOf(total));
		jqGridData.setRows(taskList);
		return jqGridData;
	}
	
	public ToDoAppDTO<TaskDto> saveTasks(final HttpServletRequest req) {
	
		int page = Integer.valueOf(req.getParameter(PAGE)).intValue();
		int pageSize = Integer.valueOf(req.getParameter(ROWS)).intValue();
		int total = -1;

		String description = req.getParameter(DESCRIPTION);
		String pendingDate = req.getParameter(PENDING_DATE);
		
		ObjectId taskid = ObjectId.get();
		TaskDto dto1 = new TaskDto(taskid.getCounter(), description, new Date(pendingDate), TaskStatus.PENDING);		
		taskService.create(dto1);		
		List < TaskDto > taskList = taskService.findAll();
		ToDoAppDTO<TaskDto> jqGridData = new ToDoAppDTO<TaskDto>();
		total = taskList.size();
		jqGridData.setPage(page);
		jqGridData.setTotal(String.valueOf(Math.ceil((double) total / pageSize)));
		jqGridData.setRecords(String.valueOf(total));
		jqGridData.setRows(taskList);
		return jqGridData;
	}
	
	public ToDoAppDTO<TaskDto> deleteTasks(final HttpServletRequest req) {

		int page = Integer.valueOf(req.getParameter(PAGE)).intValue();
		int pageSize = Integer.valueOf(req.getParameter(ROWS)).intValue();
		int total = -1;

		String taskIdList = req.getParameter("taskIdList");
		String[] taskIdStringList = taskIdList.split(",");
		TaskDto tempTaskDto =null;
		for(String taskId : taskIdStringList){
			tempTaskDto = new TaskDto();
			tempTaskDto.setTaskId(Integer.parseInt(taskId));
			tempTaskDto = taskService.find(tempTaskDto);			
			taskService.delete(tempTaskDto);
		}	
		
		ToDoAppDTO<TaskDto> jqGridData = new ToDoAppDTO<TaskDto>();
		List < TaskDto > taskList = taskService.findAll();
		total = taskList.size();
		jqGridData.setPage(page);
		jqGridData.setTotal(String.valueOf(Math.ceil((double) total / pageSize)));
		jqGridData.setRecords(String.valueOf(total));
		jqGridData.setRows(taskList);
		return jqGridData;
	}
	
	public ToDoAppDTO<TaskDto> changeStatus(final HttpServletRequest req) {
		int page = Integer.valueOf(req.getParameter(PAGE)).intValue();
		int pageSize = Integer.valueOf(req.getParameter(ROWS)).intValue();
		int total = -1;

		String taskIdList = req.getParameter("taskIdList");
		String[] taskIdStringList = taskIdList.split(",");
		TaskDto tempTaskDto =null;
		for(String taskId : taskIdStringList){
			tempTaskDto = new TaskDto();
			tempTaskDto.setTaskId(Integer.parseInt(taskId));
			tempTaskDto = taskService.find(tempTaskDto);	
			tempTaskDto.setTaskStatus(TaskStatus.COMPLETED);
			taskService.update(tempTaskDto);
		}
		
		ToDoAppDTO<TaskDto> jqGridData = new ToDoAppDTO<TaskDto>();
		List < TaskDto > taskList = taskService.findAll();
		total = taskList.size();
		jqGridData.setPage(page);
		jqGridData.setTotal(String.valueOf(Math.ceil((double) total / pageSize)));
		jqGridData.setRecords(String.valueOf(total));
		jqGridData.setRows(taskList);
		return jqGridData;
	}
}
