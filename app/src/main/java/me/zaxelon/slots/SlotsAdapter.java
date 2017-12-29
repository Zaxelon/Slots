package me.zaxelon.slots;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class SlotsAdapter {
    private Activity activity;
    private List<SlotView> slotsView;
    private List<Drawable> drawables;
    private Boolean isWork;
    private Float scrollTimePerInch = 2f;
    private Float dockingTimePerInch = 0f;
    private Integer scrollTime = 3000;
    private Integer childIncTime = 500;
    private Callback callback;

    public SlotsAdapter() {

    }

    private void addSlots(Integer... slotsViewId) {
        for (Integer slotId: slotsViewId) {
            slotsView.add((SlotView) activity.findViewById(slotId));
        }
    }

    private void addDrawables(Integer... drawablesId) {
        for (Integer drawableId: drawablesId) {
            drawables.add(activity.getResources().getDrawable(drawableId));
        }
    }

    private void build() {
        Float timePerInch = scrollTimePerInch;
        for (SlotView slotView : slotsView) {
            SpeedManager.setScrollTime(timePerInch);
            RecyclerView.LayoutManager mLayoutManager = new SpeedManager(activity);
            slotView.setLayoutManager(mLayoutManager);
            SlotAdapter mAdapter = new SlotAdapter(drawables);
            slotView.setAdapter(mAdapter);
            timePerInch += dockingTimePerInch;
        }
        slotsView.get(slotsView.size()-1).addOnScrollListener(new ScrollListener(callback));
        drawables.clear();
    }

    public boolean start() {
        if (!isWork) {
            isWork = true;
            Integer tempTime = this.scrollTime;
            for (final SlotView slotView : slotsView) {
                tempTime += childIncTime;
                LinearLayoutManager layoutManager = ((LinearLayoutManager) slotView.getLayoutManager());
                slotView.smoothScrollToPosition(layoutManager.findLastVisibleItemPosition()+100);
                Handler handler = new Handler();
                Runnable runnable = new Runnable() {
                    public void run() {
                        LinearLayoutManager layoutManager = ((LinearLayoutManager) slotView.getLayoutManager());
                        final int vs = layoutManager.findLastVisibleItemPosition() + 5;
                        slotView.smoothScrollToPosition(vs);
                        isWork = false;
                    }
                };
                handler.postDelayed(runnable, tempTime);
            }
            return true;
        } else {
            Log.e("Slots", "Slots already run");
            return false;
        }
    }

    public static Builder builder(Activity activity) {
        return new SlotsAdapter().new Builder(activity);
    }

    public class Builder {
        private Builder(Activity activity) {
            SlotsAdapter.this.activity = activity;
            SlotsAdapter.this.slotsView = new ArrayList<>();
            SlotsAdapter.this.drawables = new ArrayList<>();
            SlotsAdapter.this.isWork = false;
        }

        public Builder addSlots(Integer... slotsViewId) {
            SlotsAdapter.this.addSlots(slotsViewId);
            return this;
        }

        public Builder addDrawables(Integer... drawablesId) {
            SlotsAdapter.this.addDrawables(drawablesId);
            return this;
        }

        public Builder setScrollTimePerInch(Float scrollTimePerInch) {
            SlotsAdapter.this.scrollTimePerInch = scrollTimePerInch;
            return this;
        }

        public Builder setDockingTimePerInch(Float dockingTimePerInch) {
            SlotsAdapter.this.dockingTimePerInch = dockingTimePerInch;
            return this;
        }

        public Builder setScrollTime(Integer scrollTime) {
            SlotsAdapter.this.scrollTime = scrollTime;
            return this;
        }

        public Builder setChildIncTime(Integer childIncTime) {
            SlotsAdapter.this.childIncTime = childIncTime;
            return this;
        }

        public Builder setOnFinishListener(Callback callback) {
            SlotsAdapter.this.callback = callback;
            return this;
        }

        public SlotsAdapter build() {
            SlotsAdapter.this.build();
            return SlotsAdapter.this;
        }

    }
}
