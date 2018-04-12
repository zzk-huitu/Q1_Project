package com.yc.q1.pojo.base.pt;


import java.util.List;

import com.yc.q1.core.util.TagLenVal;

/**
 * POJO类
 * @author  zenglj 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
public class TLVModel {
	 private List<TagLenVal> tlvs;
	 private String id;
	 private String notes;
	public List<TagLenVal> getTlvs() {
		return tlvs;
	}

	public void setTlvs(List<TagLenVal> tlvs) {
		for(int i=0;i<tlvs.size();i++){
			if(tlvs.get(i)==null){
				tlvs.remove(i);
				i--;
			}
		}
		this.tlvs = tlvs;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	 

}
