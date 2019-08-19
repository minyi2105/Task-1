package com.example.fourtitudetask1.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fourtitudetask1.R;
import com.example.fourtitudetask1.activities.DummyDetailActivity;
import com.example.fourtitudetask1.model.Dummy;
import com.example.fourtitudetask1.util.ValidateUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DummyAdapter extends RecyclerView.Adapter<DummyAdapter.DummyViewHolder> {

    private List<Dummy> dummyList;
    private Context context;
    private RecyclerView recyclerView;
    private CardView cardView;

    public class DummyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_subtitle)
        TextView tvSubtitle;
        @BindView(R.id.tv_description)
        TextView tvDescription;
        @BindView(R.id.cv_dummy)
        CardView cvDummy;
        @BindView(R.id.iv_dummy)
        ImageView ivDummy;

        public DummyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);
        }
    }

    public DummyAdapter(Context context, RecyclerView recyclerView, CardView cardView, List dummyList) {
        this.context = context;
        this.dummyList = dummyList;
        this.recyclerView = recyclerView;
        this.cardView = cardView;
    }

    @Override
    public DummyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dummy_item, parent, false);

        return new DummyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DummyViewHolder holder, int position) {
        if (dummyList != null) {
            cardView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);

            Dummy dummy = dummyList.get(position);

            holder.tvTitle.setText(dummy.getTitle());
            holder.tvSubtitle.setText(dummy.getSubtitle());
            holder.tvDescription.setText(dummy.getDescription());

            Glide
                    .with(context)
                    .load(dummy.getImageUrl())
                    .placeholder(ValidateUtil.getCircularProgressDrawable(context))
                    .error(R.drawable.ic_broken_image)
                    .into(holder.ivDummy);

            holder.cvDummy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DummyDetailActivity.class);
                    intent.putExtra("id", dummyList.get(position).getId());
                    context.startActivity(intent);
                }
            });
        } else {
            cardView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return dummyList.size();
    }
}