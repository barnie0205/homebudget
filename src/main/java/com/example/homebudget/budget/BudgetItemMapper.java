package com.example.homebudget.budget;

class BudgetItemMapper {
    static BudgetItemDto fromEntity(BudgetItem budgetItem) {
        return new BudgetItemDto(budgetItem.getDescription(), budgetItem.getValue());
    }

    static BudgetItem toEntity(BudgetItemDto dto, BudgetItemType type) {
        return new BudgetItem(dto.getDescription(), dto.getValue(), type);
    }
}
