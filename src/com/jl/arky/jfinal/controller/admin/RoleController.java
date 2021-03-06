package com.jl.arky.jfinal.controller.admin;

import java.util.List;

import org.junit.Test;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.json.FastJson;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jl.arky.jfinal.interceptor.CheckPrivilegeInterceptor;
import com.jl.arky.jfinal.model.AdminModel;
import com.jl.arky.jfinal.model.ChannelModel;
import com.jl.arky.jfinal.model.ChannelRightModel;
import com.jl.arky.jfinal.model.R_P_Model;
import com.jl.arky.jfinal.model.RoleModel;
import com.jl.arky.jfinal.model.U_R_Model;
@Before(CheckPrivilegeInterceptor.class)
public class RoleController extends Controller{
	
     public void list(){
    	 //此处获取角色列表 
 		List<Model> roleList= RoleModel.dao.find("select * from role");
 		setAttr("roleList", roleList);
 		
 		List<ChannelModel> channelList=(List<ChannelModel>) ChannelModel.dao.find("select * from channel where pid = ?",0 );
     	setAttr("topChannelList",channelList);
 		
 		
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
 		String id=getPara("id");
 		System.out.println(id+"-------------====");
		if(id!=null){
			String[] ids = id.split(",");
			for(int i=0;i<ids.length;i++){
				Db.update("delete from role_privilege where roleid = ?", ids[i]);
				RoleModel.dao.deleteById(ids[i]);
				Db.update("delete from admin_role where roleid = ?", ids[i]);
			}
			
		}
 		
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
 		Integer  a[];
		
 		RoleModel upModel=(RoleModel) RoleModel.dao.findFirst("select * from role where id = ?",getParaToInt("id"));
  		setAttr("upModel",upModel);
 		List<Model> privilegeList= upModel.getAllPrivilege();
 		a=new Integer [privilegeList.size()];
  		
  		
  		for(int i=0;i<privilegeList.size();i++){
  		
  		  a[i] = privilegeList.get(i).getInt("privilegeid");
  		  System.out.println(a[i]);
  		}
  		
  		renderJson(a);
 	
 	}
 	/*
 	 * 前往更新角色的信息的链接
 	 */
 	public void toUpdateRoleInfo(){
 		 //获取要修改的角色id，要所要修改的privilege数组
   	 int roleid=getParaToInt("id");
   	 
   	RoleModel roleModel=(RoleModel) RoleModel.dao.findById(roleid);
	setAttr("roleModel", roleModel);
	
	render("update.html");
 	}
 	/*
 	 * 更新角色的信息
 	 */
 	public void updateRoleInfo(){
 		 RoleModel roleModel=getModel(RoleModel.class);
    	
         roleModel.update();
    	 redirect("/Admin/Role/list");
 	}
 	
 	/*
 	 * 修改角色拥有的栏目权限
 	 */
 	public void updateChannelPrivilege(){
 		ChannelRightModel channelRightModel;
 		
 		Integer[] channelNum=this.getParaValuesToInt("channelNum");
 		
       //获取修改角色的id和栏目id
 		int roleid=getParaToInt("roleid");
 		int cid=getParaToInt("cid");
 		
 		

// 		//以及要修改的所有权限（修改栏目的特定权限）
// 		
// 		
// 		//
// 		
 		//删除该角色以前的所有权限
 		Db.update("delete from channel_right where pid = ? and rid = ?",cid,roleid);
 		
 		
 		

 		for (int i=1; i<channelNum.length; i++) {
 			
 		 channelRightModel=	getModel(ChannelRightModel.class, "channelRightModel" + channelNum[i]);
 		channelRightModel.set("rid", roleid);
 		channelRightModel.set("pid", cid);
 		channelRightModel.save();
 		 System.out.println( getModel(ChannelRightModel.class, "channelRightModel" + i));
 			}
// 		for (int i=1; i<4; i++) {
// 			
// 			 System.out.println( getModel(AdminModel.class, "adminModel" + i));
// 			 if(getModel(AdminModel.class, "adminModel" + i).get("roleid")==null){
// 				 System.out.println("hahahh");
// 			}
// 		}
 		renderText("修改成功");
 		
 	}
 	/*
 	 * 修改角色拥有的栏目权限链接
 	 */
    public void toUpdateChannelPrivilege(){
 		//获取要修改角色的id和栏目id
    	int roleid=getParaToInt("roleid");
    	int cid=getParaToInt("cid");
    	setAttr("roleid",roleid);
    	setAttr("cid",cid);
    	
    	
    	
    	//通过cid获取到子栏目然后setAttr（到页面）
    	List<ChannelModel> channelList=(List<ChannelModel>) ChannelModel.dao.find("select * from channel where pid = ?",cid );
     	setAttr("channelList",channelList);
    	
    	//回显当前角色拥有的栏目权限
    	
    	
    	render("edit.html");
    	
 	}
}
