package com.springboot.web;


import com.springboot.baiduapi.AuthService;
import com.springboot.model.*;
import com.springboot.service.HistoryImgService;
import com.springboot.service.WwService;
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
import java.text.SimpleDateFormat;
import java.util.*;


@Controller//一个控制类可以写两个函数的
@RequestMapping(value = "/ww")//所有的路径都加一个user
//@Slf4j日志，就算了吧
@CrossOrigin//跨域问题
public class wwController {
    @Autowired
    private WwService wwService;

    @Autowired
    private HistoryImgService historyImgService;

    @Autowired
    private AuthService authService;

    @GetMapping(value="/findAllByparentId")
    public @ResponseBody Map<String, Object> findAllByparentId(Integer parentId){
        Map<String, Object> map = new HashMap<>();
        List<historyImages> results = historyImgService.findAllByparentId(parentId);
        map.put("results", results);
        return map;
    }

    @GetMapping(value="/getToken")
    public @ResponseBody Map<String, Object> getToken() {
        Map<String, Object> map = new HashMap<>();
        String token = authService.getAuth();
        map.put("token", token);
        return map;
    }

    @DeleteMapping("/deleteImg")
    public @ResponseBody Map<String, Object> deleteImg(Integer id, String name){
        Map<String, Object> map = new HashMap<>();
        try{
            String realPath = "/home/historyImgs";
            File file = new File(realPath, name);
            if(file.exists()) file.delete();
            historyImgService.deleteByPrimaryKey(id);
            map.put("success", true);
            map.put("msg", "删除成功");
        }catch(Exception e){
            map.put("success", false);
            map.put("msg", "添加失败："+ e.getMessage());
        }
        return map;
    }

    @GetMapping("/preview")
    public void preview(String name, HttpServletResponse response) throws IOException {
        String realPath = "/home/historyImgs";
        FileInputStream is = new FileInputStream(new File(realPath,name));
        response.setHeader("content-disposition","inline;fileName="+ URLEncoder.encode(name,"UTF-8"));
        ServletOutputStream os = response.getOutputStream();
        IOUtils.copy(is,os);
        IOUtils.closeQuietly(is);
        IOUtils.closeQuietly(os);
    }


    @PostMapping("/upload")
    public @ResponseBody
    Map<String,Object> upload(@RequestParam MultipartFile file, @RequestParam(value="parentId",required=false) Integer parentId) throws IOException {
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
        historyImages historyImage = new historyImages();
        historyImage.setParentid(parentId);
        historyImage.setName(newFileName);
        historyImgService.save(historyImage);
        String realPath = "/home/historyImgs";
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


    @GetMapping(value="/findAll")
    public @ResponseBody
    Map<String, Object> findAll(){
        Map<String, Object> map = new HashMap<>();
        List<antiques> results = wwService.selectAll();
        Integer num = results.size();
        map.put("results", results);
        map.put("num",num);
        return map;
    }

    @GetMapping("/find")
    public @ResponseBody Map<String, Object> find(String wwname){
        Map<String, Object> map = new HashMap<>();
        try{
            List<antiques> results = wwService.findLike(wwname,"","","","","","");
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

    @GetMapping("/findOneByNum")
    public @ResponseBody
    antiques findOneByNum(String serialnum){
        return wwService.selectByNum(serialnum);
    }

    @GetMapping("/findOneByPk")
    public @ResponseBody
    antiques findOne(Integer id){
        return wwService.selectByPrimaryKey(id);
    }


    @PostMapping("/add")
    public @ResponseBody Map<String, Object> add(antiques antique){
        Map<String, Object> map = new HashMap<>();
        try{
            wwService.insert(antique);
            map.put("success", true);
            map.put("msg", "添加成功");
        }catch(Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("msg", "添加失败："+ e.getMessage());
        }
        return map;
    }



    @PostMapping("/update")
    public @ResponseBody Map<String, Object> update(antiques antique){
        Map<String, Object> map = new HashMap<>();
        try{
            wwService.updateByPrimaryKey(antique);
            map.put("success", true);
            map.put("msg", "更新成功");
        }catch(Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("msg", "更新失败："+ e.getMessage());
        }
        return map;
    }

    @GetMapping("/findLike")
    public @ResponseBody Map<String, Object> findLike(String wwname,String wwlocation1,String wwlocation2,String wwlocation3,String wwyear,String protectionlevel,String wwcategory){
        Map<String, Object> map = new HashMap<>();
        try{
            List<antiques> results = wwService.findLike(wwname,wwlocation1,wwlocation2,wwlocation3,wwyear,protectionlevel,wwcategory);
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

    @DeleteMapping("/delete")
    public @ResponseBody Map<String, Object> delete(Integer id){
        Map<String, Object> map = new HashMap<>();
        try{
            wwService.deleteByPrimaryKey(id);
            map.put("success", true);
            map.put("msg", "删除成功");
        }catch(Exception e){
            map.put("success", false);
            map.put("msg", "添加失败："+ e.getMessage());
        }
        return map;
    }
}
