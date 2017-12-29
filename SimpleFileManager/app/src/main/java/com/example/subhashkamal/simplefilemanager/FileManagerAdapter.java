package com.example.subhashkamal.simplefilemanager;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class FileManagerAdapter extends RecyclerView.Adapter<FileManagerAdapter.customViewHolder> {


    // constant value of Mime type document of that is a directory
    static final String DIRECTORY_MIME_TYPE = "vnd.android.document/directory"; // MIME_TYPE_DIR
    private List<FileManager> mFileManager;



    public static class customViewHolder extends RecyclerView.ViewHolder {

        private final TextView mFileName;
        private final TextView mMimeType;
        private final ImageView mImage;

        public customViewHolder(View view) {
            super(view);
            mFileName = (TextView) view.findViewById(R.id.file_title_textView);
            mMimeType = (TextView) view.findViewById(R.id.mime_type_textView);
            mImage = (ImageView) view.findViewById(R.id.file_imageView);
        }

        public TextView getmFileName() {
            return mFileName;
        }

        public TextView getmMimeType() {
            return mMimeType;
        }

        public ImageView getmImage() {
            return mImage;
        }

    }


    /**
     * Initialize the directory entries of the Adapter.
     *
     * @param fileManagers an array of {@link FileManager}.
     */
    public FileManagerAdapter(List<FileManager> fileManagers) {
        mFileManager = fileManagers;
    }


    @Override
    public customViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // this will inflate the ViewHolder
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_layout, parent, false);
        return new customViewHolder(view);
    }

    @Override
    public void onBindViewHolder(customViewHolder holder, int position) {
        holder.getmFileName().setText(mFileManager.get(position).fileName);
        holder.getmFileName().setText(mFileManager.get(position).mimeType);

        if(DIRECTORY_MIME_TYPE.equals(mFileManager.get(position).mimeType)) {
            holder.getmImage().setImageResource(R.drawable.folder_icon);
        } else {
            holder.getmImage().setImageResource(R.drawable.file_icon);
        }

    }

    @Override
    public int getItemCount() {
        return mFileManager.size();
    }

    public void setmFileManager(List<FileManager> fileManagers) {
        mFileManager = fileManagers;
    }
}
