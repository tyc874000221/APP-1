package com.tcode.demo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.widget.TextView;

import com.tcode.demo.R;
import com.tcode.demo.bean.ListData;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<ViewHolder> {

	private Context mContext;
	private List<ListData> list;


	public MainAdapter (Context mContext,List<ListData> list){
		this.mContext = mContext;
		this.list = list;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

		MViewHolder viewHolder = new MViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_main_rv, parent, false));
		return  viewHolder;

	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {

		if(holder instanceof MViewHolder){
			((MViewHolder) holder).tv_dataName.setText(list.get(position).getDataName());
			((MViewHolder) holder).tv_dataNum.setText(String.valueOf(list.get(position).getDataNum()));
		}

	}

	@Override
	public int getItemCount() {
		return list.size() == 0 ? 0 : list.size();
	}

	class MViewHolder extends ViewHolder{

		TextView tv_dataName,tv_dataNum;

		public MViewHolder(View itemView) {
			super(itemView);
			tv_dataName = (TextView)itemView.findViewById(R.id.tv_dataName);
			tv_dataNum = (TextView)itemView.findViewById(R.id.tv_dataNum);
		}
	}

}
