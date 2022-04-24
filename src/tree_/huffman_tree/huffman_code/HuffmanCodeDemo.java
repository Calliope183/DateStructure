package tree_.huffman_tree.huffman_code;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @description: 赫夫曼编码
 * @author: WuW
 * @create: 2022/2/11 20:15
 */
public class HuffmanCodeDemo {

    @Test
    public void testStr() {

        String content = "abaaacaabbbc";
        byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
        byte[] huffmanZip = HuffmanCode.huffmanZip(bytes);
        System.out.println(Arrays.toString(huffmanZip));
        byte[] decode = HuffmanCode.decode(huffmanZip, HuffmanCode.huffmanCodes);
        System.out.println(Arrays.toString(decode));

    }

    @Test
    public void testFile(){
        String srcPath = "E:\\JavaIO\\bg4.jpg";
        String destPath = "E:\\JavaIO\\bg4.zip";
        HuffmanCode.zipFile(srcPath, destPath);
        HuffmanCode.unZipFile(destPath, "E:\\JavaIO\\dest.jpg");
    }
}

class HuffmanCode{
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    static StringBuilder stringBuilder = new StringBuilder();

    /**
     * 根据字符数组，得到含有所有出现字符的list集合
     * @param bytes 根据字符串形成的字符数组
     * @return 返回包含所有出现字符结点的list集合
     */
    public static List<Node> getBytes (byte[] bytes){
        List<Node> list = new ArrayList<>();
        // 先将传进来的byte数组中每个字符出现的次数存放到map对象中
        Map<Byte, Integer> map = new HashMap<>();
        for (byte b : bytes) {
            Integer count = map.get(b);
            if (count == null){
                map.put(b, 1);
            } else {
                map.put(b, count + 1);
            }
        }
        // 接着循环map对象，根据出现的字符和字符出现的次数创建Node结点，并将其加入到list中
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            list.add(new Node(entry.getKey(), entry.getValue()));
        }
        // 返回list集合
        return list;
    }

    /**
     * 根据list集合中节点的weight形成Huffman树
     * @param list 含有所有字符结点的list集合
     * @return 返回赫夫曼树的根节点
     */
    public static Node toHuffmanTree(List<Node> list){
        while(list.size() > 1){
            Collections.sort(list);
            Node leftNode = list.get(0);
            Node rightNode = list.get(1);
            Node newNode = new Node(null, leftNode.weight + rightNode.weight);
            newNode.left = leftNode;
            newNode.right = rightNode;
            list.remove(leftNode);
            list.remove(rightNode);
            list.add(newNode);
        }
        return list.get(0);
    }

    /**
     * 前序遍历二叉树
     * @param curNode
     */
    public static void preOrder(Node curNode){

        if (curNode == null){
            System.out.println("树空");
            return;
        }
        System.out.println(curNode);
        if (curNode.left != null){
            preOrder(curNode.left);
        }
        if (curNode.right != null){
            preOrder(curNode.right);
        }

    }

    /**
     * 得到赫夫曼编码，并将最终的结果存放到map集合中
     * @param node 当前节点
     * @param code 要追加的字符，左0右1
     * @param stringBuilder 用于拼接路径
     */
    public static void getCodes(Node node, String code, StringBuilder stringBuilder){
        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);
        stringBuilder1.append(code);
        if (node != null){
            if (node.data == null){// 非叶子结点
                getCodes(node.left, "0", stringBuilder1);
                getCodes(node.right, "1", stringBuilder1);
            } else {
                huffmanCodes.put(node.data, stringBuilder1.toString());
            }
        }
    }

    /**
     * 方法重载，方便调用
     * @param root
     * @return
     */
    public static Map<Byte, String> getCodes(Node root){
        if (root == null){
            System.out.println("树空，不能得到对应的赫夫曼编码");
            return null;
        }
        getCodes(root.left, "0", stringBuilder);
        getCodes(root.right, "1", stringBuilder);
        return huffmanCodes;
    }

    /**
     * 压缩字符
     * @param bytes 原字符串对应的byte数组
     * @param huffmanCodes 由原字符形成的赫夫曼编码
     * @return 返回源字符串对应的字符数组，其中8位为一个byte
     */
    public static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes){
        // 先获得源字符串根据赫夫曼编码形成的对应的字符串，存储在StringBuilder中
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        // 统计要返回byte数组的长度,可使用len = (stringBuilder.length() + 7) / 8代替
        int len;
        if (stringBuilder.length() % 8 == 0){
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        // 创建要返回的byte数组
        byte[] result = new byte[len];
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i += 8){
            String temp;
            if ((i+8) > stringBuilder.length()){
                // 说明是最后一个字节
                temp = stringBuilder.substring(i);
            } else {
                temp = stringBuilder.substring(i, i + 8);
            }
            // 将截取到的字符串转换成byte（补码——反码——源码——十进制）
            result[index] = (byte)Integer.parseInt(temp, 2);
            index ++;
        }
        return result;
    }

    /**
     * 赫夫曼压缩数据
     * @param bytes 源字符串对应的byte数组
     * @return 返回压缩后的byte数组
     */
    public static byte[] huffmanZip(byte[] bytes){
        // 统计次数，并得到对应的node集合
        List<Node> nodes = getBytes(bytes);
        // 根据结点生成赫夫曼树，返回赫夫曼树的根节点
        Node root = toHuffmanTree(nodes);
        // 生成赫夫曼编码
        Map<Byte, String> huffmanCodes = getCodes(root);
        byte[] zip = zip(bytes, huffmanCodes);
        return zip;
    }

    /**
     * 将一个byte转换成一个二进制的字符串
     * @param flag 标志是否需要高位补零
     * @param b 传入的byte（8比特）
     * @return 返回二进制字符串(补码)
     */
    public static String byteToBitString(boolean flag, byte b){
        int temp = b;
        if (flag){
            temp |= 256;// 按位或，主要解决正数高位补零
        }
        String str = Integer.toBinaryString(temp);
        if (flag){
            // 说明是正数，则返回得到的最后8位即可
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    /**
     * 按照赫夫曼编码解压缩
     * @param huffmanBytes 源字符串压缩后的赫夫曼编码
     * @param huffmanCodes 赫夫曼编码方式
     * @return 返回元字符串对应的byte数组
     */
    public static byte[] decode(byte[] huffmanBytes, Map<Byte, String> huffmanCodes){
        // 由byte数组得到二进制（补码）字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, b));
        }

        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        // 创建集合存放byte
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length();) {
            int count = 1;
            boolean flag = true;
            Byte b;
            while (flag){
                String key = stringBuilder.substring(i, i+count);
                b = map.get(key);
                if (b == null){
                    count ++;
                } else {
                    flag = false;
                    list.add(b);
                    i += count;
                }
            }
        }
        byte[] result = new byte[list.size()];
        int index = 0;
        for (Byte b : list) {
            result[index] = b;
            index++;
        }
        return result;
    }

    /**
     * 压缩文件
     * @param srcPath 源文件路径
     * @param destPath 压缩后的文件路径
     */
    public static void zipFile(String srcPath, String destPath){
        InputStream inputStream = null;
        OutputStream outputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            inputStream = new FileInputStream(srcPath);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            byte[] huffmanZip = huffmanZip(bytes);
            outputStream = new FileOutputStream(destPath);
            objectOutputStream = new ObjectOutputStream(outputStream);
            // 将压缩后的文件和赫夫曼编码序列化，以便于解压缩
            objectOutputStream.writeObject(huffmanZip);
            objectOutputStream.writeObject(huffmanCodes);
            System.out.println("压缩成功");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 解压缩文件
     * @param srcPath 源文件所在位置
     * @param destPath 解压后文件位置
     */
    public static void unZipFile(String srcPath, String destPath){
        InputStream inputStream = null;
        OutputStream outputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            inputStream = new FileInputStream(srcPath);
            objectInputStream = new ObjectInputStream(inputStream);
            byte[] bytes = (byte[]) objectInputStream.readObject();
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) objectInputStream.readObject();
            byte[] decode = decode(bytes, huffmanCodes);
            outputStream = new FileOutputStream(destPath);
            outputStream.write(decode);
            System.out.println("解压成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

class Node implements Comparable<Node> {

    Byte data;// 数据域
    int weight;// 权重，出现的次数
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}
