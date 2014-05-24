package com.talsoft.organizeme.datas;

import java.util.List;

import com.google.common.collect.Lists;
import com.talsoft.organizeme.models.Note;
import com.talsoft.organizeme.models.Tag;
import com.talsoft.organizeme.models.Task;



public class OrganizeMeDataBase 
{
	
	
	private static  OrganizeMeDataBase instance = null;
	private List<Task> tasks;
	private List<Tag> tags;
	private List<Note> notes;
	
	
	private OrganizeMeDataBase() 
	{
		tasks = Lists.newArrayList();
		tags = Lists.newArrayList();
		notes = Lists.newArrayList();
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
	
	
	public static void addTask(Task task)
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
	
	
	public static List<Note> getAllNotesWithTagName(String tagName)
	{
		List<Note> filteredList = Lists.newArrayList();
		
		for(Note current : getInstance().getNotes())
		{
			if(current.getTagName().equalsIgnoreCase(tagName))
			{
				filteredList.add(current);
			}
		}
		
		return filteredList;
	}
	
	
	public static void addNote(Note note)
	{
		getInstance().getNotes().add(note);
	}
	

	private List<Task> getTasks() 
	{
		return tasks;
	}


	private void setTasks(List<Task> tasks) 
	{
		this.tasks = tasks;
	}


	private List<Tag> getTags() 
	{
		return tags;
	}

												
	private void setTags(List<Tag> tags)
	{
		this.tags = tags;
	}


	private List<Note> getNotes() 
	{
		return notes;
	}


	private void setNotes(List<Note> notes) 
	{
		this.notes = notes;
	}
	
	
}
