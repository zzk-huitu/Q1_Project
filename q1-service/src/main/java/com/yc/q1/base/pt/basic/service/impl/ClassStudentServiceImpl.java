package com.yc.q1.base.pt.basic.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.base.pt.basic.dao.ClassStudentDao;
import com.yc.q1.base.pt.basic.service.ClassStudentService;
import com.yc.q1.base.pt.basic.service.GradeClassService;
import com.yc.q1.base.pt.system.service.DepartmentService;
import com.yc.q1.base.redis.service.PrimaryKeyRedisService;
import com.yc.q1.model.base.pt.basic.PtClassStudent;
import com.yc.q1.model.base.pt.basic.PtGradeClass;
import com.yc.q1.model.base.pt.system.PtUser;
import com.zd.core.dao.BaseDao;
import com.zd.core.model.extjs.ExtDataFilter;
import com.zd.core.model.extjs.QueryResult;
import com.zd.core.service.BaseServiceImpl;
import com.zd.core.util.JsonBuilder;
import com.zd.core.util.StringUtils;

/**
 * 
 * ClassName: JwClassstudentServiceImpl Function: TODO ADD FUNCTION. Reason:
 * TODO ADD REASON(可选). Description: 学生分班信息(JW_T_CLASSSTUDENT)实体Service接口实现类.
 * date: 2016-08-25
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class ClassStudentServiceImpl extends BaseServiceImpl<PtClassStudent> implements ClassStudentService {

	@Resource(name = "ClassStudentDao") // 将具体的dao注入进来
	public void setDao(BaseDao<PtClassStudent> dao) {
		super.setDao(dao);
	}

	@Resource
	private DepartmentService orgService;
	
	@Resource
	private GradeClassService classService;

	@SuppressWarnings("unchecked")
	@Override
	public QueryResult<PtClassStudent> getclassStudent(Integer start, Integer limit, String sort, String filter,
			Boolean isDelete, String claiId, PtUser currentUser) {
		String queryFilter = filter;
		String qrClassId = claiId;
		// 当前用户有权限的班级列表
		QueryResult<PtGradeClass> qr = classService.getGradeClassList(0, 0, "", "", true, currentUser);
		List<PtGradeClass> jgClass = qr.getResultList();
		StringBuffer sb = new StringBuffer();
		if (jgClass.size() > 0) {
			for (PtGradeClass jwTGrade : jgClass) {
				sb.append(jwTGrade.getId() + ",");
			}
			sb.deleteCharAt(sb.length() - 1);
		}
		if (StringUtils.isEmpty(claiId) || claiId.equals("2851655E-3390-4B80-B00C-52C7CA62CB39")) {
			// 选择没有选择年级，使用有权限的所有年级
			qrClassId = sb.toString();
		}
		ExtDataFilter selfFilter = (ExtDataFilter) JsonBuilder.getInstance().fromJson(
				"{\"type\":\"string\",\"comparison\":\"in\",\"value\":\"" + qrClassId + "\",\"field\":\"classId\"}",
				ExtDataFilter.class);

		if (StringUtils.isNotEmpty(filter)) {
			List<ExtDataFilter> listFilters = (List<ExtDataFilter>) JsonBuilder.getInstance().fromJsonArray(filter,
					ExtDataFilter.class);
			listFilters.add(selfFilter);

			queryFilter = JsonBuilder.getInstance().buildObjListToJson((long) listFilters.size(), listFilters, false);
		} else {
			queryFilter = "[{\"type\":\"string\",\"comparison\":\"in\",\"value\":\"" + qrClassId
					+ "\",\"field\":\"classId\"}]";
		}
		QueryResult<PtClassStudent> qrReturn = this.queryPageResult(start, limit, sort, queryFilter, true);
		return qrReturn;
	}
}