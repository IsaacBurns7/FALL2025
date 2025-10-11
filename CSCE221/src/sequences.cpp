#include <iostream>
#include <string>

using std::cout, std::string;

void printArray(const char* name, const int* a, int n){
    cout << name << ": ";
    for(int i = 0;i<n;i++){
        cout << a[i] << ' ';
    }
    cout << '\n';
}

void copyArray(const int* source, int* destination, int n){
    for(int i = 0;i<n;i++){
        destination[i] = source[i];
    }
}

int main(int argc, char** argv){
    const int arr[] = {1,2,3};

    enum Color { black , white , red , blue };
    Color balls [4];
    Color col ; // a variable used as an array index
    for ( col = black ; col <= blue ; col =( Color ) ( col +1) ){
        balls [ col ] = col ;
        for(const auto& ball: balls){
            cout << ball << " ";
        }
        cout << '\n';
    }
    constexpr int max_size = 100;
    typedef double Table [ max_size ];
    Table table1{1,2,3};
    cout << typeid(table1).name() << '\n';

    int n = 10;
    int* a = new int[n];
    for(int i = 0;i<n;i++){
        a[i] = i * i * i;
    }
    int* b = new int[n];
    // copyArray(a, b, n);
    // delete[] b; //memory leak if not placed in
    b = a; //memory leak
    for(int i = 0;i<n;i++){
        a[i] = i;
    }
    printArray("a", a, n);
    printArray("b", b, n);
    int* c = new int[n];
    for(int i = 0;i<n;i++){
        c[i] = i * i;
    }
    printArray("c", c, n);
    delete[] a;
    delete[] b;
    delete[] c;
}