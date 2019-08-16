package com.example.fourtitudetask1.activities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.fourtitudetask1.R;
import com.example.fourtitudetask1.model.Dummy;
import com.example.fourtitudetask1.room.DummyDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DummyDetailActivity extends AppCompatActivity implements View.OnClickListener {

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
            id = getIntent().getIntExtra("id", 0);
            new GetDummy(DummyDetailActivity.this, id).execute();
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (getIntent().hasExtra("id")) {
            id = getIntent().getIntExtra("id", 0);
            new GetDummy(DummyDetailActivity.this, id).execute();
        }

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

    class GetDummy extends AsyncTask<Void, Void, Dummy> {
        private WeakReference<Context> context;
        private int id;

        public GetDummy(Context context, int id) {
            this.context = new WeakReference<>(context);
            this.id = id;
        }

        @Override
        protected Dummy doInBackground(Void... voids) {
            DummyDatabase dummyDatabase = DummyDatabase.getAppDatabase(context.get());
            Dummy dummy = dummyDatabase.dummyDao().getDummy(id);
            return dummy;
        }

        @Override
        protected void onPostExecute(Dummy dummy) {
            super.onPostExecute(dummy);

            tvTitle.setText(dummy.getTitle());
            tvSubtitle.setText(dummy.getSubtitle());
            tvDescription.setText(dummy.getDescription());

            Glide
                    .with(DummyDetailActivity.this)
                    .load(dummy.getImageUrl())
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(ivDummy);
        }
    }
}
