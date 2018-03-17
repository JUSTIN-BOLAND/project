package com.secray.utils;

import com.secray.utils.JDBC.JDBC;
import com.secray.utils.common.CommonUtil;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by root on 2018/1/26 0026.
 */
public class HbUtils {

    public static String  generateHbXml(String path,String tableNames){
        if(tableNames==null || tableNames.trim().length() ==0 ) return "";
        String xml="";
        String[] tables = tableNames.split(",");
        JDBC jdbc = new JDBC(JDBC.DB);
        String entityName = null;
        String sql;
        String[] result={"  <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\" >","  <trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\" >"};
        String condition = "";
        ResultSet rs = null;
        try {
            jdbc.connect();
            for(String table : tables){
                xml="";
                condition="";
                String whereCondition="";
                String paramCondition="";
                entityName = table.toLowerCase().replace("t_","");
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
                xml = getXmlHeader(entityName);
                xml +=getResultMap(jdbc,table);
                int cnt = jdbc.queryForInt("select count(1) from information_schema.COLUMNS t WHERE t.TABLE_NAME='"+table+"'");

                int pageSize = 5;
                double tp= cnt / (double)pageSize;
                int totalPage = (int) Math.ceil(tp);
                System.out.println(cnt+"->"+totalPage);
                for(int i=1;i<totalPage+1;i++) {
                    sql = "SELECT * \n" +
                            "FROM \n" +
                            "(\n" +
                            "\tSELECT REPLACE(a.hbColumns,',  ',CONCAT(CHAR(10),'  ')) commonSql\n" +
                            "\tFROM(\n" +
                            "\tSELECT  GROUP_CONCAT(CONCAT('        <if test=\"',t.COLUMN_NAME,' != null\" >',CHAR(10),'            ',t.COLUMN_NAME,',',CHAR(10),'      </if>')) hbColumns\n" +
                            "\t FROM (select * from information_schema.COLUMNS t WHERE t.TABLE_NAME='" + table + "' limit "+(i-1)*pageSize+","+pageSize+" ) t \n" +
                            "\t ) a \n" +
                            ") a ,\n" +
                            " \n" +
                            "( \n" +
                            "        SELECT REPLACE(b.hbColumns,',  ',CONCAT(CHAR(10),'     ')) selectSql\n" +
                            "\tFROM(\n" +
                            "\t\tSELECT  GROUP_CONCAT(CONCAT('      <if test=\"',t.COLUMN_NAME,' != null\" >',CHAR(10),'            #{',t.COLUMN_NAME,',jdbcType=',\n" +
                            "\t\t\t CASE WHEN INSTR(t.DATA_TYPE ,'int') > 0 THEN 'INTEGER'  ELSE UPPER(t.DATA_TYPE) END\n" +
                            "\t\t\t,'},',CHAR(10),'     </if>')) hbColumns\n" +
                            "\t\t FROM (select * from  information_schema.COLUMNS t WHERE t.TABLE_NAME='" + table + "' limit "+(i-1)*pageSize+","+pageSize+" ) t \n" +
                            "\t ) b\n" +
                            " ) b, "+
                           "( \n" +
                            "        SELECT REPLACE(c.hbColumns,',  ',CONCAT(CHAR(10),'  ')) whereSql\n" +
                            "\tFROM(\n" +
                            "\t\tSELECT  GROUP_CONCAT(CONCAT('      <if test=\"',t.COLUMN_NAME,' != null\" >',CHAR(10),'            ',t.COLUMN_NAME,'=#{',t.COLUMN_NAME,',jdbcType=',\n" +
                            "\t\t\t CASE WHEN INSTR(t.DATA_TYPE ,'int') > 0 THEN 'INTEGER'  ELSE UPPER(t.DATA_TYPE) END\n" +
                            "\t\t\t,'},',CHAR(10),'     </if>')) hbColumns\n" +
                            "\t\t FROM (SELECT * FROM  information_schema.COLUMNS t WHERE t.TABLE_NAME='"+ table + "' LIMIT "+(i-1)*pageSize+","+pageSize+" ) t \n" +
                            "\t ) c\n" +
                            " ) c, "+
                            " ( \n" +
                            "        SELECT REPLACE(d.hbColumns,',  ',CONCAT(CHAR(10),'  ')) selWhereSql\n" +
                            "\tFROM(\n" +
                            "\t\tSELECT  GROUP_CONCAT(CONCAT('      <if test=\"',t.COLUMN_NAME,' != null and ',t.COLUMN_NAME,' !=''''\" >',CHAR(10),'           and ',t.COLUMN_NAME,'=#{',t.COLUMN_NAME,',jdbcType=',\n" +
                            "\t\t\t CASE WHEN INSTR(t.DATA_TYPE ,'int') > 0 THEN 'INTEGER'  ELSE UPPER(t.DATA_TYPE) END\n" +
                            "\t\t\t,'}',CHAR(10),'      </if>')) hbColumns\n" +
                            "\t\t FROM (SELECT * FROM  information_schema.COLUMNS t WHERE t.TABLE_NAME='"+ table + "' LIMIT "+(i-1)*pageSize+","+pageSize+" ) t \n" +
                            "\t ) d\n" +
                            " ) d ,\n"+
                            " ( SELECT REPLACE(d.hbColumns,',  ',CONCAT(CHAR(10),'  ')) paramSql\n" +
                            "\tFROM(\n" +
                            "\t\tSELECT  GROUP_CONCAT(  \n" +
                            "\t\t           CASE WHEN INSTR(UPPER(t.column_name),'NAME') > 0 THEN   CONCAT('        <if test=\"params.',t.COLUMN_NAME,' != null and params.',t.COLUMN_NAME,' !=''''\" >',CHAR(10),'         and ',t.COLUMN_NAME,' LIKE CONCAT(''%'',''${params.',t.COLUMN_NAME,'}'',''%'')',CHAR(10),'      </if>')\n" +
                            "\t\t            WHEN INSTR(UPPER(t.column_name),'TIME') > 0 OR INSTR(UPPER(t.column_name),'DATE') > 0 THEN   \n" +
                            "\t\t           CONCAT('        <if test=\"params.',t.column_name,'Start != null and params.',t.column_name,'Start !=''''\">',CHAR(10),\n" +
                            "\t\t\t      '          and ',t.column_name,' <![CDATA[>=]]> DATE_FORMAT(#{params.',t.column_name,'Start},''%Y-%m-%d 00:00:00'')',CHAR(10),\n" +
                            "\t\t\t      '      </if>',CHAR(10),\n" +
                            "\t\t\t    '        <if test=\"params.',t.column_name,'End != null and params.',t.column_name,'End !=''''\">',CHAR(10),\n" +
                            "\t\t\t    '            and ',t.column_name,' <![CDATA[<=]]> DATE_FORMAT(#{params.',t.column_name,'End},''%Y-%m-%d 23:59:59'')',CHAR(10),\n" +
                            "\t\t\t    '        </if>')\n" +
                            "\t\t              ELSE CONCAT('        <if test=\"params.',t.COLUMN_NAME,' != null and params.',t.COLUMN_NAME,' !=''''\" >',CHAR(10),'          and ',t.COLUMN_NAME,'=#{params.',t.COLUMN_NAME,'}',CHAR(10),'      </if>')\n" +
                            "\t\t           END    \n" +
                            "\t\t\t) hbColumns,t.COLUMN_NAME\n" +
                            "\t\t FROM (SELECT * FROM  information_schema.COLUMNS t WHERE t.TABLE_NAME='"+ table + "' AND t.COLUMN_NAME<>'id'  LIMIT "+(i-1)*pageSize+","+pageSize+" ) t \n" +
                            "\t ) d ) e";
                   System.out.println("sql="+sql);
                    rs = jdbc.query(sql);
                    if(rs.next()){
                        result[0] += "\n"+rs.getString(1);
                        result[1] += "\n"+rs.getString(2);
                        condition += "\n"+rs.getString(3);
                        whereCondition += "\n"+rs.getString(4);
                        paramCondition += "\n"+rs.getString(5);
                       // System.out.println("mid["+i+"]=" +result[0]);
                    }
                    rs.close();

                }
                result[0] += "\n    </trim>";
                result[1] += "\n    </trim>";
                xml +=getXmlSelect(table,entityName,whereCondition,paramCondition);
                xml += getXmlInsertUpdate(table,entityName,result,condition);
                xml += "\n</mapper>";
               // System.out.println("result=" + result[0]+"\n"+result[1]);
                System.out.println("result=" +xml);
                buildFile(path,entityName,xml,1);
                buildJavaMapper(path,entityName);
                buildJavaEntity(jdbc,table,path,entityName);
            }

        }catch(Exception ioe){
            ioe.printStackTrace();
        }
		finally
            {
                try{
                    if(rs!=null) rs.close();;
                    if( jdbc != null ) jdbc.close();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
            return xml;
    }
    private static String getResultMap(JDBC jdbc,String table) throws  Exception{
        String result = "";
        String sql="SELECT REPLACE(a.prop,',','') prop,b.COLUMNS\n" +
                "FROM \n" +
                "(\n" +
                "\tSELECT a.TABLE_NAME,GROUP_CONCAT(prop) prop\n" +
                "\tFROM\n" +
                "\t(\n" +
                "\tSELECT  t.TABLE_NAME, CONCAT('<result column=\"',t.COLUMN_NAME,'\" property=\"',t.COLUMN_NAME,'\" jdbcType=\"',\n" +
                "\t  CASE WHEN INSTR(t.DATA_TYPE ,'int') > 0 THEN 'INTEGER'  ELSE UPPER(t.DATA_TYPE) END \n" +
                "\t ,'\" />',CHAR(10)) prop\n" +
                "\t FROM information_schema.COLUMNS t WHERE t.TABLE_NAME='"+table+"' \n" +
                "\t) a  \n" +
                "\tGROUP BY a.TABLE_NAME\n" +
                ") a,\n" +
                "(\n" +
                " SELECT t.TABLE_NAME,  GROUP_CONCAT(t.COLUMN_NAME) COLUMNS\n" +
                " FROM information_schema.COLUMNS t WHERE t.TABLE_NAME='"+table+"' \n" +
                " GROUP BY t.TABLE_NAME\n" +
                ") b\n" +
                "WHERE  a.TABLE_NAME = b.TABLE_NAME\n ";
        sql="SELECT  t.TABLE_NAME, CONCAT('<result column=\"',t.COLUMN_NAME,'\" property=\"',t.COLUMN_NAME,'\" jdbcType=\"',\n" +
                "\t  CASE WHEN INSTR(t.DATA_TYPE ,'int') > 0 THEN 'INTEGER'  ELSE UPPER(t.DATA_TYPE) END \n" +
                "\t ,'\" />',CHAR(10)) prop\n" +
                "\t FROM information_schema.COLUMNS t WHERE t.TABLE_NAME='"+table+"'" ;
        //System.out.println(sql);
        ResultSet rs = jdbc.query(sql);
        while(rs.next()){
            result += "   "+rs.getString(2);
        }

        rs.close();
        sql=" SELECT GROUP_CONCAT(t.COLUMN_NAME) COLUMNS\n" +
                " FROM information_schema.COLUMNS t WHERE t.TABLE_NAME='"+table+"'" ;

        result += "\n</resultMap>\n" +
                "  <sql id=\"Base_Column_List\" >\n    "+ jdbc.queryForStr(sql)+"\n</sql>";
        //System.out.println("getResultMap="+result);
        return  result;
    }
    private static String getXmlInsertUpdate(String table ,String entityName,String[] conditions,String whereCondition){
        String result="\n <insert id=\"insert\" parameterType=\"com.deyi.entity."+entityName+"\" useGeneratedKeys=\"true\" keyColumn=\"id\" keyProperty=\"id\" >\n" +
                "    insert into "+table+" \n"+conditions[0]+" \n"+conditions[1]+"\n"+"</insert> \n";
        result+="\n<update id=\"update\" parameterType=\"com.deyi.entity."+entityName+"\" >\n" +
                "   update "+table+"\n" +
                "   <set >\n" +whereCondition+
                "\n" +
                "   </set>\n" +
                "   where id = #{id,jdbcType=INTEGER}\n" +
                "</update>\n";
        return result;

    }
    private static String getXmlSelect(String table ,String entityName,String conditions,String paramCondition){
        String result="\n<select id=\"get"+entityName+"\" resultMap=\"BaseResultMap\" parameterType=\"java.lang.Integer\" >\n" +
                "    select\n" +
                "    <include refid=\"Base_Column_List\" />\n" +
                "    from "+table+"\n" +
                "    where id = #{id,jdbcType=INTEGER}\n" +
                "  </select>\n" ;
         result +="\n<select id=\"get"+entityName+"s\" parameterType=\"com.deyi.entity."+entityName+"\" resultMap=\"BaseResultMap\">\n" +
                 "  \t select\n" +
                 "    <include refid=\"Base_Column_List\" />\n" +
                 "    from "+table+"\n" +
                 "    <where>  1=1  \n"+conditions+" \n" +
                 "   </where>\n" +
                 "  </select>";
        result +="\n<select id=\"get"+entityName+"ByCode\" parameterType=\"com.deyi.entity."+entityName+"\" resultMap=\"BaseResultMap\">\n" +
                "  \t select\n" +
                "    <include refid=\"Base_Column_List\" />\n" +
                "    from "+table+"\n" +
                "    <where>  1=1  \n"+conditions+" \n" +
                "   </where>\n" +
                "  </select>";
        result +="\n<select id=\"getPage"+entityName+"s\" parameterType=\"com.deyi.util.Page\" resultMap=\"BaseResultMap\">\n" +
                "  \t select\n" +
                "    <include refid=\"Base_Column_List\" />\n" +
                "    from "+table+"\n" +
                "    <where>  1=1  \n"+paramCondition+" \n" +
                "   </where>\n" +
                "  </select>";
        result +="\n" +
                "  <delete id=\"delete\" parameterType=\"java.lang.Integer\" >\n" +
                "    delete from "+table+"\n" +
                "    where id = #{id,jdbcType=INTEGER}\n" +
                "  </delete>\n";
        return result;
    }
    private static String getXmlHeader(String entityName){
        String header="<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >\n" +
                "<mapper namespace=\"com.deyi.dao."+entityName+"Mapper\" >\n" +
                "<cache type=\"org.mybatis.caches.ehcache.LoggingEhcache\"/>\n" +
                "  <resultMap id=\"BaseResultMap\" type=\"com.deyi.entity."+entityName+"\" > \n";
        return header;
    }

    public static void buildFile(String path,String entityName,String content,int fileType) {
        File pathFile = null;
        File file = null;
        FileWriter fw=null;
        BufferedWriter bw= null;


        try {
            pathFile = new File(path);

            if(!pathFile.exists()) pathFile.mkdirs();
            String fileName = path+File.separator+entityName+"Mapper.xml";
            if(fileType==2)  fileName = path+File.separator+entityName+".java";
            else  if(fileType==3)  fileName = path+File.separator+entityName+"Mapper.java";
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
    public static void buildJavaEntity(JDBC jdbc,String table,String path, String entityName){
        String sql="SELECT  CONCAT(  '\tprivate ', t.jType,' ',t.column_name,';',CHAR(10)) jp,\n" +
                "\t\t \n" +
                "\t\t CONCAT(  '\tpublic ', t.jType,' get',t.jFunc,'(){',CHAR(10),\n" +
                "\t\t                '\t    return ',t.column_name,';',CHAR(10),\n" +
                "\t\t                '\t}',CHAR(10),\n" +
                "\t\t                '\tpublic void', ' set',t.jFunc,'(', t.jType,' ',t.column_name,'){',CHAR(10),\n" +
                "\t\t                '\t    this.',t.column_name,'=',t.column_name,';',CHAR(10),\n" +
                "\t\t                '\t}',CHAR(10)\n" +
                "\t\t                ) jm\n" +
                "\t\t FROM (\n" +
                "\t\t  SELECT  t.COLUMN_NAME,t.COLUMN_TYPE,t.DATA_TYPE, \n" +
                "\t\t\t\tCASE WHEN INSTR(LOWER(t.DATA_TYPE) ,'int') > 0 THEN 'Integer'  \n" +
                "\t\t\t\t     WHEN INSTR(LOWER(t.DATA_TYPE) ,'varchar') > 0 THEN 'String' \n" +
                "\t\t\t\t     WHEN INSTR(LOWER(t.DATA_TYPE) ,'decimal') > 0 OR INSTR(LOWER(t.DATA_TYPE) ,'float') > 0 OR INSTR(LOWER(t.DATA_TYPE) ,'double') > 0 THEN 'Double' \n" +
                "\t\t\t\t     WHEN INSTR(LOWER(t.DATA_TYPE) ,'time') > 0  OR INSTR(LOWER(t.DATA_TYPE) ,'date') > 0 THEN 'Date' \n" +
                "\t\t\t\t     \n" +
                "\t\t\t\t     ELSE t.DATA_TYPE END jType,\n" +
                "\t\t\t\t     CONCAT(UPPER(LEFT(t.column_name,1)),SUBSTRING(t.column_name,2,(LENGTH(t.column_name)-1))) jFunc \n" +
                "                 FROM  information_schema.COLUMNS t WHERE t.TABLE_NAME='"+table+"'   ) t ";
        ResultSet rs =  null;
        String[] jpm={"",""};
        try {
            rs = jdbc.query(sql);
            while(rs.next()){
                jpm[0] += "\n"+rs.getString(1);
                jpm[1] += "\n"+rs.getString(2);
            }
            String result = "package com.deyi.entity;\n" +
                    "\n" +
                    "import java.io.Serializable;\n" +
                    "import java.util.Date;\n" +
                    "\n" +
                    "public class "+entityName+" implements Serializable{\n" +
                    "\n" +
                    "\tprivate static final long serialVersionUID = 8236469690933529842L;"+
                    jpm[0] +"\n"+jpm[1]+
                    "\n}";
            buildFile(path,entityName,result,2);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void buildJavaMapper(String path, String entityName){
       String entityVar = CommonUtil.lowerFirstLetter(entityName);
        String result="package com.deyi.dao;\n" +
                "\n" +
                "import com.deyi.entity."+entityName+";\n" +
                "import com.deyi.util.Page;\n" +
                "import org.springframework.stereotype.Repository;\n" +
                "import java.util.List;\n" +
                "\n" +
                "@Repository\n" +
                "public interface "+entityName+"Mapper {\n" +
                "    List<"+entityName+"> getPage"+entityName+"s(Page<"+entityName+"> page);\n" +
                "    List<"+entityName+"> get"+entityName+"s("+entityName+" "+entityVar+");\n" +
                "    "+entityName+" get"+entityName+"ByCode("+entityName+" "+entityVar+");\n" +
                "    "+entityName+" get"+entityName+"(Integer id);\n" +
                "    int insert("+entityName+" "+entityVar+");\n" +
                "    int update("+entityName+" "+entityVar+");\n" +
                "    int delete(Integer id);\n" +

                "}\n";
        buildFile(path,entityName,result,3);

    }

    public static  void main(String[] args) {
        String path="E:\\个人目录\\梁波\\新工作\\project\\selterm\\hbxml\\xml";
        HbUtils.generateHbXml(path,"t_recharge");
    }
}
