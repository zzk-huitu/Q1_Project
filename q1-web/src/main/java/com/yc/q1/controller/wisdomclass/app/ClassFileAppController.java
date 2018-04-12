package com.yc.q1.controller.wisdomclass.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yc.q1.core.util.DateUtil;
import com.yc.q1.core.util.EntityUtil;
import com.yc.q1.core.util.StringUtils;
import com.yc.q1.model.base.pt.basic.PtAttachment;
import com.yc.q1.model.base.pt.basic.PtFuncRoomCourse;
import com.yc.q1.model.base.pt.basic.PtGradeClass;
import com.yc.q1.model.base.pt.basic.PtInfoTerminal;
import com.yc.q1.model.base.pt.build.PtRoomInfo;
import com.yc.q1.model.base.pt.wisdomclass.PtAttendTerm;
import com.yc.q1.model.base.pt.wisdomclass.PtAttendTime;
import com.yc.q1.model.base.pt.wisdomclass.PtClassMien;
import com.yc.q1.pojo.base.app.PictureApp;
import com.yc.q1.pojo.base.app.PictureForApp;
import com.yc.q1.pojo.base.app.PictureReturnApp;
import com.yc.q1.pojo.base.app.VideoApp;
import com.yc.q1.pojo.base.app.VideoForApp;
import com.yc.q1.pojo.base.app.VideoReturnApp;
import com.yc.q1.service.base.pt.basic.PtAttachmentService;
import com.yc.q1.service.base.pt.basic.PtFuncRoomCourseService;
import com.yc.q1.service.base.pt.basic.PtGradeClassService;
import com.yc.q1.service.base.pt.basic.PtInfoTerminalService;
import com.yc.q1.service.base.pt.build.PtRoomInfoService;
import com.yc.q1.service.base.pt.wisdomclass.PtAttendTermService;
import com.yc.q1.service.base.pt.wisdomclass.PtAttendTimeService;
import com.yc.q1.service.base.pt.wisdomclass.PtClassMienService;

@Controller
@RequestMapping("/app/ClassFile")
public class ClassFileAppController {	
	
	@Value("${virtualFileUrl}")  	//读取在配置文件属性
	private String virtualFileUrl; 	//文件目录虚拟路径

	    
	@Resource
	private PtGradeClassService thisService;

	@Resource
	private PtRoomInfoService brService;
	
	@Resource
	private PtClassMienService elegantService; // service层接口

	@Resource
	private PtAttachmentService baseTAttachmentService;// service层接口

	@Resource
	private PtInfoTerminalService termService; // 终端设备serice层接口

	@Resource
	private PtFuncRoomCourseService funcCourseService; // 功能室课表

	@Resource
	private PtAttendTimeService attTimeService;
	@Resource
	private PtAttendTermService attTermService;
	
	/**
	 * 获取班级的图片信息
	 * @param termCode	设备终端号
	 * @param classId	班级ID（可为空； 当房间是功能室时，就不用传入此参数）
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/getClassPics" }, method = RequestMethod.GET)
	public PictureApp downloadPic(@RequestParam(value = "termCode") String termCode,
			@RequestParam(value = "classId", required = false) String classId) {
		
		PictureApp info = new PictureApp();

		PtInfoTerminal roomTerm = termService.getByProerties("terminalNo", termCode);
		if (roomTerm == null) {
			info.setMessage(false);
			info.setMessageInfo("没有找到该终端设备！");
			return info;
		}

		PtRoomInfo roominfo = brService.get(roomTerm.getRoomId());
		if (roominfo == null) {
			info.setMessage(false);
			info.setMessageInfo("没有找到该设备绑定的房间信息！");
			return info;
		}

		// 格式
		String[] inType = { "JPG", "JPEG", "BMP", "PNG" };
		int maxSize = 300 * 1024 * 1024;
		List<PtAttachment> attList = new ArrayList<PtAttachment>();

		if (roominfo.getRoomType().equals("5")) { // 当为功能室的时候
			
			String[] propName = new String[] { "terminalNo", "isDelete" };
			Object[] propValue = new Object[] { termCode, 0 };
			List<PtAttendTerm> attTerms = attTermService.queryByProerties(propName, propValue); // 查询特殊考勤终端
			int dayNum = DateUtil.mathWeekDay(new Date());// 星期参数
			
			// 如果当前设备被分配到了特殊课程里
			if (attTerms != null && attTerms.size() != 0) {

				String titleIds = attTerms.stream().map(x -> x.getAttendThemeId())
						.collect(Collectors.joining("','", "'", "'"));

				StringBuffer hql = new StringBuffer("from AttendTime where isDelete=0");
				hql.append(" and attendThemeId in(" + titleIds + ")");
				hql.append(" and weekDay=" + dayNum);

				// 每周都需要考勤（查询集合1）
				String hql2 = " and beginTime is null and endTime is null";
				List<PtAttendTime> everyWeek = attTimeService.queryByHql(hql.toString() + hql2);

				// 某一时间段的周几需要考勤（查询集合2）
				String today = DateUtil.formatDate(new Date());
				hql2 = " and beginTime<='" + today + "' and endTime>='" + today + "'";
				List<PtAttendTime> sometimeWeek = attTimeService.queryByHql(hql.toString() + hql2);

				everyWeek.addAll(sometimeWeek); // 融合

				// 查询这些学生中，有哪些班级
				// 获取这些特殊考勤的主题id
				titleIds = everyWeek.stream().map(x -> x.getAttendThemeId()).collect(Collectors.joining("','", "'", "'"));
				String sql = "select distinct a.classId from T_PT_ClassStudent a where a.isDelete=0 and "
						+ "	a.studentId in (select distinct userId from T_PT_AttendUser where isDelete=0 and attendThemeId in ("
						+ titleIds + "))";
				List<String> classIds=thisService.queryEntityBySql(sql, null);
				
				/*旧版本的获取方式*/
//				Set<String> claids = new HashSet<String>();
//				for (AttTime attTime : everyWeek) {
//					hql = new StringBuffer("from JwClassstudent where isDelete=0");
//					hql.append(" and studentId in(select userId from AttUser where titleId='" + attTime.getTitleId()
//							+ "' and isDelete=0)");
//					List<JwClassstudent> list = classStudentService.queryByHql(hql.toString());
//					for (JwClassstudent jwClassstudent : list) {
//						claids.add(jwClassstudent.getClaiId());
//					}
//				}
				
				for (String claid : classIds) {
					attList.addAll(filterFile(claid, inType, maxSize));
				}

			}else {	//否则查询功能室课程
							
				propName = new String[] { "roomId","isDelete"};
				propValue = new Object[] { roomTerm.getRoomId(),0 };
				
				Map<String, String> map = new HashMap<String, String>();
				map.clear();
				map.put("sections", "asc");
				List<PtFuncRoomCourse> funcroomcoursesList = funcCourseService.queryByProerties(propName, propValue,
						map);
				Set<String> classIds = new HashSet<String>();
				for (PtFuncRoomCourse jwFuncroomcourse : funcroomcoursesList) {
					String claid = EntityUtil.getPropertyValue(jwFuncroomcourse, "classId0" + dayNum) + "";	//查询星期N的班级课程
					if (StringUtils.isNotEmpty(claid)) {
						classIds.add(claid);
					}
				}
				for (String claid : classIds) {
					attList.addAll(filterFile(claid, inType, maxSize));
				}
			}

		} else { // 当为教室的时候

			PtGradeClass classInfo = thisService.get(classId);
			if (classInfo == null) {
				info.setMessage(false);
				info.setMessageInfo("未找到该班级信息！");
				return info;
			}
			attList = filterFile(classInfo.getId(), inType, maxSize);

		}
		
		//组装返回值
		if (attList.size() > 100) {
			attList = attList.subList(0, 100);
		}
		List<PictureForApp> picList = new ArrayList<PictureForApp>();
		PictureForApp pic=null;
		for (PtAttachment baseAttachment : attList) {
			pic = new PictureForApp();
					
			/*文件地址，方式一：使用接口的方式下载文件*/
//			String attUrl = baseAttachment.getAttachUrl();
//			pic.setPictureURL(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
//				+ "/app/JwTGradeclass/download?filename=" + attUrl);
//			pic.setPictureName(attUrl.substring(attUrl.lastIndexOf('/') + 1));
			
			/*文件地址，方式二：直接使用url的方式获取文件*/
			pic.setPictureURL(virtualFileUrl+"/"+baseAttachment.getFileUrl());
			pic.setPictureName(baseAttachment.getFileName());
			picList.add(pic);
		}

		PictureReturnApp data = new PictureReturnApp();
		data.setTotalCount(picList.size());
		data.setPicList(picList);
		info.setData(data);
		info.setMessage(true);
		info.setMessageInfo("请求成功！");
		return info;
	}

	/**
	 * 获取班级的视频信息
	 * @param termCode	设备终端号
	 * @param classId	班级ID（可为空； 当房间是功能室时，就不用传入此参数）
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/getClassVideos" }, method = RequestMethod.GET)
	public VideoApp getClassVideos(@RequestParam(value = "termCode") String termCode,
			@RequestParam(value = "classId", required = false) String classId) {
		
		VideoApp info = new VideoApp();

		PtInfoTerminal roomTerm = termService.getByProerties("terminalNo", termCode);
		if (roomTerm == null) {
			info.setMessage(false);
			info.setMessageInfo("没有找到该终端设备！");
			return info;
		}

		PtRoomInfo roominfo = brService.get(roomTerm.getRoomId());
		if (roominfo == null) {
			info.setMessage(false);
			info.setMessageInfo("没有找到该设备绑定的房间信息！");
			return info;
		}

		String[] inType = { "avi", "mp4", "3gp" };
		int maxSize = 800 * 1024 * 1024;
		List<PtAttachment> attList = new ArrayList<PtAttachment>();

		if (roominfo.getRoomType().equals("5")) { // 当为功能室的时候
			
			String[] propName = new String[] { "terminalNo", "isDelete" };
			Object[] propValue = new Object[] { termCode, 0 };
			List<PtAttendTerm> attTerms = attTermService.queryByProerties(propName, propValue); // 查询特殊考勤终端
			int dayNum = DateUtil.mathWeekDay(new Date());// 星期参数
			
			// 如果当前设备被分配到了特殊课程里
			if (attTerms != null && attTerms.size() != 0) {

				String titleIds = attTerms.stream().map(x -> x.getAttendThemeId())
						.collect(Collectors.joining("','", "'", "'"));

				StringBuffer hql = new StringBuffer("from AttendTime where isDelete=0");
				hql.append(" and attendThemeId in(" + titleIds + ")");
				hql.append(" and weekDay=" + dayNum);

				// 每周都需要考勤（查询集合1）
				String hql2 = " and beginTime is null and endTime is null";
				List<PtAttendTime> everyWeek = attTimeService.queryByHql(hql.toString() + hql2);

				// 某一时间段的周几需要考勤（查询集合2）
				String today = DateUtil.formatDate(new Date());
				hql2 = " and beginTime<='" + today + "' and endTime>='" + today + "'";
				List<PtAttendTime> sometimeWeek = attTimeService.queryByHql(hql.toString() + hql2);

				everyWeek.addAll(sometimeWeek); // 融合

				// 查询这些学生中，有哪些班级
				// 获取这些特殊考勤的主题id
				titleIds = everyWeek.stream().map(x -> x.getAttendThemeId()).collect(Collectors.joining("','", "'", "'"));
				String sql = "select distinct a.classId from T_PT_ClassStudent a where a.isDelete=0 and "
						+ "	a.studentId in (select distinct userId from T_PT_AttendUser where isDelete=0 and attendThemeId in ("
						+ titleIds + "))";
				List<String> classIds=thisService.queryEntityBySql(sql, null);
				
				/*旧版本的获取方式*/
//				Set<String> claids = new HashSet<String>();
//				for (AttTime attTime : everyWeek) {
//					hql = new StringBuffer("from JwClassstudent where isDelete=0");
//					hql.append(" and studentId in(select userId from AttUser where titleId='" + attTime.getTitleId()
//							+ "' and isDelete=0)");
//					List<JwClassstudent> list = classStudentService.queryByHql(hql.toString());
//					for (JwClassstudent jwClassstudent : list) {
//						claids.add(jwClassstudent.getClaiId());
//					}
//				}
				
				for (String claid : classIds) {
					attList.addAll(filterFile(claid, inType, maxSize));
				}

			}else {	//否则查询功能室课程
							
				propName = new String[] { "roomId","isDelete" };
				propValue = new Object[] { roomTerm.getRoomId(),0 };
				
				Map<String, String> map = new HashMap<String, String>();
				map.clear();
				map.put("sections", "asc");
				List<PtFuncRoomCourse> funcroomcoursesList = funcCourseService.queryByProerties(propName, propValue,
						map);
				Set<String> classIds = new HashSet<String>();
				for (PtFuncRoomCourse jwFuncroomcourse : funcroomcoursesList) {
					String claid = EntityUtil.getPropertyValue(jwFuncroomcourse, "classId0" + dayNum) + "";	//查询星期N的班级课程
					if (StringUtils.isNotEmpty(claid)) {
						classIds.add(claid);
					}
				}
				for (String claid : classIds) {
					attList.addAll(filterFile(claid, inType, maxSize));
				}
			}

		} else { // 当为教室的时候

			PtGradeClass classInfo = thisService.get(classId);
			if (classInfo == null) {
				info.setMessage(false);
				info.setMessageInfo("未找到该班级信息！");
				return info;
			}
			attList = filterFile(classInfo.getId(), inType, maxSize);

		}
		
		//组装返回值
		if (attList.size() > 100) {
			attList = attList.subList(0, 100);
		}
		List<VideoForApp> videoList = new ArrayList<VideoForApp>();
		VideoForApp vd=null;
		for (PtAttachment baseAttachment : attList) {
			vd = new VideoForApp();
					
			/*文件地址，方式一：使用接口的方式下载文件*/
//			String attUrl = baseAttachment.getAttachUrl();
//			vd.setVideoURL(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
//				+ "/app/JwTGradeclass/download?filename=" + attUrl);
//			vd.setVideoName(attUrl.substring(attUrl.lastIndexOf('/') + 1));
			
			/*文件地址，方式二：直接使用url的方式获取文件*/
			vd.setVideoURL(virtualFileUrl+"/"+baseAttachment.getFileUrl());
			vd.setVideoName(baseAttachment.getFileName());
			videoList.add(vd);
		}
		
	
		VideoReturnApp data = new VideoReturnApp();
		data.setTotalCount(videoList.size());
		data.setVideoList(videoList);
		info.setMessage(true);
		info.setMessageInfo("请求成功！");
		info.setData(data);
		return info;
	}

	// 查询文件信息
	private List<PtAttachment> filterFile(String claiId, String[] inType, int maxSize) {
		List<PtAttachment> returnList = new ArrayList<PtAttachment>();
		StringBuffer types = new StringBuffer();
		for (String type : inType) {
			types.append("'." + type + "',");
		}
		types = types.deleteCharAt(types.length() - 1);
		String hql = "from ClassMien where isDelete=0 and classId='" + claiId + "' order by createTime desc";
		List<PtClassMien> eleganeList = elegantService.queryByHql(hql);
		int size = 0;
		
		if(!eleganeList.isEmpty()){
			String recordIds=eleganeList.stream().map(x->x.getId()).collect(Collectors.joining("','","'","'"));
			
			hql = "from Attachment where isDelete=0 and recordId in (" + recordIds + ") and fileType in(" + types
					+ ") order by createTime desc";
			List<PtAttachment> attList = baseTAttachmentService.queryByHql(hql);
			for (PtAttachment baseAttachment : attList) {
				if (size + baseAttachment.getFileSize() <= maxSize) {
					returnList.add(baseAttachment);
					size += baseAttachment.getFileSize();
				}
			}
		}
		
		/*N次循环的方式*/
//		for (EccClasselegant eccClasselegant : eleganeList) {
//			hql = "from BaseAttachment where recordId='" + eccClasselegant.getId() + "' and attachType in(" + types
//					+ ") order by createTime desc";
//			List<BaseAttachment> attList = baseTAttachmentService.queryByHql(hql);
//			for (BaseAttachment baseAttachment : attList) {
//				if (size + baseAttachment.getAttachSize() <= maxSize) {
//					returnList.add(baseAttachment);
//					size += baseAttachment.getAttachSize();
//				}
//			}
//		}
		
		return returnList;
	}
	
}