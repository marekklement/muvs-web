package cz.asmk.muvsweb.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import cz.asmk.muvsweb.enumeration.FileExtensions;

class DocumentUtilTest {

	@ParameterizedTest
	@CsvSource(".xml,.csv,.txt,.docx")
	@Tag("UnitTest")
	@Tag("UtilTest")
	void getFileExtensionOk(String in1, String in2, String in3, String in4){
		Exception ex = null;
		FileExtensions extension = null;
		FileExtensions extension2 = null;
		FileExtensions extension3 = null;
		FileExtensions extension4 = null;
		try {
			extension = DocumentUtil.getFileExtension(in1);
			extension2 = DocumentUtil.getFileExtension(in2);
			extension3 = DocumentUtil.getFileExtension(in3);
			extension4 = DocumentUtil.getFileExtension(in4);
		} catch (Exception e){
			ex = e;
		}
		assertNull(ex);
		assertNotNull(extension);
		assertNotNull(extension2);
		assertNotNull(extension3);
		assertNotNull(extension4);
		assertEquals(extension,FileExtensions.XML);
		assertEquals(extension2,FileExtensions.CSV);
		assertEquals(extension3,FileExtensions.TXT);
		assertEquals(extension4,FileExtensions.DOCX);
	}

	@ParameterizedTest
	@CsvSource(".xml,.csv,.txt,.docx")
	@Tag("UnitTest")
	@Tag("UtilTest")
	void getFileExtensionStringOk(String in1, String in2, String in3, String in4){
		FileExtensions extension = DocumentUtil.getFileExtension(in1);
		FileExtensions extension2 = DocumentUtil.getFileExtension(in2);
		FileExtensions extension3 = DocumentUtil.getFileExtension(in3);
		FileExtensions extension4 = DocumentUtil.getFileExtension(in4);
		assertNotNull(extension);
		assertNotNull(extension2);
		assertNotNull(extension3);
		assertNotNull(extension4);
		assertEquals(extension,FileExtensions.XML);
		assertEquals(extension2,FileExtensions.CSV);
		assertEquals(extension3,FileExtensions.TXT);
		assertEquals(extension4,FileExtensions.DOCX);
		//
		assertEquals(DocumentUtil.getFileExtensionString(extension), in1);
		assertEquals(DocumentUtil.getFileExtensionString(extension2), in2);
		assertEquals(DocumentUtil.getFileExtensionString(extension3), in3);
		assertEquals(DocumentUtil.getFileExtensionString(extension4), in4);
	}

	@ParameterizedTest
	@CsvSource(".lol,.baba,.none")
	@Tag("UnitTest")
	@Tag("UtilTest")
	void getFileExtensionNok(String in1, String in2, String in3){
		Exception ex = null;
		FileExtensions extension = null;
		FileExtensions extension2 = null;
		FileExtensions extension3 = null;
		try {
			extension = DocumentUtil.getFileExtension(in1);
			extension2 = DocumentUtil.getFileExtension(in2);
			extension3 = DocumentUtil.getFileExtension(in3);
		} catch (Exception e){
			ex = e;
		}
		assertNull(ex);
		assertNotNull(extension);
		assertNotNull(extension2);
		assertNotNull(extension3);
		assertEquals(extension,FileExtensions.NONE);
		assertEquals(extension2,FileExtensions.NONE);
		assertEquals(extension3,FileExtensions.NONE);
	}

	@Test
	@Tag("UnitTest")
	@Tag("UtilTest")
	void getFileExtensionStringNok(){
		FileExtensions none = FileExtensions.NONE;
		assertNull(DocumentUtil.getFileExtensionString(none));
		//
		String str = null;
		Exception e = null;
		try {
			str = DocumentUtil.getFileExtensionString(null);
		} catch (Exception ex){
			e = ex;
		}
		assertNotNull(e);
		assertNull(str);
	}

}