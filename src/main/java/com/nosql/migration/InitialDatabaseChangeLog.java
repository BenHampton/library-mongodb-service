package com.nosql.migration;

import io.mongock.api.annotations.BeforeExecution;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackBeforeExecution;
import io.mongock.api.annotations.RollbackExecution;

@ChangeUnit(order = "001", id = "init commit", author = "bham")
public class InitialDatabaseChangeLog {

    @BeforeExecution
    public void beforeSeedDatabase() {
        System.out.println("before seeding database...");
    }

    @RollbackBeforeExecution
    public void rollbackBeforeExecution() {
        System.out.println("rollback before seeding database...");
    }

    @Execution
    public void executionMethodName() {
        System.out.println("execution seeding database...");
    }

    @RollbackExecution
    public void rollbackExecutionMethodName() {
        System.out.println("rollback execution seeding database...");
    }
}
