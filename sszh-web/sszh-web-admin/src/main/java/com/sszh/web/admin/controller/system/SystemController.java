package com.sszh.web.admin.controller.system;

import com.sszh.core.result.JSONResult;
import com.sszh.web.admin.cache.AdminBaseCache;
import com.sszh.web.admin.cache.AdminCacheFactory;
import com.sszh.web.admin.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Date;
import java.util.Random;

/**
 * 系统主控制器
 */
@RestController
@RequestMapping("/system")
public class SystemController extends BaseController {
    
    @Autowired
    private AdminCacheFactory adminCacheFactory;

    /**
     * 获取当前服务器时间
     * @return
     */
    @RequestMapping(value = "/syncSystemTime", method = RequestMethod.GET)
    @ResponseBody
    public JSONResult<Long> syncSystemTime() {
        return JSONResult.newSuccessResult(new Date().getTime());
    }


    /**
     * 获取图片验证码（登陆）
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/getCheckCode", method = RequestMethod.GET)
    public void getCheckCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 设置响应头 Content-type类型  
        response.setContentType("image/jpeg");
        OutputStream os = response.getOutputStream();
        int width = 83, height = 30;
        // 建立指定宽、高和BufferedImage对象  
        BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();       // 该画笔画在image上  
        Color c = g.getColor();                 // 保存当前画笔的颜色
        //填充矩形
        g.fillRect(0, 0, width, height);

        char[] ch = "abcdefghjkmnpqrstuvwxyz2345678901".toCharArray(); // 随即产生的字符串 不包括 i l(小写L) o（小写O） 1（数字1）0(数字0)  
        int length = ch.length; // 随即字符串的长度  
        String vCode = ""; // 保存随即产生的字符串  
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            // 设置字体  
            g.setFont(getFont());
            // 随即生成0-9的数字  
            String rand = Character.toString(ch[random.nextInt(length)]);
            vCode += rand;
            // 设置随机颜色  
            g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            g.drawString(rand, 20 * i + 6, 25);
        }
        //产生随即干扰点  
        for (int i = 0; i < 20; i++) {
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            g.drawOval(x1, y1, 2, 2);
        }
        // 将画笔的颜色再设置回去  
        g.setColor(c);
        // 释放此图形的上下文以及它使用的所有系统资源。
        g.dispose();
        //将验证码记录到REDIS
        adminCacheFactory.getSystemCache().setYanZhengMa(request.getSession().getId(), vCode);
        // 输出图像到页面  
        ImageIO.write(image, "JPEG", os);
    }


    //产生随即的字体  
    private Font getFont() {
        //创建random对象用来生成随机数
        Random random = new Random();
        //创建字体数组，用来装不同的字体的Font对象
        Font font[] = new Font[5];
        font[0] = new Font("Ravie", Font.PLAIN, 24);
        font[1] = new Font("Antique Olive Compact", Font.PLAIN, 24);
        font[2] = new Font("Forte", Font.PLAIN, 24);
        font[3] = new Font("Wide Latin", Font.PLAIN, 24);
        font[4] = new Font("Gill Sans Ultra Bold", Font.PLAIN, 24);
        return font[random.nextInt(5)];
    }
    
}
