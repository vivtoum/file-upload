package com.yt.file.upload.fileserver.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayOpenPublicTemplateMessageIndustryModifyRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayOpenPublicTemplateMessageIndustryModifyResponse;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.yt.file.upload.config.AlipayConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.yt.file.upload.config.AlipayConfig.*;

@Slf4j
@Controller
@RequestMapping("/alipay/")
public class AlipayController {

    @GetMapping("/pay")
    public void pay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.info("开始生成支付宝订单");
        //  订单名称
        String title = "胡毅涛商家";
        //  订单价格
        String total = "258888";
        String message = "如有什么建议欢迎留言评论";
        //生成订单号
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String orderSn = simpleDateFormat.format(Calendar.getInstance().getTime());
        //向支付宝发送请求
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl, app_id, merchant_private_key, format, charset, alipay_public_key, sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = orderSn;
        //付款金额，必填
        String total_amount = total;
        //订单名称，必填
        String subject = title;
        //商品描述，可空
        String body = message;
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"total_amount\":\""
                + total_amount + "\"," + "\"subject\":\"" + subject + "\"," + "\"body\":\"" + body + "\"," + "\"timeout_express\":\"5m\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
        //alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
        //        + "\"total_amount\":\""+ total_amount +"\","
        //        + "\"subject\":\""+ subject +"\","
        //        + "\"body\":\""+ body +"\","
        //        + "\"timeout_express\":\"10m\","
        //        + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节
        AlipayTradePagePayResponse alipayResponse = null;
        try {
            alipayResponse = alipayClient.pageExecute(alipayRequest);
            log.info("Body :" + alipayResponse.getBody());
            log.info("Msg :" + alipayResponse.getMsg());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(alipayResponse.getBody());
    }

    @PostMapping("detail")
    public void detail(String no) throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl, app_id, merchant_private_key, format, charset, alipay_public_key, sign_type);
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizContent("{" +
                "\"out_trade_no\":\"" + no + "\"," +
                "  }");
        AlipayTradeQueryResponse response = alipayClient.execute(request);
        if (response.isSuccess()) {
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
    }
}
