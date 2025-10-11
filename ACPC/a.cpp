#include <iostream>

int main(){
    int x;
    std::cin >> x;
    if(x < 0){
        std::cout << "TRUTH" << '\n';
    }else {
        std::cout << "LIE" << '\n';
    }
}