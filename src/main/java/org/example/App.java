package org.example;

import org.apache.commons.dbcp2.BasicDataSource;

/**
 * Hello world!
 *
 */
public class App 
{
    private static BasicDataSource dataSource;
    public static void main( String[] args )
    {
        dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/classroom");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");
    }
}
