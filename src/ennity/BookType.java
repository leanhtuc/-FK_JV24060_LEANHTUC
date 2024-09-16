package ennity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookType implements IBookManagement{
private int typeId;
private String typeName;
private String description;
private boolean isDeleted;

public BookType() {
}

public BookType(int typeId, String typeName, String description, boolean isDeleted) {
    this.typeId = typeId;
    this.typeName = typeName;
    this.description = description;
    this.isDeleted = isDeleted;
}

public int getTypeId() {
    return typeId;
}

public void setTypeId(int typeId) {
    this.typeId = typeId;
}

public String getTypeName() {
    return typeName;
}

public void setTypeName(String typeName) {
    this.typeName = typeName;
}

public String getDescription() {
    return description;
}

public void setDescription(String description) {
    this.description = description;
}

public boolean isDeleted() {
    return isDeleted;
}

public void setDeleted(boolean deleted) {
    isDeleted = deleted;
}

@Override
public void inputData(Scanner scanner) {
    System.out.println("Nhập tên loại sách:");
    typeName=scanner.nextLine();
    System.out.println("Nhập mô tả loại sách:");
    description=scanner.nextLine();
}

@Override
public void displayData() {
        System.out.println("Mã loại sách: " + typeId);
        System.out.println("Tên loại sách: " + typeName);
        System.out.println("Mô tả: " + description);
    }
}
