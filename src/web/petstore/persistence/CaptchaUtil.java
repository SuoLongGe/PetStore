package web.petstore.persistence;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class CaptchaUtil {

    private String RandomString;
    public  BufferedImage generateCaptcha(int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();

        // 设置背景色
        g.setColor(Color.black);
        g.fillRect(5, 6, width, height);

        // 生成验证码字符串
        String captcha = generateRandomString(4);

        // 在图像上绘制验证码
        g.setFont(new Font("Serif", Font.BOLD, 30));
        g.setColor(Color.WHITE);
        g.drawString(captcha, 10, 40);

        // 释放图形上下文
        g.dispose();

        return image;
    }

    public  String generateRandomString(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder captcha = new StringBuilder();
        for (int i = 0; i < length; i++) {
            captcha.append(chars.charAt(random.nextInt(chars.length())));
        }
        RandomString=captcha.toString();
        return captcha.toString();
    }

    public String getRandomString(){
        return RandomString;
    }
}
