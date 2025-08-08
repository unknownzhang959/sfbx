package com.itheima.sfbx.sms.binding;

import com.itheima.sfbx.framework.rabbitmq.source.SmsSource;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * @ClassName Binding.java
 * @Description 绑定声明
 */
@EnableBinding({SmsSource.class})
public class SourceBinding {
}
