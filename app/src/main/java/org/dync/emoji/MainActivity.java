package org.dync.emoji;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.dync.emojilibrary.EmojiUnicodeUtil;
import org.dync.emojilibrary.widget.EmojiParser;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView tvShoeUnicode;
    private EditText editText;
    private Button btnUnicode;
    private JSONObject jsonObject;


    private JSONObject emojiJson;
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

        btnUnicode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEmojiToTextView1(false, editText, tvShowEmoji);
            }
        });
        btnEmoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEmojiToTextView1(true, edtEmoji, tvShoeUnicode);
            }
        });

//        initData();
    }

    private void initData() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("grin", 0x1F601);
            jsonObject.put("joy", 0x1F602);
            jsonObject.put("smile", 0x1F604);
            jsonObject.put("sweat_smile", 0x1F605);
            jsonObject.put("wink", 0x1F609);
            jsonObject.put("laughing", 0x1F606);
            jsonObject.put("blush", 0x1F60A);
            jsonObject.put("yum", 0x1F60B);
            jsonObject.put("relieved", 0x1F60C);
            jsonObject.put("heart_eyes", 0x1F60D);
            jsonObject.put("smirk", 0x1F60F);
            jsonObject.put("sweat", 0x1F613);
            jsonObject.put("pensive", 0x1F614);
            jsonObject.put("confounded", 0x1F616);
            jsonObject.put("kissing_heart", 0x1F618);
            jsonObject.put("kissing_closed_eyes", 0x1F61A);
            jsonObject.put("stuck_out_tongue_winking_eye", 0x1F61C);
            jsonObject.put("stuck_out_tongue_closed_eyes", 0x1F61D);
            jsonObject.put("angry", 0x1F620);
            jsonObject.put("rage", 0x1F621);
            jsonObject.put("cry", 0x1F622);
            jsonObject.put("persevere", 0x1F623);
            jsonObject.put("triumph", 0x1F624);
            jsonObject.put("disappointed_relieved", 0x1F625);
            jsonObject.put("fearful", 0x1F628);
            jsonObject.put("weary", 0x1F629);
            jsonObject.put("sleepy", 0x1F62A);
            jsonObject.put("tired_face", 0x1F62B);
            jsonObject.put("sob", 0x1F62D);
            jsonObject.put("cold_sweat", 0x1F630);
            jsonObject.put("scream", 0x1F631);
            jsonObject.put("astonished", 0x1F632);
            jsonObject.put("flushed", 0x1F633);
            jsonObject.put("dizzy_face", 0x1F635);
            jsonObject.put("mask", 0x1F637);
            jsonObject.put("pray", 0x1F64F);
            jsonObject.put("fist", 0x270A);
            jsonObject.put("hand", 0x270B);
            jsonObject.put("v", 0x270C);
            jsonObject.put("heart", 0x2764);
            jsonObject.put("relaxed", 0x263A);
            jsonObject.put("ok_hand", 0x1F44C);
            jsonObject.put("thumbsup", 0x1F44D);
            jsonObject.put("thumbsdown", 0x1F44E);
            jsonObject.put("clap", 0x1F44F);
            jsonObject.put("broken_heart", 0x1F494);
            jsonObject.put("cupid", 0x1F498);
            jsonObject.put("revolving_hearts", 0x1F49E);
            jsonObject.put("fire", 0x1F525);
            jsonObject.put("grinning", 0x1F600);
            jsonObject.put("innocent", 0x1F607);
            jsonObject.put("smiling_imp", 0x1F608);
            jsonObject.put("sunglasses", 0x1F60E);
            jsonObject.put("expressionless", 0x1F611);
            jsonObject.put("confused", 0x1F615);
            jsonObject.put("kissing_smiling_eyes", 0x1F619);
            jsonObject.put("hushed", 0x1F62F);
            jsonObject.put("sleeping", 0x1F634);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.i("TAG", "initData: size = " + jsonObject.length());
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
            content = EmojiUnicodeUtil.getUnicodeByEmoji(text);
        } else {
            text = unicodeStr;
            content = EmojiUnicodeUtil.getEmojisByUnicode(text);
        }
        System.out.println(emojiStr);
        textView.setText(content);

//        Collection<Emoji> emojis = EmojiManager.getAll();
//        for (Emoji emoji: emojis) {
//            System.out.println("emoji" + emoji.toString());
//        }
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
            editText.setText(emojiStr);
//        content = EmojiParser.parseToHtmlDecimal(emojiStr);
//        System.out.println(content);
            content = EmojiParser.parseToHtmlHexadecimal(emojiStr);
            System.out.println(content);
        } else {
            editText.setText(unicodeStr);
            content = EmojiParser.parseToUnicode(unicodeStr);
            System.out.println(content);
        }

        textView.setText(content);
    }
}
