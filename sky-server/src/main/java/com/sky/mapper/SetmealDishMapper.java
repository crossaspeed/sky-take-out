package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealDishMapper {


    List<Long> getSetmealIdsByDishId(List<Long> dishIds);

    void insert(List<SetmealDish> setmealDishes);


    void delete(List<Long> ids);

    void update(List<SetmealDish> setmealDishes);

    void deleteBySetmealId(Long setmealId);
}
