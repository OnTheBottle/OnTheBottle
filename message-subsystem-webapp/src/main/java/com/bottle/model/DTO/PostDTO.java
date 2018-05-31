package com.bottle.model.DTO;

import com.bottle.model.entity.UploadFile;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class PostDTO {
    private UUID id;
    private String security;
    private UUID userId;
    private String text;
    private String title;
    private List<UploadFile> uploadFiles;
}
