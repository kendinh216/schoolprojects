package observers;

import model.Item;

public class TaskCounter implements Observer {
    private int numTask;

    public TaskCounter(){
        numTask = 0;
    }
    public int getNumberOfTask(){
        return numTask;
    }
    @Override
    public void update(Item item){
        numTask++;
        System.out.println("There are currently  "+ numTask + " tasks in your list.");
    }
}
