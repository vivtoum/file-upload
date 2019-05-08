package com.yt.file.upload.fileserver.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.crypto.Data;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * @version 0.0.1
 * @author: huyt
 * @date: 2019/5/8 20:37
 */
@Entity
public class Files implements Serializable {
    private String id;
    private String name;
    private String contentType;
    private String size;
    private Date uploadDate;
    private String md5;
    private String path;

    @Id
    @Column(name = "id", nullable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "content_type", nullable = true, length = 255)
    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Basic
    @Column(name = "size", nullable = true, precision = 0)
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Basic
    @Column(name = "upload_date", nullable = true)
    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    @Basic
    @Column(name = "md5", nullable = true, length = 255)
    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    @Basic
    @Column(name = "path", nullable = true, length = 255)
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Files files = (Files) o;
        return id == files.id &&
                Objects.equals(name, files.name) &&
                Objects.equals(contentType, files.contentType) &&
                Objects.equals(size, files.size) &&
                Objects.equals(uploadDate, files.uploadDate) &&
                Objects.equals(md5, files.md5) &&
                Objects.equals(path, files.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, contentType, size, uploadDate, md5, path);
    }
}
