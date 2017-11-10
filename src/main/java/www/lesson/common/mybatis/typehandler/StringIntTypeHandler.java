package www.lesson.common.mybatis.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StringIntTypeHandler extends BaseTypeHandler {


    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Object o, JdbcType jdbcType) throws SQLException {

        preparedStatement.setInt(i, Integer.parseInt(o.toString()));
    }

    public Object getNullableResult(ResultSet resultSet, String s) throws SQLException {

        return resultSet.getString(s);
    }

    public Object getNullableResult(ResultSet resultSet, int i) throws SQLException {

        return resultSet.getString(i);
    }

    public Object getNullableResult(CallableStatement callableStatement, int i) throws SQLException {

        return callableStatement.getString(i);
    }

}
