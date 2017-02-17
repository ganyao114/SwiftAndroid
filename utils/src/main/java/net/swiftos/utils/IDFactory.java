package net.swiftos.utils;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by gy939 on 2017/1/17.
 */

public class IDFactory {

    private static AtomicLong id = new AtomicLong(0);

    public static long getId() {
        return id.incrementAndGet();
    }

    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

}
