package net.sourceforge.ganttproject.task.todo;
import java.util.ArrayList;

/**
 * @author Cláudia Santos
 * @author Tomás Mondim
 * @author Rui Correia
 */
public class myTodoList implements TodoList {
    private final ArrayList<Todo> list;
    public myTodoList(){
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
}