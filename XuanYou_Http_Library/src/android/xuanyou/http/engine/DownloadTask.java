package android.xuanyou.http.engine;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.message.BasicHeader;

import android.content.Context;
import android.util.Log;
import android.xuanyou.http.entity.HttpMethod;
import android.xuanyou.http.utils.HttpRequestUtil;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RangeFileAsyncHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;
import com.loopj.android.http.SyncHttpClient;

public abstract class DownloadTask extends BaseHttpTask {

	private final List<RequestHandle> requestHandles = new LinkedList<RequestHandle>();
	private static final String CONTENT_LENGTH = "Content-Length";
	private static final String ACCEPT_RANGES = "Accept-Ranges";
	private static final int CHUNK_SIZE = 10240;
	private static String LOG_TAG = "DownloadTask";
	private File file;
	private Context context;

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	/**
	 * @param fileStr
	 *            文件全路径名 eg: /local/tmp/123.apk should be called!
	 * @param prefix
	 * @param suffix
	 * @param directory
	 * @return
	 */
	public File createTempFile(String fileStr, String prefix, String suffix, File directory) {
		if (getFileSize(fileStr) > 1) {
			return this.file = new File(fileStr);
		}
		try {
			// Temporary file to host the URL's downloaded contents.
			return this.file = File.createTempFile(prefix, suffix, directory);
		} catch (IOException e) {
			Log.e(LOG_TAG, "Cannot create temporary file", e);
		}
		return null;
	}

	public DownloadTask(String URL) {
		super(URL);
	}

	public void onDestroy() {
		if (file != null) {
			file.delete();
			file = null;
		}
	}

	// 下载接口需要补充！
	// 九、应用下载接口

	public void acquireDownloadDetail(String id) {
		HttpRequestUtil.requestById(DownloadTask.this, id);
	}

	/**
	 * 十、 应用下载（升级）列表接口
	 * 
	 * @param appId
	 * @param id
	 */
	public void acquireDownloadList(String appId, String id) {
		RequestParams params = new RequestParams();
		params.put("appId", appId);
		params.put("id", id);
		HttpRequestUtil.setTaskParamsAndRequest(DownloadTask.this, params, HttpMethod.POST);
	}

	/**
	 * 六、用户下载历史接口
	 * 
	 * @param id
	 */
	public void acquireDownloadHistory(String id) {
		HttpRequestUtil.requestById(DownloadTask.this, id);
	}

	private List<Header> headers = new ArrayList<Header>();

	public List<Header> addDownloadHeader(Header header) {
		headers.add(header);
		return headers;
	}

	@Override
	public boolean isRequestHeadersAllowed() {

		return true;
	}

	/**
	 * 下载时调用方法
	 * 
	 * @param file
	 */
	public <T> void download() {
		if (client == null) {
			client = new AsyncHttpClient();
		}
		responseHandler = getResponseHandler();
		// client.post(getURL(), null, responseHandler);
		client.head(context, getURL(), null, null, responseHandler);
		System.out.println("url:" + getURL());
	}

	private long fileSize = -1;

	public long getFileSize(String fileStr) {
		return this.fileSize = new File(fileStr).length();
	}

	@Override
	public RequestHandle execute(AsyncHttpClient client, String URL, Header[] headers, HttpEntity entity, ResponseHandlerInterface responseHandler) {
		if (totalSize > 1) {
			// Send a GET query when we know the size of the remote file.
			return client.get(context, URL, headers, null, responseHandler);
		} else {
			// Send a HEAD query to know the size of the remote file.
			return client.head(context, URL, headers, null, responseHandler);
		}
	}

	public void sendNextRangeRequest() {
		if (file.length() < totalSize) {
			// File is still smaller than remote file; send a new request.
			onRunButtonPressed();
		}
	}

	private boolean hasCalculated = false;
	private static int bytesWritten, totalSize;

	public static int getBytesWritten() {
		return bytesWritten;
	}

	public static void setBytesWritten(int bytesWritten) {
		DownloadTask.bytesWritten = bytesWritten;
	}

	public static int getTotalSize() {
		return totalSize;
	}

	public static void setTotalSize(int totalSize) {
		DownloadTask.totalSize = totalSize;
	}

	private ProgressUpdate progressUpdate;
	private StateListener stateListener;

	public ProgressUpdate getProgressUpdate() {
		return progressUpdate;
	}

	public void setProgressUpdate(ProgressUpdate progressUpdate) {
		this.progressUpdate = progressUpdate;
	}

	public StateListener getStateListener() {
		return stateListener;
	}

	public void setStateListener(StateListener stateListener) {
		this.stateListener = stateListener;
	}

	public RangeFileAsyncHttpResponseHandler getRangeFileAsyncHttpResponseHandler() {
		return new RangeFileAsyncHttpResponseHandler(file) {

			@Override
			public void onStart() {
				Log.v(LOG_TAG, file.getName() + " _download start !");
			}

			@Override
			public void onPreProcessResponse(ResponseHandlerInterface instance, HttpResponse response) {
				Log.v(LOG_TAG, file.getName() + " _onPreProcessResponse !");
				if (!hasCalculated) {
					hasCalculated = true;
					if (fileSize < 1) {
						boolean supportsRange = false;
						// Cycle through the headers and look for the
						// Content-Length
						// header.
						for (Header header : response.getAllHeaders()) {
							String headerName = header.getName();
							if (CONTENT_LENGTH.equals(headerName)) {
								totalSize = Integer.parseInt(header.getValue());
								fileSize = Long.parseLong(header.getValue());
							} else if (ACCEPT_RANGES.equals(headerName)) {
								supportsRange = true;
							}
						}
						// Is the content-length known?
						if (!supportsRange || totalSize < 1) {
							// Toast.makeText(context,
							// "Unable to determine remote file's size, or\nremote server doesn't support ranges",
							// Toast.LENGTH_LONG).show();
						}
					} else {
						setStoped(false);
						updateRequestHeaders();
					}
					sendNextRangeRequest();
				}

			}

			@Override
			public void onPostProcessResponse(ResponseHandlerInterface instance, HttpResponse response) {
				Log.v(LOG_TAG, file.getName() + " _onPostProcessResponse !");
			}

			@Override
			public void onProgress(int bytesWritten, int totalSize) {
				Log.v(LOG_TAG, String.format("Progress %d from %d (%2.0f%%)", DownloadTask.bytesWritten, totalSize, (totalSize > 0) ? (DownloadTask.bytesWritten * 1.0 / totalSize) * 100 : -1));
				// TODO

				DownloadTask.bytesWritten = bytesWritten;
				currentLength = DownloadTask.bytesWritten;
				DownloadTask.totalSize = totalSize;
				if (progressUpdate != null)
					progressUpdate.updateProgress();
			}

			@Override
			public void onFinish() {
				Log.v(LOG_TAG, file.getName() + " _download finish !");
			}

			@Override
			public void onRetry(int retryNo) {
				Log.v(LOG_TAG, file.getName() + " _download retry !");
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable arg2, File arg3) {
				System.out.println("download onFailure");
				setStoped(true);
				onCancelButtonPressed();
				if (file.length() < DownloadTask.totalSize) {
					stateListener.onStateChanged(FAILURE);
				}
			}

			@Override
			public void onSuccess(int statusCode, Header[] headers, File arg3) {
			if (file.length() == DownloadTask.totalSize) {
				stateListener.onStateChanged(SUCCESS);
			}
				System.out.println("download onSuccess");
			}

			@Override
			public void onCancel() {
				onCancelButtonPressed();
			}

			private long currentLength;

			public void updateRequestHeaders() {
				if (file.exists() && file.canWrite())
					currentLength = file.length();
				if (currentLength > 1) {
					append = true;
					// Request the next portion of the file to be downloaded.
					headers.add(new BasicHeader("Range", "bytes=" + currentLength + "-" + totalSize));
				}
			}

		};
	};

	// public AsyncHttpResponseHandler getRangeFileAsyncHttpResponseHandler() {
	// return new RangeFileAsyncHttpResponseHandler(file) {
	//
	// @Override
	// public void onSuccess(int statusCode, Header[] headers, File file) {
	// debugHeaders(LOG_TAG, headers);
	// debugStatusCode(LOG_TAG, statusCode);
	//
	// if (fileSize < 1) {
	// boolean supportsRange = false;
	// // Cycle through the headers and look for the Content-Length
	// // header.
	// for (Header header : headers) {
	// String headerName = header.getName();
	// if (CONTENT_LENGTH.equals(headerName)) {
	// fileSize = Long.parseLong(header.getValue());
	// } else if (ACCEPT_RANGES.equals(headerName)) {
	// supportsRange = true;
	// }
	// }
	//
	// // Is the content length known?
	// if (!supportsRange || fileSize < 1) {
	// Toast.makeText(context,
	// "Unable to determine remote file's size, or\nremote server doesn't support ranges",
	// Toast.LENGTH_LONG).show();
	// }
	// }
	//
	// // If remote file size is known, request next portion.
	// if (fileSize > 0) {
	// debugFileResponse(file);
	// // Send a new request for the same resource.
	// sendNextRangeRequest();
	// }
	// }
	//
	// @Override
	// public void onFailure(int statusCode, Header[] headers, Throwable e, File
	// file) {
	// debugHeaders(LOG_TAG, headers);
	// debugStatusCode(LOG_TAG, statusCode);
	// debugThrowable(LOG_TAG, e);
	// debugFileResponse(file);
	// }
	//
	// @Override
	// public void updateRequestHeaders(HttpUriRequest uriRequest) {
	// // Call super so appending could work.
	// super.updateRequestHeaders(uriRequest);
	//
	// // Length of the downloaded content thus far.
	// long length = file.length();
	//
	// // Request the next portion of the file to be downloaded.
	// uriRequest.setHeader("Range", "bytes=" + length + "-" + (length +
	// CHUNK_SIZE - 1));
	// }
	//
	// void debugFileResponse(File file) {
	// debugResponse(LOG_TAG, "File size thus far: " + file.length() +
	// " bytes");
	// }
	// };
	// }

	public List<RequestHandle> getRequestHandles() {
		return requestHandles;
	}

	public void addRequestHandle(RequestHandle handle) {
		if (null != handle) {
			requestHandles.add(handle);
		}
	}

	public void onRunButtonPressed() {
		Log.v(LOG_TAG, "onRunButtonPressed");
		responseHandler = getResponseHandler();
		if (getRequestHeaders().length == 0) {
			addRequestHandle(execute(getSyncHttpClient(), getURL(), null, null, responseHandler));
		} else {
			addRequestHandle(execute(getSyncHttpClient(), getURL(), getRequestHeaders(), null, responseHandler));
		}
	}

	public void onCancelButtonPressed() {
		client.cancelRequests(context, true);
	}

	public Header[] getRequestHeaders() {
		return headers.toArray(new Header[headers.size()]);
	}

	private SyncHttpClient getSyncHttpClient() {

		return new SyncHttpClient();
	}

	public interface ProgressUpdate {

		/**
		 * 通知进度更新
		 */
		void updateProgress();
	}

	public interface StateListener {

		/**
		 * 下载状态变化通知
		 */
		void onStateChanged(int StateValue);
	}

	/**
	 * 下载状态
	 */

	public static final int WAITING = 0;
	public static final int START = 1;
	public static final int LOADING = 2;
	public static final int PAUSE = 3;
	public static final int FAILURE = 4;
	public static final int CANCEL = 5;
	public static final int SUCCESS = 6;
	public static final int INSTALL = 7;
	public static final int RUN = 8;

}
