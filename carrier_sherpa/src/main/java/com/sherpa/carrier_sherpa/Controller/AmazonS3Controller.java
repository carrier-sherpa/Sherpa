package com.sherpa.carrier_sherpa.Controller;

import com.sherpa.carrier_sherpa.domain.service.AmazonS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/s3")
public class AmazonS3Controller {

    private final AmazonS3Service amazonS3Service;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("images") MultipartFile multipartFile) throws IOException {
        return amazonS3Service.upload(multipartFile);
    }
}