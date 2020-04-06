package com.flowable.springboot.controller;

import com.flowable.springboot.common.ResponseBean;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lichao
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String main(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("root", request.getContextPath());
        return "index.html";
    }


    @PostMapping("/signin")
    @ResponseBody
    public ResponseEntity signin(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("root", request.getContextPath());
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String sessionId = request.getSession().getId();
        // 1.获取Subject
        Subject subject = SecurityUtils.getSubject();
        // 2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        // 3.执行登录方法
        try{
            subject.login(token);

            // 写入session cookie
            Cookie cookie = new Cookie("JSESSIONID", sessionId);
            cookie.setMaxAge(300 * 60);// 设置为300min
            cookie.setPath(request.getContextPath());

            response.addCookie(cookie);
            return ResponseEntity.ok().body(new ResponseBean(200, ""));

        } catch (UnknownAccountException e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(new ResponseBean(500, "用户不存在"));

        } catch (IncorrectCredentialsException e){

            return ResponseEntity.status(500).body(new ResponseBean(500, "密码错误"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ResponseBean(500, "用户名或密码错误"));
        }
    }

    @RequestMapping("/signout")
    @ResponseBody
    public ResponseEntity logout(HttpServletRequest request, HttpServletResponse response){
        Subject subject = SecurityUtils.getSubject();

        Cookie[] cookies = request.getCookies();

        if (null !=cookies) {
            for(Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                    cookie.setValue(null);
                    cookie.setMaxAge(0); // 立即销毁cookie
                    cookie.setPath(request.getContextPath());
                    response.addCookie(cookie);
                    break;
                }
            }
        }
        if (subject != null) {
            subject.logout();
        }

        return ResponseEntity.ok().body(new ResponseBean(200, ""));
    }




}
