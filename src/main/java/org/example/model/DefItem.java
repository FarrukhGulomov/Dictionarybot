package org.example.model;

import java.util.List;

public class DefItem{
	private String pos;
	private String text;
	private List<TrItem> tr;

	public String getPos(){
		return pos;
	}

	public String getText(){
		return text;
	}

	public List<TrItem> getTr(){
		return tr;
	}
}