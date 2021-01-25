package cz.asmk.muvsweb.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

class DocumentTest {

	@ParameterizedTest
	@CsvFileSource(resources = "/csv-test/Document-in.csv", numLinesToSkip = 1)
	@Tag("UnitTest")
	void testNewDocument(String name, String location, String extension, String description, String result){
		Boolean res = Boolean.valueOf(result);
		// create from constructor
		Exception exceptionCon = null;
		Document documentConstr = null;
		try {
			documentConstr = new Document(name, location, extension, description);
			documentConstr.setId(1);
		} catch (Exception e){
			exceptionCon = e;
		}
		// create by sets
		Document documentSets = null;
		Exception exceptionSets = null;
		try {
			documentSets = new Document();
			documentSets.setId(1);
			documentSets.setName(name);
			documentSets.setLocation(location);
			documentSets.setExtention(extension);
			documentSets.setDescription(description);
		} catch (Exception e){
			exceptionSets = e;
		}
		if(res){
			assertNull(exceptionCon);
			assertNull(exceptionSets);
			assertEquals(documentConstr, documentSets);
		} else {
			assertNotNull(exceptionCon);
			assertNotNull(exceptionSets);
			assertNull(documentConstr);
		}
	}

	@ParameterizedTest
	@CsvSource("testname,location/url,.xml,testDesc,.google")
	@Tag("UnitTest")
	void setDocumentExtension(String name, String location, String extension, String description,
							  String wrongExtension){
		Document document = new Document();
		document.setName(name);
		document.setLocation(location);
		document.setDescription(description);
		document.setExtention(extension);
		//
		assertNotNull(document);
		assertEquals(name,document.getName());
		assertEquals(extension, document.getExtention());
		//
		Exception exception = null;
		try {
			document.setExtention(wrongExtension);
		} catch (Exception e){
			exception = e;
		}
		assertNotNull(exception);
		assertEquals(extension, document.getExtention());
	}

}