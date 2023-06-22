package com.example.splash1;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class StringAdapter extends RecyclerView.Adapter<StringAdapter.StringViewHolder> {
    private List<Unit> unitList;
    private SharedPreferences sharedPreferences;

    public StringAdapter(List<Unit> unitList, SharedPreferences sharedPreferences) {
        this.unitList = unitList;
        this.sharedPreferences = sharedPreferences;
    }

    @NonNull
    @Override
    public StringViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_design, parent, false);
        return new StringViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StringViewHolder holder, int position) {
        Unit unit = unitList.get(position);
        holder.bind(unit, position);
    }

    @Override
    public int getItemCount() {
        return unitList.size();
    }

    public class StringViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private CardView cardView;
        private ViewFlipper viewFlipper;
        private TextView textView;
        private TextView backTextView;
        private CheckBox checkbox;

        private boolean isFront = true;

        public StringViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            viewFlipper = itemView.findViewById(R.id.viewFlipper);
            textView = itemView.findViewById(R.id.textView);
            backTextView = itemView.findViewById(R.id.backTextView);
            checkbox = itemView.findViewById(R.id.checkbox);

            cardView.setOnClickListener(this);
        }

        public void bind(Unit unit, int position) {
            textView.setText(unit.getTitle());
            backTextView.setText(unit.getContent());
            boolean isComplete = sharedPreferences.getBoolean("complete_" + position, false);
            checkbox.setChecked(isComplete);
        }

        @Override
        public void onClick(View v) {
            flipCard();
        }

        private void flipCard() {
            if (isFront) {
                ObjectAnimator flipOutAnimator = ObjectAnimator.ofFloat(viewFlipper, "rotationY", 0f, 180f);
                flipOutAnimator.setDuration(300);
                flipOutAnimator.setInterpolator(new AccelerateInterpolator());
                flipOutAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        viewFlipper.showNext();
                        ObjectAnimator flipInAnimator = ObjectAnimator.ofFloat(viewFlipper, "rotationY", -180f, 0f);
                        flipInAnimator.setDuration(300);
                        flipInAnimator.setInterpolator(new DecelerateInterpolator());
                        flipInAnimator.start();
                    }
                });
                flipOutAnimator.start();
            } else {
                ObjectAnimator flipOutAnimator = ObjectAnimator.ofFloat(viewFlipper, "rotationY", 0f, -180f);
                flipOutAnimator.setDuration(300);
                flipOutAnimator.setInterpolator(new AccelerateInterpolator());
                flipOutAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        viewFlipper.showNext();
                        ObjectAnimator flipInAnimator = ObjectAnimator.ofFloat(viewFlipper, "rotationY", 180f, 0f);
                        flipInAnimator.setDuration(300);
                        flipInAnimator.setInterpolator(new DecelerateInterpolator());
                        flipInAnimator.start();
                    }
                });
                flipOutAnimator.start();
            }

            isFront = !isFront;
            // Save checkbox state to SharedPreferences
            int position = getAdapterPosition();
            boolean isComplete = checkbox.isChecked();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("complete_" + position, isComplete);
            editor.apply();
        }

    }
}