package fr.avalonlab.warp10;

public class WarpConfig {
    private TS_CONF ts_conf = TS_CONF.US;

    public enum TS_CONF {
        MS,US,NS
    }

    public String toWarpTs(long tsMs) {
        switch (ts_conf) {
            case US:
                return Long.toString(tsMs) + "000";
            case MS:
                return Long.toString(tsMs);
            case NS:
                return Long.toString(tsMs) + "000000";
        }
        throw new RuntimeException("A case is not implemented");
    }
}
