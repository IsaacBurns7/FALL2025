#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h> // For high-resolution timing (gettimeofday)
#include <math.h>     // For calculating powers of 2 (if needed)

// Define the maximum size of your array and the size of the long data type
#define MAXELEMS (1 << 24) // Example: 16M elements (128 MB)
#define BYTES_PER_MB (1048576.0) // 2^20 bytes

long data[MAXELEMS]; /* Global array to traverse */
/* test - Iterate over first "elems" elements of
 * array “data” with stride of "stride", using
 * using 4x4 loop unrolling.
 */
int test(int elems, int stride) {
    long i, sx2=stride*2, sx3=stride*3, sx4=stride*4;
    long acc0 = 0, acc1 = 0, acc2 = 0, acc3 = 0;
    long length = elems, limit = length - sx4;
    /* Combine 4 elements at a time */
    for (i = 0; i < limit; i += sx4) {
        acc0 = acc0 + data[i];
        acc1 = acc1 + data[i+stride];
        acc2 = acc2 + data[i+sx2];
        acc3 = acc3 + data[i+sx3];
    }
    /* Finish any remaining elements */
    for (; i < length; i++) {
        acc0 = acc0 + data[i];
    }
    return ((acc0 + acc1) + (acc2 + acc3));
}

// =================================================================
// 2. Throughput Measurement Function
// =================================================================
double measure_throughput(int elems, int stride) {
    struct timeval start, end;
    double time_taken;
    long result;
    double bytes_read = (double)elems * sizeof(long);

    // 1. Warm up the caches
    test(elems, stride);

    // 2. Measure the read throughput
    gettimeofday(&start, NULL);
    result = test(elems, stride);
    gettimeofday(&end, NULL);

    // Calculate time taken in seconds
    time_taken = (end.tv_sec - start.tv_sec) + 
                 (end.tv_usec - start.tv_usec) / 1000000.0;
    
    // Calculate throughput in MB/s
    if (time_taken > 0) {
        // Total Bytes Read / (Time * Bytes per MB)
        return (bytes_read / time_taken) / BYTES_PER_MB; 
    } else {
        return 0.0; // Avoid division by zero
    }
}

// =================================================================
// 3. Main function to drive the test
// =================================================================
int main() {
    // Initialize the global array (to ensure it's not zeroed out by the OS later)
    for (int i = 0; i < MAXELEMS; i++) {
        data[i] = i; 
    }

    printf("ELEM_SIZE (B) \t STRIDE \t THROUGHPUT (MB/s)\n");
    printf("--------------------------------------------------\n");

    // Example iteration: Test a small and a large element count
    int elem_counts[] = {1 << 10, 1 << 16, 1 << 20}; 
    int num_elem_sizes = sizeof(elem_counts) / sizeof(elem_counts[0]);

    // Example iteration: Test strides from 1 to 128
    for (int i = 0; i < num_elem_sizes; i++) {
        int current_elems = elem_counts[i];
        
        for (int stride = 8; stride <= 512; stride += 8) {
            if (current_elems < stride) continue; // Skip if elems is too small
            
            double mbps = measure_throughput(current_elems, stride);
            
            printf("%-15d \t %-6d \t %.2f\n", 
                   current_elems * (int)sizeof(long), 
                   stride, 
                   mbps);
        }
    }

    return 0;
}