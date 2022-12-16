package com.example.homebudget.budget;

import com.example.homebudget.db.DataSourceProvider;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class BudgetItemDao {
    private final DataSource dataSource;

    public BudgetItemDao() {
        try {
            this.dataSource = DataSourceProvider.getDataSource();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(BudgetItem budgetItem) {
        String sql = "INSERT INTO budget_item(description, value, type) VALUES (?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, budgetItem.getDescription());
            statement.setBigDecimal(2, budgetItem.getValue());
            statement.setString(3, budgetItem.getType().name());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<BudgetItem> findAllItemsByType(BudgetItemType type) {
        String sql = "SELECT description, value, type FROM budget_item WHERE type = ?";
        List<BudgetItem> items = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, type.name());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String description = resultSet.getString("description");
                BigDecimal itemValue = resultSet.getBigDecimal("value");
                String itemType = resultSet.getString("type");
                items.add(new BudgetItem(description, itemValue, BudgetItemType.valueOf(itemType)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return items;
    }
}
