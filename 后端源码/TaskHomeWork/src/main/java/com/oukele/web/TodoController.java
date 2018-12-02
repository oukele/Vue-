package com.oukele.web;

import com.oukele.dao.TodoMapper;
import com.oukele.model.Todo;
import com.oukele.serve.TodoServe;
import com.oukele.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/todo")
public class TodoController {

    @Autowired
    private TodoServe todoServe;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public Result getData() {
        return Result.getResult(todoServe.list());
    }

    @RequestMapping(path = "/status/{id}/{status}", method = RequestMethod.GET)
    public Result update_status(@PathVariable("id") Integer id, @PathVariable("status") Integer status) {
        Todo todo = new Todo();
        todo.setId(id);
        todo.setState(status);
        if (todoServe.updateStatus(todo) > 0) {
            return Result.getResult("修改成功");
        }
        return Result.getResult("修改失败");
    }

    @RequestMapping(path = "/del/{id}", method = RequestMethod.GET)
    public Result del(@PathVariable("id") Integer id) {
        if( todoServe.delete(id) >0){
            return Result.getResult("删除成功");
        }
        return Result.getResult("删除失败");
    }

    @RequestMapping(path = "", method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public Result insert(@RequestBody List<Todo> todo) {
        boolean flag = false;
        for (Todo todo1 : todo) {
           if( todoServe.insert(todo1) > 0 ){
               flag = true;
           }
        }

       if( flag ){
           return Result.getResult("添加成功");
       }

        return Result.getResult("添加失败");
    }


}
