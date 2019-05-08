package com.yt.file.upload.fileserver.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import com.yt.file.upload.fileserver.domain.Files;
import com.yt.file.upload.fileserver.domain.FilesEntity;
import com.yt.file.upload.fileserver.service.FilesService;
import com.yt.file.upload.fileserver.util.MD5Util;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@CrossOrigin(origins = "*", maxAge = 3600) // 允许所有域名访问
@Controller
public class FilesController {

    @Autowired
    private FilesService filesService;

    @Value("${server.address}")
    private String serverAddress;

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping(value = "/")
    public String index(Model model) {
        // 展示最新二十条数据
        model.addAttribute("files", filesService.listFilesByPage(0, 20));
        return "index";
    }

    /**
     * 分页查询文件
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("files/{pageIndex}/{pageSize}")
    @ResponseBody
    public List<Files> listFilesByPage(@PathVariable int pageIndex, @PathVariable int pageSize) {
        return filesService.listFilesByPage(pageIndex, pageSize);
    }

    /**
     * 获取文件片信息
     *
     * @param id
     * @return
     * @throws UnsupportedEncodingException
     */
    @GetMapping("files/{id}")
    @ResponseBody
    public ResponseEntity<Object> serveFile(@PathVariable String id) throws UnsupportedEncodingException {

        Optional<FilesEntity> file = filesService.getFileById(id);

        if (file.isPresent()) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=" + new String(file.get().getName().getBytes("utf-8"), "ISO-8859-1"))
                    .header(HttpHeaders.CONTENT_TYPE, "application/octet-stream")
                    .header(HttpHeaders.CONTENT_LENGTH, file.get().getSize() + "").header("Connection", "close")
                    .body(file.get().getContent().getData());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("FilesEntity was not fount");
        }

    }

    /**
     * 在线显示文件
     *
     * @param id
     * @return
     */
    @GetMapping("/view/{id}")
    @ResponseBody
    public ResponseEntity<Object> serveFileOnline(@PathVariable String id) {

        Optional<FilesEntity> file = filesService.getFileById(id);

        if (file.isPresent()) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "fileName=\"" + file.get().getName() + "\"")
                    .header(HttpHeaders.CONTENT_TYPE, file.get().getContentType())
                    .header(HttpHeaders.CONTENT_LENGTH, file.get().getSize() + "").header("Connection", "close")
                    .body(file.get().getContent().getData());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("FilesEntity was not fount");
        }

    }

    /**
     * 上传
     *
     * @param file
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

        try {
            FilesEntity f = new FilesEntity(file.getOriginalFilename(), file.getContentType(), String.valueOf(file.getSize()),
                    new Binary(file.getBytes()));
            f.setMd5(MD5Util.getMD5(file.getInputStream()));
            filesService.saveFile(f);
        } catch (IOException | NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "Your " + file.getOriginalFilename() + " is wrong!");
            return "redirect:/";
        }

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }

    /**
     * 上传接口
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @ResponseBody
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        Files returnFilesEntity = null;
        try {
            FilesEntity f = new FilesEntity(file.getOriginalFilename(), file.getContentType(), String.valueOf(file.getSize()),
                    new Binary(file.getBytes()));
            f.setMd5(MD5Util.getMD5(file.getInputStream()));
            returnFilesEntity = filesService.saveFile(f);
            String path = "//" + serverAddress + ":" + serverPort + "/view/" + returnFilesEntity.getId();
            return ResponseEntity.status(HttpStatus.OK).body(path);

        } catch (IOException | NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }

    }

    /**
     * 删除文件
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteFile(@PathVariable String id) {

        try {
            filesService.removeFile(id);
            return ResponseEntity.status(HttpStatus.OK).body("DELETE Success!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
