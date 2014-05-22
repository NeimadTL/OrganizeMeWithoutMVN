package com.talsoft.organizeme.adapters;

import java.util.List;
import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.talsoft.organizeme.R;
import com.talsoft.organizeme.models.Task;


public class TaskListAdapter extends BaseAdapter
{
	
	private Context context;
	private List<Task> tasks;
	private LayoutInflater inflater;
	
	
	public TaskListAdapter(Context context, List<Task> tasks)
	{
		this.context = context;
		this.tasks = tasks;
		this.inflater = LayoutInflater.from(context);
	}
	
	
	@Override
	public int getCount() 
	{
		return tasks.size();
	}

	
	@Override
	public Object getItem(int index) 
	{
		return tasks.get(index);
	}


	@Override
	public long getItemId(int position) 
	{

		return 0;
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		Task task = (Task) getItem(position);
		
		convertView = inflater.inflate(R.layout.list_view_task,null);
		
		TextView goal = (TextView) convertView.findViewById(R.id.goalEditText);
		goal.setText("# " + task.getGoal());
		
		TextView place = (TextView) convertView.findViewById(R.id.placeEditText);
		place.setText(task.getPlace());
		
		TextView beginDate = (TextView) convertView.findViewById(R.id.beginDateEditText);
		beginDate.setText(dateTimeAsString(task.getBeginDate()));
		
		TextView endDate = (TextView) convertView.findViewById(R.id.endDateEditText);
		endDate.setText(dateTimeAsString(task.getEndDate()));
		

		return convertView;
	}
	
	
	private String dateTimeAsString(DateTime dateTime)
	{
		DateTimeFormatter formatter = DateTimeFormat.forPattern("EEEE dd MMMM yyyy à HH:mm");
		formatter.withLocale(Locale.FRENCH);
		return formatter.print(dateTime);	
	}
}
