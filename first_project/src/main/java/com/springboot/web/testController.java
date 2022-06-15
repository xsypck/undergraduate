package com.springboot.web;

import com.springboot.model.antiques;
import com.springboot.model.healthExaminationImages;
import com.springboot.model.healthExaminations;
import com.springboot.service.TestImgService;
import com.springboot.service.TestService;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller//一个控制类可以写两个函数的
@RequestMapping(value = "/test")//所有的路径都加一个user
//@Slf4j日志，就算了吧
@CrossOrigin//跨域问题
public class testController {
    @Autowired
    private TestService testService;

    @Autowired
    private WwService wwService;

    @Autowired
    private TestImgService testImgService;

    @PostMapping("/add")
    public @ResponseBody
    Map<String, Object> add(healthExaminations test) throws ParseException {
        System.out.println(test.getWidth());
        System.out.println(test.getId());
        System.out.println(test.getHeight());
        Map<String, Object> map = new HashMap<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String sDate = simpleDateFormat.format(new Date());
        test.setDate(sDate);
        try{
            testService.insert(test);
            map.put("success", true);
            map.put("msg", "添加成功");
            map.put("test",test);
        }catch(Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("msg", "添加失败："+ e.getMessage());
        }
        return map;
    }

    @PostMapping("/update")
    public @ResponseBody
    Map<String, Object> update(healthExaminations test) throws ParseException {
        Map<String, Object> map = new HashMap<>();
        try{
            testService.updateByPrimaryKeySelective(test);
            map.put("success", true);
            map.put("msg", "修改成功");
            map.put("test",test);
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
        List<healthExaminations> results = testService.findAllByparentId(parentId);
        map.put("results", results);
        return map;
    }

    @GetMapping("/findLike")
    public @ResponseBody Map<String, Object> findLike(String date,Integer parentId){
        Map<String, Object> map = new HashMap<>();
        try{
            List<healthExaminations> results = testService.findLike(date,parentId);
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

    @GetMapping(value="/getStatus")
    public @ResponseBody Map<String, Object> getStatus(){
        int best = 0;
        int good = 0;
        int normal = 0;
        int bad = 0;
        int zero = 0;
        Map<String, Object> map = new HashMap<>();
        List<antiques> all = wwService.selectAll();
        for(int i = 0; i <all.size();i++){
            int id = testService.findNewestId(all.get(i).getWwid());
            if(id == 0) {
                zero++;
            }else{
                healthExaminations one = testService.selectByPrimaryKey(id);
                String status = one.getStatus();
                if(status.equals("优")){
                    best++;
                }else if(status.equals("良好")){
                    good++;
                }else if(status.equals("一般")){
                    normal++;
                }else if(status.equals("差")){
                    bad++;
                }
            }
        }
        map.put("best", best);
        map.put("good", good);
        map.put("normal", normal);
        map.put("bad", bad);
        map.put("zero", zero);
        return map;
    }

    @DeleteMapping("/delete")
    public @ResponseBody Map<String, Object> delete(Integer id){
        Map<String, Object> map = new HashMap<>();
        try{
            testService.deleteByPrimaryKey(id);
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
            String realPath = "/home/testImgs";
            File file = new File(realPath, name);
            if(file.exists()) file.delete();
            testImgService.deleteByPrimaryKey(id);
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
        String realPath = "/home/testImgs";
        FileInputStream is = new FileInputStream(new File(realPath,name));
        response.setHeader("content-disposition","attachment;fileName="+ URLEncoder.encode(name,"UTF-8"));
        ServletOutputStream os = response.getOutputStream();
        IOUtils.copy(is,os);
        IOUtils.closeQuietly(is);
        IOUtils.closeQuietly(os);
    }

    @GetMapping("/preview")
    public void preview(String name, HttpServletResponse response) throws IOException {
        String realPath = "/home/testImgs";
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
        List<healthExaminationImages> results = testImgService.findAllImags(parentId);
        map.put("results", results);
        return map;
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
        healthExaminationImages testImage = new healthExaminationImages();
        testImage.setParentid(parentId);
        testImage.setName(newFileName);
        testImgService.save(testImage);
        String realPath = "/home/testImgs";
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
