package com.example.tushu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.tushu.entity.Book;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author grc
 * @since 2023-09-24
 */
@Mapper
public interface BookMapper extends BaseMapper<Book> {

}
