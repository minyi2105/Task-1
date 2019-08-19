package com.example.fourtitudetask1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fourtitudetask1.R;
import com.example.fourtitudetask1.adapters.DummyAdapter;
import com.example.fourtitudetask1.model.Dummy;
import com.example.fourtitudetask1.util.AsyncResponse;
import com.example.fourtitudetask1.util.DummyDbUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AsyncResponse {

    //TODO unable to use R2
    @BindView(R.id.rv_dummy)
    RecyclerView rvDummy;
    @BindView(R.id.cv_nothingFound)
    CardView cvDummy;
    @BindView(R.id.fab_addNewDummy)
    FloatingActionButton fabAddNewDummy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        fabAddNewDummy.setOnClickListener(this);

        rvDummy.setHasFixedSize(true);
        rvDummy.setLayoutManager(new LinearLayoutManager(this));

        DummyDbUtil.GetAllDummies asyncTask = new DummyDbUtil.GetAllDummies(MainActivity.this);
        //this to set delegate/listener back to this class
        asyncTask.delegate = this;
        asyncTask.execute();
    }

    @Override
    protected void onResume() {
        super.onResume();

        DummyDbUtil.GetAllDummies asyncTask = new DummyDbUtil.GetAllDummies(MainActivity.this);
        //this to set delegate/listener back to this class
        asyncTask.delegate = this;
        asyncTask.execute();
    }

    @Override
    public void processFinish(Dummy dummy) {

    }

    @Override
    public void processFinish(List<Dummy> dummies) {
        DummyAdapter dummyAdapter = new DummyAdapter(MainActivity.this, rvDummy, cvDummy, dummies);
        rvDummy.setAdapter(dummyAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_addNewDummy:
                Intent i = new Intent(this, CreateNewDummyActivity.class);
                startActivity(i);
                break;
        }
    }
}
