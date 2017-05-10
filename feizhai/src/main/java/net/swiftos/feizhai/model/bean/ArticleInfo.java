package net.swiftos.feizhai.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ganyao on 2017/5/1.
 */

public class ArticleInfo implements Serializable {
    private String[] preViewPics = {"http://img.wzfzl.cn/uploads/allimg/170315/3-1F315102321.jpg","http://img.wzfzl.cn/uploads/allimg/170315/3-1F315102321.jpg","http://img.wzfzl.cn/uploads/allimg/170315/3-1F315102321.jpg"};

    public String[] getPreViewPics() {
        return preViewPics;
    }

    public void setPreViewPics(String[] preViewPics) {
        this.preViewPics = preViewPics;
    }
}
