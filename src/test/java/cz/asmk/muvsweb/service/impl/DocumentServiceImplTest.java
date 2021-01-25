package cz.asmk.muvsweb.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cz.asmk.muvsweb.entity.Document;
import cz.asmk.muvsweb.service.api.DocumentService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DocumentServiceImplTest {

	@Autowired
	private DocumentService documentService;

	@ParameterizedTest
	@CsvSource("file001,path/to/file1,.xml,Example of description 1")
	@Tag("IntegrationTest")
	@Order(1)
	void testNewDocument(String name, String path, String extension, String description){
		Document document = new Document(name, path, extension, description);
		Document save = documentService.save(document);
		Document document1 = documentService.get(save.getId());
		assertNotNull(save);
		assertNotNull(document1);
		assertEquals(save,document1);
		assertFalse(documentService.findAll().isEmpty());

	}

	@ParameterizedTest
	@CsvSource("file002,path/to/file2,Example of description 2,.csv,.nok")
	@Tag("IntegrationTest")
	@Order(2)
	void testSomeExtensions(String name, String path, String description, String ex1, String ex2){
		Document document = new Document(name, path, ex1, description);
		Document save = documentService.save(document);
		assertEquals(save.getExtention(), ex1);
		assertEquals(save.getExtention(), document.getExtention());
		Exception exception = null;
		try {
			save.setExtention(ex2);
		} catch (Exception e){
			exception = e;
		}
		assertNotNull(exception);
		save = documentService.save(save);
		assertEquals(save.getExtention(), ex1);
	}

	@Test
	@Tag("IntegrationTest")
	@Order(3)
	void deleteAllDocuments(){
		List<Document> all = documentService.findAll();
		assertEquals(all.size(),2);
		all.forEach(c -> documentService.delete(c.getId()));
		List<Document> finalDocuments = documentService.findAll();
		assertTrue(finalDocuments.isEmpty());
	}

}