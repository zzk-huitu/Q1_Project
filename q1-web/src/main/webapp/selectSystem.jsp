<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="zh">
<meta charset="utf-8">


    <head>
           <title>Q1  selectSystem</title>
           <link rel="stylesheet" type="text/css" href="${contextPath}/static/core/resources/css/selectSystem.css"> <!--外部样式-->
           <script type="text/javascript" src="${contextPath}/static/core/resources/js/jquery-1.8.3.js"></script>
           <script type="text/javascript" src="${contextPath}/static/core/resources/js/layer/layer.js"></script>

    </head>
    <body style="background-color: #3aa3e7;background-image:url(${contextPath}/static/core/resources/images/selectSystem/index_bg.jpg)">    
    		<div id="welecome_home"> 
    			<img class="img" src="${contextPath}/static/core/resources/images/selectSystem/tubiao1.png" >
    			<label class="welcome_in">欢迎进入智慧校园Q1系统</label>
          <a href="/login/logout" class="welcome_about">退出</a>
    			<a href="#" class="welcome_about">关于</a>
          <a href="#" class="welcome_about">软件注册</a>
    		</div>

    		<div id="center" style="display: flex;align-items: center;justify-content: center;">
    	
    			<div id="sysytem_box">
            <div id="logo_box">
              <img src="${contextPath}/static/core/resources/images/selectSystem/index_logo.png" style="margin-left: -100px;">
            </div>     
    				<div class = "top" style="margin-top: 30px;">
              <ul>
                <li data-menuCode="INTEGRATE_SYSTEM"><img src="${contextPath}/static/core/resources/images/selectSystem/index_zongheguanli.png"></li>
                <li data-menuCode="CARD_CENTER_SYSTEM"><img src="${contextPath}/static/core/resources/images/selectSystem/index_kawuzhongxin.png"></li>
                <li data-menuCode="XF_SYSTEM"><img src="${contextPath}/static/core/resources/images/selectSystem/index_xiaofeiguanli.png"></li>
                <li data-menuCode="SK_SYSTEM"><img src="${contextPath}/static/core/resources/images/selectSystem/index_shuikongguanli.png"></li>
                <li data-menuCode="DK_SYSTEM"><img src="${contextPath}/static/core/resources/images/selectSystem/index_diankongguanli.png"></li>
                <div style="clear: both;"></div>
              </ul>
    				</div>
    			  <div class="bottom">
    					<ul>
                <li data-menuCode="MJ_SYSTEM"><img src="${contextPath}/static/core/resources/images/selectSystem/index_menkongguanli.png"></li>
                <li data-menuCode="SMART_CONTROL_SYSTEM"><img src="${contextPath}/static/core/resources/images/selectSystem/index_zhinengkongzhi.png"></li>
                <li data-menuCode="WISDOM_CLASS_SYSTEM"><img src="${contextPath}/static/core/resources/images/selectSystem/index_zhihuibanpai.png"></li>
                <li data-menuCode="REPORT_CENTER"><img src="${contextPath}/static/core/resources/images/selectSystem/index_jiesuanzhongxin.png"></li>
                <li data-menuCode="ORDER_MEAL_SYSTEM"><img src="${contextPath}/static/core/resources/images/selectSystem/index_dingcanguanli.png"></li>
                <div style="clear: both;"></div>
              </ul>
            </div>
          </div>    
    	 </div>

       <div id="welecome_footer"> 
          <span class="company"></span>
      </div>
  </body>


<script language="JavaScript">


    jQuery(function($) {
      var year = new Date().getFullYear();
      $(".company").html("© " + year + " 深圳市宇川智能系统有限公司 - 智慧校园Q1系统");


     

      $("#sysytem_box li").click(function(){
        /*
        $.ajax({
          url: getContextPath() + '/login/checkRegister',
          dataType: 'json',
          type: 'POST',
          data: {
              //userName: userName
          },
          success: function(xmlRequest) {
              var returninfo = xmlRequest.result
              if (returninfo == 1) {
                document.location.href = getContextPath() + "/login/selectSystem";
              }
              layer.close(loadMask); 
          },
          error: function(XMLHttpRequest, textStatus, errorThrown) {
              //alert(errorThrown + textStatus);
              layer.alert(errorThrown + textStatus ,{title:'提示',icon: 2,closeBtn:0});
          }
        });
        */
        var menuCode=$(this).attr("data-menuCode");
        document.location.href = "/login/desktop?SystemMenuCode="+menuCode;

      });

    });

</script>
</html>