package edu.myself.mywilddog;

import com.wilddog.client.ChildEventListener;
import com.wilddog.client.DataSnapshot;
import com.wilddog.client.SyncError;
import com.wilddog.client.SyncReference;

public class MyChildEventListener implements ChildEventListener {

    private Integer childrenCount;
    private SyncReference parentSyncRef;

    public MyChildEventListener(SyncReference parent) {
        childrenCount = 0;
        parentSyncRef = parent;
    }

    public void onChildAdded(DataSnapshot snapshot, String s) {
        childrenCount++;
        System.out.println("key=" + snapshot.getKey() + ", childrenCount=" + snapshot.getChildrenCount());
    }

    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

    }

    public void onChildRemoved(DataSnapshot dataSnapshot) {

    }

    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

    }

    public void onCancelled(SyncError syncError) {

    }

    public Integer getChildrenCount() {
        return childrenCount;
    }

}
