package com.springboot.web;

import com.springboot.model.antiques;
import com.springboot.model.fourProfiles;
import com.springboot.service.FourService;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller//一个控制类可以写两个函数的
@RequestMapping(value = "/four")//所有的路径都加一个user
//@Slf4j日志，就算了吧
@CrossOrigin//跨域问题
public class fourProfilesController{
    @Autowired
    private FourService fourService;

    @Autowired
    private WwService wwService;

    @PostMapping("/upload")
    public @ResponseBody
    Map<String,Object> upload(@RequestParam MultipartFile file, @RequestParam(value="model",required=false) String model, @RequestParam(value="parentId",required=false) Integer parentId) throws IOException {
        Map<String,Object> result=new HashMap<>();
        String oldName = file.getOriginalFilename();
        if(!oldName.endsWith(".pdf")){
            result.put("status","error");
            result.put("msg","文件类型不对");
            return result;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String format = sdf.format(new Date());
        String newFileName = format + oldName;
        System.out.println(newFileName);
        fourProfiles fourprofile = new fourProfiles();
        fourprofile.setModle(model);
        fourprofile.setParentid(parentId);
        fourprofile.setName(newFileName);
        fourService.save(fourprofile);
        String realPath = "/home/files";
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
    @GetMapping(value="/getFour")
    public @ResponseBody Map<String, Object> getFour(){
        int zero = 0;
        int one = 0;
        int two = 0;
        int three = 0;
        int four = 0;
        Map<String, Object> map = new HashMap<>();
        List<antiques> all = wwService.selectAll();
        for(int i = 0; i <all.size();i++){
            int num = fourService.getFour(all.get(i).getWwid());
            if(num == 0){
                zero++;
            }else if(num == 1){
                one++;
            }else if(num == 2){
                two++;
            }else if(num == 3){
                three++;
            }else if(num == 4){
                four++;
            }
        }
        map.put("one", one);
        map.put("two", two);
        map.put("three", three);
        map.put("four", four);
        map.put("zero", zero);
        return map;
    }

    @GetMapping(value="/findModel")
    public @ResponseBody Map<String, Object> findModel(String model){
        Map<String, Object> map = new HashMap<>();
        List<fourProfiles> results = fourService.findModel(model);
        map.put("per", results.size());
        return map;
    }

    @GetMapping(value="/findAllByModel")
    public @ResponseBody Map<String, Object> findAllByModel(Integer parentId){
        Map<String, Object> map = new HashMap<>();
        List<fourProfiles> model1 = fourService.findAllByModel(parentId,"1");
        map.put("model1", model1);
        List<fourProfiles> model2 = fourService.findAllByModel(parentId,"2");
        map.put("model2", model2);
        List<fourProfiles> model3 = fourService.findAllByModel(parentId,"3");
        map.put("model3", model3);
        List<fourProfiles> model4 = fourService.findAllByModel(parentId,"4");
        map.put("model4", model4);
        return map;
    }

    @DeleteMapping("/delete")
    public @ResponseBody Map<String, Object> delete(Integer id, String name){
        Map<String, Object> map = new HashMap<>();
        try{
            String realPath = "/home/files";
            File file = new File(realPath, name);
            if(file.exists()) file.delete();
            fourService.deleteByPrimaryKey(id);
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
        String realPath = "/home/files";
        FileInputStream is = new FileInputStream(new File(realPath,name));
        response.setHeader("content-disposition","attachment;fileName="+ URLEncoder.encode(name,"UTF-8"));
        ServletOutputStream os = response.getOutputStream();
        IOUtils.copy(is,os);
        IOUtils.closeQuietly(is);
        IOUtils.closeQuietly(os);
    }

    @GetMapping("/preview")
    public void preview(String name, HttpServletResponse response) throws IOException {
        String realPath = "/home/files";
        FileInputStream is = new FileInputStream(new File(realPath,name));
        response.setHeader("content-disposition","inline;fileName="+ URLEncoder.encode(name,"UTF-8"));
        ServletOutputStream os = response.getOutputStream();
        IOUtils.copy(is,os);
        IOUtils.closeQuietly(is);
        IOUtils.closeQuietly(os);
    }
}
