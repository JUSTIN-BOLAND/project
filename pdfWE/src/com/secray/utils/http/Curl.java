package com.secray.utils.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by root on 2017/6/20 0020.
 */
public class Curl {

    public static void main(String[] args){

        String []cmds = {"curl", "-X","POST", "http://192.168.24.75/api/v1/assets/ip_ranges?email=admin@fofa.so&key=fofaisbest",
                "-H","Content-Type: application/json",
                "--data-binary",
                "{\n"
                        + "    \"ip_ranges\":{\n"
                        + "        \"ip_range\":\"10.10.10.12/32\",\n"
                        + "        \"name\":\"10.10.10.12\",\n"
                        + "        \"group_name\":\"分组名称\",\n"
                        + "        \"company\":\"公司名称\",\n"
                        + "        \"province\":\"四川\",\n"
                        + "        \"city\":\"成都\",\n"
                        + "        \"business\":\"CMS\"\n"
                        + "    }\n"
                        + "}"
               };
        ProcessBuilder pb=new ProcessBuilder(cmds);
        pb.redirectErrorStream(true);
        Process p;
        try {
            p = pb.start();
            BufferedReader br=null;
            String line=null;

            br=new BufferedReader(new InputStreamReader(p.getInputStream()));
            while((line=br.readLine())!=null){
                System.out.println("\t"+line);
            }
            br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
