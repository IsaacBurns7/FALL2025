#include <iostream>
using std::cout, std::endl;

int main(int argc, char** argv){
    int a = 3315;
    void *what = &a;
    cout << *(int *)what / ((1 << 4) - 1) << endl;
}