package com.sensei.encore.test.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class FileLocatorUtils {
	public static final String PDF_TYPE = "PDF";
	public static final String XLS_TYPE = "EXCEL97";
	public static final String XLSX_TYPE = "EXCEL";
	public static final String CSV_TYPE = "CSV";
	public static final String PSV_TYPE = "PSV";
	public static final String TXT_TYPE = "TXT";
	public static final String TUDF_TYPE = "TUDF";
	public static final String TAP_TYPE = "TAP";
	public static final String CDF_TYPE = "CDF";

	private static final Integer MAX_FILE_NAME_LENGTH = 128;

    public static final List<String> ImageTypes = Arrays.asList("png", "jpg", "gif", "jpeg", "pdf", "tif");

	// Public methods to access files in EncoreSiteDir and perform some file processing functions
    public static boolean isValidDocumentType(String fileName) {
        return FileLocatorUtils.ImageTypes.contains(getFileExtension(fileName).toLowerCase());
    }
	public static String getFileHandle (String fileName) {
    	int pos = fileName.lastIndexOf('.');
    	return (pos == -1) ? fileName : fileName.substring(0, pos);
    }
	public static String getFileExtension(String fileName){
    	int pos = fileName.lastIndexOf('.');
		return (pos == -1)? "" : fileName.substring(pos+1).toLowerCase();
	}
    public static String getBaseName (String fullName) {
    	return new File(fullName).getName();
    }
    public static String getDirName (String fullName) {
    	return new File(fullName).getParent();
    }
    public static boolean isValidFileName(String fileName) {
    	return fileName != null && !fileName.isEmpty() && fileName.length() <= MAX_FILE_NAME_LENGTH && !fileName.contains("..") &&
    		fileName.matches("[A-Za-z0-9\\-_ \\.\\[\\]\\{\\}\\(\\)\\$]*");
    }
    public static boolean isValidPdfFile(String fileName) {
    	String baseName = getBaseName(fileName);
    	String extn = getFileExtension(baseName);
    	return isValidFileName(baseName) && isPDF(extn);
    }
    public static boolean isValidCsvFile(String fileName) {
    	String baseName = getBaseName(fileName);
    	String extn = getFileExtension(baseName);
    	return isValidFileName(baseName) && isCSV(extn);
    }
	public static boolean isValidPsvFile(String fileName) {
		String baseName = getBaseName(fileName);
		String extn = getFileExtension(baseName);
		return isValidFileName(baseName) && isPSV(extn);
	}
    public static boolean isValidXlsFile(String fileName) {
    	String baseName = getBaseName(fileName);
    	String extn = getFileExtension(baseName);
    	return isValidFileName(baseName) && isXLS(extn);
    }
    public static boolean isValidXlsxFile(String fileName) {
    	String baseName = getBaseName(fileName);
    	String extn = getFileExtension(baseName);
    	return isValidFileName(baseName) && isXLSX(extn);
    }
    public static boolean isValidExcelFile(String fileName) {
    	String baseName = getBaseName(fileName);
    	String extn = getFileExtension(baseName);
    	return isValidFileName(baseName) && (isXLSX(extn) || isXLS(extn));
    }
	public static boolean isValidCsvOrExcelFile(String fileName) {
    	String baseName = getBaseName(fileName);
    	String extn = getFileExtension(baseName);
    	return isValidFileName(baseName) && (isXLSX(extn) || isXLS(extn) || isCSV(extn));
	}
    public static boolean isValidTxtFile(String fileName) {
    	String baseName = getBaseName(fileName);
    	String extn = getFileExtension(baseName);
    	return isValidFileName(baseName) && isTXT(extn);
    }
    public static boolean isValidImageFile(String fileName) {
    	String baseName = getBaseName(fileName);
    	String extn = getFileExtension(baseName);
    	return isValidFileName(baseName) && isImage(extn.toLowerCase());
    }
	public static boolean isPDF (String fileType) {
		return (PDF_TYPE).equalsIgnoreCase(fileType);
	}
	public static boolean isXLS (String fileType) {
		return (XLS_TYPE).equalsIgnoreCase(fileType) || ("XLS").equalsIgnoreCase(fileType);
	}
	public static boolean isTUDF (String fileType) {
		return (TUDF_TYPE).equalsIgnoreCase(fileType);
	}
	public static boolean isXLSX (String fileType) {
		return (XLSX_TYPE).equalsIgnoreCase(fileType) || ("XLSX").equalsIgnoreCase(fileType);
	}
	public static boolean isTAP (String fileType) {
		return (TAP_TYPE).equalsIgnoreCase(fileType);
	}
	public static boolean isCDF (String fileType) {
		return (CDF_TYPE).equalsIgnoreCase(fileType);
	}
	public static boolean isCSV (String fileType) {
		return (CSV_TYPE).equalsIgnoreCase(fileType);
	}
	public static boolean isPSV (String fileType) {
		return (PSV_TYPE).equalsIgnoreCase(fileType);
	}
	public static boolean isTXT (String fileType) {
		return (TXT_TYPE).equalsIgnoreCase(fileType);
	}
	public static boolean isImage (String fileType) {
		return ImageTypes.contains(fileType);
	}
	public static String getFileName(String fileName, String fileType) {
		if (isPDF(fileType))
			return fileName + ".pdf";
		if (isXLS(fileType))
			return fileName + ".xls";
		if (isXLSX(fileType))
			return fileName + ".xlsx";
		if (isCSV(fileType))
			return fileName + ".csv";
		if (isPSV(fileType))
			return fileName + ".psv";
		if (isTXT(fileType))
			return fileName + ".txt";
		if (isTUDF(fileType))
			return fileName + ".tudf";
		if (isTAP(fileType))
			return fileName + ".tap";
		if (isCDF(fileType))
			return fileName + ".cdf";
		return "";
	}
	public static String getContentType(String fileType) {
		if (isPDF(fileType))
			return("pdf");
		if (isXLS(fileType))
			return "vnd.ms-excel";
		if (isXLSX(fileType))
			return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		if (isCSV(fileType))
			return("csv");
		if (isPSV(fileType))
			return("psv");
		if (isPSV(fileType))
			return("tudf");
		return "";
	}

	public static List<String> readAllLinesFromFile(String file, boolean skipHeader) {
		List<String> lines = new ArrayList<>();
		try (FileReader fileReader = new FileReader(file);
			 BufferedReader bufferedReader = new BufferedReader(fileReader)) {
			String line = null;
			if (skipHeader)
				bufferedReader.readLine();
			while ((line = bufferedReader.readLine()) != null) {
				lines.add(line);
			}
		} catch (IOException e) {
		}
		return lines;
	}
	public static List<String> readAllLines(URL resource) {
		return readAllLinesFromFile(resource.getFile(), true);
	}
	public static List<String> getFileTypes(){
		List<String> fileTypes = new ArrayList<>();
		fileTypes.add(PDF_TYPE);
		fileTypes.add(XLS_TYPE);
		fileTypes.add(XLSX_TYPE);
		fileTypes.add(CSV_TYPE);
		fileTypes.add(PSV_TYPE);
		fileTypes.add(TXT_TYPE);
		fileTypes.add(TUDF_TYPE);
		return fileTypes;
	}

}
