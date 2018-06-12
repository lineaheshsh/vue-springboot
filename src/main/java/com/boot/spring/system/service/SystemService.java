package com.boot.spring.system.service;

import java.io.File;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service("systemService")
public class SystemService {

	public static String displayFilesize(long fileSize) {
        if (fileSize <= 0) {
            return "0";
        } else {
//            String[] fileUnit = new String[] {"B","KB","MB","GB","TB"};
            int group = (int)(Math.log10(fileSize) / Math.log10(1024));
            return new DecimalFormat("#,##0.#").format(Math.floor(fileSize / Math.pow(1024, group)));
//            return new DecimalFormat("#,##0.#").format(fileSize / Math.pow(1024, group)) + " " + fileUnit[group];
        }
    }
//	
	public Map showSystemInfo() {
	
		File root = null;
		Map result = new HashMap<String, Object>();
		root = new File("C:/");
		
		String path = SystemService.class.getResource("").getPath();
		System.out.println(path);
		
		
//		String[] cmd = new String[3];
//		cmd[0] = "/bin/sh";
//		cmd[1] = "-c";
//        cmd[2] = path + "python.bat";

//		String s;
//		Runtime run = Runtime.getRuntime();
		
//		try {
//			Process p = Runtime.getRuntime().exec(path + "python.bat");
			
//			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
//			while ((s = stdInput.readLine()) != null ) {
//				System.out.println("s : " + s);
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		result.put("TotalSpace", displayFilesize(root.getTotalSpace()) );
		result.put("UsableSpace", displayFilesize(root.getUsableSpace()) );
		
		return result;
	}
}
