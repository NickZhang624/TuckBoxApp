package com.example.tuckboxapp;

import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tuckboxapp.DataModelPackage.User;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {
    List<User> data =null;

    public MyAdapter(){
        new LoadUserInfo().execute();
    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.user_cardview, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.user =data.get(position);
        if(holder.user.getTitle().equals("Mr"))
            holder.imageView.setImageResource(R.drawable.male);
        else
            holder.imageView.setImageResource(R.drawable.female);
        holder.etTitle.setText(holder.user.getTitle() + " ");
        holder.etName.setText(holder.user.getFirstName() + " " + holder.user.getLastName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private class LoadUserInfo extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            data = MainActivity.userDao.searchUserByUserName(AppServices.user.getUserName());
            return null;
        }
    }
}
