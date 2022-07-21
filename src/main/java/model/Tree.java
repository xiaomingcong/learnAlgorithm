package model;

import java.util.LinkedList;

/**
 * @Description: TODO
 * @Author xiaomingcong
 * @date 2021/6/21 1:47 下午
 * Version 1.0
 */
public class Tree {

    void insert(Node root,Node n){

    };

    Node til(){
        return new Node();
    }

    class Node{

        Node(){

        }

        Node(int key){
            this.key = key;
        }

        int key;
        Node parent;
        LinkedList<Node> lefts;
        LinkedList<Node> rights;
        Color color = Color.RED;

    }

    enum Color{
        RED,BLACK;
    }

}
