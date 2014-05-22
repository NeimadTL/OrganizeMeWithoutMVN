package com.talsoft.organizeme.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.talsoft.organizeme.R;
import com.talsoft.organizeme.models.Tag;

public class TagListAdapter  extends BaseAdapter
{
	private Context context;
	private List<Tag> tags;
	private LayoutInflater inflater;
	
	
	public TagListAdapter(Context context, List<Tag> tags)
	{
		this.context = context;
		this.tags = tags;
		this.inflater = LayoutInflater.from(context);
	}
	
	
	@Override
	public int getCount() 
	{
		return tags.size();
	}
	
	
	@Override
	public Object getItem(int position) 
	{
		return tags.get(position);
	}
	
	
	@Override
	public long getItemId(int position) 
	{
		return 0;
	}
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		Tag tag = (Tag) getItem(position);
		
		convertView = inflater.inflate(R.layout.list_view_tag,null);
		
		TextView tagName = (TextView) convertView.findViewById(R.id.tagNameTextView);
		tagName.setText(tag.getName());
		
		return convertView;
	}
}
