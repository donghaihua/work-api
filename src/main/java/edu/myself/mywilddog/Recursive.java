package edu.myself.mywilddog;

import com.wilddog.client.Query;
import com.wilddog.client.SyncReference;

public class Recursive {
    private Query query;
    private SyncReference syncRef;
    private MyChildEventListener eventListener;

    public Recursive(SyncReference syncRef) {
        query = syncRef.orderByKey();
        eventListener = new MyChildEventListener(syncRef);
        query.addChildEventListener(eventListener);
        this.syncRef = syncRef;
    }

    public void output() {
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("key=" + syncRef.getKey() + " has " + eventListener.getChildrenCount() + " children.");
    }
}
