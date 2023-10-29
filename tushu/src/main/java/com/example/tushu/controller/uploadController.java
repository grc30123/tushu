package com.example.tushu.controller;

import com.example.tushu.util.result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

@RestController
@RequestMapping("/upLoad")
public class uploadController {
    //    @Value(("${web.upLoadPath}"))
//    private String upLoadPath;
    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

    @PostMapping("/uploadImage")
    public result uploadImage(@RequestParam("file") MultipartFile file) {
        // 判断文件是否为空
        if (file.isEmpty()) {
            return result.err("请选择要上传的文件");
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 生成新的文件名
//        String newFileName = System.currentTimeMillis() + suffixName;
        String newFileName = UUID.randomUUID().toString() + suffixName;
        File dest = new File(uploadPath + newFileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.ok(newFileName);

    }

    // 处理图片读取请求
    @GetMapping("/read/{fileName}")
    public ResponseEntity<InputStreamResource> read(@PathVariable("fileName") String fileName) throws FileNotFoundException {
        String filePath = uploadPath + fileName; // 拼接图片文件的真实路径
//        String filePath = "/static/books" + fileName; // 拼接图片文件的真实路径
//        + "/bookImage/"
        File file = new File(filePath); // 创建文件对象
        if (file.exists()) { // 判断文件是否存在
            InputStream inputStream = new FileInputStream(file); // 创建输入流对象
            InputStreamResource resource = new InputStreamResource(inputStream); // 创建输入流资源对象
            HttpHeaders headers = new HttpHeaders(); // 创建响应头对象
            headers.add("Content-Type", "image/jpeg"); // 设置响应头为图片类型
            return new ResponseEntity<>(resource, headers, HttpStatus.OK); // 返回响应实体对象，包含输入流资源、响应头和状态码
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 如果文件不存在，返回404状态码
        }
    }
}

