package com.example.hellodb;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.Collections;
import java.util.Map;

@Service
public class HelloDbService {

    private final SimpleJdbcCall simpleJdbcCall;

    public HelloDbService(DataSource dataSource) {
        this.simpleJdbcCall = new SimpleJdbcCall(dataSource)
        		//.withReturnValue()
        		//.withoutProcedureColumnMetaDataAccess()
        		//.withSchemaName("HELLO")
        		.withFunctionName("HELLO")
                .declareParameters(
                        new SqlParameter("P_NAME", Types.VARCHAR),
                        new SqlOutParameter("return", Types.VARCHAR)
                );
    }

    public String callHelloFunction(String name) {
        Map<String, Object> inParams = Collections.singletonMap("P_NAME", name);
        Map<String, Object> out = simpleJdbcCall.execute(inParams);
        return (String) out.get("return");
    }
}