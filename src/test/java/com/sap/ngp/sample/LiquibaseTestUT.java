/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sap.ngp.sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.FileSystemResourceAccessor;

import org.junit.Assert;


@RunWith(SpringRunner.class)
public class LiquibaseTestUT {

    private static Connection conn;
    private static Liquibase liquibase;

    @BeforeClass
    public static void createTestData() throws SQLException,
    ClassNotFoundException, LiquibaseException {

        Class.forName("org.h2.Driver");
        conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");

        Database database = DatabaseFactory.getInstance()
                .findCorrectDatabaseImplementation(new JdbcConnection(conn));

        liquibase = new Liquibase("src/test/resources/db/changelog/db.test.insert.xml",
                new FileSystemResourceAccessor(), database);
        liquibase.update("");
    }

    @AfterClass
    public static void removeTestData() throws LiquibaseException, SQLException {
        liquibase.rollback(1000, null);
        conn.close();
    }

    @Test
    public void test1() throws SQLException{
        System.out.print("test1 starts!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1!!!");

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            stmt = conn
                    .prepareStatement("select count(*) as numberOfUsers from test_scope");
            rs = stmt.executeQuery();
            rs.next();
            int numberOfUsers = rs.getInt("numberOfUsers");
            Assert.assertEquals(1, numberOfUsers);

        } finally {
            rs.close();
            stmt.close();
        }
    }
}
