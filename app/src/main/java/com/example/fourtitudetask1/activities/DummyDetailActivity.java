package com.example.fourtitudetask1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.fourtitudetask1.util.AsyncResponse;
import com.example.fourtitudetask1.R;
import com.example.fourtitudetask1.model.Dummy;
import com.example.fourtitudetask1.util.DummyDbUtil;
import com.example.fourtitudetask1.util.ValidateUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DummyDetailActivity extends AppCompatActivity implements View.OnClickListener, AsyncResponse {

    //TODO unable to use R2
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_subtitle)
    TextView tvSubtitle;
    @BindView(R.id.tv_description)
    TextView tvDescription;
    @BindView(R.id.iv_dummy)
    ImageView ivDummy;
    @BindView(R.id.fab_updateDummy)
    FloatingActionButton fabUpdateDummy;

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy_detail);

        ButterKnife.bind(this);
        fabUpdateDummy.setOnClickListener(this);

        if (getIntent().hasExtra("id")) {
            Glide
                    .with(DummyDetailActivity.this)
                    .load(ValidateUtil.getCircularProgressDrawable(DummyDetailActivity.this))
                    .placeholder(ValidateUtil.getCircularProgressDrawable(DummyDetailActivity.this))
                    .error(R.drawable.ic_broken_image)
                    .into(ivDummy);

            id = getIntent().getIntExtra("id", 0);

            DummyDbUtil.GetDummy asyncTask = new DummyDbUtil.GetDummy(DummyDetailActivity.this, id);
            //this to set delegate/listener back to this class
            asyncTask.delegate = this;
            asyncTask.execute();
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getIntent().hasExtra("id")) {
            id = getIntent().getIntExtra("id", 0);

            DummyDbUtil.GetDummy asyncTask = new DummyDbUtil.GetDummy(DummyDetailActivity.this, id);
            //this to set delegate/listener back to this class
            asyncTask.delegate = this;
            asyncTask.execute();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_updateDummy:
                Intent intent = new Intent(this, CreateNewDummyActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
        }
    }

    @Override
    public void processFinish(Dummy dummy) {
        tvTitle.setText(dummy.getTitle());
        tvSubtitle.setText(dummy.getSubtitle());
        tvDescription.setText(dummy.getDescription());

        Glide
                .with(DummyDetailActivity.this)
                .load(dummy.getImageUrl())
                .placeholder(ValidateUtil.getCircularProgressDrawable(DummyDetailActivity.this))
                .error(R.drawable.ic_broken_image)
                .into(ivDummy);
    }

    @Override
    public void processFinish(List<Dummy> dummies) {

    }
}
