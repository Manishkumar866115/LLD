import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;

enum FrequencyUnit{
    SECONDS, MINUTES, DAYS, WEEKS, MONTHS, YEAR;
}
class Task{
    static Integer idCounter = 0;

    Integer id;
    String name;
    String description;
    Boolean isRecurring;
    FrequencyUnit recurringFrequency;
    Integer frequency;
    String createdBy;
    LocalDateTime createdAt;
    LocalDateTime executionTime;

    Boolean isComplete;

    public Task(String name, String description, Boolean isRecurring, FrequencyUnit recurringFrequency, Integer frequency, String createdBy, LocalDateTime executionTime) {
        this.id = idCounter++;
        this.name = name;
        this.description = description;
        this.isRecurring = isRecurring;
        this.recurringFrequency = recurringFrequency;
        this.frequency = frequency;
        this.createdBy = createdBy;
        this.executionTime = executionTime;
        this.createdAt = LocalDateTime.now();
        this.isComplete = false;
    }

    public void execute(){
        System.out.println("Executing task with id=" + this.id + " at time=" + LocalDateTime.now());
    }
    @Override
    public String toString(){
        return "Task Id = " + this.id + " execution time = " + this.executionTime;
    }

    public Task getNextTask(){
        if( this.isRecurring){
            return new Task(this.name, null, true, this.recurringFrequency, this.frequency, this.createdBy, this.executionTime.plusSeconds(frequency));
        }else{
            return null;
        }
    }
}

class WorkerThread extends Thread{
    TaskStore taskStore;
    volatile boolean running = false;

    WorkerThread(TaskStore taskStore){
        this.taskStore = taskStore;
        running = true;
    }

    @Override
    public void run(){
        while( running && !Thread.currentThread().isInterrupted()){
            Task task = taskStore.getNextTask();
            if( task == null){
                continue;
            }
            long delay = task.executionTime.toEpochSecond(ZoneOffset.UTC) - LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
            System.out.println("delay=" + delay + " task=" + task.id + " worker=" + getId());
            try {
                if( delay > 0){
                    taskStore.addTask(task);
                    synchronized (this) {
                        wait(delay * 1000);
                    }
                }else{
                    task.execute();
                    Task nextTask = task.getNextTask();
                    if( nextTask != null){
                        taskStore.addTask(task.getNextTask());
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}

abstract class TaskStore{
    abstract Task getNextTask();
    abstract boolean addTask(Task task);
    abstract boolean removeTask(Task task);
}

class PriorityBasedStore extends TaskStore{

    PriorityBlockingQueue<Task> pq;

    PriorityBasedStore(int size){
        pq = new PriorityBlockingQueue<>(size, (p1, p2) -> {
            return (p1.executionTime.isBefore(p2.executionTime))  ? 1 : -1;
        });
    }

    @Override
    Task getNextTask() {
        return pq.poll();
    }

    @Override
    boolean addTask(Task task) {
        return pq.add(task);
    }

    @Override
    boolean removeTask(Task task) {
        return pq.remove(task);
    }
}

class JobScheduler{
    public static JobScheduler jobScheduler = null;
    List<WorkerThread> workerThreads;

    TaskStore taskStore;
    private JobScheduler(int n, TaskStore taskStore){
        this.taskStore = taskStore;
        this.workerThreads = new ArrayList<>();
        for(int i = 0; i < n; i++){
            workerThreads.add(new WorkerThread(taskStore));
            workerThreads.get(i).start();
        }
    }

    public static JobScheduler getInstance(int n, TaskStore taskStore){
        if( jobScheduler == null){
            synchronized (JobScheduler.class){
                if(jobScheduler == null){
                    jobScheduler =  new JobScheduler(n, taskStore);
                }
            }
        }
        return jobScheduler;
    }
}


public class Main {
    public static void main(String[] args) {
            Task t1 = new Task("t1", null, false, null, null, "1", LocalDateTime.now().plusSeconds(15));
            Task t2 = new Task("t2", null, true, FrequencyUnit.SECONDS, 30, "1", LocalDateTime.now().plusSeconds(30));
            TaskStore taskStore = new PriorityBasedStore(5);
            taskStore.addTask(t1);
            taskStore.addTask(t2);
            JobScheduler.getInstance(5, taskStore);
    }
}