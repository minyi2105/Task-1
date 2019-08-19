package com.example.fourtitudetask1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fourtitudetask1.util.AsyncResponse;
import com.example.fourtitudetask1.R;
import com.example.fourtitudetask1.adapters.DummyAdapter;
import com.example.fourtitudetask1.model.Dummy;
import com.example.fourtitudetask1.util.DummyDbUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
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

        //Pre populate the 20 dummy records
        new DummyDbUtil.AddAllDummies(MainActivity.this, loadDummyList()).execute();
    }

    private List<Dummy> loadDummyList() {
        List<Dummy> dummyList = new ArrayList<>();

        List<String> listOfUrls = new ArrayList<>();
        listOfUrls.add("https://upload.wikimedia.org/wikipedia/commons/3/3a/Cat03.jpg");
        listOfUrls.add("https://mymodernmet.com/wp/wp-content/uploads/2019/07/russian-blue-cats-17-1024x1024.jpg");
        listOfUrls.add("https://homepages.cae.wisc.edu/~ece533/images/airplane.png");
        listOfUrls.add("https://homepages.cae.wisc.edu/~ece533/images/arctichare.png");
        listOfUrls.add("https://homepages.cae.wisc.edu/~ece533/images/boat.png");
        listOfUrls.add("https://homepages.cae.wisc.edu/~ece533/images/barbara.png");
        listOfUrls.add("https://homepages.cae.wisc.edu/~ece533/images/girl.png");
        listOfUrls.add("https://homepages.cae.wisc.edu/~ece533/images/fruits.png");
        listOfUrls.add("https://homepages.cae.wisc.edu/~ece533/images/frymire.png");
        listOfUrls.add("https://homepages.cae.wisc.edu/~ece533/images/goldhill.png");
        listOfUrls.add("https://homepages.cae.wisc.edu/~ece533/images/monarch.png");
        listOfUrls.add("https://homepages.cae.wisc.edu/~ece533/images/mountain.png");
        listOfUrls.add("https://homepages.cae.wisc.edu/~ece533/images/zelda.png");
        listOfUrls.add("https://homepages.cae.wisc.edu/~ece533/images/peppers.png");
        listOfUrls.add("https://homepages.cae.wisc.edu/~ece533/images/pool.png");
        listOfUrls.add("https://homepages.cae.wisc.edu/~ece533/images/watch.png");
        listOfUrls.add("https://homepages.cae.wisc.edu/~ece533/images/tulips.png");
        listOfUrls.add("https://homepages.cae.wisc.edu/~ece533/images/serrano.png");
        listOfUrls.add("https://homepages.cae.wisc.edu/~ece533/images/sails.png");
        listOfUrls.add("https://homepages.cae.wisc.edu/~ece533/images/lena.png");

        for (int i = 0; i < listOfUrls.size(); i++) {
            int currentNumber = i + 1;

            Dummy dummy = new Dummy(
                    "Title " + currentNumber,
                    "Subtite " + currentNumber + " Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley ",
                    "Description " + currentNumber + " Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum. Wwhen an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                    listOfUrls.get(i));

            dummyList.add(dummy);
        }

        return dummyList;
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
