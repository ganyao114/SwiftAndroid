package net.swiftos.feizhai.model.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ganyao on 2017/5/17.
 */

public class Article implements Serializable {

    private Integer id;

    private String title;

    private String description;

    private String content;

    private long ctTime;

    private int ctId;

    private int topicId;

    private String ctName;

    private String articleType;

    private int comments;

    private int marks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCtTime() {
        return ctTime;
    }

    public void setCtTime(long ctTime) {
        this.ctTime = ctTime;
    }

    public int getCtId() {
        return ctId;
    }

    public void setCtId(int ctId) {
        this.ctId = ctId;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public String getCtName() {
        return ctName;
    }

    public void setCtName(String ctName) {
        this.ctName = ctName;
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }
}
