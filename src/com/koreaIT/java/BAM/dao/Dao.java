package com.koreaIT.java.BAM.dao;

public abstract class Dao {
	protected int lastId;

	public int getLastId(){
		return lastId + 1;
	}
}
