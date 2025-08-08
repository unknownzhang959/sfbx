package com.itheima.sfbx.file.binding;

import com.itheima.sfbx.framework.rabbitmq.source.FileSource;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * @ClassName Binding.java
 * @Description 绑定文件发送者声明
 */
@EnableBinding({FileSource.class})
public class SourceBinding {
}
