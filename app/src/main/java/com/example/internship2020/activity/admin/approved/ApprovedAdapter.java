//refer https://www.youtube.com/watch?v=yNoNwVyw9IY

package com.example.internship2020.activity.admin.approved;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.internship2020.R;
import com.example.internship2020.model.Form;

import java.util.List;

public class ApprovedAdapter extends RecyclerView.Adapter<ApprovedAdapter.MyViewHolder> {

    private List<Form> forms;
    private Context context;
    private OnFormListener formListener;

    public ApprovedAdapter(List<Form> forms, Context context, OnFormListener onFormListener) {
        this.forms = forms;
        this.context = context;
        this.formListener = onFormListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_recycler_layout,parent,false);
        return new MyViewHolder(view, formListener);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.id.setText(""+forms.get(position).getStdId());
        holder.sem.setText(""+forms.get(position).getStdSem());
        holder.topic.setText(""+forms.get(position).getEventType());

    }

    @Override
    public int getItemCount() {
        return forms.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView id,sem,topic;

        OnFormListener mOnFormListener;

        public MyViewHolder(@NonNull View itemView, OnFormListener onFormListener ) {
            super(itemView);
            id = itemView.findViewById(R.id.txt_id1);
            sem = itemView.findViewById(R.id.txt_sem_ans1);
            topic = itemView.findViewById(R.id.topic1);
            mOnFormListener = onFormListener;

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            mOnFormListener.onFormClick(getAdapterPosition());
        }
    }

    public interface OnFormListener{
        void onFormClick(int position);
    }
}
