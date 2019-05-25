package com.contest.schoolsuggestions.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.contest.schoolsuggestions.R;

import java.util.ArrayList;
import java.util.List;

public class PostListViewAdapter extends BaseAdapter {

    private List<PostInfoTO> postInfoItemList = new ArrayList<>();

    public PostListViewAdapter(List<PostInfoTO> postInfoItemList) {
        this.postInfoItemList = postInfoItemList;
    }

    @Override
    public int getCount() {
        return postInfoItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return postInfoItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.post_listview_item, parent, false);
        }

        TextView titleTextView = convertView.findViewById(R.id.titleText_postList);
        TextView agreementTextView = convertView.findViewById(R.id.agreementText_postList);
        TextView feedbackTextView = convertView.findViewById(R.id.feedbackText_postList);

        PostInfoTO postInfo = postInfoItemList.get(position);
        titleTextView.setText(postInfo.getTitle());
        agreementTextView.setText(String.valueOf(postInfo.getAgree()) + ":" + String.valueOf(postInfo.getDisagree()));
        if (postInfo.getFeedback() != null && !postInfo.getFeedback().equals("")) {
            feedbackTextView.setText("O");
        } else {
            feedbackTextView.setText("X");
        }
        return convertView;
    }
}
