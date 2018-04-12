
package com.yc.q1.controller.report;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.q1.controller.base.FrameWorkController;
import com.yc.q1.core.annotation.Auth;
import com.yc.q1.core.constant.Constant;
import com.yc.q1.core.model.extjs.QueryResult;
import com.yc.q1.core.util.JsonBuilder;
import com.yc.q1.core.util.ModelUtil;
import com.yc.q1.core.util.PoiExportExcel;
import com.yc.q1.core.util.StringUtils;
import com.yc.q1.core.util.TLVUtils;
import com.yc.q1.core.util.TagLenVal;
import com.yc.q1.model.base.pt.system.PtDataDictItem;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.model.storage.pt.PtTask;
import com.yc.q1.model.storage.sk.SkTermStatus;
import com.yc.q1.pojo.base.pt.TLVModel;
import com.yc.q1.service.base.pt.system.PtDataDictItemService;
import com.yc.q1.service.storage.pt.PtTaskService;

/**
* Created by zenglj on 2017-05-16.
*/
@Controller
@RequestMapping("/PtTask")
public class PtTaskController extends FrameWorkController<PtTask> implements Constant {

    @Resource
    PtTaskService thisService; // service层接口
	@Resource
	PtDataDictItemService dicitemService;
    /**
      * @Title: list
      * @Description: 查询数据列表
      * @param entity 实体类
      * @param request
      * @param response
      * @throws IOException    设定参数
      * @return void    返回类型
     */
    @RequestMapping(value = { "/list" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET,
            org.springframework.web.bind.annotation.RequestMethod.POST })
    public void list(@ModelAttribute PtTask entity, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String strData = ""; // 返回给js的数据
		QueryResult<PtTask> qResult = thisService.list(super.start(request), super.limit(request), super.sort(request),  super.filter(request),true);
        strData = jsonBuilder.buildObjListToJson(qResult.getTotalCount(), qResult.getResultList(), true);// 处理数据
        writeJSON(response, strData);// 返回数据
    }
    
    @RequestMapping(value = { "/tasklistbyTermId" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET,
            org.springframework.web.bind.annotation.RequestMethod.POST })
    public void tasklistbyTermId(@ModelAttribute PtTask entity, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String strData = ""; // 返回给js的数据
		Integer start = super.start(request);
		Integer limit = super.limit(request);
		String sort = super.sort(request);
		String filter = super.filter(request);
        QueryResult<PtTask> qResult = thisService.tasklistbyTermId(start, limit, sort, filter,true);
        strData = jsonBuilder.buildObjListToJson(qResult.getTotalCount(), qResult.getResultList(), true);// 处理数据
        writeJSON(response, strData);// 返回数据
    }
  
    
	@RequestMapping("/baseParam_read")
	public void baseParam_read(TLVModel tlvs, HttpServletRequest request, 
			HttpServletResponse response) throws IOException{
		PtTask perEntity = thisService.get(tlvs.getId());
		// 将entity中不为空的字段动态加入到perEntity中去。
		String strData ="";
		if(perEntity.getTaskData()!=null){
			List<TagLenVal> list=TLVUtils.decode(perEntity.getTaskData(), tlvs.getTlvs());
			tlvs.setTlvs(list);
			strData = JsonBuilder.getInstance().buildList(tlvs.getTlvs(), "");// 处理数据
		}
		writeJSON(response, strData);// 返回数据
	}
	@Auth("TASK_DETAIL_export")
	@RequestMapping("/doExportExcel")
	public void doExportExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().setAttribute("exportTaskIsEnd", "0");
		request.getSession().removeAttribute("exporTaskIsState");
	  
	    String tasktype = request.getParameter("tasktype");
	    String termsn = request.getParameter("termsn");
	    
	
		List<Map<String, Object>> allList = new ArrayList<>();
		Integer[] columnWidth = new Integer[] { 10,15, 15, 20, 20,30,25,15,15,30, 20, 20,30};
		// 数据字典项
		String mapKey = null;
		String[] propValue = { "TASKTYPE","PTTERMTYPE" };
		Map<String, String> mapDicItem = new HashMap<>();
		List<PtDataDictItem> listDicItem = dicitemService.queryByProerties("dicCode", propValue);
		for (PtDataDictItem baseDicitem : listDicItem) {
			mapKey = baseDicitem.getItemCode() + baseDicitem.getDicCode();
			mapDicItem.put(mapKey, baseDicitem.getItemName());
		}
		List<PtTask> ptTaskList = null;
		//String hql = " from PtTask a where a.isDelete=0 ";
		String hql= " select a from Task a where a.executeTime= "
				+ "(select Max(executeTime) from Task s1 where s1.termSn=a.termSn)  ";
	
		if (StringUtils.isNotEmpty(tasktype)) {
			hql+=" and a.taskType like'%"+tasktype+"%'";
		}
		if (StringUtils.isNotEmpty(termsn)) {
			hql+=" and a.termSn like'%"+termsn+"%'";
		}
		hql+=" order by updateTime desc,executeTime asc";
		ptTaskList = thisService.queryByHql(hql);

		List<Map<String, String>> ptTasExpList = new ArrayList<>();
		
		Map<String, String> ptTasMap = null;
		SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		int i = 1;
		for (PtTask ptTask : ptTaskList) {
			ptTasMap = new LinkedHashMap<>();
			ptTasMap.put("xh",i+"");
			ptTasMap.put("taskno", ptTask.getTaskNo());
			ptTasMap.put("taskdate", ptTask.getTaskDate());
			ptTasMap.put("tasktype", mapDicItem.get(ptTask.getTaskType() + "TASKTYPE"));
			ptTasMap.put("devicetype", mapDicItem.get(ptTask.getTermType() + "PTTERMTYPE"));
			ptTasMap.put("termsn", ptTask.getTermSn());
			ptTasMap.put("termName", ptTask.getTermName());
			ptTasMap.put("retrycount", ptTask.getRetryCount().toString());
			ptTasMap.put("executecount", ptTask.getExecuteCount().toString());
			ptTasMap.put("executetime", format.format(ptTask.getExecuteTime()));
			ptTasMap.put("executeresult", ptTask.getExecuteResult()==true?"成功":"失败");
			ptTasMap.put("istaskover", ptTask.getIsTaskOver()==true?"是":"否");
			ptTasMap.put("resultmsg", ptTask.getResultMsg());
			i++;
			ptTasExpList.add(ptTasMap);
		}

		Map<String, Object> courseAllMap = new LinkedHashMap<>();
		courseAllMap.put("data", ptTasExpList);
		courseAllMap.put("title", null);
		courseAllMap.put("head", new String[] {"序号","任务编号","任务日期","任务类型", "设备类型", "设备序列号","设备序名称","重试次数","执行次数","执行时间","执行结果","任务是否结束","结果消息"}); // 规定名字相同的，设定为合并
		courseAllMap.put("columnWidth", columnWidth); // 30代表30个字节，15个字符
		courseAllMap.put("columnAlignment", new Integer[] { 0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0 }); // 0代表居中，1代表居左，2代表居右
		courseAllMap.put("mergeCondition", null); // 合并行需要的条件，条件优先级按顺序决定，NULL表示不合并,空数组表示无条件
		allList.add(courseAllMap);

		// 在导出方法中进行解析
		boolean result = PoiExportExcel.exportExcel(response, "任务明细", "任务明细", allList);
		if (result == true) {
			request.getSession().setAttribute("exportTaskIsEnd", "1");
		} else {
			request.getSession().setAttribute("exportTaskIsEnd", "0");
			request.getSession().setAttribute("exporTaskIsState", "0");
		}

	}
	@RequestMapping("/checkExportEnd")
	public void checkExportEnd(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Object isEnd = request.getSession().getAttribute("exportTaskIsEnd");
		Object state = request.getSession().getAttribute("exporTaskIsState");
		if (isEnd != null) {
			if ("1".equals(isEnd.toString())) {
				writeJSON(response, jsonBuilder.returnSuccessJson("\"文件导出完成！\""));
			} else if (state != null && state.equals("0")) {
				writeJSON(response, jsonBuilder.returnFailureJson("0"));
			} else {
				writeJSON(response, jsonBuilder.returnFailureJson("\"文件导出未完成！\""));
			}
		} else {
			writeJSON(response, jsonBuilder.returnFailureJson("\"文件导出未完成！\""));
		}
	}
}
