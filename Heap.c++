#include<bits/stdc++.h>
using namespace std;

class MaxHeap{
    public:

    int arr[10000];
    int size = 0;
    int maxSize = 0;

    void insert(int x){
        arr[size] = x;
        int current = size;
        size++;
        maxSize++;

        while(current>0){
            if(arr[current] > arr[(current-1)/2]){
                swap(arr[current], arr[(current-1)/2]);
                current = (current-1)/2;
            }
            else break;
        }
    }

    int extractMax(){
        if(size==0) return -1; // Empty Heap
        int result = arr[0];
        size--;
        swap(arr[0], arr[size]);
        int current = 0;
        int largerChildInd; 
        while(2*current+1 < size){
            if(2*current+2 == size) largerChildInd=2*current+1;
            else largerChildInd = (arr[2*current+1] > arr[2*current+2]) ? 2*current+1 : 2*current+2;
            if(arr[current] < arr[largerChildInd]){
                swap(arr[current], arr[largerChildInd]);
                current = largerChildInd;
            }
            else break;
        }
        return result;
    }

    void heapSort(){
        while(size!=0) extractMax();    
    }

    void printSorted(){
        for(int i=size; i<maxSize; i++) cout << arr[i] << " ";
    } 
    void print(){
        for(int i=0; i<size; i++) cout << arr[i] << " ";
    } 
};

int main(){
    MaxHeap* mh = new MaxHeap();

    mh->insert(15);
    mh->insert(5);
    mh->insert(10);
    mh->insert(7);
    mh->insert(10);
    mh->insert(35);
    mh->print();
    mh->heapSort();
    // mh->extractMax();
    mh->printSorted();
    return 0;
}