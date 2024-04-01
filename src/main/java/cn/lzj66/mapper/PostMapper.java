package cn.lzj66.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.lzj66.entity.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper extends BaseMapper<Post> {

    List<Post> getCompanyPostList(String companyId);
}
