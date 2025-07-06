/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implement;

import dal.GenericDAO;
import entity.*;
import java.util.LinkedHashMap;
import java.util.List;
import constant.*;

/**
 *
 * @author nguye
 */
public class ProductDAO extends GenericDAO<Product>{

    @Override
    public List<Product> findAll() {
            return queryGenericDAO(Product.class);
}

    @Override
    public int insert(Product t) {
        return insertGenericDAO(t);
    }

    public Product findById(Product product) {

        String sql = "SELECT [id]\n" +
"      ,[name]\n" +
"      ,[image]\n" +
"      ,[quantity]\n" +
"      ,[price]\n" +
"      ,[description]\n" +
"      ,[categoryId]\n" +
"  FROM [dbo].[Product]\n" +
"  where id = ?";
        
        parameterMap = new LinkedHashMap<>();
        parameterMap.put("id",product.getId());
        List<Product> list =  queryGenericDAO(Product.class, sql, parameterMap);
        
        return list.isEmpty() ? null : list.get(0);
                
                
    }

    public List<Product> findByCategory(String categoryId,int page) {
        String sql = "select * from Product\n" +
"where categoryId = ?\n" +
"order by id\n" +
"offset ? rows\n" +//(page-1)* (number record/page)
"fetch next ? rows only";//(number record/page)
        
        parameterMap = new LinkedHashMap<>();
        parameterMap.put("categoryId",categoryId);
        parameterMap.put("offset",(page-1) * CommonConst.RECORD_PER_PAGE);
        parameterMap.put("fetch",CommonConst.RECORD_PER_PAGE);
        return queryGenericDAO(Product.class, sql, parameterMap);
        
    }

    public List<Product> findByName(String keyword,int page) {
        String sql = "SELECT *\n"
                + "  FROM [Product]\n"
                + "  where [name] like ?"
                + "  ORDER BY id\n"
                + "  OFFSET ? ROWS\n" //( PAGE - 1 ) * Y
                + "  FETCH NEXT ? ROWS ONLY"; // NUMBER_RECORD_PER_PAGE
        parameterMap = new LinkedHashMap<>();
        parameterMap.put("name", "%" + keyword + "%");
        parameterMap.put("offset", (page - 1) * CommonConst.RECORD_PER_PAGE);
        parameterMap.put("fetch", CommonConst.RECORD_PER_PAGE);
        return queryGenericDAO(Product.class, sql, parameterMap);

    }
    
        public List<Product> findByPage(int page) {
        String sql = "select *from Product\n" +
"\n" +
"order by id\n" +
"offset ? rows\n" +
"fetch next ? rows only";
         parameterMap = new LinkedHashMap<>();
        parameterMap.put("offset",(page-1) * CommonConst.RECORD_PER_PAGE);
        parameterMap.put("fetch",CommonConst.RECORD_PER_PAGE);
        return queryGenericDAO(Product.class, sql, parameterMap);
        
    }
    

    public int findToTalRecordByCategory(String categoryId) {
        String sql = "select count(*) from Product\n" +
"where categoryId = ?";
    
        parameterMap = new LinkedHashMap<>();
        parameterMap.put("categoryId",categoryId);
        return findTotalRecordGenericDAO(Product.class, sql, parameterMap);
    }

    public int findTotalRecord() {
        String sql = "select count(*) from Product";
        parameterMap = new LinkedHashMap<>();
        return findTotalRecordGenericDAO(Product.class, sql, parameterMap);
    
    }

    public int findTotalRecordByName(String keyword) {
         String sql = "select count(*) from Product\n" +
"where name like ?";
    
        parameterMap = new LinkedHashMap<>();
        parameterMap.put("name","%"+keyword+"%");
        return findTotalRecordGenericDAO(Product.class, sql, parameterMap);
    }

    public void deleteById(int id) {
         String sql = "DELETE FROM [dbo].[Product]\n"
                + "      WHERE [id] = ?";
        parameterMap = new LinkedHashMap<>();
        parameterMap.put("id", id);
        deleteGenericDAO(sql, parameterMap);
    }

    public void update(Product product) {
        String sql = "UPDATE [dbo].[Product]\n"
                + "   SET [name] = ?\n"
                + "      ,[image] = ?\n"
                + "      ,[quantity] = ?\n"
                + "      ,[price] = ?\n"
                + "      ,[description] = ?\n"
                + "      ,[categoryId] = ?\n"
                + " WHERE id = ?";
        parameterMap = new LinkedHashMap<>();
        parameterMap.put("name", product.getName());
        parameterMap.put("image", product.getImage());
        parameterMap.put("quantity", product.getQuantity());
        parameterMap.put("price", product.getPrice());
        parameterMap.put("description", product.getDescription());
        parameterMap.put("categoryId", product.getCategoryId());
        parameterMap.put("id", product.getId());
        updateGenericDAO(sql, parameterMap);
    }


}
