package com.gwideal.fckeditor;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.fckeditor.connector.Messages;
import net.fckeditor.handlers.CommandHandler;
import net.fckeditor.handlers.ConnectorHandler;
import net.fckeditor.handlers.ExtensionsHandler;
import net.fckeditor.handlers.RequestCycleHandler;
import net.fckeditor.handlers.ResourceTypeHandler;
import net.fckeditor.response.UploadResponse;
import net.fckeditor.response.XmlResponse;
import net.fckeditor.tool.Utils;
import net.fckeditor.tool.UtilsFile;
import net.fckeditor.tool.UtilsResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet to upload and browse files.<br />
 * 
 * This servlet accepts 4 commands which interact with the server-side
 * filesystem.<br />
 * The allowed commands are:
 * <ul>
 * <li><code>GetFolders</code>: Retrieves a list of folders in the current
 * folder</li>
 * <li><code>GetFoldersAndFiles</code>: Retrives a list of files and folders in
 * the current folder</li>
 * <li><code>CreateFolder</code>: Creates a new folder in the current folder</li>
 * <li><code>FileUpload</code>: Stores an uploaded file into the current folder.
 * (must be sent with POST)</li>
 * </ul>
 * 
 * @version $Id: ConnectorServlet.java,v 1.5 2009/03/13 06:51:22 liufang Exp $
 */
public class ConnectorServlet extends HttpServlet {

	private static final long serialVersionUID = -5742008970929377161L;
	private static final Logger logger = LoggerFactory
			.getLogger(ConnectorServlet.class);
	private static final String QUICK_UPLOAD = "QuickUpload";

	/**
	 * Initialize the servlet: <code>mkdir</code> &lt;DefaultUserFilesPath&gt;
	 */
	public void init() throws ServletException, IllegalArgumentException {
		String realDefaultUserFilesPath = getServletContext().getRealPath(
				ConnectorHandler.getDefaultUserFilesPath());

		File defaultUserFilesDir = new File(realDefaultUserFilesPath);
		UtilsFile.checkDirAndCreate(defaultUserFilesDir);

		logger.info("ConnectorServlet successfully initialized!");
	}

	/**
	 * Manage the <code>GET</code> requests (<code>GetFolders</code>,
	 * <code>GetFoldersAndFiles</code>, <code>CreateFolder</code>).<br/>
	 * 
	 * The servlet accepts commands sent in the following format:<br/>
	 * <code>connector?Command=&lt;CommandName&gt;&Type=&lt;ResourceType&gt;&CurrentFolder=&lt;FolderPath&gt;</code>
	 * <p>
	 * It executes the commands and then returns the result to the client in XML
	 * format.
	 * </p>
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.debug("Entering ConnectorServlet#doGet");

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();

		String commandStr = request.getParameter("Command");
		String typeStr = request.getParameter("Type");
		String currentFolderStr = request.getParameter("CurrentFolder");
		String uploadRuleId = request.getParameter("uploadRuleId");

		logger.debug("Parameter Command: {}", commandStr);
		logger.debug("Parameter Type: {}", typeStr);
		logger.debug("Parameter CurrentFolder: {}", currentFolderStr);
		// 根据上传规则处理浏览路径
		UploadRule rule = (UploadRule) request.getSession().getAttribute(
				UploadRule.KEY + uploadRuleId);
		XmlResponse xr;
		if (rule == null) {
			xr = new XmlResponse(XmlResponse.EN_ERROR, "没有找到上传规则，不允许操作！");
		} else if (!RequestCycleHandler.isEnabledForFileBrowsing(request))
			xr = new XmlResponse(XmlResponse.EN_ERROR,
					Messages.NOT_AUTHORIZED_FOR_BROWSING);
		else if (!CommandHandler.isValidForGet(commandStr))
			xr = new XmlResponse(XmlResponse.EN_ERROR, Messages.INVALID_COMMAND);
		else if (typeStr != null && !ResourceTypeHandler.isValid(typeStr))
			xr = new XmlResponse(XmlResponse.EN_ERROR, Messages.INVALID_TYPE);
		else if (!UtilsFile.isValidPath(currentFolderStr))
			xr = new XmlResponse(XmlResponse.EN_ERROR,
					Messages.INVALID_CURRENT_FOLDER);
		else {
			CommandHandler command = CommandHandler.getCommand(commandStr);
			ResourceTypeHandler resourceType = ResourceTypeHandler
					.getDefaultResourceType(typeStr);
			// 使用上传规则的浏览路径。浏览路径一般在制作模板时上传图片使用。

			String rootPath = rule.getRootPath() + rule.getPathPrefix();
			String typePath;
			if (rule.isHasType()) {
				typePath = rootPath + resourceType.getPath();
			} else {
				typePath = rootPath;
			}
			String typeDirPath = getServletContext().getRealPath(typePath);

			File typeDir = new File(typeDirPath);
			UtilsFile.checkDirAndCreate(typeDir);

			File currentDir = new File(typeDir, currentFolderStr);

			if (!currentDir.exists())
				xr = new XmlResponse(XmlResponse.EN_INVALID_FOLDER_NAME);
			else {
				String constructedUrl = UtilsResponse.constructResponseUrl(
						request, resourceType, currentFolderStr, true,
						ConnectorHandler.isFullUrl());
				if (!rule.isHasType()) {
					constructedUrl = StringUtils.replaceOnce(constructedUrl,
							resourceType.getPath(), "");
				}
				xr = new XmlResponse(command, resourceType, currentFolderStr,
						constructedUrl);

				if (command.equals(CommandHandler.GET_FOLDERS))
					xr.setFolders(currentDir);
				else if (command.equals(CommandHandler.GET_FOLDERS_AND_FILES))
					xr.setFoldersAndFiles(currentDir);
				else if (command.equals(CommandHandler.CREATE_FOLDER)) {
					String newFolderStr = UtilsFile.sanitizeFolderName(request
							.getParameter("NewFolderName"));
					logger.debug("Parameter NewFolderName: {}", newFolderStr);

					File newFolder = new File(currentDir, newFolderStr);
					int errorNumber = XmlResponse.EN_UKNOWN;

					if (newFolder.exists())
						errorNumber = XmlResponse.EN_ALREADY_EXISTS;
					else {
						try {
							errorNumber = (newFolder.mkdir()) ? XmlResponse.EN_OK
									: XmlResponse.EN_INVALID_FOLDER_NAME;
						} catch (SecurityException e) {
							errorNumber = XmlResponse.EN_SECURITY_ERROR;
						}
					}
					xr.setError(errorNumber);
				}
			}
		}

		out.print(xr);
		out.flush();
		out.close();
		logger.debug("Exiting ConnectorServlet#doGet");
	}

	/**
	 * Manage the <code>POST</code> requests (<code>FileUpload</code>).<br />
	 * 
	 * The servlet accepts commands sent in the following format:<br />
	 * <code>connector?Command=&lt;FileUpload&gt;&Type=&lt;ResourceType&gt;&CurrentFolder=&lt;FolderPath&gt;</code>
	 * with the file in the <code>POST</code> body.<br />
	 * <br>
	 * It stores an uploaded file (renames a file if another exists with the
	 * same name) and then returns the JavaScript callback.
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.debug("Entering Connector#doPost");
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();

		String commandStr = request.getParameter("Command");
		String typeStr = request.getParameter("Type");
		String currentFolderStr = request.getParameter("CurrentFolder");

		logger.debug("Parameter Command: {}", commandStr);
		logger.debug("Parameter Type: {}", typeStr);
		logger.debug("Parameter CurrentFolder: {}", currentFolderStr);

		UploadResponse ur;

		// if this is a QuickUpload request, 'commandStr' and 'currentFolderStr'
		// are empty
		if (Utils.isEmpty(commandStr) && Utils.isEmpty(currentFolderStr)) {
			commandStr = QUICK_UPLOAD;
			currentFolderStr = "/";
		}
		// 根据上传规则处理浏览路径
		UploadRule rule = (UploadRule) request.getSession().getAttribute(
				UploadRule.KEY );
		if (rule == null) {
			ur = new UploadResponse(new Object[]{UploadResponse.SC_ERROR, null, null,
					"没有找到上传规则，不允许操作！"});
		} else if (QUICK_UPLOAD.equals(commandStr) && !rule.isGenName()) {
			// 不生成文件名不能直接上传
			ur = new UploadResponse(new Object[]{UploadResponse.SC_ERROR, null, null,
					"请在浏览服务器中上传文件！"});
		}  else if (!CommandHandler.isValidForPost(commandStr)) {
			ur = new UploadResponse(new Object[]{UploadResponse.SC_ERROR, null, null,
					Messages.INVALID_COMMAND});
		} else if (typeStr != null && !ResourceTypeHandler.isValid(typeStr)) {
			ur = new UploadResponse(new Object[]{UploadResponse.SC_ERROR, null, null,
					Messages.INVALID_TYPE});
		} else if (!UtilsFile.isValidPath(currentFolderStr)) {
			ur = UploadResponse.UR_INVALID_CURRENT_FOLDER;
		} else {
			ResourceTypeHandler resourceType = ResourceTypeHandler
					.getDefaultResourceType(typeStr);
			// 使用上传规则的浏览路径，即上传根路径
			String rootPath = rule.getRootPath() + rule.getPathPrefix();
			String typePath;
			if (rule.isHasType()) {
				typePath = rootPath + resourceType.getPath();
			} else {
				typePath = rootPath;
			}
			String typeDirPath = getServletContext().getRealPath(typePath);

			File typeDir = new File(typeDirPath);
			UtilsFile.checkDirAndCreate(typeDir);
			// 路径有根路径和当前路径。上传有两种方式：浏览服务器上传和快速上传
			// 如果浏览服务器上传则自己选择文件夹，否则自动生成文件夹。
			if (QUICK_UPLOAD.equals(commandStr)) {
				currentFolderStr = UploadRule.genFilePath();
			}
			File currentDir = new File(typeDir, currentFolderStr);

			if (!currentDir.exists()) {
				// ur = UploadResponse.UR_INVALID_CURRENT_FOLDER;
				// 不存在则创建
				currentDir.mkdirs();
			}
			String newFilename = null;
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);

			try {

				List<FileItem> items = upload.parseRequest(request);

				// We upload only one file at the same time
				FileItem uplFile = items.get(0);
				String rawName = UtilsFile.sanitizeFileName(uplFile.getName());
				String filename = FilenameUtils.getName(rawName);
				String origName = filename;
				String baseName = FilenameUtils.removeExtension(filename);
				String extension = FilenameUtils.getExtension(filename);
				// 判断是否创建文件名，如果上传文件包含中文字，也自动创建文件名
				if (rule.isGenName() || StrUtils.hasCn(filename)) {
					baseName = UploadRule.genFileName();
					filename = baseName.concat(".").concat(extension);
				}
				long currSize = uplFile.getSize() + rule.getUploadSize();
				if (!ExtensionsHandler.isAllowed(resourceType, extension)) {
					ur = new UploadResponse(new Object[]{UploadResponse.SC_INVALID_EXTENSION});
				} else if (rule.getAllowSize() != -1
						&& currSize > rule.getAllowSize()) {
					// 上传大小限制
					ur = new UploadResponse(new Object[]{UploadResponse.SC_ERROR, null,
							null, "您今日已经上传了" + rule.getUploadSize()
									+ "文件，不能上传更多的文件了！"});
				} else {

					// construct an unique file name
					File pathToSave = new File(currentDir, filename);
					int counter = 1;
					while (pathToSave.exists()) {
						newFilename = baseName.concat("(").concat(
								String.valueOf(counter)).concat(")")
								.concat(".").concat(extension);
						pathToSave = new File(currentDir, newFilename);
						counter++;
					}

					if (Utils.isEmpty(newFilename))
						ur = new UploadResponse(new Object[]{UploadResponse.SC_OK,
								UtilsResponse.constructResponseUrl(request,
										resourceType, currentFolderStr, true,
										ConnectorHandler.isFullUrl()).concat(
										filename)});
					else
						ur = new UploadResponse(new Object[]{UploadResponse.SC_RENAMED,
								UtilsResponse.constructResponseUrl(request,
										resourceType, currentFolderStr, true,
										ConnectorHandler.isFullUrl()).concat(
										newFilename), newFilename});

					// secure image check
					if (resourceType.equals(ResourceTypeHandler.IMAGE)
							&& ConnectorHandler.isSecureImageUploads()) {
						if (UtilsFile.isImage(uplFile.getInputStream()))
							uplFile.write(pathToSave);
						else {
							uplFile.delete();
							ur = new UploadResponse(
									new Object[]{UploadResponse.SC_INVALID_EXTENSION});
						}
					} else {
						uplFile.write(pathToSave);
					}
					// 保存上传的文件，以便清理
					rule.addUploadFile(origName, pathToSave.getName(),
							pathToSave.getAbsolutePath(), pathToSave.length(),"");
					long size = pathToSave.length() + rule.getUploadSize();
					rule.setUploadSize((int) size);
				}
			} catch (Exception e) {
				ur = new UploadResponse(new Object[]{UploadResponse.SC_SECURITY_ERROR});
			}

		}

		out.print(ur);
		out.flush();
		out.close();
		logger.debug("Exiting Connector#doPost");
	}
}
