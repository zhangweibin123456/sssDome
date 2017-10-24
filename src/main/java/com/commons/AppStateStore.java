package com.commons;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

public class AppStateStore {

	@Autowired(required = true)
	private HttpServletRequest request;
	
	private String appStateAttrPrefix;
	
	public String getAppStateAttrPrefix() {
		return appStateAttrPrefix;
	}

	public void setAppStateAttrPrefix(String appStateAttrPrefix) {
		this.appStateAttrPrefix = appStateAttrPrefix;
	}

	public void setAttribute(String name, Object value) {
		request.getSession().setAttribute(appStateAttrPrefix + name, value);
	}

	public Object getAttribute(String name) {
		return this.request.getSession().getAttribute(appStateAttrPrefix + name);
	}

	public void removeAttribute(String name) {
		this.request.getSession().removeAttribute(appStateAttrPrefix + name);
	}

	public void removeAllAttributes() {
		HttpSession session = this.request.getSession();
		this.removeAllAttributes(session);
	}

	public void removeAllAttributes(HttpSession session) {
		Enumeration<String> names = session.getAttributeNames();
	    while (names.hasMoreElements()) {
	      String name = names.nextElement();
	      if (name.startsWith(this.appStateAttrPrefix)) {
	        session.removeAttribute(name);
	      }
	    }
	}
	
	public Map<String, Object> findAllAttribute() {
		Map<String, Object> attrMap = new HashMap<String, Object>();
		HttpSession session = this.request.getSession();
		Enumeration<String> attributeNames = session.getAttributeNames();
		while(attributeNames.hasMoreElements()) {
			String key = attributeNames.nextElement();
			if (key.startsWith(this.appStateAttrPrefix)) {
				attrMap.put(key.substring(this.appStateAttrPrefix.length()), session.getAttribute(key));
			}
		}
		return attrMap;
	}

}
