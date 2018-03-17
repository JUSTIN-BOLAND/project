package com.secray.utils;

/**
 * Created by root on 2018/1/31 0031.
 */
public class ControlJsp  extends  Base {
    private String[] querys ;
    private String[] pages;


    public ControlJsp(String table){
        super(table);
    }
    public void setModuleName(){

    }
    public void setCondition( String[] moduleNames,String[] querys, String[] pages){
        this.moduleNames = moduleNames;
        this.querys = querys;
        this.pages = pages;
    }

   public void buildListJsp(){
       String content =  this.buildHeader();;
       content += this.buildContent();
       content += this.buildTail();
       super.buildFile(content,super.methodName+"List.jsp");
   }
    public void buildPageJsp(){
       String[] trTd= {"",""};
        String[] values;
        for(String page: this.pages){
            values = page.split(",");
            if(values[0].toLowerCase().equals("status"))   {
                trTd[0] += "\t\t\t\t\t<td height=\"22\" align=\"center\" valign=\"middle\">\n" +
                           "\t\t\t\t\t\t\t<c:if test=\"${"+this.methodName+".status == '0'}\">\n" +
                        "\t\t\t\t\t\t\t\t启用\n" +
                        "\t\t\t\t\t\t\t</c:if>\n" +
                        "\t\t\t\t\t\t\t<c:if test=\"${"+this.methodName+".status == '1'}\" >\n" +
                        "\t\t\t\t\t\t\t<font color=\"red\">\n" +
                        "\t\t\t\t\t\t\t\t\t   禁用\n" +
                        "\t\t\t\t\t\t\t </font>\n" +
                        "\t\t\t\t\t\t\t</c:if>\n" +
                         "\t\t\t\t\t</td>\n" ;
            }
           else {
                trTd[0] += "\t\t\t\t\t<td height=\"22\" align=\"center\" valign=\"middle\">\n" +
                           "\t\t\t\t\t\t\t${"+this.methodName+"."+values[0]+"}\n" +
                           "\t\t\t\t\t</td>\n" ;;
            }
            if(values[1]!=null && values[1].trim().length() > 0 ) trTd[1] += "\t\t\t\t\t<th>"+values[1]+"</th>\n" ;
        }
       String content="<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
               "<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%>\n" +
               "<%@ taglib uri=\"http://java.sun.com/jsp/jstl/fmt\" prefix=\"fmt\"%>\n" +
               "<%@ taglib prefix=\"c\" uri=\"http://java.sun.com/jsp/jstl/core\"%>\n" +
               "<%@ taglib prefix=\"fn\" uri=\"http://java.sun.com/jsp/jstl/functions\"%>\n" +
               "<%@ taglib prefix=\"t\" uri=\"/WEB-INF/tags/page\" %>\n" +
               "\n" +
               "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
               "<head>\n" +
               "\n" +
               "</head>\n" +
               "<body>\n" +
               "  <div class=\"table-responsive\">\n" +
               "\t<table class=\"table table-hover\">\n" +
               "\t\t \t<thead>\n" +
               "\t\t \t\t<tr>\n" +
               trTd[1]+
               "\t\t\t\t\t<th>操作</th>\n" +
               "\t\t \t\t</tr>\n" +
               "\t\t \t</thead>\n" +
               "\n" +
               "\t\t \t<tbody>\n" +
               "\n" +
               "\t\t\t<c:forEach items=\"${page.results}\" var=\""+this.methodName+"\">\n" +
               "\t\t\t\t<tr>\n" +
               trTd[0]+


               "\n" +
               "\t\t\t\t\t\t  <td height=\"22\" align=\"center\" valign=\"middle\">\n" +
               "\t\t\t\t\t\t\t  <c:if test=\"${"+super.methodName+".status eq '0'}\">\n" +
               "\t\t\t\t\t\t\t\t  <t:buttonOut url=\"/"+super.methodName+"/disable.html\">\n" +
               "\t\t\t\t\t\t\t\t\t  <input type=\"button\" value=\"停用\"   onclick=\"f_disable('${"+super.methodName+".id}')\"/>\n" +
               "\t\t\t\t\t\t\t\t  </t:buttonOut>\n" +
               "\t\t\t\t\t\t\t  </c:if>\n" +
               "\t\t\t\t\t\t\t  <c:if test=\"${"+this.methodName+".status eq '1'}\">\n" +
               "\t\t\t\t\t\t\t\t  <t:buttonOut url=\"/"+super.methodName+"/enable.html\">\n" +
               "\t\t\t\t\t\t\t\t\t  <input type=\"button\" value=\"启用\"   onclick=\"f_enable('${"+super.methodName+".id}')\"/>\n" +
               "\t\t\t\t\t\t\t\t  </t:buttonOut>\n" +
               "\t\t\t\t\t\t\t  </c:if>\n" +
               "\t\t\t\t\t\t\t  <t:buttonOut url=\"/"+super.methodName+"/toEdit.html\">\n" +
               "\t\t\t\t\t\t\t\t<input type=\"button\" value=\"修改\"   onclick=\"f_toEdit('${"+super.methodName+".id}')\"/>\n" +
               "\t\t\t\t\t\t\t  </t:buttonOut>\n" +
               "\n" +
               "\t\t\t\t\t\t\t<t:buttonOut url=\"/"+super.methodName+"/delete.html\">\n" +
               "\t\t\t\t\t\t\t\t<input type=\"button\" value=\"删除\" onclick=\"f_delete('${"+super.methodName+".id}')\"/>\n" +
               "\t\t\t\t\t\t\t</t:buttonOut>\n" +
               "\n" +
               "\n" +
               "\t\t\t\t\t\t  </td>\n" +
               "\t\t\t\t</tr>\n" +
               "\t\t</c:forEach>\n" +
               "\t\t</tbody>\n" +
               "\t</table>\n" +
               "\n" +
               "\t</div>\n" +
               "\t<div class=\"main-pading clear\">\n" +
               "\t   <%@ include file=\"/WEB-INF/views/page.jsp\"%>\n" +
               "\t</div>\n" +
               "\n" +
               "\t</body>\n" +
               "\t</html>";
        super.buildFile(content,"page"+super.entityName+".jsp");
    }
    private String  buildHeader(){
        String headerContent = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\"\n" +
                "\tpageEncoding=\"UTF-8\"%>\n" +
                "<%@ taglib prefix=\"c\" uri=\"http://java.sun.com/jsp/jstl/core\"%>\n" +
                "<%@ taglib prefix=\"fn\" uri=\"http://java.sun.com/jsp/jstl/functions\"%>\n" +
                "<%@ taglib prefix=\"t\" uri=\"/WEB-INF/tld/deyi.tld\"%>\n" +
                "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "<head>\n" +
                "\t<style>\n" +
                "\n" +
                "\t\t.arcle-grap input[type=text] {\n" +
                "\t\t\tmax-width: 260px;\n" +
                "\t\t\twidth: 20%;\n" +
                "\t\t\tmin-width: 230px !important;\n" +
                "\t\t\theight: 40px;\n" +
                "\t\t\tmargin-left: 6px !important;\n" +
                "\n" +
                "\t\t\tmargin-right: 8px;\n" +
                "\t\t\tborder: 1px solid #999;\n" +
                "\t\t}\n" +
                "\t\t.arcle-grap span {\n" +
                "\t\t\tdisplay: inline-block;\n" +
                "\t\t\tmargin-bottom: 20px;\n" +
                "\n" +
                "\t\t}\n" +
                "\t</style>\n" +
                "<script type=\"text/javascript\">\n" +
                "\tlayer.config({\n" +
                "\t\tpath: '<%=request.getContextPath()%>/man/js/layer/',\n" +
                "\t\textend:'extend/layer.ext.js'\n" +
                "\t});\n" +
                "\t\t\tfunction f_delete(id){\n" +
                "         \t\tlayer.confirm('是否删除？', {\n" +
                "  \t    \t\t  btn: ['确定','取消'] //按钮\n" +
                "  \t    \t\t},\n" +
                "  \t    \t\tfunction(){\n" +
                "  \t    \t\t\t$.ajax({\n" +
                "  \t            \t\turl: '<%=request.getContextPath()%>/"+super.methodName+"/delete.html?id='+id,\n" +
                "  \t            \t\tcontext: document.body,\n" +
                "  \t            \t\tasync: false,\n" +
                "  \t            \t\tsuccess: function(data){\n" +
                "  \t            \t\t\tif(data.success){\n" +
                "  \t            \t\t\t\tquery('<%=request.getContextPath()%>/"+super.methodName+"/page.html');\n" +
                "  \t            \t\t\t\tlayer.closeAll();\n" +
                "  \t            \t\t\t}else{\n" +
                "  \t            \t\t\t\tlayer.msg('删除失败！',{icon:5});\n" +
                "  \t            \t\t\t}\n" +
                "  \t            \t\t}\n" +
                "  \t            \t});\n" +
                "  \t    \t\t}\n" +
                "  \t    \t);\n" +
                "\t\t\t}\n" +
                "\n" +
                "\n" +
                "\n" +
                "\t\t\tfunction f_disable(id){\n" +
                "         \t\tlayer.confirm('是否停用？', {\n" +
                "  \t    \t\t  btn: ['确定','取消'] //按钮\n" +
                "  \t    \t\t},\n" +
                "  \t    \t\tfunction(){\n" +
                "  \t    \t\t\t$.ajax({\n" +
                "  \t            \t\turl: '<%=request.getContextPath()%>/"+super.methodName+"/disable.html?id='+id,\n" +
                "  \t            \t\tcontext: document.body,\n" +
                "  \t            \t\tasync: false,\n" +
                "  \t            \t\tsuccess: function(data){\n" +
                "  \t            \t\t\tif(data.success){\n" +
                "  \t            \t\t\t\tquery('<%=request.getContextPath()%>/"+super.methodName+"/page.html');\n" +
                "  \t            \t\t\t\tlayer.closeAll();\n" +
                "  \t            \t\t\t}else{\n" +
                "  \t            \t\t\t\tlayer.msg('失败！',{icon:5});\n" +
                "  \t            \t\t\t}\n" +
                "  \t            \t\t}\n" +
                "  \t            \t});\n" +
                "  \t    \t\t}\n" +
                "  \t    \t);\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\tfunction f_enable(id){\n" +
                "         \t\tlayer.confirm('是否启用？', {\n" +
                "  \t    \t\t  btn: ['确定','取消'] //按钮\n" +
                "  \t    \t\t},\n" +
                "  \t    \t\tfunction(){\n" +
                "  \t    \t\t\t$.ajax({\n" +
                "  \t            \t\turl: '<%=request.getContextPath()%>/"+super.methodName+"/enable.html?id='+id,\n" +
                "  \t            \t\tcontext: document.body,\n" +
                "  \t            \t\tasync: false,\n" +
                "  \t            \t\tsuccess: function(data){\n" +
                "  \t            \t\t\tif(data.success){\n" +
                "  \t            \t\t\t\tquery('<%=request.getContextPath()%>/"+super.methodName+"/page.html');\n" +
                "  \t            \t\t\t\tlayer.closeAll();\n" +
                "  \t            \t\t\t}else{\n" +
                "  \t            \t\t\t\tlayer.msg('启用失败！',{icon:5});\n" +
                "  \t            \t\t\t}\n" +
                "  \t            \t\t}\n" +
                "  \t            \t});\n" +
                "  \t    \t\t}\n" +
                "  \t    \t);\n" +
                "\t\t\t}\n" +
                "\t\t\t//编辑商户\n" +
                "\t\t\tfunction f_toEdit(id){\n" +
                "\t\t    \t  changeRightMenu('../"+super.methodName+"/toEdit.html?id='+id);\n" +
                "\t\t\t  }\n" +
                "\n" +
                "\n" +
                "\n" +
                "\t\t\t//商户详情\n" +
                "\t\t\tfunction f_details(id){\n" +
                "\t\t    \t  changeRightMenu('../"+super.methodName+"/deale.html?id='+id);\n" +
                "\t\t\t  }\n" +
                "\n" +
                "\t\t\tfunction f_checkAppIdExist(mechantId){\n" +
                "\t\t\t\tv_result = true;\n" +
                "\t\t\t\t$.ajax({\n" +
                "\t\t\t\t\turl: '<%=request.getContextPath()%>/dealer/checkAppIdExist.html?mechantId='+mechantId,\n" +
                "\t\t\t\t\tcontext: document.body,\n" +
                "\t\t\t\t\tasync: false,\n" +
                "\t\t\t\t\tdataType:'json',\n" +
                "\t\t\t\t\tsuccess: function(data){\n" +
                "\t\t\t\t\t\tif(data.success){\n" +
                "\t\t\t\t\t\t\t//layer.msg('验证成功！',{icon:1});\n" +
                "\t\t\t\t\t\t\t//layer.closeAll();\n" +
                "\n" +
                "\t\t\t\t\t\t}else{\n" +
                "\t\t\t\t\t\t\tlayer.msg('请在设置中配置 该商户的支付宝AppId！',{icon:5});\n" +
                "\t\t\t\t\t\t\tv_result= false;\n" +
                "\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t});\n" +
                "\t\t\t\treturn v_result;\n" +
                "\t\t\t}\n" +
                "\t      $().ready(function(){\n" +
                "\t\t\t  $(\"#exportBtn\").click(function(){\n" +
                "\t\t\t\t  document.getElementById(\"query_from\").action=\"<%=request.getContextPath()%>/"+super.methodName+"/export.html\";\n" +
                "\t\t\t\t  document.getElementById(\"query_from\").submit();\n" +
                "\t\t\t\t  document.getElementById(\"query_from\").action=\"<%=request.getContextPath()%>/"+super.methodName+"/page.html\";\n" +
                "\t\t\t  });\n" +
                "\t\t  });\n" +
                "\n" +
                "\n" +
                "\n" +
                "\t\tfunction passwordReset(id){\n" +
                "\t\t\tlayer.confirm('确定重置密码？', {\n" +
                "\t    \t\t  btn: ['确定','取消'] //按钮\n" +
                "\t    \t\t},\n" +
                "\t    \t\tfunction(){\n" +
                "\t    \t\t\t$.ajax({\n" +
                "  \t            \t\turl: '<%=request.getContextPath()%>/"+super.methodName+"/passwordReset.html?id='+id,\n" +
                "  \t            \t\tcontext: document.body,\n" +
                "  \t            \t\tasync: false,\n" +
                "  \t            \t\tsuccess: function(data){\n" +
                "  \t            \t\t\tif(data.success){\n" +
                "  \t            \t\t\t\tlayer.msg('重置成功',{icon:1});\n" +
                "  \t            \t\t\t\tchangeRightMenu('<%=request.getContextPath()%>/"+super.methodName+"/list.html');\n" +
                "  \t            \t\t\t\tlayer.closeAll();\n" +
                "  \t            \t\t\t}else{\n" +
                "  \t            \t\t\t\tlayer.msg('',{icon:5});\n" +
                "  \t            \t\t\t}\n" +
                "  \t            \t\t}\n" +
                "  \t            \t});\n" +
                "\t    \t\t});\n" +
                "\t\t}\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\t\t</script>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "\t<section class=\"navigation\">\n" +
                "\t<span>您当前所在的位置：</span><a href=\"#\" title=\"\">"+this.moduleNames[0]+"管理</a>/<i>"+moduleNames[1]+"</i>\n" +
                "\t\t<t:buttonOut url=\"/"+super.methodName+"/toAdd.html\">\n" +
                "\t\t\t<input class=\"grap-btn\" type=\"button\" style=\"line-height: 0px;float:right;\"\n" +
                "\t\t\t\t   onclick=\"changeRightMenu('../"+super.methodName+"/toAdd.html');\"\n" +
                "\t\t\t\t   value=\"添加"+this.moduleNames[0]+"\" />\n" +
                "\t\t</t:buttonOut>\n" +
                "\t</section>\n" +
                "\t<section class=\"main-cont\">\n" +
                "\t<div class=\"article\">\n" +
                "\t\t<form id=\"query_from\">\n" +
                "\t\t\t<div class=\"arcle-grap\">";

        return headerContent;
    }
    private String  buildContent(){
        String content="";
        String[] values;
        int row=0;
        for(int i=0; i< this.querys.length; i++){
             values = querys[i].split(",");
             if(i % 3 == 0 ){
                 double p = Double.parseDouble(querys.length+"")/3d;
                 int rowIdx = (int)Math.ceil(p);
                 if(rowIdx<=1){
                     content +="\n<input class=\"grap-btn\" type=\"button\" style=\"\"\n" +
                             "\t\t\t\t\t\t   onclick=\"query('<%=request.getContextPath()%>/"+super.methodName+"/page.html');\"\n" +
                             "\t\t\t\t\t\t   value=\"查询\" />\n";
                     content += "\n" +
                             "\t\t\t\t\t<input class=\"grap-btn\" type=\"button\"  style=\"float:right\"\n" +
                             "\t\t\t\t\t\tid=\"exportBtn\"\n" +
                             "\t\t\t\t\t\tvalue=\"导出\" />\n";

                     content += "\n<div>"+content+"</div>\n";
                 }
                 else {
                     if (row == 1) content += "\n" +
                             "\t\t\t\t\t<input class=\"grap-btn\" type=\"button\"  style=\"float:right\"\n" +
                             "\t\t\t\t\t\tid=\"exportBtn\"\n" +
                             "\t\t\t\t\t\tvalue=\"导出\" />\n";
                     else if (row == 2)
                         content += "\n<input class=\"grap-btn\" type=\"button\" style=\"float:right\"\n" +
                                 "\t\t\t\t\t\t   onclick=\"query('<%=request.getContextPath()%>/" + super.methodName + "/page.html');\"\n" +
                                 "\t\t\t\t\t\t   value=\"查询\" />\n";
                     if (i == 0) content += "\n<div>\n";
                     else if (i == this.querys.length - 1) content += "\n</div>\n";
                     else content += "\n</div>\n<div>\n";
                 }

                 row++;
             }

                 if("INPUT".equals(values[0].toUpperCase())) {
                     content += "\n<span style=\"width:100px;\">"+values[1]+"</span><input class=\"grap-txt\" type=\"text\" name=\""+values[2]+"\" placeholder=\""+values[1]+"\" />";
                 }
                 else  if("SELECT".equals(values[0].toUpperCase())) {
                     content += "\n<span style=\"width:69px;\">"+values[1]+"</span>\n<select class=\"grap-sel\" name=\""+values[2]+"\" style=\"margin-left: 0px;\">\n" +
                             "\t\t\t\t\t\t<option value=\"\">全部</option>\n" +

                             "\t\t\t\t\t</select>";
                 }

        }


            if ((this.querys.length - 1) % 3 != 0) {
                content += "\n<input class=\"grap-btn\" type=\"button\" style=\"float:right\"\n" +
                        "\t\t\t\t\t\t   onclick=\"query('<%=request.getContextPath()%>/" + super.methodName + "/page.html');\"\n" +
                        "\t\t\t\t\t\t   value=\"查询\" />";
            } else if ((this.querys.length - 1) % 3 == 0) {
                content += "\n</div>\n";
            }

        return content;
    }
    private String  buildTail(){
       String tailContent = "\t\t</div>\n" +
               "\t\t\t<%@ include file=\"/WEB-INF/views/pageSize.jsp\"%>\n" +
               "\t\t</form>\n" +
               "\t\t<div id=\"page_data\"></div>\n" +
               "\t\t<!-- 加载初始数据 -->\n" +
               "\t\t<script type=\"text/javascript\">\n" +
               "\t \t\t\t\t\t var url = '<%=request.getContextPath()%>/"+super.methodName+"/page.html';\n" +
               "\t\t\t$(\"#page_data\").load(url);\n" +
               "\t\t</script>\n" +
               "\n" +
               "\n" +
               "\n" +
               "\t</div>\n" +
               "\t</section>\n" +
               "\n" +
               "</body>\n" +
               "</html>";
        return tailContent;
    }

    public static void main(String[] args){
        String[] moduleNames = {"设备","设备列表"};
        String[] querys = {"input,设备名称,deviceName","input,经销商名称,memo","select,设备分类,deviceType",
                           "input,设备序列号,flag","input,机器编号,deviceCode","select,状态,status"};
        String[] pages = {"deviceName,设备名称","memo,经销商名称","deviceType,设备分类","flag,设备列序号","deviceCode,机器编号","machineStatus,机器状态","status,业务状态","qrCode,二维码"};
        String[] exportFields = {"批号,卡号,姓名,身份证,手机号,账户余额,充值账户余额,业务状态,创建时间",
                                 "batchNo,cardNo,name,idCardNo,mobile,balance,deposit,status,createTime",
                                 "一卡通"};
        ControlJsp controlJsp = new ControlJsp("t_card");
        controlJsp.setExportFields(exportFields);
        controlJsp.setCondition(moduleNames,querys,pages);
        controlJsp.buildListJsp();
        controlJsp.buildPageJsp();
        controlJsp.buildActionFile();System.out.println(5 %3 +" : "+5/3);


    }
}
