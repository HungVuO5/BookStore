/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implement;
import dal.GenericDAO;
import entity.*;
import java.util.List;
/**
 *
 * @author nguye
 */
public class RoleDAO extends GenericDAO<Role>{

    @Override
    public List<Role> findAll() {
        return queryGenericDAO(Role.class);    
    }

    @Override
    public int insert(Role t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
