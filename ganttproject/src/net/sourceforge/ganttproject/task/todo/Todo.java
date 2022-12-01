public class Todo {
    private String description;
    private boolean done;
    protected Todo(String desc){
        description = desc;
        done = false;
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
    public void changeDescription(String desc){
        description = desc;
    }
}