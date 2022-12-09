package org.example.model;

import java.util.List;
import lombok.Value;

@Value
public class ExItem{
	String text;
	List<TrItem> tr;
}