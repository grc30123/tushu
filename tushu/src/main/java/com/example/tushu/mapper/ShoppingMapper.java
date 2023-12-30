package com.example.tushu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.tushu.entity.Shopping;
import com.example.tushu.mode.dto.ShoppingLIst;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author grc
 * @since 2023-09-24
 */
@Mapper
public interface ShoppingMapper extends BaseMapper<Shopping> {

    List<ShoppingLIst> getList(int size, int current, int ID_user, Integer flag);

    @Select("SELECT COUNT(*) as total FROM book LEFT JOIN shopping ON book.book_id = shopping.book_id")
    int getTotal();

    @Select("SELECT COUNT(*) as total FROM book LEFT JOIN shopping ON book.book_id = shopping.book_id WHERE shopping.ID_user = #{id_user} and shopping.flag = #{flag}")
    int getUserTotal(Integer id_user, Integer flag);

}
