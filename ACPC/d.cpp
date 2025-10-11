#include <iostream>
#include <vector>
#include <algorithm>

using std::cin, std::cout, std::vector, std::sort;

int main(){
    int n;
    int k;
    cin >> n >> k;

    vector<int> cards(n, 0);
    for(int i = 0;i<n;i++){
        cin >> cards[i];
    }
    sort(cards.begin(), cards.end());

    int l = 0;
    int r = n-1;
    int l_diff = 0;
    int r_diff = 0;
    int total_diff = 0;
    int cards_taken = 0;
    while(l < r && cards_taken < (n-k)){
        std::cout << l_diff << " vs " << r_diff << '\n';
        cout << "given params (" << l << ", " << r << ")\n";
        if(l_diff > r_diff){
            r_diff += cards[r] - cards[l];

            total_diff += l_diff;

            int l_increase = cards[l] - (l > 0 ? cards[l-1] : 0);
            int r_elems = n - r - 1;
            l_diff += l_increase;
            l_diff -= (l_increase) * (r_elems);
            l++;
        }else{
            l_diff += cards[r] - cards[l];

            total_diff += r_diff;

            int r_increase = (r < n - 1 ? cards[r-1] : cards[r]) - cards[r];
            int l_elems = l;
            r_diff += r_increase;
            r_diff -= (r_increase) * (l_elems);
            r--;
        }
        cards_taken++;
    }
    cout << "total diff: " << total_diff << '\n';
    double average = 1.0 * total_diff / (1.0 * (n-k));
    std::cout << average << '\n';

}