package com.zjt.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {

	// 获得request
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	// 获得request 属性值
	public String getRequestValue(String key) {
		String paramValue = getRequest().getParameter(key);
		if (paramValue == null) {
			return "";
		}
		return paramValue;
	}

	// 获得request 属性值
	public String getUTF8RequestValue(String key)
			throws UnsupportedEncodingException {
		String paramValue = new String(getRequest().getParameter(key).getBytes(
				"iso-8859-1"), "UTF-8");
		if (paramValue == null || "".equals(paramValue)) {
			return "";
		}
		// java.net.URLDecoder.decode(key, "UTF-8");
		return paramValue;
	}

	// 获得request 属性值
	public String getGBKRequestValue(String key)
			throws UnsupportedEncodingException {
		String paramValue = new String(getRequest().getParameter(key).getBytes(
				"iso-8859-1"), "GBK");
		if (paramValue == null || "".equals(paramValue)) {
			return "";
		}
		return paramValue;
	}

	/**
	 * 设置request属性值
	 * 
	 * @param key
	 * @param value
	 */
	public void setRequestValue(String key, Object value) {
		getRequest().setAttribute(key, value);
	}

	/**
	 * 转URL码
	 * 
	 * @param obj
	 * @return
	 */
	public String decodeURL(String obj) {
		try {
			return obj == null ? "" : java.net.URLDecoder.decode(obj, "utf-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	/**
	 * 加密URL码
	 * 
	 * @param obj
	 * @return
	 */
	public String encodeURL(String obj) {
		try {
			return obj == null ? "" : java.net.URLEncoder.encode(java.net.URLEncoder.encode(obj, "utf-8").replaceAll("\\+", "%20"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	// 获得session
	public HttpSession getHttpSession() {
		return ServletActionContext.getRequest().getSession();
	}

	// 获得session属性值
	public Object getSessionAttribute(String key) {
		return this.getHttpSession().getAttribute(key);
	}

	// 获得response
	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	// 获得application
	public ServletContext getApplication() {
		return ServletActionContext.getServletContext();
	}

	public void clearHttpSession(String key) {
		if (getHttpSession().getAttribute(key) != null) {
			getHttpSession().removeAttribute(key);
		}
	}

	// 获得struts2 session
	public Object getSession(String name) {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		return session.get(name);
	}
	
	public Map<String, Object> getSession() {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		return session;
	}
	/**
	 * 获取session里面int类型的值
	 * @param name
	 * @return
	 */
	public int  getSessionIntValue(String name){
		  Object obj = getSession(name);
		  if(obj != null && NumberUtils.isNumber(obj.toString())){
			  return Integer.parseInt(obj.toString()); 
		  }
		  return  -1;
	}

	public void reSetSession(String key, String value) {
		if (getHttpSession().getAttribute(key) != null) {
			getHttpSession().removeAttribute(key);
		}
		getHttpSession().setAttribute(key, value);
	}

	public void removeSession(String key) {
		if (getHttpSession().getAttribute(key) != null) {
			getHttpSession().removeAttribute(key);
		}
	}

	// 设置session属性值
	public void setSession(String name, Object value) {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		session.put(name, value);
	}

	// 清除页面缓存
	public void setResponseNoCache(HttpServletResponse response) {
		response.setHeader("progma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.setDateHeader("Expires", 0);
	}
	/**
	 *  输出字符串
	 * @param str
	 */
	public void writeString(String str) {
		PrintWriter pw = null;
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			pw = response.getWriter();
			setResponseNoCache(response);
			pw.print(str);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.flush();
				pw.close();
			}
		}
	}

	/**
	 * 输出js内容
	 * 
	 * @since 2013-07-15
	 * @param str
	 *            eg: alert('下载失败！');window.history.back();
	 */
	public void writeJsContent(String str) {
		PrintWriter pw = null;
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			setResponseNoCache(response);
			pw = response.getWriter();
			pw.print("<script type=\"text/javascript\">" + str + "</script>");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.flush();
				pw.close();
				pw = null;
			}
		}
	}

	// 文件上传
	public File[] uploadFiles() {
		MultiPartRequestWrapper multiWrapper = (MultiPartRequestWrapper) this
				.getRequest();
		// String[] fileNames = multiWrapper.getFileNames("upload");
		File[] files = multiWrapper.getFiles("upload");
		return files;
	}


	/**
	 * 复制文件
	 * @param src
	 * @param dst
	 */
	@SuppressWarnings("unused")
	private void copy(File src, File dst) {
		int BUFFER_SIZE = 1024 * 1024;
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
			out = new BufferedOutputStream(new FileOutputStream(dst),
					BUFFER_SIZE);
			byte[] buffer = new byte[BUFFER_SIZE];
			int len = 0;
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	// 以json格式输出
		public void writeJson(Object obj) {
			PrintWriter pw = null;
			try {
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("application/json;charset=utf-8");
				pw = response.getWriter();
				setResponseNoCache(response);
				Object jsonObject = null;
				JsonConfig jsonConfig = new JsonConfig();
//				jsonConfig.registerJsonValueProcessor(java.util.Date.class,
//						new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
				if (obj instanceof List) {
					jsonObject = JSONArray.fromObject(obj, jsonConfig);
				} else {
					jsonObject = JSONObject.fromObject(obj, jsonConfig);
				}
				pw.print(jsonObject.toString());
				
			} catch (IOException e) {
				e.printStackTrace();
			} finally{
				if(pw != null){
					pw.flush();
					pw.close();
				}
			}
		}
 
	/**
	 * 用户下载文件 
	 * @param fileName
	 * @param pathType
	 * @param outputName
	 */
	public void writeFile(String dirPath, String pathType, String outputName) {
		try {
			//这个路径后期动态传入
			dirPath = dirPath.replaceAll("\\\\", "/");

			File file = new File(dirPath);
			if (!file.exists() || !file.isFile()) {
				writeJsContent("alert('\u672A\u627E\u5230\u5F85\u4E0B\u8F7D\u6587\u4EF6\uFF0C\u53EF\u80FD\u5DF2\u7ECF\u88AB\u5220\u9664\uFF01');window.history.back();");
				return;
			}
			OutputStream os = new BufferedOutputStream(getResponse()
					.getOutputStream());
			InputStream fis = new BufferedInputStream(new FileInputStream(
					dirPath));

			getResponse().setContentType("application/octet-stream");
			getResponse().setHeader(
					"Content-Disposition",
					"attachment;filename="
							+ new String(outputName.getBytes("gbk"),
									"iso8859-1"));

			// 缓冲输出
			byte[] buff = new byte[500];
			int cha = -1;
			while ((cha = fis.read(buff, 0, buff.length)) != -1) {
				os.write(buff, 0, cha);
			}
			fis.close();
			os.flush();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8806256272457465346L;
	protected Logger LOG;
	public BaseAction() {
		// TODO Auto-generated constructor stub
		LOG = Logger.getLogger(this.getClass());
	}
	/**
	 * String/object转int
	 */
	public int toInt(Object o){
		return Integer.parseInt(o.toString());
	}
	
	
	/**
	 * int/object转String
	 */
	public String toStr(Object n){
		return n == null ? "" : String.valueOf(n);
	}
	/**
	 * 判断list是否为空，包括null判断和size为0判断
	 * @param l
	 * @return	空则返回true
	 */
	@SuppressWarnings("rawtypes") 
	public boolean eptList(List l){
		return l == null || l.size() == 0;
	}
	
	/**
	 * 移除非法字符
	 * @return
	 */
	public String delInvChar(String str){
		String newChar = "_";	//替換成此字符
		String invChar[] = new String[]{"\\", "/", "&", "#", "%", "*", "|", "\"", ":", "@", "<", ">"};
		for (String c : invChar) {
			if(str.contains(c)){
				str = str.replace(c, newChar);
			}
		}
		return str;
	}
	/**
	 * 判断是否有非法字符
	 * @return
	 * 	有则返回所有非法字符
	 * 	没有则返回null
	 */
	public String hasInvChar(String str){
		String invChar[] = new String[]{"\\", "/", "&", "#", "%", "*", "|", "\"", ":", "@", "<", ">"};
		for (String c : invChar) {
			if(str.contains(c)){
				String s = "";
				for (String cc : invChar) {
					s += cc + "  ";
				}
				return s;
			}
		}
		return null;
	}

}
