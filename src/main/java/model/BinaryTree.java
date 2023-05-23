package model;

import java.util.LinkedList;

/**
 * @Description: 按中序遍历排序的二叉树
 * @Author xiaomingcong
 * @date 2021/6/21 8:06 下午
 * Version 1.0
 */
public class BinaryTree {

    Node root = null;


    /**
       * @Description: 前序遍历
       * @Param  x
       * @return void
       * @Author xiaomingcong
       * @date 2021/6/21 10:23 下午
       * Version 1.0
    */
    public void preorderTreeWalk(Node x){
        if(x != null){
            System.out.println(x.key);
            preorderTreeWalk(x.left);
            preorderTreeWalk(x.right);
        }
    }

    /**
       * @Description: 中序遍历
       * @Param  x
       * @return void
       * @Author xiaomingcong
       * @date 2021/6/21 10:22 下午
       * Version 1.0
    */
    public void inorderTreeWalk(Node x){
        if(x != null){
            inorderTreeWalk(x.left);
            System.out.println(x.key);
            inorderTreeWalk(x.right);
        }
    }

    /**
       * @Description: 后序遍历
       * @Param  x
       * @return void
       * @Author xiaomingcong
       * @date 2021/6/21 10:26 下午
       * Version 1.0
    */
    public void postorderTreeWalk(Node x){
        if(x != null){
            postorderTreeWalk(x.left);
            postorderTreeWalk(x.right);
            System.out.println(x.key);
        }
    }

    /**
       * @Description: 查询二叉搜索树
       * @Param  x
     * @param k
       * @return model.BinaryTree.Node
       * @Author xiaomingcong
       * @date 2021/6/21 10:29 下午
       * Version 1.0
    */
    public Node iterativeTreeSearch(Node x,int k){
        while(x != null && x.key != k){
            if (k < x.key){
                x = x.left;
            }else {
                x = x.right;
            }
        }
        return x;
    }

    /**
       * @Description: 获取最小关键字的节点
       * @Param  x
       * @return model.BinaryTree.Node
       * @Author xiaomingcong
       * @date 2021/6/21 10:31 下午
       * Version 1.0
    */
    public Node treeMinimum(Node x){
        while(x != null && x.left != null){
            x = x.left;
        }
        return x;
    }

    /**
       * @Description: 获取最大关键字的节点
       * @Param  x
       * @return model.BinaryTree.Node
       * @Author xiaomingcong
       * @date 2021/6/21 10:33 下午
       * Version 1.0
    */
    public Node treeMaximum(Node x){
        while (x != null && x.right != null){
            x = x.right;
        }
        return x;
    }

    /**
       * @Description: 返回x的后继节点，如果x是这棵树的最大关键字，则返回null
       * @Param  x
       * @return model.BinaryTree.Node
       * @Author xiaomingcong
       * @date 2021/6/21 10:39 下午
       * Version 1.0
    */
    public Node treeSuccessor(Node x){
        if(x == null) return x;
        if(x.right != null){
            return treeMinimum(x.right);
        }
        Node y = x.parent;
        while(y != null && x == y.right){
            x = y;
            y = y.parent;
        }
        return y;
    }

    /**
       * @Description: 返回x的前继节点，如果x是这棵树的最小关键字，则返回null
       * @Param  x
       * @return model.BinaryTree.Node
       * @Author xiaomingcong
       * @date 2021/6/21 11:27 下午
       * Version 1.0
    */
    public Node treePredecessor(Node x){
        if(x == null) return x;
        if(x.left != null){
            return treeMaximum(x.left);
        }
        Node y = x.parent;
        while(y != null && x == y.left){
            x = y;
            y = y.parent;
        }
        return y;
    }

    /**
       * @Description: 把z节点插入到树中，并保持中序顺序
       * @Param  root
     * @param z
       * @return void
       * @Author xiaomingcong
       * @date 2021/6/21 11:31 下午
       * Version 1.0
    */
    public void insert(BinaryTree t,Node z){
        Node x = t.root;
        Node y = null;
        while(x != null){
            y = x;
            if(z.key < x.key){
                x = x.left;
            }else{
                x = x.right;
            }
        }
        z.parent = y;
        if(y == null){
            t.root = z;
        }else if(z.key < y.key){
            y.left = z;
        }else{
            y.right = z;
        }
    }

    /**
       * @Description: 在二叉树中删除z节点并保持中序顺序
       * @Param  t
     * @param z
       * @return void
       * @Author xiaomingcong
       * @date 2021/6/22 12:49 上午
       * Version 1.0
    */
    public void delete(BinaryTree t,Node z){
        if(z.left == null){
            transplant(t,z,z.right);
        }else if(z.right == null){
            transplant(t,z,z.left);
        }else{
           Node y = treeMinimum(z.right);
           if(y.parent != z){
               transplant(t,y,y.right);
               y.right = z.right;
               y.right.parent = y;
           }
           transplant(t,z,y);
           y.left = z.left;
           y.left.parent = y;
        }
    }

    /**
       * @Description: 用v为根的子树替换u
       * @Param  t
     * @param u
     * @param v
       * @return void
       * @Author xiaomingcong
       * @date 2021/6/22 12:43 上午
       * Version 1.0
    */
    public void transplant(BinaryTree t,Node u,Node v){
        if(u.parent == null) {
            t.root = v;
        }else if(u == u.parent.left){
            u.parent.left = v;
        }else{
            u.parent.right =v;
        }
        if(v != null){
            v.parent = u.parent;
        }
    }



    enum Color{
        RED,BLACK;
    }

    class Node{

        Node(){

        }

        Node(int key){
            this.key = key;
        }

        Node(int key, Node left, Node right){
            this.key = key;
            this.left = left;
            this.right = right;
        }

        int key;
        Node parent;
        Node left;
        Node right;
        Color color = Color.RED;//节点颜色
        int size;//以该节点为根节点的树所包含的节点的个数

    }



}
