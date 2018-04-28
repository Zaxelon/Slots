package me.zaxelon.slots;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.security.SecureRandom;

public class SlotsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slots);
        final SlotsBuilder.Builder builder = SlotsBuilder.builder(this);
        final SlotsBuilder slots = builder
                .addSlots(R.id.slot_one, R.id.slot_two, R.id.slot_three, R.id.slot_four, R.id.slot_five)
                .addDrawables(R.drawable.i, R.drawable.ross_rodriguez, R.drawable.tina_caldwell, R.drawable.wallace_sutton)
                .setScrollTimePerInch(1f)
                .setDockingTimePerInch(0f)
                .setScrollTime(2500 + new SecureRandom().nextInt(1500))
                .setChildIncTime(1000)
                .setOnFinishListener(new FinishListener())
                .build();

        findViewById(R.id.btn).setOnClickListener(v -> slots.start());
    }

    private class FinishListener extends Callback {
        @Override
        public void OnFinishListener() {
            Toast.makeText(SlotsActivity.this,
                    "Меня нужно обработать\nnumb last vis pos: "
                            + getLayoutManagers().get(1).findLastVisibleItemPosition(),
                    Toast.LENGTH_SHORT).show();

//            TODO:
//            example code snippet.To check the victory you can use a "for" loop
//            List<LinearLayoutManager> layoutManagers = getLayoutManagers();
//            List<Drawable> drawables = new ArrayList<>();
//
//            for (int i = 0; i < 5; i++) {
//                drawables.add(((ImageView) layoutManagers.get(i).findViewByPosition(
//                        layoutManagers.get(i).findFirstVisibleItemPosition() + 2))
//                        .getDrawable()
//                        .getCurrent());
//            }
//
//            if ((drawables.get(0) == drawables.get(1)) &&
//                    (drawables.get(1) == drawables.get(2))) {
//                //...
//            }
        }
    }
}
