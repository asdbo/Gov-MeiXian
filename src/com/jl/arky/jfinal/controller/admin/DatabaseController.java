package com.jl.arky.jfinal.controller.admin;

import java.io.BufferedReader;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.jfinal.core.Controller;
import com.jl.arky.jfinal.config.MainConfig;
import com.jl.arky.jfinal.model.DatabaseModel;
import com.jl.arky.jfinal.utils.IDUtil;

public class DatabaseController extends Controller {
	/*
	 * 
	 * 得到"/WEB-INF/back/"下所有的文件集合
	 */
	public List<DatabaseModel> databaseList() {
		File file = new File(this.judeFileExists());
		File[] files = file.listFiles();
		if (files == null) // 是否有文件
		{
			return null;
		}
		if (files.length > 0) {
			List<DatabaseModel> list = new ArrayList<DatabaseModel>();
			String fileName = "";
			for (File f : files) {
				fileName = f.getName().substring(0, f.getName().lastIndexOf("."));// 备份文件名

				DatabaseModel databaseModel = new DatabaseModel();
				databaseModel.setName(fileName);
				databaseModel.setLength(this.formetFileSize(f.length()));
				databaseModel.setDate(fileName.substring(0, 4) + "/" + fileName.substring(4, 6) + "/"
						+ fileName.substring(6, 8) + " " + fileName.substring(8, 10) + ":" + fileName.substring(10, 12)
						+ ":" + fileName.substring(12, 14));

				list.add(databaseModel);
			}
			Collections.sort(list, new Comparator<DatabaseModel>() {
				/*
				 * int compare(DatabaseModel o1, DatabaseModel o2) 返回一个基本类型的整型，
				 * 返回负数表示：o1 大于o2， 返回0 表示：o1和o2相等， 返回正数表示：o1小于o2。
				 */
				public int compare(DatabaseModel o1, DatabaseModel o2) {
					// 按照DatabaseModel的name进行降序排列
					int i = o1.getName().compareTo(o2.getName());
					if (i > 0) {
						return -1;
					}
					if (o1.getName() == o2.getName()) {
						return 0;
					}
					return 1;
				}
			});
			return list;
		} else {
			return null;
		}
	}

	/*
	 * 
	 * 数据库文件列表
	 * 
	 */
	public void list() {
		List<DatabaseModel> list = databaseList();
		if (list == null) {
			renderText("没有备份文件,请先去备份!");
		} else {
			setAttr("databaseList", list);
			render("index.html");
		}
	}

	/*
	 * 
	 * 备份文件页面
	 * 
	 */
	public void add() {
		List<DatabaseModel> list = databaseList();
		if (list != null) {
			setAttr("date", databaseList().get(0).getDate());// 最后备份信息

		} else {
			setAttr("date", "");
		}
		render("add.html");
	}

	/*
	 * 
	 * 保存
	 * 
	 */
	public void save() {
		this.judeFileExists();// 创建文件夹

		backup();// 备份

		redirect("/Admin/Database/list");
	}

	/*
	 * 
	 * 删除
	 * 
	 */
	public void delete() {
		String name = getPara("name");
		System.out.println(name);
		if (name != null) {
			String[] names = name.split(",");
			for (int i = 0; i < names.length; i++) {
				File file = new File(this.path(names[i]));// 得到文件路径
				file.delete();
			}
		}
		redirect("/Admin/Database/list");
	}

	/*
	 * 
	 * 备份
	 * 
	 */
	public void backup() {
		try {
			Runtime rt = Runtime.getRuntime();
			// 调用 mysql 的 cmd:

			Process child = rt.exec(MainConfig.MYSQL_HOME + "mysqldump -u" + MainConfig.DATABASE_USERNAME + " -p"
					+ MainConfig.DATABASE_PASSWORD + " -h" + MainConfig.DATABASE_HOST + " -P" + MainConfig.DATABASE_PORT
					+ " --set-charset=utf8 " + MainConfig.DATABASE_NAME);// 设置导出编码为utf8。这里必须是utf8
			// 把进程执行中的控制台输出信息写入.sql文件，即生成了备份文件。注：如果不对控制台信息进行读出，则会导致进程堵塞无法运行
			InputStream in = child.getInputStream();// 控制台的输出信息作为输入流
			InputStreamReader xx = new InputStreamReader(in, "utf8");// 设置输出流编码为utf8。这里必须是utf8，否则从流中读入的是乱码
			String inStr;
			StringBuffer sb = new StringBuffer("");
			String outStr;
			// 组合控制台输出信息字符串
			BufferedReader br = new BufferedReader(xx);
			while ((inStr = br.readLine()) != null) {
				sb.append(inStr + "\r\n");
			}
			outStr = sb.toString();
			FileOutputStream fout = new FileOutputStream(this.judeFileExists() + IDUtil.getImageName() + ".sql");// 备份生成的sql目标文件保存在WEB-INF/back/下
			OutputStreamWriter writer = new OutputStreamWriter(fout, "utf8");
			writer.write(outStr);
			// 注：这里如果用缓冲方式写入文件的话，会导致中文乱码，用flush()方法则可以避免
			writer.flush();
			// 关闭输入输出流,采用"先开后关"原则
			writer.close();
			fout.close();
			br.close();
			xx.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * 还原 ,还原的时候需要数据库已经建好
	 */
	public void restore() {
		String name = getPara("name");
		String fPath = this.path(name);// 备份文件路径
		try {
			Runtime rt = Runtime.getRuntime();
			// 调用 mysql 的 cmd:
			// rt.exec("create database demo");
			Process child = rt.exec(MainConfig.MYSQL_HOME + "mysql -u" + MainConfig.DATABASE_USERNAME + " -p"
					+ MainConfig.DATABASE_PASSWORD + " -h" + MainConfig.DATABASE_HOST + " -P" + MainConfig.DATABASE_PORT
					+ " " + MainConfig.DATABASE_NAME);
			OutputStream out = child.getOutputStream();// 控制台的输入信息作为输出流

			String inStr;
			StringBuffer sb = new StringBuffer("");
			String outStr;
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fPath), "utf8"));
			while ((inStr = br.readLine()) != null) {
				sb.append(inStr + "\r\n");
			}
			outStr = sb.toString();
			OutputStreamWriter writer = new OutputStreamWriter(out, "utf8");
			writer.write(outStr);
			// 注：这里如果用缓冲方式写入文件的话，会导致中文乱码，用flush()方法则可以避免
			writer.flush();
			// 关闭输入输出流,采用"先开后关"原则
			writer.close();
			br.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		redirect("/Admin/Database/list");
	}

	/*
	 * 文件存放路径
	 */
	public String path(String fileName) {
		return this.judeFileExists() + fileName + ".sql";
	}

	/*
	 * 判断文件夹是否存在,不存在则创建,返回文件夹目录
	 */
	public String judeFileExists() {
		String rootPath = getRequest().getServletContext().getRealPath("/");// 得到根目录

		String filePath = rootPath + "/WEB-INF/back/";
		File file = new File(filePath);
		if (!file.exists() && !file.isDirectory()) {
			file.mkdir();// 创建文件夹

		}
		return filePath;
	}

	/*
	 * 文件大小byte转换
	 */
	public String formetFileSize(long fileSize) {
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileSize < 1024) {
			fileSizeString = df.format((double) fileSize) + "B";
		} else if (fileSize < 1048576) {
			fileSizeString = df.format((double) fileSize / 1024) + "KB";
		} else if (fileSize < 1073741824) {
			fileSizeString = df.format((double) fileSize / 1048576) + "MB";
		} else {
			fileSizeString = df.format((double) fileSize / 1073741824) + "GB";
		}
		return fileSizeString;
	}
}
