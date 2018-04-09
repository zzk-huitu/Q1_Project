package com.zd.school.app.wisdomclass.controller;



import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zd.core.constant.Constant;
import com.zd.core.controller.core.FrameWorkController;
import com.zd.core.util.DateUtil;
import com.zd.core.util.ModelUtil;
import com.zd.core.util.SortListUtil;
import com.zd.core.util.StringUtils;
import com.zd.school.build.define.model.FuncRoomDefine;
import com.zd.school.build.define.model.RoomInfo;
import com.zd.school.jw.arrangecourse.model.CourseArrange;
import com.zd.school.jw.arrangecourse.model.FuncRoomCourse;
import com.zd.school.jw.arrangecourse.service.JwCourseArrangeService;
import com.zd.school.jw.arrangecourse.service.JwFuncroomcourseService;
import com.zd.school.jw.ecc.model.AttenceRule;
import com.zd.school.jw.eduresources.model.Calender;
import com.zd.school.jw.eduresources.model.CalenderDetail;
import com.zd.school.jw.eduresources.model.GradeClass;
import com.zd.school.jw.eduresources.service.JwTGradeclassService;
import com.zd.school.jw.model.app.JKCourse;
import com.zd.school.jw.model.app.JKCourseToDayArray;
import com.zd.school.jw.model.app.CourseArrangeApp;
import com.zd.school.oa.terminal.model.InfoTerminal;
import com.zd.school.plartform.baseset.service.BaseCalenderService;
import com.zd.school.plartform.baseset.service.BaseCalenderdetailService;
import com.zd.school.plartform.baseset.service.BaseCampusService;
import com.zd.school.plartform.baseset.service.BaseFuncRoomDefineService;
import com.zd.school.plartform.baseset.service.BaseInfotermService;
import com.zd.school.plartform.baseset.service.BaseRoominfoService;
import com.zd.school.wisdomclass.ecc.service.JwCheckruleService;

@Controller
@RequestMapping("/app/CourseArrange")
public class CourseArrangeAppController extends FrameWorkController<CourseArrange> implements Constant {

	@Resource
	JwCourseArrangeService thisService; // service层接口。。。

	@Resource
	JwTGradeclassService classService;

	@Resource
	BaseCalenderService canderService;

	@Resource
	BaseCalenderdetailService canderDetailService;

	@Resource
	BaseRoominfoService brService;

	@Resource
	JwFuncroomcourseService funcroomService;

	@Resource
	BaseFuncRoomDefineService bFuncRoomDefineService;

	@Resource
	private BaseInfotermService termService; // 终端设备serice层接口

	@Resource
	private JwCheckruleService checkruleService;

	@Resource
	private BaseCampusService campusService; // 校区信息
	
	/**
	 * 获取课程信息
	 * @param termCode	终端号
	 * @param classId	班级Id（当终端绑定的房间为功能室时，此参数可为空）
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/getCourse" }, method = RequestMethod.GET)
	public CourseArrangeApp jkcourse(@RequestParam("termCode") String termCode,
			@RequestParam(value="classId",required=false) String classId) {
		
		CourseArrangeApp arrangeForApp = new CourseArrangeApp();
		
		InfoTerminal roomTerm = termService.getByProerties("terminalNo", termCode);		
		if (roomTerm==null) {
			arrangeForApp.setMessageInfo("没有找到该终端设备！");
			arrangeForApp.setMessage(false);
			return arrangeForApp;
		}
		
		RoomInfo roominfo = brService.get(roomTerm.getRoomId());
		if (roominfo==null) {
			arrangeForApp.setMessageInfo("没有找到该终端下的房间信息！");
			arrangeForApp.setMessage(false);
			return arrangeForApp;
		}
		
		List<CourseArrange> newlists = new ArrayList<CourseArrange>();

		if (roominfo.getRoomType().equals("5"))	{	//功能室
			
			String hql = "from FuncRoomCourse where isDelete=0 and roomId='" + roomTerm.getRoomId() + "' order by sections";
			List<FuncRoomCourse> lists = funcroomService.queryByHql(hql);
			if (lists != null && lists.size() > 0) {
				for (FuncRoomCourse funcroomcourse : lists) {
					CourseArrange jca = new CourseArrange();
					jca.setCourseName01(funcroomcourse.getClassName01());
					jca.setCourseName02(funcroomcourse.getClassName02());
					jca.setCourseName03(funcroomcourse.getClassName03());
					jca.setCourseName04(funcroomcourse.getClassName04());
					jca.setCourseName05(funcroomcourse.getClassName05());
					jca.setCourseName06(funcroomcourse.getClassName06());
					jca.setCourseName07(funcroomcourse.getClassName07());
					jca.setSections(funcroomcourse.getSections());
					jca.setWeekOne(funcroomcourse.getCourseName01());
					jca.setWeekTwo(funcroomcourse.getCourseName02());
					jca.setWeekThree(funcroomcourse.getCourseName03());
					jca.setWeekFour(funcroomcourse.getCourseName04());
					jca.setWeekFive(funcroomcourse.getCourseName05());
					jca.setWeekSix(funcroomcourse.getCourseName06());
					jca.setWeekSeven(funcroomcourse.getCourseName07());
					newlists.add(jca);
				}
				arrangeForApp.setMessage(true);
				arrangeForApp.setMessageInfo("查询班级课表成功");
				arrangeForApp.setList(newlists);
			} else {
				arrangeForApp.setMessage(false);
				arrangeForApp.setMessageInfo("查询班级课表无信息");			
			}
			
		} else {
			
			GradeClass gradeClass = classService.get(classId);// 班级对象
			if (gradeClass == null) {
				arrangeForApp.setMessageInfo("没有找到对应的班级！");
				arrangeForApp.setMessage(false);
				return arrangeForApp;
			}			
						
			StringBuffer hql = new StringBuffer("from CourseArrange where classId='" + classId
					+ "' and isUse=1 and isDelete=0 order by className,sections asc");
			List<CourseArrange> lists = thisService.queryByHql(hql.toString());// 执行查询方法

			if (lists != null && lists.size() > 0) {
				for (CourseArrange jca : lists) {
					jca.setWeekOne(jca.getCourseName01() + "(" + jca.getTeacherName01() + ")");
					jca.setWeekTwo(jca.getCourseName02() + "(" + jca.getTeacherName02() + ")");
					jca.setWeekThree(jca.getCourseName03() + "(" + jca.getTeacherName03() + ")");
					jca.setWeekFour(jca.getCourseName04() + "(" + jca.getTeacherName04() + ")");
					jca.setWeekFive(jca.getCourseName05() + "(" + jca.getTeacherName05() + ")");
					jca.setWeekSix(jca.getCourseName06() + "(" + jca.getTeacherName06() + ")");
					jca.setWeekSeven(jca.getCourseName07() + "(" + jca.getTeacherName07() + ")");
					newlists.add(jca);
				}
				arrangeForApp.setMessage(true);
				arrangeForApp.setMessageInfo("查询班级课表成功");
				arrangeForApp.setList(newlists);
			} else {
				arrangeForApp.setMessageInfo("查询班级课表无信息");
				arrangeForApp.setMessage(false);
			}
		}

		return arrangeForApp;
	}
	

	/**
	 * 根据班级得到当天的课程表
	 * 
	 * @param claiId
	 */
	@ResponseBody
	@RequestMapping(value = { "/getDayCourse" }, method=RequestMethod.GET)
	public JKCourseToDayArray todaycourse(@RequestParam("termCode") String termCode,@RequestParam("classId") String classId) {
		return getTodaycourse(termCode,classId);
	}

	/**
	 * 得到当天当时的前两节课的课程表
	 * @param termCode	终端号
	 * @param classId	班级Id（若房间类型为功能室，则班级ID可为空）
	 * @return
	 * @throws ParseException 
	 */
	@ResponseBody
	@RequestMapping(value = { "/getNowCourse" }, method = RequestMethod.GET)
	public JKCourseToDayArray getCourseForNow(@RequestParam("termCode") String termCode,
			@RequestParam(value="classId",required=false) String classId) throws ParseException {
		
		JKCourseToDayArray jtd = new JKCourseToDayArray();
		
		AttenceRule checkrule = checkruleService.getByProerties(new String[]{"isDelete","startUsing"},new Object[]{0,1});
		if(checkrule==null){
			jtd.setMessage(false);
			jtd.setMessageInfo("没有找到考勤规则");
			return jtd;
		}
		
		InfoTerminal roomTerm = termService.getByProerties("terminalNo", termCode);
		if (roomTerm == null) {
			jtd.setMessage(false);
			jtd.setMessageInfo("没有找到该终端设备！");
			return jtd;
		}
		RoomInfo roominfo = brService.get(roomTerm.getRoomId());
		if (roominfo == null) {
			jtd.setMessage(false);
			jtd.setMessageInfo("没有找到设备对应房间！");
			return jtd;
		}
		
		
		// 功能室课表
		if (roominfo.getRoomType().equals("5")) {
					
			if (ModelUtil.isNotNull(roomTerm)) {
				String[] propName = { "roomId", "isDelete" };
				Object[] propValue = { roomTerm.getRoomId(), 0 };
				FuncRoomDefine funcRoomDefine = bFuncRoomDefineService.getByProerties(propName, propValue);
				
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
//				String nowTime[] = sdf.format(new Date()).trim().split(":");
//				int hour = Integer.parseInt(nowTime[0]);
//				int minute = Integer.parseInt(nowTime[1]);
				
				int courseNum = 2;
				jtd = getTodaycourseFuncRoom(funcRoomDefine);
				if (jtd == null || jtd.getMessage() == false || jtd.getJcList() == null || jtd.getJcList().size() <= 0)
					return jtd;
				
				List<JKCourse> jkcList = jtd.getJcList();
				List<JKCourse> tempJKC = new ArrayList<JKCourse>();
				jtd.setJcList(null);
				SortListUtil<JKCourse> sortJkc = new SortListUtil<JKCourse>();
				sortJkc.Sort(jkcList, "beginTime", "");	//再次排序？有意义？
				
				//结合考勤规则，根据时间的计算，得出应该显示的课程信息
				for (JKCourse jkc : jkcList) {
					String tempTime[] = jkc.getBeginTime().trim().split(":");
					int tempBeginHour = Integer.parseInt(tempTime[0]);
					int tempBeginMinute = Integer.parseInt(tempTime[1]);
					tempTime = jkc.getEndTime().trim().split(":");
					int tempEndHour = Integer.parseInt(tempTime[0]);
					int tempEndMinute = Integer.parseInt(tempTime[1]);
					if (tempBeginMinute - checkrule.getInAdvanceTime() < 0) {
						tempBeginHour -= 1;
						tempBeginMinute = tempBeginMinute - checkrule.getInAdvanceTime() + 60;
					} else {
						tempBeginMinute = tempBeginMinute - checkrule.getInAdvanceTime();
					}
					if (tempEndMinute + checkrule.getOutAdvanceTimme() >= 60) {
						tempEndHour += 1;
						tempEndMinute = tempEndMinute + checkrule.getOutAdvanceTimme() - 60;
					} else {
						tempEndMinute = tempEndMinute + checkrule.getOutAdvanceTimme();
					}

					// if (tempBeginHour < hour) continue;

					// if (tempBeginHour == hour)
					// if (tempBeginMinute <= minute)
					// continue;
					if (DateUtil.isInZone(DateUtil.getLong(tempBeginHour + ":" + tempBeginMinute),
							DateUtil.getLong(tempEndHour + ":" + tempEndMinute), DateUtil.getCurrentTime())) {
						tempJKC.add(jkc);
						courseNum--;
					}
					if (courseNum == 0)
						break;
				}
				jtd.setJcList(tempJKC);
				return jtd;
			} else {
				jtd.setMessage(false);
				jtd.setMessageInfo("没有找到对应设备");
				return jtd;
			}
			
		} else {	//处理班级的课程
			
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			String nowTime[] = sdf.format(new Date()).trim().split(":");
			int hour = Integer.parseInt(nowTime[0]);
			int minute = Integer.parseInt(nowTime[1]);
			int courseNum = 2;
			
			//获取当天的课程
			jtd = getTodaycourse(termCode,classId);
			if (jtd == null || jtd.getMessage() == false || jtd.getJcList() == null || jtd.getJcList().size() <= 0)
				return jtd;
			
			List<JKCourse> jkcList = jtd.getJcList();
			List<JKCourse> tempJKC = new ArrayList<JKCourse>();
			jtd.setJcList(null);		
			SortListUtil<JKCourse> sortJkc = new SortListUtil<JKCourse>();
			sortJkc.Sort(jkcList, "beginTime", "");	//再次排序？有意义？		
			
			//结合考勤规则，根据时间的计算，得出应该显示的课程信息
			for (JKCourse jkc : jkcList) {
				String tempTime[] = jkc.getBeginTime().trim().split(":");
				int tempBeginHour = Integer.parseInt(tempTime[0]);
				int tempBeginMinute = Integer.parseInt(tempTime[1]);
				tempTime = jkc.getEndTime().trim().split(":");
				int tempEndHour = Integer.parseInt(tempTime[0]);
				int tempEndMinute = Integer.parseInt(tempTime[1]);
				if (tempBeginMinute - checkrule.getInAdvanceTime() < 0) {
					tempBeginHour -= 1;
					tempBeginMinute = tempBeginMinute - checkrule.getInAdvanceTime() + 60;
				}
				if (tempEndMinute + checkrule.getOutAdvanceTimme() >= 60) {
					tempEndHour += 1;
					tempEndMinute = tempEndMinute + checkrule.getOutAdvanceTimme() - 60;
				}
				if (DateUtil.isInZone(DateUtil.getLong(tempBeginHour + ":" + tempBeginMinute),
						DateUtil.getLong(tempEndHour + ":" + tempEndMinute), DateUtil.getCurrentTime())){
					tempJKC.add(jkc);
					courseNum--;
				}
					// if (tempBeginHour < hour) continue;
					// if (tempBeginHour == hour)
					// if (tempBeginMinute <= minute)
					// continue;
					
				if (courseNum == 0)
					break;
			}
			jtd.setJcList(tempJKC);
			return jtd;
		}

	}

	
	/**
	 * 根据班级得到当天的课程表
	 * 
	 * @param claiId
	 * @param request
	 * @param response
	 * @throws IOException
	 * @author huangzc
	 */
	public JKCourseToDayArray getTodaycourse(String termCode,String claiId) {
		
		JKCourseToDayArray jctd = new JKCourseToDayArray();
		
		InfoTerminal roomTerm = termService.getByProerties("terminalNo", termCode);
		if (roomTerm == null) {
			jctd.setMessage(false);
			jctd.setMessageInfo("没有找到该终端设备！");
			return jctd;
		}
		RoomInfo roominfo = brService.get(roomTerm.getRoomId());
		if (roominfo == null) {
			jctd.setMessage(false);
			jctd.setMessageInfo("没有找到设备对应房间！");
			return jctd;
		}
		
		try {
			List<JKCourse> jcList = new ArrayList<JKCourse>();
			
			// 获取当前时间为星期N		
			int dayNum = DateUtil.mathWeekDay(new Date());	
			if (dayNum <= 0) {
				jctd.setMessage(false);
				jctd.setMessageInfo("日期参数有误！");
				return jctd;
			}
		
			
			//获取高中或初中或小学的作息时间	(查询不到，因为作息时间表中不存放学段数据了)	
//			List<JwCalenderdetail> canderDetilList = canderDetailService.queryJwTCanderdetailByJwTCander(
//					canderService.findJwTcanderByClaiId(classService.findJwTGradeByClaiId(claiId)));// 校历详细列表
//			if (canderDetilList == null || canderDetilList.size() <= 0) {
//				jctd.setMessage(false);
//				jctd.setMessageInfo("校历详细列表为空！");
//				return jctd;
//			}
			
			//获取高中或初中或小学的作息时间		
			List<CalenderDetail> canderDetilList = null ;
			String campusId = campusService.getCampusIdByRoom(roominfo);	//获取校区ID
			
			//找到了校区
			if(campusId!=null){
				String[] propName = new String[] { "campusId", "activityState", "isDelete" };
				Object[] propValue = new Object[] { campusId, 1, 0 };
				Calender calender = canderService.getByProerties(propName, propValue); // 查询出校区启用的作息时间
				
				//找到了作息时间
				if(calender!=null){
					propName = new String[] { "calenderId", "isDelete" };
					propValue = new Object[] { calender.getId(), 0 };
					canderDetilList = canderDetailService.queryByProerties(propName, propValue);	//查询出作息时间详细
				}
			}
			if (canderDetilList == null || canderDetilList.size() <= 0) {
				jctd.setMessage(false);
				jctd.setMessageInfo("校历详细列表为空！");
				return jctd;
			}
			
			
			//获取班级的课表信息
			StringBuffer hql = new StringBuffer("from CourseArrange where isDelete=0 and classId='");
			hql.append(claiId).append("' and isUse=1  order by className,sections asc");
			List<CourseArrange> jtaList = thisService.queryByHql(hql.toString());// 执行查询方法得到班级课程表
			if (jtaList == null || jtaList.size() <= 0) {
				jctd.setMessage(false);
				jctd.setMessageInfo("查询班级课表无信息！");
				return jctd;
			}
			
			//获取jcCode字段不为空的数据
			List<CalenderDetail> canderDetilListed = canderDetilList.stream()
					.filter(x->StringUtils.isNotEmpty(x.getSenctionCode()))
					.collect(Collectors.toList());			
//			List<JwCalenderdetail> canderDetilListed = new ArrayList<JwCalenderdetail>();
//			for (JwCalenderdetail jtc : canderDetilList) {
//				if (jtc.getJcCode() == null || jtc.getJcCode().trim().equals(""))
//					continue;
//				canderDetilListed.add(jtc);
//			}
			
			//排序
			SortListUtil<CalenderDetail> slu = new SortListUtil<CalenderDetail>();
			slu.Sort(canderDetilListed, "sectionCode", null);
			
			SortListUtil<CourseArrange> jta = new SortListUtil<CourseArrange>();
			jta.Sort(jtaList, "sections", null);
			
			//根据作息时间的节次 与 课程的节次组合，组装数据
			SimpleDateFormat simpl = new SimpleDateFormat("HH:mm");
			for (CalenderDetail tempJtc : canderDetilListed) {
				JKCourse jc = new JKCourse();
				boolean flag = false;
				for (CourseArrange tempJta : jtaList) {
					if (tempJtc.getSenctionCode().equals(tempJta.getSections())) {
						jc.setTeachTime(tempJtc.getSenctionCode());
						jc.setJcName(tempJtc.getSenctionName());
						jc.setBeginTime(simpl.format(tempJtc.getBeginTime()));
						jc.setEndTime(simpl.format(tempJtc.getEndTime()));
						jc.setNeedSignIn(tempJtc.getNeedSignIn());
						switch (dayNum) {
						case 1:
							jc.setTeachrName(tempJta.getTeacherName01());
							jc.setCourseName(tempJta.getCourseName01());
							flag = true;
							break;
						case 2:
							jc.setTeachrName(tempJta.getTeacherName02());
							jc.setCourseName(tempJta.getCourseName02());
							flag = true;
							break;
						case 3:
							jc.setTeachrName(tempJta.getTeacherName03());
							jc.setCourseName(tempJta.getCourseName03());
							flag = true;
							break;
						case 4:
							jc.setTeachrName(tempJta.getTeacherName04());
							jc.setCourseName(tempJta.getCourseName04());
							flag = true;
							break;
						case 5:
							jc.setTeachrName(tempJta.getTeacherName05());
							jc.setCourseName(tempJta.getCourseName05());
							flag = true;
							break;
						case 6:
							jc.setTeachrName(tempJta.getTeacherName06());
							jc.setCourseName(tempJta.getCourseName06());
							flag = true;
							break;
						case 7:
							jc.setTeachrName(tempJta.getTeacherName07());
							jc.setCourseName(tempJta.getCourseName07());
							flag = true;
							break;
						default:
							break;
						}
						break;
					}
				}
				if (flag)
					jcList.add(jc);
			}
			if (jcList != null && jcList.size() > 0) {
				jctd.setJcList(jcList);
				jctd.setDayFoWeek(String.valueOf(dayNum));
				jctd.setMessage(true);
			}
			
		} catch (Exception e) {
			jctd.setMessage(false);
			jctd.setMessageInfo("请求失败！");
		}
		return jctd;
	}
	
	
	//得到今天的功能室课程
	public JKCourseToDayArray getTodaycourseFuncRoom(FuncRoomDefine funcRoom){
		JKCourseToDayArray jctd = new JKCourseToDayArray();
		if (funcRoom == null ) {
			jctd.setMessage(false);
			jctd.setMessageInfo("请传入参数！");
			return jctd;
		}
		
		try {
			List<JKCourse> jcList = new ArrayList<JKCourse>();
			
			//获取星期N数据
			int dayNum = DateUtil.mathWeekDay(new Date());
			if (dayNum <= 0) {
				jctd.setMessage(false);
				jctd.setMessageInfo("日期参数有误");
				return jctd;
			}
				
			//获取校区ID
			RoomInfo roomInfo=brService.get(funcRoom.getRoomId());
			String campusId = campusService.getCampusIdByRoom(roomInfo);	
				
			//根据校区，获取作息时间
			String[] propName = { "campusId", "activityState", "isDelete" };
			Object[] propValue = { campusId, 1, 0 };
			Calender calender = canderService.getByProerties(propName, propValue);
			
			//获取作息时间详情
			List<CalenderDetail> canderDetilList = canderDetailService.queryJwTCanderdetailByJwTCander(calender);// 校历详细列表

			if (canderDetilList == null || canderDetilList.size() <= 0) {
				jctd.setMessage(false);
				jctd.setMessageInfo("校历详细列表为空");
				return jctd;
			}
			
			
			//获取功能室课程
			StringBuffer hql = new StringBuffer("from FuncRoomCourse where isDelete=0 and funcRoomId='");
			hql.append(funcRoom.getId()).append("'  order by sections asc");
			List<FuncRoomCourse> jtaList = funcroomService.queryByHql(hql.toString());// 执行查询方法得到课程表					
			if (jtaList == null || jtaList.size() <= 0) {
				jctd.setMessage(false);
				jctd.setMessageInfo("查询功能室课表无信息");
				return jctd;
			}
			
			//获取jcCode字段不为空的数据
			List<CalenderDetail> canderDetilListed = canderDetilList.stream()
					.filter(x->StringUtils.isNotEmpty(x.getSenctionCode()))
					.collect(Collectors.toList());			
			// 数据处理
//			List<JwCalenderdetail> canderDetilListed = new ArrayList<JwCalenderdetail>();
//			for (JwCalenderdetail jtc : canderDetilList) {
//				if (jtc.getJcCode() == null || jtc.getJcCode().trim().equals(""))
//					continue;
//				canderDetilListed.add(jtc);
//			}		
			//排序
			SortListUtil<CalenderDetail> slu = new SortListUtil<CalenderDetail>();
			slu.Sort(canderDetilListed, "senctionCode", null);
			
			//根据作息时间的节次 与 课程的节次组合，组装数据
			SimpleDateFormat simpl = new SimpleDateFormat("HH:mm");
			for (CalenderDetail tempJtc : canderDetilListed) {
				JKCourse jc = new JKCourse();
				boolean flag = false;
				for (FuncRoomCourse tempJta : jtaList) {
					if (tempJtc.getSenctionCode().equals(tempJta.getSections())) {
						jc.setJcName(tempJtc.getSenctionName());
						jc.setBeginTime(simpl.format(tempJtc.getBeginTime()));
						jc.setEndTime(simpl.format(tempJtc.getEndTime()));
						jc.setNeedSignIn(tempJtc.getNeedSignIn());
						switch (dayNum) {
						case 1:
							jc.setTeachrName(tempJta.getTeacherName01());
							jc.setCourseName(tempJta.getCourseName01());
							jc.setClassName(tempJta.getClassName01());
							flag = true;
							break;
						case 2:
							jc.setTeachrName(tempJta.getTeacherName02());
							jc.setCourseName(tempJta.getCourseName02());
							jc.setClassName(tempJta.getClassName02());
							flag = true;
							break;
						case 3:
							jc.setTeachrName(tempJta.getTeacherName03());
							jc.setCourseName(tempJta.getCourseName03());
							jc.setClassName(tempJta.getClassName03());
							flag = true;
							break;
						case 4:
							jc.setTeachrName(tempJta.getTeacherName04());
							jc.setCourseName(tempJta.getCourseName04());
							jc.setClassName(tempJta.getClassName04());
							flag = true;
							break;
						case 5:
							jc.setTeachrName(tempJta.getTeacherName05());
							jc.setCourseName(tempJta.getCourseName05());
							jc.setClassName(tempJta.getClassName05());
							flag = true;
							break;
						case 6:
							jc.setTeachrName(tempJta.getTeacherName06());
							jc.setCourseName(tempJta.getCourseName06());
							jc.setClassName(tempJta.getClassName06());
							flag = true;
							break;
						case 7:
							jc.setTeachrName(tempJta.getTeacherName07());
							jc.setCourseName(tempJta.getCourseName07());
							jc.setClassName(tempJta.getClassName07());
							flag = true;
							break;
						default:
							break;
						}
						break;
					}
				}
				if (flag)
					jcList.add(jc);
			}
			if (jcList != null && jcList.size() > 0) {
				jctd.setJcList(jcList);
				jctd.setDayFoWeek(String.valueOf(dayNum));
				jctd.setMessage(true);
			}
			
		} catch (Exception e) {
			jctd.setMessage(false);
			jctd.setMessageInfo("请求失败！");
		}
		return jctd;
	}
}