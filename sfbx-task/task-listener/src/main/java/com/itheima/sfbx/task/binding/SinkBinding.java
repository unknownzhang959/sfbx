package com.itheima.sfbx.task.binding;

import com.itheima.sfbx.framework.rabbitmq.sink.*;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * @ClassName Binding.java
 * @Description 绑定声明
 */
@EnableBinding({LogSink.class, SmsSink.class, FileSink.class, TradeSink.class, WarrantySink.class})
public class SinkBinding {
}
