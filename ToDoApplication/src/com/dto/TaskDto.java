package com.dto;

import java.io.Serializable;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tasks")
public class TaskDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4491100713872839842L;

	public enum TaskStatus{
		PENDING,
		COMPLETED
	}
	@Id
	private Integer taskId;
	
	private String taskDescription;
	
	private Date pendingDate;
	
	private TaskStatus taskStatus;
	
	public TaskDto(Integer taskId,String taskDescription,Date pendingDate,TaskStatus taskStatus){
		this.taskId = taskId;
		this.taskDescription = taskDescription;
		this.pendingDate = pendingDate;
		this.taskStatus = taskStatus;
	}

	public TaskDto() {
		// TODO Auto-generated constructor stub
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public Date getPendingDate() {
		return pendingDate;
	}

	public void setPendingDate(Date pendingDate) {
		this.pendingDate = pendingDate;
	}

	public TaskStatus getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(TaskStatus taskStatus) {
		this.taskStatus = taskStatus;
	}

}
