package com.example.fourtitudetask1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.fourtitudetask1.R;
import com.example.fourtitudetask1.model.Rating;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RatingAdapter extends RecyclerView.Adapter<RatingAdapter.RatingViewHolder> {

    private List<Rating> ratingList;
    private Context context;

    public class RatingViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_source)
        TextView tvSource;
        @BindView(R.id.tv_value)
        TextView tvValue;

        public RatingViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);
        }
    }

    public RatingAdapter(Context context, List<Rating> ratingList) {
        this.context = context;
        this.ratingList = ratingList;
    }

    @Override
    public RatingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rating_item, parent, false);

        return new RatingViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RatingViewHolder holder, int position) {
        if (ratingList != null) {
            Rating rating = ratingList.get(position);

            holder.tvSource.setText(rating.getSource());
            holder.tvValue.setText(rating.getValue());
        }
    }

    @Override
    public int getItemCount() {
        if (ratingList == null) {
            return 0;
        } else {
            return ratingList.size();
        }
    }
}
