package com.yt.file.upload.fileserver.service;

import java.util.List;
import java.util.Optional;

import com.yt.file.upload.fileserver.domain.Files;
import com.yt.file.upload.fileserver.domain.FilesEntity;
import com.yt.file.upload.fileserver.repository.FilesDao;
import com.yt.file.upload.fileserver.repository.FilesRepository;
import com.yt.file.upload.fileserver.util.FastCopy;
import com.yt.file.upload.fileserver.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

/**
 * FilesEntity 服务.
 *
 * @author <a href="https://waylau.com">Way Lau</a>
 * @since 1.0.0 2017年7月30日
 */
@Service
public class FilesServiceImpl implements FilesService {

    @Autowired
    private FilesRepository filesRepository;

    @Autowired
    private FilesDao filesDao;

    @Autowired
    private RedisUtil redisUtil;

    private final static String FILES_LIST_CACHE = "FILES_LIST_CACHE";

    @Override
    public Files saveFile(FilesEntity filesEntity) {
        FilesEntity filesEntity1 = filesRepository.save(filesEntity);
        Files files = FastCopy.copy(filesEntity1, Files.class);
        filesDao.save(files);
        redisUtil.del(FILES_LIST_CACHE);
        redisUtil.set(FILES_LIST_CACHE, listFilesByPage(0, 20));
        return files;
    }

    @Override
    public void removeFile(String id) {
        filesRepository.deleteById(id);
        filesDao.deleteById(id);
        redisUtil.del(FILES_LIST_CACHE);
        redisUtil.set(FILES_LIST_CACHE, listFilesByPage(0, 20));
    }

    @Override
    public Optional<FilesEntity> getFileById(String id) {
        return filesRepository.findById(id);
    }

    @Override
    public List<Files> listFilesByPage(int pageIndex, int pageSize) {
        if (redisUtil.hasKey(FILES_LIST_CACHE)) {
            return FastCopy.copyList((List<Files>) redisUtil.get(FILES_LIST_CACHE), Files.class);
        } else {
            Page<Files> page = null;
            List<Files> list = null;

            Sort sort = new Sort(Direction.DESC, "uploadDate");
            Pageable pageable = PageRequest.of(pageIndex, pageSize, sort);

            page = filesDao.findAll(pageable);
            list = page.getContent();
            redisUtil.set(FILES_LIST_CACHE, list);
            return list;
        }

    }
}
