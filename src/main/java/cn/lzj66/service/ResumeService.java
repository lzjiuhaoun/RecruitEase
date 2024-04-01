package cn.lzj66.service;


import cn.lzj66.entity.Resume;

import java.util.List;

public interface ResumeService {
    List<Resume> getResumeList();
    void createResume(Resume resume);
    void deleteResume(String id);

    void updateResume(Resume resume);

    Resume getDetail(String userId);
}
