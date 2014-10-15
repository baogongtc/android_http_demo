package android.xuanyou.http.engine;

import android.xuanyou.http.entity.HttpMethod;
import android.xuanyou.http.utils.HttpRequestUtil;

import com.alibaba.fastjson.JSON;
import com.loopj.android.http.RequestParams;

public abstract class DataCollectingTask extends BaseHttpTask {

	public DataCollectingTask(String URL) {
		super(URL);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 三十九.用户0下载1完成2安装3卸载
	 */
	public void reportUserBeHavior(String imei, String scid, String paid, String package_version, String package_version_code, String operateStatus, String ownerId, String marketVersion,
			String downloadType, String appPackage) {
		RequestParams params = new RequestParams();
		params.put("download.imei", imei);
		params.put("download.scid", scid);
		params.put("download.paid", paid);
		params.put("Download.package_version", package_version);
		params.put("Download.package_version_code", package_version_code);
		params.put("download.operateStatus", operateStatus);
		params.put("download.ownerId", ownerId);
		params.put("download.marketVersion", marketVersion);
		params.put("download.downloadType", downloadType);
		params.put("download.appPackage", appPackage);
		HttpRequestUtil.setTaskParamsAndRequest(DataCollectingTask.this, params, HttpMethod.POST);

	}

	/**
	 * 四十二.用户反馈接口
	 * 
	 * @param imei
	 * @param ownerId
	 * @param contents
	 * @param qq
	 * @param telphone
	 * @param email
	 */
	public void acquireUserFeedBack(String imei, String ownerId, String contents, String qq, String telphone, String email) {
		RequestParams params = new RequestParams();
		params.put("feedBack.imei", imei);
		params.put("feedBack.ownerId", ownerId);
		params.put("feedBack.contents", contents);
		params.put("feedBack.qq", qq);
		params.put("feedBack.telphone", telphone);
		params.put("feedBack.email", email);
		HttpRequestUtil.setTaskParamsAndRequest(DataCollectingTask.this, params, HttpMethod.POST);
	}

	/*
	 * 四十八、下拉推送成功记录 接口
	 */
	public void reportPushSuccess(String imei, String channelCode, String cpId) {
		RequestParams params = new RequestParams();
		params.put("imei", imei);
		params.put("channelCode", channelCode);
		params.put("cpId", cpId);
		HttpRequestUtil.setTaskParamsAndRequest(DataCollectingTask.this, params, HttpMethod.POST);
	}

	/**
	 * 十七、提交用户评价接口
	 * 
	 * @param baseId
	 * @param name
	 * @param userContent
	 * @param suid
	 * @param source
	 * @param mobileType
	 * @param comstar
	 */
	public void submitUserComment(String baseId, String name, String userContent, String suid, String source, String mobileType, String comstar) {

		RequestParams params = new RequestParams();
		params.put("comment.baseId", baseId);
		params.put("comment.name", name);
		params.put("comment.userContent", userContent);
		params.put("comment. suid", suid);
		params.put("comment.source", source);
		params.put("comment.mobileType", mobileType);
		params.put("comment.comStar", comstar);
		HttpRequestUtil.setTaskParamsAndRequest(DataCollectingTask.this, params, HttpMethod.POST);
	}

	/**
	 * 二十七、客户端推送安装成功回报 记录接口
	 * 
	 * @param jsonStr
	 */
	public void pushSucessReportBack(JSON jsonStr) {
		RequestParams params = new RequestParams();
		params.put("jsonStr", jsonStr);
		HttpRequestUtil.setTaskParamsAndRequest(DataCollectingTask.this, params, HttpMethod.POST);

	}

	/**
	 * 五十一 用户操作行为记录表
	 */
	public void userBehaviourRecord(String imei, String scid, String operation, String mac, String telphone, int type, String marketVersion) {
		RequestParams params = new RequestParams();
		params.put("imei", imei);
		params.put("scid", scid);
		params.put("operation", operation);
		params.put("mac", mac);
		params.put("telphone", telphone);
		params.put("type", type);
		params.put("marketVersion", marketVersion);
		HttpRequestUtil.setTaskParamsAndRequest(DataCollectingTask.this, params, HttpMethod.POST);
	}

	/**
	 * 五十四、地摊吧启动次数计算
	 * 
	 * @param imei
	 * @param scid
	 * @param operation
	 * @param mac
	 * @param telphone
	 * @param type
	 * @param marketVersion
	 */
	public void caculateStartCount(String imei, String scid, String operation, String mac, String telphone, int type, String marketVersion) {
		RequestParams params = new RequestParams();
		params.put("userBehavior.imei", imei);
		params.put("userBehavior.scid", scid);
		params.put("userBehavior.operation", operation);
		params.put("userBehavior.mac", mac);
		params.put("userBehavior.telphone", telphone);
		params.put("userBehavior.type", type);
		params.put("userBehavior.marketVersion", marketVersion);
		HttpRequestUtil.setTaskParamsAndRequest(DataCollectingTask.this, params, HttpMethod.POST);
	}

	/**
	 * 二十一、客户端用户信息补充信息接口
	 * 
	 * @param id
	 * @param email
	 * @param mobile
	 * @param sex
	 * @param birthday
	 * @param nationality
	 * @param province
	 * @param cities
	 */
	public void supplementUserInfo(String id, String email, String mobile, String sex, String birthday, String nationality, String province, String cities) {
		RequestParams params = new RequestParams();
		params.put("user.id", id);
		params.put("user.email", email);
		params.put("user.mobile", mobile);
		params.put("user.sex", sex);
		params.put("user.birthday", birthday);
		params.put("user.nationality", nationality);
		params.put("user.province", province);
		params.put("user.cities", cities);
		HttpRequestUtil.setTaskParamsAndRequest(DataCollectingTask.this, params, HttpMethod.POST);
	}
	

	/**
	 * 三十七. 客户端终端数据分析统计记录接口
	 */
	public void terminalDataStatistics(String imei,String deviceName,String resolution,String network,
			String operators,String marketVersion,String androidVersion,String channelCode){
		RequestParams params = new RequestParams();	
		params.put("imei", imei);
		params.put("deviceName", deviceName);
		params.put("resolution", resolution);
		params.put("network", network);
		params.put("operators", operators);
		params.put("marketVersion",marketVersion);
		params.put("androidVersion", androidVersion);
		params.put("channelCode", channelCode);
		HttpRequestUtil.setTaskParamsAndRequest(DataCollectingTask.this, params, HttpMethod.POST);
	}
	
	/**
	 * 四十.地摊吧启动激活接口
	 */
	public void startActivate(String imei, String scid, String marketVersion, String mac, String telphone) {
		RequestParams params = new RequestParams();
		params.put("clientActive.imei", imei);
		params.put("clientActive.scid", scid);
		params.put("clientActive.marketVersion", marketVersion);
		params.put("clientActive.mac", mac);
		params.put("clientActive.telphone", telphone);
		HttpRequestUtil.setTaskParamsAndRequest(DataCollectingTask.this, params, HttpMethod.POST);
	}
	/**
	 * 二十八、客户端版本自动检测升级 安装成功汇报 记录接口
	 * 
	 * @param jsonStr
	 */
	public void versionUpdateReportBack(JSON jsonStr) {
		RequestParams params = new RequestParams();
		params.put("jsonStr", jsonStr);
		HttpRequestUtil.setTaskParamsAndRequest(DataCollectingTask.this, params, HttpMethod.POST);
	}
	/**
	 * 
	 * 五十五、华硕游戏分类接口
	 * 
	 * @param id
	 */
	public void acquireBase(String id) {
		HttpRequestUtil.requestById(DataCollectingTask.this, id);
	}
	


}
