package com.idao;

import java.util.List;

import com.dto.TaskDto;


 
public interface TaskDao {
 
    public void create(TaskDto task);
 
    public void update(TaskDto task);
 
    public void delete(TaskDto task);
 
    public void deleteAll();
 
    public TaskDto find(TaskDto task);
 
    public List < TaskDto > findAll();    

}