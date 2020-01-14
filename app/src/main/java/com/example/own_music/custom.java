package com.example.own_music;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class custom extends BaseAdapter {

    private Context context;
    private ArrayList<String> array;
    public  custom(Context context,ArrayList<String> list)
    {
        this.context=context;
        array=list;
    }
    @Override
    public int getCount() {
        return array.size();
    }

    @Override
    public Object getItem(int position) {
        return array.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder view;
        if(convertView==null)
        {
            view=new ViewHolder();
            convertView=View.inflate(context,R.layout.custom_adapter,null);
            view.image=(ImageView)convertView.findViewById(R.id.imagesinger);
            view.singer=(TextView)convertView.findViewById(R.id.namesinger);
            convertView.setTag(view);
        }
        else
            view=(ViewHolder)convertView.getTag();
        view.singer.setText(getItem(position).toString());
        view.image.setImageResource(R.drawable.ic_people_outline_black_24dp);
        return convertView;
    }
    class ViewHolder{
        ImageView image;
        TextView singer;
    }
}
