package com.ssafy.enjoy.map.vo;

import com.ssafy.enjoy.map.dto.MyMapDto;
import com.ssafy.util.DtoException;
import com.ssafy.util.VOException;
import lombok.Getter;

@Getter
public class MapDescVo {
	private int contentId;
	private String homepage, overview, telname;

	public void setContentId(int contentId) throws VOException{
		if (contentId == 0)
			throw new VOException("content_id");
		this.contentId = contentId;
	}

	public void setHomepage(String homepage) throws VOException{
		if (homepage == null || !"".equals(homepage))
			throw new VOException("content_id");
		this.homepage = homepage;
	}

	public void setOverview(String overview) throws VOException{
		if (overview == null || !"".equals(overview))
			throw new VOException("content_id");
		this.overview = overview;
	}

	public void setTelname(String telname) throws VOException{
		if (telname == null || !"".equals(telname))
			throw new VOException("content_id");
		this.telname = telname;
	}
	@Override
	public String toString() {
		return "MapDescVo{" +
				"contentId=" + contentId +
				", homepage='" + homepage + '\'' +
				", overview='" + overview + '\'' +
				", telname='" + telname + '\'' +

				'}';
	}
}
