package net.yeoman.nmpcaport.io.response.zipCode;

import java.util.List;

public class ZipCodeEssentialsPagination {
	
	 
      private List<ZipCodeEssentials> zipCodes;
      private int number;
      private Long totalElements;
      private int size;
      private int totalPages;
      private Boolean next;
      private Boolean previous;
      private Boolean firstPage;
      private Boolean lastPage;
      private Boolean isEmpty;
      private Boolean hasContent;
     
      
      
      
	public List<ZipCodeEssentials> getZipCodes() {
		return zipCodes;
	}
	
	public void setZipCodes(List<ZipCodeEssentials> zipCodes) {
		this.zipCodes = zipCodes;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	
	
	public Long getTotalElements() {
		return totalElements;
	}
	
	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public int getTotalPages() {
		return totalPages;
	}
	
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public Boolean getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(Boolean firstPage) {
		this.firstPage = firstPage;
	}

	public Boolean getLastPage() {
		return lastPage;
	}

	public void setLastPage(Boolean lastPage) {
		this.lastPage = lastPage;
	}

	public Boolean getIsEmpty() {
		return isEmpty;
	}

	public void setIsEmpty(Boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

	public Boolean getNext() {
		return next;
	}

	public void setNext(Boolean next) {
		this.next = next;
	}

	public Boolean getPrevious() {
		return previous;
	}

	public void setPrevious(Boolean previous) {
		this.previous = previous;
	}

	public Boolean getHasContent() {
		return hasContent;
	}

	public void setHasContent(Boolean hasContent) {
		this.hasContent = hasContent;
	}
	
	
	
      
}
