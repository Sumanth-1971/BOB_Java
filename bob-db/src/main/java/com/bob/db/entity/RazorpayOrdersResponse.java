package com.bob.db.entity;

import com.bob.db.dto.RazorpayOrderDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RazorpayOrdersResponse {
    RazorpayOrderDto razorpayOrderDetails;
    String candidateName;
    String candidateEmail;
    String candidateCurrentDesignation;
    String positionTitle;
    String positionDescription;
}
