package com.example.bunsanedthinking_springback.entity.paymentDetail;

import java.util.ArrayList;

import com.example.bunsanedthinking_springback.global.exception.NotExistException;

/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:43
 */
public interface PaymentDetailList {

	/**
	 *
	 * @param paymentDetail
	 */
	public void add(PaymentDetail paymentDetail);

	/**
	 *
	 * @param id
	 */
	public void delete(int id);

	/**
	 *
	 * @param id
	 */
	public PaymentDetail get(int id) throws NotExistException;

	public void update(PaymentDetail paymentDetail) throws NotExistException;

	public ArrayList<PaymentDetail> getAllPaymentDetail();

	public ArrayList<PaymentDetail> getAllUnprocessedPaymentDetail();

	public ArrayList<PaymentDetail> getAllCompletedPaymentDetail();

}
