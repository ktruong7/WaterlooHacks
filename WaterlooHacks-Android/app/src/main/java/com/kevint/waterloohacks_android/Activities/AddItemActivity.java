package com.kevint.waterloohacks_android.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.kevint.waterloohacks_android.R;

public class AddItemActivity extends AppCompatActivity {

    private Context context;
    private ImageButton backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        context = this;
        initializeComponents();
    }

    private void initializeComponents() {
        backButton = (ImageButton) findViewById(R.id.back_button);
    }

    private void setUpButtons() {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
