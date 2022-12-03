package net.sourceforge.ganttproject.task.todo;

import java.util.ArrayList;

/**
 * @author Cláudia Santos
 * @author Tomás Mondim
 * @author Rui Correia
 */
public interface TodoList {
    Todo add(String name);
    Todo get(String id);
    void markDone(String i);
    void markUndone(String i);
    int size();
    ArrayList<Todo> list();
    void remove(String id);
}
