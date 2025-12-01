import java.util.LinkedList;
import java.util.Queue;

class BoundedBuffer {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int capacity;

    public BoundedBuffer(int capacity) {
        this.capacity = capacity;
    }

    // MONITOR METHOD: Producer puts an element
    public synchronized void put(int value, int id) throws InterruptedException {
        while (queue.size() == capacity) {
            System.out.println("Producer " + id + " waiting: buffer full");
            wait();  // release monitor lock and wait
        }

        queue.add(value);
        System.out.println("Producer " + id + " produced " + value);

        notifyAll(); // wake up consumers
    }

    // MONITOR METHOD: Consumer retrieves an element
    public synchronized int get(int id) throws InterruptedException {
        while (queue.isEmpty()) {
            System.out.println("Consumer " + id + " waiting: buffer empty");
            wait();  // release monitor lock and wait
        }

        int value = queue.remove();
        System.out.println("Consumer " + id + " consumed " + value);

        notifyAll(); // wake up producers
        return value;
    }
}
