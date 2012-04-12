package ch.zhaw.ocr.BitmapParser;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RowParserTest {

	@Before
	public void setUp() throws Exception {
	}

	private void fillRow(ContrastMatrix cm, int rowNo){
		for(int x = 0;x < cm.getContrastMatrix().length;x++){
				cm.setValue(x, rowNo, 1);
		}
	}
	
	@Test
	public void testParse() {
		fail("Not yet implemented");
	}
	

}
