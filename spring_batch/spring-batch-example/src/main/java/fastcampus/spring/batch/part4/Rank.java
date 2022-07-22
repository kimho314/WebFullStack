package fastcampus.spring.batch.part4;

import java.security.InvalidParameterException;

public enum Rank {
    NORMAL,
    SILVER,
    GOLD,
    VIP;

    private final static Long MIN_NORMAL = 0L;
    private final static Long MIN_SILVER = 200000L;
    private final static Long MIN_GOLD = 300000L;
    private final static Long MIN_VIP = 500000L;

    public static Boolean isNormalRank(Long score) {
        if (score >= MIN_NORMAL && score < MIN_SILVER) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public static Boolean isSilverRank(Long score) {
        if (score >= MIN_SILVER && score < MIN_GOLD) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public static Boolean isGoldRank(Long score) {
        if (score >= MIN_GOLD && score < MIN_VIP) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public static Boolean isVipRank(Long score) {
        if (score >= MIN_VIP) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public static Rank findByScore(Long score) {
        if (Rank.isNormalRank(score)) {
            return Rank.NORMAL;
        }
        if (Rank.isSilverRank(score)) {
            return Rank.SILVER;
        }
        if (Rank.isGoldRank(score)) {
            return Rank.GOLD;
        }
        if (Rank.isVipRank(score)) {
            return Rank.VIP;
        }
        throw new InvalidParameterException("invalid score");
    }
}
