package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
@Data
@Builder
public class LeaseSignRequestDTO {
    @Size(min = 1)
    List<String> signerEmails;
    @NonNull
    Long apartmentNumber;
    @JsonIgnore
    File file;
    @Size(min = 1)
    List<String> ccEmails;
    @JsonIgnore
    MetaData metaData;
}
