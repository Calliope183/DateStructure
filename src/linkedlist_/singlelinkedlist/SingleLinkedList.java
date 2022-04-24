package linkedlist_.singlelinkedlist;


/**
 * @author WuW
 * @date 2021/12/31 12:08
 * @Des 单向链表
 */
public class SingleLinkedList {

    private final Node headNode = new Node("", null);


    /**
     * 结点是否存在
     *
     * @param node
     * @return
     */
    public boolean isExit(Node node) {
        return false;
    }

    /**
     * 添加元素
     *
     * @param node
     */
    public void addElement(Node node) {
        Node temp = headNode;
        while (true) {
            if (temp.nextNode == null) {
                break;
            }
            temp = temp.nextNode;
        }
        temp.nextNode = node;

    }

    /**
     * 插入元素
     * @param position
     * @param node
     */
    public void insertInPosition(int position, Node node){
        // 判断插入位置是否合法
        //……
        Node temp = headNode;
        for(int i = 0 ; i < position ; i++){
            temp = temp.nextNode;
        }
        node.nextNode = temp.nextNode;
        temp.nextNode = node;
    }

    /**
     * 删除元素
     * @param name
     */
    public void deleteElement(String name){
        Node temp = headNode;
        while(true){
            temp = temp.nextNode;
            if(temp.nextNode == null){
                break;
            }
            if(name.equals(temp.nextNode.name)){
                temp.nextNode = temp.nextNode.nextNode;
            }
        }
    }

    /**
     * 显示所有元素
     */
    public void showAllElements() {
        Node temp = headNode;
        while(true){
            temp = temp.nextNode;
            if(temp == null){
                break;
            }
            System.out.println(temp);
        }
    }

}
