package com.example.tushu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.tushu.entity.Book;
import com.example.tushu.mapper.BookMapper;
import com.example.tushu.mode.vo.ListPage;
import com.example.tushu.service.BookService;
import com.example.tushu.util.result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
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
    @Autowired
    private final BookService bookService;
    private final BookMapper bookMapper;

    public BookController(BookService bookService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @GetMapping("/list")
    public result list() {
        List<Book> res = bookService.list();
        return result.ok(res);
    }

    @PostMapping("/getlist")
    public result getlist(@RequestBody ListPage listPage) {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        if (listPage.getCondition().getName() != null) {
            queryWrapper.like("name", listPage.getCondition().getName());
        }
        Page<Book> page = bookService.page(new Page<>(listPage.getCurrent(), listPage.getSize()), queryWrapper);
        List<Book> list = page.getRecords();
        int total = bookService.list().size();
        HashMap<String, Object> res = new HashMap<>();
        res.put("list", list);
        res.put("total", total);
        return result.ok(res);
    }

    @GetMapping("/getbyid")
    public result getbyid(int id) {
        Book res = bookService.getById(id);
        return result.ok(res);
    }

    @PostMapping("/savebook")
    public result savebook(@RequestBody Book book) {
        //格式化时间
        Instant instant = Instant.parse(book.getPublicationDate());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.of("UTC"));
        book.setPublicationDate(formatter.format(instant));
        boolean res = bookService.saveOrUpdate(book);
        return result.ok(res);
    }

    @DeleteMapping("/deletebook")
    public result deletebook(int id) {
        boolean res = bookService.removeById(id);
        return result.ok(res);
    }

}
