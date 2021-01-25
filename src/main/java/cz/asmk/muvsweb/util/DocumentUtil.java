package cz.asmk.muvsweb.util;

import cz.asmk.muvsweb.enumeration.FileExtensions;

public class DocumentUtil {

	private static final String WRONG_EXTENSION = "Extension has node type!";
	private static final String WRONG_EXTENSION_ERROR = "Extension has node type! Something went terribly wrong.";

	public static void checkExtension(FileExtensions ext){
		if(ext.equals(FileExtensions.NONE)){
			throw new IllegalArgumentException(WRONG_EXTENSION);
		}
		if(!(ext.equals(FileExtensions.CSV) || ext.equals(FileExtensions.DOCX) || ext.equals(FileExtensions.TXT) || ext.equals(FileExtensions.XML))){
			throw new IllegalArgumentException(WRONG_EXTENSION_ERROR);
		}
	}

	public static FileExtensions getFileExtension(String ext){
		if(ext.equals(".docx")) return FileExtensions.DOCX;
		if(ext.equals(".csv")) return FileExtensions.CSV;
		if(ext.equals(".xml")) return FileExtensions.XML;
		if(ext.equals(".txt")) return FileExtensions.TXT;
		return FileExtensions.NONE;
	}

	public static String getFileExtensionString(FileExtensions ext){
		if(ext.equals(FileExtensions.DOCX)) return ".docx";
		if(ext.equals(FileExtensions.CSV)) return ".csv";
		if(ext.equals(FileExtensions.XML)) return ".xml";
		if(ext.equals(FileExtensions.TXT)) return ".txt";
		return null;
	}

	public static void checkFileExt(String ext){
		FileExtensions fileExtension = getFileExtension(ext);
		checkExtension(fileExtension);
	}
}
