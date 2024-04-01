package cn.lzj66.service;


import cn.lzj66.entity.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentList();
    void createComment(Comment comment);
    void deleteComment(String id);
    void updateComment(Comment comment);
    Comment getCommentDetail(String id);

    List<Comment> getThingCommentList(String thingId, String order);

    List<Comment> getUserCommentList(String userId);
}
