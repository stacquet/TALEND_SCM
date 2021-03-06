// ============================================================================
//
// Copyright (c) 2006-2015, Talend Inc.
//
// This source code has been automatically generated by_Talend Open Studio for Data Integration
// / Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
// http://www.apache.org/licenses/LICENSE-2.0
// 
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package hism.dummy_talend_0_1;

import routines.Mathematical;
import routines.HISMString;
import routines.DataOperation;
import routines.Relational;
import routines.TalendDate;
import routines.TalendDataGenerator;
import routines.Numeric;
import routines.TalendString;
import routines.StringHandling;
import routines.system.*;
import routines.system.api.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Comparator;

//the import part of tJava_1
//import java.util.List;

@SuppressWarnings("unused")
/**
 * Job: DUMMY_TALEND Purpose: <br>
 * Description:  <br>
 * @author user@talend.com
 * @version 6.1.0.20151029_1337
 * @status 
 */
public class DUMMY_TALEND implements TalendJob {

	public final Object obj = new Object();

	// for transmiting parameters purpose
	private Object valueObject = null;

	public Object getValueObject() {
		return this.valueObject;
	}

	public void setValueObject(Object valueObject) {
		this.valueObject = valueObject;
	}

	private final static String defaultCharset = java.nio.charset.Charset
			.defaultCharset().name();

	private final static String utf8Charset = "UTF-8";

	// create and load default properties
	private java.util.Properties defaultProps = new java.util.Properties();

	// create application properties with default
	public class ContextProperties extends java.util.Properties {

		private static final long serialVersionUID = 1L;

		public ContextProperties(java.util.Properties properties) {
			super(properties);
		}

		public ContextProperties() {
			super();
		}

		public void synchronizeContext() {

		}

	}

	private ContextProperties context = new ContextProperties();

	public ContextProperties getContext() {
		return this.context;
	}

	private final String jobVersion = "0.1";
	private final String jobName = "DUMMY_TALEND";
	private final String projectName = "HISM";
	public Integer errorCode = null;
	private String currentComponent = "";

	private final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();
	private final static java.util.Map<String, Object> junitGlobalMap = new java.util.HashMap<String, Object>();

	private final java.util.Map<String, Long> start_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Long> end_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Boolean> ok_Hash = new java.util.HashMap<String, Boolean>();
	public final java.util.List<String[]> globalBuffer = new java.util.ArrayList<String[]>();

	// OSGi DataSource
	private final static String KEY_DB_DATASOURCES = "KEY_DB_DATASOURCES";

	public void setDataSources(
			java.util.Map<String, javax.sql.DataSource> dataSources) {
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		for (java.util.Map.Entry<String, javax.sql.DataSource> dataSourceEntry : dataSources
				.entrySet()) {
			talendDataSources.put(
					dataSourceEntry.getKey(),
					new routines.system.TalendDataSource(dataSourceEntry
							.getValue()));
		}
		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
	}

	LogCatcherUtils talendLogs_LOGS = new LogCatcherUtils();

	private final java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
	private final java.io.PrintStream errorMessagePS = new java.io.PrintStream(
			new java.io.BufferedOutputStream(baos));

	public String getExceptionStackTrace() {
		if ("failure".equals(this.getStatus())) {
			errorMessagePS.flush();
			return baos.toString();
		}
		return null;
	}

	private Exception exception;

	public Exception getException() {
		if ("failure".equals(this.getStatus())) {
			return this.exception;
		}
		return null;
	}

	private class TalendException extends Exception {

		private static final long serialVersionUID = 1L;

		private java.util.Map<String, Object> globalMap = null;
		private Exception e = null;
		private String currentComponent = null;
		private String virtualComponentName = null;

		public void setVirtualComponentName(String virtualComponentName) {
			this.virtualComponentName = virtualComponentName;
		}

		private TalendException(Exception e, String errorComponent,
				final java.util.Map<String, Object> globalMap) {
			this.currentComponent = errorComponent;
			this.globalMap = globalMap;
			this.e = e;
		}

		public Exception getException() {
			return this.e;
		}

		public String getCurrentComponent() {
			return this.currentComponent;
		}

		public String getExceptionCauseMessage(Exception e) {
			Throwable cause = e;
			String message = null;
			int i = 10;
			while (null != cause && 0 < i--) {
				message = cause.getMessage();
				if (null == message) {
					cause = cause.getCause();
				} else {
					break;
				}
			}
			if (null == message) {
				message = e.getClass().getName();
			}
			return message;
		}

		@Override
		public void printStackTrace() {
			if (!(e instanceof TalendException || e instanceof TDieException)) {
				if (virtualComponentName != null
						&& currentComponent.indexOf(virtualComponentName + "_") == 0) {
					globalMap.put(virtualComponentName + "_ERROR_MESSAGE",
							getExceptionCauseMessage(e));
				}
				globalMap.put(currentComponent + "_ERROR_MESSAGE",
						getExceptionCauseMessage(e));
				System.err
						.println("Exception in component " + currentComponent);
			}
			if (!(e instanceof TDieException)) {
				if (e instanceof TalendException) {
					e.printStackTrace();
				} else {
					e.printStackTrace();
					e.printStackTrace(errorMessagePS);
					DUMMY_TALEND.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass()
							.getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(DUMMY_TALEND.this, new Object[] { e,
									currentComponent, globalMap });
							break;
						}
					}

					if (!(e instanceof TDieException)) {
						talendLogs_LOGS.addMessage("Java Exception",
								currentComponent, 6, e.getClass().getName()
										+ ":" + e.getMessage(), 1);
						talendLogs_LOGSProcess(globalMap);
					}
				} catch (TalendException e) {
					// do nothing

				} catch (Exception e) {
					this.e.printStackTrace();
				}
			}
		}
	}

	public void tJava_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tJava_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void talendLogs_LOGS_error(Exception exception,
			String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		talendLogs_FILE_error(exception, errorComponent, globalMap);

	}

	public void talendLogs_FILE_error(Exception exception,
			String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		talendLogs_LOGS_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tJava_1_onSubJobError(Exception exception,
			String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread
				.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(),
				ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void talendLogs_LOGS_onSubJobError(Exception exception,
			String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread
				.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(),
				ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tJava_1Process(final java.util.Map<String, Object> globalMap)
			throws TalendException {
		globalMap.put("tJava_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {

			String currentMethodName = new java.lang.Exception()
					.getStackTrace()[0].getMethodName();
			boolean resumeIt = currentMethodName.equals(resumeEntryMethodName);
			if (resumeEntryMethodName == null || resumeIt || globalResumeTicket) {// start
																					// the
																					// resume
				globalResumeTicket = true;

				/**
				 * [tJava_1 begin ] start
				 */

				ok_Hash.put("tJava_1", false);
				start_Hash.put("tJava_1", System.currentTimeMillis());

				currentComponent = "tJava_1";

				int tos_count_tJava_1 = 0;

				System.out.println("DUMMY: Je viens de m'exécuter");

				/**
				 * [tJava_1 begin ] stop
				 */

				/**
				 * [tJava_1 main ] start
				 */

				currentComponent = "tJava_1";

				tos_count_tJava_1++;

				/**
				 * [tJava_1 main ] stop
				 */

				/**
				 * [tJava_1 end ] start
				 */

				currentComponent = "tJava_1";

				ok_Hash.put("tJava_1", true);
				end_Hash.put("tJava_1", System.currentTimeMillis());

				/**
				 * [tJava_1 end ] stop
				 */
			}// end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent,
					globalMap);

			throw te;
		} catch (java.lang.Error error) {

			throw error;
		} finally {

			try {

				/**
				 * [tJava_1 finally ] start
				 */

				currentComponent = "tJava_1";

				/**
				 * [tJava_1 finally ] stop
				 */
			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tJava_1_SUBPROCESS_STATE", 1);
	}

	public static class row_talendLogs_LOGSStruct implements
			routines.system.IPersistableRow<row_talendLogs_LOGSStruct> {
		final static byte[] commonByteArrayLock_HISM_DUMMY_TALEND = new byte[0];
		static byte[] commonByteArray_HISM_DUMMY_TALEND = new byte[0];

		public java.util.Date moment;

		public java.util.Date getMoment() {
			return this.moment;
		}

		public String pid;

		public String getPid() {
			return this.pid;
		}

		public String root_pid;

		public String getRoot_pid() {
			return this.root_pid;
		}

		public String father_pid;

		public String getFather_pid() {
			return this.father_pid;
		}

		public String project;

		public String getProject() {
			return this.project;
		}

		public String job;

		public String getJob() {
			return this.job;
		}

		public String context;

		public String getContext() {
			return this.context;
		}

		public Integer priority;

		public Integer getPriority() {
			return this.priority;
		}

		public String type;

		public String getType() {
			return this.type;
		}

		public String origin;

		public String getOrigin() {
			return this.origin;
		}

		public String message;

		public String getMessage() {
			return this.message;
		}

		public Integer code;

		public Integer getCode() {
			return this.code;
		}

		private java.util.Date readDate(ObjectInputStream dis)
				throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos)
				throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HISM_DUMMY_TALEND.length) {
					if (length < 1024
							&& commonByteArray_HISM_DUMMY_TALEND.length == 0) {
						commonByteArray_HISM_DUMMY_TALEND = new byte[1024];
					} else {
						commonByteArray_HISM_DUMMY_TALEND = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HISM_DUMMY_TALEND, 0, length);
				strReturn = new String(commonByteArray_HISM_DUMMY_TALEND, 0,
						length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos)
				throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos)
				throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HISM_DUMMY_TALEND) {

				try {

					int length = 0;

					this.moment = readDate(dis);

					this.pid = readString(dis);

					this.root_pid = readString(dis);

					this.father_pid = readString(dis);

					this.project = readString(dis);

					this.job = readString(dis);

					this.context = readString(dis);

					this.priority = readInteger(dis);

					this.type = readString(dis);

					this.origin = readString(dis);

					this.message = readString(dis);

					this.code = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// java.util.Date

				writeDate(this.moment, dos);

				// String

				writeString(this.pid, dos);

				// String

				writeString(this.root_pid, dos);

				// String

				writeString(this.father_pid, dos);

				// String

				writeString(this.project, dos);

				// String

				writeString(this.job, dos);

				// String

				writeString(this.context, dos);

				// Integer

				writeInteger(this.priority, dos);

				// String

				writeString(this.type, dos);

				// String

				writeString(this.origin, dos);

				// String

				writeString(this.message, dos);

				// Integer

				writeInteger(this.code, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("moment=" + String.valueOf(moment));
			sb.append(",pid=" + pid);
			sb.append(",root_pid=" + root_pid);
			sb.append(",father_pid=" + father_pid);
			sb.append(",project=" + project);
			sb.append(",job=" + job);
			sb.append(",context=" + context);
			sb.append(",priority=" + String.valueOf(priority));
			sb.append(",type=" + type);
			sb.append(",origin=" + origin);
			sb.append(",message=" + message);
			sb.append(",code=" + String.valueOf(code));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row_talendLogs_LOGSStruct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(),
						object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void talendLogs_LOGSProcess(
			final java.util.Map<String, Object> globalMap)
			throws TalendException {
		globalMap.put("talendLogs_LOGS_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;
		String currentVirtualComponent = null;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {

			String currentMethodName = new java.lang.Exception()
					.getStackTrace()[0].getMethodName();
			boolean resumeIt = currentMethodName.equals(resumeEntryMethodName);
			if (resumeEntryMethodName == null || resumeIt || globalResumeTicket) {// start
																					// the
																					// resume
				globalResumeTicket = true;

				row_talendLogs_LOGSStruct row_talendLogs_LOGS = new row_talendLogs_LOGSStruct();

				/**
				 * [talendLogs_FILE begin ] start
				 */

				ok_Hash.put("talendLogs_FILE", false);
				start_Hash.put("talendLogs_FILE", System.currentTimeMillis());

				currentVirtualComponent = "talendLogs_FILE";

				currentComponent = "talendLogs_FILE";

				int tos_count_talendLogs_FILE = 0;

				String fileName_talendLogs_FILE = "";
				fileName_talendLogs_FILE = (new java.io.File(
						"E:/Test0x00/EASYSOINS/logs" + "/" + "logs_file"
								+ TalendDate.getDate("CCYY-MM-DDhhmm") + ".txt"))
						.getAbsolutePath().replace("\\", "/");
				String fullName_talendLogs_FILE = null;
				String extension_talendLogs_FILE = null;
				String directory_talendLogs_FILE = null;
				if ((fileName_talendLogs_FILE.indexOf("/") != -1)) {
					if (fileName_talendLogs_FILE.lastIndexOf(".") < fileName_talendLogs_FILE
							.lastIndexOf("/")) {
						fullName_talendLogs_FILE = fileName_talendLogs_FILE;
						extension_talendLogs_FILE = "";
					} else {
						fullName_talendLogs_FILE = fileName_talendLogs_FILE
								.substring(0, fileName_talendLogs_FILE
										.lastIndexOf("."));
						extension_talendLogs_FILE = fileName_talendLogs_FILE
								.substring(fileName_talendLogs_FILE
										.lastIndexOf("."));
					}
					directory_talendLogs_FILE = fileName_talendLogs_FILE
							.substring(0,
									fileName_talendLogs_FILE.lastIndexOf("/"));
				} else {
					if (fileName_talendLogs_FILE.lastIndexOf(".") != -1) {
						fullName_talendLogs_FILE = fileName_talendLogs_FILE
								.substring(0, fileName_talendLogs_FILE
										.lastIndexOf("."));
						extension_talendLogs_FILE = fileName_talendLogs_FILE
								.substring(fileName_talendLogs_FILE
										.lastIndexOf("."));
					} else {
						fullName_talendLogs_FILE = fileName_talendLogs_FILE;
						extension_talendLogs_FILE = "";
					}
					directory_talendLogs_FILE = "";
				}
				boolean isFileGenerated_talendLogs_FILE = true;
				java.io.File filetalendLogs_FILE = new java.io.File(
						fileName_talendLogs_FILE);
				globalMap.put("talendLogs_FILE_FILE_NAME",
						fileName_talendLogs_FILE);
				if (filetalendLogs_FILE.exists()) {
					isFileGenerated_talendLogs_FILE = false;
				}
				int nb_line_talendLogs_FILE = 0;
				int splitEvery_talendLogs_FILE = 1000;
				int splitedFileNo_talendLogs_FILE = 0;
				int currentRow_talendLogs_FILE = 0;

				final String OUT_DELIM_talendLogs_FILE = /**
				 * Start field
				 * talendLogs_FILE:FIELDSEPARATOR
				 */
				";"/** End field talendLogs_FILE:FIELDSEPARATOR */
				;

				final String OUT_DELIM_ROWSEP_talendLogs_FILE = /**
				 * Start field
				 * talendLogs_FILE:ROWSEPARATOR
				 */
				"\n"/** End field talendLogs_FILE:ROWSEPARATOR */
				;

				// create directory only if not exists
				if (directory_talendLogs_FILE != null
						&& directory_talendLogs_FILE.trim().length() != 0) {
					java.io.File dir_talendLogs_FILE = new java.io.File(
							directory_talendLogs_FILE);
					if (!dir_talendLogs_FILE.exists()) {
						dir_talendLogs_FILE.mkdirs();
					}
				}

				// routines.system.Row
				java.io.Writer outtalendLogs_FILE = null;

				outtalendLogs_FILE = new java.io.BufferedWriter(
						new java.io.OutputStreamWriter(
								new java.io.FileOutputStream(
										fileName_talendLogs_FILE, true),
								"ISO-8859-15"));

				resourceMap.put("out_talendLogs_FILE", outtalendLogs_FILE);
				resourceMap.put("nb_line_talendLogs_FILE",
						nb_line_talendLogs_FILE);

				/**
				 * [talendLogs_FILE begin ] stop
				 */

				/**
				 * [talendLogs_LOGS begin ] start
				 */

				ok_Hash.put("talendLogs_LOGS", false);
				start_Hash.put("talendLogs_LOGS", System.currentTimeMillis());

				currentVirtualComponent = "talendLogs_LOGS";

				currentComponent = "talendLogs_LOGS";

				int tos_count_talendLogs_LOGS = 0;

				for (LogCatcherUtils.LogCatcherMessage lcm : talendLogs_LOGS
						.getMessages()) {
					row_talendLogs_LOGS.type = lcm.getType();
					row_talendLogs_LOGS.origin = (lcm.getOrigin() == null
							|| lcm.getOrigin().length() < 1 ? null : lcm
							.getOrigin());
					row_talendLogs_LOGS.priority = lcm.getPriority();
					row_talendLogs_LOGS.message = lcm.getMessage();
					row_talendLogs_LOGS.code = lcm.getCode();

					row_talendLogs_LOGS.moment = java.util.Calendar
							.getInstance().getTime();

					row_talendLogs_LOGS.pid = pid;
					row_talendLogs_LOGS.root_pid = rootPid;
					row_talendLogs_LOGS.father_pid = fatherPid;

					row_talendLogs_LOGS.project = projectName;
					row_talendLogs_LOGS.job = jobName;
					row_talendLogs_LOGS.context = contextStr;

					/**
					 * [talendLogs_LOGS begin ] stop
					 */

					/**
					 * [talendLogs_LOGS main ] start
					 */

					currentVirtualComponent = "talendLogs_LOGS";

					currentComponent = "talendLogs_LOGS";

					tos_count_talendLogs_LOGS++;

					/**
					 * [talendLogs_LOGS main ] stop
					 */

					/**
					 * [talendLogs_FILE main ] start
					 */

					currentVirtualComponent = "talendLogs_FILE";

					currentComponent = "talendLogs_FILE";

					StringBuilder sb_talendLogs_FILE = new StringBuilder();
					if (row_talendLogs_LOGS.moment != null) {
						sb_talendLogs_FILE.append(FormatterUtils.format_Date(
								row_talendLogs_LOGS.moment,
								"yyyy-MM-dd HH:mm:ss"));
					}
					sb_talendLogs_FILE.append(OUT_DELIM_talendLogs_FILE);
					if (row_talendLogs_LOGS.pid != null) {
						sb_talendLogs_FILE.append(row_talendLogs_LOGS.pid);
					}
					sb_talendLogs_FILE.append(OUT_DELIM_talendLogs_FILE);
					if (row_talendLogs_LOGS.root_pid != null) {
						sb_talendLogs_FILE.append(row_talendLogs_LOGS.root_pid);
					}
					sb_talendLogs_FILE.append(OUT_DELIM_talendLogs_FILE);
					if (row_talendLogs_LOGS.father_pid != null) {
						sb_talendLogs_FILE
								.append(row_talendLogs_LOGS.father_pid);
					}
					sb_talendLogs_FILE.append(OUT_DELIM_talendLogs_FILE);
					if (row_talendLogs_LOGS.project != null) {
						sb_talendLogs_FILE.append(row_talendLogs_LOGS.project);
					}
					sb_talendLogs_FILE.append(OUT_DELIM_talendLogs_FILE);
					if (row_talendLogs_LOGS.job != null) {
						sb_talendLogs_FILE.append(row_talendLogs_LOGS.job);
					}
					sb_talendLogs_FILE.append(OUT_DELIM_talendLogs_FILE);
					if (row_talendLogs_LOGS.context != null) {
						sb_talendLogs_FILE.append(row_talendLogs_LOGS.context);
					}
					sb_talendLogs_FILE.append(OUT_DELIM_talendLogs_FILE);
					if (row_talendLogs_LOGS.priority != null) {
						sb_talendLogs_FILE.append(row_talendLogs_LOGS.priority);
					}
					sb_talendLogs_FILE.append(OUT_DELIM_talendLogs_FILE);
					if (row_talendLogs_LOGS.type != null) {
						sb_talendLogs_FILE.append(row_talendLogs_LOGS.type);
					}
					sb_talendLogs_FILE.append(OUT_DELIM_talendLogs_FILE);
					if (row_talendLogs_LOGS.origin != null) {
						sb_talendLogs_FILE.append(row_talendLogs_LOGS.origin);
					}
					sb_talendLogs_FILE.append(OUT_DELIM_talendLogs_FILE);
					if (row_talendLogs_LOGS.message != null) {
						sb_talendLogs_FILE.append(row_talendLogs_LOGS.message);
					}
					sb_talendLogs_FILE.append(OUT_DELIM_talendLogs_FILE);
					if (row_talendLogs_LOGS.code != null) {
						sb_talendLogs_FILE.append(row_talendLogs_LOGS.code);
					}
					sb_talendLogs_FILE.append(OUT_DELIM_ROWSEP_talendLogs_FILE);

					nb_line_talendLogs_FILE++;
					resourceMap.put("nb_line_talendLogs_FILE",
							nb_line_talendLogs_FILE);

					outtalendLogs_FILE.write(sb_talendLogs_FILE.toString());

					tos_count_talendLogs_FILE++;

					/**
					 * [talendLogs_FILE main ] stop
					 */

					/**
					 * [talendLogs_LOGS end ] start
					 */

					currentVirtualComponent = "talendLogs_LOGS";

					currentComponent = "talendLogs_LOGS";

				}

				ok_Hash.put("talendLogs_LOGS", true);
				end_Hash.put("talendLogs_LOGS", System.currentTimeMillis());

				/**
				 * [talendLogs_LOGS end ] stop
				 */

				/**
				 * [talendLogs_FILE end ] start
				 */

				currentVirtualComponent = "talendLogs_FILE";

				currentComponent = "talendLogs_FILE";

				if (outtalendLogs_FILE != null) {
					outtalendLogs_FILE.flush();
					outtalendLogs_FILE.close();
				}

				globalMap.put("talendLogs_FILE_NB_LINE",
						nb_line_talendLogs_FILE);
				globalMap.put("talendLogs_FILE_FILE_NAME",
						fileName_talendLogs_FILE);

				resourceMap.put("finish_talendLogs_FILE", true);

				ok_Hash.put("talendLogs_FILE", true);
				end_Hash.put("talendLogs_FILE", System.currentTimeMillis());

				/**
				 * [talendLogs_FILE end ] stop
				 */

			}// end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent,
					globalMap);

			te.setVirtualComponentName(currentVirtualComponent);

			throw te;
		} catch (java.lang.Error error) {

			throw error;
		} finally {

			try {

				/**
				 * [talendLogs_LOGS finally ] start
				 */

				currentVirtualComponent = "talendLogs_LOGS";

				currentComponent = "talendLogs_LOGS";

				/**
				 * [talendLogs_LOGS finally ] stop
				 */

				/**
				 * [talendLogs_FILE finally ] start
				 */

				currentVirtualComponent = "talendLogs_FILE";

				currentComponent = "talendLogs_FILE";

				if (resourceMap.get("finish_talendLogs_FILE") == null) {

					java.io.Writer outtalendLogs_FILE = (java.io.Writer) resourceMap
							.get("out_talendLogs_FILE");
					if (outtalendLogs_FILE != null) {
						outtalendLogs_FILE.flush();
						outtalendLogs_FILE.close();
					}

				}

				/**
				 * [talendLogs_FILE finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("talendLogs_LOGS_SUBPROCESS_STATE", 1);
	}

	public String resuming_logs_dir_path = null;
	public String resuming_checkpoint_path = null;
	public String parent_part_launcher = null;
	private String resumeEntryMethodName = null;
	private boolean globalResumeTicket = false;

	public boolean watch = false;
	// portStats is null, it means don't execute the statistics
	public Integer portStats = null;
	public int portTraces = 4334;
	public String clientHost;
	public String defaultClientHost = "localhost";
	public String contextStr = "Default";
	public boolean isDefaultContext = true;
	public String pid = "0";
	public String rootPid = null;
	public String fatherPid = null;
	public String fatherNode = null;
	public long startTime = 0;
	public boolean isChildJob = false;
	public String log4jLevel = "";

	private boolean execStat = true;

	private ThreadLocal<java.util.Map<String, String>> threadLocal = new ThreadLocal<java.util.Map<String, String>>() {
		protected java.util.Map<String, String> initialValue() {
			java.util.Map<String, String> threadRunResultMap = new java.util.HashMap<String, String>();
			threadRunResultMap.put("errorCode", null);
			threadRunResultMap.put("status", "");
			return threadRunResultMap;
		};
	};

	private java.util.Properties context_param = new java.util.Properties();
	public java.util.Map<String, Object> parentContextMap = new java.util.HashMap<String, Object>();

	public String status = "";

	public static void main(String[] args) {
		final DUMMY_TALEND DUMMY_TALENDClass = new DUMMY_TALEND();

		int exitCode = DUMMY_TALENDClass.runJobInTOS(args);

		System.exit(exitCode);
	}

	public String[][] runJob(String[] args) {

		int exitCode = runJobInTOS(args);
		String[][] bufferValue = new String[][] { { Integer.toString(exitCode) } };

		return bufferValue;
	}

	public boolean hastBufferOutputComponent() {
		boolean hastBufferOutput = false;

		return hastBufferOutput;
	}

	public int runJobInTOS(String[] args) {
		// reset status
		status = "";

		String lastStr = "";
		for (String arg : args) {
			if (arg.equalsIgnoreCase("--context_param")) {
				lastStr = arg;
			} else if (lastStr.equals("")) {
				evalParam(arg);
			} else {
				evalParam(lastStr + " " + arg);
				lastStr = "";
			}
		}

		if (clientHost == null) {
			clientHost = defaultClientHost;
		}

		if (pid == null || "0".equals(pid)) {
			pid = TalendString.getAsciiRandomString(6);
		}

		if (rootPid == null) {
			rootPid = pid;
		}
		if (fatherPid == null) {
			fatherPid = pid;
		} else {
			isChildJob = true;
		}

		try {
			// call job/subjob with an existing context, like:
			// --context=production. if without this parameter, there will use
			// the default context instead.
			java.io.InputStream inContext = DUMMY_TALEND.class.getClassLoader()
					.getResourceAsStream(
							"hism/dummy_talend_0_1/contexts/" + contextStr
									+ ".properties");
			if (isDefaultContext && inContext == null) {

			} else {
				if (inContext != null) {
					// defaultProps is in order to keep the original context
					// value
					defaultProps.load(inContext);
					inContext.close();
					context = new ContextProperties(defaultProps);
				} else {
					// print info and job continue to run, for case:
					// context_param is not empty.
					System.err.println("Could not find the context "
							+ contextStr);
				}
			}

			if (!context_param.isEmpty()) {
				context.putAll(context_param);
			}
		} catch (java.io.IOException ie) {
			System.err.println("Could not load context " + contextStr);
			ie.printStackTrace();
		}

		// get context value from parent directly
		if (parentContextMap != null && !parentContextMap.isEmpty()) {
		}

		// Resume: init the resumeUtil
		resumeEntryMethodName = ResumeUtil
				.getResumeEntryMethodName(resuming_checkpoint_path);
		resumeUtil = new ResumeUtil(resuming_logs_dir_path, isChildJob, rootPid);
		resumeUtil.initCommonInfo(pid, rootPid, fatherPid, projectName,
				jobName, contextStr, jobVersion);

		List<String> parametersToEncrypt = new java.util.ArrayList<String>();
		// Resume: jobStart
		resumeUtil.addLog("JOB_STARTED", "JOB:" + jobName,
				parent_part_launcher, Thread.currentThread().getId() + "", "",
				"", "", "",
				resumeUtil.convertToJsonText(context, parametersToEncrypt));

		java.util.concurrent.ConcurrentHashMap<Object, Object> concurrentHashMap = new java.util.concurrent.ConcurrentHashMap<Object, Object>();
		globalMap.put("concurrentHashMap", concurrentHashMap);

		long startUsedMemory = Runtime.getRuntime().totalMemory()
				- Runtime.getRuntime().freeMemory();
		long endUsedMemory = 0;
		long end = 0;

		startTime = System.currentTimeMillis();

		this.globalResumeTicket = true;// to run tPreJob

		this.globalResumeTicket = false;// to run others jobs

		try {
			errorCode = null;
			tJava_1Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tJava_1) {
			globalMap.put("tJava_1_SUBPROCESS_STATE", -1);

			e_tJava_1.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory()
				- Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println((endUsedMemory - startUsedMemory)
					+ " bytes memory increase when running : DUMMY_TALEND");
		}

		int returnCode = 0;
		if (errorCode == null) {
			returnCode = status != null && status.equals("failure") ? 1 : 0;
		} else {
			returnCode = errorCode.intValue();
		}
		resumeUtil.addLog("JOB_ENDED", "JOB:" + jobName, parent_part_launcher,
				Thread.currentThread().getId() + "", "", "" + returnCode, "",
				"", "");

		return returnCode;

	}

	// only for OSGi env
	public void destroy() {

	}

	private java.util.Map<String, Object> getSharedConnections4REST() {
		java.util.Map<String, Object> connections = new java.util.HashMap<String, Object>();

		return connections;
	}

	private void evalParam(String arg) {
		if (arg.startsWith("--resuming_logs_dir_path")) {
			resuming_logs_dir_path = arg.substring(25);
		} else if (arg.startsWith("--resuming_checkpoint_path")) {
			resuming_checkpoint_path = arg.substring(27);
		} else if (arg.startsWith("--parent_part_launcher")) {
			parent_part_launcher = arg.substring(23);
		} else if (arg.startsWith("--watch")) {
			watch = true;
		} else if (arg.startsWith("--stat_port=")) {
			String portStatsStr = arg.substring(12);
			if (portStatsStr != null && !portStatsStr.equals("null")) {
				portStats = Integer.parseInt(portStatsStr);
			}
		} else if (arg.startsWith("--trace_port=")) {
			portTraces = Integer.parseInt(arg.substring(13));
		} else if (arg.startsWith("--client_host=")) {
			clientHost = arg.substring(14);
		} else if (arg.startsWith("--context=")) {
			contextStr = arg.substring(10);
			isDefaultContext = false;
		} else if (arg.startsWith("--father_pid=")) {
			fatherPid = arg.substring(13);
		} else if (arg.startsWith("--root_pid=")) {
			rootPid = arg.substring(11);
		} else if (arg.startsWith("--father_node=")) {
			fatherNode = arg.substring(14);
		} else if (arg.startsWith("--pid=")) {
			pid = arg.substring(6);
		} else if (arg.startsWith("--context_param")) {
			String keyValue = arg.substring(16);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.put(keyValue.substring(0, index),
							replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.put(keyValue.substring(0, index),
							keyValue.substring(index + 1));
				}
			}
		} else if (arg.startsWith("--log4jLevel=")) {
			log4jLevel = arg.substring(13);
		}

	}

	private final String[][] escapeChars = { { "\\\\", "\\" }, { "\\n", "\n" },
			{ "\\'", "\'" }, { "\\r", "\r" }, { "\\f", "\f" }, { "\\b", "\b" },
			{ "\\t", "\t" } };

	private String replaceEscapeChars(String keyValue) {

		if (keyValue == null || ("").equals(keyValue.trim())) {
			return keyValue;
		}

		StringBuilder result = new StringBuilder();
		int currIndex = 0;
		while (currIndex < keyValue.length()) {
			int index = -1;
			// judege if the left string includes escape chars
			for (String[] strArray : escapeChars) {
				index = keyValue.indexOf(strArray[0], currIndex);
				if (index >= 0) {

					result.append(keyValue.substring(currIndex,
							index + strArray[0].length()).replace(strArray[0],
							strArray[1]));
					currIndex = index + strArray[0].length();
					break;
				}
			}
			// if the left string doesn't include escape chars, append the left
			// into the result
			if (index < 0) {
				result.append(keyValue.substring(currIndex));
				currIndex = currIndex + keyValue.length();
			}
		}

		return result.toString();
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getStatus() {
		return status;
	}

	ResumeUtil resumeUtil = null;
}
/************************************************************************************************
 * 41074 characters generated by Talend Open Studio for Data Integration on the
 * 18 décembre 2015 16:22:20 CET
 ************************************************************************************************/
