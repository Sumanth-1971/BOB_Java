// File: src/main/java/com/example/JobCreation/dto/JobRequisitionApprovalRequest.java

package com.bob.JobCreation.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class JobRequisitionApprovalRequest {
    private List<UUID> requisition_id_list;
    private String approval_status;
    private String user_id;
    private String comments;
    private String user_role;
}