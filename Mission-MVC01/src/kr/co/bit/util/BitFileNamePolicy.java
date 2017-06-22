package kr.co.bit.util;

import java.io.File;
import java.util.UUID;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class BitFileNamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File f) {
		String name = f.getName();
		String ext = "";
		int dot = name.lastIndexOf(".");
		if (dot != -1) {
			ext = name.substring(dot); 
		} else {
			ext = "";
		}
		// UUID 유니크한 ID를 32비트형태로 뽑아주는 것
		String str = "bit" + UUID.randomUUID();
		File renameFile = new File(f.getParent(), str + ext);

		return renameFile;
	}
}
