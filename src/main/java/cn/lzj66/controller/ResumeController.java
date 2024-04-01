package cn.lzj66.controller;

import cn.lzj66.common.APIResponse;
import cn.lzj66.common.ResponeCode;
import cn.lzj66.entity.Resume;
import cn.lzj66.permission.Access;
import cn.lzj66.permission.AccessLevel;
import cn.lzj66.service.ResumeService;
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
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/resume")
public class ResumeController {

    private final static Logger logger = LoggerFactory.getLogger(ResumeController.class);

    @Autowired
    ResumeService service;

    @Value("${File.uploadPath}")
    private String uploadPath;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public APIResponse list(){
        List<Resume> list =  service.getResumeList();

        return new APIResponse(ResponeCode.SUCCESS, "查询成功", list);
    }

    @Access(level = AccessLevel.ADMIN)
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @Transactional
    public APIResponse create(Resume resume) throws IOException {
        saveResume(resume);

        service.createResume(resume);
        return new APIResponse(ResponeCode.SUCCESS, "创建成功");
    }

    @Access(level = AccessLevel.ADMIN)
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public APIResponse delete(String ids){
        System.out.println("ids===" + ids);
        // 批量删除
        String[] arr = ids.split(",");
        for (String id : arr) {
            service.deleteResume(id);
        }
        return new APIResponse(ResponeCode.SUCCESS, "删除成功");
    }

    @Access(level = AccessLevel.ADMIN)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @Transactional
    public APIResponse update(Resume resume) throws IOException {
        saveResume(resume);

        service.updateResume(resume);
        return new APIResponse(ResponeCode.SUCCESS, "更新成功");
    }

    public void saveResume(Resume resume) throws IOException {
        MultipartFile file = resume.getCoverFile();
        String newFileName = null;
        if (file != null && !file.isEmpty()) {

            // 存文件
            String oldFileName = file.getOriginalFilename();
            String randomStr = UUID.randomUUID().toString();
            newFileName = randomStr + oldFileName.substring(oldFileName.lastIndexOf("."));
            String filePath = uploadPath + File.separator + "resume" + File.separator + "cover" + File.separator + newFileName;
            File destFile = new File(filePath);
            if(!destFile.getParentFile().exists()){
                destFile.getParentFile().mkdirs();
            }
            file.transferTo(destFile);
        }
        if (!StringUtils.isEmpty(newFileName)) {
            resume.cover = newFileName;
        }

        file = resume.getRawFile();
        newFileName = null;
        if (file != null && !file.isEmpty()) {
            // 存文件
            String oldFileName = file.getOriginalFilename();
            String randomStr = UUID.randomUUID().toString();
            newFileName = randomStr + oldFileName.substring(oldFileName.lastIndexOf("."));
            // System.out.println(oldFileName.substring(oldFileName.lastIndexOf(".")));
            if (!oldFileName.substring(oldFileName.lastIndexOf(".")).equals(".pdf")) {
                return;
            }
            String filePath = uploadPath + File.separator + "resume" + File.separator + "raw" + File.separator + newFileName;
            File destFile = new File(filePath);
            if (!destFile.getParentFile().exists()){
                destFile.getParentFile().mkdirs();
            }
            System.out.println("upload raw: " + newFileName);
            file.transferTo(destFile);
        }
        if (!StringUtils.isEmpty(newFileName)) {
            resume.raw = newFileName;
        }
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public APIResponse detail(String userId){
        Resume resume =  service.getDetail(userId);

        return new APIResponse(ResponeCode.SUCCESS, "查询成功", resume);
    }
}
