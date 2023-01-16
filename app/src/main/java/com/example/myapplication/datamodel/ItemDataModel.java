package com.example.myapplication.datamodel;

public class ItemDataModel {

    int ExpenseId;
    String ExpenseName;
    public ItemDataModel(int expenseId, String expenseName) {
        ExpenseId = expenseId;
        ExpenseName = expenseName;
    }
    public int getExpenseId() {
        return ExpenseId;
    }
    public String getExpenseName() {
        return ExpenseName;
    }
}
