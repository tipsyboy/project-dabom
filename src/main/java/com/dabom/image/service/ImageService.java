package com.dabom.image.service;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    public String uploadImage(MultipartFile file) throws IOException;

    public String deleteImage(@RequestParam Integer idx);

}