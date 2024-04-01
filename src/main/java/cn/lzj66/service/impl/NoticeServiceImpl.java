package cn.lzj66.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.lzj66.entity.Notice;
import cn.lzj66.mapper.NoticeMapper;
import cn.lzj66.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {
    @Autowired
    NoticeMapper mapper;

    @Override
    public List<Notice> getNoticeList(String userId) {
//        QueryWrapper<Notice> wrapper = new QueryWrapper<>();
//        wrapper.eq("to_user_id", userId);
//        return mapper.selectList(wrapper);
        return mapper.selectList(null);
    }

    @Override
    public void createNotice(Notice notice) {
        System.out.println(notice);
        notice.setCreateTime(String.valueOf(System.currentTimeMillis()));
        mapper.insert(notice);
    }

    @Override
    public void deleteNotice(String id) {
        mapper.deleteById(id);
    }

    @Override
    public void updateNotice(Notice notice) {
        mapper.updateById(notice);
    }
}
