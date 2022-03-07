package com.doctorbari.helper;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

@Component
public class Helper {

	public String getImgByBLob(byte[] blobData) {
		if (blobData == null)
			return null;
		try {
			byte[] encode = Base64.getEncoder().encode(blobData);
			return new String(encode, "UTF-8");
		} catch (Exception ex) {
			return null;
		}
	}

	public String saveDocument(MultipartFile file, String empgid, String path) {
		try {
			File fileDir = new File(path);
			if (!fileDir.exists()) {
				fileDir.mkdir();
			}
			final String uuid = UUID.randomUUID().toString().replace("-", "");
			String finalName = empgid + "_" + uuid + "." + getExtension(file.getOriginalFilename());
			String filePath = "";
			filePath = Paths.get(path, finalName).toString(); // uploadPath
			BufferedOutputStream stream1 = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
			stream1.write(file.getBytes());
			stream1.close();
			return finalName;
		} catch (Exception e) {
			return null;
		}

	}

	public String getExtension(String originalFinalName) {
		String[] parts = originalFinalName.split("\\.");
		return parts[1];
	}

	public String getEncoded64StringByteArrayPdfFromFile(String fileLoc) {
		try {
			byte[] inFileBytes = Files.readAllBytes(Paths.get(fileLoc));
			return new String(Base64.getEncoder().encode(inFileBytes));
		} catch (IOException ex) {
			System.out.println(ex.toString());
			return null;
		}
	}
}
