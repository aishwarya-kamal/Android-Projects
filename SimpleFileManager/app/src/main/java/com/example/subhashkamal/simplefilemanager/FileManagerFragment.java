package com.example.subhashkamal.simplefilemanager;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FileManagerFragment extends Fragment {

    private static final String TAG = FileManagerFragment.class.getSimpleName();

    public static final int REQUEST_CODE_OPEN_DIRECTORY = 1;

    Uri mCurrentDirectoryUri;
    RecyclerView mRecyclerView;
    FileManagerAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    /**
     * Use this factory method to create a new instance of
     * this fragment_file_manager using the provided parameters.
     *
     * @return A new instance of fragment_file_manager {@link FileManagerFragment}.
     */
    public static FileManagerFragment newInstance() {
        FileManagerFragment fragment = new FileManagerFragment();
        return fragment;
    }

    public FileManagerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment_file_manager
        return inflater.inflate(R.layout.fragment_file_manager, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
        startActivityForResult(intent, REQUEST_CODE_OPEN_DIRECTORY);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.directory_recyclerView);
        mLayoutManager = mRecyclerView.getLayoutManager();
        mRecyclerView.scrollToPosition(0);
        mAdapter = new FileManagerAdapter(new ArrayList<FileManager>());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ( requestCode == REQUEST_CODE_OPEN_DIRECTORY && resultCode == Activity.RESULT_OK) {
            Log.d(TAG, String.format("Open Directory result Uri : %s", data.getData()));
            updateDirectoryEntries(data.getData());
            mAdapter.notifyDataSetChanged();
        }
    }

    /**
     * Updates the current directory of the uri passed as an argument and its children directories.
     * And updates the {@link #mRecyclerView} depending on the contents of the children.
     *
     * @param uri The uri of the current directory.
     */
    //VisibileForTesting
    void updateDirectoryEntries(Uri uri) {
        ContentResolver contentResolver = getActivity().getContentResolver();
        Uri docUri = DocumentsContract.buildDocumentUriUsingTree(uri,
                DocumentsContract.getTreeDocumentId(uri));
        Uri childrenUri = DocumentsContract.buildChildDocumentsUriUsingTree(uri,
                DocumentsContract.getTreeDocumentId(uri));

        Cursor docCursor = contentResolver.query(docUri, new String[]{
                DocumentsContract.Document.COLUMN_DISPLAY_NAME, DocumentsContract.Document.COLUMN_MIME_TYPE}, null, null, null);
        try {
            while (docCursor.moveToNext()) {
                Log.d(TAG, "found doc =" + docCursor.getString(0) + ", mime=" + docCursor
                        .getString(1));
                mCurrentDirectoryUri = uri;
                //mCurrentDirectoryTextView.setText(docCursor.getString(0));
            }
        } finally {
            closeQuietly(docCursor);
        }

        Cursor childCursor = contentResolver.query(childrenUri, new String[]{
                DocumentsContract.Document.COLUMN_DISPLAY_NAME, DocumentsContract.Document.COLUMN_MIME_TYPE}, null, null, null);
        try {
            List<FileManager> fileManagers = new ArrayList<>();
            while (childCursor.moveToNext()) {
                Log.d(TAG, "found child=" + childCursor.getString(0) + ", mime=" + childCursor
                        .getString(1));
                FileManager entry = new FileManager();
                entry.fileName = childCursor.getString(0);
                entry.mimeType = childCursor.getString(1);
                fileManagers.add(entry);
            }
            mAdapter.setmFileManager(fileManagers);
            mAdapter.notifyDataSetChanged();
        } finally {
            closeQuietly(childCursor);
        }
    }

    public void closeQuietly(AutoCloseable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException rethrown) {
                throw rethrown;
            } catch (Exception ignored) {
            }
        }
    }
}
