package com.platinumstudio.databinding10;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

//import com.jakewharton.picasso.OkHttp3Downloader;
import com.platinumstudio.databinding10.databinding.ListItemBinding;
//import com.platinumstudio.restapiwithoutretrofit.Photo;
//import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private List<User> users;
    private Context context;

    public CustomAdapter(List<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ListItemBinding listItemBinding = DataBindingUtil.inflate(LayoutInflater
                .from(viewGroup.getContext()), R.layout.list_item, viewGroup, false);

        MyViewHolder myViewHolder = new MyViewHolder(listItemBinding);
        return myViewHolder;

//        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        // bind the views
        // Get current user
        User user = users.get(position);
        myViewHolder.listItemBinding.setUser(user);

//        myViewHolder.titleTextView.setText(users.get(position).getTitle());
//
//        Picasso.Builder builder = new Picasso.Builder(context);
//        builder.downloader(new OkHttp3Downloader(context));
//        builder.build().load(users.get(position).getThumbnailUrl())
//                .placeholder((R.drawable.ic_launcher_background))
//                .error(R.drawable.ic_launcher_background)
//                .into(myViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    /**
     * ViewHolder class that represents each row of data in the RecyclerView.
     */
    class MyViewHolder extends RecyclerView.ViewHolder{

//        private TextView titleTextView;
//        private ImageView imageView;

        ListItemBinding listItemBinding;

        public MyViewHolder(@NonNull ListItemBinding listItem) {
            super(listItem.getRoot());

            listItemBinding = listItem;

//            titleTextView = listItem.findViewById(R.id.title);
//            imageView = listItem.findViewById(R.id.coverImage);
        }
    }

}
