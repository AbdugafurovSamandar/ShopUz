package com.company.service;

import com.company.dto.AttachDTO;
import com.company.entity.AttachEntity;
import com.company.exp.BadRequestException;
import com.company.repository.AttachRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service

public class AttachService {
    @Value("${attach.folder}")
    private String attachFolder;

    @Value("${server.url}")
    private String serverUrl;
    @Autowired
    private AttachRepository attachRepository;

    @Autowired
    @Lazy
    private ProfileService profileService;

    public AttachDTO create(MultipartFile file) {
        try {
            String pathFolder = getYmDString();
            String uuid = UUID.randomUUID().toString();
            String extension = getExtension(file.getOriginalFilename());

            String fileName = uuid + "." + extension;

            File folder = new File(attachFolder + pathFolder);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            byte[] bytes = file.getBytes();
            Path path = Paths.get(attachFolder + pathFolder + "/" + fileName);
            Files.write(path, bytes);


            AttachEntity entity = new AttachEntity();
            entity.setUuid(uuid);
            entity.setOriginalName(file.getOriginalFilename());
            entity.setExtension(extension);
            entity.setSize(file.getSize());
            entity.setPath(pathFolder);
            attachRepository.save(entity);

            AttachDTO dto = new AttachDTO();
            dto.setUrl(serverUrl + "attach/open/" + entity.getUuid());

            return dto;


        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public byte[] loadImage(String fileName) {
        byte[] imageInByte;
        String path = getFileFullPath(get(fileName));

        BufferedImage originalImage;
        try {
            originalImage = ImageIO.read(new File(path));
        } catch (Exception e) {
            return new byte[0];
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(originalImage, "png", baos);
            baos.flush();
            imageInByte = baos.toByteArray();
            baos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return imageInByte;
    }


    private String getExtension(String fileName) { // mp3/jpg/npg/mp4.....
        int lastIndex = fileName.lastIndexOf(".");
        return fileName.substring(lastIndex + 1);
    }

    private String getYmDString() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int day = Calendar.getInstance().get(Calendar.DATE);

        return year + "/" + month + "/" + day; // 2022/04/23
    }

    private String getFileFullPath(AttachEntity entity) {
        return attachFolder + entity.getPath() + "/" + entity.getUuid() + "." + entity.getExtension();
    }
    private AttachEntity get(String id) {
        return attachRepository.findById(id).orElseThrow(() -> {
            throw new BadRequestException("Rasm topilmadi!");
        });
    }

}

