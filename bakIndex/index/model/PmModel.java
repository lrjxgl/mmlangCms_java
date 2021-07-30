package com.deitui.morelang.index.model;
import com.model.Model;
public class PmModel extends Model {
	public PmModel() {
		this.table="pm";
		this.preTable=this.table_pre+this.table;
	}
}
