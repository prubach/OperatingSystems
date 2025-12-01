class Producer implements Runnable {
    private final BoundedBuffer buffer;
    private final int id;

    public Producer(BoundedBuffer buffer, int id) {
        this.buffer = buffer;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                buffer.put(id + i, id);
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

