#include <bits/stdc++.h>
using namespace std;
 
int partition(int arr[],int low,int high){
    int pivot=arr[high];
    int i=(low-1);
    for(int j=low;j<=high;j++){
        if(arr[j]<pivot){ // if u use <= it will make the pivot equal every time
            // and will fall in a infinite loop
            i++;
            swap(arr[i],arr[j]);
        }
    }
    i++;
    swap(arr[i],arr[high]);
    return i;
}

void quickSort(int arr[], int low, int hi){
    if(low<hi){
        int part = partition(arr, low, hi);
        quickSort(arr, low, part-1);
        quickSort(arr, part+1, hi);
    }
}

int main() {
    int arr[] = {9,7,2,3,5,1,4,5};
    quickSort(arr, 0, 7);
    for(auto i : arr) cout << i << " ";
    return 0;
}