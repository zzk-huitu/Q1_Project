package com.yc.q1.core.enumType;

public enum bagCodeEnum {
	XFBAGE("XF","1"),SKBAGE("SK","2"),XFBZBAGE("XFBZ","3"),BAGECODE("ALL","'1','2','3'");
	private String name;
	private String index;
	
	private bagCodeEnum(String name,String index){
		this.name=name;
		this.index=index;
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	

}
