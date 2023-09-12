package com.pathwheel.spring;
//testejv

import com.pathwheel.jdbc.JdbcRegister;
import com.pathwheel.jdbc.PostgreSql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;


import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hello")
public class HelloController {

    JdbcRegister jdbcRegister = new JdbcRegister();

    @GetMapping
    public String HelloController() throws SQLException {

        jdbcRegister.getjdbc();

        return "Hello word!";
    }
}
