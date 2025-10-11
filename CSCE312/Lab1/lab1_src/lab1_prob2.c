#include <stdio.h>
#include <stdlib.h>



int main(int argc, char *argv[])
{
    struct employee1 {
        int id;
        char name[50];
    };

    struct employee2 {
        int id;
        char name[52];
    };

    printf("Size of employee1 structure: %lu bytes\n", sizeof(struct employee1));
    printf("Size of employee2 structure: %lu bytes\n", sizeof(struct employee2));

    return 0;
}