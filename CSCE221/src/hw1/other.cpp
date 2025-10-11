#include <iostream>
#include <vector>
#include <algorithm>
#include <random>

int main() {
    std::vector<int> v;
    v.reserve(100);

    std::random_device rd;
    std::mt19937 gen(rd());
    std::uniform_int_distribution<> dist(1, 10); // step sizes

    int current = 0;
    for (int i = 0; i < 16; i++) {
        current += dist(gen); // ensure strictly increasing
        v.push_back(current);
    }

    // Print the vector
    for (int x : v) {
        std::cout << x << ", ";
    }
    std::cout << "\n";
}
