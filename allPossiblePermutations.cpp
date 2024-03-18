#include<bits/stdc++.h>
#include <string>
using namespace std;

void permutationGenerator(string in, string out) {
    int n = in.length();
    if(n == 0) cout << out << endl;
    string temp = in;
    for(int i=0; temp[i]!='\0'; i++){
        string x = temp.erase(i, 1);
        temp = in; 
        permutationGenerator(x, out+in[i]);
    }
}

int main(){
    permutationGenerator("abcd", "");
    return 0;
}