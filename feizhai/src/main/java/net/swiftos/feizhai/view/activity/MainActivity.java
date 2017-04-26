package net.swiftos.feizhai.view.activity;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.annotation.MainThread;
import android.widget.ImageView;
import android.widget.Toast;

import net.swiftos.common.application.BaseApplication;
import net.swiftos.common.di.component.AppComponent;
import net.swiftos.common.ospermission.PermissionCheck;
import net.swiftos.common.presenter.BasePresenter;
import net.swiftos.common.view.activity.BaseActivity;
import net.swiftos.eventposter.impls.customevent.annotation.InjectEvent;
import net.swiftos.eventposter.impls.customevent.entity.RunContextType;
import net.swiftos.feizhai.R;
import net.swiftos.usermodule.aop.DebugLog;
import net.swiftos.usermodule.aop.LoginChecked;
import net.swiftos.usermodule.aop.LoginRequired;
import net.swiftos.view.articleview.ArticleTextView;
import net.swiftos.view.articleview.ArticleView;
import net.swiftos.view.articleview.ContentType;
import net.swiftos.view.articleview.IArticleViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


public class MainActivity extends BaseFeizhaiActivity {

    @Bind(R.id.article_text)
    ArticleTextView articleTextView;
    @Bind(R.id.article_view)
    ArticleView articleView;
    @Bind(R.id.image_view)
    ImageView imageView;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_main;
    }

    @DebugLog
    @Override
    protected void initView() {
        startActivity(new Intent(this, HomeActivity.class));
    }

    @Override
    protected void initData() {
//        String test = "http://www.baidu.com dadawd  \uD83D\uDE31\uD83D\uDE31\uD83D\uDE31\uD83D\uDE31\uD83D\uDE2B\uD83D\uDE2B\uD83D\uDE2B\uD83D\uDE22\uD83D\uDE22\uD83D\uDE22\uD83D\uDE23\uD83D\uDC73\uD83D\uDE21\uD83D\uDE2D\uD83D\uDE22\uD83D\uDE31\uD83D\uDE31\uD83D\uDE2D\uD83D\uDE21\uD83D\uDC73\uD83D\uDE1D\uD83D\uDE1CI \uE32D emojicon\ndadwawd";
//        articleTextView.showText("http://www.baidu.com dadawd  \uD83D\uDE31\uD83D\uDE31\uD83D\uDE31\uD83D\uDE31\uD83D\uDE2B\uD83D\uDE2B\uD83D\uDE2B\uD83D\uDE22\uD83D\uDE22\uD83D\uDE22\uD83D\uDE23\uD83D\uDC73\uD83D\uDE21\uD83D\uDE2D\uD83D\uDE22\uD83D\uDE31\uD83D\uDE31\uD83D\uDE2D\uD83D\uDE21\uD83D\uDC73\uD83D\uDE1D\uD83D\uDE1CI \uE32D emojicon\ndadwawd");
//        BaseApplication.getAppComponent()
//                .imageLoader()
//                .context(this)
//                .load("http://img3.imgtn.bdimg.com/it/u=2969619028,981992831&fm=214&gp=0.jpg", imageView);
//        List<IArticleViewAdapter> article = new ArrayList<>();
//        article.add(new IArticleViewAdapter() {
//            @Override
//            public ContentType getType() {
//                return ContentType.Img;
//            }
//
//            @Override
//            public String getContent() {
//                return "http://up.qqjia.com/z/17/tu17742_2.jpg";
//            }
//        });
//        article.add(new IArticleViewAdapter() {
//            @Override
//            public ContentType getType() {
//                return ContentType.String;
//            }
//
//            @Override
//            public String getContent() {
//                return test;
//            }
//        });
//        articleView.setArticle(article);
//        articleView.setUrlClickListener(this::test);
    }

    @Override
    protected @IdRes int barColor() {
        return R.color.themeColor;
    }

    @Override
    protected Object setupActivityComponent() {
        return null;
    }

    @Override
    protected BasePresenter setPresenter() {
        return null;
    }

    @LoginRequired
    public void test(String str) {
        Toast.makeText(this, str , Toast.LENGTH_LONG).show();
    }

    @InjectEvent(runType = RunContextType.MainThread)
    public void unlogined(LoginChecked event) {
        Toast.makeText(this, event.getResult().toString() , Toast.LENGTH_LONG).show();
    }

}
