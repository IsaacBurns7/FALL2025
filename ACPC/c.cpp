#include <iostream>
#include <algorithm>
#include <vector>
#include <utility>
#include <unordered_map>
#include <stack>
#include <array>

using std::cout, std::cin, std::max, std::vector, std::pair, std::unordered_map, std::stack, std::array;

int main(){
    int n;
    int m;
    int C;
    cin >> n;
    cin >> m;
    cin >> C;
    
    int p;
    int w;

    vector<vector<pair<int, int> > > fieldRanges(n, vector<pair<int, int> >());
    stack<pair<int, int>> positionsFalling;    
    for(int i = 0;i<C;i++){
        cin >> w;
        cin >> p;
        pair<int, int> coin{p, w+p};
        fieldRanges[0].push_back(coin);
        for(int j = 0;j<fieldRanges[0].size();j++){
            pair<int, int> coin2 = fieldRanges[0][j];
            if((coin2.second < coin.second && coin2.second > coin.first) ||
               (coin.second < coin2.second && coin.second > coin2.first)
            ){
                pair<int, int> position{0+1, fieldRanges[0].size()};
                positionsFalling.push(position);
            }
        }
        while(!positionsFalling.empty()){
            pair<int, int> position = positionsFalling.top();
            positionsFalling.pop(); 
            int idx = position.first;
            int col = position.second;
            pair<int, int> coinFalling = fieldRanges[idx][col];
            //doing this is probably inefficient, probably should use an ordered map
            for(int j = 0;j<fieldRanges[idx].size();j++){
                pair<int, int> coin2 = fieldRanges[idx][j];
                if((coin2.second < coin.second && coin2.second > coin.first) ||
               (coin.second < coin2.second && coin.second > coin2.first)
                ){
                    pair<int, int> position{idx+1, j};
                    positionsFalling.push(position);
                }
            }
        }

    }
}