# Slots
This Android Project helps you to make your slots.
## Usage

Clone this project:
$git clone https://github.com/Zaxelon/Slots.git

### The slots needs to be configured in the XML:
```xml
<me.zaxelon.slots.SlotsView
    android:id="@+id/linear_slots"
    android:layout_width="wrap_content"
    android:layout_height="255dp"
    android:orientation="horizontal">

    <me.zaxelon.slots.SlotView
        android:id="@+id/slot_one"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:scrollbars="none" />

    <me.zaxelon.slots.SlotView
        android:id="@+id/slot_two"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:scrollbars="none" />

    <me.zaxelon.slots.SlotView
        android:id="@+id/slot_three"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:scrollbars="none" />

    <me.zaxelon.slots.SlotView
        android:id="@+id/slot_four"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:scrollbars="none" />

    <me.zaxelon.slots.SlotView
        android:id="@+id/slot_five"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:scrollbars="none" />
</me.zaxelon.slots.SlotsView>
```
    
### Add into item_list.xml:
```xml
<ImageView xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/image_view"
    android:layout_width="85dp"
    android:layout_height="85dp"
    android:padding="5dp" />
```
## And Java code:
```java
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
            Toast.makeText(SlotsActivity.this, "Обработай меня плз!?\nnumb last vis pos :" + getLayoutManagers().get(1).findLastVisibleItemPosition() , Toast.LENGTH_SHORT).show();
        }
    })
    .build();

findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        slots.start();
    }
});
```
