package com.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.bean.UserPO;
import com.model.UserModel;
import com.repo.UserRepository;
import com.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	public List<UserPO> findAll(UserModel userModel) {
	//	Assert.notNull(id, "[Assertion failed] - this argument [id] is required; it must not be null");
		Pageable pageRequest = new PageRequest(userModel.getPage().intValue(), userModel.getSize().intValue());
		List<UserPO> userList = userRepository.findAll(pageRequest).getContent();
		return userList;
	}

	@Override
	public UserPO save(UserPO userPO) {
		UserPO save = userRepository.save(userPO);
		return save;
	}

	@Override
	public UserPO login(UserModel userModel) {
		UserPO userPO =	userRepository.findOne(isLongTermCustomer(userModel));
		return userPO;
	}
	
	public static  Specification<UserPO> isLongTermCustomer(final UserModel userModel) {
		return new Specification<UserPO>() {
		public Predicate toPredicate(Root<UserPO> root, CriteriaQuery<?> query,
		CriteriaBuilder builder) {
		List<Predicate> list = new ArrayList<Predicate>();  

		if(!StringUtils.isEmpty(userModel.getUserName())){
		list.add(builder.and(builder.equal(root.get("userName"), userModel.getUserName())));
		}
		if(!StringUtils.isEmpty(userModel.getPassWord())){
		list.add(builder.and(builder.equal(root.get("passWord"), userModel.getPassWord())));
		}

		Predicate[] p = new Predicate[list.size()];  
		query.where(builder.and(list.toArray(p)));
		return null;
		}
		};
		}

}
