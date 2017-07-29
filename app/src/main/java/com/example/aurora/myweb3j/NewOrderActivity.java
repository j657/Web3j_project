package com.example.aurora.myweb3j;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class NewOrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener,CompoundButton.OnCheckedChangeListener {
    private seller seller_selected= new seller();
    private Spinner spin_day;
    private Context mContext;
    private ArrayList<Day> mData = null;
    private BaseAdapter myAdadpter = null;
    private CheckBox[] check_hour = new CheckBox[24];
    private int[] checkbox_id = new int[24];
    private Button btn_book;
    private String hour_selected = "";
    private String day_selected = "0";
    private String book_send;
    private byte[] hour_buffer = new byte[24];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Intent intent = getIntent();
        seller_selected=(seller)intent.getSerializableExtra("seller");
        toolbar.setTitle(seller_selected.parking_add);
        setSupportActionBar(toolbar);
        setSupportActionBar(toolbar);
        mData = new ArrayList<Day>();
        bindViews();

        TextView textName = (TextView) findViewById(R.id.seller_select_name);
        textName.setText(seller_selected.name);
        TextView textPhone = (TextView) findViewById(R.id.seller_select_phone);
        textPhone.setText(seller_selected.phone);
        TextView textAddress = (TextView) findViewById(R.id.seller_select_address);
        textAddress.setText(seller_selected.parking_add);

        hour_buffer= seller_selected.avaliable_date_1.getBytes();

//        for(int i = 0; i<check_hour.length; i++) {
//            String strcheckID = "checkbox" + i;
//            checkbox_id[i] = getResources().getIdentifier(strcheckID,"id","com.example.aurora.myweb3j");
//            check_hour[i] = (CheckBox) findViewById(checkbox_id[i]);
//            check_hour[i].setOnCheckedChangeListener(this);
//            if(hour_buffer[i]=='1') {
//                check_hour[i].setEnabled(false);
//            }
//            else{
//                check_hour[i].setEnabled(true);
//            }
//        }
//        btn_book = (Button) findViewById(R.id.btn_book);
//        btn_book.setOnClickListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    private void bindViews() {
        spin_day = (Spinner) findViewById(R.id.spin_day);
        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy");
        Calendar today = Calendar.getInstance();
        String date_1 = df.format(today.getTime());
        today.add(today.DATE,1);
        String date_2 = df.format(today.getTime());
        today.add(today.DATE,1);
        String date_3 = df.format(today.getTime());
        mData.add(new Day(R.drawable.day,date_1));
        mData.add(new Day(R.drawable.day,date_2));
        mData.add(new Day(R.drawable.day,date_3));

        myAdadpter = new MyAdapter<Day>(mData,R.layout.item_spin_day) {
            @Override
            public void bindView(ViewHolder holder, Day obj) {
                holder.setImageResource(R.id.img_icon,obj.gethIcon());
                holder.setText(R.id.txt_day, obj.gethDay());
            }
        };
        spin_day.setAdapter(myAdadpter);
        spin_day.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.spin_day:
                day_selected = ""+position;
                if(position==0)
                {
                    hour_buffer= seller_selected.avaliable_date_1.getBytes();
                    for(int i = 0; i<check_hour.length; i++) {
                        String strcheckID = "checkbox" + i;
                        checkbox_id[i] = getResources().getIdentifier(strcheckID,"id","com.example.aurora.myweb3j");
                        check_hour[i] = (CheckBox) findViewById(checkbox_id[i]);
                        if(hour_buffer[i]=='1') {
                            check_hour[i].setEnabled(false);
                        }
                        else{
                            check_hour[i].setEnabled(true);
                        }
                    }
                }
                else if(position==1)
                {
                    hour_buffer= seller_selected.avaliable_date_2.getBytes();
                    for(int i = 0; i<check_hour.length; i++) {
                        String strcheckID = "checkbox" + i;
                        checkbox_id[i] = getResources().getIdentifier(strcheckID,"id","com.example.aurora.myweb3j");
                        check_hour[i] = (CheckBox) findViewById(checkbox_id[i]);
                        if(hour_buffer[i]=='1') {
                            check_hour[i].setEnabled(false);
                        }
                        else{
                            check_hour[i].setEnabled(true);
                        }
                    }
                }
                else if(position==2)
                {
                    hour_buffer= seller_selected.avaliable_date_3.getBytes();
                    for(int i = 0; i<check_hour.length; i++) {
                        String strcheckID = "checkbox" + i;
                        checkbox_id[i] = getResources().getIdentifier(strcheckID,"id","com.example.aurora.myweb3j");
                        check_hour[i] = (CheckBox) findViewById(checkbox_id[i]);
                        if(hour_buffer[i]=='1') {
                            check_hour[i].setEnabled(false);
                        }
                        else{
                            check_hour[i].setEnabled(true);
                        }
                    }
                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }
    public void midToast(String str, int showTime)
    {
        Toast toast = Toast.makeText(getApplicationContext(), str, showTime);
        toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM , 0, 0);  //set the position to display
        LinearLayout layout = (LinearLayout) toast.getView();
        layout.setBackgroundColor(getResources().getColor(R.color.div_green));
        ImageView image = new ImageView(this);
        image.setImageResource(R.mipmap.check_ok);
        layout.addView(image, 0);
        toast.setGravity(Gravity.CENTER, 0, 0);
        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
        v.setTextColor(getResources().getColor(R.color.text_yellow));     //set the color
        toast.show();
    }
}
