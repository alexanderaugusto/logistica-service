package br.inatel.dm112.model;

public class DeliveryStatus {

    public enum DELIVERY_STATUS {
        OK, NULL_VALUES, ORDER_NOT_FOUND, WRONG_ORDER_STATUS, ORDER_ERROR, EMAIL_ERROR, CLIENT_NOT_FOUND
    }

    private String deliveryCpf;
    private int orderNumber;
    private String status;

    public DeliveryStatus() {
    }

    public DeliveryStatus(String status, String deliveryCpf, int orderNumber) {
        super();
        this.status = status;
        this.deliveryCpf = deliveryCpf;
        this.orderNumber = orderNumber;
    }

    public static DeliveryStatus createErrorStatus(DELIVERY_STATUS errorStatus, String deliveryCpf, int orderNumber) {
        return new DeliveryStatus(errorStatus.toString(), deliveryCpf, orderNumber);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeliveryCpf() {
        return deliveryCpf;
    }

    public void setDeliveryCpf(String deliveryCpf) {
        this.deliveryCpf = deliveryCpf;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public String toString() {
        return "PaymentStatus [deliveryCpf=" + deliveryCpf + ", orderNumber=" + orderNumber + ", status=" + status
                + "]";
    }
}
