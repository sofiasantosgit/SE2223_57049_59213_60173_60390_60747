import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class TodoList {
    private final List<Todo> list;
    public TodoList(){
        list = new ArrayList<>();
    }
    public void add(String str){
        list.add(new Todo(str));
    }
    public Iterator<Todo> list(){
        return list.iterator();
    }
}