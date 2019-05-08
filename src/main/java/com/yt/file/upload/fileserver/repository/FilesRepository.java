package com.yt.file.upload.fileserver.repository;

import com.yt.file.upload.fileserver.domain.FilesEntity;
import org.springframework.data.mongodb.repository.MongoRepository;



/**
 * FilesEntity 存储库.
 * 
 * @since 1.0.0 2017年3月28日
 * @author <a href="https://waylau.com">Way Lau</a> 
 */
public interface FilesRepository extends MongoRepository<FilesEntity, String> {
}
