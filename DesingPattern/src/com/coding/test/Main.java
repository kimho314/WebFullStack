package com.coding.test;

import com.coding.test.adaptor.*;
import com.coding.test.aop.AopBrowser;
import com.coding.test.decorator.*;
import com.coding.test.facade.Ftp;
import com.coding.test.facade.Reader;
import com.coding.test.facade.SftpClient;
import com.coding.test.facade.Writer;
import com.coding.test.observer.Button;
import com.coding.test.observer.IButtonListener;
import com.coding.test.singletone.AClazz;
import com.coding.test.singletone.BClazz;
import com.coding.test.singletone.SocketClient;
import com.coding.test.proxy.*;
import com.coding.test.strategy.Base64Strategy;
import com.coding.test.strategy.Encoder;
import com.coding.test.strategy.EncodingStrategy;
import com.coding.test.strategy.NormalStrategy;

import java.util.concurrent.atomic.AtomicLong;

public class Main {

    public static void main(String[] args) {
       /* AClazz aClazz = new AClazz();
        BClazz bClazz = new BClazz();

        SocketClient aClient = aClazz.getSocketClient();
        SocketClient bClient = bClazz.getSocketClient();

        System.out.println("두개의 객체가 동인한가?");
        System.out.println(aClient.equals(bClient));*/

        /*HairDryer hairDryer = new HairDryer();
        connect(hairDryer);

        Cleaner cleaner = new Cleaner();
        Electronic110V adapter = new SocketAdapter(cleaner);
        connect(adapter);

        AirConditioner airConditioner = new AirConditioner();
        Electronic110V airAdapter = new SocketAdapter(airConditioner);
        connect(airAdapter);*/

        /*Browser browser = new Browser("www.naver.com");
        browser.show();*/

       /* IBrowser browser = new BrowserProxy("www.naver.com");
        browser.show();
        browser.show();
        browser.show();
        browser.show();
        browser.show();
        browser.show();*/

        /*AtomicLong start = new AtomicLong();
        AtomicLong end = new AtomicLong();

        IBrowser aopBrowser = new AopBrowser("www.naver.com",
                () -> {
                    System.out.println("before");
                    start.set(System.currentTimeMillis());
                },
                () -> {
                    long now = System.currentTimeMillis();
                    end.set(now - start.get());
                });
        aopBrowser.show();
        System.out.println("loading time : " + end.get());

        aopBrowser.show();
        System.out.println("loading time : " + end.get());*/

        /*ICar audi = new Audi(1000);
        audi.showPrice();

        ICar audi3 = new A3(audi, "A3");
        audi3.showPrice();
        ICar audi4 = new A4(audi, "A4");
        audi4.showPrice();
        ICar audi5 = new A5(audi, "A5");
        audi5.showPrice();*/

        /*Button button = new Button("button");
        button.addListener(new IButtonListener() {
            @Override
            public void clickEvent(String event) {
                System.out.println(event);
            }
        });
        button.click("메시지 전달 : click1");
        button.click("메시지 전달 : click2");
        button.click("메시지 전달 : click3");
        button.click("메시지 전달 : click4");
        button.click("메시지 전달 : click5");*/

        /*Ftp ftpClient = new Ftp("www.food.co.kr", 22, "/home/etc");
        ftpClient.connect();
        ftpClient.moveDirectory();


        Writer writer = new Writer("text.tmp");
        writer.fileConnect();
        writer.write();

        Reader reader = new Reader("text.tmp");
        reader.fileConnect();
        reader.fileRead();

        reader.fileDisConnect();
        writer.fileDisConnect();
        ftpClient.disConnect();

        SftpClient sftpClient = new SftpClient("www.food.co.kr", 22, "/home/etc","text.tmp");
        sftpClient.connect();

        sftpClient.write();
        sftpClient.read();
        sftpClient.disConnect();*/

        Encoder encoder = new Encoder();

        // base64
        EncodingStrategy base64 = new Base64Strategy();

        // normal
        EncodingStrategy normal = new NormalStrategy();

        String message = "hello java";
        encoder.setEncodingStrategy(base64);
        String base64Result = encoder.getMessage(message);
        System.out.println(base64Result);

        encoder.setEncodingStrategy(normal);
        String normalResult = encoder.getMessage(message);
        System.out.println(normalResult);
    }

    public static void connect(Electronic110V electronic110V){
        electronic110V.powerOn();
    }
}
