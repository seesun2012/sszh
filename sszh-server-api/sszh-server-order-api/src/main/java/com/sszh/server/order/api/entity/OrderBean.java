package com.sszh.server.order.api.entity;

import com.sszh.core.entity.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Data
@Builder
public class OrderBean extends CommonEntity {

    private static final long serialVersionUID = 5568834579691863528L;
    
    private String id;
    private String orderNo;
    private Double price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}