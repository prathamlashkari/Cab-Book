package com.CabBook.cab.models;

import com.CabBook.cab.enums.PaymentStatus;

import lombok.Data;

@Data
public class PaymentDetails {

  private PaymentStatus paymentStatus;
  private String paymentId;
  private String razorpayPaymentLinkId;
  private String razorpayPaymentLinkRefrenceId;
  private String razorpayPaymentLinkStatus;
  private String razorPaymentId;

}
