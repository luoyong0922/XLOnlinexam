                    <script src="${rc.contextPath}/static/js/pagehelper.js"></script>

                    <div style="text-align:center;margin-top:30px;">
                        <ul class="pagination">
                            <li><span id="spanFirst">首页</span></li>
                            <li class="previous"><span id="spanPre">上一页</span></li>
                            <li class="next"><span id="spanNext">下一页</span></li>
                            <li><span id="spanLast">末页</span></li>
                        </ul>
                        第<span id="spanPageNum"></span>页/
                        共<span id="spanTotalPage"></span>页，总记录数为${pageInfo.total}条
                    </div>
                    <script>
                        var theTable = document.getElementById("Test"); //要进行分页的表格
                        var spanFirst = document.getElementById("spanFirst");  //第一页
                        var spanPre = document.getElementById("spanPre");     //上一页
                        var spanNext = document.getElementById("spanNext");   //下一页
                        var spanLast = document.getElementById("spanLast");  //最后一页
                        var pageNum = document.getElementById("spanPageNum"); //当前页
                        var totalPage = document.getElementById("spanTotalPage");  //总页数

                        var numberRowsInTable = theTable.rows.length;    //table中<tr>的数量
                        var pageSize = 6;     //一页中数据显示的数量
                        var page = 1;     //
                        hide();
                    </script>