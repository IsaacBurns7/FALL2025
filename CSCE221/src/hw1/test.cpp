#include <iostream>
#include <vector>
#include <numeric>

using std::vector, std::cout;

int num_comp = 0;

int num_comp1, num_comp2, num_comp3, num_comp4, num_comp5, num_comp6;
int num_comp7, num_comp8, num_comp9, num_comp10, num_comp11, num_comp12;

bool one_a(vector<int> in){
    int count = 0;
    for(const auto& element : in){
        if(element % 12 == 0) count++;
    }
    return count >= 2;
}

bool one_a_two(vector<int> in){
    int count = 0;
    for(size_t i = 0;i<in.size();i++){  
        for(size_t j = 0;j<in.size();j++){
            int element1 = in[i];
            int element2 = in[j];
            if(i == j && element1 % 12 == 0) count++;
        }
    }
    return count >= 2;
}

int Binary_Search(vector<int> &v, int x, int& num_comp) {
   int mid, low = 0;     
   int high = (int) v.size()-1;     
   while (low < high) {         
      mid = (low+high)/2;                 
      if (num_comp++, v[mid] < x) low = mid+1;         
      else high = mid;     
   }     
   if (num_comp++, x == v[low]) return low; //OK: found          
   return -1; //not found
} 

int main(int argc, char** argv){
    vector vec1 {1,2,3};
    vector vec2 {4,12,24};
    vector vec3 {12, 13, 14};
    vector vec4 {1, 2, 4, 12, 16, 18, 25, 27, 29, 38, 39, 46, 56, 64, 73, 76, 79, 89, 97, 107, 112, 120, 130, 135, 140, 147, 156, 157, 162, 170, 177, 182, 189, 195, 197, 207, 212, 218, 225, 233, 234, 238, 248, 257, 265, 268, 274, 275, 277, 278, 281, 287, 290, 298, 301, 304, 307, 313, 315, 320, 322, 329, 330, 333, 340, 344, 353, 362, 372, 377, 387, 397, 402, 405, 410, 415, 420, 423, 433, 442, 450, 452, 462, 464, 469, 473, 482, 491, 500, 509, 519, 524, 525, 531, 535, 537, 547, 556, 565, 567};
    vector vec5 {5, 9, 14, 18, 22, 28, 35, 43, 51, 53, 54, 61, 62, 69, 79, 89};
    std::vector<int> v1(1);
    std::iota(v1.begin(), v1.end(), 1);

    std::vector<int> v2(2);
    std::iota(v2.begin(), v2.end(), 1);

    std::vector<int> v3(4);
    std::iota(v3.begin(), v3.end(), 1);

    std::vector<int> v4(8);
    std::iota(v4.begin(), v4.end(), 1);

    std::vector<int> v5(16);
    std::iota(v5.begin(), v5.end(), 1);

    std::vector<int> v6(32);
    std::iota(v6.begin(), v6.end(), 1);

    std::vector<int> v7(64);
    std::iota(v7.begin(), v7.end(), 1);

    std::vector<int> v8(128);
    std::iota(v8.begin(), v8.end(), 1);

    std::vector<int> v9(256);
    std::iota(v9.begin(), v9.end(), 1);

    std::vector<int> v10(512);
    std::iota(v10.begin(), v10.end(), 1);

    std::vector<int> v11(1024);
    std::iota(v11.begin(), v11.end(), 1);

    std::vector<int> v12(2048);
    std::iota(v12.begin(), v12.end(), 1);


    // cout << one_a(vec1) <<  " " << one_a_two(vec1) << '\n';
    // cout << one_a(vec2) << " " << one_a_two(vec2) << '\n';
    // cout << one_a(vec3) << " " << one_a_two(vec3) << '\n';
    // cout << Binary_Search(vec5, 43, num_comp) << " " << num_comp << '\n';

    cout << Binary_Search(v1, v1.front(), num_comp1)   << " " << num_comp1  << '\n';
    cout << Binary_Search(v2, v2.front(), num_comp2)   << " " << num_comp2  << '\n';
    cout << Binary_Search(v3, v3.front(), num_comp3)   << " " << num_comp3  << '\n';
    cout << Binary_Search(v4, v4.front(), num_comp4)   << " " << num_comp4  << '\n';
    cout << Binary_Search(v5, v5.front(), num_comp5)   << " " << num_comp5  << '\n';
    cout << Binary_Search(v6, v6.front(), num_comp6)   << " " << num_comp6  << '\n';
    cout << Binary_Search(v7, v7.front(), num_comp7)   << " " << num_comp7  << '\n';
    cout << Binary_Search(v8, v8.front(), num_comp8)   << " " << num_comp8  << '\n';
    cout << Binary_Search(v9, v9.front(), num_comp9)   << " " << num_comp9  << '\n';
    cout << Binary_Search(v10, v10.front(), num_comp10)<< " " << num_comp10 << '\n';
    cout << Binary_Search(v11, v11.front(), num_comp11)<< " " << num_comp11 << '\n';
    cout << Binary_Search(v12, v12.front(), num_comp12)<< " " << num_comp12 << '\n';

}

