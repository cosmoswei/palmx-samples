package me.wei.util;

import lombok.Data;

@Data
public class Metric {
    private long times;
    private long interval;

    public Metric(long times, long interval) {
        this.times = times;
        this.interval = interval;
    }
}
