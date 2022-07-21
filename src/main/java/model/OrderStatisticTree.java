package model;

/**
 * @Description: 顺序统计树
 * @Author xiaomingcong
 * @date 2021/6/23 4:15 上午
 * Version 1.0
 */
public class OrderStatisticTree extends RBTree{

    /**
       * @Description: 确定第i小的元素
       * @Param  x
     * @param i
       * @return model.BinaryTree.Node
       * @Author xiaomingcong
       * @date 2021/6/23 4:22 上午
       * Version 1.0
    */
    public Node osSelect(Node x,int i){
        if(x == null){
            return null;
        }
        int r;
        r = x.left.size + 1;
        if(i == r){
            return x;
        }else if(i < r){
            return osSelect(x.left,i);
        }else {
            return osSelect(x.right,i);
        }
    }

    /**
       * @Description: 确定一个元素的秩
       * @Param  t
     * @param x
       * @return int
       * @Author xiaomingcong
       * @date 2021/6/23 4:25 上午
       * Version 1.0
    */
    public int osRank(BinaryTree t,Node x){
        if(x == null){
            return 0;
        }
        int r = x.left.size - 1;
        Node y = x;
        while (y != t.root){
            if(y == y.parent.right){
                r = r + y.parent.left.size + 1;
            }
            y = y.parent;
        }
        return r;
    }


}
