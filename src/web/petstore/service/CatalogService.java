package web.petstore.service;

import web.petstore.domain.Category;
import web.petstore.domain.Item;
import web.petstore.domain.Product;
import web.petstore.persistence.CategoryDao;
import web.petstore.persistence.ItemDao;
import web.petstore.persistence.ProductDao;

import java.util.List;

public class CatalogService {
    private CategoryDao categoryDao;
    private ItemDao itemDao;
    private ProductDao productDao;

    public CatalogService(){
        this.categoryDao=new CategoryDao();
        this.itemDao=new ItemDao();
        this.productDao=new ProductDao();
    }

    public List<Category> getCategoryList() {
        return categoryDao.getCategoryList();
    }

    public Category getCategory(String categoryId) {
        return categoryDao.getCategory(categoryId);
    }

    public Product getProduct(String productId) {
        return productDao.getProduct(productId);
    }

    public List<Product> getProductListByCategory(String categoryId) {
        return productDao.getProductListByCategory(categoryId);
    }

    // TODO enable using more than one keyword
    public List<Product> searchProductList(String keyword) {
        return productDao.searchProductList("%" + keyword.toLowerCase() + "%");
    }

    public List<Item> getItemListByProduct(String productId) {
        return itemDao.getItemListByProduct(productId);
    }

    public Item getItem(String itemId) {
        return itemDao.getItem(itemId);
    }

    public boolean isItemInStock(String itemId) {
        return itemDao.getInventoryQuantity(itemId) > 0;
    }
}
