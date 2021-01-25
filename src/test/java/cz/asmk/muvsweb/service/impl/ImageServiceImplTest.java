package cz.asmk.muvsweb.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cz.asmk.muvsweb.entity.Image;
import cz.asmk.muvsweb.service.api.ImageService;

@SpringBootTest
class ImageServiceImplTest {

	@Autowired
	private ImageService imageService;

	@Test
	@Tag("IntegrationTest")
	void testNewImage(){
		Image image = new Image();
		Exception exception = null;
		try {
			image.setTitle("A");
		} catch (Exception e){
			exception = e;
		}
		assertNotNull(exception);
		image.setTitle("TestTitle");
		image.setUrl("url/url");
		image = imageService.save(image);
		Image image1 = imageService.get(image.getId());
		assertNotNull(image1);
	}

	@Test
	@Tag("IntegrationTest")
	void testUpdateImage(){
		Image image = new Image();
		Exception exception = null;
		try {
			image.setTitle("A");
		} catch (Exception e){
			exception = e;
		}
		assertNotNull(exception);
		image.setTitle("TestTitle");
		image.setUrl("url/url");
		image = imageService.save(image);
		Image image1 = imageService.get(image.getId());
		String imgThum = "Thum";
		image1.setUrlThumb(imgThum);
		imageService.update(image1);
		image1 = imageService.get(image.getId());
		assertEquals(imgThum, image1.getUrlThumb());
	}

	@Test
	@Tag("IntegrationTest")
	void deleteImage(){
		Image image = new Image();
		image.setTitle("title");
		image.setUrl("url");
		image.setOrdering(1);
		Image save = imageService.save(image);
		imageService.delete(save.getId());
		Image image1 = imageService.get(save.getId());
		assertNull(image1);
	}
}