package com.ssafy.enjoy;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;


@RestController
@RequestMapping("/s3")
@RequiredArgsConstructor
public class S3Service {

    private S3Client s3Client;

    @Value("${aws.s3.bucket}")
    private String bucket;

    @GetMapping("test")
    public void test() {
        System.out.println("s3 receive");
    }

    @PostMapping("upload")
    public String S3FileUpload(@RequestParam("file") MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            String contentType = file.getContentType();
            String path = "test";

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucket)
                    .contentType(contentType)
                    .key(path + fileName)
                    .build();

//            System.out.println(file);
            PutObjectResponse response = s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));
            System.out.println(response.sdkHttpResponse().statusText());
            System.out.println(response.sdkHttpResponse().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }


}