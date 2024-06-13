package com.example.titanic.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TitanicController {
    @Autowired JdbcTemplate jt;
    @GetMapping("survived")
    public List<Map<String, Object>> titSurvived(
            @RequestParam("value") int value) {
            // 원래 데이터베이스는 숫자, 문자 다 허용하기 때문에 String value 라고 적어도 동작함
        String sql = "select * from titanic where survived = " + value;

        // // StringBuffer : sql문 라인 맞추기 좋음
        // StringBuffer sql2 = new StringBuffer();
        // sql2.append("select *");
        // sql2.append(" from titanic");
        // sql2.append(" where survived = 0");

        // // 강사님 풀이
        // List<Map<String, Object>> list = jt.queryForList(sql);
        // return list;

        return jt.queryForList(sql);
    }

    @GetMapping("name")
    public List<Map<String, Object>> titName(
            @RequestParam("value") String value) {
        String sql = "select * from titanic where name like concat('%', '" + value + "', '%')";
        // select * from titanic where name like concat('%', 'value', '%')
        return jt.queryForList(sql);
    }

}
