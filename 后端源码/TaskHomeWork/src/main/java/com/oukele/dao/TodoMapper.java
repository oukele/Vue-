package com.oukele.dao;

import com.oukele.model.Todo;
import java.util.List;

public interface TodoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Todo record);

    Todo selectByPrimaryKey(Integer id);

    List<Todo> selectAll();

    int updateStatus(Todo todo);

    int updateByPrimaryKey(Todo record);
}