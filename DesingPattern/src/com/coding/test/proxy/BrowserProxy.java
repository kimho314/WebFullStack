package com.coding.test.proxy;

public class BrowserProxy implements IBrowser{
    private String url;
    private Html html;

    public BrowserProxy(String url){
        this.url = url;
    }

    @Override
    public Html show() {
        if(html == null){
            this.html = new Html(url);
            System.out.println("BrowerProxy loading html from: " + url);
        }
        System.out.println("BrowerProxy use cache html from: " + url);
        return html;
    }
}
