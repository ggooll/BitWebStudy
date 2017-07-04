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
		
		// 등록된 모든 컨트롤러에 대해서 객체를 생성하고(target)
		// requestMapping(uri)와 메소드를 맵핑시킴
		for(String ctrl : ctrls) {
			Class<?> clz = Class.forName(ctrl.trim());
			Object target = clz.newInstance();
			System.out.println("target : " + target);
			
			// public만? (상속받은 것 포함)
			Method[] methods = clz.getMethods();
			
			// 내가 정의해 둔 메소드들만(private public ...)
			// Method[] methods = clz.getDeclaredMethods();

			for(Method method : methods) {
				// RequestMapping 어노테이션 등록 된 메소드만 찾음
				RequestMapping reqAnno = method.getAnnotation(RequestMapping.class);
				
				if(reqAnno != null) {
					// RequestMapping에 value("uri")형태를 찾아냄
					String uri = reqAnno.value();
					// CtrlAndMethod(uri에 대해 어떤 컨트롤러 인스턴스의 어떤 메소드를 호출해야 할지 맵핑)
					CtrlAndMethod cam = new CtrlAndMethod(target, method);
					mappings.put(uri, cam);
				}
			}
		}
	}
	
	/**
	 * 맵핑 된 CtrlAndMethod를 리턴함
	 * @param uri
	 * @return
	 */
	public CtrlAndMethod getCtrlAndMethod(String uri) {
		return mappings.get(uri);
	}
	
}
















