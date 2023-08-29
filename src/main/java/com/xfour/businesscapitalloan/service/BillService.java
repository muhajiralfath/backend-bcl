package com.xfour.businesscapitalloan.service;

import com.xfour.businesscapitalloan.entity.Bill;
import com.xfour.businesscapitalloan.model.request.SearchBillRequest;
import com.xfour.businesscapitalloan.model.request.UpdateBillAdminRequest;
import com.xfour.businesscapitalloan.model.request.UpdateBillUmkmRequest;
import com.xfour.businesscapitalloan.model.response.BillResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BillService {
    Bill create(Bill bill);
    Bill findById(String id);
    BillResponse getById(String id);
    Page<BillResponse> getAll(SearchBillRequest request);
    BillResponse updateForUmkm(UpdateBillUmkmRequest request);
    BillResponse updateForAdmin(UpdateBillAdminRequest request);
    List<BillResponse> getAllBillByDebtorId(String debtorId);
}
