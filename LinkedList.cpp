#include<bits/stdc++.h>
using namespace std;
struct Node{
    int value;
    Node* next;
};

struct Linkedlist{
    Node* head;
    Node* tail;
    Linkedlist(){
        this->head = new Node();
        this->head->next = nullptr;
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
    }    
    void insertAfterIthNode(int i, int x){
        Node* newNode = new Node();
        newNode->value = x;
        if(i==0){
            newNode->next = this->head;
            this->head = newNode;
        }else{
            int p=i;
            Node* ptr = this->head;
            while (--p) ptr = ptr->next;
            newNode->next = ptr->next;
            ptr->next = newNode; 
        }
        
    }
    void  printMiddle(){
        Node* itr = this->head;
        Node* middle = this->head;
        int even = 0;
        while(itr->next){
            if(!(itr->next->next)) {
                even = 1;
                break;
            }
            itr = itr->next->next;
            middle = middle->next;
        }
        // cout << "Not Printing" << endl; 
        if(!even){
            cout << middle->value << endl;
        } else{
            cout << middle->value << " " << middle->next->value << endl;
        }

    }
    void insertAtEnd(int x){
        Node* ptr = new Node();
        ptr->value = x;
        ptr->next = nullptr;
        this->tail->next = ptr;
        this->tail = ptr;
        // cout << this->tail->value << " ";
    } 
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
    void reverseList() {
        Node* r = this->head;
        Node* q = nullptr;
        Node* p = nullptr;
        while(r){
            p = q;
            q = r;
            r = r->next;
            q->next = p; 
        }
        this->tail = this->head;
        this->head = q;
        // return q;
    }

    ~Linkedlist(){
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

void printList(Node* ptr){
    if(ptr){
        cout << ptr->value << " ";
        printList(ptr->next);
    }
}
int nodeCount(Node* ptr){
    static int n = 0;
    if(ptr){
        n++;
        nodeCount(ptr->next);
    } else{
        return n;
    }
}
int nodeSum(Node* ptr){
    static int sum = 0;
    if(ptr){
        sum += (ptr->value);
        nodeSum(ptr->next);
    } else{
        return sum;
    }
}
int maxofList(Node* ptr){
    static int max = -23456;
    if(ptr){
        if(max < ptr->value) max = ptr->value;
        maxofList(ptr->next);
    } else{
        return max;
    }
}
void insertAfterIthNode(Node** head, int i, int x){ // for changing head pointer we have to take pointer to pointer
    Node* newNode = new Node();
    newNode->value = x;
    if(!(*head)){ // empty list
        newNode->next = nullptr;
        *head = newNode;
    }
    else if(i==0){
        newNode->next = *head;
        *head = newNode;
    }else{
        int p=i;
        Node* ptr = *head;
        while (--p) ptr = ptr->next;
        newNode->next = ptr->next;
        ptr->next = newNode; 
    }
    
}
Node* mergeTwoLists(Node* list1, Node* list2) {
        Node* head = new Node();
    // (list1 && list2) && 
        if((list1->value < list2->value)){
            head = list1;
            list1 = list1->next;
            head->next = nullptr;
        }else{
            head = list2;
            list2 = list2->next;
            head->next = nullptr;
        }

        Node* tail = head;
        while(list1 && list2){
            if((list1->value < list2->value)){
                tail->next = list1;
                tail = list1;
                list1 = list1->next;
                tail->next = nullptr;
            }else{
                tail->next = list2;
                tail = list2;
                list2 = list2->next;
                tail->next = nullptr;
            }
        }

        Node* rest = (list1) ? list1 : list2; 

        while(rest){
            tail->next = rest;
            tail = rest;
            rest = rest->next;
            tail->next = nullptr;
            
        }
        return head;
    }

int main(){
    Linkedlist* list1 = new Linkedlist();
    list1->head->value = 2;
    list1->insertAtEnd(8);
    list1->insertAtEnd(10);
    list1->insertAtEnd(25);
    list1->insertAtEnd(27);

    // Linkedlist* list2 = new Linkedlist();
    // list2->head->value = 4;
    // list2->insertAtEnd(7);
    // list2->insertAtEnd(12);
    // list2->insertAtEnd(14);

    list1->printMiddle();
    
    
    // delete ;
    return 0;
}