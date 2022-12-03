package net.sourceforge.ganttproject.task.todo;
import java.util.ArrayList;
import java.util.List;

public class TodoList {
    private static TodoList single_list = null;
    private final ArrayList<Todo> list;
    private TodoList(){
        list = new ArrayList<>();
    }
    public int add(String name){
        list.add(new Todo(name));
        return list.size();
    }
    public Todo get(int i) {
        return list.get(i);
    }
    public void markDone(int i) {
        list.get(i).done();
    }
    public void markUndone(int i) {
        list.get(i).undone();
    }
    public ArrayList<Todo> list(){
        return list;
    }
    public static TodoList getInstance() {
        if (single_list == null)
            single_list = new TodoList();

        return single_list;
    }
}