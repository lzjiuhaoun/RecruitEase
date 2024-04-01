package cn.lzj66.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@TableName("b_company")
public class Company {
    @TableId(value = "id",type = IdType.AUTO)
    public Long id;
    @TableField
    public String cover;
    @TableField(exist = false)
    public MultipartFile coverFile;
    @TableField
    public String title;
    @TableField
    public String size;
    @TableField
    public String industry;
    @TableField
    public String description;
    @TableField
    public String location;
    @TableField
    public String wechat;
    @TableField
    public String userId;

}
