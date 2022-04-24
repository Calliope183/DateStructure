package algorithm.string_matching;

import org.junit.jupiter.api.Test;


/**
 * @description: 字符串匹配问题
 * @author: WuW
 * @create: 2022/2/24 12:51
 */
public class StringMatching {

    @Test
    public void test(){
        String content = "我是大橘 大橘小可爱 ";
        String str = "大橘小可爱";
        System.out.println("—————————————————暴力匹配结果—————————————————");
        int i = violentMatching(content, str);
        System.out.println(str + ":::" + i);
        System.out.println("—————————————————暴力匹配结果—————————————————");
        System.out.println("—————————————————KMP匹配测试—————————————————");
        int[] next = kmpNext(str);
        int kmpMatching = kmpMatching(content, str, next);
        System.out.println(kmpMatching);
        System.out.println("—————————————————KMP匹配测试—————————————————");
    }

    /**
     * 字符串暴力匹配
     * @param content 被匹配的字符串
     * @param str 要匹配的字符串
     * @return 返回str在content第一次出现的下标
     */
    public int violentMatching(String content, String str){

        char[] contents = content.toCharArray();
        char[] strs = str.toCharArray();

        int j = 0;
        int i = 0;
        while (i < contents.length && j < strs.length){
            if (contents[i] == strs[j]){
                j ++;
                i ++;
            } else {
                // 回退
                i = i - (j - 1);
                j = 0;
            }
        }

        if (j == strs.length){
            return i - j;
        }

        return -1;
    }

    /**
     * KMP算法匹配
     * @param content 被匹配的字符串
     * @param str 要匹配的字符串
     * @return 返回str在content第一次出现的下标
     */
    public int kmpMatching(String content, String str, int[] next){

        for (int i = 0, j = 0; i < content.length(); i++) {
            while (j > 0 && content.charAt(i) != str.charAt(j)){
                j = next[j - 1];
            }
            if (content.charAt(i) == str.charAt(j)){
                j ++;
            }
            if (j == str.length()){
                return i - j + 1;
            }
        }

        return -1;
    }

    /**
     * 获取部分匹配表
     * @param str 要匹配的字符串
     * @return 返回str对应的部分匹配表
     */
    public int[] kmpNext(String str){
        int[] next = new int[str.length()];
        next[0] = 0;
        for (int i = 1, j = 0; i < str.length(); i ++){
            while (j > 0 && str.charAt(i) != str.charAt(j)){
                j = next[j - 1];
            }
            if (str.charAt(i) == str.charAt(j)){
                j ++;
            }
            next[i] = j;
        }
        return next;
    }

}
