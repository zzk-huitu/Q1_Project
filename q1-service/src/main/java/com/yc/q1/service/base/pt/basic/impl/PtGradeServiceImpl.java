package com.yc.q1.service.base.pt.basic.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.core.dao.BaseDao;
import com.yc.q1.core.model.extjs.QueryResult;
import com.yc.q1.core.service.BaseServiceImpl;
import com.yc.q1.model.base.pt.basic.PtGrade;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.service.base.pt.basic.PtGradeService;
import com.yc.q1.service.base.pt.system.PtDepartmentService;
import com.yc.q1.service.base.pt.system.PtJobService;

/**
 * 
 * ClassName: JwTGradeServiceImpl Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 学校年级信息实体Service接口实现类. date: 2016-03-13
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class PtGradeServiceImpl extends BaseServiceImpl<PtGrade> implements PtGradeService {

	@Resource(name = "ptGradeDao") // 将具体的dao注入进来
	public void setDao(BaseDao<PtGrade> dao) {
		super.setDao(dao);
	}

    @Resource
    private PtDepartmentService orgService; //部门数据服务接口

    @Resource
    private PtJobService jobService;


    @SuppressWarnings("unchecked")
    @Override
    public QueryResult<PtGrade> getGradeList(Integer start, Integer limit, String sort, String filter,
            Boolean isDelete, PtUser currentUser) {
        String queryFilter = filter;
/*        String jobId = currentUser.getJobId();
        String jobName = currentUser.getJobName();
        Integer studyYear = currentUser.getStudyYear();
        String smester = currentUser.getSemester();
        StringBuffer sb = new StringBuffer();
        String qrClassId = "";
        ExtDataFilter selfFilter = new ExtDataFilter();
        //当前该人在年级
        String propName[] = { "studyYeah", "semester", "tteacId", "isDelete" };
        Object[] propValue = { studyYear, smester, currentUser.getUuid(), 0 };
        List<JwGradeteacher> gt = gradeTeaService.queryByProerties(propName, propValue);
        for (JwGradeteacher jgt : gt) {
            sb.append(jgt.getGraiId() + ",");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        qrClassId = sb.toString();
        selfFilter = (ExtDataFilter) JsonBuilder.getInstance().fromJson(
                "{\"type\":\"string\",\"comparison\":\"in\",\"value\":\"" + qrClassId + "\",\"field\":\"uuid\"}",
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
            queryFilter = "[{\"type\":\"string\",\"comparison\":\"in\",\"value\":\"" + qrClassId
                    + "\",\"field\":\"uuid\"}]";
        }*/
        if (currentUser.getUserName().equals("schooladmin"))
            queryFilter = "";
        QueryResult<PtGrade> qr = this.queryPageResult(start, limit, sort, queryFilter, true);
        return qr;
    }

}