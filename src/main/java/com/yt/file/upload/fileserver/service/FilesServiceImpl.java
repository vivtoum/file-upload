package com.yt.file.upload.fileserver.service;

import java.util.List;
import java.util.Optional;

import com.yt.file.upload.fileserver.domain.FilesEntity;
import com.yt.file.upload.fileserver.repository.FilesRepository;
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
 * @since 1.0.0 2017年7月30日
 * @author <a href="https://waylau.com">Way Lau</a> 
 */
@Service
public class FilesServiceImpl implements FilesService {
	
	@Autowired
	public FilesRepository filesRepository;

	@Override
	public FilesEntity saveFile(FilesEntity filesEntity) {
		return filesRepository.save(filesEntity);
	}

	@Override
	public void removeFile(String id) {
		filesRepository.deleteById(id);
	}

	@Override
	public Optional<FilesEntity> getFileById(String id) {
		return filesRepository.findById(id);
	}

	@Override
	public List<FilesEntity> listFilesByPage(int pageIndex, int pageSize) {
		Page<FilesEntity> page = null;
		List<FilesEntity> list = null;
		
		Sort sort = new Sort(Direction.DESC,"uploadDate"); 
		Pageable pageable = PageRequest.of(pageIndex, pageSize, sort);
		
		page = filesRepository.findAll(pageable);
		list = page.getContent();
		return list;
	}
}
