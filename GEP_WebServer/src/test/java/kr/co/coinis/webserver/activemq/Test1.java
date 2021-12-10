package kr.co.coinis.webserver.activemq;

public class Test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		StringBuffer sbParam = new StringBuffer();


		int value = (int)(Math.random()*1000000%1000000);

		value=12;

		String authNumber=String.format("%06d", value);

		System.out.println("authNumber["+authNumber+"]");

		//System.out.println("value["+value+"]["+(int)(Math.random()*1000000)%1000000+"]");


	}

}
