package com.talsoft.organizeme.datas;

import java.util.List;

import com.google.common.collect.Lists;
import com.talsoft.organizeme.models.Tag;
import com.talsoft.organizeme.models.Task;



public class OrganizeMeDataBase 
{
	private static  OrganizeMeDataBase instance = null;
	private List<Task> tasks;
	private List<Tag> tags;
	//private Map<String,List<Note>> taggedNotes;

	
	private OrganizeMeDataBase() 
	{
		tasks = Lists.newArrayList();
		tags = Lists.newArrayList();
		//taggedNotes = Maps.newHashMap();
	}
	
	 
	private static OrganizeMeDataBase getInstance()
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
	
	
	public static List<Task> getAllTasks()
	{
		return getInstance().getTasks();
	}
	
	
	public static void  addTask(Task task)
	{
		getInstance().getTasks().add(task);
	}
	
	
	public static List<Tag> getAllTags()
	{
		return getInstance().getTags();
	}
	
	
	public static void addTag(Tag tag)
	{
		getInstance().getTags().add(tag);
	}


	private List<Task> getTasks() 
	{
		return tasks;
	}


	private void setTasks(List<Task> tasks) 
	{
		this.tasks = tasks;
	}


	public List<Tag> getTags() 
	{
		return tags;
	}


	public void setTags(List<Tag> tags)
	{
		this.tags = tags;
	}
}
