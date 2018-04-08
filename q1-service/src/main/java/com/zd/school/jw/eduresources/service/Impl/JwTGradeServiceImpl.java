package com.zd.school.jw.eduresources.service.Impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zd.core.model.extjs.QueryResult;
import com.zd.core.service.BaseServiceImpl;
import com.zd.school.jw.eduresources.dao.JwTGradeDao;
import com.zd.school.jw.eduresources.model.Grade;
import com.zd.school.jw.eduresources.model.JwTGrade;
import com.zd.school.jw.eduresources.service.JwTGradeService;
import com.zd.school.plartform.system.model.SysUser;
import com.zd.school.plartform.system.model.User;
import com.zd.school.plartform.system.service.SysJobService;
import com.zd.school.plartform.system.service.SysOrgService;

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
public class JwTGradeServiceImpl extends BaseServiceImpl<Grade> implements JwTGradeService {

    @Resource
    public void setJwTGradeDao(JwTGradeDao dao) {
        this.dao = dao;
    }

    @Resource
    private SysOrgService orgService; //部门数据服务接口

    @Resource
    private SysJobService jobService;


    @SuppressWarnings("unchecked")
    @Override
    public QueryResult<Grade> getGradeList(Integer start, Integer limit, String sort, String filter,
            Boolean isDelete, User currentUser) {
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
        QueryResult<Grade> qr = this.queryPageResult(start, limit, sort, queryFilter, true);
        return qr;
    }

}