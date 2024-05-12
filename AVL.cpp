#include<bits/stdc++.h>
using namespace std;

// balance factor = height of leftsubtree - height of rightsubtree 
// balance factor should be -1<bf<1 for every node
// rotation is performed only on tree nodes
// LL imbalance -> left subtreer left e add hoar fole imbalance
// LR imbalance -> left subtreer right e add hoar fole imbalance
// * Je path dhore insertion hoise, sei path dhore balancing factor change hobe.
// * Jei node er balancing factor 1 er beshi hoye jabe, oi node theke oi path dhore
// total 3 ta node niye LL, LR ittadi perform korte hobe

struct Node {
    int val;
    int height;
    Node *left;
    Node *right;
    Node() : val(0), left(nullptr), right(nullptr), height(1) {}
    Node(int x) : val(x), left(nullptr), right(nullptr), height(1) {}
    Node(int x, Node *left, Node *right) : val(x), left(left), right(right), height(1) {}
};

struct AVL{
    Node* root = nullptr;
    // helper functions for AVL tree
    int heightUpdate(Node* ptr){
        int leftHeight = (ptr && ptr->left) ? ptr->left->height : 0;
        int rightHeight = (ptr && ptr->right) ? ptr->right->height : 0;
        return leftHeight > rightHeight ? leftHeight+1 : rightHeight+1;
    }
    int balancingFactor(Node* ptr){
        int leftHeight = (ptr && ptr->left) ? ptr->left->height : 0;
        int rightHeight = (ptr && ptr->right) ? ptr->right->height : 0;
        return leftHeight-rightHeight;
    }
    Node* inorder(Node* ptr){
        if(!ptr) return nullptr;
        inorder(ptr->left);
        cout << ptr->val << " ";
        inorder(ptr->right);
    }

    Node* LLrotation(Node* ptr){
        bool flag = (this->root == ptr) ? true : false;
        Node* holder = ptr;
        Node* sndHolder = ptr->left->right;
        ptr = ptr->left;
        ptr->right = holder;
        ptr->right->left  = sndHolder;
        ptr->height = heightUpdate(ptr);
        if(flag) this->root = ptr;
        return ptr;
    }
    Node* LRrotation(Node* ptr){
        bool flag = (this->root == ptr) ? true : false;
        Node* A = ptr;
        Node* B = A->left;
        Node* C = B->right;
        Node* Cl = C->left;
        Node* Cr = C->right;
        ptr = C;
        ptr->left = B;
        ptr->left->right = Cl;
        ptr->right = A;
        ptr->right->left = Cr;
        ptr->height = heightUpdate(ptr);
        if(flag) this->root = ptr;
        return ptr;
    }
    Node* RLrotation(Node* ptr){
        bool flag = (this->root == ptr) ? true : false;
        Node* A = ptr;
        Node* B = A->right;
        Node* C = B->left;
        Node* Cl = C->left;
        Node* Cr = C->right;
        ptr = C;
        ptr->left = A;
        ptr->right = B;
        ptr->right->left = Cr;
        ptr->left->right = Cl;
        ptr->height = heightUpdate(ptr);
        if(flag) this->root = ptr;
        return ptr; 
    }
    Node* RRrotation(Node* ptr){
        bool flag = (this->root == ptr) ? true : false;
        Node* A = ptr;
        Node* Bl = ptr->right->left;
        ptr = ptr->right;
        ptr->left = A;
        ptr->left->right = Bl;
        ptr->height = heightUpdate(ptr);
        if(flag) this->root = ptr;
        return ptr;
    }
    Node* insertWithPointer(Node* ptr, int val) {
        if(!ptr) return new Node(val);
        
        // navigating to correct node
        if(val < ptr->val) ptr->left = insertWithPointer(ptr->left, val);
        else if(val > ptr->val) ptr->right = insertWithPointer(ptr->right, val);
        
        // updating height value throughout the path
        ptr->height = heightUpdate(ptr);

        // balancing height by rotations 
        // positive -> rotation will be needed on left
        // negative -> rotation will be needed on right
        if(balancingFactor(ptr) == 2 && balancingFactor(ptr->left)== 1) ptr = LLrotation(ptr);
        else if(balancingFactor(ptr) == 2 && balancingFactor(ptr->left)== -1) ptr = LRrotation(ptr);
        else if(balancingFactor(ptr) == -2 && balancingFactor(ptr->right)== 1) ptr = RLrotation(ptr);
        else if(balancingFactor(ptr) == -2 && balancingFactor(ptr->right)== -1) ptr = RRrotation(ptr);
        
        return ptr;
    }
    void insert(int val){
        if(!this->root) this->root = insertWithPointer(nullptr, val);
        else insertWithPointer(this->root, val);
    }
    Node* deleteNode(Node* ptr, int key) {
        if(!ptr) return ptr;
        if(ptr->val == key){ // found
            if((ptr->left) && (ptr->right)){ // two children
                if(heightUpdate(ptr->left) < heightUpdate(ptr->right)){
                    // right tree is heavier, should delete from that
                    Node* sucs = ptr->right;
                    while(sucs->left) sucs = sucs->left;
                    ptr->val = sucs->val;
                    ptr->right = deleteNode(ptr->right, sucs->val);
                } else{
                    Node* predecessor = ptr->left;
                    while(predecessor->right) predecessor = predecessor->right;
                    ptr->val = predecessor->val;
                    ptr->left = deleteNode(ptr->left, predecessor->val);
                }
            }
            else if((ptr->left) || (ptr->right)){ // 1 child
                Node* temp = ptr;
                ptr = (ptr->left) ? ptr->left : ptr->right;
                delete temp;
            } else{ // no child
                if(this->root == ptr) this->root = nullptr;
                delete ptr;
                return nullptr;
            }
        }
        else if(ptr->val > key ) ptr->left = deleteNode(ptr->left, key);
        else ptr->right = deleteNode(ptr->right, key);

        // Update height
        ptr->height = heightUpdate(ptr);
    
        // Balance Factor and rotation
        if (balancingFactor(ptr) == 2 && balancingFactor(ptr->left) == 1) {  // L1 rotation
            return LLrotation(ptr);
        } else if (balancingFactor(ptr) == 2 && balancingFactor(ptr->left) == -1){  // L-1 rotation
            return LRrotation(ptr);
        } else if (balancingFactor(ptr) == -2 && balancingFactor(ptr->right) == -1){  // R-1 rotation
            return RRrotation(ptr);
        } else if (balancingFactor(ptr) == -2 && balancingFactor(ptr->right) == 1){  // R1 rotation
            return RLrotation(ptr);
        } else if (balancingFactor(ptr) == 2 && balancingFactor(ptr->left) == 0){  // L0 rotation
            return LLrotation(ptr); // LR will also work
        } else if (balancingFactor(ptr) == -2 && balancingFactor(ptr->right) == 0){  // R0 rotation
            return RRrotation(ptr); // RL will also work
        }

        return ptr;
    }
    Node* deleteNode(int key) {
        return deleteNode(this->root, key);
    }

    int maxDepth(Node* root, int count) {
        if(!root) return count;
        int a = maxDepth(root->left, count++);
        int b = maxDepth(root->right, count++);
        return (a>b)?a:b;
    }
};

int main(){
    AVL* avl = new AVL();
    int arr[] = {31, 12, 51, 11, 57, 64, 10, 84, 94};
    for(int i=0; i<sizeof(arr)/sizeof(arr[0]); i++) avl->insert(arr[i]);
    // avl->deleteNode(avl->root, 10);
    // avl->inorder(avl->root);
    cout << avl->maxDepth(avl->root, 0);
    return 0;
}