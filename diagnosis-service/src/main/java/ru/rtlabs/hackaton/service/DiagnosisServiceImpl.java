package ru.rtlabs.hackaton.service;

import org.hl7.fhir.dstu3.model.DiagnosticReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rtlabs.hackaton.repository.DiagnosisRepository;

import java.util.Optional;

@Service
public class DiagnosisServiceImpl implements DiagnosisService {

    private final DiagnosisRepository diagnosticReportRepository;


    @Autowired
    public DiagnosisServiceImpl(DiagnosisRepository diagnosticReportRepository) {
        this.diagnosticReportRepository = diagnosticReportRepository;
    }


    @Override
    public Iterable<?> findAll() {
        return diagnosticReportRepository.findAll();
    }

    @Override
    public DiagnosticReport findByIdMyUnqualifiedId(String id) {
        Optional<DiagnosticReport> optionalDiagnosticReport = diagnosticReportRepository.findByIdMyUnqualifiedId(id);
        return optionalDiagnosticReport.orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public DiagnosticReport save(DiagnosticReport diagnosticReport) {
        return diagnosticReportRepository.save(diagnosticReport);
    }

    @Override
    public DiagnosticReport update(String id, DiagnosticReport diagnosticReport) {
        Optional<DiagnosticReport> optionalDiagnosticReport = diagnosticReportRepository.findByIdMyUnqualifiedId(id);
        DiagnosticReport update = optionalDiagnosticReport.orElseThrow(IllegalArgumentException::new);
        update.setId(diagnosticReport.getId());
        update.setCode(diagnosticReport.getCode());
        update.setStatus(diagnosticReport.getStatus());
        update.setResult(diagnosticReport.getResult());
        return diagnosticReportRepository.save(update);
    }

    @Override
    public void deleteByIdMyUnqualifiedId(String id) {
        diagnosticReportRepository.deleteByIdMyUnqualifiedId(id);
    }

    @Override
    public DiagnosticReport findByObservationId(String id) {
        Optional<DiagnosticReport> optionalDiagnosticReport = diagnosticReportRepository.findByResultId(id);
        return  optionalDiagnosticReport.orElseThrow(IllegalArgumentException::new);
    }
}
