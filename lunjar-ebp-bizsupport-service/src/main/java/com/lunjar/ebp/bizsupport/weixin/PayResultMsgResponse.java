package com.lunjar.ebp.bizsupport.weixin;

/**
 * 支付结果通知封装
 * 
 * @author Leon
 * @since 2015-11-23
 */
public class PayResultMsgResponse {

	private static final long serialVersionUID = -623749138374369665L;

	/**
	 * 返回状态码：SUCCESS、FAIL
	 * 注意：此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
	 */
	private String returnCode;
	
	/**
	 * 返回信息如非空，为错误原因：签名失败、参数格式校验错误
	 */
	private String returnMsg;
	
	/* return_code 为 SUCCESS */
	/**
	 * 公众账号ID
	 */
	private String appId;
	
	/**
	 * 商户号
	 */
	private String mchId;
	
	/**
	 * 设备号
	 */
	private String deviceInfo;
	
	/**
	 * 随机字符串
	 */
	private String nonce;
	
	/**
	 * 业务结果：SUCCESS、FAIL
	 */
	private String resultCode;
	
	/**
	 * 错误代码
	 */
	private String errCode;
	
	/**
	 * 错误代码描述
	 */
	private String errCodeDesc;
	
	/**
	 * 用户标识
	 */
	private String openId;
	
	/**
	 * 用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效
	 */
	private String hasSubscribe;
	
	/**
	 * 交易类型：JSAPI、NATIVE、APP、MICROPAY
	 */
	private String tradeType;
	
	/**
	 * 银行类型，采用字符串类型的银行标识
	 */
	private String bankType;
	
	/**
	 * 订单总金额，单位为分
	 */
	private Long totalFee;
	
	/**
	 * 货币种类，默认人民币：CNY
	 */
	private String feeType;
	
	/**
	 * 现金支付金额
	 */
	private Long cashFee;
	
	/**
	 * 现金支付货币类型，默认人民币：CNY
	 */
	private String cashFeeType;
	
	/**
	 * 代金券或立减优惠金额
	 * 注意：
	 * 1. 代金券或立减优惠金额 <= 订单总金额
	 * 2. 订单总金额 -代金券或立减优惠金额 = 现金支付金额
	 */
	private Long couponFee;
	
	/**
	 * 代金券或立减优惠使用数量
	 */
	private Integer couponCount;
	
	/**
	 * 代金券或立减优惠ID
	 */
	private String[] couponIds;
	
	/**
	 * 单个代金券或立减优惠支付金额
	 */
	private Integer[] couponFees;
	
	
	/**
	 * 微信支付订单号
	 */
	private String transactionId;
	
	/**
	 * 商户订单号
	 */
	private String outTradeNo;
	
	/**
	 * 附加数据，原样返回
	 */
	private String attach;
	
	/**
	 * 订单支付时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010
	 */
	private String timeEnd;

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrCodeDesc() {
		return errCodeDesc;
	}

	public void setErrCodeDesc(String errCodeDesc) {
		this.errCodeDesc = errCodeDesc;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getHasSubscribe() {
		return hasSubscribe;
	}

	public void setHasSubscribe(String hasSubscribe) {
		this.hasSubscribe = hasSubscribe;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public Long getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Long totalFee) {
		this.totalFee = totalFee;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public Long getCashFee() {
		return cashFee;
	}

	public void setCashFee(Long cashFee) {
		this.cashFee = cashFee;
	}

	public String getCashFeeType() {
		return cashFeeType;
	}

	public void setCashFeeType(String cashFeeType) {
		this.cashFeeType = cashFeeType;
	}

	public Long getCouponFee() {
		return couponFee;
	}

	public void setCouponFee(Long couponFee) {
		this.couponFee = couponFee;
	}

	public Integer getCouponCount() {
		return couponCount;
	}

	public void setCouponCount(Integer couponCount) {
		this.couponCount = couponCount;
	}

	public String[] getCouponIds() {
		return couponIds;
	}

	public void setCouponIds(String[] couponIds) {
		this.couponIds = couponIds;
	}

	public Integer[] getCouponFees() {
		return couponFees;
	}

	public void setCouponFees(Integer[] couponFees) {
		this.couponFees = couponFees;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}

	public boolean isSuccess() {
		boolean result = false;
		if ("SUCCESS".equals(this.returnCode)) {
			if ("SUCCESS".equals(this.resultCode)) {
				result = true;
			}
		}
		
		return result;
	}
	
}
