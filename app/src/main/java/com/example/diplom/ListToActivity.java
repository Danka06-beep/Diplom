package com.example.diplom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.List;

public class ListToActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private int position;
    private List<Note> simpleAdapterContent = new ArrayList<>();
    private ListView list;
    private Button addBtn;
    private BaseAdapter listContentAdapter;
    final String LOG_TAG = "Mylog";
    private Toolbar toolbar;
    final int DIALOG_REMOVE = 1;


    @Override
    protected void onStart() {
        super.onStart();

        loadBaseList();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Initialization();
        setSupportActionBar(toolbar);
        addBtn.setOnClickListener(this);
        listContentAdapter = createAdapter(simpleAdapterContent);
        list.setAdapter(listContentAdapter);
        list.setOnItemClickListener(this);
        list.setOnItemLongClickListener(this);


    }

    private void Initialization() {
        toolbar = findViewById(R.id.toolbar);
        list = findViewById(R.id.list);
        addBtn = findViewById(R.id.add_btn);
        list = findViewById(R.id.list);
    }


    private BaseAdapter createAdapter(List<Note> values) {
        return new Adapter (values, getApplicationContext());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_setings, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intentEdit = new Intent(ListToActivity.this, Setings.class);
                startActivity(intentEdit);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_btn:
                Intent intentEdit = new Intent(ListToActivity.this, ListAdd.class);
                startActivity(intentEdit);
                break;
        }
    }

    private void loadBaseList() {

        simpleAdapterContent.clear();
        simpleAdapterContent.addAll(App.getNoteRepository().getNotes());
    }


    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        showDialog(DIALOG_REMOVE);
        position = i;
        return true;
    }

    DialogInterface.OnClickListener myClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            switch (i) {
                case Dialog.BUTTON_POSITIVE:
                    Log.d(LOG_TAG, "Удалить");
                    simpleAdapterContent.remove(position);
                    listContentAdapter.notifyDataSetChanged();
                    App.getNoteRepository().removeNotes(position);
                    break;
                case Dialog.BUTTON_NEGATIVE:
                    Log.d(LOG_TAG, "Нет");
                    break;
            }
        }
    };
    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_REMOVE) {
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setTitle(R.string.warning);

            adb.setMessage(R.string.remove_data);

            adb.setIcon(R.drawable.ic_baseline_info_24);

            adb.setPositiveButton(R.string.remove, myClickListener);

            adb.setNegativeButton(R.string.net, myClickListener);
            return adb.create();
        }
        return super.onCreateDialog(id);
    }
    public void onBackPressed() {

        super.onBackPressed();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
        Intent intentEdit = new Intent(ListToActivity.this, ListAdd.class);
        intentEdit.putExtra("position", i);
        startActivity(intentEdit);
    }
}