package cn.lzj66.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.lzj66.mapper.AdMapper;
import cn.lzj66.service.AdService;
import cn.lzj66.entity.Ad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdServiceImpl extends ServiceImpl<AdMapper, Ad> implements AdService {
    @Autowired
    AdMapper mapper;

    @Override
    public List<Ad> getAdList() {
        return mapper.selectList(new QueryWrapper<>());
    }

    @Override
    public void createAd(Ad ad) {
        System.out.println(ad);
        ad.setCreateTime(String.valueOf(System.currentTimeMillis()));
        mapper.insert(ad);
    }

    @Override
    public void deleteAd(String id) {
        mapper.deleteById(id);
    }

    @Override
    public void updateAd(Ad ad) {
        mapper.updateById(ad);
    }
}
