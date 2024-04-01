package cn.lzj66.service;


import cn.lzj66.entity.Notice;

import java.util.List;

public interface NoticeService {
    List<Notice> getNoticeList(String userId);

    void createNotice(Notice notice);

    void deleteNotice(String id);

    void updateNotice(Notice notice);
}
