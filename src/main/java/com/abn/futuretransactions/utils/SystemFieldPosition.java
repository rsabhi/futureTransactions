package com.abn.futuretransactions.utils;
/**
 * @author abhi
 */
public enum SystemFieldPosition {
	CLIENT_TYPE_START(4),
	CLIENT_TYPE_END(7),
	CLIENT_NUMBER_START(8),
	CLIENT_NUMBER_END(11),
	ACCOUNT_NUMBER_START(12),
	ACCOUNT_NUMBER_END(15),
	SUBACCOUNT_NUMBER_START(16),
	SUBACCOUNT_NUMBER_END(19),
	EXCHANGE_CODE_START(28),
	EXCHANGE_CODE_END(31),
	PRODUCT_GROUP_CODE_START(26),
	PRODUCT_GROUP_CODE_END(27),
	SYMBOL_START(32),
	SYMBOL_END(37),
	EXPIRATION_DATE_START(38),
	EXPIRATION_DATE_END(45),
	QUANTITY_LONG_START(53),
	QUANTITY_LONG_END(62),
	QUANTITY_SHORT_START(64),
	QUANTITY_SHORT_END(73);

    private final int pos;

    private SystemFieldPosition(int pos) {
        this.pos = pos;
    }

    public int getSystemFieldNamePos() {
        return this.pos;
    }
}
