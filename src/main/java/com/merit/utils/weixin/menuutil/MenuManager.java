package com.merit.utils.weixin.menuutil;


import com.merit.utils.weixin.WxConstants;
import com.merit.utils.weixin.model.AccessToken;
import com.merit.utils.weixin.util.WeixinUtil;

/**
 * 菜单管理器类
 * 
 * @author 杨建冬
 * @date 2013-09-10
 */
public class MenuManager {
	public static void main(String[] args) {
		// 第三方用户唯一凭证
		String wxid = "";//操作指定的微信号
		String appId = WxConstants.wxMap.get(wxid).getAppID();
		// 第三方用户唯一凭证密钥
		String appSecret = WxConstants.wxMap.get(wxid).getAppSecrect();

		// 调用接口获取access_token
		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);
		System.out.println(at.getToken());
		if (null != at) {
			// 调用接口创建菜单
			int result = WeixinUtil.createMenu(getMenu(), at.getToken());
			// 判断菜单创建结果
			if (0 == result)
				System.out.println("菜单创建成功！");
			else
				System.out.println("菜单创建失败，错误码：" + result);
		}
	}

	public static void CreatMenu(String wxid) {
		String appId = WxConstants.wxMap.get(wxid).getAppID();
		// 第三方用户唯一凭证密钥
		String appSecret = WxConstants.wxMap.get(wxid).getAppSecrect();

		// 调用接口获取access_token
		
		if (null != WxConstants.JSSDKCONS.accessTokenMap.get(wxid)) {
			// 调用接口创建菜单
			int result = WeixinUtil.createMenu(getMenu(), WxConstants.JSSDKCONS.accessTokenMap.get(wxid).getToken());
			// 判断菜单创建结果
			if (0 == result) {
				System.out.println("Menu Create Success");
			} else {
				System.out.println("Menu Create Error，ErrorCode:" + result);
			}
		}
	}

	/**
	 * 组装菜单数据
	 * 
	 * @return
	 */
	private static Menu getMenu() {
		// CommonButton btn11 = new CommonButton();
		// btn11.setName("职位分类");
		// btn11.setUrl("http://iteeio.zust.edu.cn/sxjd/jsp/jsp/zwlb.html");
		// btn11.setType("view");
		// btn11.setKey("11");

		// CommonButton btn12 = new CommonButton();
		// btn12.setName("二维码");
		// btn12.setType("view");
		// btn12.setUrl("http://iteeio.zust.edu.cn/weixin/IndexPage!qrCode.htm");
		// btn12.setKey("12");

		// CommonButton btn13 = new CommonButton();
		// btn13.setName("我的二维码");
		// btn13.setType("view");
		// btn13.setUrl("http://iteeio.zust.edu.cn/weixin/IndexPage!barcode.htm");
		// btn13.setKey("13");

		CommonButton btn21 = new CommonButton();
		btn21.setName("乐天商城");
		btn21.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx469baf1fcaa81a80&redirect_uri=http%3A%2F%2Fwww.letianshangcheng.com%2Fptw%2Findex.htm&response_type=code&scope=snsapi_base&state=123#wechat_redirect");
		btn21.setType("view");
		
		CommonButton btn22 = new CommonButton();
		btn22.setName("会员中心");
		btn22.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx469baf1fcaa81a80&redirect_uri=http%3A%2F%2Fwww.letianshangcheng.com%2Fptw%2Findex!grzx.htm&response_type=code&scope=snsapi_base&state=123#wechat_redirect");
		btn22.setType("view");

		// ComplexButton mainBtn2 = new ComplexButton();
		// mainBtn2.setName("文明寝室");
		// mainBtn2.setSub_button(new CommonButton[] { btn21,btn22});

		// ComplexButton mainBtn3 = new ComplexButton();
		// mainBtn3.setName("我");
		// mainBtn3.setSub_button(new CommonButton[] { btn30,btn31, btn32,
		// btn33, btn34 });

		/**
		 * 这是公众号目前的菜单结构，每个一级菜单都有二级菜单项<br>
		 * 
		 * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br>
		 * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br>
		 * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 });
		 */
		Menu menu = new Menu();
		menu.setButton(new Button[] { btn21,btn22 });

		return menu;
	}
}
