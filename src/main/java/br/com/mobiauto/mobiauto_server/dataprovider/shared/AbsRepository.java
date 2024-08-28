package br.com.mobiauto.mobiauto_server.dataprovider.shared;

import br.com.mobiauto.mobiauto_server.core.shared.Utils;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;

public abstract class AbsRepository {

    protected void setNullableString(CallableStatement cs, int index, String string) throws SQLException {
        if (string == null) {
            cs.setNull(index, Types.VARCHAR);
        } else {
            cs.setString(index, string);
        }
    }

    protected void setNullableDate(CallableStatement cs, int index, String dateString) throws SQLException {
        if (dateString == null) {
            cs.setNull(index, Types.DATE);
        } else {
            cs.setDate(index, Date.valueOf(Utils.parse(dateString)));
        }
    }

    protected void setNullableLong(CallableStatement cs, int index, Long longValue) throws SQLException {
        if (longValue == null) {
            cs.setNull(index, Types.BIGINT);
        } else {
            cs.setLong(index, longValue);
        }
    }

}
