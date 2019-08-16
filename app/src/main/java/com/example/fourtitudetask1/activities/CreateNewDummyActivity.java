package com.example.fourtitudetask1.activities;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.fourtitudetask1.R;
import com.example.fourtitudetask1.model.Dummy;
import com.example.fourtitudetask1.room.DummyDatabase;
import com.example.fourtitudetask1.util.DummyUtil;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateNewDummyActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.et_title)
    EditText etTitle;
    @BindView(R.id.et_subtitle)
    EditText etSubtitle;
    @BindView(R.id.et_description)
    EditText etDescription;
    @BindView(R.id.et_imageUrl)
    EditText etImageUrl;
    @BindView(R.id.iv_imagePreview)
    ImageView ivImagePreview;
    @BindView(R.id.btn_save)
    Button btnSave;

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_dummy);

        ButterKnife.bind(this);
        btnSave.setOnClickListener(this);

        //if it gets through here which means the user clicked the update button
        if (getIntent().hasExtra("id")) {
            id = getIntent().getIntExtra("id", 0);
            new GetDummy(CreateNewDummyActivity.this, id).execute();
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //doing both OnFocusChangeListener and TextChangedListener because to prevent imagePreview is not showing before proceeding by clicking the btnSave
        etImageUrl.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                getUrlAndPreviewImage();
            }
        });

        etImageUrl.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                getUrlAndPreviewImage();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
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

    public void getUrlAndPreviewImage() {
        ivImagePreview.setVisibility(View.VISIBLE);

        String imageUrl = etImageUrl.getText().toString();

        Sprite doubleBounce = new DoubleBounce();

        if (!TextUtils.isEmpty(imageUrl)) {
            Glide
                    .with(CreateNewDummyActivity.this)
                    .load(imageUrl)
                    .placeholder(doubleBounce)
                    .error(R.drawable.ic_broken_image)
                    .into(ivImagePreview);
        }
    }

    class AddDummy extends AsyncTask<Void, Void, Void> {
        private Dummy dummy;
        private WeakReference<Context> context;

        public AddDummy(Context context, Dummy dummy) {
            this.context = new WeakReference<>(context);
            this.dummy = dummy;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            DummyDatabase dummyDatabase = DummyDatabase.getAppDatabase(context.get());
            dummyDatabase.dummyDao().insert(dummy);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(context.get(), "New dummy created!", Toast.LENGTH_SHORT).show();
            ((Activity) context.get()).finish();
        }
    }

    class UpdateDummy extends AsyncTask<Void, Void, Void> {
        private Dummy dummy;
        private WeakReference<Context> context;

        public UpdateDummy(Context context, Dummy dummy) {
            this.context = new WeakReference<>(context);
            this.dummy = dummy;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            DummyDatabase dummyDatabase = DummyDatabase.getAppDatabase(context.get());
            dummyDatabase.dummyDao().update(dummy);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(context.get(), "Dummy updated successfully!", Toast.LENGTH_SHORT).show();
            finish();
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

            etTitle.setText(dummy.getTitle());
            etSubtitle.setText(dummy.getSubtitle());
            etDescription.setText(dummy.getDescription());
            etImageUrl.setText(dummy.getImageUrl());
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save:

                Map<View, String> map = new HashMap<>();
                map.put(etTitle, "Title is required.");
                map.put(etSubtitle, "Subtitle is required.");
                map.put(etDescription, "Description is required");
                map.put(etImageUrl, "Image Url is required.");

                String title = DummyUtil.getOnlyText(etTitle);
                String subtitle = DummyUtil.getOnlyText(etSubtitle);
                String description = DummyUtil.getOnlyText(etDescription);
                String imageUrl = DummyUtil.getOnlyText(etImageUrl);

                if (!DummyUtil.isInputEmpty(view, map)) {
                    Dummy dummy = new Dummy(title, subtitle, description, imageUrl);

                    //if it gets through here which means the user clicked the update button
                    if (getIntent().hasExtra("id")) {
                        dummy.setId(id);
                        new UpdateDummy(CreateNewDummyActivity.this, dummy).execute();
                    } else {
                        new AddDummy(CreateNewDummyActivity.this, dummy).execute();
                    }
                }
        }
    }
}
