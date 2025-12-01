class CounterMonitor {
    private int counter = 0;

    // Synchronized method acts as a monitor entry
    public synchronized void increment(int id) {
        System.out.println("Thread " + id + " entered monitor.");

        int local = counter;
        try {
            Thread.sleep(2000);  // simulate work
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        counter = local + 1;

        System.out.println("Thread " + id + " exiting monitor.");
    }

    // Another synchronized method
    public synchronized int getValue() {
        return counter;
    }
}

class Worker implements Runnable {
    private final CounterMonitor monitor;
    private final int id;

    public Worker(CounterMonitor monitor, int id) {
        this.monitor = monitor;
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Thread " + id + " waiting to enter monitor...");
        monitor.increment(id);
    }
}

