package com.example.tushu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.tushu.entity.Book;
import com.example.tushu.mapper.BookMapper;
import com.example.tushu.mode.vo.ListPage;
import com.example.tushu.service.BookService;
import com.example.tushu.util.result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author grc
 * @since 2023-09-24
 */
@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;
    private final BookMapper bookMapper;

    public BookController(BookService bookService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

//    @GetMapping("/getlist")
//    public result getlist() {
//        List<Book> res = bookService.list();
//        return result.ok(res);
//    }

    @PostMapping("/getlist")
    public result list(@RequestBody ListPage listPage) {
        Page<Book> page = new Page<>(listPage.getCurrent(), listPage.getSize());
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        if (listPage.getCondition().getName() != null) {
            queryWrapper.like("name", listPage.getCondition().getName());
        }
        IPage<Book> Records = bookService.page(page, queryWrapper);
        List<Book> ress = Records.getRecords();
        return result.ok(ress);
    }

    @GetMapping("/getbyid")
    public result getbyid(int id) {
        Book res = bookService.getById(id);

        return result.ok(res);
    }

    @PostMapping("/savebook")
    public result savebook(@RequestBody Book book) {
        boolean res = bookService.saveOrUpdate(book);
        return result.ok(res);
    }

    @DeleteMapping("/deletebook")
    public result deletebook(int id) {
        boolean res = bookService.removeById(id);
        return result.ok(res);
    }

}
