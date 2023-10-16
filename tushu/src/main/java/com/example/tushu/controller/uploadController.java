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
@RequestMapping("upLoad")
public class uploadController {
    @Value(("${web.upLoadPath}"))
    private String upLoadPath;

    @PostMapping("/upLoadImage")
    public result upLoadImage(MultipartFile file) {
        String filename = file.getOriginalFilename();
        String suffixName = filename.substring(filename.lastIndexOf("."));
        String newName = UUID.randomUUID() + suffixName;
        String saveUrl = upLoadPath + "/" + newName;
        File newFile = new File(saveUrl);
        if (!newFile.exists()) {
            newFile.mkdir();
        }
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result.ok(saveUrl);
    }

    // 处理图片读取请求
    @GetMapping("/read")
    public ResponseEntity<InputStreamResource> read(@RequestParam("fileName") String fileName) throws FileNotFoundException {
        String filePath = upLoadPath + "/" + fileName; // 拼接图片文件的真实路径
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
