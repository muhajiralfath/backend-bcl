package com.xfour.businesscapitalloan.service.impl;

import com.xfour.businesscapitalloan.entity.Bill;
import com.xfour.businesscapitalloan.model.request.SearchBillRequest;
import com.xfour.businesscapitalloan.model.request.UpdateBillAdminRequest;
import com.xfour.businesscapitalloan.model.request.UpdateBillUmkmRequest;
import com.xfour.businesscapitalloan.model.response.BillResponse;
import com.xfour.businesscapitalloan.model.response.UmkmResponse;
import com.xfour.businesscapitalloan.repository.BillRepository;
import com.xfour.businesscapitalloan.service.BillService;
import com.xfour.businesscapitalloan.service.UmkmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BillServiceImpl implements BillService {
    private final BillRepository billRepository;
    private final UmkmService umkmService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Bill create(Bill bill) {
        log.info("start of create bill");
        Bill save = billRepository.save(bill);
        log.info("end of create bill");

        return save;
    }

    @Override
    public Bill findById(String id) {
        return billRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "bill not found"));
    }

    @Transactional(readOnly = true)
    @Override
    public BillResponse getById(String id) {
        log.info("start of get bill by id");
        Bill bill = findById(id);
        log.info("end of get bill by id");

        return toBillResponse(bill);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<BillResponse> getAll(SearchBillRequest request) {
        log.info("start of getAllBill");
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<Bill> provisions = billRepository.findAll(pageable);

        List<BillResponse> billResponses = new ArrayList<>();
        for (Bill bill: provisions.getContent()) {
            billResponses.add(toBillResponse(bill));
        }
        log.info("end of getAllBill");

        return new PageImpl<>(billResponses, pageable, provisions.getTotalElements());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public BillResponse updateForUmkm(UpdateBillUmkmRequest request) {
        log.info("start of updateForUmkm");
        Bill bill = findById(request.getId());
        bill.setIsPaid(request.getIsPaid());
        billRepository.save(bill);
        log.info("end of updateForUmkm");

        return toBillResponse(bill);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public BillResponse updateForAdmin(UpdateBillAdminRequest request) {
        log.info("start of updateForAdmin");
        Bill bill = findById(request.getId());
        bill.setIsVerify(request.getIsVerify());
        billRepository.save(bill);
        log.info("end of updateForAdmin");

        return toBillResponse(bill);
    }

    @Override
    public List<BillResponse> getAllBillByDebtorId(String debtorId) {
        log.info("get all bill by debtor id");
        UmkmResponse umkm = umkmService.getByDebtorId(debtorId);

        List<Bill> allByUmkmId = billRepository.findAllByUmkmId(umkm.getUmkmId());
        List<BillResponse> billResponseList = new ArrayList<>();

        for (Bill bill : allByUmkmId) {
            BillResponse billResponse = toBillResponse(bill);
            billResponseList.add(billResponse);
        }

        log.info("finish get all bill by debtor id");
        return billResponseList;
    }

    private BillResponse toBillResponse(Bill bill) {
        return BillResponse.builder()
                .id(bill.getId())
                .provisionId(bill.getProvision().getId())
                .umkmId(bill.getUmkm().getId())
                .umkmName(bill.getUmkm().getName())
                .debtorName(bill.getUmkm().getDebtor().getName())
                .debt(bill.getDebt())
                .interest(bill.getInterest())
                .dueDate(bill.getDueDate())
                .isPaid(bill.getIsPaid())
                .isVerify(bill.getIsVerify())
                .build();
    }
    @Scheduled(cron = "0 0 0 * * ?")
    public void startFineDate(){
        List<Bill> fineDateList = billRepository.findAllByIsPaidFalseAndFineDateLessThan(System.currentTimeMillis());
        fineDateList.forEach((bill) -> {
            Long newDebt  = bill.getDebt() + (bill.getDebt() / 100);
            bill.setDebt(newDebt);
            billRepository.save(bill);
        });
    }

}

