package com.ssafy.enjoy.board.model.dto;

import com.ssafy.util.Exception.DtoException;

public class PageDto {
	private int pgno;
	private String key;
	private String word;
	private int start;
	private int end;
	public int getStart() {
		return start;
	}
	public void setStart(int start) throws DtoException {
		if(start<0) {
			throw new DtoException(PageDto.class, "page start number is smaller than 1");
		}
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) throws DtoException {
		if(end<=0) {
			throw new DtoException(PageDto.class, "page end number is smaller than 1");
		}
		if(end<start) {
			throw new DtoException(PageDto.class, "page end number is smaller than page start number");
		}
		this.end = end;
	}
	public int getPgno() {
		return pgno;
	}
	public void setPgno(int pgno) throws DtoException {
		if(pgno<=0) {
			throw new DtoException(PageDto.class, "page number is smaller than 1");
		}
		this.pgno = pgno;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) throws DtoException  {
		if(key==null||"".equals(key)) {
			throw new DtoException(PageDto.class, "key is empty");
		}
		this.key = key;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) throws DtoException {
		if(word==null||"".equals(word)) {
			throw new DtoException(PageDto.class, "word is empty");
		}
		this.word = word;
	}
	@Override
	public String toString() {
		return "Page [pgno=" + pgno + ", key=" + key + ", word=" + word + ", start=" + start + ", end=" + end + "]";
	}
	
}	
