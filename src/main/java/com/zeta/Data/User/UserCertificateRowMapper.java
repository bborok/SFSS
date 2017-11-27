package com.zeta.Data.User;

import com.zeta.Models.Certificate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserCertificateRowMapper implements RowMapper<Certificate> {
    @Override
    public Certificate mapRow(ResultSet resultSet, int i) throws SQLException {
        Certificate certificate = new Certificate();

        certificate.setName(resultSet.getString("CertificateName"));
        certificate.setLevel(resultSet.getString("Level"));
        certificate.setNumber(resultSet.getInt("Number"));
        certificate.setExpirationDate(resultSet.getDate("ExpirationDate"));

        return certificate;
    }
}
