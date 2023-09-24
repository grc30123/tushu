package com.example.tushu.service.impl;

import com.example.tushu.entity.Book;
import com.example.tushu.mapper.BookMapper;
import com.example.tushu.service.BookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author grc
 * @since 2023-09-24
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

}
