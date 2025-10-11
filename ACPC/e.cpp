#include <iostream>
#include <queue>
#include <vector>
#include <unordered_map>
#include <unordered_set>

using std::cout, std::cin, std::vector, std::priority_queue, std::unordered_map, std::pair, std::unordered_set;

struct Node{
    vector<Node*> children; //cost is 0
    vector<Node*> parents; //cost is 1
    int val;
    Node(int val): val(val){

    }
};
class Compare{
    public:
    bool operator()(pair<Node*, int> a, pair<Node*, int> b){
        return a.second > b.second;
    };
};
int main(){
    unordered_map<int, Node*> numToNode;
    //pair is <node, value>
    priority_queue<pair<Node*, int>, vector<pair<Node*, int>>, Compare> pq;
    int n;
    int m;
    cin >> n >> m;
    for(int i = 0;i<m;i++){
        int parent;
        int child;
        cin >> parent >> child;
        if(numToNode.find(parent) == numToNode.end()){
            numToNode[parent] = new Node(parent);
        }
        if(numToNode.find(child) == numToNode.end()){
            numToNode[child] = new Node(child);
        }
        Node* parentNode = numToNode[parent];
        Node* childNode = numToNode[child];

        parentNode->children.push_back(childNode);
        childNode->parents.push_back(parentNode);
    }

    pair<Node*, int> pair1 {numToNode[1], 0};
    pq.push(pair1);
    unordered_set<int> visited;

    while(!pq.empty()){
        pair<Node*, int> toppies = pq.top();
        Node* current = toppies.first;
        pq.pop();
        int val = toppies.first->val;
        // cout << "visiting node " << val << " with cost " << toppies.second << '\n';
        if(visited.find(val) != visited.end()){
            continue;
        }
        visited.insert(val);
        if(val == n){
            cout << toppies.second << '\n';
            break;
        }
        for(const auto& child: current->children){
            pair<Node*, int> choice1{child, toppies.second};
            pq.push(choice1);
            // cout << "pushing choice " << child->val << " with cost " << toppies.second << "\n";
        }
        for(const auto& parent: current->parents){   
            pair<Node*, int> choice2{parent, toppies.second+1};
            pq.push(choice2);
            // cout << "pushing choice " << parent->val << " with cost " << toppies.second+1 << '\n';
        }
    }
    // cout << "no walkway found :<\n";
}
