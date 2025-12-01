public class MonitorDemo {
    public static void main(String[] args) {
        CounterMonitor monitor = new CounterMonitor();
        int THREAD_COUNT = 5;
        Thread[] threads = new Thread[THREAD_COUNT];

        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(new Worker(monitor, i));
            threads[i].start();
        }

        for (int i = 0; i < THREAD_COUNT; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Final counter value: " + monitor.getValue());
    }
}
