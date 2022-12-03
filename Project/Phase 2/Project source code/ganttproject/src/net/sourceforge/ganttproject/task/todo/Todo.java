package net.sourceforge.ganttproject.task.todo;

import java.util.UUID;

/**
 * @author Cláudia Santos
 * @author Tomás Mondim
 * @author Rui Correia
 */
public class Todo {
    private final String ID;
    private String description;
    private boolean done;
    protected Todo(String desc){
        description = desc;
        done = false;
        ID = UUID.randomUUID().toString();
    }
    public String getID() {
        return ID;
    }
    public String getDescription(){
        return description;
    }
    public boolean isDone(){
        return done;
    }
    public void done(){
        done = true;
    }
    public void undone(){
        done = false;
    }
    public void changeDescription(String desc){
        description = desc;
    }
}
