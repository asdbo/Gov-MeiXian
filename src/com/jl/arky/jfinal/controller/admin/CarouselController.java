package com.jl.arky.jfinal.controller.admin;

import java.awt.image.BufferedImage;

import java.io.File;

import javax.imageio.ImageIO;

import com.jfinal.core.Controller;
import com.jfinal.upload.UploadFile;
import com.jl.arky.jfinal.model.CarouselModel;
import com.jl.arky.jfinal.utils.IDUtil;

public class CarouselController extends Controller {
	/*
	 * 根据sortid升序,查询数据库的信息，将其返回页面
	 */
	public void list() {
		setAttr("carouselPage", CarouselModel.dao.find("select * from carousel order by sortid asc"));
		render("index.html");
	}

	/*
	 * 添加
	 */
	public void add() {
		render("add.html");
	}

	/*
	 * 保存
	 */
	public void save() {
		UploadFile uploadFile = getFile("filename", "/images");
		String fileName = this.uploadFile(uploadFile);
		if ("".equals(fileName)) {
			setAttr("msg", "没有添加图片!");// 回显无法保存信息
			render("add.html");
			return;
		}
		String title = getPara("carouselModel.title");
		if ("".equals(title)) {
			setAttr("tmsg", "图片标题不为空!");// 回写无法保存信息
			render("add.html");
			return;
		}
		Boolean flag = getModel(CarouselModel.class).set("link", fileName).save();
		if (flag)
			redirect("/Admin/Carousel/list");
		else
			renderText("保存错误!");
	}

	/*
	 * 修改
	 */
	public void edit() {
		setAttr("carouselModel", CarouselModel.dao.findById(getParaToInt("id")));
		render("edit.html");
	}

	/*
	 * 更新
	 */
	public void update() {
		UploadFile uploadFile = getFile("filename", "/images");
		String fileName = this.uploadFile(uploadFile);
		Boolean flag = false;
		if ("".equals(fileName)) // 判断是否有修改图片上传,如果不修改图片上传
		{
			flag = getModel(CarouselModel.class).update();
		} else {
			CarouselModel carouselModel = (CarouselModel) CarouselModel.dao.findById(getParaToInt("carouselModel.id"));
			String filename = carouselModel.get("link");// 得到图片链接
			String path = getRequest().getServletContext().getRealPath("/");// 得到根目录
			File file = new File(path + filename);
			flag = carouselModel.set("link", fileName).update();
			if (flag)// 删除原来存放的图片
			{
				file.delete();// 删除图片
			}
		}
		if (flag)
			redirect("/Admin/Carousel/list");
		else
			renderText("更新错误!");
	}

	/*
	 * 删除
	 */
	public void delete() {
		String id = getPara("id");
		if (id != null) {
			String[] ids = id.split(",");
			for (int i = 0; i < ids.length; i++) {
				CarouselModel carouselModel = (CarouselModel) CarouselModel.dao.findById(ids[i]);
				String filename = carouselModel.get("link");// 得到图片链接
				String path = getRequest().getServletContext().getRealPath("/");// 得到根目录
				File file = new File(path + filename);
				CarouselModel.dao.deleteById(ids[i]);// 删除数据库信息
				file.delete();// 删除图片
			}
		}
		redirect("/Admin/Carousel/list");
	}

	/*
	 * 判断是否有上传图片,有的话对上传图片重命名,返回上传图片目录
	 */
	public String uploadFile(UploadFile uploadFile) {
		if (uploadFile == null)
			return "";
		else if (!this.isImage(uploadFile.getFile()))// 判断上传文件是否为图片
		{
			return "";
		} else {
			String suffix = uploadFile.getFileName().substring(uploadFile.getFileName().lastIndexOf("."));// 得到上传图片的后缀名
			String fileName = IDUtil.getImageName() + suffix;// 拼接图片名
			String path = getRequest().getServletContext().getRealPath("/");// 得到根目录
			uploadFile.getFile().renameTo(new File(path + "/Public/upload/images/" + fileName));// 重命名图片
			return "/Public/upload/images/" + fileName;
		}
	}

	/*
	 * 判断文件是否为图片
	 */
	public boolean isImage(File file) {
		boolean flag = false;
		try {
			BufferedImage is = ImageIO.read(file);
			if (null == is) {
				return flag;
			}
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
