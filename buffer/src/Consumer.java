class Consumer implements Runnable {
    private final BoundedBuffer buffer;
    private final int id;

    public Consumer(BoundedBuffer buffer, int id) {
        this.buffer = buffer;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                buffer.get(id);
                Thread.sleep(2500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
