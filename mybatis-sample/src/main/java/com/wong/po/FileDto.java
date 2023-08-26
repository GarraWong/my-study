package com.wong.po;

import lombok.Data;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 这是类的描述 补充它
 *
 * @author : Wym's Code coding MacBook pro 2020 Silicon
 * @date : 2023/8/11 19:49
 */
@Data
@ToString
public class FileDto {

    private MultipartFile file;

}
