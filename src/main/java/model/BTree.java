package model;


import java.util.LinkedList;

/**
 * @Description: TODO
 * @Author xiaomingcong
 * @date 2021/6/28 11:18 下午
 * Version 1.0
 */
public class BTree {

    private Node root = null;
    private static final int t = 1024;//孩子节点个数，默认1024个

//    public BTree(int t){
//        this.t = t;
//        root = new Node();
//
//    }

    public BTree(){
//        this.t = 1024;
        root = new Node();

    }

    /**
       * @Description: 查询关键字k在以给定节点x为根节点中的位置
       * @Param  x
     * @param k
       * @return model.BTree.KeyPosition
       * @Author xiaomingcong
       * @date 2021/6/28 11:52 下午
       * Version 1.0
    */
    public static KeyPosition bTreeSearch(Node x, int k){
        if(x == null) return null;
        int n = x.n;
        Node pre = x;
        int i = 0;
        for(i = 0; i < n && k > x.keys[i];i++){

        }
        if(i < n && x.keys[i] == k ){
            return new KeyPosition(x,i);
        }else if(x.leaf){
            return null;
        }else{
            return bTreeSearch(x.children[i],k);
        }
    }

    /**
       * @Description: 分裂x节点中的第i + 1孩子（数组下标为i的孩子）,第i + 1个孩子为满节点（n=2t - 1）
       * @Param  x
     * @param i
       * @return void
       * @Author xiaomingcong
       * @date 2021/6/29 1:04 上午
       * Version 1.0
    */
    public static void bTreeSplitChild(Node x,int i){
        Node z = new Node();
        Node y = x.children[i];
        z.leaf = y.leaf;
        z.n = t - 1;//因为要分裂的节点为2i - 1；
        for (int j = 0; j < t - 1; j++) {
            z.keys[j] = y.keys[j + t];
            //类似于在循环里面调用y.keys.get(j + t)使用iterator迭代器应该效率更高，希望虚拟机有优化；
            //这里为了更好的表达语句的功能才这样使用
        }
        if(!y.leaf){
            for (int j = 0; j < t; j++) {
                z.children[j] = y.children[j + t];
            }
        }
        y.n = t - 1;
        for (int j = x.n; j > i; j--) {
            x.children[j + 1] = x.children[j];
        }
        x.children[i + 1] = z;
        for (int j = x.n - 1; j > i - 1; j--) {
            x.keys[j + 1] = x.keys[j];
        }
        x.keys[i] = y.keys[t - 1];
        x.n = x.n + 1;
        //x = null;//释放x对象的空间
    }

    public static void bTreeInsertNoFull(Node x,int k){
        int i = x.n - 1;
        if(x.leaf){
            while (i >= 0 && k < x.keys[i]){
                x.keys[i + 1] = x.keys[i];
                i = i - 1;
            }
            x.keys[i + 1] = k;
            x.n = x.n + 1;
        }else {
            while (i >= 0 && k < x.keys[i]){
                i = i - 1;
            }
            i = i + 1;
            if(x.children[i].n == 2 * t - 1){
                bTreeSplitChild(x,i);
                if(k > x.keys[i]){
                    i = i + 1;
                }
            }
            bTreeInsertNoFull(x.children[i],k);
        }
    }



    public static void bTreeInsert(BTree T,int k){
        Node r = T.root;
        if(r.n == 2 * t - 1){
            Node s = new Node();
            T.root = s;
            s.leaf = false;
            s.n = 0;
            s.children[0] = r;
            bTreeSplitChild(s,0);
            bTreeInsertNoFull(s,k);
        }else {
            bTreeInsertNoFull(r,k);
        }
    }
/*
    public void bTreeDelete(BTree T,int k){
        KeyPosition keyPosition = bTreeSearch(T.root,k);
        Node node = keyPosition.node;
        int index = keyPosition.index;
        if(node.leaf){
            for (int i = index; i < node.n - 1; i++) {
                node.keys[i] = node.keys[i + 1];
            }
            node.n = node.n - 1;
        }else if()
    }
*/

    static class Node{

        Node(){
            this.keys = new int[2 * t - 1];//可存储的关键字个数
            this.children = new Node[2 * t];
        }

        int n;//当前节点存储的关键字的个数;除了根节点外，每个节点必须至少有t - 1个关键字；如果非空，根节点至少有一个关键字；每个节点至多可以包含2t - 1 个关键字
        int[] keys;//数组中的关键字以非降序排序
        Node parent;
        Node[] children;
        boolean leaf = false;//是否是叶子节点
    }

    //关键字在树中的位置
    static class KeyPosition{
        Node node;
        int index;

        public KeyPosition(Node node,int index){
            this.node = node;
            this.index = index;
        }
    }
}
