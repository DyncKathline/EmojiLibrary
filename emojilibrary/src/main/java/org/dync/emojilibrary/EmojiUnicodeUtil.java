package org.dync.emojilibrary;

import org.dync.emojilibrary.widget.EmojiParser;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by KathLine on 2017/1/22.
 * <pre>轻量版的emoji表情转unicode，和unicode转emoji，这里只是大概的定义了emoji表情的范围用正则</pre>
 * 如不满足需求可用{@link org.dync.emojilibrary.widget.EmojiParser#parseFromUnicode(String, EmojiParser.EmojiTransformer)}
 * 或者{@link org.dync.emojilibrary.widget.EmojiParser#parseToHtmlDecimal(String, EmojiParser.FitzpatrickAction)}
 */

public class EmojiUnicodeUtil {
    public static final String REGEX = "\\[(1(f|F)([a-fA-F]|[0-9]){3}|[023]([a-fA-F]|[0-9]){3})\\]";//匹配emoji表情的UNICODE

    public static ArrayList<String> getMatchEmojis(String regex, String src) {
        ArrayList<String> matchList = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(src);
        while (matcher.find()) {
            String match = matcher.group();
            matchList.add(match);
        }
        return matchList;
    }

    /**
     * 这里的如：[1f600]格式是16进制的，不能为其他进制
     * 输入的文本格式："An [1f600]awesome [1f603]string with a few [1f609]emojis!"
     * 输出的文本格式："An 😀awesome 😃string with a few 😉emojis!";
     *
     * @param src
     * @return
     */
    public static String getEmojisByUnicode(String src) {
        ArrayList<String> matchEmojis = getMatchEmojis(REGEX, src);
        for (String match : matchEmojis) {
            if (match.length() > 1) {
                String emoji = match.substring(1, match.length() - 1);
                int unicode = Integer.parseInt(emoji, 16);
                src = src.replace(match, new String(Character.toChars(unicode)));
            }
        }
        return src;
    }

    public static String getUnicodeByEmoji(String src) {
        char[] srcChars = src.toCharArray();
        ArrayList<String> unicodeList = new ArrayList<>();
        StringBuffer sBuffer = new StringBuffer();
        for (int offset = 0; offset < srcChars.length; ) {
            int codepoint = src.codePointAt(offset);
            String hexUnicode = Integer.toHexString(codepoint);
            if (Pattern.matches(REGEX, "[" + hexUnicode + "]")) {
                sBuffer.append('[').append(hexUnicode).append(']');
                unicodeList.add("[" + hexUnicode + "]");
                int charCount = Character.charCount(codepoint);
                offset += charCount;
            } else {
                sBuffer.append((char) codepoint);
                offset++;
            }
        }
        return sBuffer.toString();
    }

    private static boolean isEmojiCharacter(int codePoint) {
        return (codePoint >= 0x2600 && codePoint <= 0x27BF) // 杂项符号与符号字体
                || codePoint == 0x303D
                || codePoint == 0x2049
                || codePoint == 0x203C
                || (codePoint >= 0x2000 && codePoint <= 0x200F)//
                || (codePoint >= 0x2028 && codePoint <= 0x202F)//
                || codePoint == 0x205F //
                || (codePoint >= 0x2065 && codePoint <= 0x206F)//
                /* 标点符号占用区域 */
                || (codePoint >= 0x2100 && codePoint <= 0x214F)// 字母符号
                || (codePoint >= 0x2300 && codePoint <= 0x23FF)// 各种技术符号
                || (codePoint >= 0x2B00 && codePoint <= 0x2BFF)// 箭头A
                || (codePoint >= 0x2900 && codePoint <= 0x297F)// 箭头B
                || (codePoint >= 0x3200 && codePoint <= 0x32FF)// 中文符号
                || (codePoint >= 0xD800 && codePoint <= 0xDFFF)// 高低位替代符保留区域
                || (codePoint >= 0xE000 && codePoint <= 0xF8FF)// 私有保留区域
                || (codePoint >= 0xFE00 && codePoint <= 0xFE0F)// 变异选择器
                || codePoint >= 0x10000; // Plane在第二平面以上的，char都不可以存，全部都转
    }
}
