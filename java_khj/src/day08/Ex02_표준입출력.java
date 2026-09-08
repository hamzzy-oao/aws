package day08;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Ex02_표준입출력 {

	public static void main(String[] args) {
		   /* 스트림
	       * - 데이터를 읽거나 쓰는데 추상화된 개념 
	       * - 데이터를 연속적으로 처리
	       * - 분류
	       *   - 입력 vs 출력
	       *   - 바이트 vs 문자
	       *   - 기반 vs 보조
	       * 
	       * 표준 입출력 p.549
	       * - PrintStream out : 표준 출력 스트림
	       * - InputStream in : 표준 입력 스트림
	       * - OutputStream err : 표준 오류 스트림
	       * 
	       * InputStream
	       * - 바이트 단위 입력 스트림 중 최상위 스트림
	       * 
	       * OutputStream
	       * - 바이트 단위 출력 스트림 중 최상위 스트림
	       * 
	       * Reader
	       * - 문자 단위 입력 스트림 중 최상위 스트림
	       * - FileReader
	       * 
	       * Writer
	       * - 문자 단위 출력 스트림 중 최상위 스트림
	       * - FileWriter
	       * 
	       */
	      String fileName = "src/day08/data.txt";
	      
	      FileReader fr = null;
	      
	      try {
	    	  //파일을 염. 파일이 없으면 예외 발생
	         fr = new FileReader(fileName);
	         //파일이 준비되면 반복. 아니면 종료
	         while(fr.ready()) {
	        	 //한 문자를 읽어옴. 왜? FileReader는 문자기반으로 읽는 스트림이어서
	        	 char ch = (char)fr.read();
	        	 System.out.print(ch);
	         }
	      }catch (FileNotFoundException e) {
	    	  e.printStackTrace();
	      }catch(IOException e) {
	    	  e.printStackTrace();
	      }finally {
	    	  if(fr != null) {
	    		  try {
	    			  fr.close(); 
	    		  }catch (IOException e) {
	    			  e.printStackTrace();
	    		  }
	    	  }
	      }
	      
   }

}