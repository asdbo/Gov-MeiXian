<?php include 'Common/index-open-header.html';?>

<div class="blank10"></div>
<div class="yjx_box">
  <div class="mod topbanner" style="background:url(images/441402.jpg) no-repeat;">梅县区政府信息公开意见箱</div>
  <div id="yjx">
    <div class="yjx_le ftl">
	  <div class="le_menu">
	    <ul>
		  <li><a href="http://zwgk.meijiang.gov.cn/zfxxgkzn" target="_blank">政府信息公开指南</a></li>
		  <li><a href="http://zwgk.meijiang.gov.cn/index/index?areacode=441402" target="_blank">政府信息公开目录</a></li>
		  <li><a href="http://zwgk.meijiang.gov.cn/zfgzbg" target="_blank">政府信息公开工作年度报告</a></li>
		  <li><a href="http://ysq.meijiang.gov.cn/" target="_blank">政府信息依申请公开</a></li>
		</ul>
	  </div>
	  <div id="gdbs_yjx"><a href="http://app.gd.gov.cn/zwgk/yjx/default.php" target="_blank"><img src="images/gdbs_yjx.jpg" alt="广东省政府信息公开意见箱"></a></div>
	</div><!-- yjx_le end -->
	
	<div class="yjx_ri ftr">
	  <div id="yjx_title">您的当前位置：<a href="http://www.meijiang.gov.cn/" target="_blank">梅江区委区政府办公室</a> &gt;&gt; <a href="http://yjx.meijiang.gov.cn/complaint?areacode=441402">政府信息公开意见箱</a></div>
            <div class="yjx_content">
    <div class="yjx_menu">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tbody>
                <tr>
                  <td width="75px"><a href="http://yjx.meijiang.gov.cn/complaint/suggest?areacode=441402"><img src="images/but_01.png" alt="我要提出意见建议"></a></td>
                  <td><a href="http://yjx.meijiang.gov.cn/complaint/suggest?areacode=441402">我要提出意见建议</a></td>
                  <td width="75px"><a href="http://yjx.meijiang.gov.cn/complaint/complain?areacode=441402"><img src="images/but_02.png" alt="我要提出投诉举报"></a></td>
                  <td><a href="http://yjx.meijiang.gov.cn/complaint/complain?areacode=441402">我要提出投诉举报</a></td>
                </tr>
                <tr>
                  <td width="75px"><a href="http://yjx.meijiang.gov.cn/complaint/complain/search?areacode=441402"><img src="images/but_03.png" alt="查询投诉举报办理情况"></a></td>
                  <td><a href="http://yjx.meijiang.gov.cn/complaint/complain/search?areacode=441402">查询投诉举报办理情况</a></td>
                  <td width="75px"><img src="images/but_04.png" alt="我想了解相关规定"></td>
                  <td>
                                                    <a href="http://yjx.meijiang.gov.cn/index/list/151/007208816" target="_blank">我想了解相关规定</a>
                                              
                  </td>
                </tr>
                            </tbody>
        </table>
    </div>
</div>	</div><!-- yjx_ri end -->
  </div>
</div>
<script>
function questionDetail(communicateId) {
	var path = "http://mzms.meizhou.gov.cn/msCommunicateWebAction.do?action=viewWebDetail&id="+communicateId;
    window.open(path);
}

function encode(datastr, bassnum) {
	var tempstr;
	var tchar;
	var newdata = "";
	for (var i = 0; i < datastr.length; i++){
		tchar = 65535 + bassnum - datastr.charCodeAt(i);
		tchar = tchar.toString();
		while(tchar.length < 5){ tchar = "0" + tchar; }
		newdata = newdata + tchar;
	}
	return newdata;
}
</script>
<div class="feed-err mod"></div>
<div class="footer mod">
<p class="jiandian">本栏目信息由梅江区委区政府办公室维护</p>
</div>


</body></html>