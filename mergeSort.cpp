#include<bits/stdc++.h>
using namespace std;

void mergeTwoParts(int arr[], int low, int mid, int hi){
    int i = low, j= mid+1, k=0;
    int temp[hi-low+1];
    while (i<=mid && j<=hi){
        if(arr[i] < arr[j]) temp[k++] = arr[i++];
        else temp[k++] = arr[j++];
    }
    for(;i<=mid;i++) temp[k++] = arr[i];
    for(;j<=hi;j++) temp[k++] = arr[j];
    for(int p=0; p<=hi-low; p++) arr[low+p]=temp[p];
}

void mergeSortItr(int arr[], int n){
    int i, j;
    for(i = 2; i <= n; i *= 2)
        for(j = 0; j <= n - i+1; j += i)
            mergeTwoParts(arr, j, j + (i / 2) - 1, j + i - 1);

    // If there are remaining elements, merge them with the rest of the array
    if (i / 2 < n)
        mergeTwoParts(arr, 0, i / 2 - 1, n - 1);
}

void mergeSortRec(int arr[], int low, int hi){
    if(low<hi){
        int mid = (hi+low)/2;
        mergeSortRec(arr, low, mid);
        mergeSortRec(arr, mid+1, hi);
        mergeTwoParts(arr, low, mid, hi);
    }
}

int main(){
    int arr[] = {21,14,6,18,3,15,7,12,14,10,5,13,0,1,4};
    // mergeSortItr(arr, 15);
    mergeSortRec(arr, 0, 14);
    for(int i=0; i<15; i++) cout << arr[i] << " "; 

    return 0;
}