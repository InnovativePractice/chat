package com.util;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 将字符串中的记号替换为相应符号的工具类
 *
 * @author Administrator
 * @since 2016/6/17
 */
@SuppressWarnings({"WeakerAccess", "FieldCanBeLocal"})
public class CompleteMoticon {
    private static String regMoticon = ":([^ ]+?):";
    private static HashMap<String, String> moticons;

    static {
        if (moticons == null) {
            moticons = new HashMap<>();
            moticons.put(":", ":");
            moticons.put("hello", "你好！");
            moticons.put("sorry", "不好意思。");
        }
    }

    /**
     * 将文本中的记号转换为相应文字
     *
     * @param s 需要转换的文本
     * @return 转换后的文本
     */
    public static String convertMoticonString(String s) {
        String result = s;
        // 进行匹配用的正则表达式，大小写不敏感
        Pattern p = Pattern.compile(regMoticon, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(result);
        int start = 0;
        while (m.find(start)) {
            String rep = moticons.get(m.group(1).toLowerCase());
            start = m.start();
            // 只在记号有对应时进行替换
            if (rep != null) {
                String pre = result.substring(0, start);
                String after = result.substring(start + m.group().length(), result.length());
                result = pre + rep + after;
//                result = result.replace(m.group(), rep);
                start += rep.length();
                // 发生变化，需要重新匹配
                m = p.matcher(result);
            } else {
                start += m.group().length();
            }
        }
        return result;
    }
}
