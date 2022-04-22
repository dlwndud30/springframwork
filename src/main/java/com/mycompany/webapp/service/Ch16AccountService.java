package com.mycompany.webapp.service;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.mycompany.webapp.dao.mybatis.Ch16AccountDao;
import com.mycompany.webapp.dto.Ch16Account;
import com.mycompany.webapp.exception.Ch16NotFoundAccountException;

@Service
public class Ch16AccountService {
	private static final Logger logger = LoggerFactory.getLogger(Ch16AccountService.class);
	
	public enum TransferResult {
		SUCCESS,
		FAIL_NOT_FOUND_ACCOUNT,
		FAIL_NOT_ENOUGH_BALANCE
	}
	
	@Resource
	private TransactionTemplate transactionTemplate;
	
	@Resource
	private Ch16AccountDao accountDao;
	
	public List<Ch16Account> getAccounts() {
		//DB에서 계좌정보를 가져와서 테이블을 만드는
		logger.info("실행");
		List<Ch16Account> accounts = accountDao.selectAll();
		return accounts;
	}
	
	public TransferResult transfer1(int fromAno, int toAno, int amount) {
		logger.info("실행");
		
		String result = transactionTemplate.execute(new TransactionCallback<String>() {
			@Override
			public String doInTransaction(TransactionStatus status) {
				try {
					//출금하기
					Ch16Account fromAccount = accountDao.selectByAno(fromAno);  //계좌 있는지 검사
					if(fromAccount == null) {
						throw new Ch16NotFoundAccountException("출금 계좌 없음");  
					}
					fromAccount.setBalance(fromAccount.getBalance() - amount);
					accountDao.updateBalance(fromAccount);
					
					//입금하기
					Ch16Account toAccount = accountDao.selectByAno(toAno);		 //계좌 있는지 검사
					if(toAccount == null) {
						throw new Ch16NotFoundAccountException("입금 계좌 없음");  //계좌가 없으면 RuntimeException 발생 -> 예외가 발생하면 Catch로넘어가 Rollback
					}
					toAccount.setBalance(toAccount.getBalance() + amount);
					accountDao.updateBalance(toAccount);
					return "success";
				} catch(Exception e) {
					//트랜잭션 작업을 모두 취소
					status.setRollbackOnly();
					return "fail";
				}
			}
		});
		
		if(result.equals("success")) {
			return TransferResult.SUCCESS;
		} else {
			return TransferResult.FAIL_NOT_FOUND_ACCOUNT;
		}
	}
	
	@Transactional   //이거만 붙여주면 선언적 Transaction임 -> 예외가 발생되면 자동으로 rollback이 됨
	public void transfer2(int fromAno, int toAno, int amount) {
		logger.info("실행");
		
		//출금하기
		Ch16Account fromAccount = accountDao.selectByAno(fromAno);
		if(fromAccount == null) {
			throw new Ch16NotFoundAccountException("출금 계좌 없음");
		}
		fromAccount.setBalance(fromAccount.getBalance() - amount);
		accountDao.updateBalance(fromAccount);
		
		//예금하기
		Ch16Account toAccount = accountDao.selectByAno(toAno);
		if(toAccount == null) {
			throw new Ch16NotFoundAccountException("입금 계좌 없음");
		}
		toAccount.setBalance(toAccount.getBalance() + amount);
		accountDao.updateBalance(toAccount);
	}
}














