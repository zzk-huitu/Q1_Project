
package com.yc.q1.controller.system;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.q1.controller.base.FrameWorkController;
import com.yc.q1.core.constant.Constant;
import com.yc.q1.core.constant.StatuVeriable;
import com.yc.q1.core.model.extjs.QueryResult;
import com.yc.q1.core.util.BeanUtils;
import com.yc.q1.core.util.StringUtils;
import com.yc.q1.model.base.pt.system.PtPermission;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.service.base.pt.system.PtPermissionService;

/**
 * 
 * ClassName: BaseTPerimissonController Function: TODO ADD FUNCTION. Reason:
 * TODO ADD REASON(可选). Description: 角色菜单权限表实体Controller（同一个菜单只会生成一条数据，可分配给多个角色可用）. date: 2016-07-17
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Controller
@RequestMapping("/PtPermission")
public class PtPermissionController extends FrameWorkController<PtPermission> implements Constant {

    @Resource
    PtPermissionService thisService; // service层接口
    
    /**
     * list查询
     * @Title: list
     * @Description: TODO
     * @param @param entity 实体类
     * @param @param request
     * @param @param response
     * @param @throws IOException    设定参数
     * @return void    返回类型
     * @throws
    */
   @RequestMapping(value = { "/list" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET,
           org.springframework.web.bind.annotation.RequestMethod.POST })
   public void list( HttpServletRequest request, HttpServletResponse response)
           throws IOException {
       String strData = ""; // 返回给js的数据
       QueryResult<PtPermission> qr = thisService.queryPageResult(super.start(request), super.limit(request),
               super.sort(request), super.filter(request), true);

       strData = jsonBuilder.buildObjListToJson(qr.getTotalCount(), qr.getResultList(), true);// 处理数据
       writeJSON(response, strData);// 返回数据
   }

   /**
    * 
     * @Title: 增加新实体信息至数据库
     * @Description: TODO
     * @param @param SysDatapermission 实体类
     * @param @param request
     * @param @param response
     * @param @throws IOException    设定参数
     * @return void    返回类型
     * @throws
    */
   @RequestMapping("/doAdd")
   public void doAdd(PtPermission entity, HttpServletRequest request, HttpServletResponse response)
           throws IOException, IllegalAccessException, InvocationTargetException {
       
		//此处为放在入库前的一些检查的代码，如唯一校验等
		
		//获取当前操作用户
		String userCh = "超级管理员";
       PtUser currentUser = getCurrentSysUser();
       if (currentUser != null)
           userCh = currentUser.getId();

       PtPermission perEntity = new PtPermission();
		BeanUtils.copyPropertiesExceptNull(entity, perEntity);
		// 生成默认的orderindex
		//如果界面有了排序号的输入，则不需要取默认的了
       Integer orderIndex = thisService.getDefaultOrderIndex(entity);
		entity.setOrderIndex(orderIndex);//排序
		
		//增加时要设置创建人
       entity.setCreateUser(userCh); //创建人
       		
		//持久化到数据库
		entity = thisService.merge(entity);
		
		//返回实体到前端界面
       writeJSON(response, jsonBuilder.returnSuccessJson(jsonBuilder.toJson(entity)));
   }

   /**
     * doDelete
     * @Title: 逻辑删除指定的数据
     * @Description: TODO
     * @param @param request
     * @param @param response
     * @param @throws IOException    设定参数
     * @return void    返回类型
     * @throws
    */
   @RequestMapping("/doDelete")
   public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
       String delIds = request.getParameter("ids");
       if (StringUtils.isEmpty(delIds)) {
           writeJSON(response, jsonBuilder.returnSuccessJson("'没有传入删除主键'"));
           return;
       } else {
           PtUser currentUser = getCurrentSysUser();
           boolean flag = thisService.doLogicDelOrRestore(delIds, StatuVeriable.ISDELETE,currentUser.getId());
           if (flag) {
               writeJSON(response, jsonBuilder.returnSuccessJson("'删除成功'"));
           } else {
               writeJSON(response, jsonBuilder.returnFailureJson("'删除失败'"));
           }
       }
   }

   /**
    * doUpdate编辑记录
    * @Title: doUpdate
    * @Description: TODO
    * @param @param SysDatapermission
    * @param @param request
    * @param @param response
    * @param @throws IOException    设定参数
    * @return void    返回类型
    * @throws
   */
   @RequestMapping("/doUpdate")
   public void doUpdate(PtPermission entity, HttpServletRequest request, HttpServletResponse response)
           throws IOException, IllegalAccessException, InvocationTargetException {
		
		//入库前检查代码
		
		//获取当前的操作用户
       String userCh = "超级管理员";
       PtUser currentUser = getCurrentSysUser();
       if (currentUser != null)
           userCh = currentUser.getId();
			
			
       //先拿到已持久化的实体
		//entity.getSchoolId()要自己修改成对应的获取主键的方法
       PtPermission perEntity = thisService.get(entity.getId());

       //将entity中不为空的字段动态加入到perEntity中去。
       
       BeanUtils.copyPropertiesExceptNull(perEntity,entity);
      
       perEntity.setUpdateTime(new Date()); //设置修改时间
       perEntity.setUpdateUser(userCh); //设置修改人的中文名
       entity = thisService.merge(perEntity);//执行修改方法

       writeJSON(response, jsonBuilder.returnSuccessJson(jsonBuilder.toJson(perEntity)));

   }
   
}