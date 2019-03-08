<!-- 提示分页信息行 -->
<div class="row">
    <!-- 分页文字信息 ：拿到控制器处理请求时封装在pageInfo里面的分页信息-->
    <div class="col-md-6">
        当前${pageInfo.pageNum}页,共${pageInfo.pages }页,总${pageInfo.total }条记录
    </div>
    <!-- 分页码 -->
    <div class="col-md-6">
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <!--
                        1.pageContext.request.contextPath表示当前项目路径，采用的是绝对路径表达方式。一般为http:localhost:8080/项目名 。
                        2.首页，末页的逻辑：pn=1访问第一次，pn=${pageInfo.pages}访问最后一页
                      -->
                <li>
                    <a value="1">首页</a>
                </li>
                <!-- 如果还有前页就访问当前页码-1的页面， -->
            <#if pageInfo.hasPreviousPage>
                <li>
                    <a value="${pageInfo.pageNum-1}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
            </#if>
            <li>
                <!--遍历所有导航页码，如果遍历的页码页当前页码相等就高亮显示，如果相等就普通显示  -->
            <#list pageInfo.navigatepageNums as page_Nums>

                <#if page_Nums==pageInfo.pageNum >
                    <li class="active"><a href="#">${page_Nums}</a></li>
                </#if>
                <#if page_Nums!=pageInfo.pageNum >
                    <li >
                        <a value="${page_Nums}">${page_Nums}</a>
                    </li>
                </#if>
            </#list>
                </li>
                <!-- 如果还有后页就访问当前页码+1的页面， -->
            <#if pageInfo.hasNextPage>
                <li>
                    <a value="${pageInfo.pageNum+1}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </#if>
                <li>
                    <a value="${pageInfo.pages}">末页</a>
                </li>
            </ul>
        </nav>
    </div>
</div>