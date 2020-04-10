package com.gwideal.fckeditor;

import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 上传规则定义类。
 * 
 * 在上传之前将上传规则对象保存在session中，之后编辑器或其他上传对象将根据上传规则上传文件。
 * 
 * 编辑器浏览服务器的根路径：rootPath。模板制作时需要指定根路径，以便上传图片。
 * 
 * 定义上传路径
 * 
 * @author liufang
 * 
 */
public class UploadRule implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 在session中的key
	 */
	public static final String KEY = "_upload_rule";

	/**
	 * 构造器
	 * 
	 * @param rootPath
	 *            根路径。浏览服务器的根路径。上传时可能需要再加上类别路径
	 * @param pathPrefix
	 *            路径前缀
	 * @param isGenName
	 *            是否创建随机文件名
	 * @param hasType
	 *            是否区分文件类别。模板制作时不需要，其他情况下需要
	 * @param needClear
	 *            是否需要清除未使用的上传文件
	 */
	public UploadRule(String rootPath, String pathPrefix, boolean isGenName,
			boolean hasType, boolean needClear) {
		this.rootPath = rootPath;
		this.pathPrefix = pathPrefix;
		this.isGenName = isGenName;
		this.hasType = hasType;
		this.needClear = needClear;
	}

	/**
	 * 构造器
	 * 
	 * @param rootPath
	 *            根路径。浏览服务器的根路径。上传时可能需要再加上类别路径
	 * @param isGenName
	 *            是否创建随机文件名
	 * @param hasType
	 *            是否区分文件类别。模板制作时不需要，其他情况下需要
	 */

	public UploadRule(String rootPath, String pathPrefix, boolean isGenName,
			boolean hasType) {
		this(rootPath, pathPrefix, isGenName, hasType, true);
	}

	/**
	 * 构造器
	 * 
	 * @param rootPath
	 *            根路径。浏览服务器的根路径。上传时可能需要再加上类别路径
	 * @param isGenName
	 *            是否创建随机文件名
	 */

	public UploadRule(String rootPath, String pathPrefix, boolean isGenName) {
		this(rootPath, pathPrefix, isGenName, true, true);
	}

	/**
	 * 构造器
	 * 
	 * @param rootPath
	 *            根路径。浏览服务器的根路径。上传时可能需要再加上类别路径
	 */

	public UploadRule(String rootPath, String pathPrefix) {
		this(rootPath, pathPrefix, true, true, true);
	}

	/**
	 * 获得文件全名
	 * 
	 * 目录前缀/年+季度/月+日/文件名.suffix
	 * 
	 * @return
	 */
	public String getPathName(String fileName, String suffix, String type) {
		StringBuilder sb = new StringBuilder(getPathPrefix()).append(type)
				.append(genFilePath());
		if (isGenName) {
			sb.append(genFileName());
		} else {
			sb.append(fileName);
		}
		sb.append('.').append(suffix);
		return sb.toString();
	}

	/**
	 * 按当前日期生产路径：/2008_2/5_20/，/年_季/月_日/
	 * 
	 * @return
	 */
	public static String genFilePath() {
		StringBuilder sb = new StringBuilder();
		Calendar cal = Calendar.getInstance();
		sb.append("/").append(cal.get(Calendar.YEAR)).append('_').append(
				cal.get((Calendar.MONTH)) / 3 + 1).append("/").append(
				cal.get(Calendar.MONTH) + 1).append('_').append(
				cal.get(Calendar.DAY_OF_MONTH)).append("/");
		return sb.toString();
	}

	/**
	 * 获得文件名
	 * 
	 * 4位随机数加上当前时间
	 * 
	 * @return
	 */
	public static String genFileName() {
		String name = StrUtils.longToN36(System.currentTimeMillis());
		return RandomStringUtils.random(4, StrUtils.N36_CHARS) + name;
	}

	/**
	 * 获得可上传图片的后缀，如没有指定，则使用默认的后缀集合。
	 * 
	 * @return
	 */
	public Set<String> getAcceptImg() {
		if (acceptImg == null) {
			acceptImg = new HashSet<String>();
			for (String s : DEF_IMG_ACCEPT) {
				acceptImg.add(s);
			}
		}
		return acceptImg;
	}

	/**
	 * 获得可上传附件的后缀，如没有指定，则使用默认的后缀集合。
	 * 
	 * @return
	 */
	public Set<String> getAcceptFile() {
		if (acceptFile == null) {
			acceptFile = new HashSet<String>();
			for (String s : DEF_File_ACCEPT) {
				acceptFile.add(s);
			}
		}
		return acceptFile;
	}
	
	
	public void addUploadFile(String origName, String fileName,
			String realPath, long size,String description) {
		if (uploadFiles == null) {
			uploadFiles = new HashMap<String, UploadFile>();
		}
		uploadFiles.put(fileName, new UploadFile(origName, fileName, realPath,
				size,description));
	}

	public void removeUploadFile(String fileName) {
		if (uploadFiles != null) {
			uploadFiles.remove(fileName);
		}
	}

	public Map<String, UploadFile> getUploadFiles() {
		return uploadFiles; 
	}

	public void clearUploadFile() {
//		if (uploadFiles != null && needClear) {
//			for (UploadFile uf : uploadFiles.values()) {
//				File file = new File(uf.getRealPath());
//				if (file.delete()) {
//					log.debug("删除未被使用的文件：{}", file.getName());
//				} else {
//					log.warn("删除文件失败：{}", file.getName());
//				}
//			}
//			uploadFiles.clear();
//		}
	}

	/**
	 * 已经上传到图片
	 */
	private Map<String, UploadFile> uploadFiles;

	/**
	 * 可以上传的图片后缀
	 */
	private Set<String> acceptImg;
	/**
	 * 可以上传的文件后缀
	 */
	private Set<String> acceptFile;
	/**
	 * 编辑器浏览服务器的根路径，也是上传的根路径
	 */
	private String rootPath;

	private String pathPrefix;
	/**
	 * 是否生成文件名
	 */
	private boolean isGenName = true;
	/**
	 * 是否区分文件类型（用于编辑器浏览服务器时使用）
	 */
	private boolean hasType = true;
	/**
	 * 是否需要清理
	 */
	private boolean needClear = true;
	/**
	 * 是否允许浏览文件
	 */
	private boolean allowFileBrowsing = true;
	/**
	 * 是否允许上传文件
	 */
	private boolean allowUpload = true;
	/**
	 * 允许上传的大小。0不允许上传，-1不受限制
	 */
	private int allowSize = -1;
	/**
	 * 已上传大小
	 */
	private int uploadSize = 0;
	/**
	 * 默认的可上传图片后缀
	 */
	public static final String[] DEF_IMG_ACCEPT = { "jpg", "gif", "jpeg",
			"png", "bmp", };

	/**
	 * 默认的可上传文件后缀
	 */
	public static final String[] DEF_File_ACCEPT= { "jpg", "gif", "jpeg",
		"png", "bmp", "doc","xls","rar","zip","ppt","pdf","wmv",};
	
	public static void main(String[] args) {
		UploadRule rule = new UploadRule("", "", true);
		System.out.println(rule.getPathName("", "jpg", "img"));
	}

	public boolean isGenName() {
		return isGenName;
	}

	public void setGenName(boolean isGenName) {
		this.isGenName = isGenName;
	}

	public void setAcceptImg(Set<String> acceptImg) {
		this.acceptImg = acceptImg;
	}

	public void setAcceptFile(Set<String> acceptFile) {
		this.acceptFile = acceptFile;
	}

	public String getRootPath() {
		return rootPath;
	}

	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}

	public boolean isHasType() {
		return hasType;
	}

	public void setHasType(boolean hasType) {
		this.hasType = hasType;
	}

	public boolean isNeedClear() {
		return needClear;
	}

	public void setNeedClear(boolean needClear) {
		this.needClear = needClear;
	}

	public static class UploadFile implements java.io.Serializable {
		private static final long serialVersionUID = 1L;

		public UploadFile() {
		}

		public UploadFile(String origName, String fileName, String realPath,
				long size,String description) {
			this.origName = origName;
			this.fileName = fileName;
			this.realPath = realPath;
			this.size = size;
			this.description=description;
		}

		public String getRelPath(String pathRoot) {
			String real = getRealPath();
			real = StringUtils.replace(real, pathRoot, "");
			real = StringUtils.replace(real, File.separator, "/");
			return real;
		}

		private String origName;
		private String fileName;
		private String realPath;
		private long size;
		private String description;

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getOrigName() {
			return origName;
		}

		public void setOrigName(String origName) {
			this.origName = origName;
		}

		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		public String getRealPath() {
			return realPath;
		}

		public void setRealPath(String realPath) {
			this.realPath = realPath;
		}

		public long getSize() {
			return size;
		}

		public void setSize(long size) {
			this.size = size;
		}
	}

	public String getPathPrefix() {
		return pathPrefix;
	}

	public void setPathPrefix(String pathPrefix) {
		this.pathPrefix = pathPrefix;
	}

	public boolean isAllowFileBrowsing() {
		return allowFileBrowsing;
	}

	public void setAllowFileBrowsing(boolean allowFileBrowsing) {
		this.allowFileBrowsing = allowFileBrowsing;
	}

	public int getUploadSize() {
		return uploadSize;
	}

	public void setUploadSize(int uploadSize) {
		this.uploadSize = uploadSize;
	}

	public boolean isAllowUpload() {
		return allowUpload;
	}

	public void setAllowUpload(boolean allowUpload) {
		this.allowUpload = allowUpload;
	}

	public int getAllowSize() {
		return allowSize;
	}

	public void setAllowSize(int allowSize) {
		this.allowSize = allowSize;
	}
}
