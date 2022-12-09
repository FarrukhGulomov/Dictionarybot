package org.example.model;

import java.util.List;

public class TrItem{
	private String pos;
	private List<MeanItem> mean;
	private String text;
	private int fr;
	private List<SynItem> syn;

	public String getPos(){
		return pos;
	}

	public List<MeanItem> getMean(){
		return mean;
	}

	public String getText(){
		return text;
	}

	public int getFr(){
		return fr;
	}

	public List<SynItem> getSyn(){
		return syn;
	}
}