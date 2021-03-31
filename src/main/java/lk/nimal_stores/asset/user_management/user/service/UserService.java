package lk.nimal_stores.asset.user_management.user.service;


import lk.nimal_stores.asset.common_asset.model.enums.LiveDead;
import lk.nimal_stores.asset.employee.entity.Employee;
import lk.nimal_stores.asset.user_management.user.dao.UserDao;
import lk.nimal_stores.asset.user_management.user.entity.User;
import lk.nimal_stores.util.interfaces.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements AbstractService< User, Integer > {
  private final UserDao userDao;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserService(PasswordEncoder passwordEncoder, UserDao userDao) {
    this.passwordEncoder = passwordEncoder;
    this.userDao = userDao;
  }


  public List< User > findAll() {
    return userDao.findAll().stream()
        .filter(x -> LiveDead.ACTIVE.equals(x.getLiveDead()))
        .collect(Collectors.toList());
  }

  public User findById(Integer id) {
    return userDao.getOne(id);
  }


  public User persist(User user) {
    user.setUsername(user.getUsername().toLowerCase());
    if ( user.getPassword() != null ) {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
    } else {
      user.setPassword(userDao.getOne(user.getId()).getPassword());
    }
    if ( user.getId() == null ) {
      user.setLiveDead(LiveDead.ACTIVE);
    }
    return userDao.save(user);
  }


  public boolean delete(Integer id) {
    //according to this project can not be deleted user
    User user = userDao.getOne(id);
    user.setLiveDead(LiveDead.STOP);
    userDao.save(user);
    return false;
  }


  public List< User > search(User user) {
    ExampleMatcher matcher =
        ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
    Example< User > userExample = Example.of(user, matcher);
    return userDao.findAll(userExample);
  }


  public Integer findByUserIdByUserName(String userName) {
    return userDao.findUserIdByUserName(userName);
  }


  @Transactional( readOnly = true )
  public User findByUserName(String name) {
    return userDao.findByUsername(name);
  }


  public User findUserByEmployee(Employee employee) {
    return userDao.findByEmployee(employee);
  }


  public boolean findByEmployee(Employee employee) {
    return userDao.findByEmployee(employee) == null;
  }


  public List< User > findByLiveDead(LiveDead live_dead) {
    return userDao.findByLiveDead(live_dead);
  }
}
