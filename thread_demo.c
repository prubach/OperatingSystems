#include <stdio.h>
#include <pthread.h>
#include <unistd.h>

int shared_value = 0;
pthread_mutex_t lock;

void* thread_func(void* arg) {
    long thread_id = (long)arg;

    pthread_mutex_lock(&lock);
    shared_value += 1;
    printf("Thread %ld: shared_value = %d (sleeping 10s)\n", thread_id, shared_value);
    pthread_mutex_unlock(&lock);

    sleep(15);  // allow time to observe the thread

    printf("Thread %ld: waking up and finishing\n", thread_id);
    return NULL;
}

int main() {
    pthread_t t1, t2;

    pthread_mutex_init(&lock, NULL);

    pthread_create(&t1, NULL, thread_func, (void*)1);
    pthread_create(&t2, NULL, thread_func, (void*)2);

    printf("Main thread: threads created (PID=%d). You can now inspect them using ps/top/htop.\n",
           getpid());

    pthread_join(t1, NULL);
    pthread_join(t2, NULL);

    pthread_mutex_destroy(&lock);

    printf("Main thread: final shared_value = %d\n", shared_value);
    return 0;
}
