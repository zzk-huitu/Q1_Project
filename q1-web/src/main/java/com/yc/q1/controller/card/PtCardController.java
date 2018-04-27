package com.yc.q1.controller.card;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.q1.controller.base.FrameWorkController;
import com.yc.q1.core.model.extjs.QueryResult;
import com.yc.q1.model.base.pt.basic.PtGradeClass;
import com.yc.q1.model.base.pt.basic.PtStudentBaseInfo;
import com.yc.q1.model.base.pt.card.PtCard;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.service.base.pt.basic.PtGradeClassService;
import com.yc.q1.service.base.pt.basic.PtStudentBaseInfoService;
import com.yc.q1.service.base.pt.card.PtCardService;
import com.yc.q1.service.base.pt.system.PtUserService;
import com.yc.q1.service.base.redis.PrimaryKeyRedisService;

/**
 * 
 * ClassName: BaseTAttachmentController Function: TODO ADD FUNCTION. Reason:
 * TODO ADD REASON(可选). Description: 公共附件信息表实体Controller. date: 2016-07-06
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Controller
@RequestMapping("/PtCard")
public class PtCardController extends FrameWorkController<PtCard> {

	@Resource
	PtCardService thisService; // service层接口

	@Resource
	PtUserService userService; // service层接口

	@Resource
	PtGradeClassService gradeClassService; // service层接口

	@Resource
	PtStudentBaseInfoService studentBaseInfoService; // service层接口

	@Resource
	private PrimaryKeyRedisService keyRedisService;

	/**
	 * 标准的查询列表功能
	 * 
	 * @param entity
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = { "/list" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void list(PtCard entity, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String strData = ""; // 返回给js的数据
		QueryResult<PtCard> qr = thisService.queryPageResult(super.start(request), super.limit(request),
				super.sort(request), super.filter(request), false);
		strData = jsonBuilder.buildObjListToJson(qr.getTotalCount(), qr.getResultList(), true);// 处理数据
		writeJSON(response, strData);// 返回数据
	}

	@RequestMapping(value = { "/userList" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void userList(PtUser entity, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String strData = ""; // 返回给js的数据
		String deptId = request.getParameter("deptId");
		PtUser currentUser = getCurrentSysUser();
		QueryResult<PtUser> qr = userService.getDeptUser(super.start(request), super.limit(request),
				super.sort(request), super.filter(request), true, deptId, currentUser);
		PtGradeClass gc = gradeClassService.get(deptId);
		if (gc != null) {
			QueryResult<PtStudentBaseInfo> qr1 = studentBaseInfoService.getStudentList(super.start(request),
					super.limit(request), super.sort(request), super.filter(request), true, deptId, currentUser);
			qr.getResultList().addAll(qr1.getResultList());
			qr.setTotalCount(qr.getTotalCount() + qr1.getTotalCount());
		}
		strData = jsonBuilder.buildObjListToJson(qr.getTotalCount(), qr.getResultList(), true);// 处理数据
		writeJSON(response, strData);// 返回数据
	}

}
