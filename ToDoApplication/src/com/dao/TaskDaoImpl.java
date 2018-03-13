package com.dao;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.dto.TaskDto;
import com.idao.TaskDao;

@Repository
@Qualifier("taskDao")
public class TaskDaoImpl implements TaskDao{ 
 
    @Autowired
    MongoTemplate mongoTemplate;
 
    final String COLLECTION = "tasks";
 
    public void create(TaskDto task) {
        mongoTemplate.insert(task);
    }
 
    public void update(TaskDto task) {
        mongoTemplate.save(task);
    }
 
    public void delete(TaskDto task) {
        mongoTemplate.remove(task);
    }
 
    public void deleteAll() {
        mongoTemplate.remove(new Query(), COLLECTION);
    }
 
    public TaskDto find(TaskDto task) {
        Query query = new Query(Criteria.where("_id").is(task.getTaskId()));
        return mongoTemplate.findOne(query, TaskDto.class, COLLECTION);
    }

 
    public List < TaskDto > findAll() {
        return (List < TaskDto > ) mongoTemplate.findAll(TaskDto.class);
    }

 
}