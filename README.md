# Slots
This Android Project help you to make your slots.
## Usage

### The slots needs to be configured in the XML:
```xml
    <your.package.SlotsView
        android:id="@+id/linear_slots"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="horizontal">

        <your.package.SlotView
            android:id="@+id/slot_one"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:scrollbars="none" />

        <your.package.SlotView
            android:id="@+id/slot_two"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:scrollbars="none" />

        <your.package.SlotView
            android:id="@+id/slot_three"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:scrollbars="none" />

        <your.package.SlotView
            android:id="@+id/slot_four"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:scrollbars="none" />
    </your.package.SlotsView>
```
    
### Add into item_list.xml:
```xml
    <LinearLayout
      xmlns:android="http://schemas.android.com/apk/res/android"
      android:layout_width="wrap_content"
      android:layout_height="wrap_content">
      <ImageView
          android:id="@+id/image_view"
          android:padding="5dp"
          android:layout_width="85dp"
          android:layout_height="85dp" />
    </LinearLayout>
```
## And Java code:
```java
    final SlotsAdapter slots = SlotsAdapter.builder(this)
        .addSlots(R.id.slot_one, R.id.slot_two, R.id.slot_three, R.id.slot_four)
        .addDrawables(R.drawable.i, R.drawable.ross_rodriguez, R.drawable.tina_caldwell, R.drawable.wallace_sutton)
        .setScrollTimePerInch(500f)
        .setDockingTimePerInch(20f)
        .build();
    slots.start();
```
