#include <list>
#include <iostream>
#include <vector>

using std::list, std::cout, std::vector;
std::list<int> intersection(const std::list<int>& l1, 
                            const std::list<int>& l2) {
    std::list<int> intersection;
    for(const auto& e1: l1){
        for(const auto& e2: l2){
            if(e1 == e2){
                intersection.push_back(e1);
            }
        }
    }
    return intersection;
}
template<typename T>
struct Node {
  Node* next;
  T obj;
  Node(T o, Node* n=nullptr)
    : obj(o), next(n) { }
};

template<typename T>
int count_nodes(Node<T>* root) {
    if(root == nullptr){
        return 0;
    }
    return 1 + count_nodes(root->next);
}

int find_max_value(vector<int> arr, int i) {
	if(i >= arr.size()){
        return std::numeric_limits<int>::min();
    }
    return std::max(arr[i], find_max_value(arr, i+1)); 
}

template<typename T>
struct Node {
  Node<T>* left;
  Node<T>* right;
  T obj;
  Node(T o, Node<T>* l = nullptr, Node<T>* r = nullptr)
    : obj(o), left(l), right(r) { }
};

template<typename T>
int count_filled_nodes(const Node<T>* node) {
    //if tree is empty
    if(node == nullptr){
        return 0;
    }
	if(node->left == nullptr && node->right == nullptr){
        return 0;
    }
    if(node->left == nullptr){
        return 0 + count_filled_nodes(node->right);
    }
    if(node->right == nullptr){
        return 0 + count_filled_nodes(node->left);
    }
    return 1 + count_filled_nodes(node->left) + count_filled_nodes(node->right);
}


int main(){
    list<int> l1{1,2,3,4};
    list<int> l2{2,3};
    list<int> l3;
    list<int> l4{2,9,14};
    list<int> l5{1,7,15};

    list<int> i1 = intersection(l1,l2);
    list<int> i2 = intersection(l2,l3);
    list<int> i3 = intersection(l4,l5);

    // for(const auto& e: i1){
    //     cout << e << " ";
    // }
    // cout << '\n';
    // for(const auto& e: i2){
    //     cout << e << " ";
    // }
    // cout << '\n';
    // for(const auto& e: i3){
    //     cout << e << " ";
    // }
    // cout << '\n';

    Node<int>* node = nullptr;
    Node<int>* dummy = new Node(1);
    Node<int>* root = dummy;
    dummy->next = new Node(2);
    dummy = dummy->next;
    dummy->next = new Node(3);
    dummy = dummy->next;

    // cout << count_nodes(node) << '\n';
    // cout << count_nodes(root) << '\n';

    vector<int> v1{1,2,3,4};
    vector<int> v2{-1};
    vector<int> v3{-1,-100};
    
    cout << find_max_value(v1, 0) << '\n';
    cout << find_max_value(v2, 0) << '\n';
    cout << find_max_value(v3, 0) << '\n';

}