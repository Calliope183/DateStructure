package linkedlist_.singlecirclelist;



/**
 * @description: 约瑟夫问题（丢手帕问题）
 * @author: WuW
 * @create: 2022/1/1 18:32
 */
public class Josepfu {

    public static void main(String[] args) {

        SingleCircleList singleCircleList = new SingleCircleList();


        // 头结点
        Node child1 = new Node("1宋江", null);
        singleCircleList.setHead(child1);
        singleCircleList.setCurrent(child1);
        child1.setNext(singleCircleList.getHead());

        // 小孩成圈
        Node child2 = new Node("2李逵",null);
        singleCircleList.addEle(child2);
        Node child3 = new Node("3林冲",null);
        singleCircleList.addEle(child3);
        Node child4 = new Node("4吴用",null);
        singleCircleList.addEle(child4);
        Node child5 = new Node("5卢俊义",null);
        singleCircleList.addEle(child5);

        // 显示所有小孩
        singleCircleList.showAll();

        // 规定从第k个小孩开始数
        int k = 1;
        Node num1 = singleCircleList.getHead();
        for (int i = 1; i < k; i++) {
            num1 = num1.getNext();
        }
        Node cur = num1;
        int count = 1;
        while(cur.getNext() != cur){
            if(count == 2){
                System.out.println(cur);
                singleCircleList.deleteEle(cur);
                count = 1;
                continue;
            }
            cur = cur.getNext();
            count ++;
        }
        System.out.println(cur);


    }
}
