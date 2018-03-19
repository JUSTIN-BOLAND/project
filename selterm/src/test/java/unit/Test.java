package unit;

import com.deyi.entity.CodeName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 2018/2/26 0026.
 */
public class Test {

    public static   List<CodeName> buildStatisList(String name, boolean zeroType, boolean sorted){
        List<CodeName> list =  new ArrayList<CodeName>();
        CodeName  codeName ;
        String[] names = name.split(",");
        int begin=0;
        if(!zeroType) begin=1;
        if(sorted) {
            for (int i = names.length-1;i>=0 ;i--) {
                codeName = new CodeName();
                codeName.setCode(i + begin+"");
                codeName.setName(names[i]);
                list.add(codeName);

            }
        }
        else{
            for (int i = 0; i < names.length; i++) {
                codeName = new CodeName();
                codeName.setCode(i + begin+"");
                codeName.setName(names[i]);
                list.add(codeName);
            }
        }
        return list;
    }
    public static void main(String[] args) throws  Exception{
        List<CodeName> payTypes = Test.buildStatisList("支付宝,微信,预付,现金",false,true);
        for(CodeName str:payTypes){
            System.out.println(str.getCode()+" -> "+str.getName());
        }
    }
}
