package com.example.plzfind;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

// CustomAdapter.java
// CustomAdapter.java
public class CustomAdapter extends ArrayAdapter<PostItem> {

    private List<PostItem> dataList;

    public void setData(List<PostItem> newData) {
        dataList = newData;
        notifyDataSetChanged();
    }

    public CustomAdapter(Context context, List<PostItem> dataList) {
        super(context, 0, dataList);
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
            Log.d("CustomAdapter", "getView: Creating new view");
        }

        PostItem postItem = getItem(position);
        if (postItem != null) {
            TextView titleTextView = convertView.findViewById(R.id.title);
            TextView bContentTextView = convertView.findViewById(R.id.b_content);
            TextView bRegdateTextView = convertView.findViewById(R.id.b_regdate);
            TextView bPlaceTextView = convertView.findViewById(R.id.b_place);
            ImageView imageView = convertView.findViewById(R.id.imageView);

            titleTextView.setText(postItem.getTitle());
            bContentTextView.setText(postItem.getContent());
            bRegdateTextView.setText(postItem.getRegDate());
            bPlaceTextView.setText(postItem.getPlace());

            // 이미지 리소스 가져오기
            int imageResource = getContext().getResources().getIdentifier(postItem.getImageName(), "drawable", getContext().getPackageName());
            if (imageResource != 0) {
                imageView.setImageResource(imageResource);
            } else {
                // 이미지 리소스를 찾을 수 없을 때 기본 이미지 설정
                imageView.setImageResource(R.drawable.menu);
            }
        }

        return convertView;
    }
}
