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

vector<int> inorderTraversal2(TreeNode* root, vector<int> *arr) {
    if(!root) return *arr;
    inorderTraversal2(root->left, arr);
    arr->push_back(root->val);
    inorderTraversal2(root->right, arr);
    return *arr;
}
vector<int> inorderTraversal(TreeNode* root) {
    
    vector<int> temp;
    return inorderTraversal2(root, &temp);

}

    
bool isSameTree(TreeNode* p, TreeNode* q) {
    static bool flag = true;
    if (!p && !q) return flag; // If both p and q are null, return true
    if ((p == nullptr) ^ (q == nullptr)) { // Check if exactly one of the nodes is null
        flag = false;
        return flag;
    }
    if (p->val != q->val) {
        flag = false;
        return flag;
    }
    isSameTree(p->left, q->left);
    isSameTree(p->right, q->right);
    return flag;
} 
vector<vector<int>> levelOrder(TreeNode* root) {
    queue<TreeNode*> Q;
    queue<TreeNode*> SecQ;
    vector<vector<int>> arr;
    vector<int> prothom;
    if(root){
        prothom.push_back(root->val);
        if(root->left) Q.push(root->left);
        if(root->right) Q.push(root->right);
        arr.push_back(prothom);
    }
        
    while(!Q.empty()){
        vector<int> list;
        while(!Q.empty()){
            TreeNode* temp = Q.front();
            Q.pop();
            list.push_back(temp->val);
            if(temp->left) {
                SecQ.push(temp->left);
            }
            if(temp->right) {
                SecQ.push(temp->right);
            }
        }
        arr.push_back(list);
        swap(Q, SecQ);
    }
    return arr;
}

int maxDepth(TreeNode* root) {
    static int count = 0;
    if(!root) return count;
    count++;
    int a = maxDepth(root->left);
    int b = maxDepth(root->right);
    return (a>b)?a:b;
}

int traversal(TreeNode* root, int count){
    if(!(root->left || root->right)) return count;
    int a = (root->left)?traversal(root->left, count+1):INT_MAX;
    int b = (root->right)?traversal(root->right, count+1):INT_MAX;
    return (a<b)?a:b;
}

int minDepth(TreeNode* root) {
    int count = 0;
    return (!root)?0:traversal(root, count+1);
}
