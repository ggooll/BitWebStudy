package kr.co.bit.framework;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import kr.co.bit.framework.annotation.RequestMapping;

public class HandlerMapping {
	
	private Map<String, CtrlAndMethod> mappings = null;
	
	/*
	 * kr.co.bit.board.controller.BoardController|
	   kr.co.bit.login.controller.LoginController
	 */
	public HandlerMapping(String ctrlNames) throws Exception {
		
		mappings = new HashMap<>();
		
		String[] ctrls = ctrlNames.split("\\|");
		
		for(String ctrl : ctrls) {
			Class<?> clz = Class.forName(ctrl.trim());
			Object target = clz.newInstance();
			System.out.println("target : " + target);
			
			Method[] methods = clz.getMethods();
//			Method[] methods = clz.getDeclaredMethods();
			for(Method method : methods) {
//				System.out.println(method);
				
				RequestMapping reqAnno 
						= method.getAnnotation(RequestMapping.class);
//				System.out.println("reqAnno : " + reqAnno);
				
				if(reqAnno != null) {
					String uri = reqAnno.value();
//					System.out.println("uri : " + uri);
//					System.out.println("method : " + method);
					
					CtrlAndMethod cam = new CtrlAndMethod(target, method);
					mappings.put(uri, cam);
				}
			}
		}
	}
	
	public CtrlAndMethod getCtrlAndMethod(String uri) {
		return mappings.get(uri);
	}
	
}
















