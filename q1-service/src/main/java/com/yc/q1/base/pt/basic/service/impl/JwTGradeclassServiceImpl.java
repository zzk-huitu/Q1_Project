package com.yc.q1.base.pt.basic.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.base.pt.basic.dao.GradeClassDao;
import com.yc.q1.base.pt.basic.model.Grade;
import com.yc.q1.base.pt.basic.model.GradeClass;
import com.yc.q1.base.pt.basic.service.JwTGradeService;
import com.yc.q1.base.pt.basic.service.JwTGradeclassService;
import com.yc.q1.base.pt.system.model.User;
import com.yc.q1.base.pt.wisdomclass.model.ClassTeacher;
import com.yc.q1.base.pt.wisdomclass.service.JwClassteacherService;
import com.zd.core.model.extjs.ExtDataFilter;
import com.zd.core.model.extjs.QueryResult;
import com.zd.core.service.BaseServiceImpl;
import com.zd.core.util.JsonBuilder;
import com.zd.core.util.ModelUtil;
import com.zd.core.util.StringUtils;

/**
 * 
 * ClassName: JwTGradeclassServiceImpl Function: TODO ADD FUNCTION. Reason: TODO
 * ADD REASON(可选). Description: 学校班级信息实体Service接口实现类. date: 2016-03-13
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class JwTGradeclassServiceImpl extends BaseServiceImpl<GradeClass> implements JwTGradeclassService {

	@Resource
	public void setJwTGradeclassDao(GradeClassDao dao) {
		this.dao = dao;
	}

	@Resource
	private JwTGradeService gradeService;



	@Resource
	private JwClassteacherService classTTeaService;

	/**
	 * 根据班级ID得到年级对象
	 * 
	 * @param classId
	 * @return
	 * @author huangzc
	 */
	@Override
	public Grade findJwTGradeByClassId(String classId) {
		GradeClass jtgClass = this.dao.getByProerties("id", classId);
		if (jtgClass == null)
			return null;
		Grade grade = gradeService.get(jtgClass.getGradeId());

		if (ModelUtil.isNotNull(grade))
			return grade;
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public QueryResult<GradeClass> getGradeClassList(Integer start, Integer limit, String sort, String filter,
			Boolean isDelete, User currentUser) {
		String queryFilter = filter;
		// String jobId = currentUser.getJobId();
		// String jobName = currentUser.getJobName();
		Integer studyYear = currentUser.getStudyYear();
		String smester = currentUser.getSemester();
		StringBuffer sb = new StringBuffer();
		StringBuffer sbClass = new StringBuffer();
		String qrClassId = "";
		ExtDataFilter selfFilter = new ExtDataFilter();
		String propName[] = { "studyYear", "semester", "teacherId", "isDelete" };
		Object[] propValue = { studyYear, smester, currentUser.getId(), 0 };
		
		//（zzk:新版本中取消了年级组长概念）当前人是否年级组长,如果是年级组长则取年级下的所有班级
//		List<JwGradeteacher> gt = gradeTeaService.queryByProerties(propName, propValue);
//		for (JwGradeteacher jgt : gt) {
//			sb.append(jgt.getGraiId() + ",");
//		}
//		if (sb.length() > 0) {
//			sb.deleteCharAt(sb.length() - 1);
//			String st = sb.toString();
//			List<JwTGradeclass> gcList = this.queryByProerties("graiId", st);
//			for (JwTGradeclass gc : gcList) {
//				sbClass.append(gc.getId() + ",");
//			}
//		}
		// 当前人是否班主任，如是则取所在的班级
		List<ClassTeacher> jct = classTTeaService.queryByProerties(propName, propValue);
		if (jct.size() > 0) {
			for (ClassTeacher jt : jct) {
				sbClass.append(jt.getClassId() + ",");
			}
		}
		if (sbClass.length() > 0) {
			sbClass.deleteCharAt(sbClass.length() - 1);
		}
		qrClassId = sbClass.toString();
		selfFilter = (ExtDataFilter) JsonBuilder.getInstance().fromJson(
				"{\"type\":\"string\",\"comparison\":\"in\",\"value\":\"" + qrClassId + "\",\"field\":\"id\"}",
				ExtDataFilter.class);
		if (StringUtils.isNotEmpty(filter)) {
			if (StringUtils.isNotEmpty(qrClassId)) {
				List<ExtDataFilter> listFilters = (List<ExtDataFilter>) JsonBuilder.getInstance().fromJsonArray(filter,
						ExtDataFilter.class);
				listFilters.add(selfFilter);

				queryFilter = JsonBuilder.getInstance().buildObjListToJson((long) listFilters.size(), listFilters,
						false);
			}
		} else {
			if (StringUtils.isNotEmpty(qrClassId))
				queryFilter = "[{\"type\":\"string\",\"comparison\":\"in\",\"value\":\"" + qrClassId
						+ "\",\"field\":\"id\"}]";
		}
		// if (currentUser.getUserName().equals("schooladmin"))
		// queryFilter = "";
		QueryResult<GradeClass> qr = this.queryPageResult(start, limit, sort, queryFilter, true);
		return qr;
	}

	@Override
	public QueryResult<GradeClass> getGradeClassList(Integer start, Integer limit, String sort, String filter,
			Boolean isDelete, User currentUser, String claiId, String claiLevel) {

		String queryFilter = "";
		StringBuffer sbClass = new StringBuffer();
		List<GradeClass> gcList = null;
		switch (claiLevel) {
		case "1": // 查询学校
			gcList = this.queryByProerties("isDelete", 0);
			for (GradeClass gc : gcList) {
				sbClass.append(gc.getId() + ",");
			}
			if (sbClass.length() > 0) {
				sbClass.deleteCharAt(sbClass.length() - 1);
				queryFilter = "[{\"type\":\"string\",\"comparison\":\"in\",\"value\":\"" + sbClass.toString()
						+ "\",\"field\":\"id\"}]";
			}
			break;
		case "2": // 查询年级
			gcList = this.queryByProerties("gradeId", claiId);
			for (GradeClass gc : gcList) {
				sbClass.append(gc.getId() + ",");
			}
			if (sbClass.length() > 0) {
				sbClass.deleteCharAt(sbClass.length() - 1);
				queryFilter = "[{\"type\":\"string\",\"comparison\":\"in\",\"value\":\"" + sbClass.toString()
						+ "\",\"field\":\"id\"}]";
			}
			break;
		case "3": // 查询班级
			queryFilter = "[{\"type\":\"string\",\"comparison\":\"in\",\"value\":\"" + claiId
					+ "\",\"field\":\"id\"}]";
			break;
		}
		QueryResult<GradeClass> qr = this.getGradeClassList(start, limit, sort, queryFilter, true, currentUser);

		return qr;
	}
}