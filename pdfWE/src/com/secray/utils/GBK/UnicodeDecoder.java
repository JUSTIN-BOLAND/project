package com.secray.utils.GBK;

public class UnicodeDecoder {
    public static String  escape (String src)
    {
        int i;
        char j;
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length()*6);
        for (i=0;i<src.length() ;i++ )
        {
            j = src.charAt(i);
            if (Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j))
                tmp.append(j);
            else
            if (j<256)
            {
                tmp.append( "%" );
                if (j<16)
                    tmp.append( "0" );
                tmp.append( Integer.toString(j,16) );
            }
            else
            {
                tmp.append( "%u" );
                tmp.append( Integer.toString(j,16) );
            }
        }
        return tmp.toString();
    }
    public static String  unescape (String str)
    {
        String   name   =   str.replace("&#x","%u");
        String src = name.replace(";","");

        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length());
        int  lastPos=0,pos=0;
        char ch;
        while (lastPos<src.length())
        {
            pos = src.indexOf("%",lastPos);
            if (pos == lastPos)
            {
                if (src.charAt(pos+1)=='u')
                {
                    ch = (char)Integer.parseInt(src.substring(pos+2,pos+6),16);
                    tmp.append(ch);
                    lastPos = pos+6;
                }
                else
                {
                    ch = (char)Integer.parseInt(src.substring(pos+1,pos+3),16);
                    tmp.append(ch);
                    lastPos = pos+3;
                }
            }
            else
            {
                if (pos == -1)
                {
                    tmp.append(src.substring(lastPos));
                    lastPos=src.length();
                }
                else
                {
                    tmp.append(src.substring(lastPos,pos));
                    lastPos=pos;
                }
            }
        }
        return tmp.toString();
    }
    public static void main(String[] args) throws java.io.UnsupportedEncodingException{
        String str="&#x7f51;&#x7ad9;&#x9632;&#x706b;&#x5899;";
        String   name   =   str.replace("&#x","%u");
        String name2= name.replace(";","");
       // String s="&#x7f51;&#x7ad9;&#x9632;&#x706b;&#x5899;";
        String str2=unescape(str);
        System.out.println("newStr: " + str2);
    }
}