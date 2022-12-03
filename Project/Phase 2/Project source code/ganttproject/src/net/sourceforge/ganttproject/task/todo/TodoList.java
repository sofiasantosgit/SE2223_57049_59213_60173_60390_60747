package net.sourceforge.ganttproject.task.todo;
import java.util.ArrayList;

public class TodoList {
    private static TodoList single_list = null;
    private final ArrayList<Todo> list;
    private TodoList(){
        list = new ArrayList<>();
    }
    public Todo add(String name){
        Todo t = new Todo(name);
        list.add(t);
        return t;
    }
    public Todo get(String id) {
        return list.stream()
                .filter(todo -> id.equals(todo.getID()))
                .findAny()
                .orElse(null);
    }
    public void markDone(String i) {
        this.get(i).done();
    }
    public void markUndone(String i) {
        this.get(i).undone();
    }
    public int size() {
        return list.size();
    }
    public ArrayList<Todo> list() {
        return list;
    }
    public void remove(String id) {
        list.remove(this.get(id));
    }
    public static TodoList getInstance() {
        if (single_list == null)
            single_list = new TodoList();

        return single_list;
    }
}