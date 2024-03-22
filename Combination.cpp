#include<bits/stdc++.h>
using namespace std;

void combinationGenerator(int in[], int out[], int n, int r, int outInd, int inInd){
    if(outInd==r){
        for(int i=0; i<r; i++)
            cout << out[i] << " ";
        cout << endl;
        return;
    }
    for(int i=inInd; i<=n-r+outInd; i++){
        out[outInd] = in[i];
        combinationGenerator(in, out, n, r, outInd+1, i+1); // output arrayr porer index e,
        // input array er je index er man ekhon populate korlam, 
        // tarporer Index theke populate kora shuru korbe;
    }
}

int main(){
    int arr[5] = {1,2,3,4,5};
    int out[3];
    combinationGenerator(arr, out, 5, 3, 0, 0);

    return 0;
}