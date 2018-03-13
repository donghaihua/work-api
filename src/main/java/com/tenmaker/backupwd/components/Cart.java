package com.tenmaker.backupwd.components;

public class Cart {
	private Long itemId;
	private Long[] specIds;
	private Integer[] counts;
	private Integer operation;

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long[] getSpecIds() {
		return specIds;
	}

	public void setSpecIds(Long[] specIds) {
		this.specIds = specIds;
	}

	public Integer[] getCounts() {
		return counts;
	}

	public void setCounts(Integer[] counts) {
		this.counts = counts;
	}

	public Integer getOperation() {
		return operation;
	}

	public void setOperation(Integer operation) {
		this.operation = operation;
	}

}
