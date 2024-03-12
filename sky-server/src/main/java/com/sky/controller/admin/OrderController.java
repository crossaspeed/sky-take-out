package com.sky.controller.admin;

import com.sky.dto.OrdersCancelDTO;
import com.sky.dto.OrdersConfirmDTO;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.dto.OrdersRejectionDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.OrderService;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("adminOrderController")
@Slf4j
@RequestMapping("/admin/order")
@Api(tags = "商家订单接口")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/conditionSearch")
    @ApiOperation(value = "订单搜索")
    public Result<PageResult> conditionSearch(OrdersPageQueryDTO ordersPageQueryDTO) {
        PageResult pageResult = orderService.conditionSearch(ordersPageQueryDTO);
        return Result.success(pageResult);
    }

    @GetMapping("/statistics")
    @ApiOperation(value = "查询各个订单的数量统计")
    public Result<OrderStatisticsVO> countOrderStatistics() {
        OrderStatisticsVO orderStatisticsVO = orderService.countOrderStatistics();
        return Result.success(orderStatisticsVO);
    }

    @GetMapping("/details/{id}")
    @ApiOperation(value = "查询订单详情")
    public Result<OrderVO> getOrderDetailById(@PathVariable Long id) {
        OrderVO orderVO = orderService.getOrderDetail(id);
        return Result.success(orderVO);
    }

    @PutMapping("/confirm")
    @ApiOperation(value = "接单")
    public Result acceptOrder(@RequestBody OrdersConfirmDTO ordersConfirmDTO) {
        orderService.acceptOrder(ordersConfirmDTO);
        return Result.success();
    }

    @PutMapping("/rejection")
    @ApiOperation(value = "拒单")
    public Result rejectOrder(@RequestBody OrdersRejectionDTO ordersRejectionDTO) throws Exception {
        orderService.rejectOrder(ordersRejectionDTO);
        return Result.success();
    }

    @PutMapping("/cancel")
    @ApiOperation(value = "取消订单")
    public Result cancelOrder(@RequestBody OrdersCancelDTO ordersCancelDTO) throws Exception {
        orderService.adminCancelOrder(ordersCancelDTO);
        return Result.success();
    }

    @ApiOperation(value = "派送订单")
    @PutMapping("/delivery/{id}")
    public Result deliverOrder(@PathVariable Long id) {
        orderService.deliverOrder(id);
        return Result.success();
    }

    @PutMapping("/complete/{id}")
    public Result complete(@PathVariable Long id){
        orderService.complete(id);
        return Result.success();
    }
}
