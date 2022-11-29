package com.bridgelabz_BST;

public class BinarySearchTree {
    private TreeNode root;

    private class TreeNode{
        private int data;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int data){
            this.data = data;
        }
    }

    public void insert(int value){
        root = insert(root, value);
    }

    public TreeNode insert( TreeNode root, int value){
        if (root == null || root.data == value){
            root = new TreeNode(value);
            return root;
        }
        if (value < root.data){
            root.left = insert(root.left, value);
        } else {
            root.right = insert(root.right, value);
        }
        return root;
    }

    public void inOrder(TreeNode root) {
        if(root == null) {
            return;
        }

        inOrder(root.left);
        System.out.print(root.data + " ");
        inOrder(root.right);
    }

    public void inOrder() {
        if (root == null) {
            return;
        }
    }

    public static void search(TreeNode root, int data, TreeNode parent) {
        // if the root is null
        if (root == null) {
            System.out.print("Key Not found");
            return;
        }

        // if the key is found
        if (root.data == data) {
            if (parent == null) {
                System.out.print("The node with key " + data + " is root node");
            }

            else if (data < parent.data) {
                System.out.println("The given element " + data + " is the left node of the Parent Node " + parent.data);
            } else {
                System.out
                        .println("The given element " + data + " is the right node of the Parent Node " + parent.data);
            }

            return;
        }

        // if the given key is less than the root node, recur for the left subtree;
        // otherwise, recur for the right subtree

        if (data < root.data) {
            search(root.left, data, root);
        } else {
            search(root.right, data, root);
        }
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(3);
        tree.insert(16);
        tree.insert(11);
        tree.insert(22);
        tree.insert(40);
        tree.insert(30);
        tree.insert(56);
        tree.insert(63);
        tree.insert(67);
        tree.insert(65);
        tree.insert(60);
        tree.insert(95);
        tree.insert(70);

        tree.inOrder(tree.root);
        search(tree.root, 63, null);
        tree.inOrder(tree.root);
    }
}
