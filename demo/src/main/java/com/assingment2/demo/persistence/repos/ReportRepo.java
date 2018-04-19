package com.assingment2.demo.persistence.repos;

import com.assingment2.demo.persistence.entities.Report;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class ReportRepo implements IReportRepo {
    Firestore firestore;

    public ReportRepo() {
        firestore = FirestoreClient.getFirestore();
    }

    @Override
    public void store(Report report) {
        firestore.collection("reports").add(report);
    }

    @Override
    public Set<Report> retrieveReports() {
        ApiFuture<QuerySnapshot> future = firestore.collection("reports").get();
        List<QueryDocumentSnapshot> documents = null;
        try {
            documents = future.get().getDocuments();
            return documents.stream().map(x -> x.toObject(Report.class)).collect(Collectors.toSet());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
