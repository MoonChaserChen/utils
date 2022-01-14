package ink.akira.utils.client.snowflake;

/**
 * Created by Allen on 2018/6/1.
 */
public enum IdType {
    // IdType.id must in [0,15)
    PURCHASE_ORDER_ID(1, "商户订单号"),
    PAY_ORDER_ID(2, "商户支付订单号"),
    ;
    private int id;
    private String name;

    IdType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static IdType getById(int id) {
        for (IdType idType : IdType.values()) {
            if (idType.getId()== id) {
                return idType;
            }
        }
        return null;
    }
}
