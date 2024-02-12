#include<bits/stdc++.h>
using namespace std;
struct Node{
    int value;
    Node* next;
};

struct CircularLinkedlist{
    Node* head;
    CircularLinkedlist(){
        this->head = new Node();
        this->head->next = this->head;
        
    }
    void printList(){
        Node* ptr = this->head;
        do{
            cout << ptr->value << " ";
            ptr = ptr->next;
        }while(ptr != this->head);
        
    }    
    // void insertAfterIthNode(int i, int x){
    //     Node* newNode = new Node();
    //     newNode->value = x;
    //     if(i==0){
    //         newNode->next = this->head;
    //         this->head = newNode;
    //     }else{
    //         int p=i;
    //         Node* ptr = this->head;
    //         while (--p) ptr = ptr->next;
    //         newNode->next = ptr->next;
    //         ptr->next = newNode; 
    //     }
        
    // }

    void deleteNthNode(int n){
        if(n==1){
            Node* temp = this->head;
            this->head = this->head->next;
            delete temp;
        }else {
            int p=n;
            Node* ptr = this->head;
            Node* behind;
            while (--p){
                behind = ptr;
                ptr = ptr->next;
            } 
            behind->next = ptr->next;
            if(ptr == this->tail) this->tail = behind;
            delete ptr;
        }
        
    }

    ~CircularLinkedlist(){
        Node* current = this->head;
        Node* next;
        do{
            next = current->next;
            delete current;
            current = next;
        } while (current != this->head);
    }
};

int main(){
    CircularLinkedlist* cycle1 = new CircularLinkedlist();
    Node* itr = cycle1->head;
    cycle1->head->value = 0;
    
    for (int i = 1; i < 5; i++){
        Node* curr = new Node(); 
        curr->value = i;
        curr->next = cycle1->head;
        itr->next = curr;
        itr = curr;
    }
    cycle1->printList();
    delete cycle1, itr;
    return 0;
}