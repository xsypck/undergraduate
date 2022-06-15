package com.springboot.web;

import com.springboot.model.users;
import com.springboot.service.UserService;
//import lombok.extern.slf4j.Slf4j;
import com.springboot.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller//一个控制类可以写两个函数的
@RequestMapping(value = "/user")//所有的路径都加一个user
//@Slf4j日志，就算了吧
@CrossOrigin//跨域问题
public class userController {
    //查询所有
    @Autowired
    private UserService userService;

    @GetMapping(value="/findAll")
    public @ResponseBody Map<String, Object> findAll(){
        Map<String, Object> map = new HashMap<>();
        List<users> results = userService.selectAll();
        map.put("total", 10);
        map.put("totalPage", 1);
        map.put("page", 1);
        map.put("results", results);
        return map;
    }

    @GetMapping(value = "/findLike")
    public @ResponseBody List<users> findLike(String username){
        List<users> results = userService.selectAllLike(username);
        return results;
    }

    @PostMapping("/findOne")
    public @ResponseBody Map<String, Object> findOne(users user){
        Map<String, Object> map = new HashMap<>();
        users result = userService.selectByPrimaryKey(user.getUsername());
        if(result == null){
            map.put("success", false);
            map.put("msg","用户不存在");
            return map;
        };
        String pwdr = result.getPassword();
        String pwdu = user.getPassword();
        if(pwdu.equals(pwdr)){
            map.put("success", true);
            String token = JWTUtils.getToken(user.getUsername());
            map.put("token",token);
            map.put("user",result);
            map.put("msg","登录成功");
        }else{
            map.put("success", false);
            map.put("msg","密码错误");
        }
        return map;
    }

    @PostMapping("/add")
    public @ResponseBody Map<String, Object> add(users user){
        Map<String, Object> map = new HashMap<>();
        try{
            userService.insert(user);
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
    public @ResponseBody Map<String, Object> update(users user){
        Map<String, Object> map = new HashMap<>();
        try{
            userService.updateByPrimaryKey(user);
            map.put("success", true);
            map.put("msg", "更新成功");
        }catch(Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("msg", "更新失败："+ e.getMessage());
        }
        return map;
    }

    @DeleteMapping("/delete")
    public @ResponseBody Map<String, Object> delete(String username){
        Map<String, Object> map = new HashMap<>();
        try{
            userService.deleteByPrimaryKey(username);
            map.put("success", true);
            map.put("msg", "删除成功");
        }catch(Exception e){
            map.put("success", false);
            map.put("msg", "添加失败："+ e.getMessage());
        }
        return map;
    }

}
