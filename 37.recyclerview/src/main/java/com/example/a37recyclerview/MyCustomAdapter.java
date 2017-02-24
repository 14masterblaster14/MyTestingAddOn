package com.example.a37recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by comp on 2/23/2017.
 */

public class MyCustomAdapter extends RecyclerView.Adapter<MyCustomAdapter.MyViewHolder> {

    private List<MyData> objectList;
    private LayoutInflater layoutInflater;

    public MyCustomAdapter(Context context, List<MyData> objectList) {

        layoutInflater = LayoutInflater.from(context);
        this.objectList = objectList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView = layoutInflater.inflate(R.layout.list_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(rootView);
        // return null;
        return myViewHolder;
    }

    @Override
    public int getItemCount() {
        // return 0;
        return objectList.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MyData currentObject = objectList.get(position);
        holder.setData(currentObject, position);
        holder.setListeners();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView imgTitle;
        private ImageView imgThumbView, imgCopy, imgeDelete;
        private int position;
        private MyData currentObject;

        public MyViewHolder(View itemView) {
            super(itemView);

            imgThumbView = (ImageView) itemView.findViewById(R.id.ImgView_Thumb);
            imgTitle = (TextView) itemView.findViewById(R.id.TxtView_Title);
            imgCopy = (ImageView) itemView.findViewById(R.id.ImgView_Copy);
            imgeDelete = (ImageView) itemView.findViewById(R.id.ImgView_Delete);
        }

        public void setData(MyData currentObject, int position) {

            this.imgTitle.setText(currentObject.getTitle());
            this.imgThumbView.setImageResource(currentObject.getImageID());
            this.position = position;
            this.currentObject = currentObject;
        }

        public void setListeners() {

            imgeDelete.setOnClickListener(MyViewHolder.this::click);
            imgCopy.setOnClickListener(MyViewHolder.this::click);
            imgThumbView.setOnClickListener(MyViewHolder.this::click);

        }

        private void click(View view) {

            switch (view.getId()) {
                case R.id.ImgView_Delete:
                    removeItem(position);
                    break;

                case R.id.ImgView_Copy:
                    addItem(position, currentObject);
                    break;

            }
        }

        private void removeItem(int position) {
            objectList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, objectList.size());
            // notifyDataSetChanged();
        }

        private void addItem(int position, MyData currentObject) {
            objectList.add(position, currentObject);
            notifyItemInserted(position);
            notifyItemRangeChanged(position, objectList.size());
            // notifyDataSetChanged();
        }
    }
}
