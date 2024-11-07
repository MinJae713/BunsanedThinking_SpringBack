package com.example.bunsanedthinking_springback.entity.paymentDetail;

import java.util.ArrayList;

import com.example.bunsanedthinking_springback.global.exception.NotExistException;

/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:43
 */
public class PaymentDetailListImpl implements PaymentDetailList {

	private static int index = 0;
	private ArrayList<PaymentDetail> paymentDetailList;

	public PaymentDetailListImpl() {
		this.paymentDetailList = new ArrayList<>();
	}

	/**
	 *
	 * @param paymentDetail
	 */
	public void add(PaymentDetail paymentDetail) {

		index++;
		String compound = PaymentDetail.PAYMENT_DETAIL_SERIAL_NUMBER + "" + index;
		paymentDetail.setId(Integer.parseInt(compound));
		this.paymentDetailList.add(paymentDetail);
	}

	/**
	 *
	 * @param id
	 */
	public void delete(int id) {

	}

	/**
	 *
	 * @param id
	 */
	public PaymentDetail get(int id) throws NotExistException {
		for (PaymentDetail paymentDetail : paymentDetailList) {
			if (paymentDetail.getId() == id) {
				return paymentDetail.clone();
			}
		}
		throw new NotExistException();
	}

	public void update(PaymentDetail paymentDetail) throws NotExistException {
		for (int i = 0; i < paymentDetailList.size(); i++) {
			if (paymentDetailList.get(i).getId() == paymentDetail.getId()) {
				paymentDetailList.set(i, paymentDetail);
				return;
			}
		}
		throw new NotExistException();
	}

	@Override
	public ArrayList<PaymentDetail> getAllPaymentDetail() {
		ArrayList<PaymentDetail> paymentDetails = new ArrayList<>();
		for (PaymentDetail paymentDetail : paymentDetailList) {
			paymentDetails.add(paymentDetail.clone());
		}
		return paymentDetails;
	}

	@Override
	public ArrayList<PaymentDetail> getAllUnprocessedPaymentDetail() {
		ArrayList<PaymentDetail> payments = new ArrayList<>();
		for (PaymentDetail paymentDetail : paymentDetailList) {
			if (paymentDetail.getProcessStatus() == PaymentProcessStatus.Unprocessed) {
				payments.add(paymentDetail.clone());
			}
		}
		return payments;
	}

	@Override
	public ArrayList<PaymentDetail> getAllCompletedPaymentDetail() {
		ArrayList<PaymentDetail> payments = new ArrayList<>();
		for (PaymentDetail paymentDetail : paymentDetailList) {
			if (paymentDetail.getProcessStatus() == PaymentProcessStatus.Completed) {
				payments.add(paymentDetail.clone());
			}
		}
		return payments;
	}

}
