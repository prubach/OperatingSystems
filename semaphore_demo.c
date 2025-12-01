#include <stdio.h>
#include <pthread.h>
#include <semaphore.h>
#include <unistd.h>

#define THREAD_COUNT 5

sem_t mutex;  // binary semaphore
int shared_counter = 0;

void* thread_function(void* arg) {
    int id = *(int*)arg;

    printf("Thread %d: waiting to enter critical section...\n", id);

    // DOWN / WAIT — acquire semaphore
    sem_wait(&mutex);

    // ---- CRITICAL SECTION ----
    printf("Thread %d: entered critical section.\n", id);

    int local = shared_counter;
    local++;
    sleep(3);  // simulate some work
    shared_counter = local;

    printf("Thread %d: leaving critical section.\n", id);
    // ---- END CRITICAL SECTION ----

    // UP / POST — release semaphore
    sem_post(&mutex);

    return NULL;
}

int main() {
    pthread_t threads[THREAD_COUNT];
    int ids[THREAD_COUNT];

    // Initialize the semaphore with value 1 (binary semaphore)
    sem_init(&mutex, 0, 1);

    for (int i = 0; i < THREAD_COUNT; i++) {
        ids[i] = i;
        pthread_create(&threads[i], NULL, thread_function, &ids[i]);
    }

    for (int i = 0; i < THREAD_COUNT; i++) {
        pthread_join(threads[i], NULL);
    }

    printf("Final shared counter value: %d\n", shared_counter);

    sem_destroy(&mutex);
    return 0;
}
