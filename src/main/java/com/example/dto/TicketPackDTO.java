package com.example.dto;
public class TicketPackDTO {
    private Double cost;
    private Integer count;
    public TicketPackDTO(Double cost, Integer count) {
        this.cost = cost;
        this.count = count;
    }
    public Double getCost() {
        return cost;
    }
    public void setCost(Double cost) {
        this.cost = cost;
    }
    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
    }
}
