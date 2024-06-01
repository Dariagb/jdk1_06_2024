import java.util.concurrent.Semaphore;

public class Philosopher extends Thread {
    private Semaphore leftFork;
    private Semaphore rightFork;
    private int id;
    private int mealsEaten = 0;

    public Philosopher(int id, Semaphore leftFork, Semaphore rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    @Override
    public void run() {
        try {
            while (mealsEaten < 3) {
                think();
                leftFork.acquire();
                rightFork.acquire();
                eat();
                leftFork.release();
                rightFork.release();
                mealsEaten++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void think() throws InterruptedException {
        System.out.println("Философ " + id + " думает");
        Thread.sleep(1000);
    }

    private void eat() throws InterruptedException {
        System.out.println("Философ  " + id + " кушает");
        Thread.sleep(1000);
    }}
