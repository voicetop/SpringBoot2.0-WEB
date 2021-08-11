package com.test.springtest.reopsitory;

import com.test.springtest.domain.Member;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.criteria.internal.CriteriaUpdateImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaUpdate;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class MemberRepositoryImpl implements MemberRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Member> getMemberList() {
        return entityManager.createQuery("select m from Member m",
                Member.class).getResultList();
    }

    public Member getMemberById(String id) {
        return entityManager.createQuery("select m from Member m where m.id = :id",
                Member.class).setParameter("id", id).getSingleResult();
    }

    public void insertMember(Member member) {
        entityManager.persist(member);
    }

    public Member updateMember(Member member) {
        return entityManager.merge(member);
    }

    public void deleteMember(Member member) {
        entityManager.remove(member);
    }

}