package com.zd.school.app.wisdomclass.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yc.q1.base.app.pojo.ClassInfoApp;
import com.yc.q1.base.app.pojo.ClassStudentApp;
import com.yc.q1.base.app.pojo.CommonApp;
import com.yc.q1.base.pt.basic.model.Calender;
import com.yc.q1.base.pt.basic.model.CalenderDetail;
import com.yc.q1.base.pt.basic.model.ClassStudent;
import com.yc.q1.base.pt.basic.model.FuncRoomCourse;
import com.yc.q1.base.pt.basic.model.GradeClass;
import com.yc.q1.base.pt.basic.model.InfoTerminal;
import com.yc.q1.base.pt.basic.model.TeacherBaseInfo;
import com.yc.q1.base.pt.basic.service.AttachmentService;
import com.yc.q1.base.pt.basic.service.CalenderService;
import com.yc.q1.base.pt.basic.service.CalenderDetailService;
import com.yc.q1.base.pt.basic.service.CampusService;
import com.yc.q1.base.pt.basic.service.InfoTerminalService;
import com.yc.q1.base.pt.basic.service.ClassStudentService;
import com.yc.q1.base.pt.basic.service.FuncRoomCourseService;
import com.yc.q1.base.pt.basic.service.GradeClassService;
import com.yc.q1.base.pt.basic.service.TeacherBaseInfoService;
import com.yc.q1.base.pt.build.model.RoomInfo;
import com.yc.q1.base.pt.build.service.RoomInfoService;
import com.yc.q1.base.pt.wisdomclass.model.ClassRedFlag;
import com.yc.q1.base.pt.wisdomclass.model.ClassStar;
import com.yc.q1.base.pt.wisdomclass.model.ClassTeacher;
import com.yc.q1.base.pt.wisdomclass.service.ClassMienService;
import com.yc.q1.base.pt.wisdomclass.service.ClassRedFlagService;
import com.yc.q1.base.pt.wisdomclass.service.ClassStarService;
import com.yc.q1.base.pt.wisdomclass.service.ClassTeacherService;
import com.zd.core.controller.core.BaseController;
import com.zd.core.util.DateUtil;
import com.zd.core.util.EntityUtil;
import com.zd.core.util.StringUtils;

@Controller
@RequestMapping("/app/GradeClass")
public class GradeClassAppController extends BaseController<GradeClass> {

	@Value("${virtualFileUrl}")  	//读取在配置文件属性
	private String virtualFileUrl; 	//文件目录虚拟路径
	
	@Resource
	GradeClassService thisService;

	@Resource
	TeacherBaseInfoService teacherService;

	@Resource
	ClassTeacherService classTeacherService;

	@Resource
	ClassStudentService classStudentService;

	@Resource
	RoomInfoService brService;

	// @Resource
	// JwClassRoomAllotService jraService;

	@Resource
	ClassStarService starService;

	@Resource
	ClassRedFlagService flagService;

	@Resource
	ClassMienService elegantService; // service层接口

	@Resource
	AttachmentService baseTAttachmentService;// service层接口

	@Resource
	private InfoTerminalService termService; // 终端设备serice层接口

	@Resource
	private FuncRoomCourseService funcCourseService; // 功能室课表
	@Resource
	private CampusService campusService; // 校区信息
	@Resource
	private CalenderService calendarService; // 校历
	@Resource
	private CalenderDetailService calendarDetailService; // 校历详情


	/**
	 * 获取班级列表信息
	 * @param classId
	 * @return
	 */
	@RequestMapping(value = { "/getGradeClassList" }, method = RequestMethod.GET)
	public @ResponseBody CommonApp<Map<String, Object>> getGradeClassList() {
		CommonApp<Map<String, Object>> info=new CommonApp<>();
		String sql="select a.classId,a.className,b.gradeName from T_PT_GradeClass a join T_PT_Grade b "
				+ " on a.gradeId=b.gradeId where a.isDelete=0 and b.isDelete=0"
				+ " order by b.sectionCode asc,b.gradeCode asc,a.orderIndex asc";
		List<Map<String, Object>> classMap = thisService.queryMapBySql(sql);
		info.setMessage(true);
		info.setMessageInfo("调用成功！");
		info.setList(classMap);
		
		return info;
	}
	
	/**
	 * 获取班级信息
	 * @param classId
	 * @return
	 */
	@RequestMapping(value = { "/getClassInfo" }, method = RequestMethod.GET)
	public @ResponseBody ClassInfoApp getClassInfo(@RequestParam(value = "classId") String classId) {
		
		ClassInfoApp info = new ClassInfoApp();

		GradeClass classInfo = thisService.get(classId);// 班级对象
		if (classInfo == null) {
			info.setMessage(false);
			info.setMessageInfo("没有找到对应的班级！");
			return info;
		}
		
		Map<String,String> sort = new HashMap<>();
		sort.put("category", "asc");
		ClassTeacher calssTeacher = classTeacherService.getByProerties(
				new String[]{"classId","isDelete"},
				new Object[]{classId,0},sort);		//率先读取正班主任
		if (calssTeacher == null) {
			info.setMessage(false);
			info.setMessageInfo("找不到班主任信息！");
			return info; 
		}
		String teacherId = calssTeacher.getTeacherId();
		List<TeacherBaseInfo> teacherList = teacherService.queryByProerties("id", teacherId);
		if (teacherList.isEmpty()) {
			info.setMessage(false);
			info.setMessageInfo("未找到该班级的班主任信息！");
			return info;
		}
		info.setTeacherInfo(teacherList.get(0));
		
		
		Date date = new Date();
		String today = DateUtil.formatDate(date);
		
		//查询班级星级信息
		String hql = "from ClassStar where isDelete=0 and classId='" + classInfo.getId() + "' and beginDate<='"
				+ today + "' and endDate>='" + today + "'";
		List<ClassStar> classstarList = starService.queryByHql(hql);
		if (!classstarList.isEmpty()) {
			ClassStar starInfo = classstarList.get(0);
			info.setClassstarInfo(starInfo);
		}
		
		//查询班级红旗信息(显示最新的红旗)
		hql = "from ClassRedFlag where isDelete=0 and classId='" + classInfo.getId() + "' and beginDate<='" + today
				+ "' and endDate>='" + today + "' order by redFlagType";
		List<ClassRedFlag> classflagList = flagService.queryByHql(hql);
		if (classflagList != null && classflagList.size() > 0) {
			if (classflagList.size() > 1) {
				for (int i = 1; i < classflagList.size(); i++) {
					ClassRedFlag before = classflagList.get(i - 1);
					ClassRedFlag now = classflagList.get(i);
					if (before.getRedFlagType().equals(now.getRedFlagType())) {
						classflagList.remove(before);
					    i--;
					}
				}
			}
			info.setRedflagList(classflagList);
		}

		info.setMessage(true);
		info.setMessageInfo("请求成功！");
		info.setClassInfo(classInfo);
		return info;
	}
	
	/**
	 * 获取班级学生列表
	 * @param termCode	设备终端号
	 * @param classId	班级ID（若设备绑定的房间为功能室，则不需要传入班级ID）
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = { "/getClassStudent" }, method = RequestMethod.GET)
	public @ResponseBody ClassStudentApp getClassstudent(@RequestParam("termCode") String termCode, 
			@RequestParam(value="classId",required=false) String classId) throws ParseException {
		
		ClassStudentApp info = new ClassStudentApp();

		InfoTerminal roomTerm = termService.getByProerties("terminalNo", termCode);
		if (roomTerm == null) {
			info.setMessage(false);
			info.setMessageInfo("没有找到该终端设备！");
			return info;
		}
		RoomInfo roominfo = brService.get(roomTerm.getRoomId());
		if (roominfo == null) {
			info.setMessage(false);
			info.setMessageInfo("没有找到设备对应房间！");
			return info;
		}

		if (roominfo.getRoomType().equals("5")) { // 当为功能室的时候
			
			classId	= null;	//置空
			
			int dayNum = DateUtil.mathWeekDay(new Date());	// 星期参数
			
			String campusId = campusService.getCampusIdByRoom(roominfo);	//获取校区ID
			
			//找到了校区
			if(campusId!=null){
				String[] propName = new String[] { "campusId", "activityState", "isDelete" };
				Object[] propValue = new Object[] { campusId, 1, 0 };
				Calender calender = calendarService.getByProerties(propName, propValue); // 查询出校区启用的作息时间
				
				//找到了作息时间
				if(calender!=null){
					propName = new String[] { "calenderId", "isDelete" };
					propValue = new Object[] { calender.getId(), 0 };
					List<CalenderDetail> calenderDetails = calendarDetailService.queryByProerties(propName, propValue);	//查询出作息时间详细
					
					
					// 根据当前时间取得现在的节次
					String teachTime = "";
					for (CalenderDetail calenderdetail : calenderDetails) {
						String tS = DateUtil.formatDate(calenderdetail.getBeginTime(), "HH:mm:ss");
						if (calenderdetail.getEndTime() != null) {
							String tE = DateUtil.formatDate(calenderdetail.getEndTime(), "HH:mm:ss");
							if (DateUtil.isInZone(DateUtil.getLong(tS), DateUtil.getLong(tE), DateUtil.getCurrentTime())) {
								teachTime = calenderdetail.getSenctionCode(); 
								break;
							}
						}
					}
					
					//获取功能室的课程信息
					propName = new String[] { "roomId", "sections","isDelete" };
					propValue = new Object[] { roomTerm.getRoomId(), teachTime,0};
					FuncRoomCourse funcroomcourses = funcCourseService.getByProerties(propName, propValue);
					
					//最后获取星期N的功能室课程的班级
					if (funcroomcourses != null) {
						classId = EntityUtil.getPropertyValue(funcroomcourses, "classId0" + dayNum) + "";
					}
				}
			}	
			
		} else { // 当为教室的时候

			// 验证班级是否存在
			GradeClass classInfo = thisService.get(classId);
			if (classInfo == null) {
				info.setMessage(false);
				info.setMessageInfo("未找到该班级信息！");
				return info;
			}

		}

		//获取学生信息（暂时不处理请假的人员）
		if (StringUtils.isNotEmpty(classId)) {
		
			//查询班级下的学生信息
			String hql = "from ClassStudent where classId='" + classId + "' and isDelete=0";
			List<ClassStudent> list = classStudentService.queryByHql(hql);
		
			//直接遍历修改各个数据的照片路径（加入虚拟目录名）
			list.stream().forEach(x->x.setPhoto(virtualFileUrl+"/"+x.getPhoto()));
			
//			for (JwClassstudent jwClassstudent : list) {
//				jwClassstudent.setZp(request.getScheme() + "://" + request.getServerName() + ":"
//						+ request.getServerPort() + "/app/JwTGradeclass/download?filename=" + jwClassstudent.getZp());		
//			}
			
			info.setTotalLeaveed(0);
			info.setList(list);
			info.setMessage(true);
			info.setMessageInfo("查询成功");
		} else {
			info.setMessage(false);
			info.setMessageInfo("暂时不需要考勤");
			return info;
		}
		return info;
	}

	
}
