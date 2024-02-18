package com.demos.practice001;

import com.demos.annotations.RunnerMember;
import com.demos.annotations.RunnerTask;
import com.demos.utils.fmt.Printable;
import org.apache.commons.lang3.StringUtils;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;

@RunnerMember
public class JVMShutdownHook {
    @RunnerTask
    public void task() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            RuntimeMXBean mxBean = ManagementFactory.getRuntimeMXBean();
            String shows = Printable
                    .start()
                    .width(80)
                    .larWidth(1)
                    .spaceNum(1)
                    .border("*")
                    .position(Printable.Position.CENTER)
                    .line(StringUtils.join(
                            mxBean.getVmName(), " @ ",
                            mxBean.getVmVersion(), " by ",
                            mxBean.getVmVendor()
                    ))
                    .line(StringUtils.join(
                            "JVM started on: ",
                            LocalTime.ofInstant(Instant.ofEpochMilli(mxBean.getStartTime()), ZoneId.of("UTC+8"))
                    ))
                    .line(StringUtils.join(
                            "JVM uptime: ",
                            mxBean.getUptime(), "ms"
                    ))
                    .build();
            System.out.println(shows);
        }));
    }
}
