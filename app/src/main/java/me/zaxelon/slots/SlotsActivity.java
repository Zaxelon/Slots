package me.zaxelon.slots;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SlotsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slots);
        final SlotsAdapter slots = SlotsAdapter.builder(this)
                .addSlots(R.id.slot_one, R.id.slot_two, R.id.slot_three, R.id.slot_four)
                .addDrawables(R.drawable.i, R.drawable.ross_rodriguez, R.drawable.tina_caldwell, R.drawable.wallace_sutton)
                .setScrollTimePerInch(500f)
                .setDockingTimePerInch(20f)
                .build();
        //slots.start();
    }
}
