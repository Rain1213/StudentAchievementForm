package com.example.internship2020.activity.admin.requested;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.internship2020.R;
import com.example.internship2020.R;
import com.example.internship2020.model.Form;

import java.util.List;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.RequestRecyclerViewAdapter> {

    private Context context;
    private List<Form> forms;
    private ItemClickListenerRequested itemClickListenerRequested;

    public RequestAdapter(Context context, List<Form> forms, ItemClickListenerRequested itemClickListenerRequested) {
        this.context = context;
        this.forms = forms;
        this.itemClickListenerRequested = itemClickListenerRequested;
    }

    @NonNull
    @Override
    public RequestRecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_requested,
                parent, false);

        return new RequestRecyclerViewAdapter(view, itemClickListenerRequested);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestRecyclerViewAdapter holder, int position) {
        Form form = forms.get(position);
        holder.tv_id.setText(""+form.getStdId());
        holder.tv_sem.setText(""+form.getStdSem());
        holder.tv_topic.setText(""+form.getEventName());

    }

    @Override
    public int getItemCount() {
        return forms.size();
    }

    class RequestRecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_id, tv_sem, tv_topic;
        CardView card_item;
        ItemClickListenerRequested itemClickListenerRequested;

        RequestRecyclerViewAdapter(@NonNull View itemView, @NonNull ItemClickListenerRequested itemClickListenerRequested) {
            super(itemView);

            tv_id = itemView.findViewById(R.id.txt_id);
            tv_sem = itemView.findViewById(R.id.txt_sem_ans);
            tv_topic= itemView.findViewById(R.id.topic);
            card_item =itemView.findViewById(R.id.requested_item);

            this.itemClickListenerRequested = itemClickListenerRequested;
            card_item.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            itemClickListenerRequested.onItemClick(v, getAdapterPosition());
        }
    }

    public interface ItemClickListenerRequested{
        void onItemClick(View view, int position);
    }
}
