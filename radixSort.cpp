#include<bits/stdc++.h>
using namespace std;

void sortSingleRadix(int arr[], int n, int exp){
    int count[10] = {0};
    for(int i=0; i<n; i++){
        count[(arr[i]/exp)%10]++;
    } 
    for(int i=1; i<10; i++){
        count[i] += count[i-1]; //making cummulative sum,
        //now each index shows how much numbers are standing sorted before him
    } 
    int output[n];
    // making the output array
    for(int i=n-1; i>=0; i--){
        output[(count[(arr[i]/exp)%10])-1] = arr[i];
        count[(arr[i]/exp)%10]--;
    }
    for(int i=0; i<n; i++){
        arr[i] = output[i];
    }
}

void radixSort(int arr[], int n){
    int max = arr[0];
    for(int i=1; i<n; i++){
        if(arr[i]>max) max = arr[i];
    }
    for(int i=1; max/i>0; i*=10){
        sortSingleRadix(arr, n, i);
    }
}

int main(){
    int n;
    cin >> n;
    int arr[n];
    for(int i=0; i<n; i++){
        cin >> arr[i];
    }
    radixSort(arr, n);
    for(int i=0; i<n; i++){
        cout << arr[i] << " ";
    }
    

    return 0;
}