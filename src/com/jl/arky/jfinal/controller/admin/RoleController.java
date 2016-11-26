package com.jl.arky.jfinal.controller.admin;

import java.util.List;

import org.junit.Test;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jl.arky.jfinal.interceptor.CheckPrivilegeInterceptor;
import com.jl.arky.jfinal.model.AdminModel;
import com.jl.arky.jfinal.model.R_P_Model;
import com.jl.arky.jfinal.model.RoleModel;
import com.jl.arky.jfinal.model.U_R_Model;
@Before(CheckPrivilegeInterceptor.class)
public class RoleController extends Controller{
	
     public void list(){
    	 //此处获取角色列表 
 		List<Model> roleList= RoleModel.dao.find("select * from role");
 		setAttr("roleList", roleList);
    	 //返回结果给列表
 		render("index.html");
    	 
     }

     public void addRole(){
    	 RoleModel roleModel=getModel(RoleModel.class);
    	 //此处同时获取到角色的权限，并保存角色的权限,维护中间表
    	 
    	 roleModel.save();
    	 R_P_Model rpModel=new R_P_Model();
    	 rpModel.set("roleid",roleModel.get("id"));
    	 rpModel.set("privilegeid",1);
    	 rpModel.save();
    	 redirect("/Admin/Role/list");
     }
     public void toAdd(){
    	 render("add.html");
     }
     /*
      * 到更新页面
      */
     public void toUpdate(){
    	 //此处需要获取角色的id，并且查询到他所有的权限
    	 int id = getParaToInt("id");

  		
  		RoleModel upModel=(RoleModel) RoleModel.dao.findFirst("select * from role where id = ?",id);
  		setAttr("upModel",upModel);
  		
  		List<Model> privilegeList= upModel.getAllPrivilege();
  		setAttr("privilegeList",privilegeList);
  		
  		
  		
    	 render("privilege.html");
     }
     /*
      * 更新角色权限
      */
     public void updateRole(){
    	 //获取要修改的角色id，要所要修改的privilege数组
    	 int roleid=getParaToInt("id");
    	 Integer[] privileges=this.getParaValuesToInt("privilegeIds");
    	 
    	 //删除中间表中原来的记录
    	 Db.update("delete from role_privilege where roleid = ?", roleid);
    	
 		
    	 //重新加入要修改的权限
    	 R_P_Model rpModel=new R_P_Model();
    	 rpModel.set("roleid",roleid);
    	 if(0<privileges.length)
    	 for(int i=0;i<privileges.length;i++)
    	 {
    		 rpModel.set("privilegeid",privileges[i]);
        	 rpModel.save();
    	 }
    	 redirect("/Admin/Role/list");
     }
     /*
      * 删除角色
      */
     public void deleteRoleById(){
    	 //获取要删除的角色id
 		int roleid = getParaToInt("id");
 	 
 		 //删除中间表中原来的记录
    	 Db.update("delete from role_privilege where roleid = ?", roleid);
 	 //删除角色
 		RoleModel.dao.deleteById(roleid);
 		
 		//删除admin_role表中间的数据
 		Db.update("delete from admin_role where roleid = ?", roleid);
 		
 		redirect("/Admin/Role/list");
     }
     
     
     /*

 	 * 校验输入角色是否注册（ajax）

 	 */
 	@Clear
 	public void doGet() {
 		
 		List<Model> roleModel = RoleModel.dao.find("select * from role where rolename=?", getPara("username"));
 		renderJson(roleModel.size());
 	}
 	
 	/*
 	 * 获取更新用户所拥有的权限
 	 */
 	@Clear
	public void getPrivilege(){
 		int a[];
		
 		RoleModel upModel=(RoleModel) RoleModel.dao.findFirst("select * from role where id = ?",getParaToInt("id"));
  		setAttr("upModel",upModel);
 		List<Model> privilegeList= upModel.getAllPrivilege();
 		a=new int[privilegeList.size()];
  		
  		
  		for(int i=0;i<privilegeList.size();i++){
  		
  		  a[i] = privilegeList.get(i).getInt("privilegeid");
  		  System.out.println(a[i]);
  		}
  		
  		renderJson(a);
 	
 	}
     
}
