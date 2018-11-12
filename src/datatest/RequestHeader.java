package datatest;
/**
 * header头dto
 * @author chengang
 *
 */
public class RequestHeader {

	/**
	 * 用户ID
	 */
	private long userid;

	/**
	 * 手机号码
	 */
	private String mobile;

	/**
	 * 当前定位的城市百度code
	 */
	private String cityCode;

	/**
	 * 当前定位的城市名称，可能为空
	 */
	private String cityName;

	/**
	 * 手机设备号
	 */
	private String imei;

	/**
	 * 0未知,1安卓,2IOS
	 */
	private int os_type;

	/**
	 * 系统版本
	 */
	private String sdk_version;

	/**
	 * 手机型号
	 */
	private String mobile_model;

	/**
	 * 手机品牌
	 */
	private String mobile_brand;

	/**
	 * 当前APP版本
	 */
	private String version;

	/**
	 * 来源渠道
	 */
	private String channel;

	/**
	 * 客户端唯一标识
	 */
	private String clientId;

	/**
	 *  升级版本，数字版本号
	 */
	private int versioncode;

	/**
	 * 用户登录token
	 */
	private String token;

	/**
	 * 应用包名
	 */
	private String packageName;

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public int getOs_type() {
		return os_type;
	}

	public void setOs_type(int os_type) {
		this.os_type = os_type;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getSdk_version() {
		return sdk_version;
	}

	public void setSdk_version(String sdk_version) {
		this.sdk_version = sdk_version;
	}

	public String getMobile_model() {
		return mobile_model;
	}

	public void setMobile_model(String mobile_model) {
		this.mobile_model = mobile_model;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public int getVersioncode() {
		return versioncode;
	}

	public void setVersioncode(int versioncode) {
		this.versioncode = versioncode;
	}

	public String getMobile_brand() {
		return mobile_brand;
	}

	public void setMobile_brand(String mobile_brand) {
		this.mobile_brand = mobile_brand;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

}
