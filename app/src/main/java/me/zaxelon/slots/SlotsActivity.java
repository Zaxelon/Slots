package me.zaxelon.slots;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.security.SecureRandom;

public class SlotsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slots);
        final SlotsAdapter slots = SlotsAdapter.builder(this)
                .addSlots(R.id.slot_one, R.id.slot_two, R.id.slot_three, R.id.slot_four, R.id.slot_five)
                .addDrawables(R.drawable.i, R.drawable.ross_rodriguez, R.drawable.tina_caldwell, R.drawable.wallace_sutton)
                .setScrollTimePerInch(1f)
                .setDockingTimePerInch(0f)
                .setScrollTime(2500 + new SecureRandom().nextInt(1500))
                .setChildIncTime(1000)
                .setOnFinishListener(new Callback() {
                    @Override
                    public void OnFinishListener() {
                        Toast.makeText(SlotsActivity.this, "Обработай меня плз!?", Toast.LENGTH_SHORT).show();
                    }
                })
                .build();
        //slots.start();

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slots.start();
            }
        });
    }
}
