package cn.lzj66.controller;

import cn.lzj66.common.APIResponse;
import cn.lzj66.common.ResponeCode;
import cn.lzj66.entity.Post;
import cn.lzj66.permission.Access;
import cn.lzj66.permission.AccessLevel;
import cn.lzj66.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/post")
public class PostController {

    private final static Logger logger = LoggerFactory.getLogger(PostController.class);

    @Autowired
    PostService service;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public APIResponse list(){
        List<Post> list =  service.getPostList();
        for (Post post : list) {
            if (!StringUtils.isEmpty(post.createTime)) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");
                Date date = new Date(Long.parseLong(post.createTime));
                post.setCreateTime(formatter.format(date));
                System.out.println(post.createTime);
            }
        }
        return new APIResponse(ResponeCode.SUCCESS, "查询成功", list);
    }

    @Access(level = AccessLevel.ADMIN)
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @Transactional
    public APIResponse create(Post post) throws IOException {

        service.createPost(post);
        return new APIResponse(ResponeCode.SUCCESS, "创建成功");
    }

    @Access(level = AccessLevel.ADMIN)
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public APIResponse delete(String ids){
        System.out.println("ids===" + ids);
        // 批量删除
        String[] arr = ids.split(",");
        for (String id : arr) {
            service.deletePost(id);
        }
        return new APIResponse(ResponeCode.SUCCESS, "删除成功");
    }

    @Access(level = AccessLevel.ADMIN)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @Transactional
    public APIResponse update(Post post) throws IOException {
        service.updatePost(post);
        return new APIResponse(ResponeCode.SUCCESS, "更新成功");
    }

    @RequestMapping(value = "/listUserPostApi", method = RequestMethod.GET)
    public APIResponse listUserPostApi(String userId){
        List<Post> list = service.getUserPost(userId);

        return new APIResponse(ResponeCode.SUCCESS, "查询成功", list);
    }

    @RequestMapping(value = "/listCompanyPostApi", method = RequestMethod.GET)
    public APIResponse listCompanyPostApi(String companyId){
        List<Post> list = service.getCompanyPost(companyId);

        return new APIResponse(ResponeCode.SUCCESS, "查询成功", list);
    }


}
