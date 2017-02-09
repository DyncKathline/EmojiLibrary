package org.dync.emoji;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.dync.emojilibrary.EmojiUnicodeUtil;
import org.dync.emojilibrary.widget.EmojiParser;

public class MainActivity extends AppCompatActivity {

    private TextView tvShoeUnicode;
    private EditText editText;
    private Button btnUnicode;

    private TextView tvShowEmoji;
    private EditText edtEmoji;
    private Button btnEmoji;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvShoeUnicode = (TextView) findViewById(R.id.tv_show_unicdoe);
        editText = (EditText) findViewById(R.id.edt_unicode);
        btnUnicode = (Button) findViewById(R.id.btn_unicode);

        tvShowEmoji = (TextView) findViewById(R.id.tv_show_emoji);
        edtEmoji = (EditText) findViewById(R.id.edt_emoji);
        btnEmoji = (Button) findViewById(R.id.btn_emoji);

        btnEmoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEmojiToTextView(true, edtEmoji, tvShoeUnicode);
            }
        });
        btnUnicode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEmojiToTextView(false, editText, tvShowEmoji);
            }
        });
        String emoji = "\\ud83d\\ude01";//  \ud83d\ude01
        String string = EmojiUnicodeUtil.unicode2Emoji(emoji);
        System.out.printf("emoji=" + string);
        tvShowEmoji.setText(string);
    }

    /**
     * 1F601 - 1F64F
     * 2702 - 27B0
     * 1F680 - 1F6C0
     * 24C2
     * 1F170 - 1F251
     * 轻量版的emoji转unicode
     */
    private void setEmojiToTextView(boolean flag, EditText editText, TextView textView) {
        String text = editText.getText().toString();
        String content = "";
        String emojiStr = "An 😀awesome 😃string with a few 😉emojis!";
        String unicodeStr = "An [1f600]awesome [1f1ff][1f468][200d][1f469][200d][1f467]string with a few [1f609]emojis!";

        if (flag) {
//            text = emojiStr;
//            content = EmojiUnicodeUtil.getEmoji2Unicode(text);
            content = EmojiUnicodeUtil.emoji2Unicode(text);
        } else {
//            text = unicodeStr;
//            content = EmojiUnicodeUtil.getUnicode2Emojis(text);
            content = EmojiUnicodeUtil.unicode2Emoji(text);
        }
        System.out.println(emojiStr);
        textView.setText(content);
    }

    /**
     *
     */
    private void setEmojiToTextView1(boolean flag, EditText editText, TextView textView) {
        String text = editText.getText().toString();

        String content = "";
        String emojiStr = "An 😀awesome 😃string with a few 😉emojis!";
        String unicodeStr = "An [1f600]awesome [1f1ff][1f468][200d][1f469][200d][1f467]string with a few :wink:emojis!";

        if (flag) {
            editText.setText(text);
//        content = EmojiParser.parseToHtmlDecimal(emojiStr);
//        System.out.println(content);
            content = EmojiParser.parseToHtmlHexadecimal(text);
            System.out.println(content);
        } else {
            editText.setText(unicodeStr);
            content = EmojiParser.parseToUnicode(unicodeStr);
            System.out.println(content);
        }

        textView.setText(content);
    }
}
