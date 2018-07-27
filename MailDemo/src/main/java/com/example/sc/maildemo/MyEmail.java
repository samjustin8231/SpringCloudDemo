package com.example.sc.maildemo;

/**
 * 类的实现描述：TODO 类实现描述
 *
 * @author sunyajun 2018/7/27 10:26
 */
public class MyEmail {

    private String receiver;
    private String subject;
    private String text;
    private String content;

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
