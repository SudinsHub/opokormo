#include<bits/stdc++.h>
using namespace std;
// front==rear indicates empty Queue
// front+1 == rear indicates full Queue

struct Queue
{
    int size;
    int front;
    int rear;
    int* arr;
    //insert in rear exert from front
    Queue(int n){
        this->size = n;
        this->front = 0;
        this->rear = 0;
        this->arr = new int[this->size+1]; //the cell pointed by front will be wasted
    }

    void enQueue(int x){
        if((this->rear+1)%(this->size) == this->front){
            cout << "Queue is full!!!" << endl;
            exit(1);
        } else{
            this->rear = (this->rear+1)%(this->size);
            this->arr[this->rear] = x;
        }
    }
    int deQueue(){
        int x = -1;
        if(this->front == this->rear){
            cout << "Queue is Empty!!!" << endl;
        }
        else{
            this->front = (this->front+1)%(this->size) ;
            x = this->arr[this->front];
        }
        return x;
    }

    void printNClearQueue(){
        while (this->front != this->rear){
            cout << this->deQueue();
        }
    }
};


int main(){
    Queue* Q1 = new Queue(100);
    Q1->enQueue(1);
    Q1->enQueue(2);
    Q1->enQueue(3);

    cout << Q1->deQueue() << " ";
    cout << Q1->deQueue() << " ";
    cout << Q1->deQueue() << " ";

}
