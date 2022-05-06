package fr.avalonlab.warp10.lowlevel;

/**
 * format: the format of the response data. Current available formats are:
 *
 * text: ready for ingestion in another Warp 10 instance.
 * fulltext: ready for ingestion in another Warp 10 instance, but less optimized
 * json: ready for web requests. See Json Output Format
 * tsv: tabulation separated values, with only timestamp and values
 * fulltsv: tabulation separated values, with classname and labels on each line
 * pack: GTS with wrapped GTS in the value: compressed object represented as base64 string, ready to UNWRAP.
 */
public enum FETCH_FORMAT {
    TEXT("text"),
    FULLTEXT("fulltext"),
    JSON("json"),
    TSV("tsv"),
    FULLTSV("fulltsv"),
    PACK("pack");

    private final String warp10Word;

    FETCH_FORMAT(String warp10Word) {
        this.warp10Word = warp10Word;
    }

    public String toWarp10() {
        return warp10Word;
    }
}
