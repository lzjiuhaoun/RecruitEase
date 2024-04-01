package cn.lzj66.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.lzj66.entity.Resume;
import cn.lzj66.mapper.ResumeMapper;
import cn.lzj66.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeServiceImpl extends ServiceImpl<ResumeMapper, Resume> implements ResumeService {
    @Autowired
    ResumeMapper mapper;

    @Override
    public List<Resume> getResumeList() {
        return mapper.selectList(new QueryWrapper<>());
    }

    @Override
    public void createResume(Resume resume) {
        System.out.println(resume);
        mapper.insert(resume);
    }

    @Override
    public void deleteResume(String id) {
        mapper.deleteById(id);
    }

    @Override
    public void updateResume(Resume resume) {
        mapper.updateById(resume);
    }

    @Override
    public Resume getDetail(String userId) {
        QueryWrapper<Resume> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return mapper.selectOne(queryWrapper);
    }
}
