package com.yt.file.upload.config;

public class AlipayConfig {

    /**
     * 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数
     */
    public static String return_url = "http://localhost:2003/alipay/success";

    /**
     * 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
     */
    public static String notify_url = "http://localhost:2003/notify";

    /**
     * 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
     */
    public static String app_id = "2016092900623717";

    /**
     * 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
     */

    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArpr50fXhrwKDwXUMsUh15sk1azWpw3kxRqXrW5cGj9ZTagWpz525LMuplehQDoz1HxO68M5QVYPt4xccvAnbQites284+pwWuLZu51+9MwvdwBMuSG+12uz12G0b9v4kRFk0mWNFq9tOuVZkw8rsLJXP0uqak4BPA/xWeTU/isfiraTHySukZBZOfDUC8hLp4lBqqEwBFxo/vYHtSjDAimcXS3fV4+MKhyjpwyaZUA96IQwwFpZiUg6ZxxWDWoRYeGfPtr3c5RgrGMrXvbMFhn9XJ0IuBindmjuvt7eeHx5G3fkyzRHTp+uh8wEhe2Bn4rbSIM7uACgFacp8B8RDBwIDAQAB";
    /**
     * 商户私钥，您的PKCS8格式RSA2私钥
     */

    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCumvnR9eGvAoPBdQyxSHXmyTVrNanDeTFGpetblwaP1lNqBanPnbksy6mV6FAOjPUfE7rwzlBVg+3jFxy8CdtCK16zbzj6nBa4tm7nX70zC93AEy5Ib7Xa7PXYbRv2/iREWTSZY0Wr2065VmTDyuwslc/S6pqTgE8D/FZ5NT+Kx+KtpMfJK6RkFk58NQLyEuniUGqoTAEXGj+9ge1KMMCKZxdLd9Xj4wqHKOnDJplQD3ohDDAWlmJSDpnHFYNahFh4Z8+2vdzlGCsYyte9swWGf1cnQi4GKd2aO6+3t54fHkbd+TLNEdOn66HzASF7YGfittIgzu4AKAVpynwHxEMHAgMBAAECggEAQsP36xU+axFnGD6Nz+yJSOp/hrNKaviYow8m5Crn03B9DPmFaWJCQKLQAgaHNkkoIe4FebcqeMit2jwoPK74LGExISPSgeHVvmPkHI07Z52N+bhb5k7xFvmtOEjgfJ3JeGkz98NCDOkbPTuP5F5f2NygT2JuISDbFgcTed2mooYRS2/uAd9AYbOWZAXwQfLuQTDjn9zJ/Lv+oXBWUwnerJM0vM44EAo27+qkHBALstt+Brg0VOsEERmVymMG/23yPngB0W1wdU9vcRZs7DQFzgeJG4z2SCN5sIn6hFiRl/GBAVR2QP9r40oAWx41P7nO+h8hDkvuK25qx61dQRUg4QKBgQDxMqO1oL3mjEcWmQ6HKa6MJkIXJ3LcxDM3HAWcFxtysXOyrICCjjHpsLvbt4o/MnSlLHw0YYmMPcMY3ZgFCIftd/PrKjlZwVD84KoZnHIABfvMXE3FiBEEstaKtvS7QV+amtVStwwOaxqOrSf56K8RMtiBk7IMe+vrj2XqjY5QswKBgQC5UiE26k5BPsgl8/Dzl1M+MBzS5zP/+2WW8DI8pwGsQSElHxQ0vzcLE8jUm1IRRSBmhhcAMMlnbIoydRtpIPEp8gFlC4YrGFZ5xIKrD7btswvdyu7+5ONCNHbOeXoM6GtDlf4+6WJaw7k3LdYLmow/zhWw45uB3YMYsAR+CwBGXQKBgQC/6/+AIqt4q95VCO0ad/w/4KFuMyGesS/fhhign3JjzPe3ELQhLmxg+xRAPMdwigFu/PrH42TDhtGbE5hHSQZ/2pboZOzFkqGS1mIDhwzfH+PANSJdL2v7RSKZtgL+jYI5T0K3UpfxUSAMr5PDs3a6RK8qTA+9B04uXihGfuTjKQKBgGbqSE3B4wFyxx3kkbwFHpJbj7ZD9WnEB8/BxnROXqx1Yt3Fm6gLRJsfOplvRC0ZEXw+HQnbDg4khb6tsDI6vBMKNWIS+0h1dJic29/5kyIAG6fpKb5eCCLdFdlmNrRyYhcfdhkj7J3xMauGBeddxWMtpHgefl6pUENmhKgmndl1AoGAUaEFAKs7tjKLLAMr2b+h60nECyklvmMrSzV4ykfUv5mmHZwGABoS97nrHSezoNXVrJMQumFh6rCsSLApjth/gXZ/8HjXZ0BCbK/RrEY5wmodCf8pcxS9/nczEFLdravFy6yk7qm4cCFNzmFjU7oVAlGG//QFt+2c+t8ir6RadIs=";
    /**
     * 签名方式
     */
    public static String sign_type = "RSA2";

    /**
     * 字符编码格式
     */

    public static String charset = "utf-8";

    /**
     * 请求参数格式化
     */
    public static String format = "json";

    /**
     * 支付宝网关
     */
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
}