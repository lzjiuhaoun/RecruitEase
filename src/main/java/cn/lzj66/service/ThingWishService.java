package cn.lzj66.service;


import cn.lzj66.entity.ThingWish;

import java.util.List;
import java.util.Map;

public interface ThingWishService {
    List<Map> getThingWishList(String userId);
    void createThingWish(ThingWish thingWish);
    void deleteThingWish(String id);

    ThingWish getThingWish(String userId, String thingId);
}
