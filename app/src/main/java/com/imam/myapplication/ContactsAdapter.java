package com.imam.myapplication;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactViewHolder> {

    private Context context;
    private ArrayList ids, names, phoneNumbers;

    public ContactsAdapter (Context context, ArrayList ids,ArrayList names, ArrayList phoneNumbers) {
        this.context = context;
        this.ids = ids;
        this.names = names;
        this.phoneNumbers = phoneNumbers;

    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        holder.fullNameTextView.setText(String.valueOf(names.get(position)));
        holder.phoneNumberTextView.setText(String.valueOf(phoneNumbers.get(position)));
        holder.linearLayout.setOnClickListener(view -> {
            Intent intent = new Intent(context, UpdateActivity.class);
            intent.putExtra("ID", String.valueOf(ids.get(position)));
            intent.putExtra("NAME", String.valueOf(names.get(position)));
            intent.putExtra("PHONE_NUMBER", String.valueOf(phoneNumbers.get(position)));
            context.startActivity(intent);

        });

    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout linearLayout;
        private TextView fullNameTextView;
        private TextView phoneNumberTextView;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.contactItemLayout);
            fullNameTextView = itemView.findViewById(R.id.fullNameTV);
            phoneNumberTextView = itemView.findViewById(R.id.phoneNumberTV);

        }
    }
}