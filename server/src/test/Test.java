package test;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
  /*        String st="66 00 00 00 00 00 01 33 99 66 00 00 00 00 00 01 44 99";
          int begin = st.indexOf("66");
          int end = st.indexOf("99");
          System.out.println( begin+ " : " + end +" : "+st.substring(begin, end+2));*/
		String st="66 SN1 SN2 SN3 SN4 [NO1] [NO2] 55 99";
		String[] sts = st.split("\\s+");
		System.out.println(sts[sts.length -2]);
		
		st="66 00 00 00 00 00 01 55 99";
		String[] recieves = st.split("\\s+");
		String clientId = recieves[1]+" "+recieves[2]+" "+recieves[3]+" "+recieves[4];
		String machineId = recieves[5]+" "+recieves[6];
			System.out.println(machineId);
	}

}
