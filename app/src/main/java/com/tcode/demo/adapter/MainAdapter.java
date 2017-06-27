package com.tcode.demo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.widget.TextView;

import com.tcode.demo.R;
import com.tcode.demo.bean.ListData;
import java.util.List;

/**
 * 1.因为要添加底部的加载状态，所以返回的数据要多处一列放置底部的布局
 * 2.因为底部最一行的布局和别的正常数据的布局不一样，所以要根据返回不同的视图类型给予不同的布局文件
 *  （如果不需要上拉加载更多，则不需要重写此方法）
 * 3.如果数据没有10条（这里的数据每页显示十条，根据每页显示的条数来给定具体的数值）则最后一行不显示加载更多的进度条
 */
public class MainAdapter extends RecyclerView.Adapter<ViewHolder> {

	private int TYPE_ITEM = 1;//普通数据列
	private int TYPE_FOOT = 0;//底部加载列

	private Context mContext;
	private List<ListData> list;//数据列表


	public MainAdapter (Context mContext,List<ListData> list){
		this.mContext = mContext;
		this.list = list;
	}



	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {



		return null;
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {





	}

	@Override
	public int getItemCount() {
		return list.size() == 0 ? 0 : list.size()+1;//说明1
	}

	//说明2
	@Override
	public int getItemViewType(int position) {
		//说明3
		if(getItemCount() < 10){
			return TYPE_ITEM;
		}

		if (position + 1 == getItemCount()){
			return TYPE_FOOT;
		}else{
			return TYPE_ITEM;
		}

	}


	class MViewHolder extends ViewHolder{

		TextView tv_dataName;

		public MViewHolder(View itemView) {
			super(itemView);

//			tv_dataName = (TextView)itemView.findViewById(R.id.tv_dataName);
		}



	}






}
