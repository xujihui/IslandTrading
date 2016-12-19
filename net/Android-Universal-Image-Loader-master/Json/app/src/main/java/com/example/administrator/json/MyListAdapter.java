package com.example.administrator.json;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/11/19.
 */
public class MyListAdapter extends BaseAdapter{
    private Context context;//上下文环境
    private List<ItemDetail> ldetail= new ArrayList<>();
    public MyListAdapter(Context c, List<ItemDetail> ls) {
        context = c;//下面的layoutInflater类构造的时候用到了啊
        ldetail = ls;
    }

    @Override
    public int getCount() {//adapter提供多少条数据，ArrayList有多少条及有多少条
        return ldetail.size();
    }

    @Override
    public Object getItem(int i) {//第几项
        return ldetail.get(i);
    }

    @Override
    public long getItemId(int i) {//唯一标识某一项
        return ldetail.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {//每一项的视图的具体显示，PPT有代码

        view = LayoutInflater.from(context).inflate(R.layout.item_detial, null);
        //绘制视图中的每一项
        TextView DetialName =(TextView) view.findViewById(R.id.DetialName);
        DetialName.setText(ldetail.get(i).getmName());
        TextView DetialContent =(TextView) view.findViewById(R.id.DetialContent);
        DetialContent.setText(ldetail.get(i).getmContent());
        TextView mPlace =(TextView) view.findViewById(R.id.DetialPlace);
        mPlace.setText(ldetail.get(i).getmPlace());
        TextView mOgnizition =(TextView) view.findViewById(R.id.zuzhizhe);
        mOgnizition.setText(ldetail.get(i).getmOgnizition());
        TextView mTime=(TextView)view.findViewById(R.id.DetialTime);
        mTime.setText( ldetail.get(i).getTime());
        ImageView mImageview=(ImageView)view.findViewById(R.id.list_img);
        ImageLoader.getInstance().displayImage(ldetail.get(i).getmImage(),mImageview);
        return view;
    }
}
