package com.pamirs.util;

import java.util.Comparator;

import com.pamirs.model.DocumentsInfo;

public class SortyByTitle implements Comparator<DocumentsInfo>{

	@Override
	public int compare(DocumentsInfo o1, DocumentsInfo o2) {
		// TODO Auto-generated method stub
		 return o1.getTitle().compareTo(o2.getTitle());
	}

}
