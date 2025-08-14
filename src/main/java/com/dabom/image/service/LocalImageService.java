package com.dabom.image.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class LocalImageService implements ImageService {
    @Override
    public String uploadImage(MultipartFile file) throws IOException {
        return "";
    }

    @Override
    public String deleteImage(Integer idx) {
        return "";
    }
}
