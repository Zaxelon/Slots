package me.zaxelon.slots;

import android.app.Activity;
import android.graphics.drawable.Drawable;
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
    private Float scrollTime = 500f;
    private Float dockingTime = 0f;

    public SlotsAdapter() {

    }

    private SlotsAdapter addSlots(Integer... slotsViewId) {
        for (Integer slotId: slotsViewId) {
            slotsView.add((SlotView) activity.findViewById(slotId));
        }
        return this;
    }

    private SlotsAdapter addDrawables(Integer... drawablesId) {
        for (Integer drawableId: drawablesId) {
            drawables.add(activity.getResources().getDrawable(drawableId));
        }
        return this;
    }

    private SlotsAdapter setScrollTimePerInch(Float scrollTime) {
        this.scrollTime = scrollTime;
        return this;
    }

    private SlotsAdapter setDockingTimePerInch(Float dockingTime) {
        this.dockingTime = dockingTime;
        return this;
    }

    private SlotsAdapter build() {
        for (SlotView slotView : slotsView) {
            SpeedManager.setScrollTime(scrollTime);
            RecyclerView.LayoutManager mLayoutManager = new SpeedManager(activity);
            slotView.setLayoutManager(mLayoutManager);
            SlotAdapter mAdapter = new SlotAdapter(drawables);
            slotView.setAdapter(mAdapter);
            scrollTime -= dockingTime;
        }
        drawables.clear();
        return this;
    }

    public boolean start() {
        if (!isWork) {
            isWork = true;
            for (final SlotView slotView : slotsView) {
                slotView.smoothScrollToPosition(Integer.MAX_VALUE);
                slotView.addOnScrollListener(new ScrollListener(activity));
                Thread t = new Thread(new Runnable() {
                    public void run() {
                        try {
                            Thread.sleep(1500 + new SecureRandom().nextInt(3500));
                            LinearLayoutManager layoutManager = ((LinearLayoutManager) slotView.getLayoutManager());
                            final int vs = layoutManager.findLastVisibleItemPosition() + 5;
                            slotView.smoothScrollToPosition(vs);
                            Thread.sleep(6000);
                            isWork = false;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                t.start();
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

        public Builder setScrollTimePerInch(Float scrollTime) {
            SlotsAdapter.this.setScrollTimePerInch(scrollTime);
            return this;
        }

        public Builder setDockingTimePerInch(Float dockingTime) {
            SlotsAdapter.this.setDockingTimePerInch(dockingTime);
            return this;
        }

        public SlotsAdapter build() {
            SlotsAdapter.this.build();
            return SlotsAdapter.this;
        }

    }
}
