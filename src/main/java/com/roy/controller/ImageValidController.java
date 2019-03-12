package com.roy.controller;

import com.roy.model.RespBean;
import com.roy.model.RespResult;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@Controller
@RequestMapping("/ImageValidController")
public class ImageValidController extends HttpServlet {

    @GetMapping("/valid/{validate}")
    @ResponseBody
    public RespResult valid(@PathVariable(name = "validate")String valid, HttpSession session){

        String isValid = (String)session.getAttribute("strRandom");
        System.out.println(valid+""+isValid);
        if((valid.toUpperCase()).equals((isValid.toUpperCase()))){
                return new RespResult("success", "校验成功!");
            } else {
                return new RespResult("fail", "验证码不正确!");
            }

    }

    @RequestMapping("/getImage")
    public void getImage(HttpSession session, HttpServletResponse response)
            throws IOException {
        response.setContentType("image/jpeg");
        // 防止浏览器缓冲
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        // 在内存中创建图象
        int width=65,height=40;
        BufferedImage bi=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        // 获取图形上下文
        Graphics g=bi.getGraphics();
        //生成随机类
        Random random = new Random();
        // 设定背景色
        g.setColor(getRandColor(200, 255));
        g.fillRect(0, 0, width,height);

        //画边框
        //g.setColor(new Color());
        //g.drawRect(0,0,width-1,height-1);
        // 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
        g.setColor(getRandColor(160,200));
        for (int i=0;i<155;i++)
        {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x,y,x+xl,y+yl);
        }
        // 取随机产生的认证码(4位字符)
        String strRandom="";
        String generateRandom="02345678abcdefghjkmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ";
        for(int i=0;i<4;i++){
            int intRandom=random.nextInt(55);
            String numRandom=generateRandom.charAt(intRandom)+"";
            strRandom+=numRandom;
            // 将认证码显示到图象中
            g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));//调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
            //设定字体
            g.setFont(new Font("Time NewRoman",Font.BOLD,18+random.nextInt(6)));
            g.drawString(numRandom,6+13*i, 16);
        }
        System.out.println(strRandom);

        // 将认证码存入SESSION
        session.setAttribute("strRandom", strRandom);
        //编码输出
        ServletOutputStream sos=response.getOutputStream();
        JPEGImageEncoder encoder= JPEGCodec.createJPEGEncoder(sos);
        encoder.encode(bi);
        // 释放图形上下文
        g.dispose();
        sos.close();
        bi=null;
    }
    //给定范围获得随机颜色
    public Color getRandColor(int fc,int bc){
        Random random = new Random();
        if(fc>255) fc=255;
        if(bc>255) bc=255;
        int r=fc+random.nextInt(bc-fc);
        int g=fc+random.nextInt(bc-fc);
        int b=fc+random.nextInt(bc-fc);
        return new Color(r,g,b);
    }


}