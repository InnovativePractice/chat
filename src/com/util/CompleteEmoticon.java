package com.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 将字符串中的记号替换为相应符号的工具类
 *
 * @author Administrator
 * @since 2016/6/17
 */
@SuppressWarnings({"WeakerAccess", "FieldCanBeLocal"})
public class CompleteEmoticon {
    private static String regMoticon = ":([^ ]+?):";
    private static HashMap<String, String> emoticons;

    static {
        if (emoticons == null) {
            emoticons = new HashMap<>();
            emoticons.put(":", ":");
            emoticons.put("hello", "你好！");
            emoticons.put("sorry", "不好意思。");
            emoticons.put("sad", "TAT");
        }
    }

    /**
     * 使用自带的默认转换表进行转换
     *
     * @param s 需要转换的文本
     * @return 转换后的文本
     */
    public static String convertEmoticonString(String s) {
        return convertEmoticonString(s, emoticons);
    }

    /**
     * 将文本中的记号转换为相应文字
     *
     * @param s       需要转换的文本
     * @param reflect 转换对应表
     * @return 转换后的文本
     */
    public static String convertEmoticonString(String s, Map<String, String> reflect) {
        StringBuilder result = new StringBuilder();
        // 进行匹配用的正则表达式，大小写不敏感
        Pattern p = Pattern.compile(regMoticon, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(s);
        int start = 0;
        while (m.find(start)) {
            String rep = reflect.get(m.group(1).toLowerCase());
            // 存在对应符号，进行替换
            int e = m.end();
            if (rep != null) {
                int st = m.start();
                result.append(s.substring(start, st)).append(rep);
            } else {
                result.append(s.substring(start, e));
            }
            start = e;
            // 被放弃的算法
//            start = m.start();
            // 只在记号有对应时进行替换
//            if (rep != null) {
//                String pre = result.substring(0, start);
//                String after = result.substring(start + m.group().length(), result.length());
//                result = pre + rep + after;
////                result = result.replace(m.group(), rep);
//                start += rep.length();
//                // 发生变化，需要重新匹配
//                m = p.matcher(result);
//            } else {
//                start += m.group().length();
//            }
        }
        // 补上最后的部分
        result.append(s.substring(start, s.length()));
        return result.toString();
    }
}
