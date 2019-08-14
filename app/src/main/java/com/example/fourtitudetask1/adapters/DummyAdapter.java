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
import com.example.fourtitudetask1.model.Dummy;
import com.example.fourtitudetask1.activities.DummyDetailActivity;

import java.util.List;

public class DummyAdapter extends RecyclerView.Adapter<DummyAdapter.DummyViewHolder> {

    private List<Dummy> dummyList;
    private Context context;

    public class DummyViewHolder extends RecyclerView.ViewHolder {
        private TextView title, subtitle, description;
        private CardView cardView;
        private ImageView image;

        public DummyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.tv_title);
            subtitle = view.findViewById(R.id.tv_subtitle);
            description = view.findViewById(R.id.tv_description);
            cardView = view.findViewById(R.id.cv_dummy);
            image = view.findViewById(R.id.iv_dummy);
        }
    }

    public DummyAdapter(Context context, List<Dummy> dummyList) {
        this.context = context;
        this.dummyList = dummyList;
    }

    @Override
    public DummyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dummy_item, parent, false);

        return new DummyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DummyViewHolder holder, int position) {
        Dummy dummy = dummyList.get(position);
        holder.title.setText(dummy.getTitle());
        holder.subtitle.setText(dummy.getSubtitle());
        holder.description.setText(dummy.getDescription());

        Glide
                .with(context)
                .load(dummy.getImageUrl())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.image);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO figure how to put intent with starter
//                DummyDetailActivity.start(context);

                Intent intent = new Intent(context, DummyDetailActivity.class);
                intent.putExtra("dummy", new Dummy(dummy.getTitle(), dummy.getSubtitle(), dummy.getDescription(), dummy.getImageUrl()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dummyList.size();
    }
}