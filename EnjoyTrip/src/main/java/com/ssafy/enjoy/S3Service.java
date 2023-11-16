package com.ssafy.enjoy;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/s3")
@RequiredArgsConstructor
public class S3Service {

    private final AmazonS3Client amazonS3Client;

    @Value("${aws.s3.bucket}")
    private String bucket;

    @GetMapping("test")
    public void test(){
        System.out.println("s3 receive");
    }

    @PostMapping("upload")
    public String saveFile(@RequestParam("file") MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        amazonS3Client.putObject(bucket, originalFilename, file.getInputStream(), metadata);
        String url = amazonS3Client.getUrl(bucket, originalFilename).toString();
        System.out.println(url);
        return url;
    }
//    public String S3FileUpload(@RequestParam("file") MultipartFile file){
//        System.out.println("upload");
//        try{
//            String fileName = file.getOriginalFilename();
//            String fileUrl = "https://" + bucket + "/img" +  fileName;
//            ObjectMetadata metaData = new ObjectMetadata();
//            metaData.setContentType(file.getContentType());
//            metaData.setContentLength(file.getSize());
//            System.out.println("여긴?");
//            amazonS3Client.putObject(bucket,fileName,file.getInputStream(),metaData);
//            return fileUrl;
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//        System.out.println("error");
//        return "error";
//    }
}