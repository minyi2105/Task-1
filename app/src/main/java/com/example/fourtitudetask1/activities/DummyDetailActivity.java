package com.example.fourtitudetask1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fourtitudetask1.R;
import com.example.fourtitudetask1.model.Dummy;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DummyDetailActivity extends AppCompatActivity {

    //TODO unable to use R2
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tv_subtitle)
    TextView tvSubtitle;

    @BindView(R.id.tv_description)
    TextView tvDescription;

    @BindView(R.id.iv_dummy)
    ImageView ivDummy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy_detail);

        ButterKnife.bind(this);

        Bundle data = getIntent().getExtras();
        if (data != null) {
            Dummy dummy = (Dummy) data.getParcelable("dummy");

            tvTitle.setText(dummy.getTitle());
            tvSubtitle.setText(dummy.getSubtitle());
            tvDescription.setText(dummy.getDescription());

            Glide
                    .with(this)
                    .load(dummy.getImageUrl())
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(ivDummy);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
}
