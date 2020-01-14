package com.example.own_music;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class list_statistical extends BaseAdapter {
    private Context context;
    private ArrayList<String> array,num;
    private String[] number;
    public list_statistical(Context context,ArrayList<String> list, String[] number)
    {
        this.context=context;
        this.array=list;
        this.number=number;
        num=new ArrayList<>();
        for (int i=0;i<number.length;i++)
            num.add(number[i]);
    }
    @Override
    public int getCount() {
        return array.size();
    }

    @Override
    public Object getItem(int position) {
        return num.get(position);
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
            convertView=View.inflate(context,R.layout.listds,null);
            view.number=(TextView) convertView.findViewById(R.id.txtnumber);
            view.singer=(TextView)convertView.findViewById(R.id.txtnamesong);
            convertView.setTag(view);
        }
        else
            view=(ViewHolder)convertView.getTag();
        view.singer.setText(array.get(position));
        view.number.setText(getItem(position).toString());
        return convertView;
    }
    class ViewHolder{

        TextView singer,number;
    }
}
