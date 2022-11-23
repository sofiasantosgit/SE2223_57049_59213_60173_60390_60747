package biz.ganttproject.task;

import java.util.ArrayList;
import java.util.List;

public class TodoList {
    private final List<Todo> list;
    public TodoList(){
        list = new ArrayList<>();
    }
    public void add(Todo t){
        list.add(t);
    }
    public List<Todo> list(){
        return list;
    }
}
