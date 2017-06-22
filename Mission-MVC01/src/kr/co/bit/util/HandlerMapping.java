package kr.co.bit.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import kr.co.bit.controller.Controller;

public class HandlerMapping {

	// 어떤 URI에 대해 어떤 객체를 사용할건지 맵핑하기 위해 Map 사용
	Map<String, Controller> mappingMap;

	/**
	 * mappingMap.put("/list.do", new ListController()); -> map에 put하는게 지저분함
	 * properties 파일로 관리하려고 함
	 */
	public HandlerMapping(String beanPath) {
		mappingMap = new HashMap<>();
		Properties properties = new Properties();

		try {
			InputStream is = new FileInputStream(new File(beanPath));
			properties.load(is);
			Set<Object> keySet = properties.keySet();

			for (Object key : keySet) {
				String className = properties.getProperty(key.toString());

				// reflection 사용
				Controller controller = (Controller) Class.forName(className).newInstance();
				mappingMap.put(key.toString(), controller);
			}

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (InstantiationException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public Controller getController(String uri) {
		return mappingMap.get(uri);
	}

}
