package bsu.rfe.java.teacher.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bsu.rfe.java.teacher.entity.CAPTCHA;

public class ImgServlet extends HttpServlet {
	private static final long serialVersionUID = -6897210875823424399L;
	private byte[] imageContent;
	private String string;

    private byte [] getImgBytes() {
    	//BufferedImage bi=CAPTCHA.getBImage();
    	//CaptchaData cd=CAPTCHA.getCapthca();
    	CAPTCHA captcha = new CAPTCHA();
    	string=captcha.getString();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(captcha.getImage(), "PNG", baos);
        } catch (IOException ex) {
        	ex.printStackTrace();
        }
        return baos.toByteArray();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        imageContent = getImgBytes();
        request.getSession().setAttribute("captcha", string);
        response.setContentType("image/png");
        response.getOutputStream().write(imageContent);
    }
    
}