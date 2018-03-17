package com.secray.utils.JDBC;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 *
 * @author lb
 *
 */
public class Configuration
{
    private Properties propertie;
    private FileInputStream inputFile;
    private FileOutputStream outputFile;

    /**
     *
     */
    public Configuration()
    {
        propertie = new Properties();
    }

    /**
     *
     * @param filePath
     */
    public Configuration(String filePath)
    {

    	//String path=this.getClass().getResource("").getPath();

        propertie = new Properties();
        try {


        	 propertie.load(new FileInputStream(new File(filePath)));

        } catch (IOException ex) {

                String clsPath = Configuration.class.getResource("/").getPath();
                 String path =clsPath.replace("bin/","")+filePath;
                 try {
                     path = URLDecoder.decode(path, "UTF-8");
                 } catch (UnsupportedEncodingException e) {
                     //e.printStackTrace();
                     System.err.println("[e] : "+e.getMessage());
                 }
                 path = path.substring(1,path.length());
                 path = path.replace("/","\\");


                try {
                    System.out.println("jdbc properties : "+path);
                    propertie.load(new FileInputStream(new File(path)));
                }
                catch(Exception fe) {
                   // fe.printStackTrace();
                    System.err.println("[fe] : "+fe.getMessage());
                }
        }
    }//end ReadConfigInfo(...)

    /**
     *
     * @param key
     * @return
     */
    public String getProperty(String key)
    {
        if(propertie.containsKey(key)){
            String value = propertie.getProperty(key);//µÃµ½Ä³Ò»ÊôÐÔµÄÖµ
            return value;
        }
        else
            return "";
    }//end getValue(...)

    /**
     *
     * @param fileName
     * @param key
     * @return
     */
    public String getValue(String fileName, String key)
    {
        try {
            String value = "";
            inputFile = new FileInputStream(fileName);
            propertie.load(inputFile);
            inputFile.close();
            if(propertie.containsKey(key)){
                value = propertie.getProperty(key);
                return value;
            }else
                return value;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "";
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

   /**
    *
    */
    public void clear()
    {
        propertie.clear();
    }//end clear();

    /**
     *
     *
     * @param key
     * @param value
     */
    public void setValue(String key, String value)
    {
        propertie.setProperty(key, value);
    }//end setValue(...)

 /**
  *
  * @param fileName
  * @param description
  */
    public void saveFile(String fileName, String description)
    {
        try {
            outputFile = new FileOutputStream(fileName);
            propertie.store(outputFile, description);
            outputFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }//end saveFile(...)

    public static String getComponentUrl()
	{
		Configuration rc = new Configuration("jdbc.properties");
		String result = rc.getProperty("bicp.componentUrl");
		if(result==null) result="http://10.87.25.91/componentcenter";
		return result;
	}
    public static void main(String[] args)
    {
        String path=Configuration.class.getResource("/").getPath();
       // System.out.println(path+" : "+ System.getProperty("user.dir")                  );
        Configuration rc = new Configuration("./conf/jdbc.properties");
        String DBDRIVER = rc.getProperty("jdbc.hive.Driver");
        String URL = rc.getProperty("jdbc.hive.URL");
        String userName = rc.getProperty("jdbc.hive.userName");
        String password = rc.getProperty("jdbc.hive.password");
        System.out.println(DBDRIVER);
      System.out.println("lb : "+Thread.currentThread().getContextClassLoader().getResource(""));
      /*    System.out.println(Configuration.class.getClassLoader().getResource(""));
        System.out.println(ClassLoader.getSystemResource(""));
        System.out.println(Configuration.class.getResource(""));
        System.out.println(Configuration.class.getResource("/"));//Class文件所在路径
        System.out.println(new File("/").getAbsolutePath());*/
        System.out.println("lb1 : "+Configuration.class.getClassLoader().getResourceAsStream("/conf/jdbc.properties"));








    }//end main()

}//e
