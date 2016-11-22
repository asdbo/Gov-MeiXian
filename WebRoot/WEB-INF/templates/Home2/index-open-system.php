
   <?php include 'Common/index-open-header.html';?>
    <div class="mod topbanner" style="background:url(images/441402.jpg) no-repeat;">梅县区政府信息依申请公开系统</div>
    
    <div class="blank10 mod"></div>
    <form action='http://zwgk.gd.gov.cn/govsearch/simp_gov_list.jsp' target='_blank' id='trssearchform' name='trssearchform' method='post'>
        <div class='xx_search mod'> <span class='searchTipNew'>全省信息搜索：</span>
            <input id='sword' class='SearchOfInput' type='text' size='55' name='sword'>
            <select id='select' name='searchColumn'>
                <option value='all'>全部</option>
                <option value='zhengwen'>正文</option>
                <option value='biaoti'>名称</option>
                <option value='wenhao'>文号</option>
                <option value='fwdw'>发文单位</option>
                <option value='keywords'>主题词</option>
            </select>
            <select id='label' name='searchYear'>
                <option value='all'>全部时间</option>
                <option value='all'>全部时间</option>
                                <option value='2005'>2005</option>
                                <option value='2006'>2006</option>
                                <option value='2007'>2007</option>
                                <option value='2008'>2008</option>
                                <option value='2009'>2009</option>
                                <option value='2010'>2010</option>
                                <option value='2011'>2011</option>
                                <option value='2012'>2012</option>
                                <option value='2013'>2013</option>
                                <option value='2014'>2014</option>
                                <option value='2015'>2015</option>
                            </select>
            <input id='pubURL' type='hidden' value='' name='pubURL'>
            <input type='hidden' value='1' name='SType'>
            <button id='searchBtn' tabindex='2' onclick='return executeSearch();'>搜索</button>

        </div>
    </form>
    <?php include 'Common/index-open-footer.html';?>