#include <bits/stdc++.h>
using namespace std;


class Stack{
    public:
    int size;
    int top;
    char* arr;
    Stack(int n){
        this->size = n;
        this->top = -1;
        this->arr = new char[this->size];
    }
    char push(char x){
        if(this->top == this->size-1){
            cout << "Stack Overflow !!!" << endl;
            return -1;
        } 
        this->top++;
        this->arr[this->top] = x;
        return x;
    }
    char pop(){
        if(this->top == -1){
            cout << "Stack Underflow pop!!!" << endl;
            return -1;
        } 
        this->top--;
        
        return this->arr[(this->top)+1];
    }
    char peek(){
        if(this->top == -1){
            cout << "Stack Underflow peek!!!" << endl;
            return -1;
        } 
        return this->arr[this->top];
    }
    void printStack(){
        for (int i = 0; i <= this->top; i++){
            cout << this->arr[i];
        }
        cout << endl;
    }
    void reversePrint(){

    }

};

void permutationGenerator(string str){
    static Stack* temp = new Stack(20);
    static int ind = 0;
    if(str[ind] == '\0') {
        temp->printStack();
        ind -= 1;
        temp->pop();
    }
    else{
        for(char x : str){
            temp->push(x);
            ind++;
            permutationGenerator(str);
        }
        temp->top--;
        ind--;
    }
    
}

int main(){
    permutationGenerator("ghka");

    return 0;
}