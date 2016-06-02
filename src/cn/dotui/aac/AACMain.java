/**
 * AACMain.java Created on 2016年6月2日
 */
package cn.dotui.aac;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * The class <code>AACMain</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class AACMain {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws Exception {
//		final InputStream in = AACMain.class.getResourceAsStream("/test.aac");
		final InputStream in =new FileInputStream("C:\\Users\\Feng\\Desktop\\ct_faac-adts.aac");
		long length = 0;
		int count = 0;
		while (true) {
			final ADTS_Header adts_Header = new ADTS_Header();
			if (adts_Header.readHeader(in)) {
				System.out.println(adts_Header);
				length += (adts_Header.getFrame_length() + 7);
				count++;
			} else {
				break;
			}
		}
		System.out.println("count:" + count);
		System.out.println("length:" + length);
	}

}
