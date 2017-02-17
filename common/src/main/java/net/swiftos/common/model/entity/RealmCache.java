package net.swiftos.common.model.entity;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ganyao on 2016/11/4.
 */

public class RealmCache extends RealmObject {

    @PrimaryKey
    private String key;
    private String content;


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
