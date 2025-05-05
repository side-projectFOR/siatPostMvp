package com.siat.post.domain.post;

import org.apache.ibatis.annotations.Mapper;

import com.siat.post.domain.post.dto.Bookmark;
import com.siat.post.domain.post.dto.BookmarkRequestDto;

@Mapper
public interface BookmarkMapper {
  public boolean existsBookmark(BookmarkRequestDto bookmark);

  public int insertBookmark(Bookmark bookmark) ;

  public int deleteBookmark(BookmarkRequestDto request);
  
}
