import java.util.Calendar;

/*
 * Shift class
 */
public class Shift {

    private Calendar startTime;
    private Calendar endTime;
    private Calendar date;
    private User worker;
    private Task task;
    private Campus campus;

    public Shift(Calendar startTime, Calendar endTime, Calendar date, Task task, Campus campus) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.task = task;
        this.campus = campus;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public void addWorker(User worker) {
        this.worker = worker;
    }

    // Won't need to clear a worker because without someone to take their place it should be removed
    public void replaceWorker(User worker) {
        this.worker = worker;
    }

    // Needs to be thoroughly tested to make sure setting worker to null doesn't set that user's data to null
    public void removeShift() {
        startTime = null;
        endTime = null;
        date = null;
        worker = null;
    }

    public Role getWorkerRole() {
        return worker.getRole();
    }
}