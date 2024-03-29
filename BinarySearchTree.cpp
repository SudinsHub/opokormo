#include<bits/stdc++.h>
using namespace std;

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};

TreeNode* nullFinder(TreeNode* root, int val) {
        if(root->val>val){
            if(!root->left) return root;
            return nullFinder(root->left, val);
        } 
        if(!root->right) return root;
        return nullFinder(root->right, val);
    }

TreeNode* insertIntoBST(TreeNode* root, int val) {
    TreeNode* newNode = new TreeNode(val);
    if(!root) return newNode;
    TreeNode* temp = nullFinder(root, val);
    if(val < temp->val) temp->left = newNode;
    else temp->right = newNode;
    return root;
}

TreeNode* searchBST(TreeNode* root, int val) {
    if(!root) return nullptr;
    if(root->val == val) return root;
    if(root->val > val )return searchBST(root->left, val);
    else return searchBST(root->right, val);
    // return nullptr;
}

TreeNode* deleteNode(TreeNode* root, int key) {
    if(!root) return root;
    if(root->val == key){ // found
        if((root->left) && (root->right)){ // two children
            TreeNode* sucs = root->right;
            while(sucs->left) sucs = sucs->left;
            root->val = sucs->val;
        
            root->right = deleteNode(root->right, sucs->val);
        }
        else if((root->left) || (root->right)){ // 1 child
            TreeNode* temp = root;
            root = (root->left) ? root->left : root->right;
            delete temp;
        } else{ // no child
            root = nullptr;
        }
    }
    else if(root->val > key ) root->left = deleteNode(root->left, key);
    else root->right = deleteNode(root->right, key);
    return root;
}