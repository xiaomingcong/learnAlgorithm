package model;

import java.util.*;

/**
 * @Description: 邻接表表示的图
 * @Author xiaomingcong
 * @date 2021/9/3 9:10 上午
 * Version 1.0
 */
public class Graph {

    static int time;


    //节点颜色
    enum Color{
        WHITE,GREY,BLACK
    }

    //邻接表的数据结构
    static class AdjacencyTable{
        Node[] adj;
        List<EdgeNode> edges;//边集,也可以从个个节点的邻接边获取adj[i].edges
        int e;//边的数量
        int v;//节点的数量
    }
    //顶点
    static class Node{
        int inDegree;//入度
        int num;//节点编号
        Color color;//颜色
        int d;//该点到s的最短路径长度，dfs中表示发现时间
        Node parent;//父节点
        List<EdgeNode> edges;//与该顶点邻接的边
        int f;//dfs中表示完成时间
    }

    // 与顶点相连的边
    static class EdgeNode{
        Node from;
        Node to;
        int w;
    }


    /**
       * @Description: 广度优先搜索
       * @Param  table 邻接表
     * @Pamam s 开始节点
       * @return void
       * @Author xiaomingcong
       * @date 2021/9/3 9:28 上午
       * Version 1.0
    */
    public static void bfs(AdjacencyTable table,Node s){
        for (int i = 0; i < table.v; i++) {
            Node node = table.adj[i];
            node.color = Color.WHITE;
            node.d = Integer.MAX_VALUE;
            node.parent = null;
        }
        s.color = Color.GREY;
        s.d = 0;
        s.parent = null;
        Queue<Node> queue = new ArrayDeque<Node>();
        queue.add(s);
        while(!queue.isEmpty()){
            Node u = queue.poll();
            for(EdgeNode e : u.edges){
                Node v = e.to;
                while(v != null){
                    if(v.color == Color.WHITE){
                        v.color = Color.GREY;
                        v.d = u.d + 1;
                        v.parent = u;
                        queue.add(v);
                    }
                }
            }
            u.color = Color.BLACK;
        }
    }

    /**
       * @Description: 输出已s为开始节点到v节点的最短路径，假设图已经按BFS计算出一颗广度优先树
       * @Param  table
     * @param s
     * @param v
       * @return void
       * @Author xiaomingcong
       * @date 2021/9/3 11:34 上午
       * Version 1.0
    */
    public void printPath(AdjacencyTable table,Node s,Node v){
        //bfs(table,s);
        if(v == s){
            System.out.print("->" + s.num);
        }else if(v.parent == null){
            System.out.println("no path from " + s.num + " to " + v.num);
        }else{
            printPath(table,s,v.parent);
            System.out.print("->" + v.num);
        }
    }

    /**
       * @Description: 深度优先搜索
       * @Param  table
     * @param s 开始节点
       * @return void
       * @Author xiaomingcong
       * @date 2021/9/3 11:31 上午
       * Version 1.0
    */
    public synchronized static void dfs(AdjacencyTable table,Node s){
        for(int i = 0; i < table.v; i++){
            Node node = table.adj[i];
            node.color = Color.WHITE;
            node.parent = null;
        }
        time = 0;
        dfsVisit(table,s);
        for (Node u : table.adj){
            if(u.color == Color.WHITE){
                dfsVisit(table,u);
            }
        }
    }

    public static void dfsVisit(AdjacencyTable table,Node u){
        time = time + 1;
        u.d = time;
        u.color = Color.GREY;
        for(EdgeNode e : u.edges){
            Node v = e.to;
            if(v.color == Color.WHITE){
                v.parent = u;
                dfsVisit(table,v);
            }
        }
        time = time + 1;
        u.f = time;
        u.color = Color.BLACK;
    }

    public static ArrayList<Node> toPoLogicalSort(AdjacencyTable table){
        ArrayList<Node> result = new ArrayList<>();

        return result;
    }


}
