package cn.lzj66.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.lzj66.entity.Post;
import cn.lzj66.mapper.PostMapper;
import cn.lzj66.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {
    @Autowired
    PostMapper mapper;

    @Override
    public List<Post> getPostList() {
        return mapper.selectList(new QueryWrapper<>());
    }

    @Override
    public void createPost(Post post) {
        post.setCreateTime(String.valueOf(System.currentTimeMillis()));
        System.out.println(post);
        mapper.insert(post);
    }

    @Override
    public void deletePost(String id) {
        mapper.deleteById(id);
    }

    @Override
    public void updatePost(Post post) {
        mapper.updateById(post);
    }

    @Override
    public List<Post> getUserPost(String userId) {
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return mapper.selectList(queryWrapper);
    }

    @Override
    public List<Post> getCompanyPost(String companyId) {
//        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("company_id", companyId);
//        return mapper.selectList(queryWrapper);
        return mapper.getCompanyPostList(companyId);
    }
}
