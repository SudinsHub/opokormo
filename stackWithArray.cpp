#include<bits/stdc++.h>
#include <string>
using namespace std;
// top == -1 indicates empty stack

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
            cout << this->arr[i] << " ";
        }
        cout << endl;
    }
    void reversePrint(){

    }

};

class IntStack{
    public:
    int size;
    int top;
    int* arr;
    IntStack(int n){
        this->size = n;
        this->top = -1;
        this->arr = new int[this->size];
    }
    int push(int x){
        if(this->top == this->size-1){
            cout << "Stack Overflow !!!" << endl;
            return -1;
        } 
        this->top++;
        this->arr[this->top] = x;
        return x;
    }
    int pop(){
        if(this->top == -1){
            cout << "Stack Underflow pop!!!" << endl;
            return -1;
        } 
        this->top--;
        
        return this->arr[(this->top)+1];
    }
    int peek(){
        if(this->top == -1){
            cout << "Stack Underflow peek!!!" << endl;
            return -1;
        } 
        return this->arr[this->top];
    }
    void printStack(){
        for (int i = 0; i <= this->top; i++){
            cout << this->arr[i] << " ";
        }
        cout << endl;
    }
};

int prece(char x){
    if( x=='+' || x=='-') return 1;
    else if( x=='*' || x=='/') return 2;
    else return 0;
}

int isOperator(char x){
    if( x=='+') return 1;
    else if( x=='-') return 2;
    else if( x=='*') return 3;
    else if( x=='/') return 4;
    else return 0;
}

string postfixConvert(string str){
    Stack* temp = new Stack(50);
    string result;
    for(char x : str){
        if(!isOperator(x)) {
            cout << x;
            result.push_back(x);
        }
        else{
            if((temp->top == -1)){
                temp->push(x);
            }else if((prece(x)>prece(temp->peek()))){
                temp->push(x);
            } else {
                result.push_back(temp->peek());
                cout << temp->pop();
                while (temp->top != -1){
                    if(prece(x)<=prece(temp->peek())){
                        result.push_back(temp->peek());
                        cout << temp->pop();
                    }
                    else break;
                }
                temp->push(x);
            }
        }
    }
    while (temp->top != -1){
        result.push_back(temp->peek());
        cout << temp->pop();
    }
    return result;
}

int postfixEval(string str){
    int result = 0;
    int ind, a, b, i;
    IntStack* nums = new IntStack(50);
    for(i=0; str[i]!='\0'; i++){
        ind = isOperator(str[i]); 
        if(ind){
            switch (ind){
            case 1:
                b = nums->pop();
                a = nums->pop();
                nums->push(a+b);
                break;
            case 2:
                b = nums->pop();
                a = nums->pop();
                nums->push(a-b);
                break;
            case 3:
                b = nums->pop();
                a = nums->pop();
                nums->push(a*b);
                break;
            case 4:
                b = nums->pop();
                a = nums->pop();
                nums->push(a/b);
                break;
            }
        }else{
            nums->push(str[i]-'0');
        }
    }
    return nums->pop();
}

int paranthesisChecker(string str){
    IntStack* bracket = new IntStack(50);
    for(char x : str){
        if(x=='(') bracket->push(1);
        else if(x==')'){
            if(bracket->top == -1) return 0;
            bracket->pop();
        }
    }
    if(bracket->top != -1) return 0;
    return 1;
}

int main(){
    string str;
    cin >> str;
    string res = postfixConvert(str);
    cout << endl << postfixEval(res);
    return 0;
}