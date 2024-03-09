package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/setmeal")
@Api(tags = "套餐类接口")
public class SetmealController {

    @Autowired
    private SetmealService setmealService;

    @PostMapping
    @ApiOperation(value = "插入套餐")
    @CacheEvict(cacheNames = "setmealCache",key = "#setmealDTO.categoryId")
    public Result insert(@RequestBody SetmealDTO setmealDTO) {
        setmealService.insert(setmealDTO);
        return Result.success();
    }

    @GetMapping("/page")
    @ApiOperation(value = "套餐分页查询")
    public Result<PageResult> pageQuery(SetmealPageQueryDTO setmealPageQueryDTO) {
        PageResult pageResult = setmealService.pageQuery(setmealPageQueryDTO);
        return Result.success(pageResult);

    }

    @DeleteMapping
    @ApiOperation(value = "批量删除套餐")
    @CacheEvict(cacheNames = "setmealCache",allEntries = true)
    public Result delete(@RequestParam List<Long> ids) {
        setmealService.delete(ids);
        return Result.success();
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询")
    public Result<SetmealVO> searchById(@PathVariable Long id) {
        SetmealVO setmealVO= setmealService.searchById(id);
        return Result.success(setmealVO);
    }



    @PostMapping("/status/{status}")
    @ApiOperation(value = "设置套餐状态")
    @CacheEvict(cacheNames = "setmealCache",allEntries = true)
    public Result setStatus(@PathVariable Integer status,Long id){
        setmealService.setStatus(status,id);
        return Result.success();
    }

    @PutMapping
    @ApiOperation(value = "更新套餐")
    @CacheEvict(cacheNames = "setmealCache",allEntries = true)
    public Result update(SetmealDTO setmealDTO){
        setmealService.update(setmealDTO);
        return Result.success();

    }
}
