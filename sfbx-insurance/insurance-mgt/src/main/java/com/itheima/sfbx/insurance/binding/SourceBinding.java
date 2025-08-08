package com.itheima.sfbx.insurance.binding;

import com.itheima.sfbx.framework.rabbitmq.source.WarrantySource;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * @ClassName Binding.java
 * @Description 绑定文件发送者声明
 */
@EnableBinding({WarrantySource.class})
public class SourceBinding {
}
