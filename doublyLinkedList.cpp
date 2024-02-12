#include<bits/stdc++.h>
using namespace std;
struct Node{
    int value;
    Node* next;
    Node* prev;
};
void swap(int* n, int* m){
    int temp = *n;
    *n = *m;
    *m = temp;
}
struct DLinkedlist{
    Node* head;
    Node* tail;
    DLinkedlist(){
        this->head = new Node();
        this->head->next = nullptr;
        this->head->prev = nullptr;
        this->tail = this->head;
    }
    void printList(){
        Node* ptr = this->head;
        int i=0;
        while(ptr){
            cout << ptr->value << " ";
            // cout << i++ << " ";
            ptr = ptr->next;
        }
        cout << endl;
    }    
    void revPrint(){
        Node* ptr = this->tail;
        while(ptr){
            cout << ptr->value << " ";
            ptr = ptr->prev;
        }
        cout << endl;
    }    
    void insertAfterIthNode(int i, int x){
        Node* newNode = new Node();
        newNode->value = x;
        if(i==0){
            newNode->next = this->head;
            newNode->prev = nullptr;
            this->head->prev = newNode;
            this->head = newNode;
        }else{
            int p=i;
            Node* ptr = this->head;
            while (--p) ptr = ptr->next;
            newNode->next = ptr->next;
            ptr->next = newNode; 
            newNode->prev = ptr;
            newNode->next->prev = newNode;
        }
        
    }
    void insertAtEnd(int x){
        Node* ptr = new Node();
        ptr->value = x;
        ptr->next = nullptr;
        ptr->prev = this->tail;
        this->tail->next = ptr;
        this->tail = ptr;
        // cout << this->tail->value << " ";
    } 

    void bubbleSort(){
        bool swapped;
        Node* ptr = this->head;
        Node* end = this->tail;
        int i=0;
        while(end != this->head){
            swapped = false;
            while(ptr != end){
                if(ptr->value > ptr->next->value){
                    swap(ptr->value, ptr->next->value);
                    swapped = true;
                }
                ptr = ptr->next;
            }
            if(swapped == false) break;
            end = end->prev;
            ptr = this->head;
        }

    }
    ~DLinkedlist(){
        Node* current = this->head;
        Node* next;
        while (current)
        {
            next = current->next;
            delete current;
            current = next;
        }
         
    }
    
};

int main(){
    DLinkedlist* list1 = new DLinkedlist();
    list1->head->value = 27;
    list1->insertAtEnd(25);
    list1->insertAtEnd(10);
    list1->insertAtEnd(30);
    list1->insertAtEnd(25);
    // list1->insertAfterIthNode(3, 15);
    list1->bubbleSort();
    list1->printList();
    // list1->revPrint();
    delete list1;
    return 0;
}