package cz.asmk.muvsweb.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class ImageTest {

	@ParameterizedTest
	@CsvFileSource(resources = "/csv-test/Image-in.csv", numLinesToSkip = 1)
	@Tag("UnitTest")
	void testNewCategory(String title, String url, String urlThumb, String descriptionCz, String descriptionEn,
						 String ordering, String result){
		boolean res = Boolean.valueOf(result);
		int order = Integer.valueOf(ordering);
		Article art = new Article();
		Exception exception = null;
		Image image = null;
		try {
			image = new Image(title, url, urlThumb, descriptionCz, descriptionEn, order, art);
			image.setId(1);
		} catch (Exception e){
			exception = e;
		}
		if(res){
			assertNotNull(image);
			assertNull(exception);
			Image img = new Image();
			Exception ex = null;
			try {
				img.setTitle(title);
				img.setArticle(art);
				img.setDescriptionCz(descriptionCz);
				img.setDescriptionEn(descriptionEn);
				img.setUrl(url);
				img.setUrlThumb(urlThumb);
				img.setOrdering(order);
				img.setId(1);
			} catch (Exception e){
				ex = e;
			}
			assertNull(ex);
			assertEquals(img, image);
		} else {
			assertNull(image);
			assertNotNull(exception);
		}
	}

}