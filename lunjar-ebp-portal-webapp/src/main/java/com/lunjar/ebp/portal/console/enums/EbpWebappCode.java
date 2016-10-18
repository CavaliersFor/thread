package com.lunjar.ebp.portal.console.enums;

/**
 * 异常编码
 * 接入者控制台错误编码：2050001-2059999
 * 异常编码定义规则：从每个业务模块的_200X001开始
 * 接入者相关：_2051001
 * 产    品相关：_2052001
 * 业    务相关：_2053001
 * 流    程相关：_2054001
 * 模    板相关：_2055001
 * 接    口相关：_2056001
 * 
 * create at 2016年3月17日 下午2:16:59
 * @author <a href="mailto:xiangzhitong@ancun.com">xiangzhitong</a>
 * @version %I%, %G%
 * @see
 */
public enum EbpWebappCode {
	
	/**模板文件不存在或为空*/
	_2055001(2055001,"模版管理请求失败,请检查访问地址是否正确"),
	_2055002(2055002,"模版管理请求失败,没有产品的权限无法访问"),
	_2055003(2055003,"模版管理请求失败,没有保全点权限无法访问"),
	_2055004(2055004,"模版管理请求失败,没有找到保全点下的步骤"),
	_2055005(2055005,"模版管理请求失败,找到多余保全点下的步骤"),
	_2055006(2055006,"模版管理请求失败,保全点步骤验证信息失败"),
	_2055007(2055007,"模版查看请求失败,缺少记录或还未上传模版"),
	_2055008(2055008,"模版查看请求失败,无权限查看当前模版记录"),
	_2055009(2055009,"模版查看请求失败,未找到当前模版文件记录"),
	_2055010(2055010,"没有产品的权限无法操作"),
	_2055011(2055011,"没有保全点权限无法操作"),
	_2055012(2055012,"产品信息和保全点信息不一致"),
	_2055013(2055013,"多步骤保全点操作缺少步骤"),
	_2055014(2055014,"无步骤保全点操作多余步骤"),
	_2055016(2055016,"没有模版的权限无法操作"),
	_2055017(2055017,"模版已启用无法修改"),
	_2055018(2055018,"模版内容不能为空"),
	_2055019(2055019,"模版已启用无法删除"),
	_2055020(2055020,"缺少请求参数"),
	_2055021(2055021,"该模版已经启用了"),
	_2055022(2055022,"您还没有通过实名认证"),
	
	;
	
	private int code;
	private String msg;
	
	private EbpWebappCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public static EbpWebappCode valueOf(int code){
		EbpWebappCode result = null ;  
		EbpWebappCode[] ts = EbpWebappCode.class.getEnumConstants();
		for(EbpWebappCode aps:ts){
			
			if(aps.getCode()==code){
				result =aps;
				break;
			}
		}
		return result;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}	
}
