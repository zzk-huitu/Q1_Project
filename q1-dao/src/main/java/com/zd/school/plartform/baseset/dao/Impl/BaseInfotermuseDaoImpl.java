package com.zd.school.plartform.baseset.dao.Impl;

import org.springframework.stereotype.Repository;

import com.zd.core.dao.BaseDaoImpl;
import com.zd.school.oa.terminal.model.InfoTerminalHistory;
import com.zd.school.plartform.baseset.dao.BaseInfotermuseDao;


/**
 * 
 * ClassName: OaInfotermuseDaoImpl
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 终端使用历史(OA_T_INFOTERMUSE)实体Dao接口实现类.
 * date: 2017-01-14
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Repository
public class BaseInfotermuseDaoImpl extends BaseDaoImpl<InfoTerminalHistory> implements BaseInfotermuseDao {
    public BaseInfotermuseDaoImpl() {
        super(InfoTerminalHistory.class);
        // TODO Auto-generated constructor stub
    }
}