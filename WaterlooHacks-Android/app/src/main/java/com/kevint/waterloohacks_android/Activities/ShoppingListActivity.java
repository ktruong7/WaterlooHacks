package com.kevint.waterloohacks_android.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.kevint.waterloohacks_android.Adapters.ShoppingListAdapter;
import com.kevint.waterloohacks_android.R;

public class ShoppingListActivity extends AppCompatActivity {

    private Context context;

    private ImageButton addButton;
    private ImageButton backButton;
    private Button clearButton;
    private ListView shoppingListView;
    public static ShoppingListAdapter shoppingListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);
        context = this;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initializeComponents();
        setUpButtons();
        setUpListView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initializeComponents() {
        addButton = (ImageButton) findViewById(R.id.add_button);
        backButton = (ImageButton) findViewById(R.id.back_button);
        clearButton = (Button) findViewById(R.id.clear_button);
        shoppingListView = (ListView) findViewById(R.id.shopping_list);
    }

    private void setUpButtons() {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddItemActivity.class);
                startActivity(intent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoppingListAdapter.clear();
            }
        });
    }

    private void setUpListView() {
        shoppingListAdapter = new ShoppingListAdapter(this, android.R.layout.simple_list_item_1);
        shoppingListView.setAdapter(shoppingListAdapter);
    }
}
