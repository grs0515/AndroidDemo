package com.grs.demo.designmode.factory;

/**
 * Created by gaoruishan on 16/4/4.
 */
public class DaoImpUser implements IDao<User> {

    @Override
    public void save(User user) {
        System.out.print("保存:"+user.getName()+","+user.getAge());
    }

    @Override
    public void delete(int id) {
        System.out.println("删除:"+id);
    }
}
