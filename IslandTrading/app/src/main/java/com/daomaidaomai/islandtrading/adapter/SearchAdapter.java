package com.daomaidaomai.islandtrading.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daomaidaomai.islandtrading.entity.Product;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.daomaidaomai.islandtrading.R;
import com.daomaidaomai.islandtrading.model.Bean;
import com.daomaidaomai.islandtrading.util.CommonAdapter;
import com.daomaidaomai.islandtrading.util.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import com.daomaidaomai.islandtrading.ui.ImgLO;

/**
 * Created by yetwish on 2015-05-11
 */
public class SearchAdapter extends CommonAdapter<Bean>{
    private Context mContext;//上下文环境，相当于传this
    //一个由数组实现的链表，相当于容器，用来放有多少个Item项，动态添加，长度不是固定的
    private List<Bean> mData = new ArrayList<Bean>();
    int layoutId;

    public SearchAdapter(Context context, List<Bean> data, int layoutId) {
        super(context, data, layoutId);
        mContext=context;
        mData=data;
    }

    @Override
    public void convert(ViewHolder holder, int position) {

        holder.setText(R.id.item_search_tv_title,mData.get(position).getTitle())
                .setText(R.id.item_search_tv_content,mData.get(position).getContent())
                .setText(R.id.item_search_tv_comments,mData.get(position).getComments());
//        ImageView iv = getView( position,R.id.item_search_iv_icon,holder);
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_bean_list, null);
        ImageView DetialImg = (ImageView) view.findViewById(R.id.item_search_iv_icon);
        //iv.setImageResource(resId);
        ImgLO.initImageLoader(mContext);
        ImageLoader.getInstance().displayImage(mData.get(position).getIconId(),DetialImg);
    }

//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        if (null == convertView) {//判断是不是空控件
//            //获取布局，将xml文件转换成view，即把xml文件当成view来使用
//            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_bean_list, null);
//        }
//        ImageView Image = (ImageView) convertView.findViewById(R.id.item_search_iv_icon);
//        com.daomaidaomai.islandtrading.util.ImgLO.initImageLoader(mContext);
//        ImageLoader.getInstance().displayImage(mData.get(position).getIconId(), Image);
//        TextView Title = (TextView) convertView.findViewById(R.id.item_search_tv_title);
//        Title.setText(mData.get(position).getTitle());
//        TextView Price = (TextView) convertView.findViewById(R.id.item_search_tv_comments);
//        Price.setText(mData.get(position).getComments() + "");
//        TextView Content = (TextView) convertView.findViewById(R.id.home_content);
//        Content.setText(mData.get(position).getContent());
//        return convertView;
//    }
}
