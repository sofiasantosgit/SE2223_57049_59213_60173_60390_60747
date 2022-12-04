package net.sourceforge.ganttproject.task;
import net.sourceforge.ganttproject.task.todo.Todo;
import net.sourceforge.ganttproject.task.todo.TodoList;
import net.sourceforge.ganttproject.test.task.TaskTestCase;

/**
 * @author Cláudia Santos
 * @author Rui Correia
 * @author Tomás Mondim
 */
public class TodoListTest extends TaskTestCase {

    public void testEmptyTodoList() {
        Task task = createTask();
        TodoList todoList = task.getTodoList();
        assertEquals(0, todoList.size());
    }

    public void testAddTodo() {
        Task task = createTask();
        TodoList todoList = task.getTodoList();
        Todo todo = todoList.add("test");
        assertEquals(1, todoList.size());
        assertEquals(todo, todoList.get(todo.getID()));
    }

    public void testDeleteTodo() {
        Task task = createTask();
        TodoList todoList = task.getTodoList();
        Todo todo = todoList.add("test");
        assertEquals(1, todoList.size());
        assertEquals(todo, todoList.get(todo.getID()));
        todoList.remove(todo.getID());
        assertEquals(0, todoList.size());
    }

    public void testAddMultipleTodos(){
        Task task1 = createTask();
        TodoList todoList1 = task1.getTodoList();
        Todo todo1 = todoList1.add("test");
        String id1 = todo1.getID();

        Task task2 = createTask();
        TodoList todoList2 = task2.getTodoList();
        Todo todo2 = todoList2.add("test");
        String id2 = todo2.getID();

        Task task3 = createTask();
        TodoList todoList3 = task3.getTodoList();
        Todo todo3 = todoList3.add("test");
        String id3 = todo3.getID();

        assertEquals(todo1, task1.getTodoList().get(id1));
        assertEquals(todo2, task2.getTodoList().get(id2));
        assertEquals(todo3, task3.getTodoList().get(id3));
    }

    public void testDeleteMultipleTodos(){
        Task task1 = createTask();
        TodoList todoList1 = task1.getTodoList();
        Todo todo1 = todoList1.add("test");
        String id1 = todo1.getID();

        Task task2 = createTask();
        TodoList todoList2 = task2.getTodoList();
        Todo todo2 = todoList2.add("test");
        String id2 = todo2.getID();

        Task task3 = createTask();
        TodoList todoList3 = task3.getTodoList();
        Todo todo3 = todoList3.add("test");
        String id3 = todo3.getID();

        task1.getTodoList().remove(id1);
        task2.getTodoList().remove(id2);
        task3.getTodoList().remove(id3);

        assertEquals(0, todoList1.size());
        assertEquals(0, todoList2.size());
        assertEquals(0, todoList3.size());
    }

}
