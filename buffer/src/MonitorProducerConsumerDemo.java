public class MonitorProducerConsumerDemo {
    public static void main(String[] args) {
        BoundedBuffer buffer = new BoundedBuffer(3);

        Thread p1 = new Thread(new Producer(buffer, 100));
        Thread p2 = new Thread(new Producer(buffer, 200));
        Thread c1 = new Thread(new Consumer(buffer, 1));

        p1.start();
        p2.start();
        c1.start();

        try {
            p1.join();
            p2.join();
            c1.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Done.");
    }
}
// 
