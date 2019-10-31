package ru.axxle.devsqlclient;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.axxle.devsqlclient.TableObject;
import org.springframework.jdbc.core.ResultSetExtractor;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Scanner;
import java.io.IOException;

@RestController
public class SqlExecController {
	
	@Autowired
    JdbcTemplate jdbcTemplate;
	
	@RequestMapping(
	value = "/sql2",
	method = RequestMethod.POST, consumes = "text/plain",
	headers = {"Content-type=text/html;charset=utf-8"}
	)
    public @ResponseBody
    ResponseEntity<TableObject> getByQuery2(HttpServletRequest request,
                                                 HttpServletResponse response) {
		String sqlQuery = request.getParameter("sqlQuery");		
		String s = extractPostRequestBody(request);
		System.out.println(s);
        return new ResponseEntity<>(executeQuery(s), HttpStatus.OK);
    }
	
	static String extractPostRequestBody(HttpServletRequest request) {
		if ("POST".equalsIgnoreCase(request.getMethod())) {
			Scanner s = null;
			try {
				s = new Scanner(request.getInputStream(), "UTF-8").useDelimiter("\\A");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return s.hasNext() ? s.next() : "";
		}
		return "";
	}
	
	private TableObject executeQuery(String sqlQuery) {
        TableObject tableObject = null;
        try {
            tableObject = jdbcTemplate.query(sqlQuery, new TableObjectExtractor());
        } catch (Exception e) {
            System.out.println(tableObject);
        }

        return tableObject;
    }

	private static final class TableObjectExtractor implements ResultSetExtractor<TableObject> {
        public TableObject extractData(ResultSet rs) throws SQLException {
            TableObject tableObject = new TableObject();
            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            if(columnCount == 0) {
                return tableObject;
            }

            tableObject.setTableName(resultSetMetaData.getTableName(1));
            tableObject.setDbTimestamp(System.currentTimeMillis());

            String [] headers = new String [columnCount];
            for(int i = 0; i < columnCount; i++) {
                headers[i] = resultSetMetaData.getColumnLabel(i+1);
            }
            tableObject.setHeaders(headers);

            List<String[]> listDataRows = new ArrayList<String[]>();
            while (rs.next()) {
                String [] row = new String [columnCount];
                for(int i = 0; i < columnCount; i++) {
                    row[i] = rs.getString(i+1);
                }
                listDataRows.add(row);
            }
            tableObject.setDataRows(listDataRows);

            return tableObject;
        }
    }
}