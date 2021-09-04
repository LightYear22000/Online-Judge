package com.onlinejudge.dispatcher.repositories;

import com.onlinejudge.dispatcher.models.Submission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionRepository extends CrudRepository<Submission, Long> {
}
