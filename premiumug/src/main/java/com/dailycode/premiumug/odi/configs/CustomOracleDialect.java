package com.dailycode.premiumug.odi.configs;

import org.hibernate.dialect.Oracle12cDialect;

public class CustomOracleDialect extends Oracle12cDialect {
    @Override
    public String getQuerySequencesString() {
        return "select SEQUENCE_OWNER, SEQUENCE_NAME, greatest(MIN_VALUE,         -9223372036854775807) MIN_VALUE,\n"+
                "Least(MAX_VALUE, 9223372036854775808) MAX_VALUE, INCREMENT_BY,     CYCLE_FLAG, ORDER_FLAG, CACHE_SIZE,\n"+
                "Least(greatest(LAST_NUMBER, -9223372036854775807), 9223372036854775808) LAST_NUMBER,\n"+
                "PARTITION_COUNT, SESSION_FLAG, KEEP_VALUE\n"+
                "from all_sequences";
    }
}
