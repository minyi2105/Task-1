package com.example.fourtitudetask1.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.fourtitudetask1.R;
import com.example.fourtitudetask1.model.Dummy;
import com.example.fourtitudetask1.util.AsyncResponse;
import com.example.fourtitudetask1.util.DummyDbUtil;
import com.example.fourtitudetask1.util.EditTextLinesLimiter;
import com.example.fourtitudetask1.util.ValidateUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateNewDummyActivity extends AppCompatActivity implements View.OnClickListener, AsyncResponse {

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

        etSubtitle.addTextChangedListener(new EditTextLinesLimiter(etSubtitle, 3));

        //if it gets through here which means the user clicked the update button
        if (getIntent().hasExtra("id")) {
            id = getIntent().getIntExtra("id", 0);

            DummyDbUtil.GetDummy asyncTask = new DummyDbUtil.GetDummy(CreateNewDummyActivity.this, id);
            //this to set delegate/listener back to this class
            asyncTask.delegate = this;
            asyncTask.execute();

            getUrlAndPreviewImage();
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
    protected void onResume() {
        super.onResume();
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
        String imageUrl = etImageUrl.getText().toString();

        if (!TextUtils.isEmpty(imageUrl)) {
            Glide
                    .with(CreateNewDummyActivity.this)
                    .load(imageUrl)
                    .placeholder(ValidateUtil.getCircularProgressDrawable(CreateNewDummyActivity.this))
                    .error(R.drawable.ic_broken_image)
                    .into(ivImagePreview);
        }
    }

    @Override
    public void processFinish(Dummy dummy) {
        etTitle.setText(dummy.getTitle());
        etSubtitle.setText(dummy.getSubtitle());
        etDescription.setText(dummy.getDescription());
        etImageUrl.setText(dummy.getImageUrl());
    }

    @Override
    public void processFinish(List<Dummy> dummies) {

    }

    @Override
    public void populatedFinish() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save:

                Map<View, String> map = new HashMap<>();
                map.put(etTitle, getResources().getString(R.string.title_is_required));
                map.put(etSubtitle, getResources().getString(R.string.subtitle_is_required));
                map.put(etDescription, getResources().getString(R.string.description_is_required));
                map.put(etImageUrl, getResources().getString(R.string.image_url_is_required));

                String title = ValidateUtil.getOnlyText(etTitle);
                String subtitle = ValidateUtil.getOnlyText(etSubtitle);
                String description = ValidateUtil.getOnlyText(etDescription);
                String imageUrl = ValidateUtil.getOnlyText(etImageUrl);

                if (!ValidateUtil.isInputEmpty(view, map)) {
                    Dummy dummy = new Dummy(title, subtitle, description, imageUrl);

                    //if it gets through here which means the user clicked the update button
                    if (getIntent().hasExtra("id")) {
                        dummy.setId(id);
                        new DummyDbUtil.UpdateDummy(CreateNewDummyActivity.this, dummy).execute();
                    } else {
                        new DummyDbUtil.AddDummy(CreateNewDummyActivity.this, dummy).execute();
                    }
                }
        }
    }
}
