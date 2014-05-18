package com.talsoft.organizeme.datas;

import java.util.List;

import com.google.common.collect.Lists;
import com.talsoft.organizeme.models.Task;



public class OrganizeMeDataBase 
{
	private static  OrganizeMeDataBase instance = null;
	private List<Task> tasks;

	
	private OrganizeMeDataBase() 
	{
		tasks = Lists.newArrayList();
	}
	
	 
	public static OrganizeMeDataBase getInstance()
	{	
		if (instance == null)
		{
			synchronized (OrganizeMeDataBase.class) 
			{
				instance = new OrganizeMeDataBase();
			}
		}
		
		return instance;
	}


	public List<Task> getTasks() 
	{
		return tasks;
	}


	public void setTasks(List<Task> tasks) 
	{
		this.tasks = tasks;
	}
	
}
