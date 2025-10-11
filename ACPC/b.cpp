#include <iostream>
#include <algorithm>
#include <vector>

using std::cout, std::cin, std::max;

int main(){
    int r = 0;
    int b = 0;
    int g = 0;
    int x;
    for(int i = 0; i < 36; i++){
        std::cin >> x;
        if(i % 2 == 0){
            r += x;
        }else{
            b += x;
        }
    }
    std::cin >> x;
    g = x;
    if(g == b && b == r){
        std::cout << "no bet" << '\n';
    }else{
        if(b > r && b > g){
            std::cout << "black" << '\n';
        }else if (r > g && r > b){
            std::cout << "red" << '\n';
        }else if (g > b && g > r){
            std::cout << "green" << '\n';
        }else{
            std::cout << "no bet" << '\n';
        }
    }
}