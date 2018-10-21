package com.merit.utils.weixin.core;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class PayFeedbBackServlet extends HttpServlet {

	private static final long serialVersionUID = -7558243553589930031L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 微信加密签名
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");

		PrintWriter out = response.getWriter();
		System.out.println("signature==" + signature);
		System.out.println("timestamp==" + timestamp);
		System.out.println("nonce==" + nonce);
		System.out.println("echostr==" + echostr);
		// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
		// if (SignUtil.checkSignature(signature, timestamp, nonce)) {
		// out.print(echostr);
		// }
		out.close();
		out = null;
	}

	/*
	 * 微信支付返回接口 (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		ServletInputStream in = req.getInputStream();
//		// 转换微信post过来的xml内容
//		String xmlMsg = Tools.inputStream2String(in);
//		// System.out.println("返回的xml数据" + xmlMsg);
//		PayFeedBack postData = XMLUtils.transXMLPayFeedBack(xmlMsg);
//		if (null == postData) {
//			System.out.println("postData:isnull...");
//			return;
//		}
//		// System.out.println(postData.toString());
//		String ddid = postData.getOut_trade_no();
//		if (TextUtils.isEmpty(ddid))
//			return;
//		CommonSer ser = (CommonSer) BeanFactory.findBean("appBaseSer");
//		// 支付失败
//		if (!postData.getResult_code().equalsIgnoreCase("SUCCESS"))
//			return;
//		// 支付成功
//		String hql = " from AppBizOrder where ddid=?";
//		Object obj = ser.queryOneByHql(hql, ddid);
//		if (null == obj)
//			return;
//		AppBizOrder order = (AppBizOrder) obj;
//		if (null != order.getZfzt() && order.getZfzt().intValue() > 0) {
//			// 表示已经支付
//			resp.getWriter().println(XMLUtils.paySuccess("SUCCESS", ""));
//		} else {
//			order.setZfzt((short) 1);
//			order.setDdzt((short) 1);
//			order.setFkzh(postData.getOpenid());
//			order.setFksj(new Date());
//			order.setUpdatetime(new Date());
//			ser.saveOrUpdate(order);
//			// 根据订单方式判断是否参团成功
//			if (null != order.getDdfs() && order.getDdfs().intValue() == 0) {
//				order.setDdzt((short) 2);
//				ser.saveOrUpdate(order);
//			} else {
//				// 团购订单
//				int tgrs = 1;
//				if (!TextUtils.isEmpty(order.getExp8()))
//					tgrs = new Integer(order.getExp8());
//				int count = 0;
//				hql = "from AppBizOrder where tid='" + order.getTid() + "' and zfzt=1";
//				count = ser.queryAllCount(hql);
//				if (count == tgrs) {
//					hql = "update AppBizOrder set tgzt=1,ddzt=2 where tid='" + order.getTid() + "'";
//					ser.executeUpdateHql(hql);
//					// 同时修改库存
//					hql = " from AppBizProduct where spid=?";
//					obj = ser.queryOneByHql(hql, order.getSpid());
//					if (null != obj) {
//						AppBizProduct p = (AppBizProduct) obj;
//						if (!TextUtils.isEmpty(p.getExp7()))
//							p.setExp7((Integer.parseInt(p.getExp7()) + p.getTgrs()) + "");
//						else
//							p.setExp7(p.getTgrs() + "");
//						ser.saveOrUpdate(p);
//					}
//					// 修改商品对应的规格数量
//					hql = " from AppBizStandard a,AppBizOrder b where a.ggid=b.exp5 and b.tid=?";
//					List<Object[]> _objs = ser.queryAllByHql(hql, order.getTid());
//					List<AppBizStandard> _svStand = new ArrayList<AppBizStandard>();
//					Map<String, AppBizStandard> _standMap = new HashMap<String, AppBizStandard>();
//					for (Object[] _obj : _objs) {
//						AppBizStandard stand = (AppBizStandard) _obj[0];
//						if (null != _standMap.get(stand.getGgid()))
//							stand = _standMap.get(stand.getGgid());
//						if (null != stand.getYscsl())
//							stand.setYscsl(stand.getYscsl() + 1);
//						else
//							stand.setYscsl(1);
//					}
//					Set _set = _standMap.keySet();
//					for (Object object : _set) {
//						_svStand.add(_standMap.get(object.toString()));
//					}
//					if (null != _svStand && _svStand.size() > 0)
//						ser.saveAll((Collection) _svStand);
//				}
//			}
//			// 判断这个团中是否都已经付款，如果都付款，表示
//			// 增加付款记录
//			AppBizRefund ref = new AppBizRefund();
//			ref.setId(TextUtils.getUUID());
//			ref.setDdid(order.getDdid());
//			ref.setGzhid(postData.getOpenid());
//			ref.setFkfs((short) 0);
//			ref.setFklsh(postData.getTransaction_id());
//			ref.setFklx((short) 0);
//			ref.setSjc(postData.getTime_end());
//			ref.setShh(postData.getMch_id());
//			ref.setSjzfc(postData.getNonce_str());
//			ref.setQm(postData.getSign());
//			ref.setShddh(postData.getOut_trade_no());
//			ref.setZje(new Double(postData.getTotal_fee()));
//			ref.setHbzl(postData.getFee_type());
//			ref.setExp1(postData.getBank_type());
//			ref.setExp2(postData.getCash_fee());
//			ref.setExp3(postData.getIs_subscribe());
//			ref.setExp4(postData.getResult_code());
//			ref.setExp5(postData.getTrade_type());
//			ref.setCreatetime(new Date());
//			ref.setUpdatetime(new Date());
//			ser.saveOrUpdate(ref);
//			// 微信端发送回复
//
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("touser", postData.getOpenid());
//			map.put("msgtype", "news");
//			List<Articles> atls = new ArrayList<Articles>();
//			Articles at = new Articles();
//			at.setTitle(com.app.Constants.FKCG_MESS_TITLE);
//			String content = com.app.Constants.FKCG_MESS_TZ;
//			if (order.getFkrsf() == 0)
//				content = com.app.Constants.FKCG_MESS_ZY;
//			at.setDescription(content);
//			String url = com.app.Constants.FKCG_MESS_URL;
//			url = url.replaceAll("DDID", order.getDdid());
//			at.setUrl(url);
//			atls.add(at);
//			Map<String, Object> _map = new HashMap<String, Object>();
//			_map.put("articles", atls);
//			map.put("news", _map);
//			WeixinUtil.sendBackMsg(map, JSSDKCONS.accessToken.getToken());
//			//
//			// Map<String, Object> map = new HashMap<String, Object>();
//			// map.put("touser", );
//			// map.put("msgtype", "text");
//			// Map<String, String> _contentMap = new HashMap<String, String>();
//			// _contentMap.put("content", "您订购的商品"+order.getExp1()+"已经成功付款");
//			// map.put("text", _contentMap);
//			// // 调用回复接口
//			// boolean flag = WeixinUtil.sendBackMsg(map,
//			// JSSDKCONS.accessToken.getToken());

//			resp.getWriter().println(XMLUtils.paySuccess("SUCCESS", ""));
//		}

	}
}
