package web.petstore.domain;

import java.io.Serializable;//序列化的主要作用是将对象的状态转换为一种可以保存或传输的形式，方便对象在不同环境下的持久化或传递。

public class Category implements Serializable{
    private static final long serialVersionUID = 3992469837058393712L;

    private String categoryId;
    private String name;
    private String description;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        return getCategoryId();
    }

}
