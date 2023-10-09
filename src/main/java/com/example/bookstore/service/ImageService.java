package com.example.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageService {
    @Autowired
    IImageService imageService;
    public String create(MultipartFile file) {
      String imageUrl= null;
//        for (MultipartFile file : files) {

        try {

            String fileName = imageService.save(file);

            imageUrl = imageService.getImageUrl(fileName);
            System.out.println(imageUrl);

            // do whatever you want with that

        } catch (Exception e) {
            e.printStackTrace();
            //  throw internal error;
        }
//        }

        return imageUrl;
    }
    public void delete(String url) {

            try {
                int lastSlashIndex = url.lastIndexOf('/');
                if (lastSlashIndex != -1) {
                    String name = url.substring(lastSlashIndex + 1);
                    imageService.delete(name);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
    public ResponseEntity create(@RequestParam(name = "file") MultipartFile[] files) {

        for (MultipartFile file : files) {

            try {

                String fileName = imageService.save(file);

                String imageUrl = imageService.getImageUrl(fileName);

                // do whatever you want with that

            } catch (Exception e) {
                //  throw internal error;
            }
        }

        return ResponseEntity.ok().build();
    }
}
