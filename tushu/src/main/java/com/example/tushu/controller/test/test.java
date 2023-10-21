package com.example.tushu.controller.test;

import com.example.tushu.util.result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/api/upload")
public class test {

    @PostMapping("/image")
    public ResponseEntity<result> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            // 检查文件是否为空
            if (file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.err("File is empty."));
            }

            // 保存文件到指定路径
            String uploadPath = "C:/Users/30123/Desktop/javaUpload/test";
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Path targetLocation = Paths.get(uploadPath).resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return ResponseEntity.status(HttpStatus.OK).body(result.ok("File uploaded successfully."));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result.err("Failed to upload file."));
        }
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        // 判断文件是否为空
        if (file.isEmpty()) {
            return "请选择要上传的文件";
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 生成新的文件名
        String newFileName = System.currentTimeMillis() + suffixName;
        // 获取上传文件的路径
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "resource" + System.getProperty("file.separator") + "static" + System.getProperty("file.separator") + "book" + System.getProperty("file.separator");
        // 创建文件对象

        File dest = new File(filePath + newFileName);
        // 判断文件父目录是否存在

        if (!dest.getParentFile().exists()) {
            // 创建父目录
            dest.getParentFile().mkdirs();
        }

        // 将上传的文件保存到指定的路径
        return filePath;
//            file.transferTo(dest);


    }

}
