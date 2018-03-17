package com.secray.utils;

/**
 * Created by root on 2018/2/1 0001.
 */
public class JspBase extends Base {
    public static final int ADD = 1;
    public static final int EDIT = 2;
    private int optType;


    public JspBase(int optType,String table){
        super(table);
        this.optType = optType;

    }
    private String[] upload={"\n<link href=\"<%=request.getContextPath()%>/man/js/bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\">\n" +
            "<script type=\"text/javascript\" src=\"<%=request.getContextPath()%>/man/js/bootstrap/js/bootstrap.js\"></script>\n" +
            "<link href=\"<%=request.getContextPath()%>/man/js/fileinput/css/fileinput.css\" media=\"all\" rel=\"stylesheet\" type=\"text/css\"/>\n" +
            "<script src=\"<%=request.getContextPath()%>/man/js/fileinput/js/fileinput.js\" type=\"text/javascript\"></script>\n" +
            "<script src=\"<%=request.getContextPath()%>/man/js/fileinput/js/locales/zh.js\" type=\"text/javascript\"></script>\n",
                               "enctype=\"multipart/form-data\"",
                               "\n       function loadImg( file){\n" +
                                       "           //获取文件\n" +
                                       "          // var file = $(\".form-add\").find(\"input\")[0].files[0];\n" +
                                       "\n" +
                                       "           //创建读取文件的对象\n" +
                                       "           var reader = new FileReader();\n" +
                                       "\n" +
                                       "           //创建文件读取相关的变量\n" +
                                       "           var imgFile;\n" +
                                       "\n" +
                                       "           //为文件读取成功设置事件\n" +
                                       "           reader.onload=function(e) {\n" +
                                       "               //alert('文件读取完成');\n" +
                                       "               imgFile = e.target.result;\n" +
                                       "               console.log(imgFile);\n" +
                                       "               $(\"#previewImg\").attr('src', imgFile);\n" +
                                       "           };\n" +
                                       "\n" +
                                       "           //正式读取文件\n" +
                                       "           reader.readAsDataURL(file);\n" +
                                       "       }\n"+
                                       "\nfunction f_initFileInput() {\n" +
                                       "\n" +
                                       "                $('input[type=file]').each(function(){\n" +
                                       "                    //alert($(this).attr(\"id\")+\" : \"+$(this).maxFileCount);\n" +
                                       "                    var maxFileCount = 1 ;\n" +
                                       "                    var showPreview=true;\n" +
                                       "                    var showCaption=true;\n" +
                                       "                    var dropZoneEnabled=false;\n" +
                                       "                    var showUpload = false;\n" +
                                       "                    if($(this).attr(\"maxFileCount\")){\n" +
                                       "                        if($(this).attr(\"maxFileCount\")==\"true\") maxFileCount = true;\n" +
                                       "                        else maxFileCount =false;\n" +
                                       "                    }\n" +
                                       "                    if($(this).attr(\"showPreview\")){\n" +
                                       "                        if( $(this).attr(\"showPreview\") == \"true\") showPreview = true;\n" +
                                       "                        else  showPreview = false;\n" +
                                       "                    }\n" +
                                       "                    if($(this).attr(\"showCaption\")){\n" +
                                       "                        if($(this).attr(\"showCaption\")==\"true\") showCaption = true;\n" +
                                       "                        else  showCaption = false;\n" +
                                       "                    }\n" +
                                       "                    if($(this).attr(\"dropZoneEnabled\")){\n" +
                                       "                        if($(this).attr(\"dropZoneEnabled\") ==\"true\") dropZoneEnabled = true;\n" +
                                       "                        else dropZoneEnabled = false;\n" +
                                       "                    }\n" +
                                       "                    if($(this).attr(\"showUpload\")){\n" +
                                       "                        if($(this).attr(\"showUpload\") ==\"true\") showUpload = true;\n" +
                                       "                        else showUpload = false;\n" +
                                       "                    }\n" +
                                       "                    //alert(\"1 : \"+$(this).attr(\"showPreview\")+\" : \"+$(this).attr(\"showCaption\")+\" : \"+$(this).attr(\"dropZoneEnabled\"));\n" +
                                       "                    //alert(\"2 : \"+showPreview+\" : \"+showCaption+\" : \"+dropZoneEnabled);\n" +
                                       "                    $(this).fileinput({\n" +
                                       "                        language: 'zh', //设置语言\n" +
                                       "                        uploadUrl:  $(this).attr(\"uploadUrl\"), //上传的地址\n" +
                                       "\n" +
                                       "                        allowedFileExtensions:  ['jpg', 'gif', 'png', 'jpeg', 'bmp'],//接收的文件后缀\n" +
                                       "                        uploadAsync: false, //默认异步上传\n" +
                                       "                        showUpload: showUpload, //是否显示上传按钮\n" +
                                       "                        showRemove : true, //显示移除按钮\n" +
                                       "                        showPreview : showPreview, //是否显示预览\n" +
                                       "                        showCaption:  showCaption,//是否显示标题\n" +
                                       "                        browseClass: \"btn btn-primary\", //按钮样式\n" +
                                       "                        dropZoneEnabled: dropZoneEnabled,//是否显示拖拽区域\n" +
                                       "                        previewFileIcon: \"<i class='glyphicon glyphicon-king'></i>\",\n" +
                                       "                        maxFileCount: maxFileCount, //表示允许同时上传的最大文件个数\n" +
                                       "                        enctype: 'multipart/form-data',\n" +
                                       "                        validateInitialCount:true,\n" +
                                       "                        previewFileIcon: \"<i class='glyphicon glyphicon-king'></i>\",\n" +
                                       "                        msgFilesTooMany: \"选择上传的文件数量({n}) 超过允许的最大数值{m}！\",\n" +
                                       "                    }).on(\"fileuploaded\", function (e, data) {\n" +
                                       "                        var res = data.response;\n" +
                                       "                        if (res.status > 0) {\n" +
                                       "                            alert('上传成功');\n" +
                                       "                            //alert(res.path);\n" +
                                       "                        }\n" +
                                       "                        else {\n" +
                                       "                            alert('上传失败')\n" +
                                       "                        }\n" +
                                       "                    }).on(\"filebatchselected\", function(event, files) {\n" +
                                       "                        //alert(files);\n" +
                                       "                        //$(this).fileinput(\"upload\");\n" +
                                       "                    });\n" +
                                       "                });\n" +
                                       "            }\n",
                                        "              f_initFileInput();\n" +
                                        "              $('#previewImg').click(function () {\n" +
                                        "                  $(\"#adlogofile\").click();\n" +
                                        "              });\n" +
                                        "              $(\"#adlogofile\").on(\"filebatchselected\", function(event, files) {\n" +
                                        "                  $(\"#adLogo\").val( $(\"#adlogofile\").val());\n" +
                                        "                  loadImg(files[0]);\n" +
                                        "              });\n" };
    private String buildHeader(){
       String content ="<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
               "<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%>\n" +
               "<%@ taglib prefix=\"c\" uri=\"http://java.sun.com/jsp/jstl/core\"%>\n" +
               "<%@ taglib prefix=\"fn\" uri=\"http://java.sun.com/jsp/jstl/functions\"%>\n" +
               "\n" +

               "      \n" +
               "        <section class=\"navigation\">\n" +
               "          <span>您当前所在的位置：</span><a  href=\"#\" title=\"\">"+moduleNames[0]+"</a>/<i>"+moduleNames[1]+"</i>\n" +
               "        </section>\n" +
               "        <section class=\"main-cont\">\n" +
               "        <div class=\"article\">\n" +
               "              <form class=\"form-add\" id=\"formadd\" action=\"\"   method=\"post\" "+upload[1]+">\n" +
               "                  <input class=\"form-txt\" type=\"hidden\" name=\"id\" value=\"${"+this.methodName+".id}\">\n";
        return content;
    }
    private String buildContent(){
        String content ="";
        String[] values= null;
        String disValue="";
        for(String field: this.addFields){
            values = field.split(",");
            if(this.optType == JspBase.ADD) disValue="";
            else disValue=" value=\"${"+this.methodName+"."+values[2]+"}\"";
            if("MULTIPLE".equals(values[0].toUpperCase())){
                content += "\n <div class=\"form-group\">\n" +
                        "                  <label class=\"form-lal\" ><i class=\"text-danger\">*</i>"+values[1]+"：</label>\n" +
                        "                  <select   id=\""+values[2]+"\"  name=\""+values[2]+"\" multiple=\"multiple\" class=\"form-sel\">\n" +
                        "                  </select>\n" +
                        "                  <span class=\"form-yz Validform_checktip\"></span>\n" +
                        "              </div>\n";
            }
            else if("SELECT".equals(values[0].toUpperCase())){
               content += "\n <div class=\"form-group\">\n" +
                       "                  <label class=\"form-lal\"><i class=\"text-danger\">*</i>"+values[1]+"：</label>\n" +
                       "                  <select   id=\""+values[2]+"\"  name=\""+values[2]+"\" class=\"form-sel\">\n" +
                       "\n" +
                       "                      <c:forEach items=\"${"+values[3]+" }\" var=\"item\" varStatus=\"idx\">\n" +
                       "                          <c:choose>\n" +
                       "                            <c:when test=\"${item.code  == "+this.methodName+"."+values[2]+" }\">\n" +
                       "                                <option value=\"${item.code }\" selected>${item.name }</option>\n" +
                       "                            </c:when>\n" +
                       "                             <c:otherwise>\n" +
                       "                                 <option value=\"${item.code }\">${item.name }</option>\n" +
                       "                             </c:otherwise>\n" +
                       "\n" +
                       "                          </c:choose>\n" +
                       "\n" +
                       "                      </c:forEach>\n" +
                       "                  </select>\n" +
                       "                  <span class=\"form-yz Validform_checktip\"></span>\n" +
                       "              </div>\n";
            }
            else  if("TEXT".equals(values[0].toUpperCase())){
                 content +="\n<div class=\"form-group\">\n" +
                         "                  <label class=\"form-lal\"><i class=\"text-danger\">*</i>"+values[1]+"：</label>\n" +
                         "\n" +
                         "                  <textarea class=\"form-cont\" name=\""+values[2]+"\" "+disValue+" rows=\"4\"\n" +
                         "                            style=\"width: 18%;\" datatype=\"*\" sucmsg=\"&nbsp;\" nullmsg=\"不能为空\" errormsg=\"请输入"+values[1]+"\">${"+this.methodName+"."+values[2]+"}</textarea>\n" +
                         "                  <span class=\"form-yz Validform_checktip\"></span>\n" +
                         "              </div>\n";
            }
            else  if("UPLOAD".equals(values[0].toUpperCase())){
                 if(this.optType == JspBase.ADD) disValue = "/man/images/upload.png";
                 content +="\n <div class=\"form-group\">\n" +
                         "\n" +
                         "                    <label class=\"form-lal\"><i class=\"text-danger\">*</i>"+values[1]+"：</label>\n" +
                         "                    <img id=\"previewImg\" style=\"z-index:1;min-width:100px;min-height:100px;max-width:500px;max-height:400px;\"\n" +
                         "                         src=\"<%=request.getContextPath()%>/${"+this.methodName+"."+values[2]+"}\" >\n" +
                         "                    <div style=\"display:none;\">\n" +
                         "                        <input id=\""+values[2].toLowerCase()+"file\" name=\"adlogofile\"   type=\"file\"   class=\"file\" showPreview=\"false\" showCaption=\"true\" dropZoneEnabled=\"false\" >\n" +
                         "                    </div>\n" +
                         "\t\t\t\t\t<span class=\"form-yz Validform_checktip\"></span> \n" +
                         "\t\t\t\t\t<input type=\"hidden\" id=\""+values[2]+"\" name=\""+values[2]+"\" datatype=\"*\" value=\"${dealer.adLogo}\" sucmsg=\"&nbsp;\" nullmsg=\"不能为空\" errormsg=\"请选择"+values[1]+"\" />\n" +
                         "\t\t\t\t</div>\n";
            }
            else{
                content +="\n <div class=\"form-group\">\n" +
                        "                  <label class=\"form-lal\"><i class=\"text-danger\">*</i>"+values[1]+"：</label>\n" +
                        "                  <input class=\"form-txt form-adv-txt\" type=\"text\" name=\""+values[2]+"\" value=\"${"+this.methodName+"."+values[2]+"}\"\n" +
                        "                         style=\"width: 18%;\" datatype=\""+values[3]+"\" sucmsg=\"&nbsp;\" nullmsg=\"不能为空\" errormsg=\"输入的"+values[1]+"不对\">\n" +
                        "                  <span class=\"form-yz Validform_checktip\"></span>\n" +
                        "              </div>\n";
            }
        }
        return content;
    }
    private String buildTail() {
        String content = "<div class=\"form-group\">\n" +
                "                      <label class=\"form-lal\">&nbsp;</label>\n" +
                "                      <input class=\"form-btn\" type=\"submit\" name=\"\" value=\"保存\"/>\n" +
                "                      <input class=\"form-btn\" type=\"button\" name=\"\" value=\"返回\"  onclick=\"myClose()\"/>\n" +
                "              </div>\n" +
                "              \n" +
                "          </form>\n" +
                "          </div>\n" +
                "        </section>\n" +
                "\n" +
                "\n" +
                "   \n" +
                "   <script type=\"text/javascript\">\n" +
                this.upload[2]+

                "\n" +
                "       //表单验证\n" +
                "\t      $(function(){\n" +
                this.upload[3]+
                "\n" +
                "\t\t    $(\".form-add\").Validform({tiptype:3,\n" +
                "                datatype:{\n" +
                "                    \"uniqueDealerName\":function(gets,obj,curform,regxp){\n" +
                "                        /*参数gets是获取到的表单元素值，\n" +
                "                         obj为当前表单元素，\n" +
                "                         curform为当前验证的表单，\n" +
                "                         regxp为内置的一些正则表达式的引用。*/\n" +
                "                        var falg = false;\n" +
                "                        $.ajax({\n" +
                (this.optType == JspBase.EDIT?
        "                            url: '<%=request.getContextPath()%>/"+this.methodName+"/uniqueUpdate"+this.entityName+"Name.html?"+this.methodName+"Name='+gets+'&old"+this.entityName+"Name=${"+this.methodName+"."+this.methodName+"Name}',\n"
                : "                            url: '<%=request.getContextPath()%>/"+this.methodName+"/unique"+this.entityName+"Name.html?"+this.methodName+"Name='+gets,\n"
                )+
                "                            context: document.body,\n" +
                "                            async: false,\n" +
                "                            timeout:5000,\n" +
                "                            success: function(date){\n" +
                "                                falg = date;\n" +
                "                            }\n" +
                "                        });\n" +
                "                        return falg;\n" +
                "                    },\n" +
                "                 \"userName\":/^[\\u4E00-\\u9FA5\\uf900-\\ufa2d]{2,6}$/,\n" +
                "                 \"account\":/^[^\\u4E00-\\u9FA5\\uf900-\\ufa2d]{2,10}$/,\n" +
                "                 \"money\"  : /^\\d+(\\.\\d{2})?$/,\n" +
                "                 \"serial\"  : /^\\d{8}$/,\n" +
                "                 \"n2\"  : /^\\d{2}$/,\n" +
                "                 \"n5\"  : /^\\d{5}$/,\n" +
                "                 \"busLicenceNo\":/^.{15,18}$/,\n" +
                "                 idCard:function(gets,obj,curform,regxp){\n" +
                "                    var aCity={11:\"北京\",12:\"天津\",13:\"河北\",14:\"山西\",15:\"内蒙古\",21:\"辽宁\",22:\"吉林\",23:\"黑龙江\",31:\"上海\",32:\"江苏\",33:\"浙江\",34:\"安徽\",35:\"福建\",36:\"江西\",37:\"山东\",41:\"河南\",42:\"湖北\",43:\"湖南\",44:\"广东\",45:\"广西\",46:\"海南\",50:\"重庆\",51:\"四川\",52:\"贵州\",53:\"云南\",54:\"西藏\",61:\"陕西\",62:\"甘肃\",63:\"青海\",64:\"宁夏\",65:\"新疆\",71:\"台湾\",81:\"香港\",82:\"澳门\",91:\"国外\"};\n   "+
                "                    var iSum=0 ;  \n" +
                "                    var info=\"\" ;  \n" +
                "                    var sId= gets;\n"+
                "                    if(!/^\\d{17}(\\d|x)$/i.test(sId)) return \"你输入的身份证长度或格式错误\";   \n" +
                "                    sId=sId.replace(/x$/i,\"a\");   \n" +
                "                    if(aCity[parseInt(sId.substr(0,2))]==null) return \"你的身份证地区非法\";   \n" +
                "                    sBirthday=sId.substr(6,4)+\"-\"+Number(sId.substr(10,2))+\"-\"+Number(sId.substr(12,2));   \n" +
                "                    var d=new Date(sBirthday.replace(/-/g,\"/\")) ;  \n" +
                "                    if(sBirthday!=(d.getFullYear()+\"-\"+ (d.getMonth()+1) + \"-\" + d.getDate()))return \"身份证上的出生日期非法\";   \n" +
                "                    for(var i = 17;i>=0;i --) iSum += (Math.pow(2,i) % 11) * parseInt(sId.charAt(17 - i),11) ;  \n" +
                "                    if(iSum%11!=1) return \"你输入的身份证号非法\";   \n" +
                "                    return true;"+
                "                  }\n"+
                "                },\n" +
                "\t\t    \tajaxPost:true,\n" +
                "                beforeSubmit:function(curform){\n" +
                "                    //alert(\"1\");\n" +
                "                    f_add();\n" +
                "                    //alert(\"2\");\n" +
                "                    return false;\n" +
                "                },\n" +
                "\t\t\t\tcallback:function(data){\n" +
                "                    alert(\"1: \"+data.toString());\n" +
                "                    return false;\n" +
                "\n" +
                "\t\t\t\t}\n" +
                "\n" +
                "\t\t    });\n" +
                "\t\t  });\n" +
                "\n" +
                "\n" +
                "\t      function myClose(){\n" +
                "\t\t \t\tchangeRightMenu('<%=request.getContextPath()%>/"+this.methodName+"/list.html');\n" +
                "\t\t \t}\n" +
                "          function f_add(){\n" +
                "                $.ajax({\n" +
                "                    url: \"<%=request.getContextPath()%>/"+this.methodName+"/"+(this.optType ==JspBase.ADD?"add":"edit" )+".html\",\n" +
                "                    type: 'POST',\n" +
                "                    cache: false,\n" +
                "                    data: new FormData($('#formadd')[0]),\n" +
                "                    processData: false,\n" +
                "                    contentType: false,\n" +
                "                    dataType:\"json\",\n" +
                "                   /* beforeSend: function(){\n" +
                "                        uploading = true;\n" +
                "                    },*/\n" +
                "                    success : function(data) {\n" +
                "                        //alert(\"f_addDealer: \"+data.success);\n" +
                "                        if (data.success) {\n" +
                "                            $(\"#bodyRight\").empty();\n" +
                "                            $(\"#bodyRight\").load(\"<%=request.getContextPath()%>/"+this.methodName+"/list.html\");\n" +
                "                            layer.msg('修改成功！', {\n" +
                "                                icon : 1\n" +
                "                            });\n" +
                "                        } else {\n" +
                "                            layer.msg('修改失败！', {\n" +
                "                                icon : 5\n" +
                "                            });\n" +
                "                        }\n" +
                "                        //uploading = false;\n" +
                "                    },\n" +
                "                    error: function (XMLHttpRequest, textStatus, errorThrown) {\n" +
                "                        // 状态码\n" +
                "                        console.log(XMLHttpRequest.status);\n" +
                "                        // 状态\n" +
                "                        console.log(XMLHttpRequest.readyState);\n" +
                "                        // 错误信息\n" +
                "                        console.log(textStatus);\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +

                "</script>";
        return  content;
    }

    public void buildJsp(){
        if(!this.isUpload)  this.upload=new String[]{"","","",""};
        String content = this.buildHeader();
        content += this.buildContent();
        content += this.buildTail();
        String jspName = "";
        if(this.optType == JspBase.ADD){
            jspName=this.methodName+"Add.jsp";
        }
        else  if(this.optType == JspBase.EDIT){
            jspName=this.methodName+"Edit.jsp";
        }
        this.buildFile(content,jspName);
    }

}
