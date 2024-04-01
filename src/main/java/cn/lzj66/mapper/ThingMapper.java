package cn.lzj66.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.lzj66.entity.Thing;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ThingMapper extends BaseMapper<Thing> {
//    List<Thing> getList();
//    boolean update(Thing thing);
    Thing getDetail(String id);
}
