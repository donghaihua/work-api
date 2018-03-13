package com.tenmaker.backupwd.util;

import java.util.List;

public class PageUtil {
	private List<Object> data;
	private Long totalCount;
	private Long lastPage;
	private Integer limit;

	public PageUtil() {

	}

	public PageUtil(List<Object> data, Long totalCount, Integer limit) {
		this.data = data;
		this.totalCount = totalCount == null ? 0L : totalCount;
		this.limit = limit;
		if (this.totalCount <= limit) {
			this.lastPage = 1L;
		} else {
			Long mu = totalCount % limit;
			Long lastPage = (totalCount - mu) / limit;
			if (mu > 0) {
				lastPage = lastPage + 1;
			}
			this.lastPage = lastPage;
		}
	}

	public List<Object> getData() {
		return data;
	}

	public void setData(List<Object> data) {
		this.data = data;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public Long getLastPage() {
		return lastPage;
	}

	public void setLastPage(Long lastPage) {
		this.lastPage = lastPage;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

}
