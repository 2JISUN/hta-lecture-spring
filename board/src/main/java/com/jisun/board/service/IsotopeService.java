package com.jisun.board.service;

import com.jisun.board.dao.IsotopeDao;
import com.jisun.board.dto.IsotopeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class IsotopeService {

    @Value("${file.path}")
    private String uploadFolder;

    private final IsotopeDao isotopeDao;

    public int insertIsotope(IsotopeDto isotopeDto) {
        log.info("service isotopeDto.getFile()==={}",isotopeDto.getFile());
        String originalFile = isotopeDto.getFile().getOriginalFilename(); // 이게 진짜 파일 이름...

        String renamedFile = null;
        String folder =  null;
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        folder = simpleDateFormat.format(now);
        File dir = new File(uploadFolder+File.separator+folder);
        if(!dir.exists()) dir.mkdirs();

        // file이름 분리하고 확장자 분리
        String fileName = originalFile.substring(0,originalFile.lastIndexOf("."));
        String ext = originalFile.substring(originalFile.lastIndexOf("."));
        simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String strNow = simpleDateFormat.format(now);
        log.info("strNow==={}",strNow);
        renamedFile = fileName+"_"+strNow+ext;
        Path imgFilePath = Paths.get(dir+File.separator+renamedFile);
        isotopeDto.setOriginal(originalFile);
        isotopeDto.setRenamed(folder+"/"+renamedFile);
        try {
            Files.write(imgFilePath,isotopeDto.getFile().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int result = isotopeDao.insertIsotope(isotopeDto);
        return result;
    }

    public List<IsotopeDto> getAllList() {
        List<IsotopeDto> boardList = isotopeDao.getAllList();
        return boardList;
    }
}
