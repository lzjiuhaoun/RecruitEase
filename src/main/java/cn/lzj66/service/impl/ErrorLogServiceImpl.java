package cn.lzj66.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.lzj66.entity.ErrorLog;
import cn.lzj66.mapper.ErrorLogMapper;
import cn.lzj66.service.ErrorLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ErrorLogServiceImpl extends ServiceImpl<ErrorLogMapper, ErrorLog> implements ErrorLogService {
    @Autowired
    ErrorLogMapper mapper;

    @Override
    public List<ErrorLog> getErrorLogList() {
        return mapper.selectList(new QueryWrapper<>());
    }

    @Override
    public void createErrorLog(ErrorLog errorLog) {
        mapper.insert(errorLog);
    }

    @Override
    public void deleteErrorLog(String id) {
        mapper.deleteById(id);
    }

    @Override
    public void updateErrorLog(ErrorLog errorLog) {
        mapper.updateById(errorLog);
    }
}
