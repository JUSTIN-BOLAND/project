package com.secray.utils;

import com.secray.utils.common.CommonUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by root on 2018/1/31 0031.
 */
public class Base {
    private String path="E:\\个人目录\\梁波\\新工作\\project\\selterm\\hbxml\\jsp";
    protected String entityName;
    protected String methodName;
    protected boolean isExport=false;

    public String[] getExportFields() {
        return exportFields;
    }

    public void setExportFields(String[] exportFields) {
        this.exportFields = exportFields;
    }

    protected String[] exportFields ;

    protected String[] addFields ;

    protected String[] moduleNames;
    protected boolean isUpload=false;

    public  Base(String table){
        this(table,null,false);
    }
    public  Base(String table,String path){
        this(table,path,false);
    }
    public  Base(String table,String path,boolean isExport){
        this.entityName = getEntityName(table);
        this.methodName = CommonUtil.lowerFirstLetter(entityName);
        this.isExport = isExport;
        if(path!=null && path.trim().length() == 0 ) this.path = path;
    }
    public void setAddFields(String[] addFields,String[] moduleNames){
        this.addFields = addFields;
        this.moduleNames = moduleNames;
        if(addFields!=null){
            for(String field: addFields){
                if(field.toLowerCase().indexOf("upload,") > - 1) {
                    this.isUpload = true;
                    break;
                }
            }
        }
    }


    public String getEntityName(String table){
        String entityName = table.toLowerCase().replace("t_","");
        if(entityName.indexOf("_") == -1) entityName = entityName.substring(0,1).toUpperCase()+entityName.substring(1);
        else {
            //entityName = entityName.substring(0,1).toUpperCase()+entityName.substring(1);
            String[] tabs = entityName.toLowerCase().split("_");
            String newTab = "";

            for(String tab:tabs) {
                String handleTab = tab.substring(0,1).toUpperCase()+tab.substring(1);

                newTab += handleTab;
            }
            entityName = newTab;
        }
        return entityName;
    }

    public void buildActionFile(){
        String content = this.buildImport();
        content += this.buildAction();
        content +=this.buildExportFile();
        content += this.buildOver();
        this.buildFile(content,this.entityName+"Controller.java");

    }
    protected  String buildImport(){
        //String methodName = CommonUtil.upperFirstLetter(entityName);
        String importContent = "import com.deyi.dao."+entityName+"Mapper;\n" +

                "import com.deyi.dao.UserDao;\n" +
                "import com.deyi.entity.*;\n" +
                "import com.deyi.service.ActionLogService;\n" +
                "\n" +

                "import com.deyi.util.*;\n" +
                "import com.deyi.vo.ReturnVo;\n" +
                "import org.apache.poi.hssf.usermodel.*;\n" +
                "import org.slf4j.Logger;\n" +
                "import org.slf4j.LoggerFactory;\n" +
                "import org.springframework.beans.factory.annotation.Autowired;\n" +
                "import org.springframework.stereotype.Controller;\n" +
                "import org.springframework.transaction.annotation.Transactional;\n" +
                "import org.springframework.web.bind.annotation.RequestMapping;\n" +
                "import org.springframework.web.bind.annotation.RequestMethod;\n" +
                "import org.springframework.web.bind.annotation.RequestParam;\n" +
                "import org.springframework.web.bind.annotation.ResponseBody;\n" +
                "import org.springframework.web.multipart.MultipartFile;\n" +
                "import org.springframework.web.servlet.ModelAndView;\n" +
                "\n" +
                "import javax.servlet.http.HttpServletRequest;\n" +
                "import javax.servlet.http.HttpServletResponse;\n" +
                "import java.io.*;\n" +
                "import java.text.SimpleDateFormat;\n" +
                "import java.util.Date;\n" +
                "import java.util.List;"+
                "\n" +
                "@Controller\n" +
                "@RequestMapping(value = \""+methodName+"\")\n" +
                "public class "+entityName+"Controller extends Component<"+entityName+"> {\n" +
                "\tprivate Logger log = LoggerFactory.getLogger("+entityName+".class);\n" +
                "\n" +

                "    @Autowired\n" +
                "    private "+entityName+"Mapper "+methodName+"Dao;\n" +
                "\n" +

                "    @Autowired\n" +
                "    private UserDao userDao;\n" +
                "\t@Autowired\n" +
                "\tprivate ActionLogService actionLogService;";
        return importContent;
    }
    protected  String buildAction(){
        String exportContent= "        String filePath =  request.getSession().getServletContext().getRealPath(\"/\");\n" +
                "        filePath = filePath + \"logo\"+System.getProperty(\"file.separator\");\n" +
                "        File pathFile = new File(filePath);\n" +
                "        if(!pathFile.exists()) pathFile.mkdirs();\n" +
                "\n" +
                "\n" +
                "        MultipartFile file =  files[0];\n" +
                "        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf(\".\"));\n" +
                "        String imgFileName = genLogoImgName()+suffix;\n" +
                "        String uploadFileName =filePath + imgFileName;\n" +
                "\n" +
                "        String saveLogoFileName = \"logo/\"+imgFileName;\n" +
                "        this.log(\"add\",\"filePath=\"+filePath+\",uploadFileName=\"+uploadFileName);\n" +
                "        InputStream is = null;\n" +
                "        FileOutputStream fos = null;\n" +
                "        try {\n" +
                "            File nFIle = new File(uploadFileName);\n" +
                "            //if(!nFIle.exists()) nFIle.createNewFile();\n" +
                "\n" +
                "            is = file.getInputStream();\n" +
                "            fos = new FileOutputStream(nFIle);\n" +
                "\n" +
                "            // 一次30kb\n" +
                "            byte[] readBuff = new byte[1024 * 30];\n" +
                "            int count = -1;\n" +
                "            while ((count = is.read(readBuff, 0, readBuff.length)) != -1) {\n" +
                "                fos.write(readBuff, 0, count);\n" +
                "            }\n" +
                "            fos.flush();\n" +
                "        } catch (IOException e) {\n" +
                "            e.printStackTrace();\n" +
                "            vo.setSuccess(false);\n" +
                "            vo.setMessage(\"上传文件失败\");\n" +
                "            return vo;\n" +
                "        } finally {\n" +
                "            try {\n" +
                "                if(fos!=null) fos.close();\n" +
                "                if(is!=null) is.close();\n" +
                "            } catch (IOException e) {\n" +
                "                e.printStackTrace();\n" +
                "            }\n" +
                "        }\n" ;

        String uExportContent= "        if(!cur\"+entityName+\".getAdLogo().equals("+methodName+".getAdLogo())) {\n" +
                "            String filePath = request.getSession().getServletContext().getRealPath(\"/\");\n" +
                "            filePath = filePath + \"logo\" + System.getProperty(\"file.separator\");\n" +
                "            File pathFile = new File(filePath);\n" +
                "            if (!pathFile.exists()) pathFile.mkdirs();\n" +
                "\n" +
                "\n" +
                "            MultipartFile file = files[0];\n" +
                "            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf(\".\"));\n" +
                "            String imgFileName = genLogoImgName()+suffix;\n" +
                "            String uploadFileName =filePath + imgFileName;\n" +
                "\n" +
                "            String saveLogoFileName = \"logo/\"+imgFileName;\n" +
                "            this.log(\"edit\", \"filePath=\" + filePath + \",uploadFileName=\" + uploadFileName);\n" +
                "            InputStream is = null;\n" +
                "            FileOutputStream fos = null;\n" +
                "            try {\n" +
                "                File nFIle = new File(uploadFileName);\n" +
                "                //if(!nFIle.exists()) nFIle.createNewFile();\n" +
                "\n" +
                "                is = file.getInputStream();\n" +
                "                fos = new FileOutputStream(nFIle);\n" +
                "\n" +
                "                // 一次30kb\n" +
                "                byte[] readBuff = new byte[1024 * 30];\n" +
                "                int count = -1;\n" +
                "                while ((count = is.read(readBuff, 0, readBuff.length)) != -1) {\n" +
                "                    fos.write(readBuff, 0, count);\n" +
                "                }\n" +
                "                fos.flush();\n" +
                "            } catch (IOException e) {\n" +
                "                e.printStackTrace();\n" +
                "                vo.setSuccess(false);\n" +
                "                vo.setMessage(\"上传文件失败\");\n" +
                "                return vo;\n" +
                "            } finally {\n" +
                "                try {\n" +
                "                    if (fos != null) fos.close();\n" +
                "                    if (is != null) is.close();\n" +
                "                } catch (IOException e) {\n" +
                "                    e.printStackTrace();\n" +
                "                }\n" +
                "            }\n" +
                "            dealer.setAdLogo(saveLogoFileName);\n" +
                "        }\n" ;
        String actionContent="\n\n@RequestMapping(value = \"list\")\n" +
                "\tpublic ModelAndView list(ModelAndView mav, HttpServletRequest request) {\n" +
                "\t\tmav.setViewName(\""+methodName+"/"+methodName+"List\");\n" +
                "\t\treturn mav;\n" +
                "\t}\n" +
                "\n" +
                "\t@RequestMapping(value = \"page\")\n" +
                "\tpublic ModelAndView page(ModelAndView mav, "+entityName+" "+methodName+", Page<"+entityName+"> page, HttpServletRequest request) {\n" +
                "\t\tUserInfo userinfo = UserManage.getCurrUserInfo();\n" +
                "\t\tString userType = userinfo.getType();\n" +
                "\n" +
                "\t\tsetParams(request, "+methodName+", page);\n" +
                "\t\tList<"+entityName+"> "+methodName+"List = "+methodName+"Dao.getPage"+entityName+"s(page);\n" +
                "\t\tpage.setResults("+methodName+"List);\n" +
                "\t\tmav.addObject(\"page\", page);\n" +
                "\t\tmav.setViewName(\""+methodName+"/page"+entityName+"\");\n" +
                "\t\treturn mav;\n" +
                "\n" +
                "\t}\n" +
                "\n" +
                "\t@RequestMapping(value = \"toAdd\")\n" +
                "\tpublic ModelAndView toAdd(ModelAndView mav) {\n" +
                "\t\tmav.setViewName(\""+methodName+"/"+methodName+"Add\");\n" +
                "\t\treturn mav;\n" +
                "\t}\n" +
                "\n" +
                "\t@RequestMapping(value = \"add\")\n" +
                "\n" +

                (isExport ? "\tpublic @ResponseBody ReturnVo<Object>  add( HttpServletRequest request, HttpServletResponse response,@RequestParam(\"imgfile\") MultipartFile[] files,"+entityName+" "+methodName+") {\n":
                        "\tpublic @ResponseBody ReturnVo<Object>  add( HttpServletRequest request, HttpServletResponse response,"+entityName+" "+methodName+") {\n")+
                "        ReturnVo<Object> vo = new ReturnVo<>();\n" +
                "\t\tUserInfo userInfo = UserManage.getCurrUserInfo();\n" +
                "\n" +
                (isExport ? exportContent:"")+


                "\t\t"+methodName+".setCreator(userInfo.getRolename());\n" +
                "\t\t"+methodName+".setCreateTime(new Date());\n" +
                "\t\t"+methodName+".setStatus(\"0\");\n" +
                "\t\t"+methodName+"Dao.insert("+methodName+");\n" +
                "\t\tactionLogService.savelog(userInfo.getId(), userInfo.getName(), Constants.TYPE_ADV, Constants.SUBTYPE_ADD, String.format(\"【%s】添加"+moduleNames[0]+"【%s】\", userInfo.getName(),"+methodName+".getId()));\n" +
                "\t\tModelAndView mav = new ModelAndView();\n" +
                "\t\tmav.setViewName(\""+methodName+"/"+methodName+"List\");\n" +
                "\t\tvo.setSuccess(true);\n" +
                "\t\tvo.setMessage(\"保存成功\");\n" +
                "\t\treturn vo;\n" +
                "\t}\n" +
                "\n" +
                "\t@RequestMapping(\"enable\")\n" +
                "\tpublic @ResponseBody ReturnVo<Object> enable(ModelAndView mav, Integer id){\n" +
                "\t\tReturnVo<Object> vo =new ReturnVo<>();\n" +
                "\t\ttry{\n" +
                "\t\t\tUserInfo userInfo = UserManage.getCurrUserInfo();\n" +
                "\t\t\t"+entityName+" Cur"+entityName+" = "+methodName+"Dao.get"+entityName+"(id);\n" +
                "\t\t\t"+entityName+" "+methodName+" = new "+entityName+"();\n" +
                " \t\t\t"+methodName+".setId(Cur"+entityName+".getId());\n" +
                "\t\t\t"+methodName+".setStatus(\"0\");\n" +

                "\t\t\t"+methodName+"Dao.update("+methodName+");\n" +
                "\t\t\tactionLogService.savelog(userInfo.getId(), userInfo.getName(), Constants.TYPE_ADV, Constants.SUBTYPE_REVOKE, String.format(\"【%s】启用【%s】\", userInfo.getName(),"+methodName+".getId()));\n" +
                "\t\t\tvo.setSuccess(true);\n" +
                "\n" +
                "\t\t}catch(Exception e){\n" +
                "\t\t\tlog.info(\"\",e);\n" +
                "\t\t\tvo.setSuccess(false);\n" +
                "\t\t}\n" +
                "\t\treturn vo;\n" +
                "\t}\n" +
                "\n" +
                "\n" +
                "\t@RequestMapping(\"disable\")\n" +
                "\tpublic @ResponseBody ReturnVo<Object> disable(ModelAndView mav, Integer id){\n" +
                "\t\tReturnVo<Object> vo =new ReturnVo<>();\n" +
                "\t\ttry{\n" +
                "\t\t\tUserInfo userInfo = UserManage.getCurrUserInfo();\n" +
                "            "+entityName+" Cur"+entityName+" = "+methodName+"Dao.get"+entityName+"(id);\n" +
                "            "+entityName+" "+methodName+" = new "+entityName+"();\n" +
                "            "+methodName+".setId(Cur"+entityName+".getId());\n" +
                "\t\t\t"+methodName+".setStatus(\"1\");\n" +
                "\t\t\t"+methodName+"Dao.update("+methodName+");\n" +
                "\t\t\tactionLogService.savelog(userInfo.getId(), userInfo.getName(), Constants.TYPE_ADV, Constants.SUBTYPE_REVOKE, String.format(\"【%s】禁用【%s】\", userInfo.getName(),"+methodName+".getId()));\n" +
                "\t\t\tvo.setSuccess(true);\n" +
                "\n" +
                "\t\t}catch(Exception e){\n" +
                "\t\t\tlog.info(\"\",e);\n" +
                "\t\t\tvo.setSuccess(false);\n" +
                "\t\t}\n" +
                "\t\t return vo;\n" +
                "\t}\n" +
                "\t\n" +
                "\t@RequestMapping(\"toEdit\")\n" +
                "\tpublic ModelAndView toEdit(ModelAndView mav, Integer id){\n" +
                "\t\t"+entityName+" "+methodName+"  = "+methodName+"Dao.get"+entityName+"(id);\n" +

                "\t\tmav.addObject(\""+methodName+"\", "+methodName+");\n" +
                "\t\tmav.setViewName(\""+methodName+"/"+methodName+"Edit\");\n" +
                "\t\treturn mav;\n" +
                "\t}\n" +
                "\t\n" +
                "\t@RequestMapping(\"edit\")\n" +
                (isExport ? "\tpublic @ResponseBody ReturnVo<Object> edit(HttpServletRequest request, HttpServletResponse response,\n" +
                "                                                      @RequestParam(\"imgfile\") MultipartFile[] files,"+entityName+" "+methodName+"){\n"
                           : "\tpublic @ResponseBody ReturnVo<Object> edit(HttpServletRequest request, HttpServletResponse response,"+entityName+" "+methodName+"){\n")
                +
                "\t\tReturnVo<Object> vo = new ReturnVo<>();\n" +
                "        UserInfo userInfo = UserManage.getCurrUserInfo();\n" +
                (isExport ? uExportContent : "")+
                "\t\ttry {\n" +

                "\t\t\t"+methodName+"Dao.update("+methodName+");\n" +
                "\t\t\tactionLogService.savelog(userInfo.getId(), userInfo.getName(), Constants.TYPE_ADV, Constants.SUBTYPE_EDIT, String.format(\"【%s】编辑"+moduleNames[0]+"【%s】\", userInfo.getName(),"+methodName+".getId()));\n" +
                "\t\t}catch (Exception e){\n" +
                "\t\t\tlog.info(\"\",e);\n" +
                "\t\t\tvo.setSuccess(false);\n" +
                "            return vo;\n" +
                "\t\t}\n" +
                "        vo.setSuccess(true);\n" +
                "        vo.setMessage(\"保存成功\");\n" +
                "\t\treturn vo;\n" +
                "\t}\n" +
                "    @ResponseBody\n" +
                "    @RequestMapping(value=\"delete\")\n" +
                "    public ReturnVo<Object> delete(Integer id){\n" +
                "        ReturnVo<Object> vo = new ReturnVo<>();\n" +
                "       "+methodName+"Dao.delete(id);\n" +
                "\n" +
                "        return vo;\n" +
                "    }\n" +
                "    @RequestMapping(value = \"unique"+entityName+"Name\")\n" +
                "    public @ResponseBody Boolean unique"+entityName+"Name(ModelAndView mav, String "+methodName+"Name) {\n" +
                "        "+entityName+" "+methodName+"Param = new "+entityName+"();\n" +
                "        "+methodName+"Param.set"+entityName+"Name("+methodName+"Name);\n" +
                "        "+entityName+" "+methodName+" = "+methodName+"Dao.get"+entityName+"ByCode("+methodName+"Param);\n" +
                "        if("+methodName+" == null) return true;\n" +
                "        else return false;\n" +
                "    }\n" +
                "\n" +
                "    @RequestMapping(value = \"uniqueUpdate"+entityName+"Name\")\n" +
                "    public @ResponseBody Boolean uniqueUpdate"+entityName+"Name(ModelAndView mav, String "+methodName+"Name,String old"+entityName+"Name) {\n" +
                "        "+entityName+" "+methodName+"Param = new "+entityName+"();\n" +
                "        "+methodName+"Param.set"+entityName+"Name("+methodName+"Name);\n" +
                "        "+methodName+"Param.setPasswd(old"+entityName+"Name);\n" +
                "        "+entityName+" "+methodName+" = "+methodName+"Dao.get"+entityName+"ByCode("+methodName+"Param);\n" +
                "        if("+methodName+" == null) return true;\n" +
                "        else return false;\n" +
                "    }";
         return actionContent;
    }
    private String buildExportFile(){
        if(exportFields!=null && exportFields.length == 3 ) {
            String content = "\n@RequestMapping(value = \"export\", method = RequestMethod.GET, produces = \"text/html; charset=UTF-8\")\n" +
                    "\t@Transactional\n" +
                    "\tpublic void export("+this.entityName+" "+this.methodName+",HttpServletRequest request, HttpServletResponse resp) throws IOException {\n" +
                    "\n" +
                    "\n" +
                    "\t\tList<"+this.entityName+"> "+this.methodName+"List = "+this.methodName+"Dao.get"+this.entityName+"s("+this.methodName+");\n" +
                    "\t\t// 转换时间格式\n" +
                    "\t\tDate currentTime = new Date();\n" +
                    "\t\tSimpleDateFormat formatter = new SimpleDateFormat(\"yyyy-MM-dd\");\n" +
                    "\t\tString dateString = formatter.format(currentTime);\n" +
                    "\t\tHSSFWorkbook wb = new HSSFWorkbook();\n" +
                    "        String[] titleFields={\""+exportFields[0]+"\",\n" +
                    "                              \""+exportFields[1]+"\"};\n" +
                    "\t\texportSheet(wb, "+this.methodName+"List,\""+exportFields[2]+"\",titleFields);\n" +
                    "\t\ttry {\n" +
                    "\t\t\t// 文件名称\n" +
                    "\t\t\tString fmString = \""+this.methodName+"\";\n" +
                    "\t\t\tString filename = fmString + dateString;\n" +
                    "\t\t\tresp.setContentType(\"application/octet-stream\");\n" +
                    "\t\t\tresp.setHeader(\"Content-disposition\",\n" +
                    "\t\t\t\t\t\"attachment;filename=\" + new String((filename + \".xls\").getBytes(), \"iso-8859-1\"));\n" +
                    "\t\t\tOutputStream out = resp.getOutputStream();\n" +
                    "\t\t\twb.write(out);\n" +
                    "\t\t\tout.close();\n" +
                    "\n" +
                    "\t\t} catch (Exception e) {\n" +
                    "\t\t\te.printStackTrace();\n" +
                    "\t\t}\n" +
                    "\t}\n" +
                    "\n" +
                    "\tpublic void exportSheet(HSSFWorkbook wb, List<"+this.entityName+"> list,String sheetName,String[] titleFields) {\n" +
                    "\t\tHSSFSheet sheet = wb.createSheet(sheetName);\n" +
                    "\t\tsheet.setDefaultColumnWidth(25); // 默认宽度\n" +
                    "\t\tsheet.setDefaultRowHeight((short) 400);\n" +
                    "\t\tHSSFRow row1 = sheet.createRow((int) 0);\n" +
                    "\t\trow1.setHeight((short) 500);\n" +
                    "\t\tHSSFCellStyle style = wb.createCellStyle();\n" +
                    "\t\tstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中样式\n" +
                    "\t\tstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);\n" +
                    "\t\tHSSFFont headerFont = (HSSFFont) wb.createFont(); // 创建字体样式\n" +
                    "\t\theaderFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体加粗\n" +
                    "\t\theaderFont.setFontName(\"Times New Roman\"); // 设置字体类型\n" +
                    "\t\theaderFont.setFontHeightInPoints((short) 8); // 设置字体大小\n" +
                    "\t\tstyle.setFont(headerFont); // 为标题样式设置字体样式\n" +
                    "\t\tstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框\n" +
                    "\t\tstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框\n" +
                    "\t\tstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框\n" +
                    "\t\tstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框\n" +
                    "\t\t//String title = \"设备名称,经销商名称,设备分类,设备列序号,机器编号,套餐,机器状态,业务状态,创建时间\";\n" +
                    "\t\tString[] titles = titleFields[0].split(\",\");\n" +
                    "\t\tString[] values = new String[titles.length];\n" +
                    "\t\tHSSFCell cell = null;\n" +
                    "\t\tfor(int i=0 ;i< titles.length;i++){\n" +
                    "\t\t\tcell = row1.createCell(i);\n" +
                    "\t\t\tcell.setCellValue(titles[i]);\n" +
                    "\t\t\tcell.setCellStyle(style);\n" +
                    "\t\t}\n" +
                    "\n" +
                    "\t\tString[] fields =  titleFields[1].split(\",\");\n" +
                    "\t\t//SimpleDateFormat formatter = new SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss\");\n" +
                    "\n" +
                    "\t\t// 设置数据\n" +
                    "\t\tfor (int i = 0; i < list.size(); i++) {\n" +
                    "\t\t\tHSSFRow row2 = sheet.createRow(i + 1);\n" +
                    "\t\t\t"+this.entityName+" "+this.methodName+" = list.get(i);\n" +
                    "\t\t\tif ("+this.methodName+" != null) {\n" +
                    "                for(int j = 0 ; j < fields.length; j++){\n" +
                    "                    Object value = this.getMethodValue("+this.methodName+",fields[j]);\n" +
                    "                    this.log(\"exportSheet\",\"value=\"+value);\n" +
                    "                    values[j] = ( value == null ? \"\":value.toString());\n" +
                    "                    if (values[j] != null) {\n" +
                    "                        row2.createCell(j).setCellValue(values[j]);\n" +
                    "                    }\n" +
                    "                }\n" +
                    "\n" +
                    "\t\t\t\t\n" +
                    "\n" +
                    "\t\t\t}\n" +
                    "\t\t}\n" +
                    "\t}\n";
            return content;
        }
        else return "";
    }
    protected  String buildOver(){
        return "}";
    }
    protected   void buildFile(String content,String genFileName) {
        File pathFile = null;
        File file = null;
        FileWriter fw=null;
        BufferedWriter bw= null;


        try {
            pathFile = new File(path);

            if(!pathFile.exists()) pathFile.mkdirs();

            String fileName = path+File.separator+genFileName;

            file = new File(fileName);
            if(file.exists()) file.delete();
            file.createNewFile();
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            bw.write(content);


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(bw!=null){
                    bw.flush();
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
