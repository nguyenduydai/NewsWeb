package paging;

public class PageRequest implements Pageble{
	private Integer page;
	private Integer maxPageItem;
	private String sortName;
	private String sortBy;
	

	public String getSortName() {
		return sortName;
	}


	public String getSortBy() {
		return sortBy;
	}


	
	public PageRequest(Integer page, Integer maxPageItem, String sortName,String sortBy) {
		this.page = page;
		this.maxPageItem = maxPageItem;
		this.sortName=sortName;
		this.sortBy = sortBy;
	}

	@Override
	public Integer getPage() {
		return this.page;
	}

	@Override
	public Integer getOffset() {
		if (this.page != null && this.maxPageItem != null) {
			return (this.page - 1) * this.maxPageItem;
		}
		return null;
	}

	@Override
	public Integer getLimit() {
		return this.maxPageItem;
	}

}
