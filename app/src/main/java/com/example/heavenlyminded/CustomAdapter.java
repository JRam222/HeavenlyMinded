package com.example.heavenlyminded;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private final Context context;
    private final ArrayList event_id;
    private final ArrayList event_type;

    CustomAdapter(Context context, ArrayList event_id, ArrayList event_type) {
        this.context = context;
        this.event_id = event_id;
        this.event_type = event_type;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.eventIdText.setText(String.valueOf(event_id.get(position)));
        holder.eventTypeText.setText(String.valueOf(event_type.get(position)));
    }

    @Override
    public int getItemCount() {
        return event_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView eventIdText, eventTypeText;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            eventIdText = itemView.findViewById(R.id.event_id_text);
            eventTypeText = itemView.findViewById(R.id.event_type_text);
        }
    }
}
