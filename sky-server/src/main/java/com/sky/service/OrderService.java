package com.sky.service;

import com.sky.dto.*;
import com.sky.entity.Orders;
import com.sky.result.PageResult;
import com.sky.vo.*;

import java.util.List;

public interface OrderService {
    OrderSubmitVO submit(OrdersSubmitDTO ordersSubmitDTO);

    OrderPaymentVO payment(OrdersPaymentDTO ordersPaymentDTO) throws Exception;

    void paySuccess(String outTradeNo);


    PageResult pageQuery(int pageNum, int pageSize, Integer status);

    OrderVO getOrderDetail(Long id);

    void cancelOrder(Long id) throws Exception;

    void repeatOrder(Long id);



    OrderStatisticsVO countOrderStatistics();


    void acceptOrder(OrdersConfirmDTO ordersConfirmDTO);

    void rejectOrder(OrdersRejectionDTO ordersRejectionDTO) throws Exception;

    void adminCancelOrder(OrdersCancelDTO ordersCancelDTO) throws Exception;

    void deliverOrder(Long id);

    PageResult conditionSearch(OrdersPageQueryDTO ordersPageQueryDTO);

    void complete(Long id);
}
