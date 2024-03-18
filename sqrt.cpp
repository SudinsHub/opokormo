#include<bits/stdc++.h>
#define precesion 0.00001
using namespace std;

int isEqual(double x, double y){
    if(abs(x-y) < precesion) return 0;
    else if(x>y) return 1;
    return -1; 
}

int main(){
    double low = 1, hi, key;
    cin >> key;
    hi = key;
    while(1){
        double mid = (low+hi)/2;
        int flag = isEqual(mid*mid, key);
        if(!flag){
            cout << mid;
            break;
        }else if(flag==-1) low=mid;
        else hi=mid;
    }

    return 0;
}
