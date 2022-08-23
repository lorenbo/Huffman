package com.cdtu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * @Auther: Loren
 * @Date: 2022/4/24 - 04 - 24 - 17:57
 * @Decsription:问题描述：
 * 假设某文本文档只包含英文单词，应用哈夫曼算法对该文档进行压缩和解压缩操作，使得该文档占用较少的存储空间。
 * 本题中将给出若干英文单词在文章中出现的次数，请编写程序为这些单词创建哈夫曼树，并计算该树带权路径长度(PWL）。
 *
 * 输入说明：
 * 第一行是一个整数N，表示一共有N组测试数据；
 * 后面是N组测试数据，每组测试数据由若干的整数构成，表示这组测试数据中各单词出现的次数，
 * 这些整数在同一行上，以 0 作为每组测试数据的结束标志。
 *
 * 输出要求：
 * 一行输出一个整数，表示对应的哈曼夫树的带权路径长度。
 *
 *
 * 输入示例：
 * 2
 * 5 2 9 11 8 3 7 0
   2 1 4 5 7 3 4 9 0
 *
 * 输出示例：
 * 120
 * 98
 */
public class Huffman {
    public static void main(String[] args) {
        System.out.println("请输入数据组数：");
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        ArrayList arr01=new ArrayList(n);
        for (int i=0;i<n;i++){
            ArrayList elements=new ArrayList();
            while (scanner.hasNext()){
                int element=scanner.nextInt();
                if (element==0)break;
                else elements.add(element);
            }
        arr01.add(elements);
        }

        for (int i=0;i<n;i++){
           Node root=build((ArrayList) arr01.get(i));
            System.out.println(root.wpl(0,0));
        }
    }
//    建立哈夫曼树
    public static Node build(ArrayList arr){
        ArrayList<Node> nodes=new ArrayList<Node>();
        for (Object i:arr){
            nodes.add(new Node((Integer)i));}  // 加强for遍历数组，并将数组每个元素变为Node类型，并且放入顺序表中
        while (nodes.size()>1){
            //实现从小到大排序
            Collections.sort(nodes);

            //取出节点权值最小的两个节点构成二叉树
            Node leftNode=nodes.get(0);
            Node rightNode=nodes.get(1);

            //重新构建新的二叉树，两个节点为左右子节点
            Node parent=new Node(leftNode.getData()+ rightNode.getData());
            parent.setLeft(leftNode);
            parent.setRight(rightNode);

            //删除两个子节点，并将父节点放入顺序表中
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        return nodes.get(0);//得到根节点
    }
}

//定义哈夫曼节点类，实现comparable接口
class Node implements Comparable<Node>{
    private int data;
    private Node left;
    private Node right;

    public Node(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override//重写，实现从小到大排序
    public int compareTo(Node o) {
        return this.data-o.data;
    }
    //求带权路径长度（WPL）
    public int wpl(int weight, int sum) {
        //当左子树和右子树为空时，则表示为哈夫曼树的叶子结点
        if (left == null && right == null) {
            sum += weight * data;
            return sum;
        }
        //否则不为空时，分别遍历左子树和右子树
        sum = left.wpl(weight + 1, sum);
        sum = right.wpl(weight + 1, sum);
        return sum;
    }
}
