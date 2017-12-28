package me.zaxelon.slots;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

public class ScrollListener extends RecyclerView.OnScrollListener {
    Activity activity;
    public ScrollListener(Activity activity) {
        this.activity = activity;
    }

    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        switch (newState) {
            case RecyclerView.SCROLL_STATE_IDLE:
                System.out.println("The RecyclerView is not scrolling");
                Toast.makeText(activity, "Конец", Toast.LENGTH_SHORT).show();
        }

    }

    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

    }
}
