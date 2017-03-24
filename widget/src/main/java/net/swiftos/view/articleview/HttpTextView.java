package net.swiftos.view.articleview;

import android.content.Context;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 大灯泡 on 2015/11/4.
 * 识别url的textView
 */
public class HttpTextView extends TextView {

    //测试文字
    public String testText =
        "1.测试测试测试google.cn测试曹娥U去我如\n" + "2.侧首IU包宿123124 baidu.com报道锁人副I我去额555\n"
            + "3.博啊us豆腐啊哦I吧安静哦.博爱us都I人.dsaboauo www.weiju.ba/xx2/b54\n"
            + "4.这是一个测试哟http://www.baidu.com,这是测试哟\n"
            + "5.测试测试啊是的赴欧 我们的网址是：qq.164701463.net测试测试哟\n"
            + "6.的撒发吧额听歌：https://xx.125.com 654987打飞机阿伯I安\n"
            + "7.把儿童的方向：ftp://4399.com多发生部位，大师傅帮你\n"
            + "8.这次是个多网址哟 www.baidu.com哈哈哈www.google.com垃圾都是泪放假啊是的佛I 8264.com\n"
            + "9.你敢相信这是一个测试？www.baidu.com/?html=12354bhb35&ask=dasoiubao\n"
            + "10.这是一个下载地址哟 www.baidu.com/img/xxxx.jpg\n"
            + "11.baidu.com这个地址在开头\n"
            + "12.这个地址在末尾baidu.com\n"
            + "13.这是文字加地址加哈哈baba.ba 微笑掉地赴澳IU发qq.com微笑";
    /*
    * 正则文本
    * ((http|ftp|https)://)(([a-zA-Z0-9\._-]+\.[a-zA-Z]{2,6})|([0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}))(:[0-9]{1,4})*(/[a-zA-Z0-9\&%_\./-~-]*)?|(([a-zA-Z0-9\._-]+\.[a-zA-Z]{2,6})|([0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}))(:[0-9]{1,4})*(/[a-zA-Z0-9\&%_\./-~-]*)?
    * */
    private String pattern =
        "((http|ftp|https)://)(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,4})*(/[a-zA-Z0-9\\&%_\\./-~-]*)?|(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,4})*(/[a-zA-Z0-9\\&%_\\./-~-]*)?";
    // 创建 Pattern 对象
    Pattern r = Pattern.compile(pattern);
    // 现在创建 matcher 对象
    Matcher m;
    //记录网址的list
    LinkedList<String> mStringList;
    //记录该网址所在位置的list
    LinkedList<UrlInfo> mUrlInfos;
    int flag= Spanned.SPAN_POINT_MARK;

    private boolean needToRegionUrl = true;//是否开启识别URL，默认开启

    private UrlClickListener urlClickListener;

    public HttpTextView(Context context) {
        this(context, null);
    }

    public HttpTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HttpTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mStringList = new LinkedList<>();
        mUrlInfos = new LinkedList<>();
    }

    public UrlClickListener getUrlClickListener() {
        return urlClickListener;
    }

    public void setUrlClickListener(UrlClickListener urlClickListener) {
        this.urlClickListener = urlClickListener;
    }

    public void setUrlText(CharSequence text) {
        if (needToRegionUrl) {
            SpannableStringBuilderAllVer stringBuilderAllVer = recognUrl(text);
            super.setText(stringBuilderAllVer);
            this.setMovementMethod(LinkMovementMethod.getInstance());
        } else {
            super.setText(text);
        }
    }

    public boolean getIsNeedToRegionUrl() {
        return needToRegionUrl;
    }

    public void setOpenRegionUrl(boolean needToRegionUrl) {
        this.needToRegionUrl = needToRegionUrl;
    }

    private SpannableStringBuilderAllVer recognUrl(CharSequence text) {
        mStringList.clear();
        mUrlInfos.clear();

        CharSequence contextText;
        CharSequence clickText;
        text = text == null ? "" : text;
        //以下用于拼接本来存在的spanText
        SpannableStringBuilderAllVer span = new SpannableStringBuilderAllVer(text);
        ClickableSpan[] clickableSpans = span.getSpans(0, text.length(), ClickableSpan.class);
        if (clickableSpans.length > 0) {
            int start=0;
            int end=0;
            for (int i=0;i<clickableSpans.length;i++){
                start=span.getSpanStart(clickableSpans[0]);
                end=span.getSpanEnd(clickableSpans[i]);
            }
            //可点击文本后面的内容页
            contextText = text.subSequence(end, text.length());
            //可点击文本
            clickText = text.subSequence(start,
                end);
        } else {
            contextText = text;
            clickText = null;
        }
        m = r.matcher(contextText);
        //匹配成功
        while (m.find()) {
            //得到网址数
            UrlInfo info = new UrlInfo();
            info.start = m.start();
            info.end = m.end();
            mStringList.add(m.group());
            mUrlInfos.add(info);
        }
        return jointText(clickText, contextText);
    }

    /** 拼接文本 */
    private SpannableStringBuilderAllVer jointText(CharSequence clickSpanText,
        CharSequence contentText) {
        SpannableStringBuilderAllVer spanBuilder;
        if (clickSpanText != null) {
            spanBuilder = new SpannableStringBuilderAllVer(clickSpanText);
        } else {
            spanBuilder = new SpannableStringBuilderAllVer();
        }
        if (mStringList.size() > 0) {
            //只有一个网址
            if (mStringList.size() == 1) {
                String preStr = contentText.toString().substring(0, mUrlInfos.get(0).start);
                spanBuilder.append(preStr);
                String url = mStringList.get(0);
                spanBuilder.append(url, new URLClick(url), flag);
                String nextStr = contentText.toString().substring(mUrlInfos.get(0).end);
                spanBuilder.append(nextStr);
            } else {
                //有多个网址
                for (int i = 0; i < mStringList.size(); i++) {
                    if (i == 0) {
                        //拼接第1个span的前面文本
                        String headStr =
                            contentText.toString().substring(0, mUrlInfos.get(0).start);
                        spanBuilder.append(headStr);
                    }
                    if (i == mStringList.size() - 1) {
                        //拼接最后一个span的后面的文本
                        spanBuilder.append(mStringList.get(i), new URLClick(mStringList.get(i)),
                            flag);
                        String footStr = contentText.toString().substring(mUrlInfos.get(i).end);
                        spanBuilder.append(footStr);
                    }
                    if (i != mStringList.size() - 1) {
                        //拼接两两span之间的文本
                        spanBuilder.append(mStringList.get(i), new URLClick(mStringList.get(i)), flag);
                        String betweenStr = contentText.toString()
                                                       .substring(mUrlInfos.get(i).end,
                                                           mUrlInfos.get(i + 1).start);
                        spanBuilder.append(betweenStr);
                    }
                }
            }
        } else {
            spanBuilder.append(contentText);
        }

        return spanBuilder;
    }

    //------------------------------------------定义-----------------------------------------------
    class UrlInfo {
        public int start;
        public int end;
    }

    class URLClick extends ClickableSpan {
        private String text;

        public URLClick(String text) {
            this.text = text;
        }

        @Override
        public void onClick(View widget) {
            if (urlClickListener != null) {
                urlClickListener.onUrlClick(text);
            }
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setColor(0xff517fae);
            ds.setUnderlineText(false);
        }
    }

    public interface UrlClickListener {
        void onUrlClick(String url);
    }
}
