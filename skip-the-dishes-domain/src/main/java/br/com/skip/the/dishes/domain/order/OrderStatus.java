package br.com.skip.the.dishes.domain.order;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum OrderStatus {
    CANCELLED("Cancelled"),
    FINISHED("Finished"),
    NEW("New");

    private final String status;

    private static final Map<String, OrderStatus> MAP_STATUS;

    static {
        MAP_STATUS = Arrays.stream(values()).collect(Collectors.toMap(k -> k.status.toUpperCase(), v -> v));
    }

    OrderStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }

    public OrderStatus parse(String status) {
        if (status == null) {
            return null;
        }

        return MAP_STATUS.get(status.toUpperCase());
    }
}