import java.util.Scanner;

public class BST {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(){
            this.val = 0;
            this.left = null;
            this.right = null;
        }
        TreeNode(int val){
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    static TreeNode nullFinder(TreeNode root, int val) {
        if(root.val>val){
            if(root.left == null) return root;
            return nullFinder(root.left, val);
        } 
        if(root.right == null) return root;
        return nullFinder(root.right, val);
    }
    
    static TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode newNode = new TreeNode(val);
        if(root == null) return newNode;
        TreeNode temp = nullFinder(root, val);
        if(val < temp.val) temp.left = newNode;
        else temp.right = newNode;
        return root;
    }
    
    static TreeNode searchBST(TreeNode root, int val) {
        if(root == null) return null;
        if(root.val == val) return root;
        if(root.val > val )return searchBST(root.left, val);
        else return searchBST(root.right, val);
    }

    static void inorderTraversal(TreeNode root) {
        if(root == null) return;
        inorderTraversal(root.left);
        System.out.print(root.val + " ");
        inorderTraversal(root.right);
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        TreeNode root = null;
        String str = sc.nextLine();
        String[] arr = str.split(",");

        for (int i = 0; i < arr.length; i++) {
            int x = Integer.parseInt(arr[i].trim());
            if(root == null) root = new TreeNode(x);
            else insertIntoBST(root, x);
        }
        inorderTraversal(root);
        int qur = sc.nextInt();
        TreeNode res = searchBST(root, qur);
        if (res == null) {
            System.out.println(qur + " is not found in the BST");
        }
        else System.out.println(qur + " is found in the BST");
        sc.close();
    }
}