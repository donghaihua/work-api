package edu.myself.mywilddog;

import com.wilddog.client.*;
import com.wilddog.wilddogcore.WilddogApp;
import com.wilddog.wilddogcore.WilddogOptions;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class First {
    public static void main(String[] args) {
        WilddogOptions options = new WilddogOptions.Builder().setSyncUrl("https://wd5893948993yctyok.wilddogio.com").build();
        WilddogApp.initializeApp(options);
        System.out.println("My wilddog initialized.");

        SyncReference root = WilddogSync.getInstance().getReference("/node3");
/*        MyBean bean = new MyBean();
        bean.setLover("Haydn 2007");
        bean.setAge(11L);
        ref.child("node3").setValue(bean);*/

/*        Query query = root.orderByKey();
        MyChildEventListener eventListener = new MyChildEventListener(root);
        query.addChildEventListener(eventListener);
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("key=" + root.getKey() + " has " + eventListener.getChildrenCount() + " children.");*/
/*        Recursive rootRecursive = new Recursive(root);
        rootRecursive.output();*/

        System.out.println("Job's done!");
        System.exit(0);
    }
}
