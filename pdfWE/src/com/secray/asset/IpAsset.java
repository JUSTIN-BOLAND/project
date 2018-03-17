package com.secray.asset;

import com.secray.utils.http.HttpClient;
import com.secray.utils.http.Spider;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.Iterator;
import java.util.Map;
import java.util.LinkedHashMap;


/**
 * Created by root on 2017/6/20 0020.
 */
public class IpAsset {

    private     static  String createIpRangeParams(){
        JSONObject obj = new JSONObject();
        obj.element("ip_range", "192.168.24.0/24");
        obj.element("name", "192.168.24.12");
        obj.element("group_name", "公司内网");
        obj.element("company", "郑州市信达天瑞信息技术有限公司");
        obj.element("province", "河南省");
        obj.element("city", "郑州市");
        obj.element("business", "安全");

        JSONObject jsonObject = new  JSONObject();
        jsonObject.put("ip_ranges" , obj );

        return  jsonObject.toString();
    }
    private     static  String createScanParams(){
        JSONObject obj = new JSONObject();
        obj.element("page", 1);
        obj.element("per_page", 50);

        return  obj.toString();
    }

    public static void parseJsonStr(String jsonStr){
        JSONObject json =JSONObject.fromObject(jsonStr);
        System.out.println(json.get("status").toString());
        Map<String, Map<String,String>> assets = new LinkedHashMap<String, Map<String,String>>();
        Map<String,String> result =  null;
        int row = 0;
        if("OK".equals(json.get("status").toString().toUpperCase()) ){
            JSONArray scanList= JSONArray.fromObject(json.get("list"));
            JSONObject scanObj = null;
            JSONObject ports = null;
            JSONObject port = null;
            JSONObject source  = null;
            for(int i=0;i<scanList.size();i++) {

                scanObj = scanList.getJSONObject(i);

                ports =  scanObj.getJSONObject("_source").getJSONObject("port_list");
                int j = 0;
                Iterator iterator = ports.keys();//ports.entrySet().iterator();

                while (iterator.hasNext())  {

                    result = new LinkedHashMap<String,String>();
                    result.put("optTye",scanObj.get("_index").toString());
                    result.put("scanTye",scanObj.get("_type").toString());

                    result.put("ip",scanObj.get("_id").toString());
                    source  = scanObj.getJSONObject("_source");
                    result.put("os",(source.get("os") == null ? "":source.get("os").toString()) );
                    result.put("mac",(source.get("mac") == null ? "":source.get("mac").toString()) );
                    result.put("assetTye", (source.get("cat_tags") == null ? "":source.get("cat_tags").toString()));
                    result.put("assetProducter",(source.get("company_tags") == null ? "":source.get("company_tags").toString()) );

                    result.put("rule_tags",(source.get("rule_tags") == null ? "":source.get("rule_tags").toString()) );
                    String key = iterator.next().toString();
                    port = (JSONObject)ports.get(key); //(JSONObject)iterator.next();
                    result.put("port", port.get("port").toString());
                    result.put("protocol", port.get("protocol").toString());
                    result.put("banner", port.get("banner").toString());
                    result.put("status",port.get("status").toString());
                    result.put("net_bios",scanObj.getJSONObject("_source").get("net_bios").toString());
                    assets.put(""+row,result);
                    j++;
                }
                row = row + (i+j);

            }
            print(assets);
        }
    }
    public static void print(Map<String, Map<String,String>> lh){
        Iterator<Map.Entry<String, Map<String,String>>> iter = lh.entrySet().iterator();
        Map.Entry<String, Map<String,String>> e  = null;
        Map<String,String>  value = null;
        Map.Entry<String,String> el = null;
        Iterator<Map.Entry<String,String>> valueIt =  null;
        String rows = "'";
        while (iter.hasNext()) {
            e = iter.next();
            value = e.getValue();
            valueIt = value.entrySet().iterator();
            rows ="";
            while (valueIt.hasNext()) {
                el = valueIt.next();
                if(rows.length() >0 ) rows += ",";
                rows += "【"+el.getKey() +" = " +el.getValue()+"】";

            }
            System.out.println(e.getKey() + " -> " +rows);

        }
    }
    public static String addIPRange(){
        String url = "http://192.168.24.75/api/v1/assets/ip_ranges?email=admin@fofa.so&key=1cb00a4a4d548fc53b7e6262dadb3dae";
        String param = createIpRangeParams();
        String ret= HttpClient.sendPost(url,param,false,HttpClient.JSON);
        System.out.println(ret);
        return ret;
    }
    public static String getScanResult(){
        String url = "http://192.168.24.75/api/v1/assets?email=admin@fofa.so&key=1cb00a4a4d548fc53b7e6262dadb3dae";
        String param = createScanParams();
        param="";
        String ret= HttpClient.sendGet(url,param,null);
        System.out.println(ret);
        parseJsonStr(ret);
        return ret;
    }

    public static void main(String[] args){
        //IpAsset.addIPRange();
        IpAsset.getScanResult();

    }
}
