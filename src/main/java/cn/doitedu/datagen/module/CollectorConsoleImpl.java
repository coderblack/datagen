package cn.doitedu.datagen.module;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CollectorConsoleImpl implements Collector {
    @Override
    public void collect(String logdata) {
        log.info(logdata);
    }
}
