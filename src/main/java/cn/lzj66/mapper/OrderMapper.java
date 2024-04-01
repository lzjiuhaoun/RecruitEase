package cn.lzj66.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.lzj66.entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    List<Order> getList();

    List<Order> getUserOrderList(String userId, String status);
}
