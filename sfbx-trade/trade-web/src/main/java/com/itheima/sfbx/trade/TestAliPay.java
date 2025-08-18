package com.itheima.sfbx.trade;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeWapPayResponse;

public class TestAliPay {

    //黑马 应用私钥
    private static final String merchantPrivateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCYTgKj2xEIJi7lqjGRuQ/XWNDRuhoMtWvQg1DzF3fDWodh6WMed5V7mi3s4zXLnm4TvHVazgLnVXJlONxG4EsVJRGp3hY1fSGAAcByRC2E8E+NdhIvaOAWUuEid6AeshfNqmePnJyHxpxf9ZYn0Bij0kM8yMwv1izkBmsuydXroQegYfias6HT+CQ9mgU0awb6ZPeGqC6sw9Tv991jOohGv4xgEVjRh69pvB8Eo1vubYJ8mhOEf5xwWkx8/n7tDa4uA7ioUlgLRxhtOkCkpTQXzLAzm8gxoJlEYL/sFR3plaPBRAafWoKdfF55W0SBXDhhNqqce+r1H4pw4IyZxqyrAgMBAAECggEAYAcnoPpZlcLFZObXFCMTutpz5xgonoSwsqppGqxcRZ7Jp1FIvof1hxYiCK8FVxnQG7+CWrtzlzoHw4yDTmjSzkUuCuVNKXJ48cWo+iLEdII0FmQweRXt3AVrj5jPKyts2K6tVx4Oj4kJRXOJthZ9wqSq4iNUooCukyL852Y467P/Me+9Vr2vQb117qLaNTwvR2GV6OZUNQPl7qKNsp86e5lREIBHPk8uhQO3KS+QPPvTDkKBH+bNo7Bu4L4J0ITdvjU9FupaQ6xfFCOrgu7P2bw9Mk2JM6jHp1hEjuoLVGA97sL6CSPhQu9s9KJbup2DGOI1qQoEATBypHFueYv4yQKBgQD57xI1iPUl3tVNH5e3yYa08NLS+mV9N3tRTjIWHbMwQKMbraaO+dGQRGiytjejao3y0EVFuqOuhbXAxtvb1pUukASqY3zW01zzVNNw8nO3zeVPo+xWLAvlnyX3MekKMYjo1dAyWBzXuPgo3D413nMiPZ8oOgtbpTyu/dTeN3NJTQKBgQCcAFWSUinmZ3x9Xr779CeX1MsKG+++C4iLP7vNP8Lf8IcPE8NnHYZTQqDuvq1Itai7UbhZX95itYqjp9SlXT4hSrMGI7qwobJ0vXxXrNN6VwZtz1N75vnIrZNHnTFMWTUBKCySLcpsqs7qCSQEv6luOQUSUH/0gaN6txOq5W4R1wKBgH4GdLIV6zc7U2beJUyBC7G1NTk5FW+8SCxJN6w7MZ2FGjncp/20Ll2GgRyMESYPlp/3MNbmM57OwUUBgN8rJnIiIJgiLlLMpTP1c+CiAIOQCK7Nw1/4Oc+BHk21FwMS0yxElASutWx5UniYBa54CqobVGOeURfXC/BZAbtDTpiJAoGAMR3B03HfE1Xd0jM0emti0+EBlEs7bmB/Oyhz3qmGl69JNqwIR7z5/9johoKuWEgpueB+5FTU1ctGvUQoJXB4EU9Nkk9JhjdC0pKeRZR6ePhRY9108XvFhTNxPYj2bo1frN+TOOsF4rTctL7wAja+B6AYQq3pu3fdmtNtc88Mmr0CgYEAuDD/PzVTLMYgEqq0ruZcCyFd0HwMV8KoC07aXXeLZT/IGtoJASFkWIxcoyK8NVZvDqoGkpaLAw3G7CmzoxpqSvyMWwHGdZy7KDV55g5O9r4Lt/dti7xt6gBYm8XB1X7cLcu0x9lYs6KL01To+Ep8DxSO7LUODQ0Utu3Pkf9W1qc=";

    //黑马 支付宝公钥
    private static final String alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhUnjdAKwZApwZEcfq+5L0pa77Vg3mqcoXv+th8RR0SYotkPsH1f2JkbS48ySaSCM6YNWSMNfqp5qdOla2zUJOBnJ/yaBg7s7fVD6V3M2mEog8kCDYGKt/3P4VII3xYl8lFYMQ3IcFRELkxCBBCA8JDKmf5z2R4F/Z/jFFEuOwxaJvp+7Ke9OzZHYdWGNnU6QP8YYLYUeX7VNZLHEuly34ExAw6A+yJkNDsYEho2Lu31QjT2pLh9g+88MlRfiI92iN25O9NVdeM4f5RcpvBPrBQZQs9tlFmALYSFS3prIf3FAobWM+W7iwxT6J25nFIhst1DdJQfIBpaeRUJVTkn99QIDAQAB";

    public static void main(String[] args) throws Exception {

        AlipayClient alipayClient = new DefaultAlipayClient(
        "https://openapi.alipay.com/gateway.do", "2021002173680104",
         merchantPrivateKey, "json", "utf-8", alipayPublicKey, "RSA2");
        AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
        //异步接收地址，仅支持http/https，公网可访问
        request.setNotifyUrl("");
        //同步跳转地址，仅支持http/https
        request.setReturnUrl("");
        /******必传参数******/
        JSONObject bizContent = new JSONObject();
        //商户订单号，商家自定义，保持唯一性，每次测试都要修改
        bizContent.put("out_trade_no", "2021081701010100400045");
        //支付金额，最小值0.01元
        bizContent.put("total_amount", 0.01);
        //订单标题，不可使用特殊符号
        bizContent.put("subject", "测试商品");
        request.setBizContent(bizContent.toString());
        AlipayTradeWapPayResponse response = alipayClient.pageExecute(request, "POST");
        // 如果需要返回GET请求，请使用
        // AlipayTradeWapPayResponse response = alipayClient.pageExecute(request,"GET");
        String pageRedirectionData = response.getBody();
        System.out.println(pageRedirectionData);

        if (response.isSuccess()) {
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
    }

}