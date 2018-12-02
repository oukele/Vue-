package com.oukele.serve;

import com.oukele.dao.TodoMapper;
import com.oukele.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServe  {
    @Autowired
    private TodoMapper todoMapper;

    public List<Todo> list(){
        return todoMapper.selectAll();
    };

    public int updateStatus(Todo todo){
        return todoMapper.updateStatus(todo);
    }

    public int insert(Todo todo){
        return todoMapper.insert(todo);
    }

    public int delete(int id){
        return todoMapper.deleteByPrimaryKey(id);
    }

}
