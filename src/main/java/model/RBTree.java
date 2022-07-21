package model;

/**
 * @Description: 红黑树
 * @Author xiaomingcong
 * @date 2021/6/21 10:13 下午
 * Version 1.0
 */
public class RBTree extends BinaryTree{



    /**
       * @Description: 对x节点进行左旋
       * @Param  t
     * @param x
       * @return void
       * @Author xiaomingcong
       * @date 2021/6/22 3:59 上午
       * Version 1.0
    */
    public void leftRotate(BinaryTree t,Node x){
        Node y = null;
        y = x.right;
        x.right = y.left;
        //----------------------顺序统计树的维护
        y.size = x.size;
        x.size = x.left.size + x.right.size + 1;
        //----------------------顺序统计树的维护
        if(y.left != null){
            y.left.parent = x;
        }
        y.parent = x.parent;
        if(x.parent == null){
            t.root = y;
        }else if(x == x.parent.left){
            x.parent.left = y;
        }else{
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    /**
       * @Description: 对y节点进行右旋
       * @Param  t
     * @param y
       * @return void
       * @Author xiaomingcong
       * @date 2021/6/22 4:08 上午
       * Version 1.0
    */
    public void rightRotate(BinaryTree t,Node y){
        Node x = null;
        x = y.left;
        y.left = x.right;
        //----------------------顺序统计树的维护
        x.size = y.size;
        y.size = y.left.size + y.right.size + 1;
        //----------------------顺序统计树的维护
        if(x.right != null){
            x.right.parent = y;
        }
        x.parent = y.parent;
        if(y.parent == null) {
            t.root = x;
        }else if(y == y.parent.left){
            y.parent.left = x;
        }else {
            y.parent.right = x;
        }
        x.right = y;
        y.parent = x;
    }

    @Override
    public void insert(BinaryTree t, Node z) {
        Node y = null;
        Node x = t.root;
        while (x != null){
            y = x;
            if(z.key < x.key){
                x = x.left;
            }else {
                x = x.right;
            }
        }
        z.parent = y;
        if(y == null){
            t.root = z;
        }else if(z.key < y.key){
            y.left = z;
        }else {
            y.right = z;
        }
        z.left = null;
        z.right = null;
        z.color = Color.RED;
        //-------------顺序统计树的维护
        z.size = 1;
        x = z.parent;
        while (x != null){
            x.size = x.size + 1;
        }
        //-------------顺序统计树的维护
        RBInsertFixUp(t,z);
    }

    /**
       * @Description: 红黑树插入节点之后的颜色纠正
       * @Param  t
     * @param z
       * @return void
       * @Author xiaomingcong
       * @date 2021/6/22 5:10 上午
       * Version 1.0
    */
    public void RBInsertFixUp(BinaryTree t,Node z){
        Node y = null;
        while(z.parent.color == Color.RED){
            if(z.parent == z.parent.parent.left){
                y = z.parent.parent.right;
                if(y.color == Color.RED){
                    z.color = Color.BLACK;
                    y.color = Color.BLACK;
                    y.parent.color = Color.RED;
                    z = z.parent.parent;
                    continue;
                }
                if(z == z.parent.right){
                    z = z.parent;
                    leftRotate(t,z);
                }
                z.parent.color = Color.BLACK;
                z.parent.parent.color = Color.RED;
                rightRotate(t,z.parent.parent);
            }else{
                y = z.parent.parent.left;
                if(y.color == Color.RED){
                    z.color = Color.BLACK;
                    y.color = Color.BLACK;
                    y.parent.color = Color.RED;
                    z = z.parent.parent;
                    continue;
                }
                if(z == z.parent.left){
                    z = z.parent;
                    rightRotate(t,z);
                }
                z.parent.color = Color.BLACK;
                z.parent.parent.color = Color.RED;
                leftRotate(t,z.parent.parent);
            }
        }
        t.root.color = Color.BLACK;
    }

    public void rBTransplant(BinaryTree t, Node u, Node v){
        if(u.parent == null){
            t.root = v;
        }else if(u == u.parent.left){
            u.parent.left = v;
        }else {
            u.parent.right = v;
        }
        v.parent = u.parent;
    }

    public void rBDelete(BinaryTree t, Node z){
        Node y = z;
        Node x = null;
        Color yOriginalColor = y.color;
        if(z.left == null){
            x = z.right;
            rBTransplant(t,z,z.right);
        }else if(z.right == null){
            x = z.left;
            rBTransplant(t,z,z.left);
        }else{
            y = treeMinimum(z.right);
            x = y.right;
            if(z == y.parent){
                x.parent = y;
            }else{
                rBTransplant(t,y,y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            rBTransplant(t,z,y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }
        if(yOriginalColor == Color.BLACK){
            rBDeleteFixUp(t,x);
        }
    }

    public void rBDeleteFixUp(BinaryTree t,Node x){
        Node w = null;
        while(x != t.root && x.color == Color.BLACK){
            if(x == x.parent.left) {
                w = x.parent.right;
                if (w.color == Color.RED) {
                    w.color = Color.BLACK;
                    x.parent.color = Color.RED;
                    leftRotate(t, x.parent);
                    w = x.parent.right;
                }
                if (w.left.color == Color.BLACK && w.right.color == Color.BLACK) {
                    w.color = Color.RED;
                    x = x.parent;
                } else {
                    if (w.right.color == Color.BLACK) {
                        w.left.color = Color.BLACK;
                        w.color = Color.RED;
                        rightRotate(t, w);
                        w = x.parent.right;
                    }

                    w.color = x.parent.color;
                    x.parent.color = Color.BLACK;
                    w.right.color = Color.BLACK;
                    leftRotate(t, x.parent);
                    x = t.root;
                }
            }else{
                w = x.parent.left;
                if(w.color == Color.RED){
                    w.color = Color.BLACK;
                    x.parent.color = Color.RED;
                    rightRotate(t,x.parent);
                    w = x.parent.left;
                }
                if(w.right.color == Color.BLACK && w.left.color == Color.BLACK){
                    w.color = Color.RED;
                    x = x.parent;
                }else {
                    if (w.left.color == Color.BLACK) {
                        w.right.color = Color.BLACK;
                        w.color = Color.RED;
                        leftRotate(t, w);
                        w = x.parent.left;
                    }
                    w.color = x.parent.color;
                    x.parent.color = Color.BLACK;
                    w.left.color = Color.BLACK;
                    rightRotate(t, x.parent);
                    x = t.root;
                }
            }
        }
        x.color = Color.BLACK;
    }


}
