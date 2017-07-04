package kr.co.bit.bonddebt.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class HandlerMapping {

	Map<String, Controller> mappingMap;

	/**
	 * mappingMap.put("/list.do", new ListController());
	 */
	public HandlerMapping(String beanPath) {
		mappingMap = new HashMap<>();
		Properties properties = new Properties();
		System.out.println("call handler mapping");
		try {
			InputStream is = new FileInputStream(new File(beanPath));
			properties.load(is);
			Set<Object> keySet = properties.keySet();

			for (Object key : keySet) {
				String className = properties.getProperty(key.toString());
				System.out.println(key.toString() + ": " + className);

				// reflection ���
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
