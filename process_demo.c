#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>

int main() {
    int value = 10;

    pid_t pid = fork();

    if (pid < 0) {
        perror("fork failed");
        return 1;
    }

    if (pid == 0) {
        // Child
        value += 5;
        printf("Child process: PID=%d, value=%d (sleeping 15s)\n", getpid(), value);
        sleep(15);  // child sleeps so you can observe it
        printf("Child process (PID=%d) finished\n", getpid());
    } else {
        // Parent
        value -= 5;
        printf("Parent process: PID=%d, ChildPID=%d, value=%d (sleeping 15s)\n",
               getpid(), pid, value);
        sleep(15);  // parent sleeps so you can observe it
        printf("Parent process (PID=%d) finished\n", getpid());
    }

    return 0;
}
