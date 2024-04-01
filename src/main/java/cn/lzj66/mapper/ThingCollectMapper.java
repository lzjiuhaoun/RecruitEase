package cn.lzj66.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.lzj66.entity.ThingCollect;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ThingCollectMapper extends BaseMapper<ThingCollect> {


    List<Map> getThingCollectList(String userId);
}
