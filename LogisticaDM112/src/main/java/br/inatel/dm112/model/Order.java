package br.inatel.dm112.model;

import java.util.Date;

public class Order {

    public static enum STATUS {
        FILLED, PENDING, CONFIRMED
    }

    public static enum DELIVERY_STATUS {
        PENDING, ON_ROUTE, DELIVERED
    }

    private int number;

    private String cpf;

    private float value;

    private int status;

    private Date orderDate;

    private Date issueDate;

    private Date paymentDate;

    private int deliveryStatus;

    private Date deliveryDate;

    private String deliveryCpf;

    public Order() {
    }

    public Order(int number, String cpf, float value, int status, Date orderDate, Date issueDate, Date paymentDate,
            int deliveryStatus, Date deliveryDate, String deliveryCpf) {
        super();
        this.number = number;
        this.cpf = cpf;
        this.value = value;
        this.status = status;
        this.orderDate = orderDate;
        this.issueDate = issueDate;
        this.paymentDate = paymentDate;
        this.deliveryStatus = deliveryStatus;
        this.deliveryDate = deliveryDate;
        this.deliveryCpf = deliveryCpf;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public int getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(int deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryCpf() {
        return deliveryCpf;
    }

    public void setDeliveryCpf(String deliveryCpf) {
        this.deliveryCpf = deliveryCpf;
    }

    @Override
    public String toString() {
        return "Order [number=" + number + ", cpf=" + cpf + ", value=" + value + ", status=" + status + ", orderDate="
                + orderDate + ", issueDate=" + issueDate + ", paymentDate=" + paymentDate + "]";
    }

}
