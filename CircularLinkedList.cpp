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
            // if(ptr == this->tail) this->tail = behind;
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


struct CircularDLinkedlist{
    struct Node{
        bool value;
        Node* next;
        Node* prev;
    };
    Node* head;
    Node* tail;
    CircularDLinkedlist(){
        this->head = nullptr;
        this->tail = nullptr;
    }
    void printList(){
        Node* ptr = this->head;
        do{
            cout << ptr->value << " ";
            ptr = ptr->next;
        }while(ptr != this->head);
        
    }    
 void insertAtTail(bool x){
        Node* newNode = new Node();
        newNode->value = x;
        if(!head){
            newNode->next = newNode;
            newNode->prev = newNode;
            this->head = newNode;
            this->tail = newNode;
        } else if(this->head == this->tail){
            newNode->next = this->head;
            newNode->prev = this->head;
            this->tail = newNode;
            this->head->next = newNode;
            this->head->prev = newNode;
        }
        else{
            newNode->prev = this->tail;
            newNode->next = this->head;
            this->tail->next = newNode; 
            this->head->prev = newNode;
            this->tail = newNode;
        }
    }

    bool play(){
        bool aliceWon = false;
        if(this->head == this->tail) return this->head->value;
        while (this->head->next != this->tail ){
            Node* ptr = this->head;
            Node* temp = this->tail;
            while(!(ptr->value)){
                if(temp == ptr) return aliceWon;
                ptr = ptr->next;
            } 
            ptr->next->value = !(ptr->next->value);
            ptr->prev->value = !(ptr->prev->value);
            if(ptr==this->head) this->head = this->head->next;
            if(ptr==this->tail) this->tail = this->tail->prev;
            ptr->prev->next = ptr->next;
            ptr->next->prev = ptr->prev;
            delete ptr; 
            aliceWon = !aliceWon;
        }
        if(((this->head->value) && !(this->head->next->value)) || (!(this->head->value) && (this->head->next->value))) return !aliceWon;
        return aliceWon;
    }

    void print(){
        Node* ptr = this->head;
        do {
            cout << ptr->value << " ";
            ptr = ptr->next;
        } while (ptr != this->head);
    }

    ~CircularDLinkedlist(){
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