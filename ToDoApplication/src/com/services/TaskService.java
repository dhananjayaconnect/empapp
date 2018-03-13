package com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.TaskDto;
import com.idao.TaskDao;
import com.iservices.ITaskService;

 
@Service("taskService")
public class TaskService implements ITaskService {
 
    @Autowired
    TaskDao taskDao;
 
    public void create(TaskDto task) {
        taskDao.create(task);
    }
 
    public void update(TaskDto task) {
        taskDao.update(task);
    }
 
    public void delete(TaskDto task) {
        taskDao.delete(task);
    }
 
    public List < TaskDto > findAll() {
        return taskDao.findAll();
    }
 
    public TaskDto find(TaskDto task) {
        return taskDao.find(task);
    }
 
    public void deleteAll() {
        taskDao.deleteAll();
    }
}
