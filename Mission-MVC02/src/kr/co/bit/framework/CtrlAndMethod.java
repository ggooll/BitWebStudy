package kr.co.bit.framework;

import java.lang.reflect.Method;

public class CtrlAndMethod {

	// CtrlAndMethod(uri에 대해 어떤 컨트롤러 인스턴스의 어떤 메소드를 호출해야 할지 맵핑)

	private Object target;
	private Method method;

	public CtrlAndMethod(Object target, Method method) {
		this.target = target;
		this.method = method;
	}

	public Object getTarget() {
		return target;
	}

	public Method getMethod() {
		return method;
	}

}
