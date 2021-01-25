package cz.asmk.muvsweb.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cz.asmk.muvsweb.entity.Category;
import cz.asmk.muvsweb.service.api.CategoryService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CategoryServiceImplTest {

	private static final String TEST_CATEGORY_NAME_1 = "Test1";
	private static final String TEST_CATEGORY_NAME_2 = "Test2";
	private static final String TEST_CATEGORY_PEREX_1 = "TestPerex1";
	private static final String TEST_CATEGORY_PEREX_2 = "TestPerex2";
	//
	private static final String TEST_CATEGORY_NEW_NAME_1 = "TestNew1";
	private static final String TEST_CATEGORY_NEW_NAME_2 = "TestNew2";
	//
	private long id1 = -1;
	private long id2 = -1;

	@Autowired
	private CategoryService categoryService;

	@BeforeAll
	static void before(){
		System.out.println("First test names");
		System.out.println(TEST_CATEGORY_NAME_1);
		System.out.println(TEST_CATEGORY_NAME_2);
		System.out.println(TEST_CATEGORY_PEREX_1);
		System.out.println(TEST_CATEGORY_PEREX_2);
		System.out.println("Next changed names");
		System.out.println(TEST_CATEGORY_NEW_NAME_1);
		System.out.println(TEST_CATEGORY_NEW_NAME_2);
		System.out.println("Test starting ...");
		System.out.println();
	}

	@Test
	@Order(1)
	void testCreate(){
		Category ct1 = new Category();
		ct1.setTitleCz(TEST_CATEGORY_NAME_1);
		ct1.setTitleEn(TEST_CATEGORY_NAME_1);
		ct1.setPerexCz(TEST_CATEGORY_PEREX_1);
		ct1.setPerexEn(TEST_CATEGORY_PEREX_1);
		categoryService.save(ct1);
		//
		Category ct2 = new Category();
		ct2.setTitleCz(TEST_CATEGORY_NAME_2);
		ct2.setTitleEn(TEST_CATEGORY_NAME_2);
		ct2.setPerexCz(TEST_CATEGORY_PEREX_2);
		ct2.setPerexEn(TEST_CATEGORY_PEREX_2);
		categoryService.save(ct2);
	}

	@Test
	@Tag("IntegrationTest")
	@Order(2)
	void testFindAllCategories(){
		List<Category> all = categoryService.findAll();
		assertEquals(2, all.size());
		int countFound = 0;
		for (Category cat : all) {
			if(cat.getTitleCz().equals(TEST_CATEGORY_NAME_1)){
				assertEquals(TEST_CATEGORY_PEREX_1, cat.getPerexCz());
				assertEquals(TEST_CATEGORY_PEREX_1, cat.getPerexEn());
				assertEquals(TEST_CATEGORY_NAME_1, cat.getTitleEn());
				countFound ++;
			}
			if(cat.getTitleCz().equals(TEST_CATEGORY_NAME_2)){
				assertEquals(TEST_CATEGORY_PEREX_2, cat.getPerexCz());
				assertEquals(TEST_CATEGORY_PEREX_2, cat.getPerexEn());
				assertEquals(TEST_CATEGORY_NAME_2, cat.getTitleEn());
				countFound ++;
			}
		}
		assertEquals(2, countFound);
	}

	@Test
	@Tag("IntegrationTest")
	@Order(3)
	void testUpdateCategory(){
		List<Category> all = categoryService.findAll();
		Category update1 = null;
		Category update2 = null;
		for (Category cat : all) {
			if(cat.getTitleCz().equals(TEST_CATEGORY_NAME_1)){
				cat.setTitleCz(TEST_CATEGORY_NEW_NAME_1);
				cat.setTitleEn(TEST_CATEGORY_NEW_NAME_1);
				update1 = categoryService.update(cat);
				assertNotNull(update1);
				id1 = update1.getId();
			}
			if(cat.getTitleCz().equals(TEST_CATEGORY_NAME_2)){
				cat.setTitleCz(TEST_CATEGORY_NEW_NAME_2);
				cat.setTitleEn(TEST_CATEGORY_NEW_NAME_2);
				update2 = categoryService.update(cat);
				assertNotNull(update2);
				id2 = update2.getId();
			}
		}
		assertNotEquals(id1,id2);
		assertEquals(TEST_CATEGORY_NEW_NAME_1, update1.getTitleEn());
		assertEquals(TEST_CATEGORY_NEW_NAME_1, update1.getTitleCz());
		assertEquals(TEST_CATEGORY_NEW_NAME_2, update2.getTitleEn());
		assertEquals(TEST_CATEGORY_NEW_NAME_2, update2.getTitleCz());
		//
		Category categoryFinal1 = categoryService.get(id1);
		Category categoryFinal2 = categoryService.get(id2);
		assertEquals(update1, categoryFinal1);
		assertEquals(update2, categoryFinal2);
	}

	@Test
	@Tag("IntegrationTest")
	@Order(4)
	void testGetCategory(){
		List<Category> all = categoryService.findAll();
		assertFalse(all.isEmpty());
		Category categoryFinal1 = null;
		Category categoryFinal2 = null;
		for (Category cat : all){
			if(cat.getTitleCz().equals(TEST_CATEGORY_NEW_NAME_1)){
				categoryFinal1 = categoryService.get(cat.getId());
			}
			if(cat.getTitleCz().equals(TEST_CATEGORY_NEW_NAME_2)){
				categoryFinal2 = categoryService.get(cat.getId());
			}
		}
		assertNotNull(categoryFinal1);
		assertNotNull(categoryFinal2);
		assertEquals(TEST_CATEGORY_NEW_NAME_1, categoryFinal1.getTitleEn());
		assertEquals(TEST_CATEGORY_NEW_NAME_1, categoryFinal1.getTitleCz());
		assertEquals(TEST_CATEGORY_NEW_NAME_2, categoryFinal2.getTitleEn());
		assertEquals(TEST_CATEGORY_NEW_NAME_2, categoryFinal2.getTitleCz());
		Exception exception = null;
		Category wrong = null;
		try {
			wrong = categoryService.get(id1);
		} catch (Exception e){
			exception = e;
		}
		assertNull(exception);
		assertNull(wrong);
	}

	@Test
	@Tag("IntegrationTest")
	@Order(5)
	void deleteCategory(){
		List<Category> all = categoryService.findAll();
		assertFalse(all.isEmpty());
		for (Category cat : all){
			categoryService.delete(cat.getId());
		}
		assertTrue(categoryService.findAll().isEmpty());
	}

}