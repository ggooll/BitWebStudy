package kr.co.bit.framework;

import java.util.HashMap;
import java.util.Map;

public class ModelAndView {

	// 어떤 뷰로 갈때, 공유영역에 등록시킬 공유객체
	private String view;
	private Map<String, Object> model;
	
	public ModelAndView() {
		model = new HashMap<>();
	}

	public ModelAndView(String view) {
		this();
		this.view = view;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public Map<String, Object> getModel() {
		return model;
	}

	public void setModel(Map<String, Object> model) {
		this.model = model;
	}
	
	public void addAttribute(String key, Object value) {
		model.put(key, value);
	}
	
}
