package com.springboot.web;

import com.springboot.model.*;
import com.springboot.service.PatrolImgService;
import com.springboot.service.PatrolService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller//一个控制类可以写两个函数的
@RequestMapping(value = "/patrol")//所有的路径都加一个user
//@Slf4j日志，就算了吧
@CrossOrigin//跨域问题
public class patrolController {
    @Autowired
    private PatrolService patrolService;

    @Autowired
    private PatrolImgService patrolImgService;

    @PostMapping("/add")
    public @ResponseBody
    Map<String, Object> add(patrols patrol) throws ParseException {
        Map<String, Object> map = new HashMap<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String sDate = simpleDateFormat.format(new Date());
        patrol.setDate(sDate);
        try{
            patrolService.insert(patrol);
            map.put("success", true);
            map.put("msg", "添加成功");
            map.put("patrol",patrol);
        }catch(Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("msg", "添加失败："+ e.getMessage());
        }
        return map;
    }

    @GetMapping("/findLike")
    public @ResponseBody Map<String, Object> findLike(String date,Integer parentId){
        Map<String, Object> map = new HashMap<>();
        try{
            List<patrols> results = patrolService.findLike(date,parentId);
            map.put("results",results);
            map.put("success", true);
            map.put("msg", "查询成功");
        }catch(Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("msg", "查询失败："+ e.getMessage());
        }
        return map;
    }

    @PostMapping("/update")
    public @ResponseBody
    Map<String, Object> update(patrols patrol) throws ParseException {
        Map<String, Object> map = new HashMap<>();
        try{
            patrolService.updateByPrimaryKeySelective(patrol);
            map.put("success", true);
            map.put("msg", "修改成功");
            map.put("patrol",patrol);
        }catch(Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("msg", "修改失败："+ e.getMessage());
        }
        return map;
    }

    @GetMapping(value="/findAllByparentId")
    public @ResponseBody Map<String, Object> findAllByparentId(Integer parentId){
        Map<String, Object> map = new HashMap<>();
        List<patrols> results = patrolService.findAllByparentId(parentId);
        map.put("results", results);
        return map;
    }

    @DeleteMapping("/delete")
    public @ResponseBody Map<String, Object> delete(Integer id){
        Map<String, Object> map = new HashMap<>();
        try{
            patrolService.deleteByPrimaryKey(id);
            map.put("success", true);
            map.put("msg", "删除成功");
        }catch(Exception e){
            map.put("success", false);
            map.put("msg", "添加失败："+ e.getMessage());
        }
        return map;
    }

    @DeleteMapping("/deleteImg")
    public @ResponseBody Map<String, Object> deleteImg(Integer id, String name){
        Map<String, Object> map = new HashMap<>();
        try{
            String realPath = "/home/patrolImgs";
            File file = new File(realPath, name);
            if(file.exists()) file.delete();
            patrolImgService.deleteByPrimaryKey(id);
            map.put("success", true);
            map.put("msg", "删除成功");
        }catch(Exception e){
            map.put("success", false);
            map.put("msg", "添加失败："+ e.getMessage());
        }
        return map;
    }

    @GetMapping("/download")
    public void download(String name, HttpServletResponse response) throws IOException {
        String realPath = "/home/patrolImgs";
        FileInputStream is = new FileInputStream(new File(realPath,name));
        response.setHeader("content-disposition","attachment;fileName="+ URLEncoder.encode(name,"UTF-8"));
        ServletOutputStream os = response.getOutputStream();
        IOUtils.copy(is,os);
        IOUtils.closeQuietly(is);
        IOUtils.closeQuietly(os);
    }

    @GetMapping("/preview")
    public void preview(String name, HttpServletResponse response) throws IOException {
        String realPath = "/home/patrolImgs";
        FileInputStream is = new FileInputStream(new File(realPath,name));
        response.setHeader("content-disposition","inline;fileName="+ URLEncoder.encode(name,"UTF-8"));
        ServletOutputStream os = response.getOutputStream();
        IOUtils.copy(is,os);
        IOUtils.closeQuietly(is);
        IOUtils.closeQuietly(os);
    }

    @GetMapping(value="/findAllImgs")
    public @ResponseBody Map<String, Object> findAllImgs(Integer parentId){
        Map<String, Object> map = new HashMap<>();
        List<patrolImages> results = patrolImgService.findAllImags(parentId);
        map.put("results", results);
        return map;
    }

    @PostMapping("/upload")
    public @ResponseBody
    Map<String,Object> upload(@RequestParam MultipartFile file,  @RequestParam(value="parentId",required=false) Integer parentId) throws IOException {
        Map<String,Object> result=new HashMap<>();
        String oldName = file.getOriginalFilename();
        if(!oldName.endsWith(".jpg")){
            result.put("status","error");
            result.put("msg","文件类型不对");
            return result;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String format = sdf.format(new Date());
        String newFileName = format + oldName;
        System.out.println(newFileName);
        patrolImages patrolImage = new patrolImages();
        patrolImage.setParentid(parentId);
        patrolImage.setName(newFileName);
        patrolImgService.save(patrolImage);
        String realPath = "/home/patrolImgs";
        try{
            file.transferTo(new File(realPath,newFileName));
            result.put("status","success");
            result.put("msg","上传成功");
        }catch (IOException e){
            result.put("status","error");
            result.put("msg","文件类型不对");
            e.printStackTrace();
        }
        return result;
    }
}
