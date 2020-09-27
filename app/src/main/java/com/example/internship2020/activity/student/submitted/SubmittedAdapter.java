package com.example.internship2020.activity.student.submitted;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.internship2020.R;
import com.example.internship2020.model.Form;

import java.util.List;

public class SubmittedAdapter extends RecyclerView.Adapter<SubmittedAdapter.RecyclerViewAdapterSubmitted> {

    private Context contextSub;
    private List<Form> formsSub;
    private ItemClickListenerSubmitted itemClickListenerSubmitted;

    public SubmittedAdapter(Context context, List<Form> forms, ItemClickListenerSubmitted itemClickListenerSubmitted) {
        this.contextSub = context;
        this.formsSub = forms;
        this.itemClickListenerSubmitted = itemClickListenerSubmitted;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterSubmitted onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(contextSub).inflate(R.layout.item_submitted, parent, false);

        //maybe error here
        return new RecyclerViewAdapterSubmitted(view, itemClickListenerSubmitted);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterSubmitted holder, int position) {
        Form formSub = formsSub.get(position);
        holder.tv_topic.setText(""+ formSub.getEventName());
        holder.tv_fromDt.setText(""+ formSub.getFromDate());
        holder.tv_toDt.setText(""+ formSub.getToDate());
        holder.tv_semSub.setText(""+ formSub.getStdSem());

        if(formSub.getAccepted() == 1){
            holder.card_item_sub.setCardBackgroundColor(Color.parseColor("#3CB371"));
        }else if(formSub.getAccepted() == -1){
            holder.card_item_sub.setCardBackgroundColor(Color.parseColor("#B43757"));
        }
    }

    @Override
    public int getItemCount() {
        return formsSub.size();
    }

    class RecyclerViewAdapterSubmitted extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tv_topic, tv_fromDt, tv_toDt, tv_semSub;
        CardView card_item_sub;
        ItemClickListenerSubmitted itemClickListenerSubmitted;


        RecyclerViewAdapterSubmitted(@NonNull View itemView, @NonNull   ItemClickListenerSubmitted itemClickListenerSubmitted) {
            super(itemView);

            tv_topic = itemView.findViewById(R.id.txt_topic_sub);
            tv_fromDt = itemView.findViewById(R.id.date_from_sub);
            tv_toDt = itemView.findViewById(R.id.date_to_sub);
            tv_semSub = itemView.findViewById(R.id.ans_sem_sub);
            card_item_sub = itemView.findViewById(R.id.submitted_item);

            this.itemClickListenerSubmitted = itemClickListenerSubmitted;
            card_item_sub.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            itemClickListenerSubmitted.onItemClick(v, getAdapterPosition());
        }
    }

    public interface  ItemClickListenerSubmitted{
        void onItemClick(View view, int position);
    }
}
