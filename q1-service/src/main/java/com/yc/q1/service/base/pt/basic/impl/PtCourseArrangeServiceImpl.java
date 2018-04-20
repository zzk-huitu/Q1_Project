package com.yc.q1.service.base.pt.basic.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.core.dao.BaseDao;
import com.yc.q1.core.model.ImportNotInfo;
import com.yc.q1.core.service.BaseServiceImpl;
import com.yc.q1.core.util.StringUtils;
import com.yc.q1.model.base.pt.basic.PtBaseCourse;
import com.yc.q1.model.base.pt.basic.PtCourseArrange;
import com.yc.q1.model.base.pt.basic.PtCourseTeacher;
import com.yc.q1.model.base.pt.basic.PtGradeClass;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.service.base.pt.basic.PtBaseCourseService;
import com.yc.q1.service.base.pt.basic.PtCourseArrangeService;
import com.yc.q1.service.base.pt.basic.PtCourseTeacherService;
import com.yc.q1.service.base.pt.basic.PtGradeClassService;
import com.yc.q1.service.base.redis.PrimaryKeyRedisService;

/**
 * 
 * ClassName: JwCourseArrangeServiceImpl Function: TODO ADD FUNCTION. Reason:
 * TODO ADD REASON(可选). Description: 排课课程表实体Service接口实现类. date: 2016-08-23
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class PtCourseArrangeServiceImpl extends BaseServiceImpl<PtCourseArrange> implements PtCourseArrangeService {

	@Resource(name = "PtCourseArrangeDao") // 将具体的dao注入进来
	public void setDao(BaseDao<PtCourseArrange> dao) {
		super.setDao(dao);
	}

	@Resource
	private PtGradeClassService jwClassService;

	@Resource
	private PtBaseCourseService jtbService;

	@Resource
	private PtCourseTeacherService courseTeacherService;

	@Resource
	private PrimaryKeyRedisService keyRedisService;

	@Override
	public List<ImportNotInfo> doImportCourse(Map<String, List<String>> gccMap, PtUser currentUser) {

		List<ImportNotInfo> listNotExit = new ArrayList<>();
		ImportNotInfo notExits = null;
		Integer notCount = 1;

		String schoolId = currentUser.getSchoolId();
		String schoolName = currentUser.getSchoolName();
		String andIsDelete = " and isDelete=0 ";
		String hql;

		String doResult = "";
		String title = "";
		String errorLevel = "";
		boolean isError = false;

		for (String key : gccMap.keySet()) {
			title = key;
			doResult = "成功"; // 默认是成功
			isError = false;

			try {

				List<PtCourseArrange> courseArranges = new ArrayList<PtCourseArrange>(); // 排课课程表对象

				// key为年级班级名称
				/*
				 * int gcStrLength = key.length(); String grade =
				 * key.substring(0, 2); String gcName; if (gcStrLength == 4) {
				 * String classNum = key.substring(2, 3); gcName = grade + "(" +
				 * classNum + ")" + "班"; } else { String classNum =
				 * key.substring(2, 4); gcName = grade + "(" + classNum + ")" +
				 * "班"; }
				 */
				String[] keys = key.split("-"); // 使用-作为分割，也可以直接写班级名称（若班级名称格式为
												// 高一（1）班）
				String grade = "";
				String className = "";
				if (keys.length == 2) {
					grade = keys[0];
					className = keys[1];
				} else {
					className = keys[0];
				}

				// 查询此班级信息
				hql = "from PtGradeClass where className='" + className + "'" + andIsDelete;
				List<PtGradeClass> gcList = jwClassService.queryByHql(hql);
				PtGradeClass gc = null;

				if (gcList.size() == 1) {
					gc = gcList.get(0);
				} else {
					if (gcList.size() > 1) {
						isError = true;
						errorLevel = "错误";
						doResult = "系统中存在多个同名的班级：" + className;
					} else {
						isError = true;
						errorLevel = "错误";
						doResult = "系统中不存在此班级：" + className;
					}

					notExits = new ImportNotInfo();
					notExits.setOrderIndex(notCount);
					notExits.setTitle(title);
					notExits.setErrorLevel(errorLevel);
					notExits.setErrorInfo(doResult);

					listNotExit.add(notExits);
					notCount++;
					continue;
				}

				// 把课程信息插入到课程安排表中
				List<String> gccList = gccMap.get(key);
				int index = 0;
				for (int j = 1; j <= gccList.size() / 9; j++) { // 每天9门课，一星期5-7天

					if (isError == true) // 当出错时，再跳出这一层
						break;

					for (int i = 0; i < 9; i++) {
						PtCourseArrange jca; // 每天的课程，都一次性录入到同一条数据中
						if (courseArranges.size() > i) {
							jca = courseArranges.get(i);
						} else {
							jca = new PtCourseArrange();
							courseArranges.add(jca);
						}

						String cousreName = gccList.get(index); // 取出课程
						index++;

						// 查询课程信息
						hql = "from PtCourse where courseName='" + cousreName + "'" + andIsDelete;
						List<PtBaseCourse> baseCourseList = jtbService.queryByHql(hql);
						PtBaseCourse basecourse = null;
						if (baseCourseList.size() == 1) {
							basecourse = baseCourseList.get(0);
						} else {
							if (baseCourseList.size() > 1) {
								isError = true;
								errorLevel = "错误";
								doResult = "系统中存在多个同名的课程：" + cousreName;
								break; // 跳出这一层
							} else {
								isError = true;
								errorLevel = "错误";
								doResult = "系统中不存在此课程信息：" + cousreName;
								break;
							}
						}

						// 查询任课教师信息
						hql = "from PtCourseTeacher where courseId='" + basecourse.getId() + "' and classId='"
								+ gc.getId() + "'" + andIsDelete;

						List<PtCourseTeacher> courseteachers = courseTeacherService.queryByHql(hql);
						String teacherGh = "";
						String teacherName = "";
						String teacherId = "";
						for (PtCourseTeacher courseteacher : courseteachers) {
							teacherId += courseteacher.getTeacherId() + ",";
							teacherGh += courseteacher.getUserNumb() + ",";
							teacherName += courseteacher.getName() + ",";
						}
						if (teacherId.length() > 0) {
							teacherId = StringUtils.trimLast(teacherId);
							teacherGh = StringUtils.trimLast(teacherGh);
							teacherName = StringUtils.trimLast(teacherName);
						}

						jca.setClassName(className);
						jca.setClassId(gc.getId());
						jca.setSections(String.valueOf(i + 1));
						jca.setIsUse(false);
						jca.setIsDelete(0);

						switch (j) {
						case 1:
							jca.setCourseId01(basecourse.getId());
							jca.setCourseName01(basecourse.getCourseName());
							jca.setTeacherId01(teacherId);
							jca.setTeacherNumber01(teacherGh);
							jca.setTeacherName01(teacherName);
							break;
						case 2:
							jca.setCourseId02(basecourse.getId());
							jca.setCourseName02(basecourse.getCourseName());
							jca.setTeacherId02(teacherId);
							jca.setTeacherNumber02(teacherGh);
							jca.setTeacherName02(teacherName);
							break;
						case 3:
							jca.setCourseId03(basecourse.getId());
							jca.setCourseName03(basecourse.getCourseName());
							jca.setTeacherId03(teacherId);
							jca.setTeacherNumber03(teacherGh);
							jca.setTeacherName03(teacherName);
							break;
						case 4:
							jca.setCourseId04(basecourse.getId());
							jca.setCourseName04(basecourse.getCourseName());
							jca.setTeacherId04(teacherId);
							jca.setTeacherNumber04(teacherGh);
							jca.setTeacherName04(teacherName);
							break;
						case 5:
							jca.setCourseId05(basecourse.getId());
							jca.setCourseName05(basecourse.getCourseName());
							jca.setTeacherId05(teacherId);
							jca.setTeacherNumber05(teacherGh);
							jca.setTeacherName05(teacherName);
							break;
						case 6:
							jca.setCourseId06(basecourse.getId());
							jca.setCourseName06(basecourse.getCourseName());
							jca.setTeacherId06(teacherId);
							jca.setTeacherNumber06(teacherGh);
							jca.setTeacherName06(teacherName);
							break;
						case 7:
							jca.setCourseId07(basecourse.getId());
							jca.setCourseName07(basecourse.getCourseName());
							jca.setTeacherId07(teacherId);
							jca.setTeacherNumber07(teacherGh);
							jca.setTeacherName07(teacherName);
							break;
						}
					}

				}

				// 当此班级的课表信息不出现错误时，那就入库
				if (isError == false) {
					for (PtCourseArrange jwCourseArrange : courseArranges) {
						jwCourseArrange.setId(keyRedisService.getId(PtCourseArrange.ModuleType));	//手动设置id
						this.merge(jwCourseArrange);
					}
				}

			} catch (Exception e) {

				errorLevel = "错误";
				doResult = "导入失败；异常信息：" + e.getMessage();
			}

			if (!"成功".equals(doResult)) {
				// List<Map<String, Object>>
				notExits = new ImportNotInfo();
				notExits.setOrderIndex(notCount);
				notExits.setTitle(title);
				notExits.setErrorLevel(errorLevel);
				notExits.setErrorInfo(doResult);

				listNotExit.add(notExits);
				notCount++;
			}
		}

		return listNotExit;
	}

	@Override
	public void doCouseUse(String[] idArr, String[] classIdArr, String[] sectionsArr, String xm) {
		// TODO Auto-generated method stub
		// 将班级下之前启用的课表，设置为不启用
		for (int i = 0; i < idArr.length; i++) {
			this.updateByProperties(new String[] { "classId", "isDelete", "sections" },
					new Object[] { classIdArr[i], 0, sectionsArr[i] },
					new String[] { "isUse", "updateUser", "updateTime" }, new Object[] { false, xm, new Date() });
		}

		// 再设置启用的课表
		this.updateByProperties("id", idArr, new String[] { "isUse", "updateUser", "updateTime" },
				new Object[] { true, xm, new Date() });

	}

}