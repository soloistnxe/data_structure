package HuffmanCode;

import java.io.*;
import java.util.*;

public class HuffmanCode {
    public static void main(String[] args) {
        //测试压缩文件
//        String srcFile = "D://src.bmp";
//        String dstFile = "D://dst.zip";
//        zipFile(srcFile, dstFile);
//        System.out.println("压缩ok");
        //测试解压文件
        String zipFile = "D://dst.zip";
        String dstFile = "D://src2.bmp";
        unzipFile(zipFile, dstFile);
        System.out.println("解压成功");
       /* String str = "i like like like java do you like a java";
        byte[] countbyte = str.getBytes();

        //测试是否生成了霍夫曼编码
        byte[] zipcode = huffmanZip(countbyte);
        System.out.println(Arrays.toString(zipcode));
        System.out.println("原来的字符串" + new String(decode(huffmanCode, zipcode)));*/
    }
    //对压缩文件的解压

    /**
     * @param zipfile 准备解压的文件
     * @param dstfile 将文件解压到哪个路径
     */
    public static void unzipFile(String zipfile, String dstfile) {
        //定义文件的输入流
        InputStream inputStream = null;
        //定义一个对象输入流
        ObjectInputStream objectInputStream = null;
        //定义一个文件的输出流
        OutputStream outputStream = null;
        try {
            //创建文件输入流
            inputStream = new FileInputStream(zipfile);
            //创建一个和inputstream关联的对象输入流
            objectInputStream = new ObjectInputStream(inputStream);
            //读取byte数组 huffmanbytes
            byte[] huffmanBytes = (byte[]) objectInputStream.readObject();
            //读取霍夫曼编码表
            Map<Byte, String> hummfancode = (Map<Byte, String>) objectInputStream.readObject();
            //解码
            byte[] bytes = decode(hummfancode, huffmanBytes);
            //将bytes数组写入到目标文件
            outputStream = new FileOutputStream(dstfile);
            //写数据到文件中
            outputStream.write(bytes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                inputStream.close();
                objectInputStream.close();
                outputStream.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }


        }
    }

    /**
     * 压缩文件的方法
     *
     * @param srcFile 源文件路径
     * @param dstFile 压缩后的文件路径
     */
    public static void zipFile(String srcFile, String dstFile) {
        //创建输出流
        //创建一个文件的输入流
        FileInputStream fileInputStream = null;
        OutputStream outputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileInputStream = new FileInputStream(srcFile);
            //创建一个和源文件大小一样的byte数组
            byte[] b = new byte[fileInputStream.available()];
            //读取文件
            fileInputStream.read(b);
            //获取到文件对应的霍夫曼编码表,直接对源文件压缩
            byte[] huffmanBytes = huffmanZip(b);
            //创建文件的输出流,存放压缩文件
            outputStream = new FileOutputStream(dstFile);
            //创建一个和文件输出流关联的objectoutputstream
            objectOutputStream = new ObjectOutputStream(outputStream);
            //把霍夫曼编码后的字节数组写入压缩文件
            objectOutputStream.writeObject(huffmanBytes);
            //这里以对象流的方式写入霍夫曼编码，是为了以后恢复原文件时使用
            //注意一定要把编码表写入压缩文件
            objectOutputStream.writeObject(huffmanCode);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                fileInputStream.close();
                outputStream.close();
                objectOutputStream.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }
    //完成数据的解压
    //1.huffmanbyte数组重新转成霍夫曼编码对应二进制
    //2.二进制字符串对照霍夫曼编码表重新转换成原始的字符串

    /**
     * @param huffmanCode  霍夫曼编码表
     * @param huffmanBytes 霍夫曼编码得到的字节数组
     * @return 原来字符串对应的字节数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCode, byte[] huffmanBytes) {
        //1.先得到huffmanBytes对应的二进制字符串，形式1010100010...
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转成二进制字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            //判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(ByteToBitString(!flag, b));
        }

        //把字符串按照指定的霍夫曼编码进行解码
        //把霍夫曼编码表进行一个调换
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCode.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        //创建一个集合，存放byte
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag) {
                //递增的取出一个 key
                String key = stringBuilder.substring(i, i + count);//i不动，让count移动，直到匹配到一个字符
                b = map.get(key);
                if (b == null) { //说明没有匹配到
                    count++;
                } else
                    //匹配到
                    flag = false;
            }
            list.add(b);
            i += count; //i直接移动到count

        }
        //当for循环结束后，我们list中就存放了所有的字符
        //把list中的数据放入到byte[] 并返回
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }


    /**
     * 将一个byte转成一个二进制字符串
     *
     * @param b
     * @param flag 表示是否需要补高位
     * @return 是b对应二进制字符串，按照补码返回
     */
    private static String ByteToBitString(boolean flag, byte b) {
        //使用一个变量保存b
        int temp = b;
        if (flag) {
            temp |= 256;// 按位与 256: 1 0000 0000
        } //转成int
        //如果是正数，还存在补高位

        String str = Integer.toBinaryString(temp); //返回的是二进制补码
        if (flag) {
            return str.substring(str.length() - 8);
        } else
            return str;

    }
    //使用一个方法，将前面的方法封装起来

    /**
     * @param bytes 原始的字符串对应的字节数组
     * @return 经过编码后的数组
     */
    private static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodeList = getNodes(bytes);
        //创建霍夫曼树
        Node hufTree = creatHufTree(nodeList);
        //生成对应的霍夫曼编码
        Map<Byte, String> Huffmancode = getcode(hufTree);
        //根据霍夫曼编码将原始数据压缩
        byte[] huffmancode = zip(bytes, Huffmancode);
        return huffmancode;
    }

    //编写一个方法，将字符串对应的byte数组，通过生成的霍夫曼编码表返回一个霍夫曼编码压缩后的byte[]

    /**
     * @param bytes       原始的字符串对应的byte数组
     * @param huffmanCode 生成的霍夫曼编码map
     * @return 返回处理后的编码数组
     * 八位编码对应一个byte
     * 举例： String content = "i like like like java do you like a java"; =》 byte[] contentBytes = content.getBytes();
     * 返回的是 字符串 "1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100"
     * => 对应的 byte[] huffmanCodeBytes  ，即 8位对应一个 byte,放入到 huffmanCodeBytes
     * huffmanCodeBytes[0] =  10101000(补码) => byte  [推导  10101000=> 10101000 - 1 => 10100111(反码)=> 11011000= -88 ]
     * huffmanCodeBytes[1] = -88
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCode) {
        //1.利用huffmancode将byte转成霍夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //遍历bytes数组
        for (byte b : bytes) {
            stringBuilder.append(huffmanCode.get(b));
        }
        //将字符串转成byte数组
        //统计返回byte[]huffmanCodeBytes长度
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else
            len = stringBuilder.length() / 8 + 1;
        //创建存储压缩后的byte数组
        byte[] by = new byte[len];
        int index = 0;//记录第几个byte
        for (int i = 0; i < stringBuilder.length(); i += 8) {//因为是每8位对应一个byte
            String strByte;
            if (i + 8 > stringBuilder.length()) { //说明不够8位
                strByte = stringBuilder.substring(i);
            } else
                strByte = stringBuilder.substring(i, i + 8);
            //将strByte转成一个byte数组，放入到by数组
            by[index] = (byte) Integer.parseInt(strByte, 2);
            index++;

        }
        return by;
    }

    private static List<Node> getNodes(byte[] bytes) {
        //1.创建一个ArrList
        ArrayList<Node> nodes = new ArrayList<Node>();
        //遍历bytes，统计每一个byte出现的次数
        Map<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) { //Map还没有这个字符的数据，第一次
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }
        //把每一个键值对转成一个Node
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    //通过List 创建霍夫曼树
    private static Node creatHufTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            //排序,从小到大
            Collections.sort(nodes);
            //取出第一颗最小的二叉树
            Node leftnode = nodes.get(0);
            Node rightnode = nodes.get(1);
            //创建一颗新的二叉树,它的根节点没有data，只有权值
            Node root = new Node(null, leftnode.weight + rightnode.weight);
            root.left = leftnode;
            root.right = rightnode;
            //将已经处理的两颗二叉树从nodes删除
            nodes.remove(leftnode);
            nodes.remove(rightnode);
            //将新的二叉树加入到nodes
            nodes.add(root);
        }
        //nodes最后的节点就是哈夫曼树的根节点
        return nodes.get(0);
    }

    //生成霍夫曼树对应霍夫曼编码表
    //1.将生成的霍夫曼编码表存放到map中Map<Byte,String>
    static Map<Byte, String> huffmanCode = new HashMap<>();
    //2.在生成霍夫曼编码表时需要去拼接路径，定义一个StrBuilder 存储某个叶子节点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    //重载getcode
    private static Map<Byte, String> getcode(Node root) {
        if (root == null)
            return null;
        //处理root的左子树
        getcode(root.left, "0", stringBuilder);
        //处理右子树
        getcode(root.right, "1", stringBuilder);
        return huffmanCode;
    }

    /**
     * @param node          将传入的node节点的所有叶子节点的霍夫曼编码得到，并放入到huffmancode集合
     * @param code          路径：左子节点是0，右子节点1
     * @param stringBuilder 用于拼接路径
     */
    private static void getcode(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        //将code加入到stringBuilder2
        stringBuilder2.append(code);
        if (node != null) {
            //判断当前node是叶子节点还是非叶子节点
            if (node.data == null) {//非叶子节点
                //递归处理
                //向左
                getcode(node.left, "0", stringBuilder2);
                //向右递归
                getcode(node.right, "1", stringBuilder2);
            } else {//说明是一个叶子节点
                //就表示找到了某个叶子节点的最后
                huffmanCode.put(node.data, stringBuilder2.toString());
            }
        }
    }

    //前序遍历
    private static void preOrder(Node root) {
        if (root != null) {
            root.preorder();
        } else
            System.out.println("树空");
    }
}

class Node implements Comparable<Node> {
    Byte data; //存放的数据（字符）本身
    int weight; //表示字符出现的次数
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    //前序遍历
    public void preorder() {
        System.out.println(this);
        if (this.left != null)
            this.left.preorder();
        if (this.right != null)
            this.right.preorder();
    }
}